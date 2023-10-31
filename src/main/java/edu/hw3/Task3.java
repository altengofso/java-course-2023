package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Task3 {
    private Task3() {
    }

    public static Map<Object, Integer> freqDict(List<Object> list) {
        Map<Object, Integer> map = new HashMap<>();
        for (var object : list) {
            map.put(object, map.getOrDefault(object, 0) + 1);
        }
        return map;
    }
}
