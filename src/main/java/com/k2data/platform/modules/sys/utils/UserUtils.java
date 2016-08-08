/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.k2data.platform.modules.sys.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.k2data.platform.common.service.BaseService;
import com.k2data.platform.common.utils.CacheUtils;
import com.k2data.platform.common.utils.SpringContextHolder;
import com.k2data.platform.modules.lg.common.service.GeneralQueryService;
import com.k2data.platform.modules.lg.dao.LgGpsTemplateDao;
import com.k2data.platform.modules.lg.dao.LgMachineDimensionDao;
import com.k2data.platform.modules.lg.dao.LgMachineTypeDao;
import com.k2data.platform.modules.lg.dao.LgMachineprofileDao;
import com.k2data.platform.modules.lg.dao.citylist.LgMapCityDao;
import com.k2data.platform.modules.lg.dao.exptmachine.LgExptMachinePickDao;
import com.k2data.platform.modules.lg.entity.LgGpsTemplate;
import com.k2data.platform.modules.lg.entity.LgGpsTemplateDetail;
import com.k2data.platform.modules.lg.entity.LgMachineDimension;
import com.k2data.platform.modules.lg.entity.LgMachineType;
import com.k2data.platform.modules.lg.entity.LgMachineprofile;
import com.k2data.platform.modules.lg.entity.citylist.LgMapCity;
import com.k2data.platform.modules.lg.entity.exptmachine.LgExptMachinePick;
import com.k2data.platform.modules.sys.dao.AreaDao;
import com.k2data.platform.modules.sys.dao.MenuDao;
import com.k2data.platform.modules.sys.dao.OfficeDao;
import com.k2data.platform.modules.sys.dao.RoleDao;
import com.k2data.platform.modules.sys.dao.UserDao;
import com.k2data.platform.modules.sys.entity.Area;
import com.k2data.platform.modules.sys.entity.Menu;
import com.k2data.platform.modules.sys.entity.Office;
import com.k2data.platform.modules.sys.entity.Role;
import com.k2data.platform.modules.sys.entity.User;
import com.k2data.platform.modules.sys.security.SystemAuthorizingRealm.Principal;

/**
 * 用户工具类
 * @author ThinkGem
 * @version 2013-12-05
 */
public class UserUtils {

	private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
	private static RoleDao roleDao = SpringContextHolder.getBean(RoleDao.class);
	private static MenuDao menuDao = SpringContextHolder.getBean(MenuDao.class);
	private static AreaDao areaDao = SpringContextHolder.getBean(AreaDao.class);
	private static OfficeDao officeDao = SpringContextHolder.getBean(OfficeDao.class);
	private static LgMachineDimensionDao lgMachineDimensionDao = SpringContextHolder.getBean(LgMachineDimensionDao.class);
	private static LgMachineprofileDao lgMachineprofileDao = SpringContextHolder.getBean(LgMachineprofileDao.class);
	private static LgGpsTemplateDao lgGpsTemplateDao = SpringContextHolder.getBean(LgGpsTemplateDao.class);
	private static LgMachineTypeDao lgMachineTypeDao = SpringContextHolder.getBean(LgMachineTypeDao.class);
	private static LgExptMachinePickDao lgExptMachinePickDao = SpringContextHolder.getBean(LgExptMachinePickDao.class);
	
	private static GeneralQueryService generalQueryService = SpringContextHolder.getBean(GeneralQueryService.class);
	
	private static LgMapCityDao lgMapCityDao = SpringContextHolder.getBean(LgMapCityDao.class);
	
	public static final String USER_CACHE = "userCache";
	public static final String USER_CACHE_ID_ = "id_";
	public static final String USER_CACHE_LOGIN_NAME_ = "ln";
	public static final String USER_CACHE_LIST_BY_OFFICE_ID_ = "oid_";

	public static final String CACHE_ROLE_LIST = "roleList";
	public static final String CACHE_MENU_LIST = "menuList";
	public static final String CACHE_AREA_LIST = "areaList";
	public static final String CACHE_OFFICE_LIST = "officeList";
	public static final String CACHE_OFFICE_ALL_LIST = "officeAllList";
	public static final String CACHE_MODEL_LIST = "modelList";
	public static final String CACHE_CONTROLL_LIST = "controllList";
	public static final String CACHE_DIMENSION_LIST = "dimensionList";
	
