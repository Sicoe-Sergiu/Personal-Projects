package com.example.myinterpreter_gui_2.Model.Statements;

import com.example.myinterpreter_gui_2.Exceptions.ADTException;
import com.example.myinterpreter_gui_2.Exceptions.ExpressionEvaluationException;
import com.example.myinterpreter_gui_2.Exceptions.StatementExecutionException;
import com.example.myinterpreter_gui_2.Model.ADTs.MyIDictionary;
import com.example.myinterpreter_gui_2.Model.ADTs.MyIList;
import com.example.myinterpreter_gui_2.Model.Expressions.Expression;
import com.example.myinterpreter_gui_2.Model.ProgramState.ProgramState;
import com.example.myinterpreter_gui_2.Model.Types.Type;
import com.example.myinterpreter_gui_2.Model.Values.Value;

public class PrintStatement implements IStatement{

    Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return String.format("Print(%s)", expression.toString());
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        MyIList<Value> out = state.getOut();
        out.add(expression.eval(state.getSymTable(), state.getHeapTable()));
        state.setOut(out);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new PrintStatement(expression.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String,Type> typeEnv) throws ExpressionEvaluationException, ADTException {
        expression.typeCheck(typeEnv);
        return typeEnv;
    }
}
