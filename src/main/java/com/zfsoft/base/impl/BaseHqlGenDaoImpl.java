package com.zfsoft.base.impl;

import com.zfsoft.base.IBaseHqlGenDao;
import com.zfsoft.common.Pagination;
import com.zfsoft.hqlGen.generate.HqlQueryGenerate;
import com.zfsoft.hqlGen.generate.PropertyFilter;
import com.zfsoft.vo.SqlVo;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * @author wangsh
 * @description
 * @date 2019-9-6
 * @Copyright
 */
public abstract class BaseHqlGenDaoImpl<T> implements IBaseHqlGenDao<T> {

    private SessionFactory sessionFactory;
    private Class clazz;
    private HqlQueryGenerate hqlQueryGenerate;

    public abstract void initParam();

    @Override
    public T queryFirstObjectByParams(PropertyFilter... params) throws Exception {
        return null;
    }

    @Override
    public List<T> queryObjectByParams(SqlVo sqlVo, Integer limit, PropertyFilter... params) throws Exception {
        return null;
    }

    @Override
    public List<T> queryObjectByParams(PropertyFilter... params) throws Exception {
        return null;
    }

    @Override
    public Integer queryObjectCountByParams(PropertyFilter... params) throws Exception {
        return null;
    }

    @Override
    public Pagination queryObjectPageByParams(SqlVo sqlVo, Integer pageNumber, Integer pageSize, PropertyFilter... params) throws Exception {
        return null;
    }

    @Override
    public PropertyFilter getPojoBaseParam() throws Exception {
        return null;
    }

    @Override
    public T getDefultValBean(T t) throws Exception {
        return null;
    }
}
