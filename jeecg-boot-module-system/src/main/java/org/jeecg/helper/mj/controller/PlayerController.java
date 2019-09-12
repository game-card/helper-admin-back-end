package org.jeecg.helper.mj.controller;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.util.DySmsEnum;
import org.jeecg.common.util.DySmsHelper;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.modules.custom.helper.mj.entity.*;
import org.jeecg.modules.custom.helper.mj.service.*;
import org.jeecg.modules.shiro.vo.DefContants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * author : dashixiong
 * created at : 2019/9/5 17:26
 * email : lclllccll@163.com
 */
@RestController
@RequestMapping("/player")
@Api(tags="玩家登录")
public class PlayerController extends BaseController {

    @Autowired
    RedisUtil redisUtil;
    @Autowired
    IMjPlayerService playerService;
    @Autowired
    IMjGameService gameService;
    @Autowired
    IMjPlayerGameService playerGameService;
    @Autowired
    IMjPlayerCardService playerCardService;
    @Autowired
    IMjPlayerAccountService playerAccountService;


    @GetMapping(value = "/captcha")
    @ApiOperation("短信验证码接口")
    public Result<JSONObject> captcha(MjPlayer player,HttpServletRequest req) throws Exception {
        Result<JSONObject> result = new Result<JSONObject>();
        String mobile = req.getParameter("mobile");

        Object object = redisUtil.get(mobile);
        if (object != null) {
            result.setMessage("验证码10分钟内，仍然有效！");
            result.setSuccess(false);
            return result;
        }

        //随机数
        String captcha = RandomUtil.randomNumbers(4);
        JSONObject obj = new JSONObject();
        obj.put("code", captcha);
        System.out.println("验证码："+ captcha);

//        boolean b = false;
//        if (CommonConstant.SMS_TPL_TYPE_1.equals(mode)) {
//            b = DySmsHelper.sendSms(mobile, obj, DySmsEnum.REGISTER_TEMPLATE_CODE);
//        }else{
//            if (CommonConstant.SMS_TPL_TYPE_0.equals(mode)) {
//                b = DySmsHelper.sendSms(mobile, obj, DySmsEnum.LOGIN_TEMPLATE_CODE);
//            }
//        }
//
//        if (b == false) {
//            result.setMessage("短信验证码发送失败,请稍后重试");
//            result.setSuccess(false);
//            return result;
//        }

        //验证码10分钟内有效
        redisUtil.set(mobile, captcha, 600);
        result.success("验证码发送成功");
        return result;
    }

