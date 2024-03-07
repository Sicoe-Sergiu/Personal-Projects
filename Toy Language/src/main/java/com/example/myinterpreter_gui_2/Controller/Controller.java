package com.example.myinterpreter_gui_2.Controller;

import com.example.myinterpreter_gui_2.Exceptions.ADTException;
import com.example.myinterpreter_gui_2.Exceptions.ExpressionEvaluationException;
import com.example.myinterpreter_gui_2.Exceptions.StatementExecutionException;
import com.example.myinterpreter_gui_2.Model.ProgramState.ProgramState;
import com.example.myinterpreter_gui_2.Model.Values.RefValue;
import com.example.myinterpreter_gui_2.Model.Values.Value;
import com.example.myinterpreter_gui_2.Repository.IRepository;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {

    IRepository repository;
    boolean displayFlag = false;
    ExecutorService executor;

    public Controller(IRepository repository) {
        this.repository = repository;
    }

    public void setDisplayFlag(boolean val){this.displayFlag = val;}

    public Map<Integer, Value> safeGarbageCollector(List<Integer> symTableAddr, List<Integer> heapTableAddr, Map<Integer,Value> heapTable){
        return heapTable.entrySet().stream()
                .filter(elem -> (symTableAddr.contains(elem.getKey()) || heapTableAddr.contains(elem.getKey())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));}

    public List<Integer> getAddrFromSymTable(Collection<Value> symTableValues){
        return symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {RefValue v1 = (RefValue) v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    public List<Integer> getAddrFromHeapTable(Collection<Value> heapTableValues) {
        return heapTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {RefValue v1 = (RefValue) v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    public void conservativeGarbageCollector(List<ProgramState> programStates) {
        List<Integer> symTableAddresses = Objects.requireNonNull(programStates.stream()
                        .map(p -> getAddrFromSymTable(p.getSymTable().values()))
                        .map(Collection::stream)
                        .reduce(Stream::concat).orElse(null))
                .collect(Collectors.toList());
        programStates.forEach(p -> p.getHeapTable().setContent((HashMap<Integer, Value>) safeGarbageCollector(symTableAddresses, getAddrFromHeapTable(p.getHeapTable().getContent().values()), p.getHeapTable().getContent())));
    }

    public List<ProgramState> removeCompletedPrograms(List<ProgramState> inPrgList) {
        return inPrgList.stream().filter(ProgramState::isNotCompleted).collect(Collectors.toList());
    }

    public void oneStepForAllPrograms(List<ProgramState> programStates) throws InterruptedException{
        programStates.forEach(programState ->{
            try {
                repository.logPrgStateExec(programState);
                display(programState);
            }catch (IOException | ADTException e){
                System.out.println(e.getMessage());

            }
        });

        List<Callable<ProgramState>> callList = programStates.stream()
                .map((ProgramState p) -> (Callable<ProgramState>) (p :: oneStep)).toList();

        List<ProgramState> newProgramList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (ExecutionException | InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                    return null;
                })
                .filter(Objects::nonNull).toList();

        programStates.addAll(newProgramList);

        programStates.forEach(programState -> {
            try {
                repository.logPrgStateExec(programState);
                display(programState);
            } catch (IOException | ADTException e) {
                System.out.println(e.getMessage());
            }
        });

        repository.setProgramStates(programStates);
    }
    public void oneStep() throws InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> programStates = removeCompletedPrograms(repository.getProgramStates());
        oneStepForAllPrograms(programStates);
        conservativeGarbageCollector(programStates);
        executor.shutdownNow();
    }
    public void allSteps() throws ExpressionEvaluationException, ADTException, StatementExecutionException, IOException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> programStates = removeCompletedPrograms(repository.getProgramStates());
        while(programStates.size() > 0){
            conservativeGarbageCollector(programStates);
            oneStepForAllPrograms(programStates);
            programStates = removeCompletedPrograms(repository.getProgramStates());
        }
        executor.shutdownNow();
        repository.setProgramStates(programStates);
    }

    private void display(ProgramState programState){
        if (displayFlag){
            System.out.println(programState.toString());
        }
    }
    public List<ProgramState> getProgramStates(){return this.repository.getProgramStates(); }
    public void setProgramStates(List<ProgramState> programStates) {this.repository.setProgramStates(programStates);}

}
