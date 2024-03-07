package com.example.myinterpreter_gui_2.Model.Expressions;

import com.example.myinterpreter_gui_2.Exceptions.ADTException;
import com.example.myinterpreter_gui_2.Exceptions.ExpressionEvaluationException;
import com.example.myinterpreter_gui_2.Model.ADTs.MyIDictionary;
import com.example.myinterpreter_gui_2.Model.ADTs.MyIHeap;
import com.example.myinterpreter_gui_2.Model.Types.Type;
import com.example.myinterpreter_gui_2.Model.Values.Value;

public class ValueExpression implements Expression{

    Value value;

    public ValueExpression(Value value) {
        this.value = value;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIHeap heapTable) throws ExpressionEvaluationException, ADTException {
        return value;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    @Override
    public Expression deepCopy() {
        return new ValueExpression(value);
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws ExpressionEvaluationException, ADTException {
        return value.getType();
    }
}
