package org.oop.app;
import java.time.LocalDate;

import lombok.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor

public class ArticolComanda {
    @EqualsAndHashCode.Include
    private Integer id;
    Produs produs;
    Double cantitate;
    @Setter(AccessLevel.NONE)
    private Double valoareArticol;
    private Double calculValoare(){
        Double valoare = 0.0;
        if (produs != null && cantitate !=null)
            valoare = produs.getPretUnitar() * cantitate;
        return valoare;
    }
    public Double getValoareArticol(){
        if (valoareArticol == null)
            valoareArticol = calculValoare();
        return valoareArticol;
    }
    public void setProdus(Produs produs) {this.produs = produs;
        this.valoareArticol = calculValoare();
    }
    public void setCantitate(Double cantitate) {
        this.cantitate = cantitate;
        this.valoareArticol = calculValoare();
    }
    public ArticolComanda(Integer id, Produs produs, Double cantitate) {
        this.id = id;
        this.produs = produs;
        this.cantitate = cantitate;
        this.valoareArticol = calculValoare();
    }
}