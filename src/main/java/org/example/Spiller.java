package org.example;

public class Spiller {
    private int plass;
    private int antallS;
    private String navn;

    private boolean erFengselt;

    public Spiller(String navn) {
        this.navn = navn;
        plass = 1;
        antallS = 0;
        erFengselt = false;
    }

    public int getPlass() {
        return plass;
    }

    public void setPlass(int plass) {
        this.plass = plass;
    }

    public String getName() {
        return navn;
    }

    public boolean straff(int kast) {
        if(kast == 6) {
            antallS++;
        } else if (kast != 6 && antallS > 2){
            System.out.println("Whoops! Better luck next time!");
        } else {
            antallS = 0;
        }
        return antallS > 2;
    }
}
