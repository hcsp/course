package com.github.hcsp.course.controller;

import com.github.hcsp.course.model.HttpException;
import com.github.hcsp.course.model.PageResponse;
import com.github.hcsp.course.model.User;
import com.github.hcsp.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理，如授予某个用户管理员权限等。
 */
@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * @api {get} /api/v1/user 获取用户列表
     * @apiName 获取用户列表
     * @apiGroup 用户管理
     * @apiDescription
     *  管理员才能访问，获取分页的用户信息，支持搜索
     *
     * @apiHeader {String} Accept application/json
     * @apiParam {Number} [pageSize] 每页包含多少个用户
     * @apiParam {Number} [pageNum] 页码，从1开始
     * @apiParam {String} [orderBy] 排序字段，如username/createdAt
     * @apiParam {String} [orderType] 排序方法，Asc/Desc
     *
     * @apiParamExample Request-Example:
     *            GET /api/v1/user?pageSize=10&pageNum=1&orderBy=price&orderType=Desc&search=zhang
     *
     * @apiSuccess {Number} totalPage 总页数
     * @apiSuccess {Number} pageNum 当前页码，从1开始
     * @apiSuccess {Number} pageSize 每页包含多少个用户
     * @apiSuccess {List[User]} data 用户列表
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     {
     *       "totalPage": 100,
     *       "pageSize": 10,
     *       "pageNum": 1,
     *       "data": [
     *          {
     *              "id": 12345
     *              "username": "zhangsan"
     *              "roles": [
     *                  {
     *                      "name": "管理员"
     *                  }
     *              ]
     *          }
     *       ]
     *     }
     * @apiError 400 Bad request 若请求中包含错误
     * @apiError 401 Unauthorized 若未登录
     * @apiError 403 Forbidden 若没有权限
     *
     * @apiErrorExample Error-Response:
     *     HTTP/1.1 401 Unauthorized
     *     {
     *       "message": "Unauthorized"
     *     }
     */
    /**
     * @param search
     * @param pageSize
     * @param pageNum
     * @param orderBy
     * @return
     */
    @GetMapping("/user")
    public PageResponse<User> getAllUsers(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @RequestParam(value = "orderBy", required = false) String orderBy,
            @RequestParam(value = "orderType", required = false) String orderType
    ) {
        if (pageSize == null) {
            pageSize = 10;
        }
        if (pageNum == null) {
            pageNum = 1;
        }
        if (orderType != null && orderBy == null) {
            throw new HttpException(400, "缺少orderBy!");
        }
        return userService.getAllUsers(search, pageSize, pageNum, orderBy, orderType == null ? null : Sort.Direction.fromString(orderType));
    }

    /**
     * @api {get} /api/v1/user/{id} 获取指定id的用户
     * @apiName 获取指定id的用户
     * @apiGroup 用户管理
     * @apiDescription
     *  管理员才能访问此接口
     *
     * @apiHeader {String} Accept application/json
     * @apiHeader {String} Content-Type application/json
     *
     * @apiParamExample Request-Example:
     *    GET /api/v1/user/1
     * @apiSuccess {User} user 用户信息
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *          {
     *              "id": 12345
     *              "username": "zhangsan"
     *              "roles": [
     *                  {
     *                      "name": "管理员"
     *                  }
     *              ]
     *          }
     * @apiError 400 Bad Request 若请求中包含错误
     * @apiError 401 Unauthorized 若未登录
     * @apiError 403 Forbidden 若没有权限
     * @apiError 404 Not Found 若用户未找到
     *
     * @apiErrorExample Error-Response:
     *     HTTP/1.1 403 Forbidden
     *     {
     *       "message": "Forbidden"
     *     }
     */
    /**
     * @param id 用户ID
     * @return 获得的用户
     */
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    /**
     * @api {patch} /api/v1/user 更新用户
     * @apiName 更新用户信息（权限）
     * @apiGroup 用户管理
     * @apiDescription
     *  管理员才能访问，获取分页的用户信息，支持搜索*
     *
     * @apiHeader {String} Accept application/json
     * @apiHeader {String} Content-Type application/json
     *
     * @apiParamExample Request-Example:
     *   {
     *       "id": 12345,
     *       "roles": [
     *          {
     *              "name": "管理员"
     *          }
     *       ]
     *   }
     *
     * @apiSuccess {User} user 更新后的用户信息
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *          {
     *              "id": 12345
     *              "username": "zhangsan"
     *              "roles": [
     *                  {
     *                      "name": "管理员"
     *                  }
     *              ]
     *          }
     * @apiError 400 Bad request 若请求中包含错误
     * @apiError 401 Unauthorized 若未登录
     * @apiError 403 Forbidden 若没有权限
     * @apiError 404 Not Found 若用户未找到
     *
     * @apiErrorExample Error-Response:
     *     HTTP/1.1 403 Forbidden
     *     {
     *       "message": "Forbidden"
     *     }
     */
    /**
     *
     * @param id 要修改的用户 id
     * @param user 新的用户数据
     * @return
     */
    @PatchMapping("/user/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }
}
