package com.zfsoft.hqlGen.enumnation;

import org.apache.commons.lang3.StringUtils;

/**
 * @description name , 值之间的关系
 * @author wangsh
 * @date
 * @param
 * @return
 */
public enum PropertyType {

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
    NOT_EMPTY( "NOT_EMPTY");



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
}
