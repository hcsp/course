package com.github.hcsp.course.controller;

import com.alipay.api.AlipayApiException;
import com.github.hcsp.course.configuration.Config;
import com.github.hcsp.course.dao.CourseDao;
import com.github.hcsp.course.dao.CourseOrderDao;
import com.github.hcsp.course.model.Course;
import com.github.hcsp.course.model.CourseOrder;
import com.github.hcsp.course.model.HttpException;
import com.github.hcsp.course.model.OrderStatus;
import com.github.hcsp.course.model.User;
import com.github.hcsp.course.service.AlipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * 订单、支付相关
 */
@RestController
public class OrderController {
    @Autowired
    CourseOrderDao courseOrderDao;
    @Autowired
    CourseDao courseDao;
    @Autowired
    AlipayService alipayService;

    /**
     * @api {get} /showPay?courseId={id} 获取指定id的课程的付款页面
     * @apiName 获取指定id课程的付款页面
     * @apiGroup 订单支付管理
     * @apiDescription
     *  调用支付宝的接口，获取付款页面HTML。
     *
     * @apiHeader {String} Accept text/html
     *
     * @apiParamExample Request-Example:
     *    GET /showPay?courseId=1
     * @apiSuccess {HTML} html 付款页面的HTML
     * @apiSuccessExample Success-Response:
     *
     *     HTTP/1.1 200 OK
     *     <html>
     *        付款页面HTML
     *     </html>
     * @apiError 400 Bad Request 若请求中包含错误
     * @apiError 401 Unauthorized 若未登录
     *
     * @apiErrorExample Error-Response:
     *     HTTP/1.1 403 Forbidden
     *     {
     *       "message": "Forbidden"
     *     }
     */
    /**
     * @param courseId
     * @return
     */
    @GetMapping("/showPay")
    @ResponseBody
    public String showPay(
            @RequestParam("courseId") Integer courseId,
            HttpServletResponse response
    ) throws IOException {
        // 检查对应的订单，如果已经创建了，检查订单状态：
        //  若已经付款，直接跳转到对应的课程页面
        //  若未付款，跳转到支付宝页面

        User user = Config.UserContext.getCurrentUserOr401();
        Optional<CourseOrder> order = courseOrderDao.findByCourseIdAndUserId(courseId, user.getId());
        if (order.isEmpty()) {
            Course course = courseDao.findById(courseId).orElseThrow(
                    () -> new HttpException(404, "课程不存在: " + courseId)
            );
            CourseOrder newOrder = new CourseOrder();
            newOrder.setPrice(course.getPrice());
            newOrder.setStatus(OrderStatus.UNPAID);
            newOrder.setCourse(course);
            newOrder.setUser(user);
            courseOrderDao.save(newOrder);
            return alipayService.getPayPageHtml(newOrder);
        } else {
            if (order.get().getStatus() == OrderStatus.PAID) {
                // 已经付过款，无需再次付款
                response.sendRedirect("http://localhost:8080/api/v1/course/" + courseId);
                return "";
            } else {
                return alipayService.getPayPageHtml(order.get());
            }
        }
    }

    /**
     * @api {get} /checkPay?orderId={id} 付款完成之后检查支付状态页面
     * @apiName 检查支付状态页面
     * @apiGroup 订单支付管理
     * @apiDescription
     *  在付款完成后，由支付宝负责在浏览器端跳转到此页面，
     *  后端收到此请求后开始向检查订单状态并修改对应数据库的状态。
     *  若订单已经付款，跳转到该订单对应到课程页面。
     *
     * @apiParamExample Request-Example:
     *    GET /checkPay?orderId=123
     * @apiSuccess {HTML} html 付款页面的HTML
     * @apiSuccessExample Success-Response:
     *
     *     HTTP/1.1 302 Found
     * @apiError 400 Bad Request 若请求中包含错误
     * @apiError 401 Unauthorized 若未登录
     * @apiError 403 Forbidden 若非本人订单
     *
     * @apiErrorExample Error-Response:
     *     HTTP/1.1 403 Forbidden
     *     {
     *       "message": "Forbidden"
     *     }
     */
    /**
     * @param orderId
     */
    @GetMapping("/checkPay")
    @ResponseBody
    public void checkPay(
            @RequestParam("orderId") Integer orderId,
            @RequestParam("trade_no") String alipayTradeNo,
            HttpServletResponse response
    ) throws IOException, AlipayApiException {
        User user = Config.UserContext.getCurrentUserOr401();
        CourseOrder order = courseOrderDao.findById(orderId).orElseThrow(
                () -> new HttpException(404, "Not found")
        );

        if (!user.getId().equals(order.getUser().getId())) {
            throw new HttpException(403, "Forbidden");
        }

        if (order.getStatus() == OrderStatus.UNPAID) {
            // 如果能从支付宝处查到交易记录，则将订单状态设置为PAID
            String status = alipayService.checkOrderStatus(order, alipayTradeNo);
            if ("TRADE_SUCCESS".equals(status)) {
                order.setStatus(OrderStatus.PAID);
                courseOrderDao.save(order);
            }
        }
        response.sendRedirect("http://localhost:8080/api/v1/course/" + order.getCourse().getId());
    }
}
