package org.oop.app;

import java.util.Comparator;

public class ComparatorProdusNrArticole implements Comparator<Comanda> {
    @Override
    public int compare(Comanda c1, Comanda c2) {

    int nrarticole1=c1.getArticole().size();
    int nrarticole2=c2.getArticole().size();
    if(nrarticole1!=nrarticole2)
    {return  Integer.compare(nrarticole1,nrarticole2);}
    else{ return c1.getIdComanda().compareTo(c2.getIdComanda());}
    }
}

