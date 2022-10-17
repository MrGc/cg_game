package com.cg.train.json;

import com.cg.train.annotation.JsonBean;

import java.util.List;
import java.util.Map;

@JsonBean(jsonPath = "/data/test.json")
public record TestJson(int id, String name, List<Integer> list, Map<Integer, Integer> map) implements BaseJson{
}
