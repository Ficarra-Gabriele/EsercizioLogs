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
     * Conta i fallimenti per ogni utente ordinando i dati alfabeticamente.
     *
     * @param dati Lista originale dei log.
     * @return Lista di coppie.
     */
    
    public List<String[]> accessiFalliti(List<String[]> dati) {
        List<String[]> risultati = new ArrayList<>();
        if (dati == null) {
            return risultati;
        }

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
            risultati.add(new String[]{utente, String.valueOf(count)});
            i = j;
        }
        return risultati;
    }

    /**
     * Filtra gli IP con almeno 2 tentativi falliti.
     *
     * @param dati Lista dei log.
     * @return Lista di coppie.
     */
    public List<String[]> ipSospetti(List<String[]> dati) {
        List<String[]> risultati = new ArrayList<>();
        if (dati == null) {
            return risultati;
        }

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
     * Estrae IP unici attivi in una determinata fascia oraria.
     *
     * @param dati Lista dei log.
     * @param oraInizio Limite orario.
     * @param oraFine Limite orario.
     * @return Lista di array.
     */
    public List<String[]> intervallo(List<String[]> dati, String oraInizio, String oraFine) {
        List<String[]> risultati = new ArrayList<>();
        List<String> ipTrovati = new ArrayList<>();
        if (dati == null) {
            return risultati;
        }

        for (String[] riga : dati) {
            String o = riga[0].substring(11);
            if (o.compareTo(oraInizio) >= 0 && o.compareTo(oraFine) <= 0) {
                if (!ipTrovati.contains(riga[2])) {
                    ipTrovati.add(riga[2]);
                    risultati.add(new String[]{riga[2], o});
                }
            }
        }
        return risultati;
    }

    /**
     * Ordina cronologicamente i log tramite il timestamp.
     *
     * @param dati Lista dei log.
     * @return Lista ordinata per data e ora.
     */
    public List<String[]> ordinaPerTimestamp(List<String[]> dati) {
        if (dati == null) {
            return new ArrayList<>();
        }
        List<String[]> copia = new ArrayList<>(dati);
        for (int i = 0; i < copia.size() - 1; i++) {
            for (int j = 0; j < copia.size() - i - 1; j++) {
                if (copia.get(j)[0].compareTo(copia.get(j + 1)[0]) > 0) {
                    String[] temp = copia.get(j);
                    copia.set(j, copia.get(j + 1));
                    copia.set(j + 1, temp);
                }
            }
        }
        return copia;
    }
}
