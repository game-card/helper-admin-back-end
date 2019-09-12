package org.jeecg.helper.mj.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.api.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author : dashixiong
 * created at : 2019/9/5 18:05
 * email : lclllccll@163.com
 */
@RestController
@RequestMapping("/message")
@Api(tags="玩家平台消息")
public class MessageController extends BaseController {

    @GetMapping(value = "/list")
    @ApiOperation("消息列表")
    public Result<JSONObject> list() throws Exception {
        return null;
    }

    @GetMapping(value = "/info")
    @ApiOperation("消息内容")
    public Result<JSONObject> info() throws Exception {
        return null;
    }

}
