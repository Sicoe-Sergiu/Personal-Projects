package com.example.myinterpreter_gui_2.Model.Expressions;

import com.example.myinterpreter_gui_2.Exceptions.ADTException;
import com.example.myinterpreter_gui_2.Exceptions.ExpressionEvaluationException;
import com.example.myinterpreter_gui_2.Model.ADTs.MyIDictionary;
import com.example.myinterpreter_gui_2.Model.ADTs.MyIHeap;
import com.example.myinterpreter_gui_2.Model.Types.RefType;
import com.example.myinterpreter_gui_2.Model.Types.Type;
import com.example.myinterpreter_gui_2.Model.Values.RefValue;
import com.example.myinterpreter_gui_2.Model.Values.Value;

public class HeapReadingExpression implements Expression{
    Expression expression;

    public HeapReadingExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIHeap heapTable) throws ExpressionEvaluationException, ADTException {
        Value value = expression.eval(symTable, heapTable);
        if (!(value instanceof RefValue refValue))
            throw new ExpressionEvaluationException(String.format("%s is not a RefType", value));
        return heapTable.get(refValue.getAddress());
    }

    @Override
    public String toString() { return String.format("ReadHeap(%s)", expression); }

    @Override
    public Expression deepCopy() {
        return new HeapReadingExpression(expression.deepCopy());
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws ExpressionEvaluationException, ADTException {
        Type type = expression.typeCheck(typeEnv);
        if (type instanceof RefType refType) {
            return refType.getInner();
        } else
            throw new ExpressionEvaluationException("The rH argument is not a RefType");
    }
}
