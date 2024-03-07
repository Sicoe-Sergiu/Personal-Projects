package com.example.myinterpreter_gui_2.Model.Expressions;

import com.example.myinterpreter_gui_2.Exceptions.ADTException;
import com.example.myinterpreter_gui_2.Exceptions.ExpressionEvaluationException;
import com.example.myinterpreter_gui_2.Model.ADTs.MyIDictionary;
import com.example.myinterpreter_gui_2.Model.ADTs.MyIHeap;
import com.example.myinterpreter_gui_2.Model.Types.Type;
import com.example.myinterpreter_gui_2.Model.Values.Value;

public interface Expression {

    Value eval(MyIDictionary<String, Value> symTable, MyIHeap heapTable) throws ExpressionEvaluationException, ADTException;
    String toString();
    Expression deepCopy();
    Type typeCheck(MyIDictionary<String, Type> typeEnv) throws ExpressionEvaluationException, ADTException;

}
