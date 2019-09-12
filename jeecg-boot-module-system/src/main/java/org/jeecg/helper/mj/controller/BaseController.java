package org.jeecg.helper.mj.controller;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.modules.custom.helper.mj.entity.MjPlayer;
import org.jeecg.modules.shiro.vo.DefContants;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * author : dashixiong
 * created at : 2019/9/8 16:36
 * email : lclllccll@163.com
 */
public class BaseController {

    @Autowired
    RedisUtil redisUtil;
    /**
     * 检测登陆信息是否有效
     * @param request
     * @return
     */
    protected Result<Object> checkLoginInfo(HttpServletRequest request) {
        String token = request.getHeader(DefContants.X_ACCESS_TOKEN);
        MjPlayer player = (MjPlayer) redisUtil.get(CommonConstant.PREFIX_USER_TOKEN + token);
        Result<Object> result = new Result<>();
        if(token == null) {
            result.error500("token不存在");
            return result;
        }
        if(player == null){
            result.error500("登陆信息已过期，请重新登陆");
            return result;
        }

        return result;
    }

}
