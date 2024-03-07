package com.example.myinterpreter_gui_2.Model.Expressions;

import com.example.myinterpreter_gui_2.Exceptions.ADTException;
import com.example.myinterpreter_gui_2.Exceptions.ExpressionEvaluationException;
import com.example.myinterpreter_gui_2.Model.ADTs.MyIDictionary;
import com.example.myinterpreter_gui_2.Model.ADTs.MyIHeap;
import com.example.myinterpreter_gui_2.Model.Types.BoolType;
import com.example.myinterpreter_gui_2.Model.Types.IntType;
import com.example.myinterpreter_gui_2.Model.Types.Type;
import com.example.myinterpreter_gui_2.Model.Values.BoolValue;
import com.example.myinterpreter_gui_2.Model.Values.IntValue;
import com.example.myinterpreter_gui_2.Model.Values.Value;

import java.util.Objects;

public class RelationalExpression implements Expression{

    Expression expression1;
    Expression expression2;
    String operator;

    public RelationalExpression(String operator, Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operator = operator;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIHeap heapTable) throws ExpressionEvaluationException, ADTException {
        Value value1, value2;
        value1 = this.expression1.eval(symTable, heapTable);
        if (value1.getType().equals(new IntType())) {
            value2 = this.expression2.eval(symTable, heapTable);
            if (value2.getType().equals(new IntType())) {
                IntValue val1 = (IntValue) value1;
                IntValue val2 = (IntValue) value2;
                int v1, v2;
                v1 = val1.getValue();
                v2 = val2.getValue();
                if (Objects.equals(this.operator, "<"))
                    return new BoolValue(v1 < v2);
                else if (Objects.equals(this.operator, "<="))
                    return new BoolValue(v1 <= v2);
                else if (Objects.equals(this.operator, "=="))
                    return new BoolValue(v1 == v2);
                else if (Objects.equals(this.operator, "!="))
                    return new BoolValue(v1 != v2);
                else if (Objects.equals(this.operator, ">"))
                    return new BoolValue(v1 > v2);
                else if (Objects.equals(this.operator, ">="))
                    return new BoolValue(v1 >= v2);
            } else
                throw new ExpressionEvaluationException("Second operand is not an integer.");
        } else
            throw new ExpressionEvaluationException("First operand in not an integer.");
        return null;
    }

    @Override
    public String toString() {
        return this.expression1.toString() + " " + this.operator + " " + this.expression2.toString();
    }

    @Override
    public Expression deepCopy() {
        return new RelationalExpression(operator, expression1.deepCopy(), expression2.deepCopy());
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws ExpressionEvaluationException, ADTException {
        Type type1, type2;
        type1 = expression1.typeCheck(typeEnv);
        type2 = expression1.typeCheck(typeEnv);
        if (type1.equals(new IntType())) {
            if (type2.equals(new IntType())) {
                return new BoolType();
            } else
                throw new ExpressionEvaluationException("Second operand is not a boolean.");
        } else
            throw new ExpressionEvaluationException("First operand is not a boolean.");
    }
}
