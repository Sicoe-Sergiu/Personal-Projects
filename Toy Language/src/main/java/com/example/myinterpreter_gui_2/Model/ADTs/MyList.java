package com.example.myinterpreter_gui_2.Model.ADTs;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T>{

    List<T> list;

    public MyList(){list = new ArrayList<>();}

    @Override
    public void add(T element) {
        list.add(element);
    }

    @Override
    public List<T> getList() {
        return list;
    }

    @Override
    public String toString() {
        return this.list.toString();
    }
}
