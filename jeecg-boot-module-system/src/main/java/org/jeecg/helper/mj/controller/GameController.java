package org.jeecg.helper.mj.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.modules.custom.helper.mj.entity.MjConf;
import org.jeecg.modules.custom.helper.mj.entity.MjGame;
import org.jeecg.modules.custom.helper.mj.entity.MjPlayer;
import org.jeecg.modules.custom.helper.mj.service.IMjConfService;
import org.jeecg.modules.custom.helper.mj.service.IMjGameService;
import org.jeecg.modules.custom.helper.mj.service.IMjPlayerGameService;
import org.jeecg.modules.shiro.vo.DefContants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * author : dashixiong
 * created at : 2019/9/5 17:41
 * email : lclllccll@163.com
 */
@RestController
@RequestMapping("/game")
@Api(tags="游戏配置")
public class GameController extends BaseController {
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    IMjGameService mjGameService;
    @Autowired
    IMjConfService mjConfService;
    @Autowired
    IMjPlayerGameService playerGameService;

    @PostMapping(value = "/conf")
    @ApiOperation("配置接口")
    public Result<Object> conf(MjConf mjConf,@RequestBody JSONObject jsonObject, HttpServletRequest request) throws Exception {
        String token = request.getHeader(DefContants.X_ACCESS_TOKEN);
        MjPlayer player = (MjPlayer) redisUtil.get(CommonConstant.PREFIX_USER_TOKEN + token);
        Result<Object> result = new Result<Object>();
        if(token == null) {
            result.error500("token不存在");
            return result;
        }

        if(player == null){
            result.error500("用户登陆信息已过期，请重新登陆");
            return result;
        }

        mjConf.setId(jsonObject.getString("id"));
        mjConf.setGameId(jsonObject.get("gameId").toString());
        mjConf.setContent(jsonObject.get("content").toString());
        mjConf.setPlayerId(player.getId());
        mjConf.setCreateTime(new Date());

        //检测可用
        boolean isExist = playerGameService.isExist(player.getId(),mjConf.getGameId());
        if(!isExist){
            result.error500("即将添加游戏到可用,然后再试!");
            return result;
        }

        mjConfService.saveOrUpdate(mjConf);
        result.success("成功配置游戏");
        return result;
    }

    @GetMapping(value = "/online")
    @ApiOperation("游戏列表")
    public Result<List<MjGame>> listAll(MjGame game) throws Exception {
        game.setStatus("1");
        List<MjGame> list = mjGameService.listOnline(game);
        Result<List<MjGame>> result = new Result<>();
        result.setResult(list);
        result.success("成功获取游戏列表");
        return result;
    }

}
