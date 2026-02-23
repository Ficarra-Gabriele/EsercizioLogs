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
     * Conta e stampa il numero di accessi falliti per ogni utente.
     *
     * @param dati è una lista di array di stringhe contenente i log.
     */
    public void accessiFalliti(List<String[]> dati) {

        ArrayList controllati = new ArrayList<>();

        for (int i = 0; i < dati.size(); i++) {
            String x = dati.get(i)[1];
            if (!controllati.contains(x)) {
                int count = 0;
                for (int j = 0; j < dati.size(); j++) {
                    if (dati.get(j)[1].equals(x) && dati.get(j)[3].equals("FAIL")) {
                        count++;
                    }
                }
                System.out.println(x + ": " + count);
                controllati.add(x);
            }
        }
    }

    /**
     * Identifica e stampa gli indirizzi IP chevengono considerati sospetti.
     *
     * @param dati lista di array di stringhe contenente i log.
     */
    public void ipSospetti(List<String[]> dati) {
        int limite = 2;
        ArrayList<String> controllati = new ArrayList<>();

        for (int i = 0; i < dati.size(); i++) {
            String ipAttuale = dati.get(i)[2];

            if (!controllati.contains(ipAttuale)) {
                int countFail = 0;

                for (int j = 0; j < dati.size(); j++) {
                    if (dati.get(j)[2].equals(ipAttuale) && dati.get(j)[3].equals("FAIL")) {
                        countFail++;
                    }
                }

                if (countFail >= limite) {
                    System.out.println(ipAttuale + " e' sospetto, ha fatto:  " + countFail + " fallimenti");
                }
                controllati.add(ipAttuale);
            }
        }
    }

    /**
     * Filtra gli accessi avvenuti in un determinato intervallo temporale.
     *
     * @param dati Lista di array di stringhe contenente i log.
     * @param oraInizio Orario di inizio dell'intervallo.
     * @param oraFine Orario di fine dell'intervallo.
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
     * Ordina cronologicamente i dati in base al timestamp.
     *
     * @param dati lista di array di stringhe da ordinare.
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