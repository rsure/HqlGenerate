package com.wangsh.hqlGenerate.generate.condition;

import com.zfsoft.hqlGen.enumnation.PropertyLinkType;
import com.zfsoft.hqlGen.enumnation.PropertyType;

/**
 * @author wangsh
 * @description
 * @date 2019-9-6
 * @Copyright
 */
public class Property {

    private String field;

    private Object value;

    private PropertyType type;

    private PropertyLinkType linkType;

    public Property(String field, Object value, PropertyType type, PropertyLinkType linkType) {
        this.field = field;
        this.value = value;
        this.type = type;
        this.linkType = linkType;
    }

    public static Property and(String field, Object value, PropertyType type){
        return new Property(field ,value , type , PropertyLinkType.AND);
    }

    public static Property or(String field, Object value, PropertyType type){
        return new Property(field ,value , type , PropertyLinkType.OR);
    }


    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public PropertyType getType() {
        return type;
    }

    public void setType(PropertyType type) {
        this.type = type;
    }

    public PropertyLinkType getLinkType() {
        return linkType;
    }

    public void setLinkType(PropertyLinkType linkType) {
        this.linkType = linkType;
    }
}
