package ehu.isad.utils;

import com.google.gson.Gson;
import ehu.isad.controller.fitxategiak.IrudiKud;
import ehu.isad.modeloak.LiburuDetaileak;
import ehu.isad.modeloak.Liburua;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class Sarea {

    //Atributuak
    private static String url_lehenengo_zatia   = "https://openlibrary.org/api/books?bibkeys=ISBN:";
    private static String url_bigarren_zatia    = "&jscmd=details&format=json";

    //Metodoak
    public static LiburuDetaileak bilatuLiburuaIsbnrekin(String pIsbn){

        LiburuDetaileak detaileak;

        //URL-tik Json formatuko testua lortuko da orain
        String urlOsoa = url_lehenengo_zatia + pIsbn + url_bigarren_zatia;
        String jsonFormatukoLerroa = urltikJsonFormatuanTextuaLortu(urlOsoa);

        //Liburuaren informazio osoa ez dugula behar, detaileak bakarrik hartuko dira
        String[] zatiak = jsonFormatukoLerroa.split("ISBN:"+pIsbn+"\":");
        String liburukoInfo = zatiak[1].substring(0, zatiak[1].length()-1);
        detaileak = detaileakEmanLiburukoInfotik(liburukoInfo, pIsbn);

        return detaileak;
    }

    private static String urltikJsonFormatuanTextuaLortu(String pUrlOsoa) {

        String jsonTestua = "";

        try {
            URL openLib = new URL(pUrlOsoa);
            URLConnection yc = openLib.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            jsonTestua = in.readLine();
            in.close();
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonTestua;
    }

    private static LiburuDetaileak detaileakEmanLiburukoInfotik(String pLiburukoInfo, String pIsbn) {

        Gson gson = new Gson();
        Liburua liburu = gson.fromJson(pLiburukoInfo, Liburua.class);
        LiburuDetaileak detaileak = liburu.getDetails();
        detaileak.setIsbn(pIsbn);
        String irudiaUrl = liburu.getIrudiarenUrl();
        try{
            Sarea.irudiaSortu(irudiaUrl, detaileak);
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }

        return detaileak;
    }

    private static void irudiaSortu(String pIrudiUrl, LiburuDetaileak pDetaileak) throws IOException{

        Image irudia = Sarea.createImage(pIrudiUrl);
        String formatua = IrudiKud.getInstantzia().irudiaGorde(irudia, pDetaileak.getIsbn());
        pDetaileak.irudiIzenaSortu(formatua);

    }


    private static Image createImage(String url) throws IOException {
        URLConnection conn = new URL(url).openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh;" +
                "Intel Mac OS 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) " +
                "Chrome/85.0.4183.121 Safari/537.36");
        try (InputStream stream = conn.getInputStream()){
            return new Image(stream);
        }
    }



}
