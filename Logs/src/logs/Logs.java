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
        
        FileManager f1 = new FileManager();
        
        List<String[]> dati;
        dati = f1.leggiFile();
        System.out.println();
        
        Gestore g = new Gestore();
        
        g.accessiFalliti(dati);
        System.out.println();
        
        g.intervallo(dati, "14:30:05", "16:00:00");
        System.out.println();
        
        g.ipSospetti(dati);
        System.out.println();
        
        g.ordinaPerTimestamp(dati);
    }
}