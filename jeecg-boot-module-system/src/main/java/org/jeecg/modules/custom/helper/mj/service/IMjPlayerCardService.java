package org.jeecg.modules.custom.helper.mj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.custom.helper.mj.entity.MjPlayerCard;

import java.util.List;

/**
 * @Description: 玩家银行卡
 * @Author: jeecg-boot
 * @Date:   2019-09-07
 * @Version: V1.0
 */
public interface IMjPlayerCardService extends IService<MjPlayerCard> {

	public List<MjPlayerCard> selectByMainId(String mainId);
}
