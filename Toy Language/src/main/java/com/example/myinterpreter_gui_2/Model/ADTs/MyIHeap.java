package com.example.myinterpreter_gui_2.Model.ADTs;

import com.example.myinterpreter_gui_2.Exceptions.ADTException;
import com.example.myinterpreter_gui_2.Model.Values.Value;

import java.util.HashMap;
import java.util.Set;

public interface MyIHeap {
    HashMap<Integer, Value> getContent();
    void setContent(HashMap<Integer, Value> newMap);
    int add(Value value);
    void update(Integer position, Value value) throws ADTException;
    Value get(Integer position) throws ADTException;
    boolean containsKey(Integer position);
    Set<Integer> keySet();
    String toString();
}
