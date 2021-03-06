package org.jeecg.modules.custom.helper.mj.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.custom.helper.mj.entity.MjPlayerAccount;

/**
 * @Description: 玩家帐号
 * @Author: jeecg-boot
 * @Date:   2019-09-07
 * @Version: V1.0
 */
public interface MjPlayerAccountMapper extends BaseMapper<MjPlayerAccount> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<MjPlayerAccount> selectByMainId(@Param("mainId") String mainId);
}
