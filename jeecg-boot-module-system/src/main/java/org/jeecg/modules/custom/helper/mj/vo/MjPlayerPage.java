package org.jeecg.modules.custom.helper.mj.vo;

import java.util.List;
import org.jeecg.modules.custom.helper.mj.entity.MjPlayer;
import org.jeecg.modules.custom.helper.mj.entity.MjPlayerAccount;
import org.jeecg.modules.custom.helper.mj.entity.MjPlayerCard;
import org.jeecg.modules.custom.helper.mj.entity.MjPlayerGame;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @Description: 游戏玩家
 * @Author: jeecg-boot
 * @Date:   2019-09-06
 * @Version: V1.0
 */
@Data
public class MjPlayerPage {
	
	/**主键*/
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
	/**昵称*/
	@Excel(name = "昵称", width = 15)
	private java.lang.String name;
	/**性别*/
	@Excel(name = "性别", width = 15)
	private java.lang.String sex;
	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
	private java.lang.String mobile;
	/**用户状态*/
	@Excel(name = "用户状态", width = 15)
	private java.lang.String status;
	/**推广码*/
	@Excel(name = "推广码", width = 15)
	private java.lang.String promoCode;
	/**会员级别*/
	@Excel(name = "会员级别", width = 15)
	private java.lang.String lever;
	/**上级*/
	@Excel(name = "上级", width = 15)
	private java.lang.String superior;
	/**通知方式*/
	@Excel(name = "通知方式", width = 15)
	private java.lang.String noticeWay;
	/**可返次数*/
	@Excel(name = "可返次数", width = 15)
	private java.lang.String canCashbackTime;
	
	@ExcelCollection(name="玩家帐号")
	private List<MjPlayerAccount> mjPlayerAccountList;	
	@ExcelCollection(name="玩家银行卡")
	private List<MjPlayerCard> mjPlayerCardList;	
	@ExcelCollection(name="可用游戏")
	private List<MjPlayerGame> mjPlayerGameList;	
	
}
