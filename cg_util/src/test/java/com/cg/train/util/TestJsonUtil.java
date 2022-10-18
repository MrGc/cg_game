package com.cg.train.util;

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

    static class TestRef{
        private int id;
        private String name;
        private int sex;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }
    }
}
