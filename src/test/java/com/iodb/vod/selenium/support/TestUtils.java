
package com.iodb.vod.selenium.support;

import java.nio.charset.Charset;
import java.util.Random;

public class TestUtils {
    
    public static String testString(){
        byte[] array = new byte[15];
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }
    
}
