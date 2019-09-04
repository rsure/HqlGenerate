package com.zfsoft.vo;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangsh
 * @description 生成sql 时用到的关键属性
 * @date 2019-9-4
 * @Copyright
 */
public class SqlVo {

    /**
     * @description 别名
     */
    private String alias;

    /*
     * @description  select 字段 , 不带别名
     */
    private List<String> select;

    /*
     * @description  groupBy 字段 不带别名
     */
    private List<String> groupBy;

    private List<String> orderBy;

    public SqlVo() {
        this.alias = "t";
        select = new ArrayList<String>();
        groupBy = new ArrayList<String>();
        orderBy = new ArrayList<String>();
    }

    public SqlVo(String alias) {
        if (StringUtils.isBlank(alias)) alias = "t";
        this.alias = alias;
        select = new ArrayList<String>();
        groupBy = new ArrayList<String>();
        orderBy = new ArrayList<String>();
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * @description: 添加select
     */
    public SqlVo selk(String... selcts) {
        if (selcts != null && selcts.length > 0) {
            for (String selct : selcts) {
                this.select.add(selct);
            }
        }
        return this;
    }

    /**
     * @description: 添加select
     */
    public SqlVo selk(List<String> selcts) {
        if (selcts != null && selcts.size() > 0) {
            for (String selct : selcts) {
                this.select.add(selct);
            }
        }
        return this;
    }

    //~~~~~~~~~~~~groupBy~~~~~~~~~~~~~~~~~~

    /**
     * @description: 添加group
     */
    public SqlVo group(List<String> groupBys) {
        if (groupBys != null && groupBys.size() > 0) {
            for (String group : groupBys) {
                this.groupBy.add(group);
            }
        }
        return this;
    }

    /**
     * @description: 添加group
     */
    public SqlVo group(String... groupBys) {
        if (groupBys != null && groupBys.length > 0) {
            for (String group : groupBys) {
                this.groupBy.add(group);
            }
        }
        return this;
    }

    //~~~~~~~~~~~~orderBy~~~~~~~~~~~~~~~~~~

    /**
     * @description: 添加orderBy
     */
    public SqlVo order(List<String> orders) {
        if (orders != null && orders.size() > 0) {
            for (String order : orders) {
                this.orderBy.add(order);
            }
        }
        return this;
    }

    /**
     * @description: 添加orderBy
     */
    public SqlVo order(String... orders) {
        if (orders != null && orders.length > 0) {
            for (String order : orders) {
                this.orderBy.add(order);
            }
        }
        return this;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setSelect(List<String> select) {
        this.select = select;
    }

    public void setGroupBy(List<String> groupBy) {
        this.groupBy = groupBy;
    }

    public void setOrderBy(List<String> orderBy) {
        this.orderBy = orderBy;
    }

    public List<String> getSelect() {
        return select;
    }

    public List<String> getGroupBy() {
        return groupBy;
    }

    public List<String> getOrderBy() {
        return orderBy;
    }

    public String getSelects() {
        if (select == null || select.size() == 0) return "";
        return " select " + getStr(select);
    }

    public String getOrderBys() {
        if (orderBy == null || orderBy.size() == 0) return "";
        return " order by " + getStr(orderBy);
    }

    public String getGroupBys() {
        if (groupBy == null || groupBy.size() == 0) return "";
        return " group by " + getStr(groupBy);
    }

    private String getStr(List<String> strs) {
        String result = "";
        for (int i = 0; i < strs.size(); i++) {
            result += " " + this.alias + "." + strs.get(i).trim() + (i < strs.size() - 1 ? "," : "");
        }
        return result;
    }

}
