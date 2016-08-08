/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.lg.web.exptmachine;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.k2data.platform.common.config.Global;
import com.k2data.platform.common.persistence.Page;
import com.k2data.platform.common.web.BaseController;
import com.k2data.platform.common.utils.DateUtils;
import com.k2data.platform.common.utils.IdGen;
import com.k2data.platform.common.utils.StringUtils;
import com.k2data.platform.common.utils.excel.ExportExcel;
import com.k2data.platform.common.utils.excel.ImportExcel;
import com.k2data.platform.modules.lg.entity.LgLabMachineProfileQuery;
import com.k2data.platform.modules.lg.entity.LgMachineDimension;
import com.k2data.platform.modules.lg.entity.LgMachineType;
import com.k2data.platform.modules.lg.entity.LgMachineprofile;
import com.k2data.platform.modules.lg.entity.exptmachine.LgExptMachinePick;
import com.k2data.platform.modules.lg.entity.exptmachine.LgExptMachinePickDetail;
import com.k2data.platform.modules.lg.service.LgMachineDimensionService;
import com.k2data.platform.modules.lg.service.LgMachineTypeService;
import com.k2data.platform.modules.lg.service.exptmachine.LgExptMachinePickDetailService;
import com.k2data.platform.modules.lg.service.exptmachine.LgExptMachinePickService;
import com.k2data.platform.modules.sys.utils.UserUtils;

/**
 * 试验机器选取Controller
 * @author wangshengguo
 * @version 2016-06-21
 */
@Controller
@RequestMapping(value = "${adminPath}/lg/exptmachine/lgExptMachinePick")
public class LgExptMachinePickController extends BaseController {

	@Autowired
	private LgExptMachinePickService lgExptMachinePickService;
	@Autowired
	private LgExptMachinePickDetailService lgExptMachinePickDetailService;

	@Autowired
	private LgMachineTypeService lgMachineTypeService;
	@Autowired
	private LgMachineDimensionService lgMachineDimensionService;
	
