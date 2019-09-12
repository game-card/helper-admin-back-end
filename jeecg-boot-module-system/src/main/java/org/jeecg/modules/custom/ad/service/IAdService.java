package org.jeecg.modules.custom.ad.service;

import org.jeecg.modules.custom.ad.entity.Ad;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.custom.ad.vo.AdVo;

import java.util.List;

/**
 * @Description: 广告
 * @Author: jeecg-boot
 * @Date:   2019-09-06
 * @Version: V1.0
 */
public interface IAdService extends IService<Ad> {

    public List<Ad> listOnline(AdVo adVo);

}
