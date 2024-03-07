package com.example.myinterpreter_gui_2.Model.Types;

import com.example.myinterpreter_gui_2.Model.Values.Value;

public interface Type {
    boolean equals(Type anotherType);
    String toString();
    Value defaultValue();
    Type deepCopy();
}
