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
import java.io.IOException;

public class CloseReadFile implements IStatement {

    Expression expression;

    public CloseReadFile(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return String.format("CloseReadFile(%s)", expression.toString());
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        Value result = this.expression.eval(state.getSymTable(), state.getHeapTable());
        if (result.getType().equals(new StringType())){
            StringValue fileName = (StringValue) result;
            MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
            if (fileTable.isDefined(fileName.getValue())){
                BufferedReader bufferedReader = fileTable.lookUp(fileName.getValue());
                try {
                    bufferedReader.close();
                }catch (IOException e){
                    throw new StatementExecutionException(String.format("Could not close the file %s.", fileName));
                }
                fileTable.remove(fileName.getValue());
                state.setFileTable(fileTable);
            }else {
                throw new StatementExecutionException(String.format("The file table doesn't contain the file %s.", fileName));
            }
        }else{
            throw new StatementExecutionException(String.format("%s is not a String.", expression.toString()));
        }

        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new CloseReadFile(expression.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        if (expression.typeCheck(typeEnv).equals(new StringType()))
            return typeEnv;
        else
            throw new StatementExecutionException("CloseReadFile requires a String expression.");
    }
}
