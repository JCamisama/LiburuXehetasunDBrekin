package ehu.isad.modeloak;

import javafx.scene.image.Image;

public class LiburuDetaileak {

    //Atributuak
    private String[]    publishers;
    private String      title;
    private Integer     number_of_pages;
    private Image       irudia;
    private String      isbn;

    //Eraikitzaileak
    public LiburuDetaileak(String pIsbn){
        this.isbn = pIsbn;
        this.title = "";
    }

    //Gainontzeko metodoak
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
}
