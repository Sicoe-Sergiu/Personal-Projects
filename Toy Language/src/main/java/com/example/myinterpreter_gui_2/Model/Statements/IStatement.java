package com.example.myinterpreter_gui_2.Model.Statements;

import com.example.myinterpreter_gui_2.Exceptions.ADTException;
import com.example.myinterpreter_gui_2.Exceptions.ExpressionEvaluationException;
import com.example.myinterpreter_gui_2.Exceptions.StatementExecutionException;
import com.example.myinterpreter_gui_2.Model.ADTs.MyIDictionary;
import com.example.myinterpreter_gui_2.Model.ProgramState.ProgramState;
import com.example.myinterpreter_gui_2.Model.Types.Type;

public interface IStatement {
    String toString();
    ProgramState execute(ProgramState state)throws StatementExecutionException, ExpressionEvaluationException, ADTException;
    IStatement deepCopy();
    MyIDictionary<String, Type> typeCheck(MyIDictionary<String,Type> typeEnv) throws StatementExecutionException, ExpressionEvaluationException, ADTException;

}
