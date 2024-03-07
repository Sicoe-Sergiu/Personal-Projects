package com.example.myinterpreter_gui_2;

import com.example.myinterpreter_gui_2.Controller.Controller;
import com.example.myinterpreter_gui_2.Model.ADTs.MyIDictionary;
import com.example.myinterpreter_gui_2.Model.ADTs.MyIHeap;
import com.example.myinterpreter_gui_2.Model.ProgramState.ProgramState;
import com.example.myinterpreter_gui_2.Model.Statements.IStatement;
import com.example.myinterpreter_gui_2.Model.Values.Value;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProgramExecutorController {
    private Controller controller;
    @FXML
    private TextField noProgramsTextFilled;

    @FXML
    private TableView<Pair<Integer, Value>> heapTableView;
    @FXML
    private TableColumn<Pair<Integer, Value>, Integer> adressHeapColumn;
    @FXML
    private TableColumn<Pair<Integer, Value>, String> ValueHeapColumn;
    @FXML
    private TableView<Pair<String, Value>> symTableTableView;
    @FXML
    private TableColumn<Pair<String, Value>, String> varNameSymColumn;
    @FXML
    private TableColumn<Pair<String, Value>, String> valueSymColumn;

    @FXML
    private ListView<String> outputListView;

    @FXML
    private ListView<String> fileTableListView;

    @FXML
    private ListView<Integer> programStateIDSListView;
    @FXML
    private ListView<String> executionStackListView;

    @FXML
    private Button StepIntoButton;

    public void setController(Controller controller) {
        this.controller = controller;
        populate();
    }

    @FXML
    public void initialize(){
        programStateIDSListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        adressHeapColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        ValueHeapColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue().toString()));

        varNameSymColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey()));
        valueSymColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue().toString()));
    }

    private ProgramState getCurrentProgramState(){
        if (controller.getProgramStates().size() == 0)
            return null;
        else {
            int currentId = programStateIDSListView.getSelectionModel().getSelectedIndex();
            if (currentId == -1)
                return controller.getProgramStates().get(0);
            else
                return controller.getProgramStates().get(currentId);
        }
    }
    private void populate(){
        populateHeapTableView();
        populateSymbolTableView();
        populateOutputListView();
        populateFileTableListView();
        populateProgramStatesIDsListView();
        populateExeStackListView();
    }

    private void populateExeStackListView() {
        ProgramState programState = getCurrentProgramState();
        List<String> exeStackToString = new ArrayList<>();

        if (programState != null)
            for (IStatement statement : programState.getExeStack().getReversed()){
                exeStackToString.add(statement.toString());
            }
        executionStackListView.setItems(FXCollections.observableList(exeStackToString));
    }

    private void populateProgramStatesIDsListView() {
        List<ProgramState> programStates = controller.getProgramStates();
        List<Integer> idList = programStates.stream().map(ProgramState :: getId).collect(Collectors.toList());
        programStateIDSListView.setItems(FXCollections.observableList(idList));
        populateNumberProgramStatesTextField();
    }

    private void populateNumberProgramStatesTextField() {
        List<ProgramState> programStates = controller.getProgramStates();
        noProgramsTextFilled.setText(String.valueOf(programStates.size()));
    }

    private void populateFileTableListView() {
        ProgramState programState = getCurrentProgramState();
        List<String> files = new ArrayList<>(Objects.requireNonNull(programState).getFileTable().getContent().keySet());
        fileTableListView.setItems(FXCollections.observableList(files));
    }

    private void populateOutputListView() {
        ProgramState programState = getCurrentProgramState();
        List<String> out =new ArrayList<>();
        List<Value> outputList = Objects.requireNonNull(programState).getOut().getList();

        for (Value value : outputList){
            out.add(value.toString());
        }

        outputListView.setItems(FXCollections.observableArrayList(out));
    }

    private void populateSymbolTableView() {
        ProgramState programState = getCurrentProgramState();
        MyIDictionary<String, Value> symTable = Objects.requireNonNull(programState).getSymTable();
        ArrayList<Pair<String, Value>> symTableEntries = new ArrayList<>();
        for (Map.Entry<String, Value> entry: symTable.getContent().entrySet()) {
            symTableEntries.add(new Pair<>(entry.getKey(), entry.getValue()));
        }
        symTableTableView.setItems(FXCollections.observableArrayList(symTableEntries));
    }

    private void populateHeapTableView() {
        ProgramState programState = getCurrentProgramState();
        MyIHeap heapTable = Objects.requireNonNull(programState).getHeapTable();
        ArrayList<Pair<Integer, Value>> heapEntries =new ArrayList<>();

        for (Map.Entry<Integer, Value> entry : heapTable.getContent().entrySet())
            heapEntries.add(new Pair<>(entry.getKey(), entry.getValue()));

        heapTableView.setItems(FXCollections.observableArrayList(heapEntries));
    }

    @FXML
    private void changeProgramState(MouseEvent event){
        populateExeStackListView();
        populateSymbolTableView();
    }
    @FXML
    private void runOneStep(){
        if (controller != null){
            try {
                List<ProgramState> programStates = Objects.requireNonNull(controller.getProgramStates());
                if (programStates.size() > 0){
                    controller.oneStep();
                    populate();
                    programStates = controller.removeCompletedPrograms(controller.getProgramStates());
                    controller.setProgramStates(programStates);
                    populateProgramStatesIDsListView();
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!");
                    alert.setHeaderText("An error has occured!");
                    alert.setContentText("There is nothing left to execute!");
                    alert.showAndWait();
                }
            }catch (InterruptedException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Execution error!");
                alert.setHeaderText("An execution error has occured!");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("An error has occured!");
            alert.setContentText("No program selected!");
            alert.showAndWait();
        }
    }
}
