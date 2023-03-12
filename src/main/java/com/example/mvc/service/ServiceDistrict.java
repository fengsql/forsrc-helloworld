package com.example.mvc.service;

import com.example.common.spring.base.BaseService;
import com.example.mvc.bean.detail.DetailDistrict;
import com.example.mvc.bean.rep.RepDistrict;
import com.example.mvc.bean.req.ReqDistrict;
import com.example.mvc.cache.CacheDistrict;
import com.example.mvc.dao.DaoDistrict;
import com.example.mvc.model.District;
import com.forsrc.common.constant.Code;
import com.forsrc.common.constant.ConfigCommon;
import com.forsrc.common.constant.Enum;
import com.forsrc.common.exception.CommonException;
import com.forsrc.common.extend.bean.Field;
import com.forsrc.common.extend.bean.ParamExport;
import com.forsrc.common.extend.tool.ToolExport;
import com.forsrc.common.spring.base.IService;
import com.forsrc.common.spring.db.DbOperator;
import com.forsrc.common.tool.Tool;
import com.forsrc.common.tool.ToolJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ServiceDistrict extends BaseService implements IService<District> {
  private static final String tableName = "District";
  @Resource
  private CacheDistrict cacheDistrict;
  @Resource
  private DaoDistrict daoDistrict;
  @Resource
  private DbOperator<District> dbOperator;

  /**
   * 添加县表。空值将被忽略。
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
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
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param district 县表。
   * @return 返回添加的县表。
   */
  public District insert(District district) {
    return insert(null, null, district);
  }

  /**
   * 同步批量添加县表。空值将被忽略。
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
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
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param districts 县表。
   * @return 返回添加的县表数。
   */
  @Transactional(rollbackFor = Exception.class)
  public int insertSync(List<District> districts) {
    return insertSync(null, null, districts);
  }

  /**
   * 异步批量添加县表。空值将被忽略。
   * 注意：异步模式不会添加到缓存中。
   * @param districts 县表。
   */
  public void insertAsyn(HttpServletRequest request, HttpServletResponse response, List<District> districts) {
    if (districts == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    for (District district : districts) {
      dbOperator.insert(district, daoDistrict);
    }
  }

  /**
   * 异步批量添加县表。空值将被忽略。
   * 注意：异步模式不会添加到缓存中。
   * @param districts 县表。
   */
  public void insertAsyn(List<District> districts) {
    insertAsyn(null, null, districts);
  }


  /**
   * 更新县表。空值将被忽略。
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
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
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param district 县表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int update(District district) {
    return update(null, null, district);
  }

  /**
   * 删除县表。
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param district 县表。
   * @return 返回删除的记录数。
   */
  public int delete(HttpServletRequest request, HttpServletResponse response, District district) {
    if (district == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = cacheDistrict.delete(district.getId()) ? 1 : 0;
    return count;
  }

  /**
   * 删除县表。
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param district 县表。
   * @return 返回删除的记录数。
   */
  public int delete(District district) {
    return delete(null, null, district);
  }

  /**
   * 删除县表。
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param id 县编号。
   * @return 返回删除的记录数。
   */
  public int delete(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    District district = new District();
    district.setId(id);
    int count = cacheDistrict.delete(id) ? 1 : 0;
    return count;
  }

  /**
   * 删除县表。
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param id 县编号。
   * @return 返回删除的记录数。
   */
  public int delete(Integer id) {
    return delete(null, null, id);
  }

  /**
   * 根据主键查询一条县表。
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * @param id 县编号。
   * @return 返回县表。
   */
  public District selectByPrimary(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    District district = new District();
    district.setId(id);
    District district1 = cacheDistrict.get(id);
    return district1;
  }

  /**
   * 根据主键查询一条县表。
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * @param id 县编号。
   * @return 返回县表。
   */
  public District selectByPrimary(Integer id) {
    return selectByPrimary(null, null, id);
  }

  /**
   * 根据条件查询一条县表详情。
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
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
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * @param district 县表。
   * @return 返回县表详情。
   */
  public DetailDistrict selectDetail(District district) {
    return selectDetail(null, null, district);
  }

  /**
   * 根据主键查询一条县表详情。
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * @param id 县编号。
   * @return 返回县表详情。
   */
  public DetailDistrict selectDetailByPrimary(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    District district = new District();
    district.setId(id);
    DetailDistrict detailDistrict = daoDistrict.selectDetail(district);
    return detailDistrict;
  }

  /**
   * 根据主键查询一条县表详情。
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * @param id 县编号。
   * @return 返回县表详情。
   */
  public DetailDistrict selectDetailByPrimary(Integer id) {
    return selectDetailByPrimary(null, null, id);
  }


  /**
   * 根据唯一键查询一条县表。
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * @param cityId 市编号。
   * @param districtName 县名称。
   * @return 返回县表。
   */
  public District selectByDistrictName(HttpServletRequest request, HttpServletResponse response, Integer cityId, String districtName) {
    boolean _hasParam = false;
    if (!Tool.isNull(cityId)) {
      _hasParam = true;
    }
    if (!Tool.isNull(districtName)) {
      _hasParam = true;
    }
    if (!_hasParam) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    District district = new District();
    district.setCityId(cityId);
    district.setDistrictName(districtName);
    District district1 = cacheDistrict.getByDistrictName(cityId, districtName);
    return district1;
  }

  /**
   * 根据唯一键查询一条县表。
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * @param cityId 市编号。
   * @param districtName 县名称。
   * @return 返回县表。
   */
  public District selectByDistrictName(Integer cityId, String districtName) {
    return selectByDistrictName(null, null, cityId, districtName);
  }

  /**
   * 根据唯一键查询一条县表详情。
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * @param cityId 市编号。
   * @param districtName 县名称。
   * @return 返回县表。
   */
  public DetailDistrict selectDetailByDistrictName(HttpServletRequest request, HttpServletResponse response, Integer cityId, String districtName) {
    boolean _hasParam = false;
    if (!Tool.isNull(cityId)) {
      _hasParam = true;
    }
    if (!Tool.isNull(districtName)) {
      _hasParam = true;
    }
    if (!_hasParam) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    District district = new District();
    district.setCityId(cityId);
    district.setDistrictName(districtName);
    DetailDistrict detailDistrict = daoDistrict.selectDetail(district);
    return detailDistrict;
  }

  /**
   * 根据唯一键查询一条县表详情。
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * @param cityId 市编号。
   * @param districtName 县名称。
   * @return 返回县表。
   */
  public DetailDistrict selectDetailByDistrictName(Integer cityId, String districtName) {
    return selectDetailByDistrictName(null, null, cityId, districtName);
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