package com.cg.train.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONReader;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName JsonUtil
 * @Description json工具类
 * @Author craig
 * @Date 2022/10/17 23:21
 * @Version 1.0
 */
@Slf4j
public class JsonUtil {
    public static <T> List<T> fileBuildToJson(String path, Class<T> classes) {
        List<T> jsonList = new ArrayList<>();
        URL resource = JsonUtil.class.getResource(path);
        if (resource == null) {
            log.warn("配置文件不存在,{}", path);
            return jsonList;
        }

        File file = new File(resource.getFile());
        try {
            FileReader fileReader = new FileReader(file);
            JSONReader jsonReader = JSONReader.of(fileReader);
            jsonReader.startArray();
            while (!jsonReader.isEnd()) {
                if (jsonReader.nextIfMatch(']')) {
                    jsonReader.endArray();
                    continue;
                }
                String readString = jsonReader.readString();
                log.debug("==="+readString);
                T t = JSONObject.parseObject(readString, classes);
                jsonList.add(t);
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return jsonList;
    }

}
