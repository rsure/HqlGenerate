package com.wangsh.hqlGenerate.generate.condition;

import com.wangsh.hqlGenerate.generate._property.Property;
import com.wangsh.hqlGenerate.helper.Helper;
import com.wangsh.hqlGenerate.enumnation.PropertyType;
import com.wangsh.hqlGenerate.vo.BetweenVo;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author wangsh
 * @description
 * @date 2019-9-6
 * @Copyright
 */
public class ConditionGen implements Helper {


    protected Class clasz;

    protected List<Property> conditions;

    private Map<String, Object> hqlParams;

    public static ConditionGen getInstance(Class clasz) {
        ConditionGen helper = new ConditionGen(clasz);
        return helper;
    }

    public ConditionGen(Class clasz) {
        this.clasz = clasz;
        this.conditions = new ArrayList<>();
        this.hqlParams = new HashMap<>();
    }

    @Override
    public StringBuffer gengrate() throws Exception {
        StringBuffer conditionHql = new StringBuffer("");
        if (!conditions.isEmpty()) {
            conditionHql.append(gengrate(null, this.conditions));
        }
        return conditionHql;
    }

    private StringBuffer gengrate(Integer index, List<Property> properties) {
        StringBuffer conditionHql = new StringBuffer("");
        if (properties != null) {
            for (int i = 0; i < properties.size(); i++) {
                if (properties.get(i) != null) {
                    Property property = properties.get(i);
                    Object value = property.getValue();
                    // 字段 方式
                    PropertyType propertyType = property.getType();
                    if (i > 0) {
                        conditionHql.append(" " + property.getLinkType().name() + " ");
                    }
                    // 分组
                    if (PropertyType.GROUP.equals(propertyType)) {
                        if (property.getValue() != null) {
                            conditionHql.append(" ( ").append(
                                    // 内部的组合 参数 处理
                                    gengrate(i, (List<Property>) value)

                            ).append(" ) ");
                        }
                    } else if (PropertyType.NO_ACTIVE.equals(propertyType)) {
                        // 直连 sql
                        if (value != null && StringUtils.isNotBlank(value.toString())) {
                            conditionHql.append("(").append(value.toString()).append(")");
                        }
                    } else {
                        // 单个参数处理
                        // 参数字段 属于class
                        String $field = alies + "." + property.getField();


                        if (propertyType.equals(PropertyType.EMPTY)) {
                            // 为空
                            conditionHql.append("(" + $field + " is null or " + $field + " = '' )");
                        } else if (propertyType.equals(PropertyType.NOT_EMPTY)) {
                            // 非空
                            conditionHql.append("(" + $field + " is not null and " + $field + " != '' )");
                        } else if (propertyType.equals(PropertyType.NULL) || propertyType.equals(PropertyType.NOT_NULL)) {
                            // null 或 not null
                            conditionHql.append($field + propertyType.getType());

                        } else if (PropertyType.EXIST.equals(propertyType) || PropertyType.NOT_EXIST.equals(propertyType)) {
                            conditionHql.append(propertyType.getType()).append(" ( ").append(value.toString()).append(" ) ");
                        } else {
                            // 参数名
                            StringBuffer fieldParamName = new StringBuffer(alies.replaceAll("\\.", "") + "_");
                            if (index != null) {
                                fieldParamName.append(index).append("_").append(i);
                            } else {
                                fieldParamName.append(i).append("_").append(0);
                            }
                            fieldParamName.append("_" + property.getField());
                            if (PropertyType.IN.equals(propertyType) || PropertyType.NOT_IN.equals(propertyType)) {

                                if (value instanceof List) {
                                    if (!((List) value).isEmpty()) {
                                        if (((List) value).size() == 1) {
                                            conditionHql.append($field + (PropertyType.IN.equals(propertyType) ? " = " : " != ") + " :").append(fieldParamName).append(" ");
                                            this.hqlParams.put(fieldParamName.toString(), ((List) value).get(0));
                                        } else {
                                            conditionHql.append($field + propertyType.getType() + " (:").append(fieldParamName).append(") ");
                                            this.hqlParams.put(fieldParamName.toString(), value);
                                        }
                                    }
                                } else {
                                    // 内联sql
                                    conditionHql.append($field + propertyType.getType() + " (" + value.toString() + ") ");
                                }

                            } else if (PropertyType.BETWEEN.equals(propertyType)) {
                                BetweenVo vo = (BetweenVo) value;
                                StringBuffer beginName = new StringBuffer(fieldParamName).append("_begin");
                                StringBuffer endName = new StringBuffer(fieldParamName).append("_end");

                                conditionHql.append("(" + $field + propertyType.getType() + ":").append(beginName).append(" and " + ":").append(endName).append(")");

                                this.hqlParams.put(beginName.toString(), vo.getBegin());
                                this.hqlParams.put(endName.toString(), vo.getEnd());
                            } else {
                                conditionHql.append($field + propertyType.getType() + " :").append(fieldParamName).append(" ");
                                this.hqlParams.put(fieldParamName.toString(), value);
                            }

                        }

                    }
                }
            }
        }
        return conditionHql;
    }


    public List<Property> getConditions() {
        return conditions;
    }

    public Map<String, Object> getHqlParams() {
        return hqlParams;
    }

    public Class getClasz() {
        return clasz;
    }

    public void setClasz(Class clasz) {
        this.clasz = clasz;
    }

    public Helper add(Property... propertys) {
        if (propertys != null && propertys.length > 0) {
            if (propertys.length == 1) {
                this.conditions.add(propertys[0]);
            } else {
                this.conditions.addAll(Arrays.asList(propertys));
            }
        }
        return this;
    }

    public Helper add(List<Property> propertys) {
        this.conditions.addAll(propertys);
        return this;
    }
}
