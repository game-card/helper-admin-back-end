package org.jeecg.modules.custom.helper.mj.service.impl;

import org.jeecg.modules.custom.helper.mj.entity.MjPlayerAccount;
import org.jeecg.modules.custom.helper.mj.mapper.MjPlayerAccountMapper;
import org.jeecg.modules.custom.helper.mj.service.IMjPlayerAccountService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 玩家帐号
 * @Author: jeecg-boot
 * @Date:   2019-09-07
 * @Version: V1.0
 */
@Service
public class MjPlayerAccountServiceImpl extends ServiceImpl<MjPlayerAccountMapper, MjPlayerAccount> implements IMjPlayerAccountService {
	
	@Autowired
	private MjPlayerAccountMapper mjPlayerAccountMapper;
	
	@Override
	public List<MjPlayerAccount> selectByMainId(String mainId) {
		return mjPlayerAccountMapper.selectByMainId(mainId);
	}
}
