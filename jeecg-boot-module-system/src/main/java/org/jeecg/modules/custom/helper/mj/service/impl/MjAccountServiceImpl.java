package org.jeecg.modules.custom.helper.mj.service.impl;

import org.jeecg.modules.custom.helper.mj.entity.MjAccount;
import org.jeecg.modules.custom.helper.mj.mapper.MjAccountMapper;
import org.jeecg.modules.custom.helper.mj.service.IMjAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 平台收款帐号
 * @Author: jeecg-boot
 * @Date:   2019-09-06
 * @Version: V1.0
 */
@Service
public class MjAccountServiceImpl extends ServiceImpl<MjAccountMapper, MjAccount> implements IMjAccountService {

    @Autowired
    MjAccountMapper accountMapper;

    @Override
    public List<MjAccount> getOnline() {
        List<MjAccount> cardList = accountMapper.selectOnline();
        return cardList;

    }
}
