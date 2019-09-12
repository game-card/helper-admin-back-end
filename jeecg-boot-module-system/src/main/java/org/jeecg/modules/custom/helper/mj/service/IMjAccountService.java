package org.jeecg.modules.custom.helper.mj.service;

import org.jeecg.modules.custom.helper.mj.entity.MjAccount;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 平台收款帐号
 * @Author: jeecg-boot
 * @Date:   2019-09-06
 * @Version: V1.0
 */
public interface IMjAccountService extends IService<MjAccount> {

    List<MjAccount> getOnline();

}
