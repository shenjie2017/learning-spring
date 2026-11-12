package com.blue.springboot.util;

import com.blue.springboot.utils.MD5Util;
import org.junit.jupiter.api.Test;

class MD5Test {

    @Test
    public void testGen(){
        System.out.println(MD5Util.getMD5("123456"));
    }
}
