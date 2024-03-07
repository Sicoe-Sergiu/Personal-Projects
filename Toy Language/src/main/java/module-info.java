module com.example.myinterpreter_gui_ {

    requires javafx.controls;
    requires javafx.fxml;


    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    //requires org.kordamp.ikonli.javafx;

    opens com.example.myinterpreter_gui_2 to javafx.fxml;
    exports com.example.myinterpreter_gui_2;
}