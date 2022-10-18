package com.cg.train.util;

import org.junit.jupiter.api.Test;

import java.util.Set;

/**
 * @Description:
 * @Author: Craig
 * @Date: 2022/10/18 14:32
 * @Version: 1.0.0
 */
public class TestFileUtil {
    @Test
    public void testGetClasses() {
        Set<Class<?>> classes = FileUtil.getClasses("com.cg.train");
        assert !classes.isEmpty();
    }
}
