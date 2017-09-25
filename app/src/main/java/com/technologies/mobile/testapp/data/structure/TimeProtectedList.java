package com.technologies.mobile.testapp.data.structure;

import com.technologies.mobile.testapp.domain.entities.IList;
import com.technologies.mobile.testapp.domain.models.News;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class TimeProtectedList<T extends News> implements IList<T> {

    private List<T> list;

    public TimeProtectedList(List<T> list) {
        this.list = list;
    }

    @Override
    public void add(T t) {
        if (list.isEmpty() || list.get(list.size() - 1).getUnixTime() > t.getUnixTime()) {
            list.add(t);
        }
    }

    @Override
    public void add(int index, T t) {
        if (list.isEmpty() || list.get(index).getUnixTime() < t.getUnixTime()) {
            list.add(index, t);
        }
    }

    @Override
    public void addAll(Collection<? extends T> collection) {
        for (T t : collection) {
            add(t);
        }
    }

    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
