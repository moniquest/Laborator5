package org.oop.app;
import java.util.Collection;
import java.util.List;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main(String[] args) {
        /* (ii) For-uri de populare (random): generaţi (drept obiecte de test)
         * - 10 produse cu preturi intre 50 si 1500;
         * - 100 comenzi conţinând între 1 şi 5 articole.
         *
         * Clasa Registru continand cod skeleton
         */
        Registru registru = new Registru();
// Afiseaza comenzi generate//
        System.out.println("Afizeaza comenzi : ******************************************");
        for(Comanda c: registru.getComenzi()){
            System.out.println(c);
            for(ArticolComanda a: c.getArticole()){
                System.out.println(a.getProdus() + ": " + a.getCantitate() + ": " +
                        a.getProdus().getPretUnitar());
            }
            System.out.println("----------------------------------------");
        }
        /* (iv) Probleme de ordonare*/
        System.out.println("Afizeaza produse ordonate dupa idProdus : *******************");
        Collection<Produs> produseOrdonate = registru.getProduseOrdonateDupaId();
        for(Produs p: produseOrdonate){
            System.out.println(p);
        }
        System.out.println("Afizeaza produse ordonate dupa denumire : *******************");
        produseOrdonate = registru.getProduseOrdonateDupaDenumire();
        for(Produs p: produseOrdonate){
            System.out.println(p);
        }
// TODO
        System.out.println("Afizeaza comenzi ordonate dupa valoareTotala : **************");
        /* (v) Probleme de interogare 1 (operaţii de căutare simplă în colecţii) */
        System.out.println("Afizeaza produsul cu id 5 si comanda 10: ********************");
        try {
            System.out.println(registru.getProdus(10000));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(registru.getComanda(10));
        /* (vi) Probleme de interogare 2 (operaţii cu mulţimi) */
// Articole din comenzile 4 si 5
        System.out.println("Afizeaza produse din comenzile 4 si 5: **********************");
        Collection<Produs> produse_c4_union_c5 = registru.getProduseDinReuniuneComenzi(4,5);
        for (Produs p: produse_c4_union_c5)
            System.out.println(p);
        System.out.println("Afizeaza produse comune comenzilor 4 si 5: ******************");
        Collection<Produs> produse_c4_intersect_c5 = registru.getProduseComuneComenzi(4, 5);
        for (Produs p: produse_c4_intersect_c5)
            System.out.println(p);
        System.out.println("Afizeaza produse din comanda 4, dar nu si din 5: ************");
        Collection<Produs> produse_c4_minus_c5 = registru.getProduseDiferentaComenzi(5, 4);
        for (Produs p: produse_c4_minus_c5)
            System.out.println(p);
        /* (viii) Probleme de indexare 4 (operaţii de căutare) */
        System.out.println("Afizeaza produse cu id 8 sau numele Produs_8: ***************");
        System.out.println(registru.getProdusMapId(8));
        System.out.println(registru.getProdusMapDenumire("Produs_8"));


        //TEMA//

        //1.Comenzi ordonate dupa idComenzi//
        System.out.println("Afizeaza Comenzi ordonate dupa idComanda : *******************");
        Collection<Comanda> comenziordonate = registru.getComandaOrdonatadupaId();
        for(Comanda p: comenziordonate){
            System.out.println(p);
        }
        //2//
        // --- TEMA 2: Testare ordonare specială (Nr. Articole, apoi ID) ---
        System.out.println("Afiseaza comenzi ordonate dupa Nr. Articole, apoi ID: *******");
        Collection<Comanda> comenziSortateSpecial = registru.getProduseOrdonateDupaNrArticole();
        for (Comanda c : comenziSortateSpecial) {
            System.out.println(c + " [Nr. Articole: " + c.getArticole().size() + "]");
        }
        //3//
        System.out.println("Afisaza comanda cumulata din 4 si 5: ********* ");
        Comanda comandaCumulata = registru.comenziadunate(6, 7);
        System.out.println(comandaCumulata);
        System.out.println("Articolele din comanda cumulată:");
        for (ArticolComanda art : comandaCumulata.getArticole()) {
            System.out.println("   -> " + art); }
        //4//
        System.out.println("Afisaza comenzile care contin id 5: ********* ");
        List<Comanda> comenzigasite=registru.getComenziCuProdus(5);
        if(!comenzigasite.isEmpty()){System.out.println("Nu exista comenzi!");}
        for (Comanda c : comenzigasite) {
            System.out.println(c);
        }
    }

}
