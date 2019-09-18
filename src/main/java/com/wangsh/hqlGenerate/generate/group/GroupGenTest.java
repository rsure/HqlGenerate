package com.wangsh.hqlGenerate.generate.group;

import com.wangsh.hqlGenerate.generate.order.OrderGen;

/**
 * @author wangsh
 * @description
 * @date 2019-9-12
 * @Copyright
 */
class GroupGenTest {
    public static void main(String[] args) {
        try{
            String hql = GroupGen.getInstance(GroupGenTest.class).group("name").group("code").group("age","t2").gengrate().toString();
            System.out.println(hql);

        }catch (Exception e){

        }
    }
}
