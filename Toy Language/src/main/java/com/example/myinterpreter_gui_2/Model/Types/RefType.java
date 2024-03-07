package com.example.myinterpreter_gui_2.Model.Types;

import com.example.myinterpreter_gui_2.Model.Values.RefValue;
import com.example.myinterpreter_gui_2.Model.Values.Value;

public class RefType implements Type{

    Type inner;

    public RefType(Type inner) {
        this.inner = inner;
    }

    public Type getInner() {
        return inner;
    }

    @Override
    public boolean equals(Type anotherType) {
        if (anotherType instanceof RefType)
            return inner.equals(((RefType) anotherType).getInner());
        else
            return false;
    }

    @Override
    public String toString() { return String.format("ref(%s)", inner); }

    @Override
    public Value defaultValue() {
        return new RefValue(0,inner);
    }

    @Override
    public Type deepCopy() {
        return new RefType(inner.deepCopy());
    }
}
