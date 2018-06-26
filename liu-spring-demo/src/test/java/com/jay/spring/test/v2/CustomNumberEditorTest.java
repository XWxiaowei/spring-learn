package com.jay.spring.test.v2;

import com.jay.spring.bean.propertyeditors.CustomNumberEditor;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by xiang.wei on 2018/6/27
 *
 * @author xiang.wei
 */
public class CustomNumberEditorTest {
    @Test
    public void testConvertString() {
        CustomNumberEditor editor = new CustomNumberEditor(Integer.class,true);

        editor.setAsText("3");
        Object value = editor.getValue();
        Assert.assertTrue(value instanceof Integer);
        Assert.assertEquals(3, ((Integer)editor.getValue()).intValue());


        editor.setAsText("");
        Assert.assertTrue(editor.getValue() == null);


        try{
            editor.setAsText("3.1");

        }catch(IllegalArgumentException e){
            return ;
        }
        Assert.fail();

    }

}
