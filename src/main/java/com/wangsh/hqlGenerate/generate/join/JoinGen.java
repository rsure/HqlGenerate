package com.wangsh.hqlGenerate.generate.join;

import com.wangsh.hqlGenerate.generate.condition.ConditionGen;
import com.wangsh.hqlGenerate.helper.Helper;

import java.util.Map;

/**
 * @author wangsh
 * @description 表关联
 * @date 2019-9-6
 * @Copyright
 */
public class JoinGen extends ConditionGen {

    private String field;
    private String withFild;
    private String joinType;
    private String joinAlies;

    private final static String LEFT_JOIN = " LEFT JOIN ";
    private final static String RIGHT_JOIN = " RIGHT JOIN ";
    private final static String INNER_JOIN = " INNER JOIN ";

    private JoinGen(Class clasz ,String field, String withFild, String joinAlies ,String joinType) {
        super(clasz);
        this.field = field;
        this.withFild = withFild;
        this.joinAlies = joinAlies;
        this.joinType = joinType;
    }

    public static JoinGen leftJoin(Class clasz ,String field, String withFild ,String joinAlies) {
        JoinGen gen = new JoinGen(clasz,field, withFild, joinAlies , LEFT_JOIN);
        return gen;
    }

    public static JoinGen rightJoin(Class clasz ,String field, String withFild ,String joinAlies) {
        JoinGen gen = new JoinGen(clasz,field, withFild, joinAlies ,RIGHT_JOIN);
        return gen;
    }

    public static JoinGen innerJoin(Class clasz ,String field, String withFild ,String joinAlies) {
        JoinGen gen = new JoinGen(clasz,field, withFild,  joinAlies ,INNER_JOIN);
        return gen;
    }

    @Override
    public StringBuffer gengrate() throws Exception {
        StringBuffer joinHql = new StringBuffer(this.joinType  + this.withFild + " as "+ joinAlies );
        if(!super.getConditions().isEmpty()){
            joinHql.append(" with ").append(super.gengrate());
        }
        return joinHql;
    }

    public Map<String, Object> getHqlParams() {
        return super.getHqlParams();
    }

}
