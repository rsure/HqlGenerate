package com.zfsoft.base;

import com.zfsoft.common.Pagination;
import com.zfsoft.hqlGen.generate.PropertyFilter;
import com.zfsoft.vo.SqlVo;

import java.util.List;

/**
 * @author wangsh
 * @description
 * @date 2019-8-26
 * @Copyright
 */
public interface IBaseHqlGenDao<T> {


    /**
     * @return
     * @description 根据对象和对象参数 获取对象
     * @author wangsh
     * @date 20190802
     */
    T queryFirstObjectByParams (PropertyFilter...params) throws Exception;

    /**
     * @return
     * @description 根据对象和对象参数 获取对象列表
     * @author wangsh
     * @date 20190802
     */
    List<T> queryObjectByParams(SqlVo sqlVo , Integer limit , PropertyFilter...params) throws Exception;
    /**
     * @return
     * @description 根据对象和对象参数 获取对象列表
     * @author wangsh
     * @date 20190802
     */
    List<T> queryObjectByParams( PropertyFilter...params) throws Exception;
    /**
     * @return
     * @description 根据对象和对象参数 获取对象数量
     * @author wangsh
     * @date 20190802
     */
    Integer queryObjectCountByParams( PropertyFilter...params) throws Exception;

    /**
     * @return
     * @description 根据对象和对象参数 获取对象page
     * @author wangsh
     * @date 20190802
     */
    Pagination queryObjectPageByParams(SqlVo sqlVo , Integer pageNumber, Integer pageSize , PropertyFilter...params) throws Exception;

    /**
     * @description  获取类的基础参数
     * @author wangsh
     * @date
     * @param
     * @return
     */
    PropertyFilter getPojoBaseParam() throws Exception;

    /**
     * @description  设置对象默认值
     * @author wangsh
     * @date
     * @param
     * @return
     */
    T  getDefultValBean(T t) throws Exception ;

}
