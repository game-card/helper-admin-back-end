package org.jeecg.modules.custom.helper.mj.service.impl;

import org.jeecg.modules.custom.helper.mj.entity.MjPlayer;
import org.jeecg.modules.custom.helper.mj.entity.MjPlayerAccount;
import org.jeecg.modules.custom.helper.mj.entity.MjPlayerCard;
import org.jeecg.modules.custom.helper.mj.entity.MjPlayerGame;
import org.jeecg.modules.custom.helper.mj.mapper.MjPlayerAccountMapper;
import org.jeecg.modules.custom.helper.mj.mapper.MjPlayerCardMapper;
import org.jeecg.modules.custom.helper.mj.mapper.MjPlayerGameMapper;
import org.jeecg.modules.custom.helper.mj.mapper.MjPlayerMapper;
import org.jeecg.modules.custom.helper.mj.service.IMjPlayerService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 游戏玩家
 * @Author: jeecg-boot
 * @Date:   2019-09-07
 * @Version: V1.0
 */
@Service
public class MjPlayerServiceImpl extends ServiceImpl<MjPlayerMapper, MjPlayer> implements IMjPlayerService {

	@Autowired
	private MjPlayerMapper mjPlayerMapper;
	@Autowired
	private MjPlayerAccountMapper mjPlayerAccountMapper;
	@Autowired
	private MjPlayerCardMapper mjPlayerCardMapper;
	@Autowired
	private MjPlayerGameMapper mjPlayerGameMapper;
	
	@Override
	@Transactional
	public void saveMain(MjPlayer mjPlayer, List<MjPlayerAccount> mjPlayerAccountList, List<MjPlayerCard> mjPlayerCardList, List<MjPlayerGame> mjPlayerGameList) {
		mjPlayerMapper.insert(mjPlayer);
		if(mjPlayerAccountList!=null && mjPlayerAccountList.size()>0) {
			for(MjPlayerAccount entity:mjPlayerAccountList) {
				//外键设置
				entity.setPlayerId(mjPlayer.getId());
				mjPlayerAccountMapper.insert(entity);
			}
		}
		if(mjPlayerCardList!=null && mjPlayerCardList.size()>0) {
			for(MjPlayerCard entity:mjPlayerCardList) {
				//外键设置
				entity.setPlayerId(mjPlayer.getId());
				mjPlayerCardMapper.insert(entity);
			}
		}
		if(mjPlayerGameList!=null && mjPlayerGameList.size()>0) {
			for(MjPlayerGame entity:mjPlayerGameList) {
				//外键设置
				entity.setPlayerId(mjPlayer.getId());
				mjPlayerGameMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(MjPlayer mjPlayer,List<MjPlayerAccount> mjPlayerAccountList,List<MjPlayerCard> mjPlayerCardList,List<MjPlayerGame> mjPlayerGameList) {
		mjPlayerMapper.updateById(mjPlayer);
		
		//1.先删除子表数据
		mjPlayerAccountMapper.deleteByMainId(mjPlayer.getId());
		mjPlayerCardMapper.deleteByMainId(mjPlayer.getId());
		mjPlayerGameMapper.deleteByMainId(mjPlayer.getId());
		
		//2.子表数据重新插入
		if(mjPlayerAccountList!=null && mjPlayerAccountList.size()>0) {
			for(MjPlayerAccount entity:mjPlayerAccountList) {
				//外键设置
				entity.setPlayerId(mjPlayer.getId());
				mjPlayerAccountMapper.insert(entity);
			}
		}
		if(mjPlayerCardList!=null && mjPlayerCardList.size()>0) {
			for(MjPlayerCard entity:mjPlayerCardList) {
				//外键设置
				entity.setPlayerId(mjPlayer.getId());
				mjPlayerCardMapper.insert(entity);
			}
		}
		if(mjPlayerGameList!=null && mjPlayerGameList.size()>0) {
			for(MjPlayerGame entity:mjPlayerGameList) {
				//外键设置
				entity.setPlayerId(mjPlayer.getId());
				mjPlayerGameMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		mjPlayerAccountMapper.deleteByMainId(id);
		mjPlayerCardMapper.deleteByMainId(id);
		mjPlayerGameMapper.deleteByMainId(id);
		mjPlayerMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			mjPlayerAccountMapper.deleteByMainId(id.toString());
			mjPlayerCardMapper.deleteByMainId(id.toString());
			mjPlayerGameMapper.deleteByMainId(id.toString());
			mjPlayerMapper.deleteById(id);
		}
	}

	@Override
	public MjPlayer getByPromoCode(String promoCode) {
		return mjPlayerMapper.selectByPromoCode(promoCode);
	}

	@Override
	public MjPlayer getByMobile(String mobile) {
		return mjPlayerMapper.selectByMobile(mobile);
	}

	@Override
	public List<MjPlayer> listFollower(String playerId) {
		return mjPlayerMapper.selectFollower(playerId);
	}

}
