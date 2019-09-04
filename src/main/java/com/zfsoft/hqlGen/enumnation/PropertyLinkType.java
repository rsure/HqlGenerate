package com.zfsoft.hqlGen.enumnation;

/**
 * @description 参数之间的 关联关系
 * @author wangsh
 * @date
 * @param
 * @return
 */
public enum PropertyLinkType {

    AND , OR ;

    public boolean equal(String type){
        return this.name().equals(type);
    }
}
