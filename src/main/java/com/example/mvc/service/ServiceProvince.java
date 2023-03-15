package com.example.mvc.service;

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
import com.example.common.spring.base.BaseService;
import com.example.mvc.bean.detail.DetailProvince;
import com.example.mvc.bean.rep.RepProvince;
import com.example.mvc.bean.req.ReqProvince;
import com.example.mvc.dao.DaoProvince;
import com.example.mvc.model.Province;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import com.example.mvc.cache.CacheProvince;

@Service
@Slf4j
public class ServiceProvince extends BaseService implements IService<Province> {
  private static final String tableName = "Province";
  @Resource
  private CacheProvince cacheProvince;
  @Resource
  private DaoProvince daoProvince;
  @Resource
  private DbOperator<Province> dbOperator;

  /**
   * 添加省表。空值将被忽略。
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param province 省表。
   * @return 返回添加的省表。
   */
  public Province insert(HttpServletRequest request, HttpServletResponse response, Province province) {
    if (province == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoProvince.insert(province);
    if (count <= 0) {
      throw new CommonException("insert fail!");
    }
    if (ConfigCommon.redis.cacheInsert) {
      cacheProvince.put(province);
    }
    return province;
  }

  /**
   * 添加省表。空值将被忽略。
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param province 省表。
   * @return 返回添加的省表。
   */
  public Province insert(Province province) {
    return insert(null, null, province);
  }

  /**
   * 同步批量添加省表。空值将被忽略。
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param provinces 省表。
   * @return 返回添加的省表数。
   */
  @Transactional(rollbackFor = Exception.class)
  public int insertSync(HttpServletRequest request, HttpServletResponse response, List<Province> provinces) {
    if (provinces == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int num = 0;
    for (Province province : provinces) {
      int count = daoProvince.insert(province);
      if (count <= 0) {
        throw new CommonException("insertSync fail!");
      }
      if (ConfigCommon.redis.cacheInsert) {
        cacheProvince.put(province);
      }
      num++;
    }
    return num;
  }

  /**
   * 同步批量添加省表。空值将被忽略。
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param provinces 省表。
   * @return 返回添加的省表数。
   */
  @Transactional(rollbackFor = Exception.class)
  public int insertSync(List<Province> provinces) {
    return insertSync(null, null, provinces);
  }

  /**
   * 异步批量添加省表。空值将被忽略。
   * 注意：异步模式不会添加到缓存中。
   * @param provinces 省表。
   */
  public void insertAsyn(HttpServletRequest request, HttpServletResponse response, List<Province> provinces) {
    if (provinces == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    for (Province province : provinces) {
      dbOperator.insert(province, daoProvince);
    }
  }

  /**
   * 异步批量添加省表。空值将被忽略。
   * 注意：异步模式不会添加到缓存中。
   * @param provinces 省表。
   */
  public void insertAsyn(List<Province> provinces) {
    insertAsyn(null, null, provinces);
  }

  /**
   * 更新省表。空值将被忽略。
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param province 省表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int update(HttpServletRequest request, HttpServletResponse response, Province province) {
    if (province == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoProvince.update(province);
    if (count > 0) {
      cacheProvince.update(daoProvince.selectOne(province));
    }
    return count;
  }

  /**
   * 更新省表。空值将被忽略。
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param province 省表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int update(Province province) {
    return update(null, null, province);
  }

  /**
   * 删除省表。
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param province 省表。
   * @return 返回删除的记录数。
   */
  public int delete(HttpServletRequest request, HttpServletResponse response, Province province) {
    if (province == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = cacheProvince.delete(province.getId()) ? 1 : 0;
    return count;
  }

  /**
   * 删除省表。
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param province 省表。
   * @return 返回删除的记录数。
   */
  public int delete(Province province) {
    return delete(null, null, province);
  }

  /**
   * 删除省表。
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param id 省编号。
   * @return 返回删除的记录数。
   */
  public int delete(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    Province province = new Province();
    province.setId(id);
    int count = cacheProvince.delete(id) ? 1 : 0;
    return count;
  }

  /**
   * 删除省表。
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param id 省编号。
   * @return 返回删除的记录数。
   */
  public int delete(Integer id) {
    return delete(null, null, id);
  }

  /**
   * 根据主键查询一条省表。
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * @param id 省编号。
   * @return 返回省表。
   */
  public Province selectByPrimary(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    Province province = new Province();
    province.setId(id);
    Province province1 = cacheProvince.get(id);
    return province1;
  }

  /**
   * 根据主键查询一条省表。
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * @param id 省编号。
   * @return 返回省表。
   */
  public Province selectByPrimary(Integer id) {
    return selectByPrimary(null, null, id);
  }

  /**
   * 根据条件查询一条省表详情。
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * @param province 省表。
   * @return 返回省表详情。
   */
  public DetailProvince selectDetail(HttpServletRequest request, HttpServletResponse response, Province province) {
    if (province == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    DetailProvince detailProvince = daoProvince.selectDetail(province);
    return detailProvince;
  }

  /**
   * 根据条件查询一条省表详情。
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * @param province 省表。
   * @return 返回省表详情。
   */
  public DetailProvince selectDetail(Province province) {
    return selectDetail(null, null, province);
  }

  /**
   * 根据主键查询一条省表详情。
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * @param id 省编号。
   * @return 返回省表详情。
   */
  public DetailProvince selectDetailByPrimary(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    Province province = new Province();
    province.setId(id);
    DetailProvince detailProvince = daoProvince.selectDetail(province);
    return detailProvince;
  }

  /**
   * 根据主键查询一条省表详情。
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * @param id 省编号。
   * @return 返回省表详情。
   */
  public DetailProvince selectDetailByPrimary(Integer id) {
    return selectDetailByPrimary(null, null, id);
  }

  /**
   * 查询省表列表。返回所有符合条件的省表，未分页。
   * @param province 省表。
   * @return 返回省表列表。
   */
  public List<Province> select(HttpServletRequest request, HttpServletResponse response, Province province) {
    if (province == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    List<Province> list = daoProvince.select(province);
    return list;
  }

  /**
   * 查询省表列表。返回所有符合条件的省表，未分页。
   * @param province 省表。
   * @return 返回省表列表。
   */
  public List<Province> select(Province province) {
    return select(null, null, province);
  }

  /**
   * 综合查询省表列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqProvince 查询的参数。
   * @return 返回省表列表。
   */
  public RepProvince selectRelative(HttpServletRequest request, HttpServletResponse response, ReqProvince reqProvince) {
    if (reqProvince == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    initService(request, reqProvince);
    RepProvince repProvince = new RepProvince();
    if (isQueryTotal(reqProvince)) {
      repProvince.setTotal(daoProvince.selectTotal(reqProvince));
    }
    repProvince.setRows(daoProvince.selectRelative(reqProvince));
    return repProvince;
  }

  /**
   * 综合查询省表列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqProvince 查询的参数。
   * @return 返回省表列表。
   */
  public RepProvince selectRelative(ReqProvince reqProvince) {
    return selectRelative(null, null, reqProvince);
  }

  /**
   * 根据唯一键更新一条省表，此方法不适用根据唯一键更改唯一键的字段值。
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param province 省表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateByProvinceName(HttpServletRequest request, HttpServletResponse response, Province province) {
    if (province == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(province.getProvinceName())) {
      throw new CommonException(Code.PARAM_EMPTY, "provinceName is null!");
    }
    int count = daoProvince.updateByProvinceName(province);
    if (count > 0) {
      cacheProvince.update(daoProvince.selectOne(province));
    }
    return count;
  }

  /**
   * 根据唯一键更新一条省表，此方法不适用根据唯一键更改唯一键的字段值。
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param province 省表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateByProvinceName(Province province) {
    return updateByProvinceName(null, null, province);
  }

  /**
   * 根据唯一键删除一条省表。
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param province 省表。
   * @return 返回删除的记录数。
   */
  public int deleteByProvinceName(HttpServletRequest request, HttpServletResponse response, Province province) {
    if (province == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(province.getProvinceName())) {
      throw new CommonException(Code.PARAM_EMPTY, "provinceName is null!");
    }
    Province province1 = new Province();
    province1.setProvinceName(province.getProvinceName());
    int count = cacheProvince.deleteByProvinceName(province1) ? 1 : 0;
    return count;
  }

  /**
   * 根据唯一键删除一条省表。
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param province 省表。
   * @return 返回删除的记录数。
   */
  public int deleteByProvinceName(Province province) {
    return deleteByProvinceName(null, null, province);
  }

  /**
   * 根据唯一键查询一条省表。
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * @param province 省表。
   * @return 返回省表。
   */
  public Province selectByProvinceName(HttpServletRequest request, HttpServletResponse response, Province province) {
    if (province == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(province.getProvinceName())) {
      throw new CommonException(Code.PARAM_EMPTY, "provinceName is null!");
    }
    Province province1 = new Province();
    province1.setProvinceName(province.getProvinceName());
    Province province2 = cacheProvince.getByProvinceName(province1);
    return province2;
  }

  /**
   * 根据唯一键查询一条省表。
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * @param province 省表。
   * @return 返回省表。
   */
  public Province selectByProvinceName(Province province) {
    return selectByProvinceName(null, null, province);
  }

  /**
   * 根据唯一键查询一条省表详情。
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * @param province 省表。
   * @return 返回省表。
   */
  public DetailProvince selectDetailByProvinceName(HttpServletRequest request, HttpServletResponse response, Province province) {
    if (province == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(province.getProvinceName())) {
      throw new CommonException(Code.PARAM_EMPTY, "provinceName is null!");
    }
    Province province1 = new Province();
    province1.setProvinceName(province.getProvinceName());
    DetailProvince detailProvince = daoProvince.selectDetail(province1);
    return detailProvince;
  }

  /**
   * 根据唯一键查询一条省表详情。
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * @param province 省表。
   * @return 返回省表。
   */
  public DetailProvince selectDetailByProvinceName(Province province) {
    return selectDetailByProvinceName(null, null, province);
  }

  /**
   * 导出省表到 excel。
   * @param paramExport 导出的参数。
   */
  public void export(HttpServletRequest request, HttpServletResponse response, ParamExport paramExport) {
    if (paramExport == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(paramExport.getFields())) {
      throw new CommonException(Code.PARAM_EMPTY, "fields 不能为空！");
    }
    ReqProvince reqProvince;
    String reqParam = paramExport.getReqParam();
    if (!Tool.isNull(reqParam)) {
      reqProvince = ToolJson.toBean(reqParam, ReqProvince.class);
    } else {
      reqProvince = new ReqProvince();
    }
    List<Map<String, Object>> list = daoProvince.selectRelativeMap(reqProvince);
    String title = paramExport.getTitle();
    if (Tool.isNull(title)) {
      title = "省表";
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
      case "provinceName":
        field.setLength(64);
        field.setExportFieldType(Enum.ExportFieldType.string_);
        break;
      default:
        throw new CommonException(Code.PARAM_EMPTY, "unknow fileName: " + name);

    }
  }
}