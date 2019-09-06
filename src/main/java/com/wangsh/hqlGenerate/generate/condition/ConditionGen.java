package com.wangsh.hqlGenerate.generate.condition;

import com.wangsh.hqlGenerate.helper.condition.ConditionHelper;
import com.wangsh.hqlGenerate.util.ClassUtil;
import com.zfsoft.hqlGen.enumnation.PropertyLinkType;
import com.zfsoft.hqlGen.enumnation.PropertyType;
import com.zfsoft.vo.BetweenVo;
import com.zfsoft.vo.SqlVo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangsh
 * @description
 * @date 2019-9-6
 * @Copyright
 */
public class ConditionGen implements ConditionHelper {


    private Class clasz;

    private List<Property> conditions;

    private List<List<Property>> andConditions;
    private List<List<Property>> orConditions;

    public ConditionHelper getInstance() {
        ConditionHelper helper = new ConditionGen();
        return helper;
    }

    public ConditionGen() {
        this.andConditions = new ArrayList<List<Property>>();
        this.orConditions = new ArrayList<List<Property>>();
        this.conditions = new ArrayList<Property>();
    }

    private ConditionHelper add(String name, Object value, PropertyType type) {
        this.conditions.add(Property.and(name, value, type));
        return this;
    }

    private ConditionHelper or(String name, Object value, PropertyType type) {
        this.conditions.add(Property.or(name, value, type));
        return this;
    }

    @Override
    public StringBuffer gengrate() throws Exception {
        StringBuffer conditionHql = new StringBuffer();
        for (int i = 0; i < andConditions.size(); i++) {
            for (int j = 0; j < andConditions.get(i).size(); j++) {
                Property p = andConditions.get(i).get(j);
                // 参数 在
                if(ClassUtil.filedInClass(clasz , p.getField())){
                    String begin =  alies+"." +p.getField();
                    String end ="";
                    // 字段 方式
                    PropertyType type = p.getType();
                    // 字段值
                    Object value = p.getValue();
                    if(type.equals(PropertyType.EMPTY) || type.equals(PropertyType.NOT_EMPTY) ){
                        conditionHql.append(p.getLinkType().name() +"("+begin+" is null or "+begin+" = '' )");
                    }


                }
            }
        }
        
        
        return null;
    }

    @Override
    public ConditionHelper groupAnd() {
        List<Property> _group = this.conditions;
        andConditions.add(_group);
        this.conditions = new ArrayList<Property>();
        return this;
    }

    @Override
    public ConditionHelper groupOr() {
        List<Property> _group = this.conditions;
        orConditions.add(_group);
        this.conditions = new ArrayList<Property>();
        return this;
    }

    @Override
    public ConditionHelper equal(String field, Object value) {
        return add(field, value, PropertyType.EQUAL);
    }

    @Override
    public ConditionHelper orEqual(String field, Object value) {
        return or(field, value, PropertyType.EQUAL);
    }

    @Override
    public ConditionHelper notEqual(String field, Object value) {
        return add(field, value, PropertyType.NOT_EQUAL);
    }

    @Override
    public ConditionHelper orNotEqual(String field, Object value) {
        return or(field, value, PropertyType.NOT_EQUAL);
    }

    @Override
    public ConditionHelper lt(String field, Comparable value) {
        return add(field, value, PropertyType.LESS);
    }

    @Override
    public ConditionHelper lt$eq(String field, Comparable value) {
        return add(field, value, PropertyType.LESS_EQUAL);
    }

    @Override
    public ConditionHelper gt(String field, Comparable value) {
        return add(field, value, PropertyType.GREATER);
    }

    @Override
    public ConditionHelper gt$eq(String field, Comparable value) {
        return add(field, value, PropertyType.GREATER_EQUAL);
    }

    @Override
    public ConditionHelper orLt(String field, Comparable value) {
        return or(field, value, PropertyType.LESS);
    }

    @Override
    public ConditionHelper orLt$eq(String field, Comparable value) {
        return or(field, value, PropertyType.LESS_EQUAL);
    }

    @Override
    public ConditionHelper orGt(String field, Comparable value) {
        return or(field, value, PropertyType.GREATER);
    }

    @Override
    public ConditionHelper orGt$eq(String field, Comparable value) {
        return or(field, value, PropertyType.GREATER_EQUAL);
    }

    @Override
    public ConditionHelper in(String field, SqlVo sqlVo) {
        return add(field, sqlVo, PropertyType.IN);
    }

