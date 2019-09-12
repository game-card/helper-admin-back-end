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
 * @Description: 平台收款银行卡
 * @Author: jeecg-boot
 * @Date:   2019-09-06
 * @Version: V1.0
 */
@Data
@TableName("mj_card")
public class MjCard implements Serializable {
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
	/**收款人名称*/
	@Excel(name = "收款人名称", width = 15)
	private java.lang.String name;
	/**银行*/
	@Excel(name = "银行", width = 15)
	private java.lang.String bankType;
	/**银行卡号码*/
	@Excel(name = "银行卡号码", width = 15)
	private java.lang.String cardNum;
	/**帐号状态*/
	@Excel(name = "帐号状态", width = 15)
	private java.lang.String status;
	/**收款二维码*/
	@Excel(name = "收款二维码", width = 15)
	private java.lang.String qrCodeImage;
}
