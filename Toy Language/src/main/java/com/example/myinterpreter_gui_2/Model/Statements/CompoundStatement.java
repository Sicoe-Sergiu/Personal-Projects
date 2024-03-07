package com.example.myinterpreter_gui_2.Model.Statements;

import com.example.myinterpreter_gui_2.Exceptions.ADTException;
import com.example.myinterpreter_gui_2.Exceptions.ExpressionEvaluationException;
import com.example.myinterpreter_gui_2.Exceptions.StatementExecutionException;
import com.example.myinterpreter_gui_2.Model.ADTs.MyIDictionary;
import com.example.myinterpreter_gui_2.Model.ADTs.MyIStack;
import com.example.myinterpreter_gui_2.Model.ProgramState.ProgramState;
import com.example.myinterpreter_gui_2.Model.Types.Type;

public class CompoundStatement implements IStatement{
    IStatement first, second;

    public CompoundStatement(IStatement first, IStatement second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return String.format("(%s ; %s)", first.toString(), second.toString());
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        MyIStack<IStatement> stack = state.getExeStack();
        stack.push(second);
        stack.push(first);
        state.setExeStack(stack);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new CompoundStatement(first.deepCopy(), second.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String,Type> typeEnv) throws ExpressionEvaluationException, ADTException, StatementExecutionException {
        return second.typeCheck(first.typeCheck(typeEnv));
    }
}