    @Override
    public ConditionHelper orIn(String field, SqlVo sqlVo) {
        return or(field, sqlVo, PropertyType.IN);
    }

    @Override
    public ConditionHelper notIn(String field, SqlVo sqlVo) {
        return add(field, sqlVo, PropertyType.NOT_IN);
    }

    @Override
    public ConditionHelper orNotIn(String field, SqlVo sqlVo) {
        return or(field, sqlVo, PropertyType.NOT_IN);
    }

    @Override
    public ConditionHelper in(String field, List value) {
        return add(field , value ,PropertyType.IN);
    }

    @Override
    public ConditionHelper orIn(String field, Object[] value) {
        return or(field, Arrays.asList(value), PropertyType.IN);
    }

    @Override
    public ConditionHelper in(String field, List value, PropertyLinkType link) {
        return add(field, value, PropertyType.IN);
    }

    @Override
    public ConditionHelper orIn(String field, Object[] value, PropertyLinkType link) {
        return or(field, Arrays.asList(value), PropertyType.IN);
    }

    @Override
    public ConditionHelper exist(String field, SqlVo sqlVo) {
        return add(field, sqlVo, PropertyType.EXIST);
    }

    @Override
    public ConditionHelper notExist(String field, SqlVo sqlVo) {
        return add(field, sqlVo, PropertyType.NOT_EXIST);
    }

    @Override
    public ConditionHelper orExist(String field, SqlVo sqlVo) {
        return or(field, sqlVo, PropertyType.EXIST);
    }

    @Override
    public ConditionHelper orNotExist(String field, SqlVo sqlVo) {
        return or(field, sqlVo, PropertyType.NOT_EXIST);
    }

    @Override
    public ConditionHelper notIn(String field, List value) {
        return add(field, Arrays.asList(value), PropertyType.NOT_IN);
    }

    @Override
    public ConditionHelper orNotIn(String field, Object[] value) {
        return or(field, Arrays.asList(value), PropertyType.NOT_IN);
    }

    @Override
    public ConditionHelper notIn(String field, List value, PropertyLinkType link) {
        return add(field, Arrays.asList(value), PropertyType.NOT_IN);
    }

    @Override
    public ConditionHelper orNotIn(String field, Object[] value, PropertyLinkType link) {
        return or(field, Arrays.asList(value), PropertyType.NOT_IN);
    }

    @Override
    public ConditionHelper startWith(String field, String value) {
        return add(field, value+"%", PropertyType.LIKE);
    }

    @Override
    public ConditionHelper orStartWith(String field, String value) {
        return or(field, value+"%", PropertyType.LIKE);
    }

    @Override
    public ConditionHelper endWith(String field, String value) {
        return add(field, "%"+value, PropertyType.LIKE);
    }

    @Override
    public ConditionHelper orEndWith(String field, String value, PropertyLinkType link) {
        return or(field, "%"+value, PropertyType.LIKE);
    }

    @Override
    public ConditionHelper like(String field, String value) {
        return add(field, "%"+value+"%", PropertyType.LIKE);
    }

    @Override
    public ConditionHelper orLike(String field, String value) {
        return or(field, "%"+value+"%", PropertyType.LIKE);
    }

    @Override
    public ConditionHelper between(String field, BetweenVo vo) {
        return add(field, vo, PropertyType.BETWEEN);
    }

    @Override
    public ConditionHelper orBetween(String field, BetweenVo vo) {
        return or(field, vo, PropertyType.BETWEEN);
    }

    @Override
    public ConditionHelper isNull(String field) {
        return add(field, null, PropertyType.EQUAL);
    }

    @Override
    public ConditionHelper orNull(String field) {
        return or(field, null, PropertyType.EQUAL);
    }

    @Override
    public ConditionHelper notNull(String field) {
        return add(field, null, PropertyType.NOT_EQUAL);
    }

    @Override
    public ConditionHelper orNotNull(String field) {
        return or(field, null, PropertyType.NOT_EQUAL);
    }

    @Override
    public ConditionHelper isEmpty(String field) {
        return add(field, null, PropertyType.EMPTY);
    }

    @Override
    public ConditionHelper orEmpty(String field) {
        return or(field, null, PropertyType.EMPTY);
    }

    @Override
    public ConditionHelper isNotEmpty(String field) {
        return add(field, null, PropertyType.NOT_EMPTY);
    }

    @Override
    public ConditionHelper orNotEmpty(String field) {
        return or(field, null, PropertyType.NOT_EMPTY);
    }
}
