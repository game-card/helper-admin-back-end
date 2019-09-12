package org.jeecg.modules.custom.ad.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.custom.ad.entity.Ad;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.custom.ad.vo.AdVo;

/**
 * @Description: 广告
 * @Author: jeecg-boot
 * @Date:   2019-09-06
 * @Version: V1.0
 */
public interface AdMapper extends BaseMapper<Ad> {

    public List<Ad> selectOnline(@Param("adVo") AdVo advo);

}
