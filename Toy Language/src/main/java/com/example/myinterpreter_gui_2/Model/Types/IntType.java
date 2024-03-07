package com.example.myinterpreter_gui_2.Model.Types;

import com.example.myinterpreter_gui_2.Model.Values.IntValue;
import com.example.myinterpreter_gui_2.Model.Values.Value;

public class IntType implements Type{
    @Override
    public boolean equals(Type anotherType) {
        return anotherType instanceof IntType;
    }

    @Override
    public Value defaultValue() {
        return new IntValue(0);
    }

    @Override
    public Type deepCopy() {
        return new IntType();
    }

    @Override
    public String toString(){return "int"; }
}
