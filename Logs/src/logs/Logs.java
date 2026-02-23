/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package logs;

import java.util.List;

/**
 *
 * @author ironm
 */
public class Logs {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        FileManager fileManager = new FileManager();
        Gestore gestore = new Gestore();
        
        List<String[]>  dati = fileManager.leggiFile();
        System.out.println();
        
        gestore.accessiFalliti(dati);
        System.out.println();
        
        gestore.intervallo(dati, "14:30:05", "16:00:00");
        System.out.println();
        
        gestore.ipSospetti(dati);
        System.out.println();
        
        gestore.ordinaPerTimestamp(dati);
    }
}