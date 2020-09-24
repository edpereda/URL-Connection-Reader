/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urlconnectionreader;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author eddyp
 */
public class Static_variables {
    public static ArrayList<String> list_url = new ArrayList<String>();
    public static ExecutorService pool = Executors.newFixedThreadPool(10);
}
