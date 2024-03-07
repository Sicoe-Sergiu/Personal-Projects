package com.example.myinterpreter_gui_2.Repository;

import com.example.myinterpreter_gui_2.Exceptions.ADTException;
import com.example.myinterpreter_gui_2.Model.ProgramState.ProgramState;

import java.io.IOException;
import java.util.List;

public interface IRepository {

    //ProgramState getCurrentState();
    void addProgram(ProgramState program);
    void logPrgStateExec(ProgramState programState) throws ADTException, IOException;

    List<ProgramState> getProgramStates();
    void setProgramStates(List<ProgramState> programStates);
}
