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

public class HeapWritingStatement implements IStatement{

    String varName;
    Expression expression;

    public HeapWritingStatement(String varName, Expression expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public String toString() { return String.format("WriteHeap(%s, %s)", varName, expression);}

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap heapTable = state.getHeapTable();
        if(!symTable.isDefined(varName))
            throw new StatementExecutionException(String.format("%s is not present in the symbol table", varName));

        Value value = symTable.lookUp(varName);
        if(!(value instanceof RefValue refValue))
            throw new StatementExecutionException(String.format("%s is not of RefType", value));

        Value evaluated = expression.eval(symTable, heapTable);
        if (!evaluated.getType().equals(refValue.getLocationType()))
            throw new StatementExecutionException(String.format("%s is not of %s", evaluated, refValue.getLocationType()));
        heapTable.update(refValue.getAddress(), evaluated);
        state.setHeapTable(heapTable);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new HeapWritingStatement(varName, expression.deepCopy());
    }


    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String,Type> typeEnv) throws ADTException, ExpressionEvaluationException, StatementExecutionException {
        Type typeVar = typeEnv.lookUp(varName);
        Type typeExp = expression.typeCheck(typeEnv);
        if (typeVar.equals(new RefType(typeExp)))
            return typeEnv;
        else
            throw new StatementExecutionException("WriteHeap statement: right hand side and left hand side have different types.");
    }
}
