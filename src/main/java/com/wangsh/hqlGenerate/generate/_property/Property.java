package com.wangsh.hqlGenerate.generate._property;

import com.wangsh.hqlGenerate.HqlGenBaseUtil;
import com.wangsh.hqlGenerate.enumnation.PropertyLinkType;
import com.wangsh.hqlGenerate.enumnation.PropertyType;
import com.wangsh.hqlGenerate.vo.BetweenVo;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;


/**
 * @author wangsh
 * @description 参数
 * @date 2019-9-6
 * @Copyright
 */
public class Property {

    private String field;

    private Object value;

    private PropertyType type;

    private PropertyLinkType linkType;

    private Property(String field, Object value, PropertyType type, PropertyLinkType linkType) {
        this.field = field;
        this.value = value;
        this.type = type;
        this.linkType = linkType;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public PropertyType getType() {
        return type;
    }

    public void setType(PropertyType type) {
        this.type = type;
    }

    public PropertyLinkType getLinkType() {
        return linkType;
    }

    public void setLinkType(PropertyLinkType linkType) {
        this.linkType = linkType;
    }

    // ~~~~~~~~~~~~~~~~~~~~~ 增加参数 公共私有方法 防止意外调用~~~~~~~~~

    private static Property and(String field, Object value, PropertyType type) {
        return new Property(field, value, type, PropertyLinkType.AND);
    }

    private static Property or(String field, Object value, PropertyType type) {
        return new Property(field, value, type, PropertyLinkType.OR);
    }

    //~~~~~~~~~~~~~~~~~~~~~~ 内联 hql  start ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static Property andHql(String hql) {
        if (StringUtils.isBlank(hql)) return null;
        return Property.and(null, hql, PropertyType.NO_ACTIVE);
    }

    public static Property orHql(String hql) {
        if (StringUtils.isBlank(hql)) return null;
        return Property.or(null, hql, PropertyType.NO_ACTIVE);
    }

    public static Property inHql(String field, String hql) {
        if (StringUtils.isBlank(field)) return null;
        if (StringUtils.isBlank(hql)) return null;
        return add(field, hql, PropertyType.IN);
    }

    public static Property orInHql(String field, String hql) {
        if (StringUtils.isBlank(field)) return null;
        if (StringUtils.isBlank(hql)) return null;
        return or(field, hql, PropertyType.IN);
    }

    public static Property notInHql(String field, String hql) {
        if (StringUtils.isBlank(field)) return null;
        if (StringUtils.isBlank(hql)) return null;
        return and(field, hql, PropertyType.NOT_IN);
    }

    public static Property orNotInHql(String field, String hql) {
        if (StringUtils.isBlank(field)) return null;
        if (StringUtils.isBlank(hql)) return null;
        return or(field, hql, PropertyType.NOT_IN);
    }

    public static Property existHql(String field, String hql) {
        if (StringUtils.isBlank(field)) return null;
        if (StringUtils.isBlank(hql)) return null;
        return Property.add(field, hql, PropertyType.EXIST);
    }

    public static Property orExistHql(String field, String hql) {
        if (StringUtils.isBlank(field)) return null;
        if (StringUtils.isBlank(hql)) return null;
        return Property.addOr(field, hql, PropertyType.EXIST);
    }

    public static Property notExistHql(String field, String hql) {
        if (StringUtils.isBlank(field)) return null;
        if (StringUtils.isBlank(hql)) return null;
        return Property.add(field, hql, PropertyType.NOT_EXIST);
    }

    public static Property orNotExistHql(String field, String hql) {
        if (StringUtils.isBlank(field)) return null;
        if (StringUtils.isBlank(hql)) return null;
        return Property.addOr(field, hql, PropertyType.NOT_EXIST);
    }