	public static final Map<String, String> customCacheMap = new HashMap<String, String>();
	static {
	    customCacheMap.put("CACHE_MATERIALLEDGER_LIST", "materialledgerList");
	    customCacheMap.put("CACHE_MACHINE_LIST", "machineList");
	    customCacheMap.put("CACHE_TEMPLATE_LIST", "templateList");
	    customCacheMap.put("CACHE_PRODUCT_TYPE_LIST", "productTypeList");
	    customCacheMap.put("CACHE_ORDER_NUMBER_LIST", "orderNumberList");
	    customCacheMap.put("CACHE_BATCHNUMBERS_LIST", "batchNumberList");
	    customCacheMap.put("CACHE_SENSOR_NAME_LIST", "sensorNameList");
	    customCacheMap.put("CACHE_LEAFCITY_LIST", "leafCityList");
	}
	
	/**
     * 根据设备号查询传感器列表
     * 
     * @param productType 主机型号
     * @return
     */
    public static List<LgGpsTemplateDetail> getSensorNameList(String deviceNo) {
        List<LgGpsTemplateDetail> gpsTemplateDetails = getCache(customCacheMap.get("CACHE_SENSOR_NAME_LIST"));
        
        if (gpsTemplateDetails == null) {
            gpsTemplateDetails = generalQueryService.getSensorNameList(deviceNo);
            putCache(customCacheMap.get("CACHE_SENSOR_NAME_LIST"), gpsTemplateDetails);
        }
        
        return gpsTemplateDetails;
    }
	
	/**
	 * 获得订货号列表
	 * 
	 * @param productType 主机型号
	 * @return
	 */
	public static List<LgMachineType> getOrderNumberList(String productType) {
		List<LgMachineType> orderNumberList = getCache(customCacheMap.get("CACHE_ORDER_NUMBER_LIST"));
		
		if (orderNumberList == null) {
			orderNumberList = generalQueryService.getOrderNumberList(productType);
			putCache(customCacheMap.get("CACHE_ORDER_NUMBER_LIST"), orderNumberList);
		}
		
		return orderNumberList;
	}
	
	/**
	 * 获得主机型号列表
	 * 
	 * @param machineType 主机类别
	 * @return
	 */
	public static List<String> getProductTypeList(String machineType) {
		List<String> productTypeList = getCache(customCacheMap.get("CACHE_PRODUCT_TYPE_LIST"));
		
		if (productTypeList == null) {
			productTypeList = generalQueryService.getProductTypeList(machineType);
			putCache(customCacheMap.get("CACHE_PRODUCT_TYPE_LIST"), productTypeList);
		}
		
		return productTypeList;
	}
	
	
	/**
	 * 获取纬度列表
	 * @return 取不到返回null
	 */
	public static List<LgMachineDimension> getMachineDimension(Integer type){
		List<LgMachineDimension> dimensionList = getCache(CACHE_MODEL_LIST+type);
		if (dimensionList == null){
			dimensionList = lgMachineDimensionDao.getMachineDimension(type);
			putCache(CACHE_MODEL_LIST+type, dimensionList);
		}	
		if(dimensionList != null)
			return dimensionList;
		// 如果数据为空，则返回实例化空的GoldBasicmodel对象。
		return null;
	}
	
	/**
	 * 获取整机型号
	 */
	public static List<LgMachineType> getModelNumber(){
			List<LgMachineType> modelNumberList = lgMachineTypeDao.getModelNumberList();
			return modelNumberList;
	}

	/**
	 * 获取试验批次号
	 */
	public static List<LgExptMachinePick> getBatchNumber(){
		List<LgExptMachinePick> batchNumberList = getCache(customCacheMap.get("CACHE_BATCHNUMBERS_LIST"));
		if (batchNumberList == null){
			batchNumberList = lgExptMachinePickDao.getBatchNumberList();
			putCache(customCacheMap.get("CACHE_BATCHNUMBERS_LIST"), batchNumberList);
		}	
		if(batchNumberList != null)
			return batchNumberList;
		// 如果数据为空，则返回实例化空的GoldBasicmodel对象。
		return null;
	}

