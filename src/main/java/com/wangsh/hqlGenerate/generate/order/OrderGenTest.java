package com.wangsh.hqlGenerate.generate.order;

/**
 * @author wangsh
 * @description
 * @date 2019-9-12
 * @Copyright
 */
public class OrderGenTest {
    public static void main(String[] args) {
        try{
            String hql = OrderGen.getInstance().asc("name").desc("code").desc("age","t2").gengrate().toString();
            System.out.println(hql);

        }catch (Exception e){

        }
    }
}
