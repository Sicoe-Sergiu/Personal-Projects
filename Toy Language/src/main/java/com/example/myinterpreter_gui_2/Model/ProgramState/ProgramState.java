package com.example.myinterpreter_gui_2.Model.ProgramState;

import com.example.myinterpreter_gui_2.Exceptions.ADTException;
import com.example.myinterpreter_gui_2.Exceptions.ExpressionEvaluationException;
import com.example.myinterpreter_gui_2.Exceptions.StatementExecutionException;
import com.example.myinterpreter_gui_2.Model.ADTs.MyIDictionary;
import com.example.myinterpreter_gui_2.Model.ADTs.MyIHeap;
import com.example.myinterpreter_gui_2.Model.ADTs.MyIList;
import com.example.myinterpreter_gui_2.Model.ADTs.MyIStack;
import com.example.myinterpreter_gui_2.Model.Statements.IStatement;
import com.example.myinterpreter_gui_2.Model.Values.Value;

import java.io.BufferedReader;
import java.util.List;

public class ProgramState {

    private MyIStack<IStatement> exeStack;
    private MyIDictionary<String, Value> symTable;
    private MyIList<Value> out;
    IStatement originalProgram;
    MyIDictionary<String, BufferedReader> fileTable;
    MyIHeap heapTable;
    int id;
    private static int lastId = 0;

    public ProgramState(MyIStack<IStatement> exeStack, MyIDictionary<String, Value> symTable, MyIList<Value> out, MyIDictionary<String, BufferedReader> fileTable, MyIHeap heapTable, IStatement program) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.originalProgram = program.deepCopy();
        this.exeStack.push(originalProgram);
        this.fileTable = fileTable;
        this.heapTable = heapTable;
        this.id = setId();
    }

    public ProgramState(MyIStack<IStatement> exeStack, MyIDictionary<String, Value> symTable, MyIList<Value> out, MyIDictionary<String, BufferedReader> fileTable, MyIHeap heapTable) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heapTable = heapTable;
        this.id = setId();
    }

    public synchronized static int setId(){
        lastId++;
        return lastId;
    }
    public void setExeStack(MyIStack<IStatement> newStack){ exeStack = newStack;}
    public void setSymTable(MyIDictionary<String, Value> newSimTable){ symTable = newSimTable; }
    public void setOut(MyIList<Value> newOut){out = newOut; }

    public int getId() {
        return id;
    }

    public void setFileTable(MyIDictionary<String, BufferedReader> newfileTable) {
        this.fileTable = newfileTable;
    }

    public void setHeapTable(MyIHeap heapTable) {
        this.heapTable = heapTable;
    }

    public MyIStack<IStatement> getExeStack() {
        return exeStack;
    }

    public MyIDictionary<String, Value> getSymTable() {
        return symTable;
    }
    public MyIList<Value> getOut() { return out; }
    public MyIDictionary<String, BufferedReader> getFileTable() {
        return fileTable;
    }

    public MyIHeap getHeapTable() {
        return heapTable;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nExecution stack: \n" + exeStack.getReversed() + "\nSymbol table: \n" + symTable.toString() + "\nOutput list: \n" + out.toString()+ "\nHeap table: \n" + heapTable.toString();
    }

    public String exeStackToString() {
        StringBuilder exeStackStringBuilder = new StringBuilder();
        List<IStatement> stack = exeStack.getReversed();
        for (IStatement statement: stack) {
            exeStackStringBuilder.append(statement.toString()).append("\n");
        }
        return exeStackStringBuilder.toString();
    }

    public String symTableToString() throws ADTException {
        StringBuilder symTableStringBuilder = new StringBuilder();
        for (String key: symTable.keySet()) {
            symTableStringBuilder.append(String.format("%s -> %s\n", key, symTable.lookUp(key).toString()));
        }
        return symTableStringBuilder.toString();
    }

    public String outToString() {
        StringBuilder outStringBuilder = new StringBuilder();
        for (Value elem: out.getList()) {
            outStringBuilder.append(String.format("%s\n", elem.toString()));
        }
        return outStringBuilder.toString();
    }

    public String fileTableToString(){
        StringBuilder fileTableStringBuilder = new StringBuilder();
        for (String key : fileTable.keySet()){
            fileTableStringBuilder.append(String.format("%s\n", key));
        }
        return fileTableStringBuilder.toString();
    }

    public String heapTableToString() throws ADTException {
        StringBuilder heapStringBuilder = new StringBuilder();
        for (int key: heapTable.keySet()) {
            heapStringBuilder.append(String.format("%d -> %s\n", key, heapTable.get(key)));
        }
        return heapStringBuilder.toString();
    }

    public String programStateToString() throws ADTException {
        return "ID: " + id + "\nExecution stack: \n" + exeStackToString() + "Symbol table: \n" + symTableToString() + "Output list: \n" + outToString() + "File table:\n" + fileTableToString() + "Heap table:\n" + heapTableToString();
    }

    //threads

    public boolean isNotCompleted(){
        return !exeStack.isEmpty();
    }

    public ProgramState oneStep() throws ADTException, ExpressionEvaluationException, StatementExecutionException {

        if (exeStack.isEmpty()){
            throw new StatementExecutionException("Program state stack is empty.");
        }
        IStatement currentStatement = exeStack.pop();
        return currentStatement.execute(this);
    }

}
