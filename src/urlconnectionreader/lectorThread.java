/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urlconnectionreader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.JOptionPane;

/**
 *
 * @author eddyp
 */
public class lectorThread extends Thread{
    int numero_pagina;
    String url_pagina;
    
    public lectorThread(int numero_pagina, String url_pagina) {
        this.numero_pagina=numero_pagina;
        this.url_pagina=url_pagina;
    }
    
    
    @Override
    public void run(){
        
        if (!se_encuentra_lista()){
            
            Static_variables.list_url.add(url_pagina);
            System.out.println("URL AÑADIDA");
            System.out.println("LectorThread ha iniciado con pagina: "+url_pagina);
            File dir_raiz = new File(".");

            try{
                String ruta = dir_raiz.getAbsolutePath()+"//pagina_web//pagina"+numero_pagina+".html";
                //obtener_nombre_pagina(url_pagina);

                File file = new File(ruta);
                file.createNewFile();

                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);

                    String ruta_pagina_web=url_pagina;

                    URL oracle = new URL(ruta_pagina_web);
                    URLConnection yc = oracle.openConnection();

                    BufferedReader in = new BufferedReader(new InputStreamReader(
                            yc.getInputStream()));

                    String inputLine;
                    while ((inputLine = in.readLine()) != null){
                        //System.out.println(inputLine);
                        
                        if (inputLine.contains("href=")){
                        
                        int inicio = inputLine.indexOf("href");
                        //JOptionPane.showMessageDialog(null, "Inicio: "+inicio);
                        
                        String URL_limpia = limpiar_URL(inputLine,inicio);
                        //JOptionPane.showMessageDialog(null, "cadena limpia: "+URL_limpia);
                        
                        //String inputLineMod = modificar_href(inputLine,URL_limpia,inicio,contador);
                        //JOptionPane.showMessageDialog(null, inputLineMod);
                        
                        if (URL_limpia!=null){
                            numero_pagina++;
                            lectorThread lectorT = new lectorThread(numero_pagina, URL_limpia);
                            //lectorT.start();
                            Static_variables.pool.execute(lectorT);
                            while(lectorT.isAlive()){
                                
                            }
                        }
                        
                    }

                        bw.write(inputLine);
                        bw.flush();
                    }

                    in.close();
                    bw.close();

            }catch(Exception e){
                e.printStackTrace();

            }
        }
    }
    
    public boolean se_encuentra_lista(){
        for (int i = 0; i< Static_variables.list_url.size(); i++){
            if (Static_variables.list_url.get(i).compareToIgnoreCase(url_pagina)==0){
                return true;
            }
        }
        return false;
    }
    
    private static String limpiar_URL(String inputLine, int inicio){
        char cadena[] = inputLine.toCharArray();
        String URL_limpia="";
        
        if (inputLine.contains("#")){           //Si contiene #
            return null;
        }
        
        if (!inputLine.contains("www")){        //si no contiene www
            return null;
        }
        
        if (inputLine.contains(".jpg")||inputLine.contains(".png")||inputLine.contains(".ico")){        //si contiene imagenes
            return null;
        }
        
        if (inputLine.contains(".css")){
            return null;
        }
        
        for ( int i = inicio+6; i<inputLine.length(); i++){
            
            if (cadena[i]=='"'){
                return URL_limpia;              //Regresa la URL
            }else{
                URL_limpia+=cadena[i]+"";
            }
        }
        return null;
    }
    private static String obtener_nombre_pagina(String url_pagina) {
        int indexbeg = url_pagina.lastIndexOf("/");
        String nombre_pagina = url_pagina.substring(indexbeg, url_pagina.length()-1);
        //JOptionPane.showMessageDialog(null, nombre_pagina);
        return nombre_pagina;
    }
    
    
    
}
