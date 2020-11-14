package com.github.hcsp.course.controller;

import com.github.hcsp.course.model.Course;
import com.github.hcsp.course.model.PageResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * 课程管理相关
 */
@RestController
@RequestMapping("/api/v1")
public class CourseController {
    /**
     * @api {get} /api/v1/course 获取课程列表
     * @apiName 获取课程列表
     * @apiGroup 课程管理
     * @apiDescription
     *  获取分页的课程信息。课程信息里包含视频信息和教师信息。
     *  若视频的URL为空，证明未购买该课程。
     *
     * @apiHeader {String} Accept application/json
     * @apiParam {String} [search] 搜索关键字
     * @apiParam {Number} [pageSize] 每页包含多少个课程
     * @apiParam {Number} [pageNum] 页码，从1开始
     * @apiParam {String} [orderBy] 排序字段，如price/createdAt
     * @apiParam {String} [orderType] 排序方法，Asc/Desc
     *
     * @apiParamExample Request-Example:
     *            GET /api/v1/course?pageSize=10&pageNum=1&orderBy=price&orderType=Desc&search=21天
     * @apiSuccess {Number} totalPage 总页数
     * @apiSuccess {Number} pageNum 当前页码，从1开始
     * @apiSuccess {Number} pageSize 每页包含多少个课程
     * @apiSuccess {List[Course]} data 课程列表
     *
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     {
     *       "totalPage": 100,
     *       "pageSize": 10,
     *       "pageNum": 1,
     *       "data": [
     *          {
     *             "id": 12345,
     *             "name": "21天精通C++",
     *             "teacherName": "Torvalds Linus",
     *             "teacherDescription": "Creator of Linux",
     *             videos: [
     *                {
     *                    "id": 456,
     *                    "name": "第一课",
     *                    "description": "",
     *                    "url": "https://oss.aliyun.com/xxx"
     *                }
     *             ]
     *             price: 9900,
     *             purchased: true
     *          }
     *       ]
     *     }
     * @apiError 400 Bad request 若请求中包含错误
     *
     * @apiErrorExample Error-Response:
     *     HTTP/1.1 400 Bad Request
     *     {
     *       "message": "Bad Request"
     *     }
     */
    /**
     * @param pageSize
     * @param pageNum
     * @param orderBy
     * @return
     */
    @GetMapping("/course")
    public PageResponse<Course> getCourses(
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("orderBy") String orderBy
    ) {
        return null;
    }

    /**
     * @api {get} /api/v1/course/{id} 获取课程
     * @apiName 获取课程
     * @apiGroup 课程管理
     * @apiDescription
     *  获取指定id的课程信息。课程信息里包含视频信息和教师信息。
     *  若视频的URL为空，证明未购买该课程。
     *
     * @apiHeader {String} Accept application/json
     *
     * @apiParamExample Request-Example:
     *            GET /api/v1/course/123
     *
     * @apiSuccess {Course} course 课程信息
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *          {
     *             "id": 12345,
     *             "name": "21天精通C++",
     *             "teacherName": "Torvalds Linus",
     *             "teacherDescription": "Creator of Linux",
     *             "videos": [
     *                {
     *                    "id": 456,
     *                    "name": "第一课",
     *                    "description": "",
     *                    "url": "https://oss.aliyun.com/xxx"
     *                }
     *             ]
     *             "price": 9900,
     *             "purchased": false
     *          }
     * @apiError 400 Bad request 若请求中包含错误
     *
     * @apiErrorExample Error-Response:
     *     HTTP/1.1 400 Bad Request
     *     {
     *       "message": "Bad Request"
     *     }
     */
    /**
     * @param id
     * @return
     */
    @GetMapping("/course/{id}")
    public Course getCourse(@PathParam("id") Integer id) {
        return null;
    }

    /**
     * @api {post} /api/v1/course 创建课程
     * @apiName 创建课程
     * @apiGroup 课程管理
     * @apiDescription
     *  填写必要信息，创建一门课程。需要"课程管理"权限。
     *
     * @apiHeader {String} Accept application/json
     * @apiHeader {String} Content-Type application/json
     *
     * @apiParamExample Request-Example:
     *          POST /api/v1/course
     *          {
     *             "id": 12345,
     *             "name": "21天精通C++",
     *             "teacherName": "Torvalds Linus",
     *             "teacherDescription": "Creator of Linux",
     *             "price": 9900
     *          }
     * @apiSuccess {Course} course 新创建的课程信息
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *          {
     *             "id": 12345,
     *             "name": "21天精通C++",
     *             "teacherName": "Torvalds Linus",
     *             "teacherDescription": "Creator of Linux",
     *             "price": 9900
     *          }
     * @apiError 400 Bad Request 若请求中包含错误
     * @apiError 401 Unauthorized 若未登录
     * @apiError 403 Forbidden 若无权限
     *
     * @apiErrorExample Error-Response:
     *     HTTP/1.1 400 Bad Request
     *     {
     *       "message": "Bad Request"
     *     }
     */
    /**
     * @return
     */
    @PostMapping("/course")
    public Course createCourse(@RequestBody Course course) {
        return null;
    }
    /**
     * @api {delete} /api/v1/course 删除课程
     * @apiName 删除课程
     * @apiGroup 课程管理
     * @apiDescription
     *  需要"课程管理"权限。
     *
     * @apiHeader {String} Accept application/json
     * @apiHeader {String} Content-Type application/json
     *
     * @apiParamExample Request-Example:
     *            DELETE /api/v1/course
     *
     * @apiSuccess {String} empty 空字符串
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 204 No Content
     * @apiError 400 Bad Request 若请求中包含错误
     * @apiError 401 Unauthorized 若未登录
     * @apiError 403 Forbidden 若无权限
     *
     * @apiErrorExample Error-Response:
     *     HTTP/1.1 400 Bad Request
     *     {
     *       "message": "Bad Request"
     *     }
     */
    /**
     * @return
     */
    @DeleteMapping("/course/{id}")
    public void deleteCourse(@PathVariable("id") Integer id) {
    }

    /**
     * @api {patch} /api/v1/course/{id} 修改课程
     * @apiName 修改课程
     * @apiGroup 课程管理
     * @apiDescription
     *  需要"课程管理"权限。
     *
     * @apiHeader {String} Accept application/json
     * @apiHeader {String} Content-Type application/json
     *
     * @apiParamExample Request-Example:
     * PATCH /api/v1/course
     *          {
     *             "id": 12345,
     *             "name": "21天精通C++",
     *             "teacherName": "Torvalds Linus",
     *             "teacherDescription": "Creator of Linux",
     *             "price": 9900
     *          }
     *
     * @apiSuccess {Course} course 修改后的课程信息
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *          {
     *             "id": 12345,
     *             "name": "21天精通C++",
     *             "teacherName": "Torvalds Linus",
     *             "teacherDescription": "Creator of Linux",
     *             "price": 9900
     *          }
     * @apiError 400 Bad Request 若请求中包含错误
     * @apiError 401 Unauthorized 若未登录
     * @apiError 403 Forbidden 若无权限
     *
     * @apiErrorExample Error-Response:
     *     HTTP/1.1 400 Bad Request
     *     {
     *       "message": "Bad Request"
     *     }
     */
    /**
     * @return
     */
    @PatchMapping("/course/{id}")
    public Course updateCourse(@PathVariable("id")Integer id, @RequestBody Course course) {
        return null;
    }
}
