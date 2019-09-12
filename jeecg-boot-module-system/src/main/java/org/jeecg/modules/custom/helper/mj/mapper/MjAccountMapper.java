package org.jeecg.modules.custom.helper.mj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.custom.helper.mj.entity.MjAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 平台收款帐号
 * @Author: jeecg-boot
 * @Date:   2019-09-06
 * @Version: V1.0
 */
public interface MjAccountMapper extends BaseMapper<MjAccount> {

    List<MjAccount> selectOnline();
}
