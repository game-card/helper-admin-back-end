package org.jeecg.modules.custom.helper.mj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.custom.helper.mj.entity.MjPlayer;
import org.jeecg.modules.custom.helper.mj.entity.MjPlayerAccount;
import org.jeecg.modules.custom.helper.mj.entity.MjPlayerCard;
import org.jeecg.modules.custom.helper.mj.entity.MjPlayerGame;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 游戏玩家
 * @Author: jeecg-boot
 * @Date:   2019-09-07
 * @Version: V1.0
 */
public interface IMjPlayerService extends IService<MjPlayer> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(MjPlayer mjPlayer, List<MjPlayerAccount> mjPlayerAccountList, List<MjPlayerCard> mjPlayerCardList, List<MjPlayerGame> mjPlayerGameList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(MjPlayer mjPlayer,List<MjPlayerAccount> mjPlayerAccountList,List<MjPlayerCard> mjPlayerCardList,List<MjPlayerGame> mjPlayerGameList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);

	/**
	 * 根据推广码查询
	 * @param promoCode
	 * @return
	 */
	public MjPlayer getByPromoCode(String promoCode);

	MjPlayer getByMobile(String mobile);

	List<MjPlayer> listFollower(String id);
}
