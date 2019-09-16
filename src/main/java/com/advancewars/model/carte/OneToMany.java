package com.advancewars.model.carte;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OneToMany<K, V> {


    private final ListMultimap<K, V> keysToValues = ArrayListMultimap.create();

    private final Map<V, K> valuesToKeys = new HashMap();

    public List<V> getValues(K key) {
        return keysToValues.get(key);
    }

    public K getKeys(V value) {
        return valuesToKeys.get(value);
    }

    public void put(K key, V value) {

        keysToValues.put(key, value);
        valuesToKeys.put(value, key);
    }

    public void putAll(K key, Iterable<? extends V> values) {
        for (V value : values) {
            put(key, value);
        }
    }

    public boolean containsValue() {
        return !keysToValues.isEmpty();
    }


    public void remove(K distance, V point) {
        keysToValues.remove(distance, point);
        valuesToKeys.remove(point);
    }
}
