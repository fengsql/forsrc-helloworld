package com.example.mvc.service;

import com.forsrc.common.constant.Code;
import com.forsrc.common.constant.ConfigCommon;
import com.forsrc.common.constant.Enum;
import com.forsrc.common.db.batch.DbBatch;
import com.forsrc.common.exception.CommonException;
import com.forsrc.common.extend.bean.Field;
import com.forsrc.common.extend.bean.ParamExport;
import com.forsrc.common.extend.tool.ToolExport;
import com.forsrc.common.spring.base.IService;
import com.forsrc.common.tool.Tool;
import com.forsrc.common.tool.ToolJson;
import com.example.common.spring.base.BaseService;
import com.example.mvc.bean.detail.DetailDistrict;
import com.example.mvc.bean.rep.RepDistrict;
import com.example.mvc.bean.req.ReqDistrict;
import com.example.mvc.dao.DaoDistrict;
import com.example.mvc.model.District;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.example.mvc.cache.CacheDistrict;

@Service
@Slf4j
public class ServiceDistrict extends BaseService implements IService<District> {
  private static final String tableName = "District";

  @Resource
  private CacheDistrict cacheDistrict;
  @Resource
  private DaoDistrict daoDistrict;
  @Resource
  private DbBatch<District> dbBatch;