    //~~~~~~~~~~~~~~~~~~~~~~ 内联 hql   end ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //~~~~~~~~~~~~~~~~~~~~~~ 内联 参数   start ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static Property groupAnd(List<Property> value) {
        if (value == null || value.isEmpty()) return null;
        if (value.size() == 1)
            return new Property(null, value.get(0), PropertyType.GROUP, PropertyLinkType.AND);
        return new Property(null, value, PropertyType.GROUP, PropertyLinkType.AND);
    }

    public static Property groupOr(List<Property> value) {
        if (value == null || value.isEmpty()) return null;
        if (value.size() == 1)
            return new Property(null, value.get(0), PropertyType.GROUP, PropertyLinkType.OR);
        return new Property(null, value, PropertyType.GROUP, PropertyLinkType.OR);
    }


    private static Property add(String name, Object value, PropertyType type) {
        return Property.and(name, value, type);
    }

    private static Property addOr(String name, Object value, PropertyType type) {
        return Property.or(name, value, type);
    }

    public static Property equal(String field, Object value) {
        if (StringUtils.isBlank(field)) return null;
        if (HqlGenBaseUtil.isEmpty(value)) return null;
        return add(field, value, PropertyType.EQUAL);
    }

    public static Property orEqual(String field, Object value) {
        if (StringUtils.isBlank(field)) return null;
        if (HqlGenBaseUtil.isEmpty(value)) return null;
        return addOr(field, value, PropertyType.EQUAL);
    }


    public static Property notEqual(String field, Object value) {
        if (StringUtils.isBlank(field)) return null;
        if (HqlGenBaseUtil.isEmpty(value)) return null;
        return add(field, value, PropertyType.NOT_EQUAL);
    }


    public static Property orNotEqual(String field, Object value) {
        if (StringUtils.isBlank(field)) return null;
        if (HqlGenBaseUtil.isEmpty(value)) return null;
        return addOr(field, value, PropertyType.NOT_EQUAL);
    }


    public static Property lt(String field, Comparable value) {
        if (StringUtils.isBlank(field)) return null;
        if (HqlGenBaseUtil.isEmpty(value)) return null;
        return add(field, value, PropertyType.LESS);
    }


    public static Property lt$eq(String field, Comparable value) {
        if (StringUtils.isBlank(field)) return null;
        if (HqlGenBaseUtil.isEmpty(value)) return null;
        return add(field, value, PropertyType.LESS_EQUAL);
    }


    public static Property gt(String field, Comparable value) {
        if (StringUtils.isBlank(field)) return null;
        if (HqlGenBaseUtil.isEmpty(value)) return null;
        return add(field, value, PropertyType.GREATER);
    }


    public static Property gt$eq(String field, Comparable value) {
        if (StringUtils.isBlank(field)) return null;
        if (HqlGenBaseUtil.isEmpty(value)) return null;
        return add(field, value, PropertyType.GREATER_EQUAL);
    }


    public static Property orLt(String field, Comparable value) {
        if (StringUtils.isBlank(field)) return null;
        if (HqlGenBaseUtil.isEmpty(value)) return null;
        return addOr(field, value, PropertyType.LESS);
    }


    public static Property orLt$eq(String field, Comparable value) {
        if (StringUtils.isBlank(field)) return null;
        if (HqlGenBaseUtil.isEmpty(value)) return null;
        return addOr(field, value, PropertyType.LESS_EQUAL);
    }


    public static Property orGt(String field, Comparable value) {
        if (StringUtils.isBlank(field)) return null;
        if (HqlGenBaseUtil.isEmpty(value)) return null;
        return addOr(field, value, PropertyType.GREATER);
    }


    public static Property orGt$eq(String field, Comparable value) {
        if (StringUtils.isBlank(field)) return null;
        if (HqlGenBaseUtil.isEmpty(value)) return null;
        return addOr(field, value, PropertyType.GREATER_EQUAL);
    }

    public static Property in(String field, List value) {
        if (StringUtils.isBlank(field)) return null;
        if (HqlGenBaseUtil.isEmpty(value)) return null;
        return add(field, value, PropertyType.IN);
    }


    public static Property in(String field, Object[] value) {
        if (StringUtils.isBlank(field)) return null;
        if (HqlGenBaseUtil.isEmpty(value)) return null;
        return add(field, Arrays.asList(value), PropertyType.IN);
    }


    public static Property orIn(String field, List value) {
        if (StringUtils.isBlank(field)) return null;
        if (HqlGenBaseUtil.isEmpty(value)) return null;
        return addOr(field, value, PropertyType.IN);
    }


    public static Property orIn(String field, Object[] value) {
        if (StringUtils.isBlank(field)) return null;
        if (HqlGenBaseUtil.isEmpty(value)) return null;
        return addOr(field, Arrays.asList(value), PropertyType.IN);
    }

    public static Property notIn(String field, List value) {
        if (StringUtils.isBlank(field)) return null;
        if (HqlGenBaseUtil.isEmpty(value)) return null;
        return add(field, Arrays.asList(value), PropertyType.NOT_IN);
    }


    public static Property orNotIn(String field, Object[] value) {
        if (StringUtils.isBlank(field)) return null;
        if (HqlGenBaseUtil.isEmpty(value)) return null;
        return addOr(field, Arrays.asList(value), PropertyType.NOT_IN);
    }

    public static Property orNotIn(String field, List value) {
        if (StringUtils.isBlank(field)) return null;
        if (HqlGenBaseUtil.isEmpty(value)) return null;
        return addOr(field, value , PropertyType.NOT_IN);
    }


    public static Property startWith(String field, String value) {
        if (StringUtils.isBlank(field)) return null;
        if (HqlGenBaseUtil.isEmpty(value)) return null;
        return add(field, value + "%", PropertyType.LIKE);
    }


    public static Property orStartWith(String field, String value) {
        if (StringUtils.isBlank(field)) return null;
        if (HqlGenBaseUtil.isEmpty(value)) return null;
        return addOr(field, value + "%", PropertyType.LIKE);
    }


    public static Property endWith(String field, String value) {
        if (StringUtils.isBlank(field)) return null;
        if (HqlGenBaseUtil.isEmpty(value)) return null;
        return add(field, "%" + value, PropertyType.LIKE);
    }


    public static Property orEndWith(String field, String value) {
        if (StringUtils.isBlank(field)) return null;
        if (HqlGenBaseUtil.isEmpty(value)) return null;
        return addOr(field, "%" + value, PropertyType.LIKE);
    }


    public static Property like(String field, String value) {
        if (StringUtils.isBlank(field)) return null;
        if (HqlGenBaseUtil.isEmpty(value)) return null;
        return add(field, "%" + value + "%", PropertyType.LIKE);
    }


    public static Property orLike(String field, String value) {
        if (StringUtils.isBlank(field)) return null;
        if (HqlGenBaseUtil.isEmpty(value)) return null;
        return addOr(field, "%" + value + "%", PropertyType.LIKE);
    }


    public static Property between(String field, Comparable start, Comparable end) {
        if (StringUtils.isBlank(field)) return null;

        if (start == null && end == null) return isNull(field);
        if (start == null) return lt(field, end);
        if (end == null) return gt(field, end);

        BetweenVo vo = new BetweenVo(start, end);
        return add(field, vo, PropertyType.BETWEEN);
    }


    public static Property orBetween(String field, Comparable start, Comparable end) {
        if (StringUtils.isBlank(field)) return null;

        if (start == null && end == null) return orNull(field);
        if (start == null) return orLt(field, end);
        if (end == null) return orGt(field, end);

        BetweenVo vo = new BetweenVo(start, end);
        return addOr(field, vo, PropertyType.BETWEEN);
    }


    public static Property isNull(String field) {
        if (StringUtils.isBlank(field)) return null;
        return add(field, null, PropertyType.NULL);
    }


    public static Property orNull(String field) {
        if (StringUtils.isBlank(field)) return null;
        return addOr(field, null, PropertyType.NULL);
    }


    public static Property notNull(String field) {
        if (StringUtils.isBlank(field)) return null;
        return add(field, null, PropertyType.NOT_NULL);
    }


    public static Property orNotNull(String field) {
        if (StringUtils.isBlank(field)) return null;
        return addOr(field, null, PropertyType.NOT_NULL);
    }


    public static Property isEmpty(String field) {
        if (StringUtils.isBlank(field)) return null;
        return add(field, null, PropertyType.EMPTY);
    }


    public static Property orEmpty(String field) {
        if (StringUtils.isBlank(field)) return null;
        return addOr(field, null, PropertyType.EMPTY);
    }


    public static Property isNotEmpty(String field) {
        if (StringUtils.isBlank(field)) return null;
        return add(field, null, PropertyType.NOT_EMPTY);
    }


    public static Property orNotEmpty(String field) {
        if (StringUtils.isBlank(field)) return null;
        return addOr(field, null, PropertyType.NOT_EMPTY);
    }


}
