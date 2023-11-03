package com.example.mvc.service;

import com.example.common.spring.base.BaseService;
import com.example.mvc.bean.detail.DetailStatTest;
import com.example.mvc.bean.rep.RepStatTest;
import com.example.mvc.bean.req.ReqStatTest;
import com.example.mvc.dao.DaoStatTest;
import com.example.mvc.model.StatTest;
import com.forsrc.common.constant.Code;
import com.forsrc.common.constant.Enum;
import com.forsrc.common.db.batch.DbBatch;
import com.forsrc.common.exception.CommonException;
import com.forsrc.common.extend.bean.Field;
import com.forsrc.common.extend.bean.ParamExport;
import com.forsrc.common.extend.tool.ToolExport;
import com.forsrc.common.spring.base.IService;
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
public class ServiceStatTest extends BaseService implements IService<StatTest> {
  private static final String tableName = "StatTest";
  @Resource
  private DaoStatTest daoStatTest;
  @Resource
  private DbBatch<StatTest> dbBatch;

  /**
   * 添加统计信息。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param statTest 统计信息。
   * @return 返回添加的统计信息。
   */
  public StatTest insert(HttpServletRequest request, HttpServletResponse response, StatTest statTest) {
    if (statTest == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoStatTest.insert(statTest);
    if (count <= 0) {
      throw new CommonException("insert fail!");
    }
    return statTest;
  }

  /**
   * 添加统计信息。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param statTest 统计信息。
   * @return 返回添加的统计信息。
   */
  public StatTest insert(StatTest statTest) {
    return insert(null, null, statTest);
  }

  /**
   * 同步批量添加统计信息。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param statTests 统计信息。
   * @return 返回添加的统计信息数。
   */
  @Transactional(rollbackFor = Exception.class)
  public int insertSync(HttpServletRequest request, HttpServletResponse response, List<StatTest> statTests) {
    if (statTests == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int num = 0;
    for (StatTest statTest : statTests) {
      int count = daoStatTest.insert(statTest);
      if (count <= 0) {
        throw new CommonException("insertSync fail!");
      }
      num++;
    }
    return num;
  }

  /**
   * 同步批量添加统计信息。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param statTests 统计信息。
   * @return 返回添加的统计信息数。
   */
  @Transactional(rollbackFor = Exception.class)
  public int insertSync(List<StatTest> statTests) {
    return insertSync(null, null, statTests);
  }

  /**
   * 异步批量添加统计信息。空值将被忽略。
   * //#  if (isCache(table)) {
   * 注意：异步模式不会添加到缓存中。
   * //#  }
   * @param statTests 统计信息。
   */
  public void insertAsyn(HttpServletRequest request, HttpServletResponse response, List<StatTest> statTests) {
    if (statTests == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    for (StatTest statTest : statTests) {
      dbBatch.insert(statTest, daoStatTest);
    }
  }

  /**
   * 异步批量添加统计信息。空值将被忽略。
   * //#  if (isCache(table)) {
   * 注意：异步模式不会添加到缓存中。
   * //#  }
   * @param statTests 统计信息。
   */
  public void insertAsyn(List<StatTest> statTests) {
    insertAsyn(null, null, statTests);
  }

  /**
   * 更新统计信息。空值将被忽略。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param statTest 统计信息。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int update(HttpServletRequest request, HttpServletResponse response, StatTest statTest) {
    if (statTest == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoStatTest.update(statTest);
    return count;
  }

  /**
   * 更新统计信息。空值将被忽略。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param statTest 统计信息。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int update(StatTest statTest) {
    return update(null, null, statTest);
  }

  /**
   * 更新统计信息。空值将被更新为 null。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param statTest 统计信息。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateEvenNull(HttpServletRequest request, HttpServletResponse response, StatTest statTest) {
    if (statTest == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoStatTest.updateEvenNull(statTest);
    return count;
  }

  /**
   * 更新统计信息。空值将被更新为空。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param statTest 统计信息。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateEvenNull(StatTest statTest) {
    return updateEvenNull(null, null, statTest);
  }

  /**
   * 删除统计信息。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param statTest 统计信息。仅可传入主键、外键、常量字段作为条件。
   * @return 返回删除的记录数。
   */
  public int delete(HttpServletRequest request, HttpServletResponse response, StatTest statTest) {
    if (statTest == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoStatTest.delete(statTest);
    return count;
  }

  /**
   * 删除统计信息。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param statTest 统计信息。
   * @return 返回删除的记录数。
   */
  public int delete(StatTest statTest) {
    return delete(null, null, statTest);
  }

  /**
   * 删除统计信息。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param id 编号。
   * @return 返回删除的记录数。
   */
  public int delete(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    StatTest statTest = new StatTest();
    statTest.setId(id);
    int count = daoStatTest.delete(statTest);
    return count;
  }

  /**
   * 删除统计信息。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param id 编号。
   * @return 返回删除的记录数。
   */
  public int delete(Integer id) {
    return delete(null, null, id);
  }

  /**
   * 根据主键查询一条统计信息。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param id 编号。
   * @return 返回统计信息。
   */
  public StatTest selectByPrimary(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    StatTest statTest = daoStatTest.selectByPrimary(id);
    return statTest;
  }

  /**
   * 根据主键查询一条统计信息。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param id 编号。
   * @return 返回统计信息。
   */
  public StatTest selectByPrimary(Integer id) {
    return selectByPrimary(null, null, id);
  }

  /**
   * 根据条件查询一条统计信息。
   * @param statTest 统计信息。
   * @return 返回统计信息。
   */
  public StatTest selectOne(HttpServletRequest request, HttpServletResponse response, StatTest statTest) {
    if (statTest == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    StatTest statTest1 = daoStatTest.selectOne(statTest);
    return statTest1;
  }

  /**
   * 根据条件查询一条统计信息。
   * @param statTest 统计信息。
   * @return 返回统计信息。
   */
  public StatTest selectOne(StatTest statTest) {
    return selectOne(null, null, statTest);
  }

  /**
   * 根据条件查询一条统计信息详情。
   * @param statTest 统计信息。
   * @return 返回统计信息详情。
   */
  public DetailStatTest selectDetail(HttpServletRequest request, HttpServletResponse response, StatTest statTest) {
    if (statTest == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    DetailStatTest detailStatTest = daoStatTest.selectDetail(statTest);
    return detailStatTest;
  }

  /**
   * 根据条件查询一条统计信息详情。
   * @param statTest 统计信息。
   * @return 返回统计信息详情。
   */
  public DetailStatTest selectDetail(StatTest statTest) {
    return selectDetail(null, null, statTest);
  }

  /**
   * 根据主键查询一条统计信息详情。
   * @param id 编号。
   * @return 返回统计信息详情。
   */
  public DetailStatTest selectDetailByPrimary(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    DetailStatTest detailStatTest = daoStatTest.selectDetailByPrimary(id);
    return detailStatTest;
  }

  /**
   * 根据主键查询一条统计信息详情。
   * @param id 编号。
   * @return 返回统计信息详情。
   */
  public DetailStatTest selectDetailByPrimary(Integer id) {
    return selectDetailByPrimary(null, null, id);
  }

  /**
   * 查询统计信息列表。返回所有符合条件的统计信息，未分页。
   * @param statTest 统计信息。
   * @return 返回统计信息列表。
   */
  public List<StatTest> select(HttpServletRequest request, HttpServletResponse response, StatTest statTest) {
    if (statTest == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    List<StatTest> list = daoStatTest.select(statTest);
    return list;
  }

  /**
   * 查询统计信息列表。返回所有符合条件的统计信息，未分页。
   * @param statTest 统计信息。
   * @return 返回统计信息列表。
   */
  public List<StatTest> select(StatTest statTest) {
    return select(null, null, statTest);
  }

  /**
   * 综合查询统计信息列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqStatTest 查询的参数。
   * @return 返回统计信息列表。
   */
  public RepStatTest selectRelative(HttpServletRequest request, HttpServletResponse response, ReqStatTest reqStatTest) {
    if (reqStatTest == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    initService(request, reqStatTest);
    RepStatTest repStatTest = new RepStatTest();
    if (isQueryTotal(reqStatTest)) {
      repStatTest.setTotal(daoStatTest.selectTotal(reqStatTest));
    }
    repStatTest.setRows(daoStatTest.selectRelative(reqStatTest));
    return repStatTest;
  }

  /**
   * 综合查询统计信息列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqStatTest 查询的参数。
   * @return 返回统计信息列表。
   */
  public RepStatTest selectRelative(ReqStatTest reqStatTest) {
    return selectRelative(null, null, reqStatTest);
  }

  /**
   * 导出统计信息到 excel。
   * @param paramExport 导出的参数。
   */
  public void export(HttpServletRequest request, HttpServletResponse response, ParamExport paramExport) {
    if (paramExport == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(paramExport.getFields())) {
      throw new CommonException(Code.PARAM_EMPTY, "fields 不能为空！");
    }
    ReqStatTest reqStatTest;
    String reqParam = paramExport.getReqParam();
    if (!Tool.isNull(reqParam)) {
      reqStatTest = ToolJson.toBean(reqParam, ReqStatTest.class);
    } else {
      reqStatTest = new ReqStatTest();
    }
    List<Map<String, Object>> list = daoStatTest.selectRelativeMap(reqStatTest);
    String title = paramExport.getTitle();
    if (Tool.isNull(title)) {
      title = "统计信息";
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
      case "dayNo":
        field.setLength(32);
        field.setExportFieldType(Enum.ExportFieldType.string_);
        break;
      case "argTime":
        field.setExportFieldType(Enum.ExportFieldType.decimal_);
        break;
      case "totalTime":
        field.setExportFieldType(Enum.ExportFieldType.decimal_);
        break;
      case "argNum":
        field.setExportFieldType(Enum.ExportFieldType.decimal_);
        break;
      case "totalSum":
        field.setExportFieldType(Enum.ExportFieldType.decimal_);
        break;
      case "addTime":
        field.setExportFieldType(Enum.ExportFieldType.datetime_);
        break;
      default:
        throw new CommonException(Code.PARAM_EMPTY, "unknow fileName: " + name);

    }
  }
}