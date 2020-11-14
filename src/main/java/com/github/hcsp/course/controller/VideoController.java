package com.github.hcsp.course.controller;

import com.github.hcsp.course.model.Course;
import com.github.hcsp.course.model.Video;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 视频管理相关
 */
@RestController
@RequestMapping("/api/v1")
public class VideoController {
    /**
     * @api {get} /api/v1/course/{id}/video 获取课程下的视频列表
     * @apiName 获取课程下的视频列表
     * @apiGroup 视频管理
     * @apiDescription
     *  获取指定课程包含的视频信息。
     *  若视频的URL为空，证明未购买该课程。
     *
     * @apiHeader {String} Accept application/json
     * @apiParam {Number} id 课程id
     *
     * @apiParamExample Request-Example:
     *   GET /api/v1/course/12345/video
     * @apiSuccess {List[Video]} data 视频列表
     *
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *      [
     *        {
     *             "id": 456,
     *             "name": "第一课",
     *             "description": "",
     *             "url": "https://oss.aliyun.com/xxx"
     *         }
     *      ]
     * @apiError 400 Bad Request 若请求中包含错误
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
    @GetMapping("/course/{id}/video")
    public List<Video> getVideos(@PathVariable("id") Integer courseId) {
        return null;
    }

    /**
     * @api {get} /api/v1/video/{id} 获取视频
     * @apiName 获取视频
     * @apiGroup 视频管理
     * @apiDescription
     *  获取指定id的视频信息。
     *  若视频的URL为空，证明未购买该课程。
     *
     * @apiHeader {String} Accept application/json
     *
     * @apiParamExample Request-Example:
     *    GET /api/v1/video/123
     *
     * @apiSuccess {Course} course 课程信息
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *        {
     *             "id": 456,
     *             "name": "第一课",
     *             "description": "",
     *             "url": "https://oss.aliyun.com/xxx"
     *         }
     * @apiError 400 Bad Request 若请求中包含错误
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
    @GetMapping("/video/{id}")
    public Course getVideo(@PathVariable("id") Integer id) {
        return null;
    }

    /**
     * @api {post} /api/v1/course/{id}/video 创建视频
     * @apiName 创建视频
     * @apiGroup 视频管理
     * @apiDescription
     *  填写必要信息，在指定课程下创建一个视频。需要"课程管理"权限。
     *
     * @apiHeader {String} Accept application/json
     * @apiHeader {String} Content-Type application/json
     *
     * @apiParamExample Request-Example:
     *          POST /api/v1/course/123/video
     *        {
     *             "id": 456,
     *             "name": "第一课",
     *             "description": "",
     *             "url": "https://oss.aliyun.com/xxx"
     *         }
     * @apiSuccess {Course} course 新创建的视频信息
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *        {
     *             "id": 456,
     *             "name": "第一课",
     *             "description": "",
     *             "url": "https://oss.aliyun.com/xxx"
     *         }
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
    @PostMapping("/course/{id}/video")
    public Course createVideo(@PathVariable("id") Integer id, @RequestBody Video video) {
        return null;
    }
    /**
     * @api {delete} /api/v1/video 删除视频
     * @apiName 删除视频
     * @apiGroup 视频管理
     *
     * @apiHeader {String} Accept application/json
     *
     * @apiParamExample Request-Example:
     *  DELETE /api/v1/course/123/video
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
     * @param id
     */
    @DeleteMapping("/video/{id}")
    public void deleteVideo(@PathVariable("id") Integer id) {
    }

    /**
     * @api {patch} /api/v1/video/{id} 修改视频
     * @apiName 修改视频
     * @apiGroup 视频管理
     * @apiDescription
     *  需要"课程管理"权限。
     *
     * @apiHeader {String} Accept application/json
     * @apiHeader {String} Content-Type application/json
     *
     * @apiParamExample Request-Example:
     * PATCH /api/v1/course
     *        {
     *             "id": 456,
     *             "name": "第一课",
     *             "description": "",
     *             "url": "https://oss.aliyun.com/xxx"
     *         }
     *
     * @apiSuccess {Video} video 修改后的视频信息
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *        {
     *             "id": 456,
     *             "name": "第一课",
     *             "description": "",
     *             "url": "https://oss.aliyun.com/xxx"
     *         }
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
    @PatchMapping("/video/{id}")
    public Video updateVideo(@PathVariable("id") Integer id, @RequestBody Video video) {
        return null;
    }

    /**
     * @api {get} /api/v1/course/{id}/token 获取上传视频所需token
     * @apiName 获取在指定课程下上传视频所需token等验证信息
     * @apiGroup 视频管理
     * @apiDescription
     *  验证信息不止包括token。详见 https://help.aliyun.com/document_detail/31927.html
     *
     *  当客户端上传成功时，应调用createVideo接口发起一个新的POST请求将视频URL发给应用。
     *
     * @apiHeader {String} Accept application/json
     * @apiParam {Number} id 课程id
     *
     * @apiParamExample Request-Example:
     *   GET /api/v1/course/12345/token
     * @apiSuccess {String} accessid
     * @apiSuccess {String} host
     * @apiSuccess {String} policy
     * @apiSuccess {String} signature
     * @apiSuccess {Number} expire
     * @apiSuccess {String} dir
     *
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     {
     *       "accessid":"6MKO******4AUk44",
     *       "host":"http://post-test.oss-cn-hangzhou.aliyuncs.com",
     *       "policy":"MCwxMDQ4NTc2MDAwXSxbInN0YXJ0cy13aXRoIiwiJGtleSIsInVzZXItZGlyXC8iXV19",
     *       "signature":"VsxOcOudx******z93CLaXPz+4s=",
     *       "expire":1446727949,
     *       "dir":"user-dirs/"
     *     }
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
    @GetMapping("/course/{id}/token")
    public Object getToken(@PathVariable("id") Integer courseId) {
        return null;
    }
}
