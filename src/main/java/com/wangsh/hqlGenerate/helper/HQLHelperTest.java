package com.wangsh.hqlGenerate.helper;

/**
 * @author wangsh
 * @description
 * @date 2019-9-18
 * @Copyright
 */
class HQLHelperTest {

    private String id;
    private String name;
    private String code;
    private String description;
    private int status;


    public static void main(String[] args) {

        String hql = HQLHelper.getInstance(HQLHelperTest.class).selects("id","name","status").customSelects("t2.hehe").leftJoin("code","com.code.User","t2",null).andHql("1=1").startWith("code","123").like("name","芳芳").desc("status").toString();
        System.out.println(hql);

    }
}