  /**
   * 添加县表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param district 县表。
   * @return 返回添加的县表。
   */
  public District insert(HttpServletRequest request, HttpServletResponse response, District district) {
    if (district == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoDistrict.insert(district);
    if (count <= 0) {
      throw new CommonException("insert fail!");
    }
    if (ConfigCommon.redis.cacheInsert) {
      cacheDistrict.put(district);
    }
    return district;
  }

  /**
   * 添加县表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param district 县表。
   * @return 返回添加的县表。
   */
  public District insert(District district) {
    return insert(null, null, district);
  }

  /**
   * 同步批量添加县表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param districts 县表。
   * @return 返回添加的县表数。
   */
  @Transactional(rollbackFor = Exception.class)
  public int insertSync(HttpServletRequest request, HttpServletResponse response, List<District> districts) {
    if (districts == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int num = 0;
    for (District district : districts) {
      int count = daoDistrict.insert(district);
      if (count <= 0) {
        throw new CommonException("insertSync fail!");
      }
      if (ConfigCommon.redis.cacheInsert) {
        cacheDistrict.put(district);
      }
      num++;
    }
    return num;
  }

  /**
   * 同步批量添加县表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param districts 县表。
   * @return 返回添加的县表数。
   */
  @Transactional(rollbackFor = Exception.class)
  public int insertSync(List<District> districts) {
    return insertSync(null, null, districts);
  }

  /**
   * 异步批量添加县表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 注意：异步模式不会添加到缓存中。
   * //#  }
   * @param districts 县表。
   */
  public void insertAsyn(HttpServletRequest request, HttpServletResponse response, List<District> districts) {
    if (districts == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    for (District district : districts) {
      dbBatch.insert(district, daoDistrict);
    }
  }

  /**
   * 异步批量添加县表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 注意：异步模式不会添加到缓存中。
   * //#  }
   * @param districts 县表。
   */
  public void insertAsyn(List<District> districts) {
    insertAsyn(null, null, districts);
  }

  /**
   * 更新县表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param district 县表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int update(HttpServletRequest request, HttpServletResponse response, District district) {
    if (district == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoDistrict.update(district);
    if (count > 0) {
      cacheDistrict.update(daoDistrict.selectOne(district));
    }
    return count;
  }

  /**
   * 更新县表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param district 县表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int update(District district) {
    return update(null, null, district);
  }

  /**
   * 更新县表。空值将被更新为 null。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param district 县表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateEvenNull(HttpServletRequest request, HttpServletResponse response, District district) {
    if (district == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoDistrict.updateEvenNull(district);
    if (count > 0) {
      cacheDistrict.update(daoDistrict.selectOne(district));
    }
    return count;
  }

  /**
   * 更新县表。空值将被更新为空。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param district 县表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateEvenNull(District district) {
    return updateEvenNull(null, null, district);
  }

  /**
   * 删除县表。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param district 县表。仅可传入主键、外键、常量字段作为条件。
   * @return 返回删除的记录数。
   */
  public int delete(HttpServletRequest request, HttpServletResponse response, District district) {
    if (district == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    List<District> districts_ = daoDistrict.select(district);
    if (Tool.isNull(districts_)) {
      return 0;
    }
    for (District district1 : districts_) {
      cacheDistrict.delete(district1.getId());
    }
    return districts_.size();
  }

  /**
   * 删除县表。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param district 县表。
   * @return 返回删除的记录数。
   */
  public int delete(District district) {
    return delete(null, null, district);
  }

  /**
   * 删除县表。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param id 县编号。
   * @return 返回删除的记录数。
   */
  public int delete(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    District district = new District();
    district.setId(id);
    District district1 = cacheDistrict.get(id);
    if (district1 == null) {
      return 0;
    }
    List<District> districts_ = new ArrayList<>();
    districts_.add(district1);
    cacheDistrict.delete(id);
    return 1;
  }

  /**
   * 删除县表。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param id 县编号。
   * @return 返回删除的记录数。
   */
  public int delete(Integer id) {
    return delete(null, null, id);
  }

  /**
   * 根据主键查询一条县表。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param id 县编号。
   * @return 返回县表。
   */
  public District selectByPrimary(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    District district = cacheDistrict.get(id);
    return district;
  }

  /**
   * 根据主键查询一条县表。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param id 县编号。
   * @return 返回县表。
   */
  public District selectByPrimary(Integer id) {
    return selectByPrimary(null, null, id);
  }

  /**
   * 根据条件查询一条县表。
   * @param district 县表。
   * @return 返回县表。
   */
  public District selectOne(HttpServletRequest request, HttpServletResponse response, District district) {
    if (district == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    District district1 = daoDistrict.selectOne(district);
    return district1;
  }

  /**
   * 根据条件查询一条县表。
   * @param district 县表。
   * @return 返回县表。
   */
  public District selectOne(District district) {
    return selectOne(null, null, district);
  }

  /**
   * 根据条件查询一条县表详情。
   * @param district 县表。
   * @return 返回县表详情。
   */
  public DetailDistrict selectDetail(HttpServletRequest request, HttpServletResponse response, District district) {
    if (district == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    DetailDistrict detailDistrict = daoDistrict.selectDetail(district);
    return detailDistrict;
  }

  /**
   * 根据条件查询一条县表详情。
   * @param district 县表。
   * @return 返回县表详情。
   */
  public DetailDistrict selectDetail(District district) {
    return selectDetail(null, null, district);
  }

  /**
   * 根据主键查询一条县表详情。
   * @param id 县编号。
   * @return 返回县表详情。
   */
  public DetailDistrict selectDetailByPrimary(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    DetailDistrict detailDistrict = daoDistrict.selectDetailByPrimary(id);
    return detailDistrict;
  }

  /**
   * 根据主键查询一条县表详情。
   * @param id 县编号。
   * @return 返回县表详情。
   */
  public DetailDistrict selectDetailByPrimary(Integer id) {
    return selectDetailByPrimary(null, null, id);
  }

  /**
   * 查询县表列表。返回所有符合条件的县表，未分页。
   * @param district 县表。
   * @return 返回县表列表。
   */
  public List<District> select(HttpServletRequest request, HttpServletResponse response, District district) {
    if (district == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    List<District> list = daoDistrict.select(district);
    return list;
  }

  /**
   * 查询县表列表。返回所有符合条件的县表，未分页。
   * @param district 县表。
   * @return 返回县表列表。
   */
  public List<District> select(District district) {
    return select(null, null, district);
  }

  /**
   * 综合查询县表列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqDistrict 查询的参数。
   * @return 返回县表列表。
   */
  public RepDistrict selectRelative(HttpServletRequest request, HttpServletResponse response, ReqDistrict reqDistrict) {
    if (reqDistrict == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    initService(request, reqDistrict);
    RepDistrict repDistrict = new RepDistrict();
    if (isQueryTotal(reqDistrict)) {
      repDistrict.setTotal(daoDistrict.selectTotal(reqDistrict));
    }
    repDistrict.setRows(daoDistrict.selectRelative(reqDistrict));
    return repDistrict;
  }

  /**
   * 综合查询县表列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqDistrict 查询的参数。
   * @return 返回县表列表。
   */
  public RepDistrict selectRelative(ReqDistrict reqDistrict) {
    return selectRelative(null, null, reqDistrict);
  }

  /**
   * 根据唯一键更新一条县表，此方法不适用根据唯一键更改唯一键的字段值。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param district 县表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateByDistrictName(HttpServletRequest request, HttpServletResponse response, District district) {
    if (district == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(district.getCityId())) {
      throw new CommonException(Code.PARAM_EMPTY, "cityId is null!");
    }
    if (Tool.isNull(district.getDistrictName())) {
      throw new CommonException(Code.PARAM_EMPTY, "districtName is null!");
    }
    int count = daoDistrict.updateByDistrictName(district);
    if (count > 0) {
      cacheDistrict.update(daoDistrict.selectOne(district));
    }
    return count;
  }

  /**
   * 根据唯一键更新一条县表，此方法不适用根据唯一键更改唯一键的字段值。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param district 县表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateByDistrictName(District district) {
    return updateByDistrictName(null, null, district);
  }

  /**
   * 根据唯一键删除一条县表。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param district 县表。
   * @return 返回删除的记录数。
   */
  public int deleteByDistrictName(HttpServletRequest request, HttpServletResponse response, District district) {
    if (district == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(district.getCityId())) {
      throw new CommonException(Code.PARAM_EMPTY, "cityId is null!");
    }
    if (Tool.isNull(district.getDistrictName())) {
      throw new CommonException(Code.PARAM_EMPTY, "districtName is null!");
    }
    District district1 = new District();
    district1.setCityId(district.getCityId());
    district1.setDistrictName(district.getDistrictName());
    District district2 = cacheDistrict.getByDistrictName(district1);
    if (district2 == null) {
      return 0;
    }
    List<District> districts_ = new ArrayList<>();
    districts_.add(district2);
    cacheDistrict.deleteByDistrictName(district2);
    return 1;
  }

  /**
   * 根据唯一键删除一条县表。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param district 县表。
   * @return 返回删除的记录数。
   */
  public int deleteByDistrictName(District district) {
    return deleteByDistrictName(null, null, district);
  }

  /**
   * 根据唯一键查询一条县表。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param district 县表。
   * @return 返回县表。
   */
  public District selectByDistrictName(HttpServletRequest request, HttpServletResponse response, District district) {
    if (district == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(district.getCityId())) {
      throw new CommonException(Code.PARAM_EMPTY, "cityId is null!");
    }
    if (Tool.isNull(district.getDistrictName())) {
      throw new CommonException(Code.PARAM_EMPTY, "districtName is null!");
    }
    District district0 = cacheDistrict.getByDistrictName(district);
    return district0;
  }

  /**
   * 根据唯一键查询一条县表。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param district 县表。
   * @return 返回县表。
   */
  public District selectByDistrictName(District district) {
    return selectByDistrictName(null, null, district);
  }

  /**
   * 根据唯一键查询一条县表详情。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param district 县表。
   * @return 返回县表。
   */
  public DetailDistrict selectDetailByDistrictName(HttpServletRequest request, HttpServletResponse response, District district) {
    if (district == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(district.getCityId())) {
      throw new CommonException(Code.PARAM_EMPTY, "cityId is null!");
    }
    if (Tool.isNull(district.getDistrictName())) {
      throw new CommonException(Code.PARAM_EMPTY, "districtName is null!");
    }
    DetailDistrict detailDistrict = daoDistrict.selectDetailByDistrictName(district);
    return detailDistrict;
  }

  /**
   * 根据唯一键查询一条县表详情。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param district 县表。
   * @return 返回县表。
   */
  public DetailDistrict selectDetailByDistrictName(District district) {
    return selectDetailByDistrictName(null, null, district);
  }

  /**
   * 导出县表到 excel。
   * @param paramExport 导出的参数。
   */
  public void export(HttpServletRequest request, HttpServletResponse response, ParamExport paramExport) {
    if (paramExport == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(paramExport.getFields())) {
      throw new CommonException(Code.PARAM_EMPTY, "fields 不能为空！");
    }
    ReqDistrict reqDistrict;
    String reqParam = paramExport.getReqParam();
    if (!Tool.isNull(reqParam)) {
      reqDistrict = ToolJson.toBean(reqParam, ReqDistrict.class);
    } else {
      reqDistrict = new ReqDistrict();
    }
    List<Map<String, Object>> list = daoDistrict.selectRelativeMap(reqDistrict);
    String title = paramExport.getTitle();
    if (Tool.isNull(title)) {
      title = "县表";
    }
    List<Field> fields = getFields(paramExport.getFields());
    ToolExport.export(response, tableName, title, fields, list);
  }

  private List<Field> getFields(List<Field> fields) {
    for (Field field : fields) {
      setExportFieldType(field);
    }
    return fields;
  }

  private void setExportFieldType(Field field) {
    if (field.getExportFieldType() != null) {
      return;
    }
    String name = field.getName();
    switch (name) {
      case "id":
        field.setExportFieldType(Enum.ExportFieldType.integer_);
        break;
      case "cityId":
        field.setExportFieldType(Enum.ExportFieldType.integer_);
        break;
      case "cityName":
        field.setLength(64);
        field.setExportFieldType(Enum.ExportFieldType.string_);
        break;
      case "districtName":
        field.setLength(64);
        field.setExportFieldType(Enum.ExportFieldType.string_);
        break;
      default:
        throw new CommonException(Code.PARAM_EMPTY, "unknow fileName: " + name);

    }
  }
}