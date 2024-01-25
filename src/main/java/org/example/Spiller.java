package org.example;

public class Spiller {
    private int plass;
    private int antallS;
    private String navn;

    private boolean erFengslet;

    /**
     * Initialiserer spiller, tar inn navn på spiller.
     * @param navn
     */
    public Spiller(String navn) {
        this.navn = navn;
        plass = 1;
        antallS = 0;
        erFengslet = false;
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

    /**
     * Tar inn terningkast fra brett, sjekker om det er en 6-er, og inkrementerer antallS som passer på hvor mange 6-ere
     * som har kommet på rad. Setter antallS til 0 om terning != 6, sjekker antallS og rerturnerer en bool avhengig
     * om antallS er > 2 eller ikke.
     *
     * Denne metoden er også ansvarlig for å sjekke om spiller kan flytte seg fra start etter å få 3 6-ere på rad.
     * @param kast
     * @return
     */
    public boolean straff(int kast) {
        if(kast == 6 && antallS < 3) {
            antallS++;
        } else if (kast != 6 && antallS > 2){
            System.out.println("Whoops! Better luck next time!");
        } else {
            antallS = 0;
        }
        return antallS > 2;
    }
}
