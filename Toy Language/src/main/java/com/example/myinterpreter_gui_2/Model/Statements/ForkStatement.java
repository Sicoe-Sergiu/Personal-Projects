package com.example.myinterpreter_gui_2.Model.Statements;

import com.example.myinterpreter_gui_2.Exceptions.ADTException;
import com.example.myinterpreter_gui_2.Exceptions.ExpressionEvaluationException;
import com.example.myinterpreter_gui_2.Exceptions.StatementExecutionException;
import com.example.myinterpreter_gui_2.Model.ADTs.MyDictionary;
import com.example.myinterpreter_gui_2.Model.ADTs.MyIDictionary;
import com.example.myinterpreter_gui_2.Model.ADTs.MyIStack;
import com.example.myinterpreter_gui_2.Model.ADTs.MyStack;
import com.example.myinterpreter_gui_2.Model.ProgramState.ProgramState;
import com.example.myinterpreter_gui_2.Model.Types.Type;
import com.example.myinterpreter_gui_2.Model.Values.Value;

import java.util.Map;

public class ForkStatement implements IStatement{
    IStatement statement;

    public ForkStatement(IStatement statement) {
        this.statement = statement;
    }

    @Override
    public String toString() {
        return String.format("Fork(%s)", statement.toString());
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        MyIStack<IStatement> newStack = new MyStack<>();
        newStack.push(statement);

        MyIDictionary<String, Value> newSymTable = new MyDictionary<>();
        for (Map.Entry<String, Value> entry : state.getSymTable().getContent().entrySet()){
            newSymTable.put(entry.getKey(), entry.getValue().deepCopy());
        }

        return new ProgramState(newStack, newSymTable, state.getOut(), state.getFileTable(), state.getHeapTable());
    }

    @Override
    public IStatement deepCopy() {
        return new ForkStatement(statement.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        statement.typeCheck(typeEnv.deepCopy());
        return typeEnv;
    }
}
