package com.technologies.mobile.testapp.domain.entities;

import java.util.Collection;
import java.util.Iterator;

public interface IList<T> extends Iterable<T> {

    void add(T t);

    void add(int index, T t);

    void addAll(Collection<? extends T> collection);

    T get(int index);

    int size();
}
