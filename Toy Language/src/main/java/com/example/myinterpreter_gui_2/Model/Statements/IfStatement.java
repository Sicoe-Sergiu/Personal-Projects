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

public class IfStatement implements IStatement{
    Expression expression;
    IStatement thenStatement;
    IStatement elseStatement;

    public IfStatement(Expression expression, IStatement thenStatement, IStatement elseStatement) {
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public String toString() {
        return String.format("IF(%s)THEN{%s}ELSE{%s}", expression.toString(), thenStatement.toString(), elseStatement.toString());
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        Value result = expression.eval(state.getSymTable(), state.getHeapTable());
        if (result instanceof BoolValue boolResult) {
            IStatement statement;
            if (boolResult.getValue()) {
                statement = thenStatement;
            } else {
                statement = elseStatement;
            }
            MyIStack<IStatement> stack = state.getExeStack();
            stack.push(statement);
            state.setExeStack(stack);
            return null;

        } else {
            throw new StatementExecutionException("Conditional expression is not a boolean.");
        }
    }

    @Override
    public IStatement deepCopy() {
        return new IfStatement(expression.deepCopy(), thenStatement.deepCopy(), elseStatement.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String,Type> typeEnv) throws ExpressionEvaluationException, ADTException, StatementExecutionException {
        Type typeExp = expression.typeCheck(typeEnv);
        if (typeExp.equals(new BoolType())) {
            thenStatement.typeCheck(typeEnv.deepCopy());
            elseStatement.typeCheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else
            throw new StatementExecutionException("The condition of IF does not have the type Bool.");
    }
}
