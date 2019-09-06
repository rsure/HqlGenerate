package com.wangsh.hqlGenerate.helper.condition;

import com.wangsh.hqlGenerate.helper.Helper;
import com.zfsoft.hqlGen.enumnation.PropertyLinkType;
import com.zfsoft.hqlGen.enumnation.PropertyType;
import com.zfsoft.vo.BetweenVo;
import com.zfsoft.vo.SqlVo;

import java.util.List;

/**
 * @author wangsh
 * @description
 * @date 2019-9-6
 * @Copyright
 */
public interface ConditionHelper extends Helper {

    ConditionHelper groupAnd();
    ConditionHelper groupOr();

    ConditionHelper equal(String field , Object value);
    ConditionHelper orEqual(String field , Object value  );

    ConditionHelper notEqual(String field , Object value);
    ConditionHelper orNotEqual(String field , Object value  );

    ConditionHelper lt(String field , Comparable value);
    ConditionHelper lt$eq(String field , Comparable value);
    ConditionHelper gt(String field , Comparable value);
    ConditionHelper gt$eq(String field , Comparable value);

    ConditionHelper orLt(String field , Comparable value);
    ConditionHelper orLt$eq(String field , Comparable value);
    ConditionHelper orGt(String field , Comparable value);
    ConditionHelper orGt$eq(String field , Comparable value);


    ConditionHelper in(String field , SqlVo sqlVo);
    ConditionHelper orIn(String field , SqlVo sqlVo  );

    ConditionHelper notIn(String field , SqlVo sqlVo);
    ConditionHelper orNotIn(String field , SqlVo sqlVo);

    ConditionHelper in(String field , List value);
    ConditionHelper orIn(String field , Object[] value);
    ConditionHelper in(String field , List value , PropertyLinkType link );
    ConditionHelper orIn(String field , Object[] value , PropertyLinkType link);

    ConditionHelper exist(String field , SqlVo sqlVo);
    ConditionHelper notExist(String field , SqlVo sqlVo);

    ConditionHelper orExist(String field , SqlVo sqlVo);
    ConditionHelper orNotExist(String field , SqlVo sqlVo);


    ConditionHelper notIn(String field , List value);
    ConditionHelper orNotIn(String field , Object[] value);
    ConditionHelper notIn(String field , List value , PropertyLinkType link );
    ConditionHelper orNotIn(String field , Object[] value , PropertyLinkType link);

    ConditionHelper startWith(String field ,String value);
    ConditionHelper orStartWith(String field ,String value );

    ConditionHelper endWith(String field ,String value);
    ConditionHelper orEndWith(String field ,String value , PropertyLinkType link);

    ConditionHelper like(String field ,String value);
    ConditionHelper orLike(String field ,String value );

    ConditionHelper between(String field , BetweenVo vo);
    ConditionHelper orBetween(String field , BetweenVo vo);


    ConditionHelper isNull(String field );
    ConditionHelper orNull(String field );
    ConditionHelper notNull(String field );
    ConditionHelper orNotNull(String field );

    ConditionHelper isEmpty(String field );
    ConditionHelper orEmpty(String field );
    ConditionHelper isNotEmpty(String field );
    ConditionHelper orNotEmpty(String field );



}
