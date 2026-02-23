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
     * stampa i fallimenti totali raggruppandoli per utente.
     *
     * @param dati lista dei log da elaborare.
     */
    public void accessiFalliti(List<String[]> dati) {

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
        int n = dati.size();

        while (i < n) {
            String utenteCorrente = dati.get(i)[1];
            int count = 0;

            int j = i;
            while (j < n && dati.get(j)[1].equals(utenteCorrente)) {
                if (dati.get(j)[3].equals("FAIL")) {
                    count++;
                }
                j++;
            }

            System.out.println(utenteCorrente + ": " + count);
            i = j;
        }
    }

    /**
     * individua gli ip con un numero di fallimenti superiore al limite.
     * @param dati lista dei log da controllare.
     */
    
    public void ipSospetti(List<String[]> dati) {
        if (dati.isEmpty()) {
            return;
        }

        int limite = 2;

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
        int n = dati.size();

        while (i < n) {
            String ipAttuale = dati.get(i)[2];
            int countFail = 0;

            int j = i;
            while (j < n && dati.get(j)[2].equals(ipAttuale)) {
                if (dati.get(j)[3].equals("FAIL")) {
                    countFail++;
                }
                j++;
            }

            if (countFail >= limite) {
                System.out.println(ipAttuale + " e' sospetto, ha fatto: " + countFail + " fallimenti");
            }

            i = j;
        }
    }

    /**
     * mostra gli ip unici che hanno effettuato accessi in una fascia oraria.
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
