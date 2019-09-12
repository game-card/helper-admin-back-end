package org.jeecg.modules.custom.helper.mj.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.custom.helper.mj.entity.MjPlayerAccount;
import org.jeecg.modules.custom.helper.mj.entity.MjPlayerCard;
import org.jeecg.modules.custom.helper.mj.entity.MjPlayerGame;
import org.jeecg.modules.custom.helper.mj.entity.MjPlayer;
import org.jeecg.modules.custom.helper.mj.vo.MjPlayerPage;
import org.jeecg.modules.custom.helper.mj.service.IMjPlayerService;
import org.jeecg.modules.custom.helper.mj.service.IMjPlayerAccountService;
import org.jeecg.modules.custom.helper.mj.service.IMjPlayerCardService;
import org.jeecg.modules.custom.helper.mj.service.IMjPlayerGameService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;

 /**
 * @Description: 游戏玩家
 * @Author: jeecg-boot
 * @Date:   2019-09-06
 * @Version: V1.0
 */
@RestController
@RequestMapping("/helper.mj/mjPlayer")
@Slf4j
public class MjPlayerController {
	@Autowired
	private IMjPlayerService mjPlayerService;
	@Autowired
	private IMjPlayerAccountService mjPlayerAccountService;
	@Autowired
	private IMjPlayerCardService mjPlayerCardService;
	@Autowired
	private IMjPlayerGameService mjPlayerGameService;
	
	/**
	  * 分页列表查询
	 * @param mjPlayer
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<MjPlayer>> queryPageList(MjPlayer mjPlayer,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<MjPlayer>> result = new Result<IPage<MjPlayer>>();
		QueryWrapper<MjPlayer> queryWrapper = QueryGenerator.initQueryWrapper(mjPlayer, req.getParameterMap());
		Page<MjPlayer> page = new Page<MjPlayer>(pageNo, pageSize);
		IPage<MjPlayer> pageList = mjPlayerService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param mjPlayerPage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<MjPlayer> add(@RequestBody MjPlayerPage mjPlayerPage) {
		Result<MjPlayer> result = new Result<MjPlayer>();
		try {
			MjPlayer mjPlayer = new MjPlayer();
			BeanUtils.copyProperties(mjPlayerPage, mjPlayer);
			
			mjPlayerService.saveMain(mjPlayer, mjPlayerPage.getMjPlayerAccountList(),mjPlayerPage.getMjPlayerCardList(),mjPlayerPage.getMjPlayerGameList());
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param mjPlayerPage
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<MjPlayer> edit(@RequestBody MjPlayerPage mjPlayerPage) {
		Result<MjPlayer> result = new Result<MjPlayer>();
		MjPlayer mjPlayer = new MjPlayer();
		BeanUtils.copyProperties(mjPlayerPage, mjPlayer);
		MjPlayer mjPlayerEntity = mjPlayerService.getById(mjPlayer.getId());
		if(mjPlayerEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = mjPlayerService.updateById(mjPlayer);
			mjPlayerService.updateMain(mjPlayer, mjPlayerPage.getMjPlayerAccountList(),mjPlayerPage.getMjPlayerCardList(),mjPlayerPage.getMjPlayerGameList());
			result.success("修改成功!");
		}
		
		return result;
	}
	
	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			mjPlayerService.delMain(id);
		} catch (Exception e) {
			log.error("删除失败",e.getMessage());
			return Result.error("删除失败!");
		}
		return Result.ok("删除成功!");
	}
	
	/**
	  *  批量删除
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	public Result<MjPlayer> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<MjPlayer> result = new Result<MjPlayer>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.mjPlayerService.delBatchMain(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryById")
	public Result<MjPlayer> queryById(@RequestParam(name="id",required=true) String id) {
		Result<MjPlayer> result = new Result<MjPlayer>();
		MjPlayer mjPlayer = mjPlayerService.getById(id);
		if(mjPlayer==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(mjPlayer);
			result.setSuccess(true);
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryMjPlayerAccountByMainId")
	public Result<List<MjPlayerAccount>> queryMjPlayerAccountListByMainId(@RequestParam(name="id",required=true) String id) {
		Result<List<MjPlayerAccount>> result = new Result<List<MjPlayerAccount>>();
		List<MjPlayerAccount> mjPlayerAccountList = mjPlayerAccountService.selectByMainId(id);
		result.setResult(mjPlayerAccountList);
		result.setSuccess(true);
		return result;
	}
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryMjPlayerCardByMainId")
	public Result<List<MjPlayerCard>> queryMjPlayerCardListByMainId(@RequestParam(name="id",required=true) String id) {
		Result<List<MjPlayerCard>> result = new Result<List<MjPlayerCard>>();
		List<MjPlayerCard> mjPlayerCardList = mjPlayerCardService.selectByMainId(id);
		result.setResult(mjPlayerCardList);
		result.setSuccess(true);
		return result;
	}
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryMjPlayerGameByMainId")
	public Result<List<MjPlayerGame>> queryMjPlayerGameListByMainId(@RequestParam(name="id",required=true) String id) {
		Result<List<MjPlayerGame>> result = new Result<List<MjPlayerGame>>();
		List<MjPlayerGame> mjPlayerGameList = mjPlayerGameService.selectByMainId(id);
		result.setResult(mjPlayerGameList);
		result.setSuccess(true);
		return result;
	}

  /**
      * 导出excel
   *
   * @param request
   * @param response
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, MjPlayer mjPlayer) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<MjPlayer> queryWrapper = QueryGenerator.initQueryWrapper(mjPlayer, request.getParameterMap());
      List<MjPlayer> queryList = mjPlayerService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<MjPlayer> mjPlayerList = new ArrayList<MjPlayer>();
      if(oConvertUtils.isEmpty(selections)) {
    	  mjPlayerList = queryList;
      }else {
    	  List<String> selectionList = Arrays.asList(selections.split(","));
    	  mjPlayerList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }
	  // Step.2 组装pageList
      List<MjPlayerPage> pageList = new ArrayList<MjPlayerPage>();
      for (MjPlayer main : mjPlayerList) {
          MjPlayerPage vo = new MjPlayerPage();
          BeanUtils.copyProperties(main, vo);
          List<MjPlayerAccount> mjPlayerAccountList = mjPlayerAccountService.selectByMainId(main.getId());
          vo.setMjPlayerAccountList(mjPlayerAccountList);
          List<MjPlayerCard> mjPlayerCardList = mjPlayerCardService.selectByMainId(main.getId());
          vo.setMjPlayerCardList(mjPlayerCardList);
          List<MjPlayerGame> mjPlayerGameList = mjPlayerGameService.selectByMainId(main.getId());
          vo.setMjPlayerGameList(mjPlayerGameList);
          pageList.add(vo);
      }
      // Step.3 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "游戏玩家列表");
      mv.addObject(NormalExcelConstants.CLASS, MjPlayerPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("游戏玩家列表数据", "导出人:Jeecg", "导出信息"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
  }

  /**
      * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<MjPlayerPage> list = ExcelImportUtil.importExcel(file.getInputStream(), MjPlayerPage.class, params);
              for (MjPlayerPage page : list) {
                  MjPlayer po = new MjPlayer();
                  BeanUtils.copyProperties(page, po);
                  mjPlayerService.saveMain(po, page.getMjPlayerAccountList(),page.getMjPlayerCardList(),page.getMjPlayerGameList());
              }
              return Result.ok("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.ok("文件导入失败！");
  }

}
