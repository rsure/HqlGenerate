package com.zfsoft.hqlGen.generate;

import com.zfsoft.hqlGen.annotation.BaseBeanFieldDefultParam;

/**
 * @author wangsh
 * @description
 * @date 2019-9-4
 * @Copyright
 */
public class TestBean {

    private String name;
    private String age;
    @BaseBeanFieldDefultParam("sexxxx")
    private String sex;

    private String hobby;

    private int height;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

}
