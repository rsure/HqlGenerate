package com.wangsh.hqlGenerate.generate.select;

import com.wangsh.hqlGenerate.generate.group.GroupGen;

/**
 * @author wangsh
 * @description
 * @date 2019-9-12
 * @Copyright
 */
class SelectGenTest {
    public static void main(String[] args) {
        try{
            String hql = SelectGen.getInstance(SelectGenTest.class).select("name").select("code").select("age","t2").gengrate().toString();
            System.out.println(hql);
        }catch (Exception e){

        }
    }
}
