package com.example.myinterpreter_gui_2.Model.Statements.FileOperations;

import com.example.myinterpreter_gui_2.Exceptions.ADTException;
import com.example.myinterpreter_gui_2.Exceptions.ExpressionEvaluationException;
import com.example.myinterpreter_gui_2.Exceptions.StatementExecutionException;
import com.example.myinterpreter_gui_2.Model.ADTs.MyIDictionary;
import com.example.myinterpreter_gui_2.Model.Expressions.Expression;
import com.example.myinterpreter_gui_2.Model.ProgramState.ProgramState;
import com.example.myinterpreter_gui_2.Model.Statements.IStatement;
import com.example.myinterpreter_gui_2.Model.Types.IntType;
import com.example.myinterpreter_gui_2.Model.Types.StringType;
import com.example.myinterpreter_gui_2.Model.Types.Type;
import com.example.myinterpreter_gui_2.Model.Values.IntValue;
import com.example.myinterpreter_gui_2.Model.Values.StringValue;
import com.example.myinterpreter_gui_2.Model.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStatement {

    Expression expression;
    String varName;

    public ReadFile(Expression expression, String varName) {
        this.expression = expression;
        this.varName = varName;
    }

    @Override
    public String toString() {
        return String.format("ReadFile(%s, %s)", expression.toString(), varName);
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();

        if (symTable.isDefined(varName)){
            Value value = symTable.lookUp(varName);
            if (value.getType().equals(new IntType())){
                Value result = this.expression.eval(state.getSymTable(), state.getHeapTable());
                if (result.getType().equals(new StringType())){
                    StringValue castValue = (StringValue) result;

                    if (fileTable.isDefined(castValue.getValue())){
                        BufferedReader bufferedReader = fileTable.lookUp(castValue.getValue());
                        try {
                            String line = bufferedReader.readLine();
                            if (line == null)
                                line = "0";
                            symTable.put(varName, new IntValue(Integer.parseInt(line)));
                        }catch (IOException e){
                            throw new StatementExecutionException(String.format("Could not read from file %s.", castValue));
                        }
                    }else{
                        throw new StatementExecutionException(String.format("The file table doesn't contain the file %s.", castValue));
                    }
                }else {
                    throw new StatementExecutionException(String.format("%s is not a String.", expression.toString()));
                }
            }else {
                throw new StatementExecutionException(String.format("Variable %s is not an integer.", value));
            }
        }else {
            throw new StatementExecutionException(String.format("Variable %s is not defined in the SymbolTable.", varName));
        }
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new ReadFile(expression.deepCopy(), varName);
    }


    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        if (expression.typeCheck(typeEnv).equals(new StringType()))
            if (typeEnv.lookUp(varName).equals(new IntType()))
                return typeEnv;
            else
                throw new StatementExecutionException("ReadFile requires an int as its variable parameter.");
        else
            throw new StatementExecutionException("ReadFile requires a String as its expression parameter.");
    }
}
