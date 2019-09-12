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
 * created at : 2019/9/5 17:50
 * email : lclllccll@163.com
 */
@RestController
@RequestMapping("/spread")
@Api(tags="玩家推广")
public class SpreadController extends BaseController {

    @GetMapping(value = "/result")
    @ApiOperation("推广结果接口")
    public Result<JSONObject> captcha() throws Exception {
        Result<JSONObject> result = new Result<JSONObject>();
        return null;
    }

}
