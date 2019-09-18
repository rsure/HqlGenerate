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

    private List<String> orders;

    public OrderGen(Class clasz) {
        orders = new ArrayList<>();
    }

    public static OrderGen getInstance(Class clasz){
        OrderGen gen = new OrderGen(clasz);
        return gen;
    }

    /*
     * @description 添加 order
     * @param order 排序字段
     * @param fieldAlies 排序字段 所属表别名
     *
     */
    private OrderGen order(String order,String fieldAlies ,String desc){
        if(StringUtils.isNotBlank(order)){
            if(StringUtils.isNotBlank(fieldAlies)){
                order = fieldAlies+"."+order.trim();
            }
            orders.add(order + " " +desc);
        }
        return this;
    }

    /*
     * @description 添加自定义排序字段 , order字段为默认表
     */
    public OrderGen order(String order){
        return order(order , alies , null);
    }

    /*
     * @description 添加自定义排序字段 , order 需要加上别名
     */
    public OrderGen customOrder(String order){
        if(StringUtils.isNotBlank(order)){
            orders.add(order.trim());
        }
        return this;
    }

    public OrderGen asc(String order){
        return asc(order,alies);
    }
    public OrderGen desc(String order){
        return desc(order,alies);
    }
    public OrderGen asc(String order,String fieldAlies){
        return order(order , fieldAlies ,null);
    }
    public OrderGen desc(String order,String fieldAlies){
        return order(order , fieldAlies ,"DESC");
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
}
