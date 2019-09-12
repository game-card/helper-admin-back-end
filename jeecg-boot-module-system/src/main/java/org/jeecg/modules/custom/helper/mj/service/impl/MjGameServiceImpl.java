package org.jeecg.modules.custom.helper.mj.service.impl;

import org.jeecg.modules.custom.helper.mj.entity.MjGame;
import org.jeecg.modules.custom.helper.mj.mapper.MjGameMapper;
import org.jeecg.modules.custom.helper.mj.service.IMjGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 游戏平台
 * @Author: jeecg-boot
 * @Date:   2019-09-06
 * @Version: V1.0
 */
@Service
public class MjGameServiceImpl extends ServiceImpl<MjGameMapper, MjGame> implements IMjGameService {

    @Autowired
    MjGameMapper mjGameMapper;
    @Override
    public List<MjGame> listOnline(MjGame mjGame) {
        return mjGameMapper.selectOnline(mjGame);
    }

    @Override
    public List<MjGame> listByPlayerId(String id) {
        return mjGameMapper.selectByPlayerId(id);
    }
}
