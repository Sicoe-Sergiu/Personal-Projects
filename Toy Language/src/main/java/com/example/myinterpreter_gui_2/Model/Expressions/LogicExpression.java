package com.example.myinterpreter_gui_2.Model.Expressions;

import com.example.myinterpreter_gui_2.Exceptions.ADTException;
import com.example.myinterpreter_gui_2.Exceptions.ExpressionEvaluationException;
import com.example.myinterpreter_gui_2.Model.ADTs.MyIDictionary;
import com.example.myinterpreter_gui_2.Model.ADTs.MyIHeap;
import com.example.myinterpreter_gui_2.Model.Types.BoolType;
import com.example.myinterpreter_gui_2.Model.Types.Type;
import com.example.myinterpreter_gui_2.Model.Values.BoolValue;
import com.example.myinterpreter_gui_2.Model.Values.Value;

import java.util.Objects;

public class LogicExpression implements Expression{

    Expression expression1;
    Expression expression2;
    String operation;

    public LogicExpression(String operation, Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operation = operation;
    }
    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIHeap heapTable) throws ExpressionEvaluationException, ADTException {
        Value value1, value2;
        value1 = this.expression1.eval(symTable, heapTable);
        if (value1.getType().equals(new BoolType())) {
            value2 = this.expression2.eval(symTable, heapTable);
            if (value2.getType().equals(new BoolType())) {
                BoolValue bool1 = (BoolValue) value1;
                BoolValue bool2 = (BoolValue) value2;
                boolean b1, b2;
                b1 = bool1.getValue();
                b2 = bool2.getValue();
                if (Objects.equals(this.operation, "and")) {
                    return new BoolValue(b1 && b2);
                } else if (Objects.equals(this.operation, "or")) {
                    return new BoolValue(b1 || b2);
                }
            } else {
                throw new ExpressionEvaluationException("Second operand is not a boolean.");
            }
        } else {
            throw new ExpressionEvaluationException("First operand is not a boolean.");
        }
        return null;
    }

    @Override
    public String toString() {
        return expression1.toString() + " " + operation + " " + expression2.toString();
    }

    @Override
    public Expression deepCopy() {
        return new LogicExpression(operation, expression1.deepCopy(), expression2.deepCopy());
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws ExpressionEvaluationException, ADTException {
        Type type1, type2;
        type1 = expression1.typeCheck(typeEnv);
        type2 = expression2.typeCheck(typeEnv);
        if (type1.equals(new BoolType())) {
            if (type2.equals(new BoolType())) {
                return new BoolType();
            } else
                throw new ExpressionEvaluationException("Second operand is not a boolean.");
        } else
            throw new ExpressionEvaluationException("First operand is not a boolean.");
    }
}
