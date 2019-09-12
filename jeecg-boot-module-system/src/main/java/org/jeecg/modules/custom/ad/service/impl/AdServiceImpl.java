package org.jeecg.modules.custom.ad.service.impl;

import org.jeecg.modules.custom.ad.entity.Ad;
import org.jeecg.modules.custom.ad.mapper.AdMapper;
import org.jeecg.modules.custom.ad.service.IAdService;
import org.jeecg.modules.custom.ad.vo.AdVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 广告
 * @Author: jeecg-boot
 * @Date:   2019-09-06
 * @Version: V1.0
 */
@Service
public class AdServiceImpl extends ServiceImpl<AdMapper, Ad> implements IAdService {

    @Autowired
    AdMapper adMapper;
    @Override
    public List<Ad> listOnline(AdVo adVo) {
        return adMapper.selectOnline(adVo);
    }
}
