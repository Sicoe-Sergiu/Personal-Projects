package com.example.myinterpreter_gui_2.Model.Statements;

import com.example.myinterpreter_gui_2.Exceptions.ADTException;
import com.example.myinterpreter_gui_2.Exceptions.ExpressionEvaluationException;
import com.example.myinterpreter_gui_2.Exceptions.StatementExecutionException;
import com.example.myinterpreter_gui_2.Model.ADTs.MyIDictionary;
import com.example.myinterpreter_gui_2.Model.ADTs.MyIHeap;
import com.example.myinterpreter_gui_2.Model.Expressions.Expression;
import com.example.myinterpreter_gui_2.Model.ProgramState.ProgramState;
import com.example.myinterpreter_gui_2.Model.Types.RefType;
import com.example.myinterpreter_gui_2.Model.Types.Type;
import com.example.myinterpreter_gui_2.Model.Values.RefValue;
import com.example.myinterpreter_gui_2.Model.Values.Value;

public class HeapAllocationStatement implements IStatement{
    String varName;
    Expression expression;

    public HeapAllocationStatement(String varName, Expression expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap heapTable = state.getHeapTable();
        if (!symTable.isDefined(varName))
            throw new StatementExecutionException(String.format("%s is not in the symbol table", varName));

        Value varValue = symTable.lookUp(varName);
        if (!(varValue.getType() instanceof RefType))
            throw new StatementExecutionException(String.format("%s in not a RefType", varName));

        Value evaluated = expression.eval(symTable, heapTable);
        Type locationType =((RefValue) varValue).getLocationType();
        if (!locationType.equals(evaluated.getType()))
            throw new StatementExecutionException(String.format("%s not of %s", varName, evaluated.getType()));

        int newPosition = heapTable.add(evaluated);
        symTable.put(varName, new RefValue(newPosition, locationType));
        state.setSymTable(symTable);
        state.setHeapTable(heapTable);
        return null;
    }

    @Override
    public String toString() {
        return String.format("New(%s, %s)", varName, expression);
    }


    @Override
    public IStatement deepCopy() {
        return new HeapAllocationStatement(varName, expression.deepCopy());
    }


    @Override
    public MyIDictionary<String,Type> typeCheck(MyIDictionary<String,Type> typeEnv) throws ADTException, ExpressionEvaluationException, StatementExecutionException {
        Type typeVar = typeEnv.lookUp(varName);
        Type typeExp = expression.typeCheck(typeEnv);
        if (typeVar.equals(new RefType(typeExp)))
            return typeEnv;
        else
            throw new StatementExecutionException("NEW statement: right hand side and left hand side have different types.");
    }
}
