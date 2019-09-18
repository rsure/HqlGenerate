package com.wangsh.hqlGenerate.helper;

import com.wangsh.hqlGenerate.generate._property.Property;
import com.wangsh.hqlGenerate.generate.group.GroupGen;
import com.wangsh.hqlGenerate.generate.join.JoinGen;
import com.wangsh.hqlGenerate.generate.order.OrderGen;
import com.wangsh.hqlGenerate.generate.select.SelectGen;
import com.wangsh.hqlGenerate.generate.where.WhereGen;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author wangsh
 * @description
 * @date 2019-9-12
 * @Copyright
 */
public class HQLHelper implements Helper {

    /*
     * @description 目标 类的class
     */
    private Class clazz;
    /*
     * @description select 子句 , 可为空
     */
    private SelectGen select;
    /*
     * @description where 子句 , 可为空
     */
    private WhereGen where;
    /*
     * @description joins 子句 , 可为空
     */
    private List<JoinGen> joins;
    /*
     * @description groupBy 子句 , 可为空
     */
    private GroupGen groupBy;
    /*
     * @description orderBy 子句 , 可为空
     */
    private OrderGen orderBy;

    private Map<String, Object> params;

    private HQLHelper(Class clazz) {
        this.clazz = clazz;
        this.params = new HashMap<>();
    }

