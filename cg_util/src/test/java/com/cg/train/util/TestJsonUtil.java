package com.cg.train.util;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @Description:
 * @Author: Craig
 * @Date: 2022/10/18 15:04
 * @Version: 1.0.0
 */
public class TestJsonUtil {
    @Test
    public void TestFileBuildToJson() {
        String path = "/data/test.json";
        List<TestRef> testRefs = JsonUtil.fileBuildToJson(path, TestRef.class);
        assert !testRefs.isEmpty();
    }

    @Data
    class TestRef{
        private int id;
        private String name;
        private int sex;
    }
}
