package ehu.isad.modeloak;

import javafx.scene.image.Image;

public class LiburuDetaileak {

    //Atributuak
    private String[]    publishers;
    private String      title;
    private Integer     number_of_pages;
    private String      isbn;
    private String      irudiIzena;

    //Eraikitzaileak
    public LiburuDetaileak(String pIsbn){
        this.isbn = pIsbn;
        this.title = "";
    }

    public LiburuDetaileak(String pIsbn, String pTitle, Integer pNofPages, String pPublisher, String pImageName){

        this.isbn = pIsbn;
        this.title = pTitle;
        this.number_of_pages = pNofPages;
        this.irudiIzena = pImageName;

        this.publishers = new String[10];
        this.publishers[0] = pPublisher;
    }

    //Gainontzeko metodoak

    public String getIsbn(){return this.isbn;}


    public String getIzena(){
        return this.title;
    }


    public int getOrriKop(){
        return this.number_of_pages;
    }


    public String[] getArgitaletxeak(){
        return this.publishers;
    }


    public void irudiIzenaSortu(String pFormatua){
        this.irudiIzena = this.isbn+"."+pFormatua;
    }


    public String getIrudiaIzena(){ return this.irudiIzena;}


    public void setIsbn(String pIsbn) {
        this.isbn = pIsbn;
    }
}
