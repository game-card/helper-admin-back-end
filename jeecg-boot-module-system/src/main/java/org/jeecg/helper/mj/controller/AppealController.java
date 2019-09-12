package org.jeecg.helper.mj.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.api.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author : dashixiong
 * created at : 2019/9/5 18:08
 * email : lclllccll@163.com
 */
@RestController
@RequestMapping("/appeal")
@Api(tags="申诉")
public class AppealController extends BaseController {

    @PostMapping(value = "/add")
    @ApiOperation("添加申诉")
    public Result<JSONObject> add() throws Exception {
        return null;
    }

}
