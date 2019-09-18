package com.wangsh.hqlGenerate.enumnation;

import org.apache.commons.lang3.StringUtils;

/**
 * @description name , 值之间的关系
 * @author wangsh
 * @date
 * @param
 * @return
 */
public enum PropertyType {

    NULL(" is null "),
    NOT_NULL(" is not null "),
    NOT_EQUAL(" != "),
    GREATER(" > "),
    LESS(" < "),
    EQUAL(" = "),
    LIKE (" like "),
    GREATER_EQUAL( " >= "),
    LESS_EQUAL(" <= "),
    IN(" in "),
    NOT_IN(" not in "),
    BETWEEN(" between "),
    EXIST( " EXIST "),
    NOT_EXIST( " NOT EXIST "),
    EMPTY( "EMPTY"),
    NOT_EMPTY( "NOT_EMPTY"),
    /*
     * @description 值可再分
     */
    GROUP("GROUP"),
    /*
     * @description 无关联 直接 and
     */
    NO_ACTIVE("NO_ACTIVE");




    private final String type;
    PropertyType(String type) {
        this.type = type;
    }

    public String getType(){
        return this.type;
    }

    public boolean equals(String typeName){
        if(StringUtils.isBlank(typeName)) return false;
        return this.getType().equals(typeName);
    }

    public boolean equals(PropertyType type){
        return this.getType().equals(type.getType());
    }
}
