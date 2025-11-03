package org.oop.app;

import lombok.*;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@RequiredArgsConstructor
public class Comanda implements Comparable<Comanda> {
    @EqualsAndHashCode.Include
    @NonNull private Integer idComanda;
    @NonNull private LocalDate dataComanda;
    //
    private List<ArticolComanda> articole = new ArrayList<ArticolComanda>();
    /* Adaugare proprietate valoare totala*/
    @Setter(AccessLevel.NONE)
    private Double valoareTotala;
    private Double calculValoareTotala(){
        Double valoare = 0.0;
        for (ArticolComanda articol: articole)
            valoare += articol.getValoareArticol();
        return valoare;
    }
    public Double getValoareTotala(){
        if (valoareTotala == null)
            this.valoareTotala = calculValoareTotala();
        return valoareTotala;
    }
    /* Adaugare operatie manipulare detalii colectie*/
    public void adaugaArticol(ArticolComanda articol){
        if(!this.articole.contains(articol)) {
            this.articole.add(articol);
            this.valoareTotala = calculValoareTotala();
        }
    }
    public void stergeArticol(ArticolComanda articol){
        if(!this.articole.contains(articol)) {
            this.articole.remove(articol);
            this.valoareTotala = calculValoareTotala();
        }
    }
//
    @Override
    public String toString() {
        return "Comanda " + this.idComanda + " valoare " + this.getValoareTotala();
    }
    public int compareTo(Comanda other) {
// Produse ordonate dupa id
        return this.idComanda.compareTo(other.getIdComanda());
} }