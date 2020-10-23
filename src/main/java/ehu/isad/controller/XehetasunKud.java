package ehu.isad.controller;

import ehu.isad.modeloak.LiburuDetaileak;
import ehu.isad.modeloak.Liburuak;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class XehetasunKud {

    //Atributuak
    private Liburuak        liburuApp;

    @FXML
    private Text izenburuContainer;
    @FXML
    private Text argitaletxeContainer;
    @FXML
    private Text orriKopContainer;
    @FXML
    private ImageView irudiContainer;

    //Metodoak
    public void liburuarenDatuakPublikatu(LiburuDetaileak pDetaileak){
        this.izenburuContainer.setText( pDetaileak.getIzena() );
        this.argitaletxeContainer.setText( pDetaileak.getArgitaletxeak()[0] );
        this.orriKopContainer.setText( String.valueOf( pDetaileak.getOrriKop() ) );
       // this.irudiContainer.setImage(pDetaileak.getIrudia());
    }

    public void setMainApp(Liburuak liburuak) {
        this.liburuApp = liburuak;
    }

    public void onClickBotoian(ActionEvent actionEvent) {
        this.liburuApp.liburuakErakutsi();
    }
}
