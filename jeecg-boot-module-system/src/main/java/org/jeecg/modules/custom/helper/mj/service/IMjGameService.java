package org.jeecg.modules.custom.helper.mj.service;

import org.jeecg.modules.custom.helper.mj.entity.MjGame;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 游戏平台
 * @Author: jeecg-boot
 * @Date:   2019-09-06
 * @Version: V1.0
 */
public interface IMjGameService extends IService<MjGame> {

    List<MjGame> listOnline(MjGame mjGame);

    List<MjGame> listByPlayerId(String id);
}
