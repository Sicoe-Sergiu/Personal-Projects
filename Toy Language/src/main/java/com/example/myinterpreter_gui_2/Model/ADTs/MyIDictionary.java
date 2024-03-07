package com.example.myinterpreter_gui_2.Model.ADTs;

import com.example.myinterpreter_gui_2.Exceptions.ADTException;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface MyIDictionary<T1, T2> {
    boolean isDefined(T1 key);
    void put(T1 key, T2 value);
    T2 lookUp(T1 key) throws ADTException;
    void update(T1 key, T2 value)throws ADTException;
    Collection<T2> values();
    Set<T1> keySet();
    Map<T1, T2> getContent();
    void remove(T1 keya)throws ADTException;
    String toString();

    MyIDictionary<T1, T2> deepCopy() throws ADTException;

}
