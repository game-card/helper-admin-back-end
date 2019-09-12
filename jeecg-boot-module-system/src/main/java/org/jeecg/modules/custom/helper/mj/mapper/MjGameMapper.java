package org.jeecg.modules.custom.helper.mj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.custom.helper.mj.entity.MjGame;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 游戏平台
 * @Author: jeecg-boot
 * @Date:   2019-09-06
 * @Version: V1.0
 */
public interface MjGameMapper extends BaseMapper<MjGame> {

    List<MjGame> selectOnline(@Param("game") MjGame mjGame);

    List<MjGame> selectByPlayerId(@Param("playerId") String playerId);
}
