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
 * @Description: 返现记录
 * @Author: jeecg-boot
 * @Date:   2019-09-06
 * @Version: V1.0
 */
@Data
@TableName("mj_cash_back_record")
public class MjCashBackRecord implements Serializable {
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
	/**申请用户*/
	@Excel(name = "申请用户", width = 15)
	private java.lang.String playerId;
	/**申请金额*/
	@Excel(name = "申请金额", width = 15)
	private java.lang.String totalNum;
	/**返现状态*/
	@Excel(name = "返现状态", width = 15)
	private java.lang.String status;
	/**执行金额*/
	@Excel(name = "执行金额", width = 15)
	private java.lang.String doneNum;
	/**执行时间*/
	@Excel(name = "执行时间", width = 15)
	private java.lang.String doneTime;
	/**支付记录*/
	@Excel(name = "支付记录", width = 15)
	private java.lang.String payRecordId;
	/**回执图片*/
	@Excel(name = "回执图片", width = 15)
	private java.lang.String picturePath;
}
