package ehu.isad.modeloak;

import ehu.isad.controller.ui.LiburuKud;
import ehu.isad.controller.ui.XehetasunKud;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class Liburuak extends Application {

    //Atributuak
    private Parent xehetasunakUI;
    private Parent liburuakUI;

    private Stage stage;

    private Scene eszenaLib;
    private Scene eszenaXeh;

    private LiburuKud liburuKud;
    private XehetasunKud xeheKud;

    private String liburuenLeihoa       = "/Liburuak.fxml";
    private String xehetasunenLeihoa    = "/xehetasunak.fxml";


    //Metodoak
    @Override
    public void start(Stage primaryStage) throws Exception {

        this.stage = primaryStage;
        pantailakKargatu();

        this.stage.setTitle("OpenLibrary APIa eta Datu Basea aztertzen");
        this.eszenaLib = new Scene(this.liburuakUI, 450, 200);
        this.eszenaXeh = new Scene(this.xehetasunakUI, 900, 500);

        this.eszenaErakutsi(this.eszenaLib);
    }


    private void pantailakKargatu() throws IOException {

        FXMLLoader loaderLiburuak = new FXMLLoader(getClass().getResource(this.liburuenLeihoa));
        this.liburuakUI = (Parent) loaderLiburuak.load();
        this.liburuKud = loaderLiburuak.getController();
        this.liburuKud.liburuzBete();
        this.liburuKud.setMainApp(this);

        FXMLLoader loaderXehe = new FXMLLoader(getClass().getResource(this.xehetasunenLeihoa));
        this.xehetasunakUI = (Parent) loaderXehe.load();
        this.xeheKud = loaderXehe.getController();
        this.xeheKud.setMainApp(this);
    }



    public static void main(String[] args) {
        launch(args);
    }


    public void liburuakErakutsi() {
        this.eszenaErakutsi(this.eszenaLib);
    }


    public void xehetasunakErakutsi(LiburuDetaileak pDetaileak){
        this.xeheKud.liburuarenDatuakPublikatu(pDetaileak);
        this.eszenaErakutsi(this.eszenaXeh);
    }


    private void eszenaErakutsi(Scene pEszena){
        this.stage.setScene(pEszena);
        this.stage.show();
    }
}
