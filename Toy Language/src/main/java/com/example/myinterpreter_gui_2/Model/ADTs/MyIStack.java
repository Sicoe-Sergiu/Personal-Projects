package com.example.myinterpreter_gui_2.Model.ADTs;

import com.example.myinterpreter_gui_2.Exceptions.ADTException;

import java.util.List;

public interface MyIStack<T> {
    T pop() throws ADTException;
    void push(T element);
    boolean isEmpty();
    List<T> getReversed();
    String toString();
}
