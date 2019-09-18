package com.wangsh.hqlGenerate.generate._property;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangsh
 * @description 获取参数集合的快速工具类
 * @date 2019-9-11
 * @Copyright
 */
public class PropertyUtil {

    private List<Property> propertys;

    public PropertyUtil() {
        this.propertys = new ArrayList<>();
    }


    public static PropertyUtil getInstance() {
        return new PropertyUtil();
    }

    public List<Property> getPropertys() {
        // 去除空参数
        List<Property> nullProperty = new ArrayList<>();
        nullProperty.add(null);
        propertys.removeAll(nullProperty);
        return propertys;
    }

    public void setPropertys(List<Property> propertys) {
        this.propertys = propertys;
    }

    // ~~ 特殊方法 ~~~~~~~~~~~~~~~~~~~~~~~

    /*
     * @description 将所有的 参数 组成一个参数组 并用 and 链接
     * 生成类似 and( 1=1 and (name='1' or 2=1) )
     */
    public Property groupAndProperties(){
        return Property.groupAnd(this.getPropertys());
    }

    /*
     * @description 将所有的 参数 组成一个参数组 并用 or 链接
     * 生成类似  or( 1=1 and (name='1' or 2=1) )
     */
    public Property groupOrProperties(){
        return Property.groupOr(this.getPropertys());
    }

