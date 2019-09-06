package com.wangsh.hqlGenerate.util;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import java.lang.reflect.Field;

/**
 * @author wangsh
 * @description
 * @date 2019-9-6
 * @Copyright
 */
public class ClassUtil {
    /**
    　　* @description: 判断字段是否在class 中
    　　* @param
    　　* @return
    　　* @throws
    　　* @author wangsh
    　　* @date 2019-9-6 17:24
    　　*/
    public static Boolean filedInClass(Class clasz, String filedName) {

        if (StringUtils.isBlank(filedName)) return false;

        String fieldInsideName = null;
        if (filedName.indexOf(".") > 0) {
            // 判断是否是内部字段
            fieldInsideName = filedName.substring(filedName.indexOf("."));
            filedName = filedName.substring(0, filedName.indexOf(".") - 1);
        }
        Field field = null;
        try {
            field = clasz.getDeclaredField(filedName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (field != null) {
            if (field.getType().isAnnotationPresent(Entity.class)) {
                return filedInClass(field.getType(), fieldInsideName);
            } else {
                return true;
            }
        }
        return false;
    }
}
