package com.example.myinterpreter_gui_2.Model.Values;

import com.example.myinterpreter_gui_2.Model.Types.RefType;
import com.example.myinterpreter_gui_2.Model.Types.Type;

public class RefValue implements Value{

    int address;
    Type locationType;

    public RefValue(int address, Type locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddress() {
        return address;
    }

    public Type getLocationType() {
        return locationType;
    }

    @Override
    public Type getType(){
        return new RefType(locationType);
    }

    @Override
    public String toString() {
        return String.format("(%d, %s)", address, locationType);
    }

    @Override
    public Value deepCopy() {
        return new RefValue(address, locationType.deepCopy());
    }
}
