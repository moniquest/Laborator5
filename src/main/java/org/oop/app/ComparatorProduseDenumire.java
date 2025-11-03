package org.oop.app;

import java.util.Comparator;

public class ComparatorProduseDenumire implements Comparator<Produs> {
    @Override
    public int compare(Produs p1, Produs p2) {return p1.getDenumire().compareTo(p2.getDenumire());
    }
}