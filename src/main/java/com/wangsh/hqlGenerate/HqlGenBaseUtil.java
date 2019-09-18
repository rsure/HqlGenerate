package com.wangsh.hqlGenerate;

import com.wangsh.hqlGenerate.annotation.BaseBeanFieldDefultParam;
import com.wangsh.hqlGenerate.generate._property.Property;
import com.wangsh.hqlGenerate.generate._property.PropertyUtil;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangsh
 * @description
 * @date 2019-9-17
 * @Copyright
 */
public class HqlGenBaseUtil {
    public static List<Property> getBeanDefultProperty(Class clasz){
        PropertyUtil util = PropertyUtil.getInstance();
        if (clasz != null) {
            Field[] declaredFields = clasz.getDeclaredFields();
            Field[] var3 = declaredFields;
            int var4 = declaredFields.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Field declaredField = var3[var5];
                if (declaredField.isAnnotationPresent(BaseBeanFieldDefultParam.class)) {
                    BaseBeanFieldDefultParam val = (BaseBeanFieldDefultParam)declaredField.getAnnotation(BaseBeanFieldDefultParam.class);
                    util.equal(declaredField.getName(), val.value());
                }
            }
        }
        return util.getPropertys();
    }

    public static boolean isEmpty(Object value){
        if(value !=null){
            if(value instanceof List){
                return ((List)value).size() == 0;
            }if(value instanceof Object[]){
                return ((Object[])value).length == 0;
            }if(value instanceof CharSequence){
                return StringUtils.isBlank(value.toString());
            }else{
                return false;
            }
        }else{
            return  true;
        }
    }

    public static boolean isNotEmpty(Object value){
        return ! HqlGenBaseUtil.isEmpty(value);
    }

    public static void main(String[] args) {
        List<String> testList = null;
        System.out.println("testList : "+HqlGenBaseUtil.isEmpty(testList));
        testList = new ArrayList<>();
        System.out.println("testList : "+HqlGenBaseUtil.isEmpty(testList));
        testList.add("12123");
        System.out.println("testList : "+HqlGenBaseUtil.isEmpty(testList));

        String [] testArray = null;
        System.err.println("testArray : "+HqlGenBaseUtil.isEmpty(testArray));
        testArray = new String[]{};
        System.err.println("testArray : "+HqlGenBaseUtil.isEmpty(testArray));
        testArray = new String[]{"123"};
        System.err.println("testArray : "+HqlGenBaseUtil.isEmpty(testArray));

        String  testString = null;
        System.out.println("testString : "+HqlGenBaseUtil.isEmpty(testString));
        testString = "";
        System.out.println("testString : "+HqlGenBaseUtil.isEmpty(testString));
        testString = "  ";
        System.out.println("testString : "+HqlGenBaseUtil.isEmpty(testString));
        testString = " 123 ";
        System.out.println("testString : "+HqlGenBaseUtil.isEmpty(testString));
    }
}