    @PostMapping(value = "/signIn")
    @ApiOperation("登录接口")
    public Result<Object> signIn(MjPlayer player,@RequestBody JSONObject jsonObject,HttpServletRequest req) throws Exception {
        Result<Object> result = new Result<>();
        String mobile = jsonObject.get("mobile").toString();
        String captcha = jsonObject.get("captcha").toString();

        Object code = redisUtil.get(mobile);
        if (!captcha.equals(code)) {
            result.error500("手机验证码错误");
            return result;
        }else {
            redisUtil.del(mobile);
        }

        player = playerService.getByMobile(mobile);
        if(player == null){
            result.error500(" 手机号未注册，请先注册！");
            return  result;
        }

        String token = JwtUtil.sign(mobile,mobile);
        redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, player, 1800);
        result.setResult(token);
        result.success("登陆成功");
        return result;
    }

    @PostMapping(value = "/signUp")
    @ApiOperation("注册接口")
    public Result<String> signUp(MjPlayer player,@RequestBody JSONObject jsonObject,HttpServletRequest req) throws Exception {
        Result<String> result = new Result<String>();
        String mobile = jsonObject.get("mobile").toString();
        String captcha = jsonObject.get("captcha").toString();
        String superior = jsonObject.get("superior").toString();

        Object code = redisUtil.get(mobile);
        if (!captcha.equals(code)) {
            result.error500("手机验证码错误");
            return result;
        }else {
            redisUtil.del(mobile);
        }

        MjPlayer playerByMobile = playerService.getByMobile(mobile);

        if(playerByMobile != null) {
            result.error500("电话号码已被注册，请登陆");
            return result;
        }
        MjPlayer guider = playerService.getByPromoCode(superior);
        if(guider == null){
            result.error500("推广码无效");
            return result;
        }

        player.setMobile(mobile);
        player.setStatus("1");
        player.setSex("2");
        player.setSuperior(guider.getId());
        player.setCreateTime(new Date());
        playerService.save(player);

        player = playerService.getByMobile(mobile);
        String token = JwtUtil.sign(mobile,mobile);
        //验证码30分钟内有效
        redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, player, 1800);
        result.setResult(token);
        result.success("注册成功");
        return result;
    }

    @GetMapping(value = "/signOut")
    @ApiOperation("登出接口")
    public Result<Object> signOut(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader(DefContants.X_ACCESS_TOKEN);
        Result<Object> result = new Result<Object>();
        if(token == null){
            result.error500("token不存在");
        }else {
            redisUtil.del(CommonConstant.PREFIX_USER_TOKEN + token);
        }
        result.success("登出成功");
        return result;
    }

    @GetMapping(value = "/info")
    @ApiOperation("获取玩家信息接口")
    public Result<Object> getInfo(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader(DefContants.X_ACCESS_TOKEN);
        MjPlayer player = (MjPlayer) redisUtil.get(CommonConstant.PREFIX_USER_TOKEN + token);
//        Result<Object> result = new Result<Object>();
//        if(token == null) {
//            result.error500("token不存在");
//            return result;
//        }
//        if(player != null){
//            result.setResult(player);
//        }else {
//            redisUtil.del(CommonConstant.PREFIX_USER_TOKEN + token);
//            result.error500("用户登陆信息已过期，请重新登陆");
//        }

        Result<Object> result = checkLoginInfo(request);
        if(result.getCode() != 0){
            return result;
        }

        String refresh = request.getParameter("refresh");
        if(!StringUtils.isEmpty(refresh)){
            player = playerService.getByMobile(player.getMobile());
            redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, player, 1800);
        }

        result.setResult(player);
        result.success("获取玩家信息成功");
        return result;
    }

    @GetMapping(value = "/follower")
    @ApiOperation("获取直接下级")
    public Result<Object> listFollower(HttpServletRequest request){
        String token = request.getHeader(DefContants.X_ACCESS_TOKEN);
        MjPlayer player = (MjPlayer) redisUtil.get(CommonConstant.PREFIX_USER_TOKEN + token);
        Result<Object> result = checkLoginInfo(request);
        if(result.getCode() != 0){
            return result;
        }
        List<MjPlayer> followerList = playerService.listFollower(player.getId());
        result.setResult(followerList);
        result.success("成功获取下级");
        return result;
    }

    @GetMapping(value = "/gameList")
    @ApiOperation("获取玩家游戏")
    public Result<Object> listPlayerGame(HttpServletRequest request){
        String token = request.getHeader(DefContants.X_ACCESS_TOKEN);
        MjPlayer player = (MjPlayer) redisUtil.get(CommonConstant.PREFIX_USER_TOKEN + token);
        Result<Object> result = checkLoginInfo(request);
        if(result.getCode() != 0){
            return result;
        }

        List<MjGame> gameList = gameService.listByPlayerId(player.getId());
        result.setResult(gameList);
        result.success("成功获取玩家游戏列表");
        return result;
    }

    @GetMapping(value = "/gameCanUse")
    @ApiOperation("玩家是否可配置")
    public Result<Object> gameCanUse(HttpServletRequest request){
        String token = request.getHeader(DefContants.X_ACCESS_TOKEN);
        MjPlayer player = (MjPlayer) redisUtil.get(CommonConstant.PREFIX_USER_TOKEN + token);
        Result<Object> result = checkLoginInfo(request);
        if(result.getCode() != 0){
            return result;
        }

        String gameId = request.getParameter("gameId");
        if(StringUtils.isEmpty(gameId)){
            result.error500("数据有误，请重试！");
            return result;
        }
        List<MjGame> gameList = gameService.listByPlayerId(player.getId());
        for(MjGame game:gameList){
            if(game.getId().equals(gameId) && game.getStatus().equals("1")){
                result.setResult(true);
                result.success("游戏可以配置");
                return result;
            }
        }

        result.error500("先升级会员，然后再试");
        result.setResult(false);
        return result;
    }

    @GetMapping(value = "/gameAdd")
    @ApiOperation("玩家游戏添加")
    public Result<Object> gameAdd(HttpServletRequest request) {
        String token = request.getHeader(DefContants.X_ACCESS_TOKEN);
        MjPlayer player = (MjPlayer) redisUtil.get(CommonConstant.PREFIX_USER_TOKEN + token);
        Result<Object> result = checkLoginInfo(request);
        if(result.getCode() != 0){
            return result;
        }

        String gameId = request.getParameter("gameId");
        if(StringUtils.isEmpty(gameId)){
            result.error500("数据有误，请重试！");
            return result;
        }

        List<MjPlayerGame> list = playerGameService.selectByMainId(player.getId());
        if(Integer.parseInt(player.getLever()) == 1){
            result.error500("普通会员可添加0个游戏，升级会员可用更多游戏！");
            return result;
        }else if(Integer.parseInt(player.getLever()) == 2){
            if(list.size()>5){
                result.error500("白银会员可添加5个游戏，升级会员可用更多游戏！");
                return result;
            }
        }else if(Integer.parseInt(player.getLever()) == 3){
            if(list.size()>10){
                result.error500("黄金会员可添加10个游戏，升级会员可用更多游戏！");
                return result;
            }
        }

        //检测可用
        boolean isExist = playerGameService.isExist(player.getId(),gameId);
        if(isExist){
            result.success("游戏已添加到玩家");
            return result;
        }else {
            MjPlayerGame pg = new MjPlayerGame();
            pg.setPlayerId(player.getId());
            pg.setGameId(gameId);
            playerGameService.save(pg);
        }

        result.success("游戏成功添加到玩家");
        return result;
    }

    @GetMapping(value = "/cashBackInfo")
    @ApiOperation("返现工具信息")
    public Result<Object> listCashBackTool(HttpServletRequest request){
        String token = request.getHeader(DefContants.X_ACCESS_TOKEN);
        MjPlayer player = (MjPlayer) redisUtil.get(CommonConstant.PREFIX_USER_TOKEN + token);
        Result<Object> result = checkLoginInfo(request);
        if(result.getCode() != 0){
            return result;
        }

        String mode = request.getParameter("mode");
        if(StringUtils.isEmpty(mode)){
            result.error500("参数有误，请检查！");
            return result;
        }

        if(mode.equals("card")){
            //银行卡
            List<MjPlayerCard> cardList = playerCardService.selectByMainId(player.getId());
            result.setResult(cardList);
        }else {
            List<MjPlayerAccount> accountList = playerAccountService.selectByMainId(player.getId());
            List<MjPlayerAccount> tempList = new ArrayList<>();
            if(mode.equals("wx")){
                //微信
                for(MjPlayerAccount pa:accountList){
                    if(pa.getAccountType().equals("wx")){
                        tempList.add(pa);
                    }
                }
            }else if(mode.equals("zfb")){
                //支付宝
                for(MjPlayerAccount pa:accountList){
                    if(pa.getAccountType().equals("zfb")){
                        tempList.add(pa);
                    }
                }
            }
            result.setResult(tempList);
        }
        result.success("成功获取玩家返现工具列表");
        return result;
    }

    @PostMapping(value = "/cashBackEdit")
    @ApiOperation("返现工具维护")
    public Result<Object> editCashBackTool(HttpServletRequest request,@RequestBody JSONObject jsonObject){
        String token = request.getHeader(DefContants.X_ACCESS_TOKEN);
        MjPlayer player = (MjPlayer) redisUtil.get(CommonConstant.PREFIX_USER_TOKEN + token);
        Result<Object> result = checkLoginInfo(request);
        if(result.getCode() != 0){
            return result;
        }

//        String mode = request.getParameter("mode");
        String mode = jsonObject.getString("mode");
        if(StringUtils.isEmpty(mode)){
            result.error500("参数有误，请检查！");
            return result;
        }

        if(mode.equals("card")){
            MjPlayerCard pc = new MjPlayerCard();
            pc.setId(jsonObject.getString("id"));
            pc.setPlayerId(player.getId());
            pc.setBankType(jsonObject.getString("bankType"));
            pc.setCardNum(jsonObject.getString("cardNum"));
            pc.setStatus(StringUtils.isEmpty(jsonObject.getString("status"))?"1":jsonObject.getString("status"));
            pc.setQrCodeImage(jsonObject.getString("image"));
            playerCardService.saveOrUpdate(pc);
        }else {
            MjPlayerAccount pa = new MjPlayerAccount();
            pa.setId(jsonObject.getString("id"));
            pa.setAccountType(mode);
            pa.setAccountNum(jsonObject.getString("accountNum"));
            pa.setPlayerId(player.getId());
            pa.setStatus(StringUtils.isEmpty(jsonObject.getString("status"))?"1":jsonObject.getString("status"));
            pa.setQrCodeImage(jsonObject.getString("image"));
            playerAccountService.saveOrUpdate(pa);
        }

        result.success("返现工具维护成功");
        return result;
    }

}
