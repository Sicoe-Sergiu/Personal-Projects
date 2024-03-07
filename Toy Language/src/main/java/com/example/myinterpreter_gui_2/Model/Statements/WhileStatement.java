package com.example.myinterpreter_gui_2.Model.Statements;

import com.example.myinterpreter_gui_2.Exceptions.ADTException;
import com.example.myinterpreter_gui_2.Exceptions.ExpressionEvaluationException;
import com.example.myinterpreter_gui_2.Exceptions.StatementExecutionException;
import com.example.myinterpreter_gui_2.Model.ADTs.MyIDictionary;
import com.example.myinterpreter_gui_2.Model.ADTs.MyIStack;
import com.example.myinterpreter_gui_2.Model.Expressions.Expression;
import com.example.myinterpreter_gui_2.Model.ProgramState.ProgramState;
import com.example.myinterpreter_gui_2.Model.Types.BoolType;
import com.example.myinterpreter_gui_2.Model.Types.Type;
import com.example.myinterpreter_gui_2.Model.Values.BoolValue;
import com.example.myinterpreter_gui_2.Model.Values.Value;

public class WhileStatement implements IStatement{
    Expression expression;
    IStatement statement;

    public WhileStatement(Expression expression, IStatement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public String toString() {
        return String.format("while(%s){%s}", expression, statement);
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        Value value = expression.eval(state.getSymTable(), state.getHeapTable());
        MyIStack<IStatement> exeStack = state.getExeStack();
        if (!value.getType().equals(new BoolType()))
            throw new StatementExecutionException(String.format("%s is not a boolean", value));
        BoolValue boolValue = (BoolValue) value;

        if (boolValue.getValue()){
            exeStack.push(this);
            exeStack.push(statement);
        }
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new WhileStatement(expression.deepCopy(), statement.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String,Type> typeEnv) throws ExpressionEvaluationException, ADTException, StatementExecutionException {
        Type typeExp = expression.typeCheck(typeEnv);
        if (typeExp.equals(new BoolType())) {
            statement.typeCheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else
            throw new StatementExecutionException("The condition of WHILE does not have the type Bool.");
    }
}
