package com.wangsh.hqlGenerate.generate.condition;

import com.wangsh.hqlGenerate.generate._property.PropertyUtil;

import java.util.Date;

/**
 * @author wangsh
 * @description
 * @date 2019-9-10
 * @Copyright
 */
public class ConditionGenTest {

    public String name;

    public String code;

    public Date createDate;

    public int status;

    public long time;


    public static void main(String[] args) {
        ConditionGen gen = ConditionGen.getInstance();
        gen.setClasz(ConditionGenTest.class);
        gen.add(PropertyUtil.getInstance().like("name", "abc").startWith("code", "GB_").lt("time", System.currentTimeMillis()).between("createDate", new Date(), new Date()).getPropertys());
        gen.add(PropertyUtil.getInstance().equal("code", "HK_").orNotEmpty("code").groupOrProperties());
        try {
            String hql = gen.gengrate().toString();
            System.out.println(
                    hql
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
