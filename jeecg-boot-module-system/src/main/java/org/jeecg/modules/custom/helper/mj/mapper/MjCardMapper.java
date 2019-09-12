package org.jeecg.modules.custom.helper.mj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.custom.helper.mj.entity.MjCard;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 平台收款银行卡
 * @Author: jeecg-boot
 * @Date:   2019-09-06
 * @Version: V1.0
 */
public interface MjCardMapper extends BaseMapper<MjCard> {

    List<MjCard> selectOnline();
}
