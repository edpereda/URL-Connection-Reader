/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*https://is603unahavv.wordpress.com/2016/09/02/tipos-de-instrucciones-mips/*/
package urlconnectionreader;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author eddyp
 */
public class URLConnectionReader {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        InicioThread iniciot = new InicioThread();
        iniciot.start();
    }

    
}
