package com.wangsh.hqlGenerate.helper;

import com.wangsh.hqlGenerate.generate._property.Property;

import java.util.List;

/**
 * @author wangsh
 * @description
 * @date 2019-9-6
 * @Copyright
 */
public interface Helper {

    String alies = "t";

    /**
     * @param
     * @return
     * @description
     * @author wangsh
     * @date
     */
    StringBuffer gengrate() throws Exception;

    Class getClasz();

    void setClasz(Class clasz);
}
