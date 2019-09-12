package com.wangsh.hqlGenerate.generate.select;

import com.wangsh.hqlGenerate.generate.group.GroupGen;
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
public class SelectGen implements Helper {
    private Class clasz;
    private String fieldAlies;
    private List<String> selects;


    public SelectGen(Class clasz) {
        selects = new ArrayList<>();
    }

    public static SelectGen getInstance(Class clasz){
        SelectGen gen = new SelectGen(clasz);
        return gen;
    }

    public SelectGen select(String field){
        return select(field,alies);
    }

    public SelectGen select(String field , String fieldAlies){
        if(StringUtils.isNotBlank(field)){
            this.selects.add(fieldAlies+"."+field);
        }
        return this;
    }

    @Override
    public StringBuffer gengrate( ) throws Exception {
        StringBuffer select = new StringBuffer("");
        if(!this.selects.isEmpty()){
            select.append(" Select ");
            for (String group : selects) {
                select.append(group).append(",");
            }
            return new StringBuffer(select.substring(0,select.lastIndexOf(",")));
        }
        return select;
    }

    public StringBuffer getDefultSelect(){
        return new StringBuffer("SELECT "+alies+".* ");
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