    public static HQLHelper getInstance(Class clazz) {
        return new HQLHelper(clazz);
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~select ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~select ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~select ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /*
     * @description
     * @param field 目标字段
     * @param alies 目标字段所属表别名
     */
    public HQLHelper select(String field, String alies) {
        getSelect().select(field, alies);
        return this;
    }

    /*
     * @description
     * @param field 目标字段
     */
    public HQLHelper select(String field) {
        return select(field, alies);
    }

    /*
     * @description
     * @param fields 目标字段 集合  默认为 主表字段
     */
    public HQLHelper selects(String... fields) {
        if (fields != null && fields.length > 0) {
            for (String field : fields) {
                this.select(field);
            }
        }
        return this;
    }

    /*
     * @description 添加自定义select字段 , select 需要加上别名
     * 如 customSelects("t.id","t1.name"...)
     */
    public HQLHelper  customSelects(String...fields){
        if (fields != null && fields.length > 0) {
            for (String field : fields) {
                getSelect().customSelect(field);
            }
        }
        return this;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~where ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~where ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~where ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /*
     * @description
     * @param properties Property 参数集合
     */
    public HQLHelper where(Property... properties) {
        getWhere().add(properties);
        return this;
    }

    /*
     * @description 创建where 子句
     * @param properties Property 参数集合
     */
    public HQLHelper where(List<Property> properties) {
        getWhere().add(properties);
        return this;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~group ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~group ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~group ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /*
     * @description 创建group 子句
     * @param fields 目标字段集合 , 默认为 主表字段
     */
    public HQLHelper groups(String... fields) {
        if (fields != null && fields.length > 0) {
            for (String field : fields) {
                this.group(field);
            }
        }
        return this;
    }
    /*
     * @description 添加自定义group字段 , group 需要加上别名
     * 如 customGroups("t.id","t1.name"...)
     */
    public HQLHelper customGroups(String... fields) {
        if (fields != null && fields.length > 0) {
            for (String field : fields) {
                this.group(field);
            }
        }
        return this;
    }

    /*
     * @description 创建group 子句
     * @param field 目标字段
     */
    public HQLHelper group(String field) {
        getGroupBy().group(field);
        return this;
    }

    /*
     * @description 创建group 子句
     * @param field 目标字段
     * @param alies 目标字段所属表别名
     */
    public HQLHelper group(String field, String fieldAlies) {
        if (groupBy == null) groupBy = GroupGen.getInstance(clazz);
        groupBy.group(field, fieldAlies);
        return this;
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~order ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~order ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~order ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /*
     * @description 添加自定义排序字段 , order 需要加上别名 默认表别名为t
     * 如 customOrders("t.id","t1.name"...)
     */
    public HQLHelper customOrders(String... orders) {
        if(orders!=null && orders.length > 0){
            for (String order : orders) {
                getOrderBy().customOrder(order);
            }
        }
        return this;
    }

    /*
     * @description 添加默认表的排序字段
     */
    public HQLHelper orders(String... orders) {
        if(orders!=null && orders.length > 0){
            for (String order : orders) {
                getOrderBy().order(order);
            }
        }
        return this;
    }

    /*
     * @description 排序降序 子句
     * @param field 目标字段
     */
    public HQLHelper desc(String field) {
        getOrderBy().desc(field);
        return this;
    }

    /*
     * @description 创建排序降序 子句
     * @param field 目标字段
     * @param alies 目标字段所属表别名
     */
    public HQLHelper desc(String field, String fieldAlies) {
        getOrderBy().desc(field, fieldAlies);
        return this;
    }

    /*
     * @description 排序升序序 子句
     * @param field 目标字段
     */
    public HQLHelper asc(String field) {
        getOrderBy().asc(field);
        return this;
    }

    /*
     * @description 创建排序升序 子句
     * @param field 目标字段
     * @param alies 目标字段所属表别名
     */
    public HQLHelper asc(String field, String fieldAlies) {
        getOrderBy().asc(field, fieldAlies);
        return this;
    }

    ///~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~joins~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    ///~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~joins~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    ///~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~joins~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /*
     * @description 创建左连接子句
     * @param field 目标字段或对象classname
     * @param joinAlies 被链接表别名
     * @param withFild 被链接字段名 : com.***.SysUser  或者 user -> user 为t的字字段
     * @param properties 参数集合
     */
    public HQLHelper leftJoin(String field, String withFild, String joinAlies, Property... properties) {
        if (joins == null) joins = new ArrayList<>();
        JoinGen gen = JoinGen.leftJoin(clazz, field, withFild, joinAlies);
        gen.add(properties);
        joins.add(gen);
        return this;
    }

    /*
     * @description 创建右连接子句
     * @param field 目标字段或对象classname
     * @param joinAlies 被链接表别名
     * @param withFild 被链接字段名 : com.***.user.name  或者 user.name -> user 为t的字字段
     * @param properties 参数集合
     */
    public HQLHelper rightJoin(String field, String withFild, String joinAlies, Property... properties) {
        if (joins == null) joins = new ArrayList<>();
        JoinGen gen = JoinGen.rightJoin(clazz, field, withFild, joinAlies);
        gen.add(properties);
        joins.add(gen);
        return this;
    }

    /*
     * @description 创建inner连接子句
     * @param field 目标字段或对象classname
     * @param joinAlies 被链接表别名
     * @param withFild 被链接字段名 : com.***.user.name  或者 user.name -> user 为t的字字段
     * @param properties 参数集合
     */
    public HQLHelper innerJoin(String field, String withFild, String joinAlies, Property... properties) {
        if (joins == null) joins = new ArrayList<>();
        JoinGen gen = JoinGen.innerJoin(clazz, field, withFild, joinAlies);
        gen.add(properties);
        joins.add(gen);
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
        return getHqlWithCostomSelect(select == null ? null : select.gengrate());
    }

    /**
     * @param
     * @return
     * @description 获取 用于统计数量的 hql 语句
     * @author wangsh
     * @date
     */
    public StringBuffer getCountHql(String select) throws Exception {
        StringBuffer hqlBuffer = new StringBuffer(" select count(*) ").append(getBaseHql());
        return hqlBuffer;
    }

    public StringBuffer getHqlWithCostomSelect(CharSequence select) throws Exception {
        StringBuffer hqlBuffer = new StringBuffer();
        if (StringUtils.isNotBlank(select)) {
            hqlBuffer.append(select);
        }
        hqlBuffer.append(getBaseHql());
        if (orderBy != null) hqlBuffer.append(orderBy.gengrate());
        return hqlBuffer;
    }

    /**
     * 　　* @description: 获取 无 select 无 order 的 hql 语句
     * 　　* @param
     * 　　* @return
     * 　　* @throws
     * 　　* @author wangsh
     * 　　* @date 2019-9-18 10:58
     */
    public StringBuffer getBaseHql() throws Exception {
        StringBuffer hqlBuffer = new StringBuffer(" from " + this.clazz.getName() + " " + this.alies + " ");
        if (joins != null) {
            for (JoinGen join : joins) {
                hqlBuffer.append(join.gengrate());
                params.putAll(join.getHqlParams());
            }
        }
        if (where != null) {
            hqlBuffer.append(where.gengrate());
            params.putAll(where.getHqlParams());
        }
        if (groupBy != null) hqlBuffer.append(groupBy.gengrate());
        return hqlBuffer;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        try {
            String string = this.gengrate().toString();
            if (this.getParams() != null) {
                for (String key : params.keySet()) {
                    if (params.get(key) != null)
                        string.replace(key, params.get(key).toString());
                }
            }
            return string;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.toString();
    }

    // 私有的get方法
    private SelectGen getSelect() {
        if (select == null) select = new SelectGen(this.clazz);
        return select;
    }

    private WhereGen getWhere() {
        if (where == null) where = new WhereGen(this.clazz);
        return where;
    }

    private List<JoinGen> getJoins() {
        if (joins == null) joins = new ArrayList<>();
        return joins;
    }

    private GroupGen getGroupBy() {
        if (groupBy == null) groupBy = new GroupGen(this.clazz);
        return groupBy;
    }

    private OrderGen getOrderBy() {
        if (orderBy == null) orderBy = new OrderGen(this.clazz);
        return orderBy;
    }

    //~~~~~~~~~~~~~~~~~~~~~~ where 拓展方法 and ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /*
     * @description 创建where 子句 的一个条件
     * @param properties Property 参数集合
     */
    public HQLHelper equal(String field, Object value) {
        getWhere().add(Property.equal(field, value));
        return this;
    }

    /*
     * @description 创建where 子句 的一个条件
     * @param properties Property 参数集合
     */
    public HQLHelper notEqual(String field, Object value) {
        getWhere().add(Property.notEqual(field, value));
        return this;
    }

    /*
     * @description 创建where 子句 的一个条件
     * @param properties Property 参数集合
     */
    public HQLHelper lt(String field, Comparable value) {
        getWhere().add(Property.lt(field, value));
        return this;
    }

    /*
     * @description 创建where 子句 的一个条件
     * @param properties Property 参数集合
     */
    public HQLHelper lt$eq(String field, Comparable value) {
        getWhere().add(Property.lt$eq(field, value));
        return this;
    }

    /*
     * @description 创建where 子句 的一个条件
     * @param properties Property 参数集合
     */
    public HQLHelper gt(String field, Comparable value) {
        getWhere().add(Property.gt(field, value));
        return this;
    }

    /*
     * @description 创建where 子句 的一个条件
     * @param properties Property 参数集合
     */
    public HQLHelper gt$eq(String field, Comparable value) {
        getWhere().add(Property.gt$eq(field, value));
        return this;
    }

    /*
     * @description 创建where 子句 的一个条件
     * @param properties Property 参数集合
     */
    public HQLHelper in(String field, List value) {
        getWhere().add(Property.in(field, value));
        return this;
    }

    /*
     * @description 创建where 子句 的一个条件
     * @param properties Property 参数集合
     */
    public HQLHelper in(String field, Object[] value) {
        getWhere().add(Property.in(field, value));
        return this;
    }

    public HQLHelper like(String field, String value) {
        getWhere().add(Property.like(field, value));
        return this;
    }

    public HQLHelper endWith(String field, String value) {
        getWhere().add(Property.endWith(field, value));
        return this;
    }

    public HQLHelper startWith(String field, String value) {
        getWhere().add(Property.startWith(field, value));
        return this;
    }

    /*
     * @description 创建where 子句 的一个条件
     * @param properties Property 参数集合
     */
    public HQLHelper between(String field, Comparable start, Comparable end) {
        getWhere().add(Property.between(field, start, end));
        return this;
    }

    /*
     * @description 创建where 子句 的一个条件
     * @param properties Property 参数集合
     */
    public HQLHelper isNull(String field) {
        getWhere().add(Property.isNull(field));
        return this;
    }

    /*
     * @description 创建where 子句 的一个条件
     * @param properties Property 参数集合
     */
    public HQLHelper notNull(String field) {
        getWhere().add(Property.notNull(field));
        return this;
    }

    /*
     * @description 创建where 子句 的一个条件
     * @param properties Property 参数集合
     */
    public HQLHelper isEmpty(String field) {
        getWhere().add(Property.isEmpty(field));
        return this;
    }

    /*
     * @description 创建where 子句 的一个条件
     * @param properties Property 参数集合
     */
    public HQLHelper isNotEmpty(String field) {
        getWhere().add(Property.isNotEmpty(field));
        return this;
    }

    /*
     * @description 创建where 子句 的一个条件
     * @param properties Property 参数集合
     */
    public HQLHelper andHql(String hql) {
        getWhere().add(Property.andHql(hql));
        return this;
    }

    /*
     * @description 创建where 子句 的一个条件
     * @param properties Property 参数集合
     */
    public HQLHelper inHql(String field, String hql) {
        getWhere().add(Property.inHql(field, hql));
        return this;
    }

    /*
     * @description 创建where 子句 的一个条件
     * @param properties Property 参数集合
     */
    public HQLHelper notInHql(String field, String hql) {
        getWhere().add(Property.notInHql(field, hql));
        return this;
    }

    /*
     * @description 创建where 子句 的一个条件
     * @param properties Property 参数集合
     */
    public HQLHelper existHql(String field, String hql) {
        getWhere().add(Property.existHql(field, hql));
        return this;
    }

    /*
     * @description 创建where 子句 的一个条件
     * @param properties Property 参数集合
     */
    public HQLHelper notExistHql(String field, String hql) {
        getWhere().add(Property.notExistHql(field, hql));
        return this;
    }

    //~~~~~~~~~~~~~~~~~~~~~~ where 拓展方法 or~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public HQLHelper orHql(String hql) {
        getWhere().add(Property.orHql(hql));
        return this;
    }

    public HQLHelper orInHql(String field, String hql) {
        getWhere().add(Property.orInHql(field, hql));
        return this;
    }

    public HQLHelper orNotInHql(String field, String hql) {
        getWhere().add(Property.orNotInHql(field, hql));
        return this;
    }

    public HQLHelper orExistHql(String field, String hql) {
        getWhere().add(Property.orExistHql(field, hql));
        return this;
    }

    public HQLHelper orNotExistHql(String field, String hql) {
        getWhere().add(Property.orNotExistHql(field, hql));
        return this;
    }

    public HQLHelper groupOr(List<Property> value) {
        getWhere().add(Property.groupOr(value));
        return this;
    }

    public HQLHelper orEqual(String field, Object value) {
        getWhere().add(Property.orEqual(field, value));
        return this;
    }

    public HQLHelper orNotEqual(String field, Object value) {
        getWhere().add(Property.orNotEqual(field, value));
        return this;
    }

    public HQLHelper orLt(String field, Comparable value) {
        getWhere().add(Property.orLt(field, value));
        return this;
    }


    public HQLHelper orLt$eq(String field, Comparable value) {
        getWhere().add(Property.orLt$eq(field, value));
        return this;
    }


    public HQLHelper orGt(String field, Comparable value) {
        getWhere().add(Property.orGt(field, value));
        return this;
    }


    public HQLHelper orGt$eq(String field, Comparable value) {
        getWhere().add(Property.orGt$eq(field, value));
        return this;
    }

    public HQLHelper orIn(String field, List value) {
        getWhere().add(Property.orIn(field, Arrays.asList(value)));
        return this;
    }


    public HQLHelper orIn(String field, Object[] value) {
        getWhere().add(Property.orIn(field, Arrays.asList(value)));
        return this;
    }

    public HQLHelper orNotIn(String field, Object[] value) {
        getWhere().add(Property.orNotIn(field, value));
        return this;
    }

    public HQLHelper orNotIn(String field, List value) {
        getWhere().add(Property.orNotIn(field, value));
        return this;
    }

    public HQLHelper orStartWith(String field, String value) {
        getWhere().add(Property.orStartWith(field, value));
        return this;
    }

    public HQLHelper orEndWith(String field, String value) {
        getWhere().add(Property.orEndWith(field, value));
        return this;
    }

    public HQLHelper orLike(String field, String value) {
        getWhere().add(Property.orLike(field, value));
        return this;
    }

    public HQLHelper orBetween(String field, Comparable start, Comparable end) {
        getWhere().add(Property.orBetween(field, start, end));
        return this;
    }

    public HQLHelper orNull(String field) {
        getWhere().add(Property.orNull(field));
        return this;
    }

    public HQLHelper orNotNull(String field) {
        getWhere().add(Property.orNotNull(field));
        return this;
    }

    public HQLHelper orEmpty(String field) {
        getWhere().add(Property.orEmpty(field));
        return this;
    }

    public HQLHelper orNotEmpty(String field) {
        getWhere().add(Property.orNotEmpty(field));
        return this;
    }
}
