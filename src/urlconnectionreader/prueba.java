/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urlconnectionreader;

import javax.swing.JOptionPane;

/**
 *
 * @author eddyp
 */
public class prueba {
    public static void main (String args []){
        obtener_nombre_pagina(JOptionPane.showInputDialog("Ingrese URL: "));
    }
    
    private static String obtener_nombre_pagina(String url_pagina) {
        String nombre_pagina = url_pagina.substring(url_pagina.length()-10, url_pagina.length()-1);
        JOptionPane.showMessageDialog(null, nombre_pagina);
        return nombre_pagina;
    }
}
