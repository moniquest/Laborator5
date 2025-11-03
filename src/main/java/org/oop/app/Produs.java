package org.oop.app;

import lombok.*;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@RequiredArgsConstructor
public class Produs implements Comparable<Produs>{
    @EqualsAndHashCode.Include
    @NonNull protected Integer idProdus;
    @NonNull private String denumire;
    @NonNull private Double pretUnitar;
    // Comparable dupa idPodus
    @Override
    public int compareTo(Produs other) {
// Produse ordonate dupa id
        return this.idProdus.compareTo(other.getIdProdus());
// Produse ordonate dupa id reverse
//return -this.idProdus.compareTo(other.getIdProdus());
    }
}



