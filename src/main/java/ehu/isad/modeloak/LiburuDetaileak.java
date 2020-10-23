package ehu.isad.modeloak;

import javafx.scene.image.Image;

public class LiburuDetaileak {

    //Atributuak
    private String[]    publishers;
    private String      title;
    private Integer     number_of_pages;
    private Image       irudia;
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


    public Image getIrudia(){
        return this.irudia;
    }

    public void setIrudia(Image pIrudia){
        this.irudia = pIrudia;
    }

    public String getIrudiaIzena(){ return this.irudiIzena;}
}
