package com.zfsoft.hqlGen.generate;

/**
 * @author wangsh
 * @description
 * @date 2019-8-24
 * @Copyright
 */

import com.zfsoft.hqlGen.enumnation.PropertyType;
import com.zfsoft.hqlGen.enumnation.PropertyLinkType;

import java.util.ArrayList;
import java.util.List;

/**
 * @param
 * @author wangsh
 * @description bean 获取基础搜索方法参数
 * @date
 * @return
 */
public class PropertyFilter {
    // 类型 and or
    private PropertyLinkType linkType;
    // 字段名称 , 与 class.field 相同
    private List<String> fields;
    // 字段 值
    private List<Object> fieldVals;
    // 字段查询类型
    private List<PropertyType> fieldTypes;

    public void put(String fieldName, Object fieldVal, PropertyType fieldType) {
        this.fields.add(fieldName);
        this.fieldVals.add(fieldVal);
        this.fieldTypes.add(fieldType);
    }

    public PropertyFilter() {
        this.linkType = PropertyLinkType.AND;
        this.fields = new ArrayList<String>();
        this.fieldVals = new ArrayList<Object>();
        this.fieldTypes = new ArrayList<PropertyType>();
    }

    public PropertyFilter(PropertyLinkType linkType) {
        this.linkType = linkType;
        this.fields = new ArrayList<String>();
        this.fieldVals = new ArrayList<Object>();
        this.fieldTypes = new ArrayList<PropertyType>();
    }

    public static PropertyFilter getInstance() {
        return new PropertyFilter();
    }

    /**
     * @param
     * @return
     * @description 默认等于 , 设置参数
     * @author wangsh
     * @date
     */
    public void put(String fieldName, Object fieldVal) {
        fields.add(fieldName);
        fieldVals.add(fieldVal);
        fieldTypes.add(PropertyType.EQUAL);
    }

    /**
    　　* @description: 批量添加参数
    　　* @param propertyFilter 参数
    　　* @return
    　　* @throws
    　　* @author wangsh
    　　* @date 2019-9-2 17:06
    　　*/
    public void putAll(PropertyFilter propertyFilter) {
        if (propertyFilter != null && propertyFilter.getFields() != null) {
            for (int i = 0; i < propertyFilter.getFields().size(); i++) {
                this.put(propertyFilter.getFields().get(i), propertyFilter.getFieldVals().get(i), propertyFilter.getFieldTypes().get(i));
            }
        }
    }

    /**
     * @param
     * @return
     * @description 清除 参数中指定名称的参数
     * @author wangsh
     * @date
     */
    public void remove(String fieldName) {
        int index = fields.indexOf(fieldName);
        while (fields.indexOf(fieldName) >= 0) {
            fields.remove(index);
            fieldVals.remove(index);
            fieldTypes.remove(index);
        }
    }

    /**
     * 根据index 取字段名
     *
     * @param
     * @return
     * @author wangsh
     * @date
     */
    public String getFields(int index) {
        return fields.get(index);
    }

    /**
     * 根据index 取值
     *
     * @param
     * @return
     * @author wangsh
     * @date
     */
    public Object getFieldVals(int index) {
        return fieldVals.get(index);
    }

    /**
     * 根据index 取类型
     *
     * @param
     * @return
     * @author wangsh
     * @date
     */
    public String getFieldTypes(int index) {
        return fieldTypes.get(index).getType();
    }


    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public List<Object> getFieldVals() {
        return fieldVals;
    }

    public void setFieldVals(List<Object> fieldVals) {
        this.fieldVals = fieldVals;
    }

    public List<PropertyType> getFieldTypes() {
        return fieldTypes;
    }

    public void setFieldTypes(List<PropertyType> fieldTypes) {
        this.fieldTypes = fieldTypes;
    }

    public PropertyLinkType getLinkType() {
        return this.linkType;
    }

    public void setLinkType(PropertyLinkType linkType) {
        this.linkType = linkType;
    }
}
