package org.jeecg.modules.custom.helper.mj.service.impl;

import org.jeecg.modules.custom.helper.mj.entity.MjCard;
import org.jeecg.modules.custom.helper.mj.mapper.MjCardMapper;
import org.jeecg.modules.custom.helper.mj.service.IMjCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 平台收款银行卡
 * @Author: jeecg-boot
 * @Date:   2019-09-06
 * @Version: V1.0
 */
@Service
public class MjCardServiceImpl extends ServiceImpl<MjCardMapper, MjCard> implements IMjCardService {

    @Autowired
    MjCardMapper cardMapper;

    @Override
    public MjCard getOnline() {
        List<MjCard> cardList = cardMapper.selectOnline();
        if(cardList.size() > 0) return cardList.get(0);
        return null;
    }
}
