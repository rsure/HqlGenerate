package com.wangsh.hqlGenerate.generate.group;

import com.wangsh.hqlGenerate.generate.order.OrderGen;
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
public class GroupGen implements Helper {

    private Class clasz;
    private String fieldAlies;
    private List<String> groups;

    public GroupGen(Class clasz) {
        groups = new ArrayList<>();
    }

    public static GroupGen getInstance(Class clasz){
        GroupGen gen = new GroupGen(clasz);
        return gen;
    }

    public GroupGen group(String field){
       return group(field,alies);
    }
    /**
     * @description 自定义的分组字段 需要带上别名
     */
    public GroupGen customGroup(String field){
        if(StringUtils.isNotBlank(field)){
            this.groups.add(field);
        }
        return this;
    }

    public GroupGen group(String field , String fieldAlies){
        if(StringUtils.isNotBlank(field)){
            this.groups.add(fieldAlies+"."+field);
        }
        return this;
    }

    @Override
    public StringBuffer gengrate( ) throws Exception {
        StringBuffer grouphql = new StringBuffer("");
        if(!this.groups.isEmpty()){
            grouphql.append(" GROUP BY ");
            for (String group : groups) {
                grouphql.append(group).append(",");
            }
            return new StringBuffer(grouphql.substring(0,grouphql.lastIndexOf(",")));
        }
        return grouphql;
    }

}
