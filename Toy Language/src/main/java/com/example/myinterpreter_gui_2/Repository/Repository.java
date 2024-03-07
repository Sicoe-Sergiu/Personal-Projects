package com.example.myinterpreter_gui_2.Repository;

import com.example.myinterpreter_gui_2.Exceptions.ADTException;
import com.example.myinterpreter_gui_2.Model.ProgramState.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{

    List<ProgramState> programStates;
    int currentPosition;
    String logFilePath;
    public Repository(ProgramState program, String logFilePath) {
        this.logFilePath = logFilePath;
        this.programStates = new ArrayList<>();
        this.currentPosition = 0;
        this.addProgram(program);
    }

    @Override
    public List<ProgramState> getProgramStates() {
        return programStates;
    }

    @Override
    public void setProgramStates(List<ProgramState> programStates) {
        this.programStates = programStates;
    }

//    @Override
//    public ProgramState getCurrentState() {
//        return this.programStates.get(this.currentPosition);
//    }

    @Override
    public void addProgram(ProgramState program) {
        this.programStates.add(program);
    }

    @Override
    public void logPrgStateExec(ProgramState programState) throws ADTException, IOException {
        PrintWriter logFile;
        logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        logFile.println(programState.programStateToString());
        logFile.close();
    }
}
