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
 * @Description: 支付记录
 * @Author: jeecg-boot
 * @Date:   2019-09-06
 * @Version: V1.0
 */
@Data
@TableName("mj_pay_record")
public class MjPayRecord implements Serializable {
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
	/**收入支出*/
	@Excel(name = "收入支出", width = 15)
	private java.lang.String payType;
	/**支付用户*/
	@Excel(name = "支付用户", width = 15)
	private java.lang.String fromPlayerId;
	/**支付平台*/
	@Excel(name = "支付平台", width = 15)
	private java.lang.String fromAccountType;
	/**支付帐号*/
	@Excel(name = "支付帐号", width = 15)
	private java.lang.String fromAccount;
	/**支付银行*/
	@Excel(name = "支付银行", width = 15)
	private java.lang.String fromBankType;
	/**支付卡号*/
	@Excel(name = "支付卡号", width = 15)
	private java.lang.String fromBankAccount;
	/**接收用户*/
	@Excel(name = "接收用户", width = 15)
	private java.lang.String toPlayerId;
	/**接收平台*/
	@Excel(name = "接收平台", width = 15)
	private java.lang.String toAccountType;
	/**接收帐户*/
	@Excel(name = "接收帐户", width = 15)
	private java.lang.String toAccount;
	/**接收银行*/
	@Excel(name = "接收银行", width = 15)
	private java.lang.String toBankType;
	/**接收卡号*/
	@Excel(name = "接收卡号", width = 15)
	private java.lang.String toBankAccount;
	/**支付订单号*/
	@Excel(name = "支付订单号", width = 15)
	private java.lang.String payOrderId;
	/**支付金额*/
	@Excel(name = "支付金额", width = 15)
	private java.lang.String payNum;
	/**支付时间*/
	@Excel(name = "支付时间", width = 15)
	private java.lang.String payTime;
}
