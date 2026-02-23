/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logs;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ironm
 */
public class Gestore {

    /**
     * conta i fallimenti per utente e restituisce i dati per la tabella.
     *
     * @param dati lista originale dei log.
     * @return lista contenente coppie utente-conteggio.
     */
    
    public List<String[]> accessiFalliti(List<String[]> dati) {
        List<String[]> risultati = new ArrayList<>();
        
        for (int i = 0; i < dati.size() - 1; i++) {
            for (int j = 0; j < dati.size() - i - 1; j++) {
                if (dati.get(j)[1].compareTo(dati.get(j + 1)[1]) > 0) {
                    String[] temp = dati.get(j);
                    dati.set(j, dati.get(j + 1));
                    dati.set(j + 1, temp);
                }
            }
        }

        int i = 0;
        while (i < dati.size()) {
            String utente = dati.get(i)[1];
            int count = 0;
            int j = i;
            while (j < dati.size() && dati.get(j)[1].equals(utente)) {
                if (dati.get(j)[3].equals("FAIL")) {
                    count++;
                }
                j++;
            }
            // Aggiungiamo solo le due colonne che ci interessano
            risultati.add(new String[]{utente, String.valueOf(count)});
            i = j;
        }
        return risultati;
    }

    /**
     * identifica gli ip sospetti e restituisce i dati filtrati.
     *
     * @param dati lista originale dei log.
     * @return lista di array.
     */
    public List<String[]> ipSospetti(List<String[]> dati) {
        List<String[]> risultati = new ArrayList<>();
        
        for (int i = 0; i < dati.size() - 1; i++) {
            for (int j = 0; j < dati.size() - i - 1; j++) {
                if (dati.get(j)[2].compareTo(dati.get(j + 1)[2]) > 0) {
                    String[] temp = dati.get(j);
                    dati.set(j, dati.get(j + 1));
                    dati.set(j + 1, temp);
                }
            }
        }

        int i = 0;
        while (i < dati.size()) {
            String ip = dati.get(i)[2];
            int count = 0;
            int j = i;
            while (j < dati.size() && dati.get(j)[2].equals(ip)) {
                if (dati.get(j)[3].equals("FAIL")) {
                    count++;
                }
                j++;
            }
            if (count >= 2) {
                risultati.add(new String[]{ip, String.valueOf(count)});
            }
            i = j;
        }
        return risultati;
    }

    /**
     * mostra gli ip unici che hanno effettuato accessi in una fascia oraria.
     *
     * @param dati lista dei log.
     * @param oraInizio limite inferiore orario.
     * @param oraFine limite superiore orario.
     */
    public void intervallo(List<String[]> dati, String oraInizio, String oraFine) {
        List<String> ipTrovati = new ArrayList<>();
        for (int i = 0; i < dati.size(); i++) {
            String t = dati.get(i)[0];
            String o = t.substring(11);
            if (o.compareTo(oraInizio) >= 0 && o.compareTo(oraFine) <= 0) {
                String ip = dati.get(i)[2];
                if (!ipTrovati.contains(ip)) {
                    ipTrovati.add(ip);
                    System.out.println("accesso rilevato dall'IP: " + ip + " alle ore " + o);
                }
            }
        }
    }

    /**
     * ordina e stampa i log in ordine temporale crescente.
     *
     * @param dati lista dei log da ordinare.
     */
    public void ordinaPerTimestamp(List<String[]> dati) {

        for (int i = 0; i < dati.size() - 1; i++) {
            for (int j = 0; j < dati.size() - i - 1; j++) {
                String ts1 = dati.get(j)[0];
                String ts2 = dati.get(j + 1)[0];

                if (ts1.compareTo(ts2) > 0) {
                    String[] temp = dati.get(j);
                    dati.set(j, dati.get(j + 1));
                    dati.set(j + 1, temp);
                }
            }
        }
        for (int i = 0; i < dati.size(); i++) {
            String[] riga = dati.get(i);
            System.out.println(riga[0] + " | " + riga[1] + " | " + riga[2] + " | " + riga[3]);
        }
    }
}