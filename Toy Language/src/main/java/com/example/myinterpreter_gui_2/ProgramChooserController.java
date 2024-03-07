package com.example.myinterpreter_gui_2;

import com.example.myinterpreter_gui_2.Controller.Controller;
import com.example.myinterpreter_gui_2.Exceptions.ADTException;
import com.example.myinterpreter_gui_2.Exceptions.ExpressionEvaluationException;
import com.example.myinterpreter_gui_2.Exceptions.StatementExecutionException;
import com.example.myinterpreter_gui_2.Model.ADTs.MyDictionary;
import com.example.myinterpreter_gui_2.Model.ADTs.MyHeap;
import com.example.myinterpreter_gui_2.Model.ADTs.MyList;
import com.example.myinterpreter_gui_2.Model.ADTs.MyStack;
import com.example.myinterpreter_gui_2.Model.Expressions.*;
import com.example.myinterpreter_gui_2.Model.ProgramState.ProgramState;
import com.example.myinterpreter_gui_2.Model.Statements.*;
import com.example.myinterpreter_gui_2.Model.Statements.FileOperations.CloseReadFile;
import com.example.myinterpreter_gui_2.Model.Statements.FileOperations.OpenReadFile;
import com.example.myinterpreter_gui_2.Model.Statements.FileOperations.ReadFile;
import com.example.myinterpreter_gui_2.Model.Types.BoolType;
import com.example.myinterpreter_gui_2.Model.Types.IntType;
import com.example.myinterpreter_gui_2.Model.Types.RefType;
import com.example.myinterpreter_gui_2.Model.Types.StringType;
import com.example.myinterpreter_gui_2.Model.Values.BoolValue;
import com.example.myinterpreter_gui_2.Model.Values.IntValue;
import com.example.myinterpreter_gui_2.Model.Values.StringValue;
import com.example.myinterpreter_gui_2.Repository.Repository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.util.ArrayList;
import java.util.List;

public class ProgramChooserController {
    private ProgramExecutorController programExecutorController;

    public void setProgramExecutorController(ProgramExecutorController programExecutorController) {
        this.programExecutorController = programExecutorController;
    }

    @FXML
    private ListView<IStatement> programsListView;

    @FXML
    private Button runSelectedProgramButton;

    @FXML
    public void initialize(){
        programsListView.setItems(getAllStatements());
        programsListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    private void displayProgram(ActionEvent actionEvent){
        IStatement selected = programsListView.getSelectionModel().getSelectedItem();
        if (selected == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("No program selected!");
            alert.showAndWait();
        }else {
            int id = programsListView.getSelectionModel().getSelectedIndex();

            try {
                selected.typeCheck(new MyDictionary<>());
                ProgramState programState = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), selected);
                Repository repository = new Repository(programState, "log" + (id + 1) + ".txt");
                Controller controller = new Controller(repository);
                programExecutorController.setController(controller);

            } catch (ExpressionEvaluationException | ADTException | StatementExecutionException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }
    @FXML
    private ObservableList<IStatement> getAllStatements(){
        List<IStatement> allStatements = new ArrayList<>();

        IStatement ex1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));
        allStatements.add(ex1);

        IStatement ex2 = new CompoundStatement(new VariableDeclarationStatement("a",new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression('+',new ValueExpression(new IntValue(2)),new
                                ArithmeticExpression('*',new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignmentStatement("b",new ArithmeticExpression('+',new VariableExpression("a"), new ValueExpression(new
                                        IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
        allStatements.add(ex2);

        IStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a", new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),
                                        new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                                        new AssignmentStatement("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStatement(new VariableExpression("v"))))));
        allStatements.add(ex3);

        IStatement ex4 = new CompoundStatement(new VariableDeclarationStatement("varf", new StringType()),
                new CompoundStatement(new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(new OpenReadFile(new VariableExpression("varf")),
                                new CompoundStatement(new VariableDeclarationStatement("varc", new IntType()),
                                        new CompoundStatement(new ReadFile(new VariableExpression("varf"), "varc"),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                        new CompoundStatement(new ReadFile(new VariableExpression("varf"), "varc"),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                        new CloseReadFile(new VariableExpression("varf"))))))))));
        allStatements.add(ex4);

        IStatement ex5 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new IntValue(5))),
                                new CompoundStatement(new AssignmentStatement("b", new ValueExpression(new IntValue(7))),
                                        new IfStatement(new RelationalExpression(">", new VariableExpression("a"),
                                                new VariableExpression("b")),new PrintStatement(new VariableExpression("a")),
                                                new PrintStatement(new VariableExpression("b")))))));
        allStatements.add(ex5);

        IStatement ex6 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(4))),
                        new CompoundStatement(new WhileStatement(new RelationalExpression(">", new VariableExpression("v"), new ValueExpression(new IntValue(0))),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignmentStatement("v",new ArithmeticExpression('-', new VariableExpression("v"), new ValueExpression(new IntValue(1)))))),
                                new PrintStatement(new VariableExpression("v")))));
        allStatements.add(ex6);

        IStatement ex7 = new CompoundStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(new HeapAllocationStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new VariableExpression("a")))))));
        allStatements.add(ex7);

        IStatement ex8 = new CompoundStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(new HeapAllocationStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
                                                new PrintStatement(new ArithmeticExpression('+',new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a"))), new ValueExpression(new IntValue(5)))))))));
        allStatements.add(ex8);

        IStatement ex9 = new CompoundStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement( new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
                                new CompoundStatement(new HeapWritingStatement("v", new ValueExpression(new IntValue(30))),
                                        new PrintStatement(new ArithmeticExpression('+', new HeapReadingExpression(new VariableExpression("v")), new ValueExpression(new IntValue(5))))))));
        allStatements.add(ex9);

        IStatement ex10 = new CompoundStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(new HeapAllocationStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntValue(30))),
                                                new PrintStatement(new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a")))))))));
        allStatements.add(ex10);

        IStatement ex11 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("a", new RefType(new IntType())),
                        new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(10))),
                                new CompoundStatement(new HeapAllocationStatement("a", new ValueExpression(new IntValue(22))),
                                        new CompoundStatement(new ForkStatement(new CompoundStatement(new HeapWritingStatement("a", new ValueExpression(new IntValue(30))),
                                                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(32))),
                                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new HeapReadingExpression(new VariableExpression("a"))))))),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new HeapReadingExpression(new VariableExpression("a")))))))));
        allStatements.add(ex11);
        return FXCollections.observableArrayList(allStatements);
    }
}
