package com.example.myinterpreter_gui_2.Model.Types;

import com.example.myinterpreter_gui_2.Model.Values.BoolValue;
import com.example.myinterpreter_gui_2.Model.Values.Value;

public class BoolType implements Type{
    @Override
    public boolean equals(Type anotherType) {
        return anotherType instanceof BoolType;
    }

    @Override
    public Value defaultValue() {
        return new BoolValue(false);
    }

    @Override
    public Type deepCopy() {
        return new BoolType();
    }

    @Override
    public String toString(){return "bool"; }
}
