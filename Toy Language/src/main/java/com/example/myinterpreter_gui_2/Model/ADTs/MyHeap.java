package com.example.myinterpreter_gui_2.Model.ADTs;

import com.example.myinterpreter_gui_2.Exceptions.ADTException;
import com.example.myinterpreter_gui_2.Model.Values.Value;

import java.util.HashMap;
import java.util.Set;

public class MyHeap implements MyIHeap{
    HashMap<Integer, Value> heap;
    Integer freeLocationValue;

    public MyHeap(){
        heap = new HashMap<>();
        freeLocationValue = 1;
    }

    public int newValue(){
        freeLocationValue += 1;
        while (freeLocationValue == 0 || heap.containsKey(freeLocationValue)){
            freeLocationValue += 1;
        }
        return freeLocationValue;
    }

    @Override
    public HashMap<Integer, Value> getContent() {
        return heap;
    }

    @Override
    public void setContent(HashMap<Integer, Value> newMap) {
        heap = newMap;
    }

    @Override
    public int add(Value value) {
        heap.put(freeLocationValue, value);
        Integer toReturn = freeLocationValue;
        freeLocationValue = newValue();
        return toReturn;
    }

    @Override
    public void update(Integer position, Value value) throws ADTException {
        if (!heap.containsKey(position))
            throw new ADTException(String.format("%d is not present in the heap", position));
        heap.put(position, value);
    }

    @Override
    public Value get(Integer position) throws ADTException {
        if (!heap.containsKey(position))
            throw new ADTException(String.format("%d is not present in the heap", position));
        return heap.get(position);
    }

    @Override
    public boolean containsKey(Integer position) {
        return heap.containsKey(position);
    }

    @Override
    public Set<Integer> keySet() {
        return heap.keySet();
    }

    @Override
    public String toString() {
        return this.heap.toString();
    }
}
