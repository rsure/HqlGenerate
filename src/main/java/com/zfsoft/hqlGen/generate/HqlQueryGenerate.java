package com.zfsoft.hqlGen.generate;

import com.zfsoft.hqlGen.enumnation.PropertyType;
import com.zfsoft.vo.BetweenVo;
import com.zfsoft.vo.SqlVo;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangsh
 * @description
 * @date 2019-8-24
 * @Copyright
 */
@Repository("hqlQueryGenerate")
public class HqlQueryGenerate {

    /**
     * @param session hibernate session
     * @param clazz   目标对象class
     * @param sqlVo   别名 默认 t
     * @return
     * @description 生成 hibernate query
     * @author wangsh
     * @date
     */
    public Query guery(Session session, Class clazz, SqlVo sqlVo, PropertyFilter... params) throws Exception {
        if (session == null) {
            throw new NullPointerException("hibernateSession 为空 , 无法生成query");
        }
        Map<String, Object> result = getQueryAndParams(clazz, sqlVo , params);
        String hql = (String) result.get("hql");
        Map<String, Object> sqlParameters = (Map<String, Object>) result.get("sqlParameters");
        Query query = session.createQuery(hql.toString());
        for (Map.Entry<String, Object> entry : sqlParameters.entrySet()) {
            if (entry.getValue() instanceof List) {
                query.setParameterList(entry.getKey(), (List) entry.getValue());
            } else {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }
        return query;
    }


    public Map<String, Object> getQueryAndParams(Class clazz, SqlVo sqlVo, PropertyFilter... params) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        if (clazz == null) {
            throw new NullPointerException("clasz 为空 , 无法生成 hql");
        }
        String className = clazz.getName();
        if(sqlVo == null ) sqlVo = new SqlVo();
        StringBuffer hql = new StringBuffer(sqlVo.getSelects() + " from " + className + " " + sqlVo.getAlias() + " where 1=1 ");
        Map<String, Object> sqlParameters = new HashMap<String, Object>();
        // 拼装 sql 参数
        if (params != null && params.length > 0) {
            for (PropertyFilter param : params) {
                Map<String, Object> inside = getSqlAndsqlParametersWithClasz(param.getLinkType().name(), clazz, param, sqlVo.getAlias());
                if (inside != null) {
                    String insideHql = (String) inside.get("hql");
                    Map<String, Object> insideParam = (Map<String, Object>) inside.get("sqlParameters");
                    hql.append(" and " + insideHql);
                    if (insideParam != null && !insideParam.isEmpty()) sqlParameters.putAll(insideParam);
                }
            }
        }
        hql.append(sqlVo.getGroupBys());
        hql.append(sqlVo.getOrderBys());

        result.put("hql", hql.toString());
        result.put("sqlParameters", sqlParameters);
        return result;

    }

    /**
     * @param
     * @return
     * @description 根据class 和 参数 获取sql
     * @author wangsh
     * @date
     * @NoSuchFieldException NoSuchFieldException
     */
    private Map<String, Object> getSqlAndsqlParametersWithClasz(String linkType, Class clasz, PropertyFilter params, String alias) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();

        StringBuffer hql = new StringBuffer(" ( 1=1");
        Map<String, Object> sqlParameters = new HashMap<String, Object>();
        // 别名
        if (alias != null && StringUtils.isNotEmpty(alias)) {
            alias = alias + ".";
        }
        // 链接类型 and or
        if (linkType == null || StringUtils.isEmpty(linkType)) {
            linkType = "and";
        }
        linkType = " " + linkType + " ";
        // 防止 字段重名
        String randomStr = alias.replaceAll("\\.", "");
        for (int index = 0; index < params.getFields().size(); index++) {
            String fieldName = params.getFields().get(index);
            Field field;
            try {
                // 校验字段是否存在
                field = clasz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                throw new NoSuchFieldException(clasz.getName() + " 不存在 字段 : " + params.getFields().get(index));
            }
            if (field != null) {
                // 包含字段
                if (field.getType().isAnnotationPresent(Entity.class)) {
                    // 字段是BaseBean的 有内部参数 获取内部参数的 sql
                    PropertyFilter insideFilter = (PropertyFilter) params.getFieldVals(index);
                    Map inside = getSqlAndsqlParametersWithClasz(insideFilter.getLinkType().name(), field.getType(), insideFilter, alias + fieldName);
                    hql.append(" and " + inside.get("hql"));
                    Map<String, Object> insideSqlParam = (Map<String, Object>) inside.get("sqlParameters");
                    sqlParameters.putAll(insideSqlParam);
                } else {
                    // 字段值
                    String type = params.getFieldTypes(index);
                    // 字段类型
                    Object value = params.getFieldVals(index);
                    if (value == null) {
                        hql.append(linkType + alias + fieldName + (!PropertyType.NOT_EQUAL.equals(type) ? " is null " : " is not null"));
                    } else {
                        // 内联sql 处理
                        if (value.getClass().getName().equals(PropertySql.class.getName())) {
                            if (PropertyType.EXIST.equals(type) || PropertyType.NOT_EXIST.equals(type)) {
                                hql.append(linkType + type + "(" + ((PropertySql) value).getSql() + ")");
                            } else {
                                hql.append(linkType + alias + fieldName + type + "(" + ((PropertySql) value).getSql() + ")");
                            }

                            Map<String, Object> sqlParam = ((PropertySql) value).getSqlParamNames();
                            for (String key : sqlParam.keySet()) {
                                if (sqlParameters.containsKey(key)) {
                                    throw new Exception(this.getClass().getName() + " 参数" + key + " 重复 ,  请检查sql;");
                                }
                            }
                            sqlParameters.putAll(sqlParam);

                        } else {
                            // 检查参数重复 主要是检查内联SQL中的参数重复 , 其余参数根据 index 排序 , 一般不会发生冲突
                            String keyName = randomStr + "_" + index + "_" + fieldName;
                            if (sqlParameters.containsKey(keyName)) {
                                throw new Exception(this.getClass().getName() + " 参数" + keyName + " 重复 ,  请检查sql;");
                            }
                            // 参数list 处理
                            if (value instanceof List) {
                                if (PropertyType.EQUAL.equals(type)) {
                                    type = PropertyType.IN.getType();
                                } else if (PropertyType.NOT_EQUAL.equals(type)) {
                                    type = PropertyType.NOT_IN.getType();
                                }
                            }
                            if (PropertyType.IN.equals(type) || PropertyType.NOT_IN.equals(type)) {
                                hql.append(linkType + alias + fieldName + type + " (:" + keyName + ") ");
                                sqlParameters.put(keyName, value);
                            } else if (PropertyType.BETWEEN.equals(type)) {
                                BetweenVo vo = (BetweenVo) value;
                                String beginName = keyName + "_begin";
                                String endName = keyName + "_end";
                                hql.append(linkType + "(" + alias + fieldName + type + ":" + beginName + " and " + ":" + endName + ")");

                                sqlParameters.put(beginName, vo.getBegin());
                                sqlParameters.put(endName, vo.getEnd());

                            } else {
                                hql.append(linkType + alias + fieldName + type + " :" + keyName + " ");
                                // 数值类型的特殊处理
                                if (field.getType().equals(Integer.class)) {
                                    value = Integer.parseInt(value.toString());
                                } else if (field.getType().equals(Long.class)) {
                                    value = Long.parseLong(value.toString());
                                }
                                sqlParameters.put(keyName, value);
                            }
                        }

                    }
                }
            }
        }
        hql.append(" )");
        result.put("hql", hql.toString());
        result.put("sqlParameters", sqlParameters);
        return result;
    }

}
