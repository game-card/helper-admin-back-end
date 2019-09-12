package org.jeecg.helper.mj.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.helper.mj.util.Base64Helper;
import org.jeecg.modules.custom.helper.mj.entity.MjAccount;
import org.jeecg.modules.custom.helper.mj.entity.MjCard;
import org.jeecg.modules.custom.helper.mj.entity.MjPayPicture;
import org.jeecg.modules.custom.helper.mj.entity.MjPlayer;
import org.jeecg.modules.custom.helper.mj.service.IMjAccountService;
import org.jeecg.modules.custom.helper.mj.service.IMjCardService;
import org.jeecg.modules.custom.helper.mj.service.IMjPayPictureService;
import org.jeecg.modules.shiro.vo.DefContants;
import org.jeecg.modules.system.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * author : dashixiong
 * created at : 2019/9/5 17:44
 * email : lclllccll@163.com
 */
@RestController
@RequestMapping("/pay")
@Api(tags="支付")
public class PayController extends BaseController {

    @Autowired
    RedisUtil redisUtil;
    @Autowired
    IMjCardService mjCardService;
    @Autowired
    IMjAccountService mjAccountService;
    @Autowired
    IMjPayPictureService payPictureService;
    @Autowired
    ISysDictService sysDictService;

    @Value(value = "${jeecg.path.upload}")
    private String uploadpath;

    @GetMapping(value = "/online")
    @ApiOperation("可用收款二维码接口")
    public Result<Object> getOnline(HttpServletRequest request) throws Exception {
        Result<Object> result = new Result<Object>();
        String mode = request.getParameter("mode");
        if(StringUtils.isEmpty(mode)){
            result.error500("参数有误，请检查！");
            return result;
        }

        if(mode.equals("card")){
            MjCard card = mjCardService.getOnline();
            result.setResult(card);
        }else{
            List<MjAccount> accountList = mjAccountService.getOnline();
            for(MjAccount account: accountList){
                if(account.getAccountType().equals(mode)){
                    result.setResult(account);
                    break;
                }
            }
        }
        result.success("成功获取可用收款二维码");
        return result;
    }

    @PostMapping(value = "/picture")
    @ApiOperation("支付回执上传接口")
    public Result<Object> picture(HttpServletRequest request,@RequestBody JSONObject jsonObject) throws Exception {
        String token = request.getHeader(DefContants.X_ACCESS_TOKEN);
        MjPlayer player = (MjPlayer) redisUtil.get(CommonConstant.PREFIX_USER_TOKEN + token);
        Result<Object> result = checkLoginInfo(request);
        if(result.getCode() != 0){
            return result;
        }

        String action = jsonObject.getString("action");
        String imgData = jsonObject.getString("imgData");
        if(StringUtils.isEmpty(action) || StringUtils.isEmpty(imgData)){
            result.error500("请求参数不全,请检查");
            return result;
        }

        //普通会员
        if("1".equals(player.getLever())){

        }
        //白银会员
        if("2".equals(player.getLever())){
            if(!("4".equals(action) || "5".equals(action))){
                result.error500("你是白银会员，只可升级黄金或钻石");
                return result;
            }
        }
        //黄金会员
        if("3".equals(player.getLever())){
            if(!("6".equals(action))){
                result.error500("你是黄金会员，只可升级钻石");
                return result;
            }
        }
        //钻石会员
        if("4".equals(player.getLever())){
            result.error500("你是钻石会员，不能再升级");
            return result;
        }

        String text = sysDictService.queryDictTextByKey("pay_action", action);
        if(StringUtils.isEmpty(text)){
            result.error500("参数有误,请检查");
            return result;
        }

        String filePath = Base64Helper.saveImg(uploadpath, imgData);
        MjPayPicture picture = new MjPayPicture();
        picture.setPlayerId(player.getId());
        picture.setCreateTime(new Date());
        picture.setStatus("1");
        picture.setAction(action);
        picture.setPath(filePath);

//        Map query = new HashMap();
//        query.put("status",picture.getStatus());
//        query.put("player_id",picture.getPlayerId());
//        Collection list = payPictureService.listByMap(query);
//        if(list.size()>0){
//            result.error500("一次支付不要多次上传,正在审核中");
//            return result;
//        }

        payPictureService.save(picture);
        result.success("成功上传回执");
        return result;
    }



}
