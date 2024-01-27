package no.hvl.StigeogSlangeSpill;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;


public class Brett {
    private final int FIN = 100;
    private final int BEG = 1;
    Map<Integer, Integer> events = new HashMap<>();
    private boolean winner = false;

    /**
     * Initialiserings metode for brettet, her er det laget en del slanger og stiger.
     */
    public Brett() {
        // Enkel testverdier for slanger/stiger
        // I en seinere iterasjon kunne man ha implementert tilfeldig generering av slanger og stiger
        events.put(80, 19); events.put(98, -19); events.put(94, -19); events.put(93, -20);
        events.put(87, -10); events.put(50, 60); events.put(20, 88); events.put(12, 40);
        events.put(24, 4); events.put(40, 20);
    }

    /**
     * Denne metoden representerer en spillers terning kast, og de reglene som må følges per resultatet på dette kastet
     * Den tar inn en spiller som parameter
     * @param spiller spilleren som triller terning
     */
    public void terningKast(Spiller spiller) {
        // Triller terning
        int randomNr = ThreadLocalRandom.current().nextInt(1, 7);
        System.out.println(spiller.getName() + " got [" + randomNr + "] on the dice!");
        int newPlass = spiller.getPlass() + randomNr;

        //Sjekker om spiller står i fengsel, og om de har fått 6-eren de trenger for å komme ut
        if (spiller.straff(randomNr)){
            System.out.println(spiller.getName() + " gets to stay where they are.");
        }

        // Sjekker om spiller har kastet en terning som gjør at de vandrer lengere en brettet.
        // Regelen sier at da skal de stå i ro.
        if (newPlass > FIN) {
            System.out.println("Too far! Better luck next time!");
            return;
        }

        // Flytter brikke, sjekker plass om det er stiger eller slanger her.
        if(events.get(newPlass) != null) {
            newPlass = slangeEllerStige(newPlass, spiller);
        }

        // Sjekker om spillet er ferdig.
        if (newPlass == FIN) {
            System.out.println(spiller.getName() + " is the winner!!");
            winner = true;
            return;
        }

        // Endrer spillerens posisjon
        spiller.setPlass(newPlass);
        System.out.println("The new location of player " + spiller.getName() + " is: " + spiller.getPlass());

        //Sjekker om det er trillet 6, om det er trillet 6 skal spiller gå en gang til, men om det er trillet 6
        // 3 ganger skal spilleren flyttes til start posisjonen og sitte i "fengsel"
        if (randomNr == 6) {
            // straff metoden sjekker om 6 er blitt kastet 3 ganger på rad.
            if (spiller.straff(randomNr)){
                spiller.setPlass(BEG);
            } else {
                // En som kaster 6 skal trille på nytt
                terningKast(spiller);
                return;
            }
        }
        // Setter telle variabel til 0, dette reseter 6-er telleren til spilleren.
        spiller.straff(randomNr);
    }

    /**
     * Sjekker om det er deklarert en vinner, denne brukes i spill loopen.
     * @return returnerer en bool som forteller oss om spillet er over
     */
    public boolean foundWinner() {
        return winner;
    }

    private int slangeEllerStige(int newPlass, Spiller spiller) {
        // Sjekker om det er en stige eller slange avhengig om det er negativ eller posetiv heltall i events
        if (events.get(newPlass) > 0) {
            System.out.println("Player: " + spiller.getName() + " has reached a ladder!" +
                    " And is moving " + events.get(newPlass) + " places up!");
        } else {
            System.out.println("Player: " + spiller.getName() + " has angered a snake!" +
                    " And is being chased " + events.get(newPlass) + " places down!");
        }
        // Endrer plass variablet slik at den tar i betraktning stige/slange forflyttingen
        return newPlass + events.get(newPlass);
    }
}
