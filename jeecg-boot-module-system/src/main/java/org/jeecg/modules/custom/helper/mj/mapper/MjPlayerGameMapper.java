package org.jeecg.modules.custom.helper.mj.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.custom.helper.mj.entity.MjConf;
import org.jeecg.modules.custom.helper.mj.entity.MjPlayerGame;

/**
 * @Description: 可用游戏
 * @Author: jeecg-boot
 * @Date:   2019-09-07
 * @Version: V1.0
 */
public interface MjPlayerGameMapper extends BaseMapper<MjPlayerGame> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<MjPlayerGame> selectByMainId(@Param("mainId") String mainId);

    int countConf(@Param("pg") MjPlayerGame pg);
}