	/**
	 * 获取整机信息
	 * @return 取不到返回null
	 */
	public static List<LgMachineprofile> getMachineInfo(){
		List<LgMachineprofile> machineList = getCache(customCacheMap.get("CACHE_MACHINE_LIST"));
		if (machineList == null){
			machineList = lgMachineprofileDao.findAllList(new LgMachineprofile());
			putCache(customCacheMap.get("CACHE_MACHINE_LIST"), machineList);
		}	
		if(machineList != null)
			return machineList;
		// 如果数据为空，则返回实例化空的GoldBasicmodel对象。
		return null;
	}
	
	/**
	 * 获取整机信息
	 * @return 取不到返回null
	 */
	public static List<LgMapCity> getLeafCityList(){
		List<LgMapCity> leafCityList = getCache(customCacheMap.get("CACHE_LEAFCITY_LIST"));
		if (leafCityList == null){
			leafCityList = lgMapCityDao.getLeafCityList();
			putCache(customCacheMap.get("CACHE_LEAFCITY_LIST"), leafCityList);
		}	
		if(leafCityList != null)
			return leafCityList;
		// 如果数据为空，则返回实例化空的GoldBasicmodel对象。
		return null;
	}
	/**
	 * 获取GPS模板信息
	 * @return 取不到返回null
	 */
	public static List<LgGpsTemplate> getGpsTemplateInfo(){
		List<LgGpsTemplate> templateList = getCache(customCacheMap.get("CACHE_TEMPLATE_LIST"));
		LgGpsTemplate lgGpsTemplate = new LgGpsTemplate();
		lgGpsTemplate.setStopMark(1);
		if (templateList == null){
			templateList = lgGpsTemplateDao.findAllList(lgGpsTemplate);
			putCache(customCacheMap.get("CACHE_TEMPLATE_LIST"), templateList);
		}	
		if(templateList != null)
			return templateList;
		// 如果数据为空，则返回实例化空的GoldBasicmodel对象。
		return null;
	}
	
	/**
	 * 根据ID获取用户
	 * @param id
	 * @return 取不到返回null
	 */
	public static User get(String id){
		User user = (User)CacheUtils.get(USER_CACHE, USER_CACHE_ID_ + id);
		if (user ==  null){
			user = userDao.get(id);
			if (user == null){
				return null;
			}
			user.setRoleList(roleDao.findList(new Role(user)));
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
			CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
		}
		return user;
	}
	
	/**
	 * 根据登录名获取用户
	 * @param loginName
	 * @return 取不到返回null
	 */
	public static User getByLoginName(String loginName){
		User user = (User)CacheUtils.get(USER_CACHE, USER_CACHE_LOGIN_NAME_ + loginName);
		if (user == null){
			user = userDao.getByLoginName(new User(null, loginName));
			if (user == null){
				return null;
			}
			user.setRoleList(roleDao.findList(new Role(user)));
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
			CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
		}
		return user;
	}
	
	/**
	 * 清除当前用户缓存
	 */
	public static void clearCache(){
		removeCache(CACHE_ROLE_LIST);
		removeCache(CACHE_MENU_LIST);
		removeCache(CACHE_AREA_LIST);
		removeCache(CACHE_OFFICE_LIST);
		removeCache(CACHE_OFFICE_ALL_LIST);
		removeCache(CACHE_MODEL_LIST);
		removeCache(CACHE_CONTROLL_LIST);
		removeCache(CACHE_DIMENSION_LIST);
		
		for (Entry<String, String> entry : customCacheMap.entrySet()) {
		    removeCache(entry.getValue());
        }
		
		UserUtils.clearCache(getUser());
	}
	
