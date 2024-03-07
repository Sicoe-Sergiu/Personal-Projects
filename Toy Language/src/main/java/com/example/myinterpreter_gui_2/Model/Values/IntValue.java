package com.example.myinterpreter_gui_2.Model.Values;

import com.example.myinterpreter_gui_2.Model.Types.IntType;
import com.example.myinterpreter_gui_2.Model.Types.Type;

public class IntValue implements Value{

    int value;

    public IntValue(int value) {
        this.value = value;
    }

    public int getValue(){ return value;}

    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public String toString(){ return String.format("%d", this.value); }

    @Override
    public Value deepCopy() {
        return new IntValue(value);
    }

    public boolean equals(Value anotherValue){
        if (!(anotherValue instanceof IntValue))
            return false;
        return this.value == ((IntValue) anotherValue).value;
    }
}
