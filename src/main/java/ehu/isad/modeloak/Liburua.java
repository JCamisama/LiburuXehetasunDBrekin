package ehu.isad.modeloak;

public class Liburua {

    //Atributuak
    String info_url;
    String bib_key;
    String preview_url;
    String thumbnail_url;
    LiburuDetaileak details;
    String preview;

    //Gainontzeko metodoak
    public LiburuDetaileak getDetails(){
         return this.details;
    }

    public String getIrudiarenUrl(){

        //return this.thumbnail_url;
        return this.tamainaErtainekoIrudiaLortu();
    }

     @Override
     public String toString() {
         return "Liburua{" +
                 "info_url='" + info_url + '\'' +
                 ", bib_key='" + bib_key + '\'' +
                 ", preview_url='" + preview_url + '\'' +
                 ", thumbnail_url='" + thumbnail_url + '\'' +
                 ", details=" + details +
                 ", preview='" + preview + '\'' +
                 '}';
     }

     private String tamainaErtainekoIrudiaLortu(){

        String[] zatiak =this.thumbnail_url.split("-");
        String tamainaErtainekoa = zatiak[0]+"-M.jpg";

        return tamainaErtainekoa;
     }


}
