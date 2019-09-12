package org.jeecg.modules.custom.helper.mj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.custom.helper.mj.entity.MjPlayerAccount;

import java.util.List;

/**
 * @Description: 玩家帐号
 * @Author: jeecg-boot
 * @Date:   2019-09-07
 * @Version: V1.0
 */
public interface IMjPlayerAccountService extends IService<MjPlayerAccount> {

	public List<MjPlayerAccount> selectByMainId(String mainId);
}
