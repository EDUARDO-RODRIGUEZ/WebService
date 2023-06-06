package com.eduardo.app.component;


import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

public class MapperData {
    private Map data;

    private MapperData() {
        this.data = new HashMap();
    }

    public Map build() {
        return this.data;
    }

    public MapperData add(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public static MapperData builder() {
        return new MapperData();
    }
}
