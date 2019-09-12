package org.jeecg.modules.custom.helper.mj.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 游戏玩家
 * @Author: jeecg-boot
 * @Date:   2019-09-06
 * @Version: V1.0
 */
@Data
@TableName("mj_player")
public class MjPlayer implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.UUID)
	private java.lang.String id;
	/**创建人*/
	private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**更新人*/
	private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**昵称*/
	private java.lang.String name;
	/**性别*/
	private java.lang.String sex;
	/**手机号码*/
	private java.lang.String mobile;
	/**用户状态*/
	private java.lang.String status;
	/**推广码*/
	private java.lang.String promoCode;
	/**会员级别*/
	private java.lang.String lever;
	/**上级*/
	private java.lang.String superior;
	/**通知方式*/
	private java.lang.String noticeWay;
	/**可返次数*/
	private java.lang.String canCashbackTime;
}
