package com.example.mvc.service;

import com.example.common.spring.base.BaseService;
import com.example.mvc.bean.detail.DetailCity;
import com.example.mvc.bean.rep.RepCity;
import com.example.mvc.bean.req.ReqCity;
import com.example.mvc.cache.CacheCity;
import com.example.mvc.dao.DaoCity;
import com.example.mvc.model.City;
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
public class ServiceCity extends BaseService implements IService<City> {
  private static final String tableName = "City";
  @Resource
  private CacheCity cacheCity;
  @Resource
  private DaoCity daoCity;
  @Resource
  private DbOperator<City> dbOperator;

  /**
   * 添加市表。空值将被忽略。
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param city 市表。
   * @return 返回添加的市表。
   */
  public City insert(HttpServletRequest request, HttpServletResponse response, City city) {
    if (city == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoCity.insert(city);
    if (count <= 0) {
      throw new CommonException("insert fail!");
    }
    if (ConfigCommon.redis.cacheInsert) {
      cacheCity.put(city);
    }
    return city;
  }

  /**
   * 添加市表。空值将被忽略。
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param city 市表。
   * @return 返回添加的市表。
   */
  public City insert(City city) {
    return insert(null, null, city);
  }

  /**
   * 同步批量添加市表。空值将被忽略。
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param citys 市表。
   * @return 返回添加的市表数。
   */
  @Transactional(rollbackFor = Exception.class)
  public int insertSync(HttpServletRequest request, HttpServletResponse response, List<City> citys) {
    if (citys == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int num = 0;
    for (City city : citys) {
      int count = daoCity.insert(city);
      if (count <= 0) {
        throw new CommonException("insertSync fail!");
      }
      if (ConfigCommon.redis.cacheInsert) {
        cacheCity.put(city);
      }
      num++;
    }
    return num;
  }

  /**
   * 同步批量添加市表。空值将被忽略。
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param citys 市表。
   * @return 返回添加的市表数。
   */
  @Transactional(rollbackFor = Exception.class)
  public int insertSync(List<City> citys) {
    return insertSync(null, null, citys);
  }

  /**
   * 异步批量添加市表。空值将被忽略。
   * 注意：异步模式不会添加到缓存中。
   * @param citys 市表。
   */
  public void insertAsyn(HttpServletRequest request, HttpServletResponse response, List<City> citys) {
    if (citys == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    for (City city : citys) {
      dbOperator.insert(city, daoCity);
    }
  }

  /**
   * 异步批量添加市表。空值将被忽略。
   * 注意：异步模式不会添加到缓存中。
   * @param citys 市表。
   */
  public void insertAsyn(List<City> citys) {
    insertAsyn(null, null, citys);
  }

  /**
   * 更新市表。空值将被忽略。
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param city 市表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int update(HttpServletRequest request, HttpServletResponse response, City city) {
    if (city == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoCity.update(city);
    if (count > 0) {
      cacheCity.update(daoCity.selectOne(city));
    }
    return count;
  }

  /**
   * 更新市表。空值将被忽略。
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param city 市表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int update(City city) {
    return update(null, null, city);
  }

  /**
   * 删除市表。
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param city 市表。
   * @return 返回删除的记录数。
   */
  public int delete(HttpServletRequest request, HttpServletResponse response, City city) {
    if (city == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = cacheCity.delete(city.getId()) ? 1 : 0;
    return count;
  }

  /**
   * 删除市表。
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param city 市表。
   * @return 返回删除的记录数。
   */
  public int delete(City city) {
    return delete(null, null, city);
  }

  /**
   * 删除市表。
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param id 市编号。
   * @return 返回删除的记录数。
   */
  public int delete(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    City city = new City();
    city.setId(id);
    int count = cacheCity.delete(id) ? 1 : 0;
    return count;
  }

  /**
   * 删除市表。
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param id 市编号。
   * @return 返回删除的记录数。
   */
  public int delete(Integer id) {
    return delete(null, null, id);
  }

  /**
   * 根据主键查询一条市表。
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * @param id 市编号。
   * @return 返回市表。
   */
  public City selectByPrimary(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    City city = cacheCity.get(id);
    return city;
  }

  /**
   * 根据主键查询一条市表。
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * @param id 市编号。
   * @return 返回市表。
   */
  public City selectByPrimary(Integer id) {
    return selectByPrimary(null, null, id);
  }

  /**
   * 根据条件查询一条市表。
   * @param city 市表。
   * @return 返回市表。
   */
  public City selectOne(HttpServletRequest request, HttpServletResponse response, City city) {
    if (city == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    City city1 = daoCity.selectOne(city);
    return city1;
  }

  /**
   * 根据条件查询一条市表详情。
   * @param city 市表。
   * @return 返回市表详情。
   */
  public DetailCity selectDetail(HttpServletRequest request, HttpServletResponse response, City city) {
    if (city == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    DetailCity detailCity = daoCity.selectDetail(city);
    return detailCity;
  }

  /**
   * 根据条件查询一条市表详情。
   * @param city 市表。
   * @return 返回市表详情。
   */
  public DetailCity selectDetail(City city) {
    return selectDetail(null, null, city);
  }

  /**
   * 根据主键查询一条市表详情。
   * @param id 市编号。
   * @return 返回市表详情。
   */
  public DetailCity selectDetailByPrimary(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    DetailCity detailCity = daoCity.selectDetailByPrimary(id);
    return detailCity;
  }

  /**
   * 根据主键查询一条市表详情。
   * @param id 市编号。
   * @return 返回市表详情。
   */
  public DetailCity selectDetailByPrimary(Integer id) {
    return selectDetailByPrimary(null, null, id);
  }

  /**
   * 查询市表列表。返回所有符合条件的市表，未分页。
   * @param city 市表。
   * @return 返回市表列表。
   */
  public List<City> select(HttpServletRequest request, HttpServletResponse response, City city) {
    if (city == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    List<City> list = daoCity.select(city);
    return list;
  }

  /**
   * 查询市表列表。返回所有符合条件的市表，未分页。
   * @param city 市表。
   * @return 返回市表列表。
   */
  public List<City> select(City city) {
    return select(null, null, city);
  }

  /**
   * 综合查询市表列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqCity 查询的参数。
   * @return 返回市表列表。
   */
  public RepCity selectRelative(HttpServletRequest request, HttpServletResponse response, ReqCity reqCity) {
    if (reqCity == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    initService(request, reqCity);
    RepCity repCity = new RepCity();
    if (isQueryTotal(reqCity)) {
      repCity.setTotal(daoCity.selectTotal(reqCity));
    }
    repCity.setRows(daoCity.selectRelative(reqCity));
    return repCity;
  }

  /**
   * 综合查询市表列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqCity 查询的参数。
   * @return 返回市表列表。
   */
  public RepCity selectRelative(ReqCity reqCity) {
    return selectRelative(null, null, reqCity);
  }

  /**
   * 根据唯一键更新一条市表，此方法不适用根据唯一键更改唯一键的字段值。
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param city 市表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateByCityName(HttpServletRequest request, HttpServletResponse response, City city) {
    if (city == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(city.getProvinceId())) {
      throw new CommonException(Code.PARAM_EMPTY, "provinceId is null!");
    }
    if (Tool.isNull(city.getCityName())) {
      throw new CommonException(Code.PARAM_EMPTY, "cityName is null!");
    }
    int count = daoCity.updateByCityName(city);
    if (count > 0) {
      cacheCity.update(daoCity.selectOne(city));
    }
    return count;
  }

  /**
   * 根据唯一键更新一条市表，此方法不适用根据唯一键更改唯一键的字段值。
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param city 市表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateByCityName(City city) {
    return updateByCityName(null, null, city);
  }

  /**
   * 根据唯一键删除一条市表。
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param city 市表。
   * @return 返回删除的记录数。
   */
  public int deleteByCityName(HttpServletRequest request, HttpServletResponse response, City city) {
    if (city == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(city.getProvinceId())) {
      throw new CommonException(Code.PARAM_EMPTY, "provinceId is null!");
    }
    if (Tool.isNull(city.getCityName())) {
      throw new CommonException(Code.PARAM_EMPTY, "cityName is null!");
    }
    City city1 = new City();
    city1.setProvinceId(city.getProvinceId());
    city1.setCityName(city.getCityName());
    int count = cacheCity.deleteByCityName(city1) ? 1 : 0;
    return count;
  }

  /**
   * 根据唯一键删除一条市表。
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param city 市表。
   * @return 返回删除的记录数。
   */
  public int deleteByCityName(City city) {
    return deleteByCityName(null, null, city);
  }

  /**
   * 根据唯一键查询一条市表。
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * @param city 市表。
   * @return 返回市表。
   */
  public City selectByCityName(HttpServletRequest request, HttpServletResponse response, City city) {
    if (city == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(city.getProvinceId())) {
      throw new CommonException(Code.PARAM_EMPTY, "provinceId is null!");
    }
    if (Tool.isNull(city.getCityName())) {
      throw new CommonException(Code.PARAM_EMPTY, "cityName is null!");
    }
    City city0 = cacheCity.getByCityName(city);
    return city0;
  }

  /**
   * 根据唯一键查询一条市表。
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * @param city 市表。
   * @return 返回市表。
   */
  public City selectByCityName(City city) {
    return selectByCityName(null, null, city);
  }

  /**
   * 根据唯一键查询一条市表详情。
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * @param city 市表。
   * @return 返回市表。
   */
  public DetailCity selectDetailByCityName(HttpServletRequest request, HttpServletResponse response, City city) {
    if (city == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(city.getProvinceId())) {
      throw new CommonException(Code.PARAM_EMPTY, "provinceId is null!");
    }
    if (Tool.isNull(city.getCityName())) {
      throw new CommonException(Code.PARAM_EMPTY, "cityName is null!");
    }
    DetailCity detailCity = daoCity.selectDetailByCityName(city);
    return detailCity;
  }

  /**
   * 根据唯一键查询一条市表详情。
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * @param city 市表。
   * @return 返回市表。
   */
  public DetailCity selectDetailByCityName(City city) {
    return selectDetailByCityName(null, null, city);
  }

  /**
   * 导出市表到 excel。
   * @param paramExport 导出的参数。
   */
  public void export(HttpServletRequest request, HttpServletResponse response, ParamExport paramExport) {
    if (paramExport == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(paramExport.getFields())) {
      throw new CommonException(Code.PARAM_EMPTY, "fields 不能为空！");
    }
    ReqCity reqCity;
    String reqParam = paramExport.getReqParam();
    if (!Tool.isNull(reqParam)) {
      reqCity = ToolJson.toBean(reqParam, ReqCity.class);
    } else {
      reqCity = new ReqCity();
    }
    List<Map<String, Object>> list = daoCity.selectRelativeMap(reqCity);
    String title = paramExport.getTitle();
    if (Tool.isNull(title)) {
      title = "市表";
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
      case "provinceId":
        field.setExportFieldType(Enum.ExportFieldType.integer_);
        break;
      case "provinceName":
        field.setLength(64);
        field.setExportFieldType(Enum.ExportFieldType.string_);
        break;
      case "cityName":
        field.setLength(64);
        field.setExportFieldType(Enum.ExportFieldType.string_);
        break;
      default:
        throw new CommonException(Code.PARAM_EMPTY, "unknow fileName: " + name);

    }
  }
}