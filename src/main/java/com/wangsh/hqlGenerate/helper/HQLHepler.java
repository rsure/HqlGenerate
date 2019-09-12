package com.wangsh.hqlGenerate.helper;

import com.wangsh.hqlGenerate.generate._property.Property;
import com.wangsh.hqlGenerate.generate.group.GroupGen;
import com.wangsh.hqlGenerate.generate.join.JoinGen;
import com.wangsh.hqlGenerate.generate.order.OrderGen;
import com.wangsh.hqlGenerate.generate.select.SelectGen;
import com.wangsh.hqlGenerate.generate.where.WhereGen;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * @author wangsh
 * @description
 * @date 2019-9-12
 * @Copyright
 */
public class HQLHepler implements Helper {

    private Class clazz;

    private SelectGen select;
    private WhereGen where;
    private List<JoinGen> joins;
    private GroupGen groupBy;
    private OrderGen orderBy;

    private List<Map<String,Object>> params;

    private HQLHepler(Class clazz) {
        this.clazz = clazz;
    }
    public HQLHepler getInstance(Class clazz){
        return new HQLHepler(clazz);
    }

    public HQLHepler select(String field , String alies){
        if(select == null) select = SelectGen.getInstance(clazz);
        select.select(field , alies );
        return this;
    }

    public HQLHepler select(String field){
        return select(field , alies);
    }

    public HQLHepler where(Property...properties){
        if(where == null) where = WhereGen.getInstance(clazz);
        where.add(properties);
        return this;
    }

    public HQLHepler where(List<Property> properties){
        if(where == null) where = WhereGen.getInstance(clazz);
        where.add(properties);
        return this;
    }



    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public StringBuffer gengrate() throws Exception {
        return null;
    }

    @Override
    public Class getClasz() {
        return null;
    }

    @Override
    public void setClasz(Class clasz) {

    }
}
