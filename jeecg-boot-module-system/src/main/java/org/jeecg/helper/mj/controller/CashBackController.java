package org.jeecg.helper.mj.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.modules.custom.helper.mj.entity.MjPlayer;
import org.jeecg.modules.custom.helper.mj.service.IMjCashBackCanService;
import org.jeecg.modules.custom.helper.mj.service.IMjCashBackRecordService;
import org.jeecg.modules.shiro.vo.DefContants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * author : dashixiong
 * created at : 2019/9/5 17:54
 * email : lclllccll@163.com
 */
@RestController
@RequestMapping("/cashBack")
@Api(tags="返现")
public class CashBackController extends BaseController {

    @Autowired
    IMjCashBackCanService cashBackCanService;
    @Autowired
    IMjCashBackRecordService iMjCashBackRecordService;

    @PostMapping(value = "/account")
    @ApiOperation("返现帐号维护")
    public Result<JSONObject> cashBackAccount() throws Exception {
        return null;
    }

    @GetMapping(value = "/can")
    @ApiOperation("可返数据")
    public Result<Object> canBack(HttpServletRequest request) throws Exception {
        String token = request.getHeader(DefContants.X_ACCESS_TOKEN);
        MjPlayer player = (MjPlayer) redisUtil.get(CommonConstant.PREFIX_USER_TOKEN + token);
        Result<Object> result = checkLoginInfo(request);
        if(result.getCode() != 0){
            return result;
        }

        String status = request.getParameter("status");
        Map queryMap = new HashMap<>();
        queryMap.put("status",status);
        queryMap.put("player_id",player.getId());
        Collection cashBackList = cashBackCanService.listByMap(queryMap);
        result.setResult(cashBackList);
        result.success("成功获取可返数据列表");
        return result;
    }

    @GetMapping(value = "/record")
    @ApiOperation("返现记录")
    public Result<Object> record(HttpServletRequest request) throws Exception {
        String token = request.getHeader(DefContants.X_ACCESS_TOKEN);
        MjPlayer player = (MjPlayer) redisUtil.get(CommonConstant.PREFIX_USER_TOKEN + token);
        Result<Object> result = checkLoginInfo(request);
        if (result.getCode() != 0) {
            return result;
        }

        String status = request.getParameter("status");
        Map queryMap = new HashMap<>();
        queryMap.put("status", status);
        queryMap.put("player_id", player.getId());
        Collection cashBackList = iMjCashBackRecordService.listByMap(queryMap);
        result.setResult(cashBackList);
        result.success("成功获取可返数据列表");
        return result;
    }

}
