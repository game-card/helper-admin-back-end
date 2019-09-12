package org.jeecg.modules.custom.helper.mj.service.impl;

import org.jeecg.modules.custom.helper.mj.entity.MjConf;
import org.jeecg.modules.custom.helper.mj.entity.MjPlayerGame;
import org.jeecg.modules.custom.helper.mj.mapper.MjPlayerGameMapper;
import org.jeecg.modules.custom.helper.mj.service.IMjPlayerGameService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 可用游戏
 * @Author: jeecg-boot
 * @Date:   2019-09-07
 * @Version: V1.0
 */
@Service
public class MjPlayerGameServiceImpl extends ServiceImpl<MjPlayerGameMapper, MjPlayerGame> implements IMjPlayerGameService {
	
	@Autowired
	private MjPlayerGameMapper mjPlayerGameMapper;
	
	@Override
	public List<MjPlayerGame> selectByMainId(String mainId) {
		return mjPlayerGameMapper.selectByMainId(mainId);
	}

	@Override
	public boolean isExist(String playerId, String gameId) {
		MjPlayerGame pg = new MjPlayerGame();
		pg.setPlayerId(playerId);
		pg.setGameId(gameId);
		int count = mjPlayerGameMapper.countConf(pg);
		if(count > 0) return  true;
		return false;
	}
}
