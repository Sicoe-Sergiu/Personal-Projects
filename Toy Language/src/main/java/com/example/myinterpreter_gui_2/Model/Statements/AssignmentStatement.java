package com.example.myinterpreter_gui_2.Model.Statements;

import com.example.myinterpreter_gui_2.Exceptions.ADTException;
import com.example.myinterpreter_gui_2.Exceptions.ExpressionEvaluationException;
import com.example.myinterpreter_gui_2.Exceptions.StatementExecutionException;
import com.example.myinterpreter_gui_2.Model.ADTs.MyIDictionary;
import com.example.myinterpreter_gui_2.Model.Expressions.Expression;
import com.example.myinterpreter_gui_2.Model.ProgramState.ProgramState;
import com.example.myinterpreter_gui_2.Model.Types.Type;
import com.example.myinterpreter_gui_2.Model.Values.Value;

public class AssignmentStatement implements IStatement{

    String id;
    Expression expression;

    public AssignmentStatement(String id, Expression expression) {
        this.id = id;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return String.format("%s = %s", id, expression.toString());
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        if (symTable.isDefined(id)){
            Value value = expression.eval(symTable, state.getHeapTable());
            Type typeId = (symTable.lookUp(id)).getType();
            if (value.getType().equals(typeId)){
                symTable.update(id, value);
            }else throw new StatementExecutionException("Declared type of variable " + id + " and type of the assigned expression do not match.");
        }else throw new StatementExecutionException("The used variable " + id + " was not declared before.");

        state.setSymTable(symTable);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new AssignmentStatement(id, expression.deepCopy());
    }

    @Override
    public MyIDictionary<String,Type> typeCheck(MyIDictionary<String,Type> typeEnv) throws ADTException, ExpressionEvaluationException, StatementExecutionException {
        Type typeVar = typeEnv.lookUp(id);
        Type typExp = expression.typeCheck(typeEnv);
        if (typeVar.equals(typExp))
            return typeEnv;
        else
            throw new StatementExecutionException("Assignment: right hand side and left hand side have different types.");
    }
}
