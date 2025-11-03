package org.oop.app;
import lombok.*;

import java.time.LocalDate;
import java.util.*;

public class Registru {

    public Collection<Produs> getProduseOrdonateDupaId() {
        TreeSet<Produs> produseOrdonate = new TreeSet<Produs>();
// Produs implementeaza Comparable dupa idProdus
        produseOrdonate.addAll(this.produse);
        return produseOrdonate;
    }
    public Collection<Produs> getProduseOrdonateDupaDenumire() {
        List<Produs> produseOrdonate = new ArrayList<Produs>();
        produseOrdonate.addAll(this.produse);
        Collections.sort(produseOrdonate, new ComparatorProduseDenumire());
        return produseOrdonate;
    }
    /* (v) Probleme de interogare 1 (operaţii de căutare simplă în colecţii) */
    public Produs getProdus(Integer idProdus) throws Exception {
        Produs p = new Produs();
        p.setIdProdus(idProdus);
        Integer pIndex = produse.indexOf(p);
        if (pIndex >= 0)
            return this.produse.get(pIndex);
        else
            throw new Exception("No data found: Produs inexistent!");
    }
    public Comanda getComanda(Integer idComanda) {
        Comanda c = new Comanda();
        c.setIdComanda(idComanda);
        Integer cIndex = comenzi.indexOf(c);
        if (cIndex >= 0)
            return this.comenzi.get(cIndex);
        else
            throw new RuntimeException("No data found: Comanda inexistenta!");
    }
    /* (vi) Probleme de interogare 2 (operaţii cu mulţimi) */
    public List<Produs> getProduseDinComanda(Integer idComanda) {
        Comanda c = getComanda(idComanda);
        List<Produs> produse = new ArrayList<Produs>();
        for (ArticolComanda a : c.getArticole())
            produse.add(a.getProdus());
        return produse;
    }
    public Collection<Produs> getProduseDinReuniuneComenzi(Integer idComanda1,
                                                           Integer idComanda2) {
        List<Produs> produse_c1 = this.getProduseDinComanda(idComanda1);
        List<Produs> produse_c2 = this.getProduseDinComanda(idComanda2);
        Set<Produs> produse_c1_union_c2 = new TreeSet<Produs>();
        produse_c1_union_c2.addAll(produse_c1);
        produse_c1_union_c2.addAll(produse_c2);
        return produse_c1_union_c2;
    }
    public Collection<Produs> getProduseComuneComenzi(Integer idComanda1,
                                                      Integer idComanda2) {
        List<Produs> produse_c1 = this.getProduseDinComanda(idComanda1);
        List<Produs> produse_c2 = this.getProduseDinComanda(idComanda2);
        Set<Produs> produse_c1_intersect_c2 = new TreeSet<Produs>();
        produse_c1_intersect_c2.addAll(produse_c1);
        produse_c1_intersect_c2.retainAll(produse_c2);
        return produse_c1_intersect_c2;
    }
    public Collection<Produs> getProduseDiferentaComenzi(Integer idComanda1,
                                                         Integer idComanda2) {
        List<Produs> produse_c1 = this.getProduseDinComanda(idComanda1);
        List<Produs> produse_c2 = this.getProduseDinComanda(idComanda2);
        Set<Produs> produse_c1_minus_c2 = new TreeSet<Produs>();
        produse_c1_minus_c2.addAll(produse_c1);
        produse_c1_minus_c2.removeAll(produse_c2);
        return produse_c1_minus_c2;
    }
    /*
     * (viii) Probleme de indexare 4 (operaţii de căutare): model de lucru şi
     * problemă individuală
     */
    Map<Integer, Produs> produseMapId = new HashMap<Integer, Produs>();
    Map<String, Produs> produseMapdenumire = new TreeMap<String, Produs>();
    public Produs getProdusMapId(Integer idProdus) {
        if (produseMapId.isEmpty()) {
            for (Produs p : this.produse)
                produseMapId.put(p.getIdProdus(), p);
        }
        return produseMapId.get(idProdus);
    }
    public Produs getProdusMapDenumire(String denumire) {
        if (produseMapdenumire.isEmpty()) {
            for (Produs p : this.produse)
                produseMapdenumire.put(p.getDenumire(), p);
        }
        return produseMapdenumire.get(denumire);
    }
    /* --------------------------------------------------------------------- */
    List<Produs> produse = new ArrayList<Produs>();
    List<Comanda> comenzi = new ArrayList<Comanda>();
    public List<Produs> getProduse() {
        return produse;
    }
    public void setProduse(List<Produs> produse) {
        this.produse = produse;
    }
    public List<Comanda> getComenzi() {
        return comenzi;
    }
    public void setComenzi(List<Comanda> comenzi) {
        this.comenzi = comenzi;
    }
    public Registru() {
        generateRandomProduse(20);
        generateRandomComenzi(30); }
    public Registru(Integer nrProduse, Integer nrComenzi) {
        generateRandomProduse(nrProduse);
        generateRandomComenzi(nrComenzi); }
    /* ---------------------------- */
    private void generateRandomProduse(Integer nrProduse) {
        Random randomPret = new Random();
        Integer pret;
        for (int i = 1; i <= nrProduse; i++) {
            pret = 50 + randomPret.nextInt(1450);
            produse.add(new Produs(i, "Produs_" + i, pret.doubleValue()));
        }
    }
    private void generateRandomComenzi(Integer nrComenzi) {
        Random randomNrArticole = new Random();
        Random randomCantitate = new Random();
        Random randomProdus = new Random();
//
        Comanda comandaRandom;
        ArticolComanda articolRandom;
        Double cantitateRandom;
        Integer produsPozRandom;
        Produs produsRandom;
        for (int i = 1; i <= nrComenzi; i++) {
            comandaRandom = new Comanda(i, LocalDate.now());
            for (int j = 1; j <= 1 + randomNrArticole.nextInt(4); j++) {
                cantitateRandom = 1.0 + randomCantitate.nextInt(100);
                produsPozRandom = randomProdus.nextInt(produse.size() - 1);
                produsRandom = produse.get(produsPozRandom);
                articolRandom = new ArticolComanda(
                        Integer.valueOf(String.valueOf(i) + j),
                        produse.get(produsPozRandom), cantitateRandom);
                comandaRandom.adaugaArticol(articolRandom);
            }
            comenzi.add(comandaRandom);
        }
    }
    //TEMA//
    public Collection<Comanda> getComandaOrdonatadupaId() {
        TreeSet<Comanda> comenziordonate = new TreeSet<Comanda>();
        comenziordonate.addAll(this.comenzi);
        return comenziordonate;
    }
    public Collection<Comanda> getProduseOrdonateDupaNrArticole() {
        List<Comanda> articoleordonate = new ArrayList<Comanda>();
        articoleordonate.addAll(this.comenzi);
        Collections.sort(articoleordonate, new ComparatorProdusNrArticole());
        return articoleordonate;
    }
    public Comanda comenziadunate (Integer idComanda1, Integer idComanda2) {
        Comanda c1 = this.getComanda(idComanda1);
        Comanda c2 = this.getComanda(idComanda2);
        Comanda comandanoua = new Comanda(31, LocalDate.now());
        for (ArticolComanda art : c1.getArticole()) {
            comandanoua.adaugaArticol(art);
        }
        for (ArticolComanda art : c2.getArticole()) {
            comandanoua.adaugaArticol(art);
        }
        return comandanoua; }

    public List<Comanda> getComenziCuProdus(Integer idProdus) {

        List<Comanda> rezultate = new ArrayList<>();
        for (Comanda c : this.comenzi) {
            for (ArticolComanda art : c.getArticole()) {
                if (art.getProdus().getIdProdus().equals(idProdus)) {
                    rezultate.add(c);
                    break;
                }
            }
        }
        return rezultate;
    }

}