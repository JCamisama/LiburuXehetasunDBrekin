package ehu.isad.controller.ui;


import ehu.isad.controller.db.ZerbitzuKud;
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
    private ComboBox    comboLiburuak;
    @FXML
    private Button      ikusiBotoia;

    //Metodoak
    public void liburuzBete() throws IOException {

        ZerbitzuKud zerbitzuak = ZerbitzuKud.getInstance();
        ObservableList<LiburuDetaileak> liburuList = zerbitzuak.lortuLiburuak();

        this.comboLiburuak.setItems(liburuList);
        this.comboLiburuak.setEditable(false);
        this.comboBoxekoEtiketaEguneratu();
        this.unekoLiburua = liburuList.get(0);
    }

    @FXML
    public void onClickComboBoxen(ActionEvent actionEvent) {

        //this.comboBoxekoEtiketaEguneratu();
        this.unekoLiburuaEguneratu();
        this.comboBoxekoEtiketaEguneratu();
    }

    @FXML
    public void onClickBotoian(){
        if(this.unekoLiburua.getIzena().equals(""))
        {
            this.comboBoxekoZerrendaEguneratu();
            this.comboBoxekoEtiketaEguneratu();
        }
        this.liburuApp.xehetasunakErakutsi(this.unekoLiburua);
    }


    private void comboBoxekoZerrendaEguneratu(){

        ObservableList<LiburuDetaileak> liburuZerrenda = this.comboLiburuak.getItems();

        String isbn     = this.unekoLiburua.getIsbn();
        int    posizioa = liburuZerrenda.indexOf(this.unekoLiburua);

        liburuZerrenda.remove(this.unekoLiburua);
        this.unekoLiburua = ZerbitzuKud.getInstance().liburuaEguneratu(isbn);
        liburuZerrenda.add(posizioa, this.unekoLiburua);
    }


    private void comboBoxekoEtiketaEguneratu(){

        this.comboLiburuak.setConverter(new StringConverter<LiburuDetaileak>() {

            @Override
            public String toString(LiburuDetaileak pDetaileak) {
                if (pDetaileak == null) {
                    return "";
                }
                else if (pDetaileak.getIzena().equals("")) {
                    return pDetaileak.getIsbn();
                }
                else{
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
            this.unekoLiburua = (LiburuDetaileak)this.comboLiburuak.getValue();
    }

    public void setMainApp(Liburuak liburuak) {
        this.liburuApp = liburuak;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){}


   /*public void liburuzBeteHasieraketa() {

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
    }*/
}
