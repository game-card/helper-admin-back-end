package org.jeecg.modules.custom.helper.mj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.custom.helper.mj.entity.MjPlayerGame;

import java.util.List;

/**
 * @Description: 可用游戏
 * @Author: jeecg-boot
 * @Date:   2019-09-07
 * @Version: V1.0
 */
public interface IMjPlayerGameService extends IService<MjPlayerGame> {

	public List<MjPlayerGame> selectByMainId(String mainId);

    boolean isExist(String id, String gameId);
}
