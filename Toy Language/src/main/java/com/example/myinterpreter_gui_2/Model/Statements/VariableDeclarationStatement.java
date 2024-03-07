package com.example.myinterpreter_gui_2.Model.Statements;

import com.example.myinterpreter_gui_2.Exceptions.ADTException;
import com.example.myinterpreter_gui_2.Exceptions.ExpressionEvaluationException;
import com.example.myinterpreter_gui_2.Exceptions.StatementExecutionException;
import com.example.myinterpreter_gui_2.Model.ADTs.MyIDictionary;
import com.example.myinterpreter_gui_2.Model.ProgramState.ProgramState;
import com.example.myinterpreter_gui_2.Model.Types.Type;
import com.example.myinterpreter_gui_2.Model.Values.Value;

public class VariableDeclarationStatement implements IStatement{

    String name;
    Type type;

    public VariableDeclarationStatement(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("%s %s", type.toString(), name);
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        if (symTable.isDefined(name))
            throw new StatementExecutionException("Variable " + name + " is already declared in the Symbol Table.");
        symTable.put(name, type.defaultValue());
        state.setSymTable(symTable);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new VariableDeclarationStatement(name, type);
    }

    @Override
    public MyIDictionary<String,Type> typeCheck(MyIDictionary<String,Type> typeEnv) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        typeEnv.put(name, type);
        return typeEnv;
    }


}
