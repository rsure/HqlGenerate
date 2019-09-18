package com.wangsh.hqlGenerate.generate.where;

import com.wangsh.hqlGenerate.generate.condition.ConditionGen;

/**
 * @author wangsh
 * @description
 * @date 2019-9-6
 * @Copyright
 */
public class WhereGen extends ConditionGen {

    public WhereGen(Class clasz) {
        super(clasz);
    }

    public static WhereGen getInstance(Class clasz) {
        WhereGen helper = new WhereGen(clasz);
        return helper;
    }

    @Override
    public StringBuffer gengrate( ) throws Exception {
        if(super.conditions != null && !super.conditions.isEmpty()){
            return new StringBuffer(" where ").append(super.gengrate());
        }
        return new StringBuffer(" where 1=1 ");
    }

}
