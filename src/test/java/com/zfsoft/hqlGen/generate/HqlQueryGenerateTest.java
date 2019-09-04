package com.zfsoft.hqlGen.generate;

import com.zfsoft.vo.BetweenVo;
import com.zfsoft.vo.SqlVo;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * HqlQueryGenerate Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>9�� 4, 2019</pre>
 */
public class HqlQueryGenerateTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: guery(Session session, Class clazz, SqlVo sqlVo, PropertyFilter... params)
     */
    @Test
    public void testGuery() throws Exception {
        //TODO: Test goes here...
    }


    /**
     * Method: getSqlAndsqlParametersWithClasz(String linkType, Class clasz, PropertyFilter params, String alias)
     */
    @Test
    public void testGetQueryAndParams() throws Exception {

        try {
            HqlQueryGenerate gen = new HqlQueryGenerate();
            Method method = gen.getClass().getMethod("getQueryAndParams", Class.class, SqlVo.class, PropertyFilter[].class);
            method.setAccessible(true);

            SqlVo sqlVo = new SqlVo();
            sqlVo.order("height").selk("name").group("name");
            PropertyFilter filter = BeanDefultUtil.getDefultPropertyFilter(TestBean.class);
            filter.put("height", new BetweenVo<Integer>(160, 183));

            Map<String, Object> result = (Map<String, Object>) method.invoke(gen ,TestBean.class , sqlVo, filter);
            Map<String, Object> param = (Map<String, Object>) result.get("sqlParameters");
            System.out.println(result.get("hql"));
            for (Map.Entry<String, Object> entry : param.entrySet()) {
                System.out.println(entry.getKey() + " -- " + entry.getValue().toString());
            }

        } catch (NoSuchMethodException e) {
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        }

    }

} 
