package ehu.isad.controller;


import ehu.isad.modeloak.LiburuDetaileak;
import ehu.isad.utils.Sarea;
import ehu.isad.modeloak.Liburuak;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LiburuKud implements Initializable {

    //Atributuak
    private Liburuak        liburuApp;
    private LiburuDetaileak unekoLiburua;

    @FXML
    private ComboBox    comboZerbitzua;
    @FXML
    private Button      ikusiBotoia;


    //Metodoak
    public void liburuzBete() throws IOException {

        ObservableList<LiburuDetaileak> liburuList = FXCollections.observableArrayList();
        liburuList.addAll(
                Sarea.bilatuLiburuaIsbnrekin("9781491920497"),
                Sarea.bilatuLiburuaIsbnrekin("1491910399"),
                Sarea.bilatuLiburuaIsbnrekin("1491946008"),
                Sarea.bilatuLiburuaIsbnrekin("1491978236"),
                Sarea.bilatuLiburuaIsbnrekin("9781491906187")
        );

        this.comboZerbitzua.setItems(liburuList);
        this.comboZerbitzua.setEditable(false);
        this.comboBoxekoEtiketaEguneratu();
    }

    @FXML
    public void onClickComboBoxen(ActionEvent actionEvent) {

        this.comboBoxekoEtiketaEguneratu();
        this.unekoLiburuaEguneratu();
    }

    @FXML
    public void onClickBotoian(){
        this.liburuApp.xehetasunakErakutsi(this.unekoLiburua);
    }


    private void comboBoxekoEtiketaEguneratu(){

        this.comboZerbitzua.setConverter(new StringConverter<LiburuDetaileak>() {

            @Override
            public String toString(LiburuDetaileak pDetaileak) {
                if (pDetaileak == null) {
                    return "";
                } else {
                    return pDetaileak.getIzena();
                }
            }
            @Override
            public LiburuDetaileak fromString(String izena) {
                return null;
            }
        });
    }

    private void unekoLiburuaEguneratu(){
            this.unekoLiburua = (LiburuDetaileak)this.comboZerbitzua.getValue();
    }

    public void setMainApp(Liburuak liburuak) {
        this.liburuApp = liburuak;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){}


   public void liburuzBeteHasieraketa() {

        ObservableList<LiburuDetaileak> liburuList = FXCollections.observableArrayList();
        liburuList.addAll(
                new LiburuDetaileak("9781491920497"),
                new LiburuDetaileak("1491910399"),
                new LiburuDetaileak("1491946008"),
                new LiburuDetaileak("1491978236"),
                new LiburuDetaileak("9781491906187")
        );

        this.comboZerbitzua.setItems(liburuList);
        this.comboZerbitzua.setEditable(false);
        this.comboBoxekoEtiketaEguneratu();
    }
}
