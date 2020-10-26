package ehu.isad.controller.db;

import ehu.isad.modeloak.LiburuDetaileak;
import ehu.isad.utils.Sarea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZerbitzuKud {

    private static final ZerbitzuKud instance = new ZerbitzuKud();

    public static ZerbitzuKud getInstance() {
        return instance;
    }

    private ZerbitzuKud() {
    }


    public ObservableList<LiburuDetaileak> lortuLiburuak() {

        ObservableList<LiburuDetaileak> liburuList = FXCollections.observableArrayList();
        LiburuDetaileak detaileak;
        ResultSet rSet = this.liburuakEskatuDatuBaseari();

        try {
            while (rSet.next()) {
                detaileak = this.detaileakLortu(rSet);
                liburuList.add(detaileak);
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return liburuList;
    }


    private ResultSet liburuakEskatuDatuBaseari(){

        String query = "select isbn, izenburu, argitaletxe, " +
                "orriKop, irudia from liburuak";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        return rs;
    }



    private LiburuDetaileak detaileakLortu(ResultSet pLiburuZerre) throws SQLException {

        //AurreB: Datu-Baseko liburuak izena badauka, gainerako datu guztiak izango ditu.

        LiburuDetaileak detaileak;

        String isbn = pLiburuZerre.getString("isbn");
        String izenburu = pLiburuZerre.getString("izenburu");
        if(pLiburuZerre.wasNull()){
            detaileak = new LiburuDetaileak(isbn);
        }
        else{
            String argitaletxea = pLiburuZerre.getString("argitaletxe");
            int orriKop = pLiburuZerre.getInt("orriKop");
            String irudia = pLiburuZerre.getString("irudia");

            detaileak = new LiburuDetaileak(isbn, izenburu, orriKop, argitaletxea, irudia);
        }

        return detaileak;
    }



    public LiburuDetaileak liburuaEguneratu(String pIsbn) {

        //Liburua lortzen OpenLibrary-tik
        LiburuDetaileak libHau = Sarea.bilatuLiburuaIsbnrekin(pIsbn);

        //Liburuaren datuak datu basean erregistratzen
        String updateEskaera = this.liburuaUpdateEskaeraPrestatu(libHau, pIsbn);
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        dbKudeatzaile.execSQL(updateEskaera);

        return libHau;
    }


    private String liburuaUpdateEskaeraPrestatu(LiburuDetaileak pLibDetaileak, String pIsbn){

        //liburu taulako zutabeak: isbn, izenburu, argitaletxe, orriKop, irudia

        String  izenburu     = pLibDetaileak.getIzena();
        int     orriKop      = pLibDetaileak.getOrriKop();
        String  argitaletxe  = pLibDetaileak.getArgitaletxeak()[0];
        String  irudia       = pLibDetaileak.getIrudiaIzena();

        String query = "update liburuak " +
                "set " +
                "izenburu = \""+izenburu+"\", " +
                "argitaletxe = \""+argitaletxe+"\", " +
                "orriKop = "+orriKop+", " +
                "irudia = \""+irudia+"\" " +
                "where isbn = \"" +pIsbn+"\";";

        return query;
    }
}
