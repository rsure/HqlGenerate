package com.zfsoft.hqlGen.generate;

import com.zfsoft.hqlGen.enumnation.PropertyValType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangsh
 * @description sql vo , 当 内部语句为 内联sql 的时候 使用 , 当 PropertyType 为  IN , NOT_IN , EXIST  , NOT_EXIST 时 会判断此类型 , 其他时候传此类型无效
 * @date 2019-8-29
 * @Copyright
 */
public abstract class PropertySql {
    // 内部插入的sql
    private String sql;

    // sql 名
    private Map<String, Object> sqlParamNames;


    public PropertySql put(String name , Object param  ){
        this.sqlParamNames.put(name , param );
        return this;
    }

    public PropertySql(String sql) {
        this.sql = sql;
        sqlParamNames = new HashMap<String, Object>();
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Map<String, Object> getSqlParamNames() {
        return sqlParamNames;
    }

    public void setSqlParamNames(Map<String, Object> sqlParamNames) {
        this.sqlParamNames = sqlParamNames;
    }
}