    //~~~~~~~~~~~~~~~~~~~~~~ 内联 hql  start ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public PropertyUtil andHql(String hql) {
        this.propertys.add(Property.andHql(hql));
        return this;
    }

    public PropertyUtil orHql(String hql) {
        this.propertys.add(Property.orHql(hql));
        return this;
    }

    public PropertyUtil inHql(String field, String hql) {
        this.propertys.add(Property.inHql(field, hql));
        return this;
    }

    public PropertyUtil orInHql(String field, String hql) {
        this.propertys.add(Property.orInHql(field, hql));
        return this;
    }

    public PropertyUtil notInHql(String field, String hql) {
        this.propertys.add(Property.notInHql(field, hql));
        return this;
    }

    public PropertyUtil orNotInHql(String field, String hql) {
        this.propertys.add(Property.orNotInHql(field, hql));
        return this;
    }

    public PropertyUtil existHql(String field, String hql) {
        this.propertys.add(Property.existHql(field, hql));
        return this;
    }

    public PropertyUtil orExistHql(String field, String hql) {
        this.propertys.add(Property.orExistHql(field, hql));
        return this;
    }

    public PropertyUtil notExistHql(String field, String hql) {
        this.propertys.add(Property.notExistHql(field, hql));
        return this;
    }

    public PropertyUtil orNotExistHql(String field, String hql) {
        this.propertys.add(Property.orNotExistHql(field, hql));
        return this;
    }
    //~~~~~~~~~~~~~~~~~~~~~~ 内联 hql   end ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    public PropertyUtil groupAnd(List<Property> value) {
        this.propertys.add(Property.groupAnd( value));
        return this;
    }

    public PropertyUtil groupOr(List<Property> value) {
        this.propertys.add(Property.groupOr( value));
        return this;
    }

    public PropertyUtil equal(String field, Object value) {
        this.propertys.add(Property.equal(field, value));
        return this;
    }


    public PropertyUtil orEqual(String field, Object value) {
        this.propertys.add(Property.orEqual(field, value));
        return this;
    }


    public PropertyUtil notEqual(String field, Object value) {
        this.propertys.add(Property.notEqual(field, value));
        return this;
    }


    public PropertyUtil orNotEqual(String field, Object value) {
        this.propertys.add(Property.orNotEqual(field, value));
        return this;
    }


    public PropertyUtil lt(String field, Comparable value) {
        this.propertys.add(Property.lt(field, value));
        return this;
    }


    public PropertyUtil lt$eq(String field, Comparable value) {
        this.propertys.add(Property.lt$eq(field, value));
        return this;
    }


    public PropertyUtil gt(String field, Comparable value) {
        this.propertys.add(Property.gt(field, value));
        return this;
    }


    public PropertyUtil gt$eq(String field, Comparable value) {
        this.propertys.add(Property.gt$eq(field, value));
        return this;
    }


    public PropertyUtil orLt(String field, Comparable value) {
        this.propertys.add(Property.orLt(field, value));
        return this;
    }


    public PropertyUtil orLt$eq(String field, Comparable value) {
        this.propertys.add(Property.orLt$eq(field, value));
        return this;
    }


    public PropertyUtil orGt(String field, Comparable value) {
        this.propertys.add(Property.orGt(field, value));
        return this;
    }


    public PropertyUtil orGt$eq(String field, Comparable value) {
        this.propertys.add(Property.orGt$eq(field, value));
        return this;
    }


    public PropertyUtil in(String field, List value) {
        this.propertys.add(Property.in(field, value));
        return this;
    }


    public PropertyUtil in(String field, Object[] value) {
        this.propertys.add(Property.in(field, Arrays.asList(value)));
        return this;
    }


    public PropertyUtil orIn(String field, List value) {
        this.propertys.add(Property.orIn(field, Arrays.asList(value)));
        return this;
    }


    public PropertyUtil orIn(String field, Object[] value) {
        this.propertys.add(Property.orIn(field, Arrays.asList(value)));
        return this;
    }


    public PropertyUtil notIn(String field, List value) {
        this.propertys.add(Property.notIn(field, value));
        return this;
    }


    public PropertyUtil orNotIn(String field, Object[] value) {
        this.propertys.add(Property.orNotIn(field, value));
        return this;
    }

    public PropertyUtil orNotIn(String field, List value) {
        this.propertys.add(Property.orNotIn(field, value));
        return this;
    }

    public PropertyUtil startWith(String field, String value) {
        this.propertys.add(Property.startWith(field, value));
        return this;
    }


    public PropertyUtil orStartWith(String field, String value) {
        this.propertys.add(Property.orStartWith(field, value));
        return this;
    }


    public PropertyUtil endWith(String field, String value) {
        this.propertys.add(Property.endWith(field, value));
        return this;
    }


    public PropertyUtil orEndWith(String field, String value) {
        this.propertys.add(Property.orEndWith(field, value));
        return this;
    }


    public PropertyUtil like(String field, String value) {
        this.propertys.add(Property.like(field, value));
        return this;
    }


    public PropertyUtil orLike(String field, String value) {
        this.propertys.add(Property.orLike(field, value));
        return this;
    }


    public PropertyUtil between(String field, Comparable start, Comparable end) {
        this.propertys.add(Property.between(field, start, end));
        return this;
    }


    public PropertyUtil orBetween(String field, Comparable start, Comparable end) {
        this.propertys.add(Property.orBetween(field, start, end));
        return this;
    }


    public PropertyUtil isNull(String field) {
        this.propertys.add(Property.isNull(field));
        return this;
    }


    public PropertyUtil orNull(String field) {
        this.propertys.add(Property.orNull(field));
        return this;
    }


    public PropertyUtil notNull(String field) {
        this.propertys.add(Property.notNull(field));
        return this;
    }


    public PropertyUtil orNotNull(String field) {
        this.propertys.add(Property.orNotNull(field));
        return this;
    }


    public PropertyUtil isEmpty(String field) {
        this.propertys.add(Property.isEmpty(field));
        return this;
    }


    public PropertyUtil orEmpty(String field) {
        this.propertys.add(Property.orEmpty(field));
        return this;
    }


    public PropertyUtil isNotEmpty(String field) {
        this.propertys.add(Property.isNotEmpty(field));
        return this;
    }


    public PropertyUtil orNotEmpty(String field) {
        this.propertys.add(Property.orNotEmpty(field));
        return this;
    }


}
