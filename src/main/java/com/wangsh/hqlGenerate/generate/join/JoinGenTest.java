package com.wangsh.hqlGenerate.generate.join;

import com.wangsh.hqlGenerate.generate._property.PropertyUtil;

import java.util.Date;

/**
 * @author wangsh
 * @description
 * @date 2019-9-12
 * @Copyright
 */
class JoinGenTest {

    private String thirdOid;

    public String name;

    public String code;

    public Date createDate;

    public int status;

    public long time;

    public static void main(String[] args) {
        JoinGen gen =JoinGen.leftJoin(JoinGenTest.class ,"thirdOid" , JoinGenTest.class.getName() ,"t2" );
        gen.setClasz(JoinGenTest.class);

        gen.add(PropertyUtil.getInstance().like("name", "abc").startWith("code", "GB_").lt("time", System.currentTimeMillis()).between("createDate", new Date(), new Date()).getPropertys());
        try{
            String hql = gen.gengrate().toString();
            System.out.println(
                    hql
            );
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