	@ModelAttribute
	public LgExptMachinePick get(@RequestParam(required=false) String id) {
		LgExptMachinePick entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lgExptMachinePickService.get(id);
		} 
		if (entity == null){
			entity = new LgExptMachinePick();
		}
		return entity;
	}
	
	@RequiresPermissions("lg:exptmachine:lgExptMachinePick:view")
	@RequestMapping(value = {"list", ""})
	public String list(LgExptMachinePick lgExptMachinePick, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LgExptMachinePick> page = lgExptMachinePickService.findPage(new Page<LgExptMachinePick>(request, response), lgExptMachinePick); 
		model.addAttribute("page", page);
		return "modules/lg/exptmachine/lgExptMachinePickList";
	}

	@RequiresPermissions("lg:exptmachine:lgExptMachinePick:view")
	@RequestMapping(value = "form")
	public String form(LgExptMachinePick lgExptMachinePick, Model model) {

		//用户新增时，设置初始值，默认选取时间为当前时间，有效标志为有效
		if(lgExptMachinePick.getId() == null || "".equals(lgExptMachinePick.getId())) {
			lgExptMachinePick.setIsEffective("1");
			lgExptMachinePick.setPickTime(new Date());
		}
		model.addAttribute("lgExptMachinePick", lgExptMachinePick);
		return "modules/lg/exptmachine/lgExptMachinePickForm";
	}

	@RequiresPermissions("lg:exptmachine:lgExptMachinePick:edit")
	@RequestMapping(value = "save")
	public String save(LgExptMachinePick lgExptMachinePick, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lgExptMachinePick)){
			return form(lgExptMachinePick, model);
		}
		//选取人为当前登陆用户
		if(lgExptMachinePick.getPickStaff() == null || "".equals(lgExptMachinePick.getPickStaff())) {
			lgExptMachinePick.setPickStaff(UserUtils.getUser().getNo());
		}
		lgExptMachinePickService.save(lgExptMachinePick);
		addMessage(redirectAttributes, "保存试验机器选取成功");
		return "redirect:"+Global.getAdminPath()+"/lg/exptmachine/lgExptMachinePick/?repage";
	}
	
	@RequiresPermissions("lg:exptmachine:lgExptMachinePick:edit")
	@RequestMapping(value = "delete")
	public String delete(LgExptMachinePick lgExptMachinePick, RedirectAttributes redirectAttributes) {
		lgExptMachinePickService.delete(lgExptMachinePick);
		addMessage(redirectAttributes, "删除试验机器选取成功");
		return "redirect:"+Global.getAdminPath()+"/lg/exptmachine/lgExptMachinePick/?repage";
	}
	
	@RequiresPermissions("lg:exptmachine:lgExptMachinePick:view")
	@RequestMapping(value = "info")
	public String info(LgExptMachinePick lgExptMachinePick, HttpServletRequest request, HttpServletResponse response, Model model) {
		LgMachineprofile lgMachineprofile= new LgMachineprofile();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		String pickId = request.getParameter("id");
		queryMap.put("pickId", pickId);
		lgMachineprofile.setQueryMap(queryMap);
		if(pickId != null && !"".equals(pickId)) {
			Page<LgMachineprofile> page = lgExptMachinePickDetailService.findMachinePage(new Page<LgMachineprofile>(request, response), lgMachineprofile);
			model.addAttribute("page", page);
		}
		return "modules/lg/exptmachine/lgExptMachinePickInfo";
	}
	@RequiresPermissions("lg:exptmachine:lgExptMachinePick:view")
	@RequestMapping(value = "infoPop")
	public String infoPop(LgExptMachinePick lgExptMachinePick, HttpServletRequest request, HttpServletResponse response, Model model) {
		//model.addAttribute("lgExptMachinePick", lgExptMachinePick);
		return "modules/lg/exptmachine/lgExptMachinePickAdd";
	}
	
	/**
	 * 根据机器类型获取产品型号记录列表
	 * @param machineType
	 * @return
	 */
	
	@RequestMapping(value="productTypeList")
	public @ResponseBody List<String> productTypeList(String machineType) {
		List<String> productTypeList = lgMachineTypeService.getProductTypeList(machineType);
		return productTypeList;
	}
	
	/**
	 * 根据产品型号获取订货号记录列表
	 * @param productType
	 * @return
	 */
	
	@RequestMapping(value="orderNumberList")
	public @ResponseBody List<LgMachineType> orderNumberList(String productType) {
		LgMachineType lgMachineType = new LgMachineType();
		lgMachineType.setProductType(productType);
		List<LgMachineType> productTypeList = lgMachineTypeService.findList(lgMachineType);
		return productTypeList;
	}
	/**
	 * 根据维度类别获取列表
	 * @param machineType
	 * @return
	 */
	@RequestMapping(value="dimensionList")	
	public @ResponseBody List<LgMachineDimension> dimensionList(String dimensionType) {
		LgMachineDimension lgMachineDimension = new LgMachineDimension();
		lgMachineDimension.setDimensionType(dimensionType); 
		List<LgMachineDimension> dimensionList = lgMachineDimensionService.findList(lgMachineDimension);
		return dimensionList;
	}
	
	@RequestMapping(value = "queryList")
	public @ResponseBody Page<LgLabMachineProfileQuery> queryList(@ModelAttribute("machineType") LgLabMachineProfileQuery lgLabMachineProfileQuery, 
			HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LgLabMachineProfileQuery> page = lgExptMachinePickDetailService.findMachinePageAdd(new Page<LgLabMachineProfileQuery>(request, response), lgLabMachineProfileQuery); 
		return page;
	}
	
	/**
	 * 下载导入试验机器明细数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	
	@RequiresPermissions("lg:exptmachine:lgExptMachinePick:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String pickId = request.getParameter("id");
		try {
            String fileName = "试验机器导入模板.xlsx";
    		List<LgExptMachinePickDetail> list = Lists.newArrayList(); 
    		new ExportExcel("试验机器选取", LgExptMachinePickDetail.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lg/exptmachine/lgExptMachinePick/info?id=" + pickId;
    }

	/**
	 * 导入试验机器明细数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("lg:exptmachine:lgExptMachinePick:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(HttpServletRequest request,MultipartFile file, RedirectAttributes redirectAttributes) {
		String pickId = request.getParameter("id");
		try {
			int successNum = 0;
			int failureNum = 0;
			int notExistMachineNum = 0;
			int totalNum = 0;
			///1.获取导入数据
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<LgExptMachinePickDetail> list = ei.getDataList(LgExptMachinePickDetail.class);
			totalNum = list.size();

			StringBuilder failureMsg = new StringBuilder();

			List<LgExptMachinePickDetail> machineList = new ArrayList<LgExptMachinePickDetail>();

			String remarks = DateUtils.formatDateTime(new Date()) + "文件导入";
			//用户导入的整机编码内容列表
			List<String> codeList = new ArrayList<String>();
			for (LgExptMachinePickDetail machine : list){
				String machineId = StringUtils.trim(machine.getMachineId());
				if (!StringUtils.isBlank(machineId)){
					codeList.add(machineId);
				} 
			}
			//////
			///2.筛选应经存在的整机
			//查询条件 查询该批次下已经存在的试验机器
			LgExptMachinePickDetail pick = new LgExptMachinePickDetail();
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("codeList", codeList);
			queryMap.put("pickId", pickId);
			pick.setQueryMap(queryMap);
			List<String> existList = lgExptMachinePickDetailService.findMachineCodeList(pick);
			//存在的条数，所以导入失败
			failureNum = existList.size();
			
			for(String exitsCode : existList) {
				failureMsg.append(exitsCode).append(",");
			}
			if (failureNum>0){
				failureMsg.insert(0, "，"+failureNum+" 台试验机器失败，原因：该批次下已存在，不需要重新导入，整机编码信息如下：");
				//剔除存在的记录
				codeList.removeAll(existList);
			}
	
			//去重
			HashSet<String> tmpHashSet  = new HashSet<String>(codeList); 
			codeList.clear(); 
			codeList.addAll(tmpHashSet); 
			
			for(String machineId : codeList) {
				LgExptMachinePickDetail machine = new LgExptMachinePickDetail();
				machine.setId(IdGen.uuid());
				machine.setPickId(pickId);
				machine.setMachineId(machineId);
				machine.setRemarks(remarks);
				machineList.add(machine);
			}
		    ///3.导入筛选后的数据
			if(machineList != null && machineList.size() > 0) {
				successNum = lgExptMachinePickDetailService.saveImportBatch(machineList);
			}
			notExistMachineNum = totalNum - failureNum - successNum;
			if(notExistMachineNum > 0) {
				failureMsg.append("另外").append(notExistMachineNum).append("台试验机不存在或存在重复记录，请检查导入的数据;");
			}
			////////
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 台试验机器"+failureMsg);
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导入用户失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lg/exptmachine/lgExptMachinePick/info?id=" + pickId;
    }
	@RequiresPermissions("lg:exptmachine:lgExptMachinePick:view")
	@RequestMapping(value = "checkBatchNumber")
	public @ResponseBody Boolean checkBatchNumber(LgExptMachinePick lgExptMachinePick) {
		return lgExptMachinePickService.checkBatchNumber(lgExptMachinePick);
	}
}