package org.jeecg.modules.custom.helper.mj.service.impl;

import org.jeecg.modules.custom.helper.mj.entity.MjPlayerCard;
import org.jeecg.modules.custom.helper.mj.mapper.MjPlayerCardMapper;
import org.jeecg.modules.custom.helper.mj.service.IMjPlayerCardService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 玩家银行卡
 * @Author: jeecg-boot
 * @Date:   2019-09-07
 * @Version: V1.0
 */
@Service
public class MjPlayerCardServiceImpl extends ServiceImpl<MjPlayerCardMapper, MjPlayerCard> implements IMjPlayerCardService {
	
	@Autowired
	private MjPlayerCardMapper mjPlayerCardMapper;
	
	@Override
	public List<MjPlayerCard> selectByMainId(String mainId) {
		return mjPlayerCardMapper.selectByMainId(mainId);
	}
}