	/**
	 * 清除指定用户缓存
	 * @param user
	 */
	public static void clearCache(User user){
		CacheUtils.remove(USER_CACHE, USER_CACHE_ID_ + user.getId());
		CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName());
		CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getOldLoginName());
		if (user.getOffice() != null && user.getOffice().getId() != null){
			CacheUtils.remove(USER_CACHE, USER_CACHE_LIST_BY_OFFICE_ID_ + user.getOffice().getId());
		}
	}
	
	/**
	 * 获取当前用户
	 * @return 取不到返回 new User()
	 */
	public static User getUser(){
		Principal principal = getPrincipal();
		if (principal!=null){
			User user = get(principal.getId());
			if (user != null){
				return user;
			}
			return new User();
		}
		// 如果没有登录，则返回实例化空的User对象。
		return new User();
	}

	/**
	 * 获取当前用户角色列表
	 * @return
	 */
	public static List<Role> getRoleList(){
		@SuppressWarnings("unchecked")
		List<Role> roleList = (List<Role>)getCache(CACHE_ROLE_LIST);
		if (roleList == null){
			User user = getUser();
			if (user.isAdmin()){
				roleList = roleDao.findAllList(new Role());
			}else{
				Role role = new Role();
				role.getSqlMap().put("dsf", BaseService.dataScopeFilter(user.getCurrentUser(), "o", "u"));
				roleList = roleDao.findList(role);
			}
			putCache(CACHE_ROLE_LIST, roleList);
		}
		return roleList;
	}
	
	/**
	 * 获取当前用户授权菜单
	 * @return
	 */
	public static List<Menu> getMenuList(){
		@SuppressWarnings("unchecked")
		List<Menu> menuList = (List<Menu>)getCache(CACHE_MENU_LIST);
		if (menuList == null){
			User user = getUser();
			if (user.isAdmin()){
				menuList = menuDao.findAllList(new Menu());
			}else{
				Menu m = new Menu();
				m.setUserId(user.getId());
				menuList = menuDao.findByUserId(m);
			}
			putCache(CACHE_MENU_LIST, menuList);
		}
		return menuList;
	}
	
	/**
	 * 获取当前用户授权的区域
	 * @return
	 */
	public static List<Area> getAreaList(){
		@SuppressWarnings("unchecked")
		List<Area> areaList = (List<Area>)getCache(CACHE_AREA_LIST);
		if (areaList == null){
			areaList = areaDao.findAllList(new Area());
			putCache(CACHE_AREA_LIST, areaList);
		}
		return areaList;
	}
	
	/**
	 * 获取当前用户有权限访问的部门
	 * @return
	 */
	public static List<Office> getOfficeList(){
		@SuppressWarnings("unchecked")
		List<Office> officeList = (List<Office>)getCache(CACHE_OFFICE_LIST);
		if (officeList == null){
			User user = getUser();
			if (user.isAdmin()){
				officeList = officeDao.findAllList(new Office());
			}else{
				Office office = new Office();
				office.getSqlMap().put("dsf", BaseService.dataScopeFilter(user, "a", ""));
				officeList = officeDao.findList(office);
			}
			putCache(CACHE_OFFICE_LIST, officeList);
		}
		return officeList;
	}

	/**
	 * 获取当前用户有权限访问的部门
	 * @return
	 */
	public static List<Office> getOfficeAllList(){
		@SuppressWarnings("unchecked")
		List<Office> officeList = (List<Office>)getCache(CACHE_OFFICE_ALL_LIST);
		if (officeList == null){
			officeList = officeDao.findAllList(new Office());
		}
		return officeList;
	}
	
	/**
	 * 获取授权主要对象
	 */
	public static Subject getSubject(){
		return SecurityUtils.getSubject();
	}
	
	/**
	 * 获取当前登录者对象
	 */
	public static Principal getPrincipal(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Principal principal = (Principal)subject.getPrincipal();
			if (principal != null){
				return principal;
			}
//			subject.logout();
		}catch (UnavailableSecurityManagerException e) {
			
		}catch (InvalidSessionException e){
			
		}
		return null;
	}
	
	public static Session getSession(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null){
				session = subject.getSession();
			}
			if (session != null){
				return session;
			}
//			subject.logout();
		}catch (InvalidSessionException e){
			
		}
		return null;
	}
	
	// ============== User Cache ==============
	
	@SuppressWarnings("unchecked")
	public static <T> T getCache(String key) {
		return (T) getCache(key, null);
	}
	
	public static Object getCache(String key, Object defaultValue) {
//		Object obj = getCacheMap().get(key);
		Object obj = getSession().getAttribute(key);
		return obj==null?defaultValue:obj;
	}

	public static void putCache(String key, Object value) {
//		getCacheMap().put(key, value);
		getSession().setAttribute(key, value);
	}

	public static void removeCache(String key) {
//		getCacheMap().remove(key);
		getSession().removeAttribute(key);
	}
	
//	public static Map<String, Object> getCacheMap(){
//		Principal principal = getPrincipal();
//		if(principal!=null){
//			return principal.getCacheMap();
//		}
//		return new HashMap<String, Object>();
//	}
	
}
