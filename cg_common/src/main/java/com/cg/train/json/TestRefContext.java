package com.cg.train.json;

import com.cg.train.annotation.JsonBean;
import com.cg.train.dispatcher.IReload;
import com.cg.train.util.JsonUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @ClassName TestRefContext
 * @Description TestRef上下文
 * @Author craig
 * @Date 2022/10/18 23:51
 * @Version 1.0
 */
@JsonBean(jsonPath = "test.json")
public class TestRefContext implements IReload {
    private Map<Integer, TestRef> REF_MAP = new ConcurrentHashMap<>();
    private List<TestRef> REF_LIST = new CopyOnWriteArrayList<>();

    public TestRef get(int refId) {
        return REF_MAP.get(refId);
    }

    public List<TestRef> getList() {
        return REF_LIST;
    }

    @Override
    public void reload() {
        List<TestRef> refs = JsonUtil.fileBuildToJson("/data/test.json", TestRef.class);
        REF_LIST = new CopyOnWriteArrayList<>(refs);
        Map<Integer, TestRef> map = refs.stream().collect(Collectors.toMap(TestRef::id, p -> p));
        REF_MAP = new ConcurrentHashMap<>(map);
    }
}
