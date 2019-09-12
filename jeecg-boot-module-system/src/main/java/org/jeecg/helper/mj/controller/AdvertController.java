package org.jeecg.helper.mj.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.custom.ad.entity.Ad;
import org.jeecg.modules.custom.ad.service.IAdService;
import org.jeecg.modules.custom.ad.vo.AdVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * author : dashixiong
 * created at : 2019/9/7 10:19
 * email : lclllccll@163.com
 */

@RestController
@RequestMapping("/ad")
@Api(tags="广告")
public class AdvertController extends BaseController {

    @Autowired
    IAdService iAdService;

    @GetMapping(value = "/online")
    @ApiOperation("获取上线广告")
    public Result<List<Ad>> online(AdVo adVo) throws Exception {
        adVo.setShowIn("mj");
        adVo.setShowAt("1");
        adVo.setStatus("1");
        adVo.setQueryTime(new Date());

        List list = iAdService.listOnline(adVo);
        Result<List<Ad>> result = new Result<>();
        result.setResult(list);
        result.success("成功获取广告");
        return result;
    }

}
