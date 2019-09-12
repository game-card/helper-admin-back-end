package org.jeecg.modules.custom.helper.mj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.custom.helper.mj.entity.MjPlayer;

/**
 * @Description: 游戏玩家
 * @Author: jeecg-boot
 * @Date:   2019-09-07
 * @Version: V1.0
 */
public interface MjPlayerMapper extends BaseMapper<MjPlayer> {

    MjPlayer selectByPromoCode(@Param("promoCode") String promoCode);
    MjPlayer selectByMobile(@Param("mobile") String mobile);

    List<MjPlayer> selectFollower(@Param("playerId") String playerId);
}
