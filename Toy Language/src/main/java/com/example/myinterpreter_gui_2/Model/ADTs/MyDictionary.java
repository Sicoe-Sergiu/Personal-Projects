package com.example.myinterpreter_gui_2.Model.ADTs;

import com.example.myinterpreter_gui_2.Exceptions.ADTException;

import java.util.*;

public class MyDictionary<T1,T2> implements MyIDictionary<T1,T2>{

    HashMap<T1,T2> dictionary;

    public MyDictionary() {
        this.dictionary = new HashMap<T1, T2>();
    }

    @Override
    public boolean isDefined(T1 key) {
        return this.dictionary.containsKey(key);
    }

    @Override
    public void put(T1 key, T2 value) {
        this.dictionary.put(key, value);
    }

    @Override
    public T2 lookUp(T1 key) throws ADTException {
        if(!isDefined(key))
            throw new ADTException(key + " is not defined.");
        return this.dictionary.get(key);
    }

    @Override
    public void update(T1 key, T2 value) throws ADTException {
        if(!isDefined(key))
            throw new ADTException(key + " is not defined.");
        this.dictionary.put(key, value);
    }

    @Override
    public Collection<T2> values() {
        return this.dictionary.values();
    }

    @Override
    public Set<T1> keySet() {
        return this.dictionary.keySet();
    }

    @Override
    public Map<T1, T2> getContent() {
        return dictionary;
    }

    @Override
    public void remove(T1 key) throws ADTException {
        if(!isDefined(key))
            throw new ADTException(key + " is not defined.");
        this.dictionary.remove(key);
    }

    @Override
    public String toString() {
        return this.dictionary.toString();
    }

    @Override
    public MyIDictionary<T1, T2> deepCopy() throws ADTException {
        MyIDictionary<T1, T2> toReturn = new MyDictionary<>();
        for (T1 key: keySet())
            toReturn.put(key, lookUp(key));
        return toReturn;
    }
}
