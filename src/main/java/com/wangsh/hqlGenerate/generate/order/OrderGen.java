package com.wangsh.hqlGenerate.generate.order;

import com.wangsh.hqlGenerate.helper.Helper;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangsh
 * @description
 * @date 2019-9-6
 * @Copyright
 */
public class OrderGen implements Helper {

    private Class clasz;

    private String fieldAlies;
    private List<String> orders;

    public OrderGen() {
        orders = new ArrayList<>();
    }

    public static OrderGen getInstance(){
        OrderGen gen = new OrderGen();
        return gen;
    }

    public OrderGen asc(String order){
        return asc(order,alies);
    }
    public OrderGen desc(String order){
        return desc(order,alies);
    }
    public OrderGen asc(String order,String fieldAlies){
        if(StringUtils.isNotBlank(order)){
            if(StringUtils.isNotBlank(fieldAlies)){
                order = fieldAlies+"."+order;
            }
            orders.add(order.trim());
        }
        return this;
    }
    public OrderGen desc(String order,String fieldAlies){
        if(StringUtils.isNotBlank(order)){
            if(StringUtils.isNotBlank(fieldAlies)){
                order = fieldAlies+"."+order;
            }
            orders.add(order.trim() + " DESC ");
        }

        return this;
    }

    @Override
    public StringBuffer gengrate( ) throws Exception {
        StringBuffer orderhql = new StringBuffer("");
        if(!this.orders.isEmpty()){
            orderhql.append(" ORDER BY ");
            for (String order : orders) {
                orderhql.append(order).append(",");
            }
            return new StringBuffer(orderhql.substring(0,orderhql.lastIndexOf(",")));
        }
        return orderhql;
    }

    @Override
    public Class getClasz() {
        return clasz;
    }

    @Override
    public void setClasz(Class clasz) {
        this.clasz = clasz;
    }
}
