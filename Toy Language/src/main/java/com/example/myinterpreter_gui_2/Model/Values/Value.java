package com.example.myinterpreter_gui_2.Model.Values;

import com.example.myinterpreter_gui_2.Model.Types.Type;

public interface Value {
    Type getType();
    String toString();

    Value deepCopy();
}
