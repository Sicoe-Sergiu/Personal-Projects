package com.example.myinterpreter_gui_2.Model.Values;

import com.example.myinterpreter_gui_2.Model.Types.StringType;
import com.example.myinterpreter_gui_2.Model.Types.Type;

public class StringValue implements Value{

    String value;

    public StringValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public String toString() { return "\"" + this.value + "\""; }

    @Override
    public Value deepCopy() {
        return new StringValue(value);
    }


    public boolean equals(Value anotherValue){
        if(!(anotherValue instanceof StringValue))
            return false;

        return this.value.equals(((StringValue) anotherValue).value);
    }
}
