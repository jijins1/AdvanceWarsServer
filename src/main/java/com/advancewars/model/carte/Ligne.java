package com.advancewars.model.carte;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Ligne<T> {
    private Map<Integer, T> map;
    private Integer size;

    public Ligne(List<T> ligne) {
        map = new LinkedHashMap<>();
        size = 0;
        for (T value : ligne) {
            add(value);
        }
    }

    public void add(T t) {
        set(size(), t);
        size++;
    }

    public T get(Integer index) {
        return this.map.get(index);
    }

    public T set(Integer index, T value) {
        if (value == null) {
            return this.map.remove(index);
        } else {
            return this.map.put(index, value);
        }
    }

    public Integer size() {
        return this.size;
    }

    @Deprecated
    public List<T> getList() {
        return null;
    }
}
