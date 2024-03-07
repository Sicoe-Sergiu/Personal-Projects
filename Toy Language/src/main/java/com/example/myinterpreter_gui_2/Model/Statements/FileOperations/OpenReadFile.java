package com.example.myinterpreter_gui_2.Model.Statements.FileOperations;

import com.example.myinterpreter_gui_2.Exceptions.ADTException;
import com.example.myinterpreter_gui_2.Exceptions.ExpressionEvaluationException;
import com.example.myinterpreter_gui_2.Exceptions.StatementExecutionException;
import com.example.myinterpreter_gui_2.Model.ADTs.MyIDictionary;
import com.example.myinterpreter_gui_2.Model.Expressions.Expression;
import com.example.myinterpreter_gui_2.Model.ProgramState.ProgramState;
import com.example.myinterpreter_gui_2.Model.Statements.IStatement;
import com.example.myinterpreter_gui_2.Model.Types.StringType;
import com.example.myinterpreter_gui_2.Model.Types.Type;
import com.example.myinterpreter_gui_2.Model.Values.StringValue;
import com.example.myinterpreter_gui_2.Model.Values.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenReadFile implements IStatement {

    Expression expression;

    public OpenReadFile(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return String.format("OpenReadFile(%s)", expression.toString());
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        Value result = expression.eval(state.getSymTable(), state.getHeapTable());
        if (result.getType().equals(new StringType())){
            StringValue fileName = (StringValue) result;
            MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
            if (!fileTable.isDefined(fileName.getValue())){
                BufferedReader bufferedReader;
                try {
                    bufferedReader = new BufferedReader(new FileReader(fileName.getValue()));
                }catch (FileNotFoundException e){
                    throw new StatementExecutionException(String.format("File %s could not be open.", fileName.getValue()));
                }
                fileTable.put(fileName.getValue(), bufferedReader);
                state.setFileTable(fileTable);
            }else {
                throw new StatementExecutionException(String.format("File %s is already open.", fileName.getValue()));
            }
        }
        else {
            throw new StatementExecutionException(String.format("%s is not a String.", expression.toString()));
        }

        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new OpenReadFile(expression.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        if (expression.typeCheck(typeEnv).equals(new StringType()))
            return typeEnv;
        else
            throw new StatementExecutionException("OpenReadFile requires a String expression.");
    }
}
