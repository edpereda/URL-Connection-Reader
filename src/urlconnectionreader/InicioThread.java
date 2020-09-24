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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JOptionPane;

/**
 *
 * @author eddyp
 */
public class InicioThread extends Thread{
    @Override
    public void run(){
        File dir_raiz = new File(".");
        
        try {
            System.out.println("Inicio");
            String ruta = dir_raiz.getAbsolutePath()+"//pagina_web//pagina.html";
            
            File file = new File(ruta);
            file.createNewFile();
            
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            
                String ruta_pagina_web=JOptionPane.showInputDialog("Ingrese la URL: ");
                
                URL oracle = new URL(ruta_pagina_web);
                URLConnection yc = oracle.openConnection();
                
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        yc.getInputStream()));
                
                
                String inputLine;
                int contador=0;
                
                while ((inputLine = in.readLine()) != null){
                    System.out.println(inputLine);
                    
                    //DEBEMOS VERIFICAR SI LO QUE HAY EN INPUTLINE HAY UNA URL, Y SI ES ASI. QUE TAMBIEN SE DESCARGUE LA PAGINA
                    if (inputLine.contains("href=")){
                        
                        int inicio = inputLine.indexOf("href");
                        //JOptionPane.showMessageDialog(null, "Inicio: "+inicio);
                        
                        String URL_limpia = limpiar_URL(inputLine,inicio);
                        //JOptionPane.showMessageDialog(null, "cadena limpia: "+URL_limpia);
                        
                        //String inputLineMod = modificar_href(inputLine,URL_limpia,inicio,contador);
                        //JOptionPane.showMessageDialog(null, inputLineMod);
                    bw.write(inputLine+"\n");
                    bw.flush();
                        
                        if (URL_limpia!=null){
                            System.out.println("Entro a URL_LIMPIA INICIO");
                            System.out.println("URL LIMPIA: "+URL_limpia);
                            contador++;
                            lectorThread lectorT = new lectorThread(contador, URL_limpia);
                            //lectorT.start();
                            Static_variables.pool.execute(lectorT);
                            while(lectorT.isAlive()){
                                
                            }
                        }
                        
                    }
                    //FALTA MODIFICAR EL INPUT LINE PARA QUE SE PUEDA ACCEDER DE MANERA LOCAL
                    //Aqui escribimos la linea de codigo leida en la actual pagina
                }
                    
                in.close();
                bw.close();
                    
                
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static String limpiar_URL(String inputLine, int inicio){
        char cadena[] = inputLine.toCharArray();
        String URL_limpia="";
        
        if (inputLine.contains("#")){           //Si contiene #
            return null;
        }
        
        /*if (!inputLine.contains("www")){        //si no contiene www
            return null;
        }*/
        
        for ( int i = inicio+6; i<inputLine.length(); i++){
            
            if (cadena[i]=='"'){
                return URL_limpia;              //Regresa la URL
            }else{
                URL_limpia+=cadena[i]+"";
            }
        }
        return null;
    }
}
