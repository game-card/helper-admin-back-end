package org.jeecg.modules.custom.helper.mj.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;

/**
 * @Description: 可返记录
 * @Author: jeecg-boot
 * @Date:   2019-09-06
 * @Version: V1.0
 */
@Data
@TableName("mj_cash_back_can")
public class MjCashBackCan implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.UUID)
	private java.lang.String id;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
	private java.lang.String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
	private java.lang.String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;
	/**所属部门*/
	@Excel(name = "所属部门", width = 15)
	private java.lang.String sysOrgCode;
	/**支付记录*/
	@Excel(name = "支付记录", width = 15)
	private java.lang.String payRecordId;
	/**玩家ID*/
	@Excel(name = "玩家ID", width = 15)
	private java.lang.String playerId;
	/**可返金额*/
	@Excel(name = "可返金额", width = 15)
	private java.lang.String cashBackNum;
	/**可返状态*/
	@Excel(name = "可返状态", width = 15)
	private java.lang.String status;
	/**一级下线ID*/
	@Excel(name = "一级下线ID", width = 15)
	private java.lang.String followerDirectId;
	/**一级下线*/
	@Excel(name = "一级下线", width = 15)
	private java.lang.String followerDirect;
	/**二级下线ID*/
	@Excel(name = "二级下线ID", width = 15)
	private java.lang.String followerIndirectId;
	/**二级下线*/
	@Excel(name = "二级下线", width = 15)
	private java.lang.String followerIndirect;
	/**返现ID*/
	@Excel(name = "返现ID", width = 15)
	private java.lang.String cashBackId;
}
