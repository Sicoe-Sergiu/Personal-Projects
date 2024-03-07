package com.example.myinterpreter_gui_2.Model.ADTs;

import java.util.List;

public interface MyIList<T> {
    void add(T element);
    List<T> getList();
    String toString();
}
