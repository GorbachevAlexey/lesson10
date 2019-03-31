package ru.sbt.javaschool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class Streams<T> {
    private List<T> collection;

    public Streams(List<T> list) {
        this.collection = new ArrayList<T>(list);
    }

    public int size() {
        return collection.size();
    }

    public static <T> Streams<T> of(List<T> list) {
        return new Streams<>(list);
    }

    public Streams<T> filter(Predicate<? super T> predicate) {
        List<T> newList = new ArrayList<>();
        int count = this.collection.size();
        for (int i = 0; i < count; i++) {
            if (predicate.test(this.collection.get(i)))
                newList.add(this.collection.get(i));
        }
        return new Streams<>(newList);
    }

    public <R> Streams<R> transform(Function<? super T, ? extends R> mapper) {
        List<R> newList = new ArrayList<>();
        for (T t : collection) {
            newList.add(mapper.apply(t));
        }
        return new Streams<>(newList);
    }

    public <K, V> Map<K, V> toMap(Function<? super T, ? extends K> mapperKey, Function<? super T, ? extends V> mapperValue) {
        Map<K, V> map = new HashMap<>();
        for (T t : collection) {
            map.put(mapperKey.apply(t), mapperValue.apply(t));
        }
        return map;
    }
}
