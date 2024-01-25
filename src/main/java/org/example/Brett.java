package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;


public class Brett {
    private final int FIN = 100;
    private final int BEG = 1;
    Map<Integer, Integer> events = new HashMap<>();
    private boolean winner = false;

    /**
     * Initialiserings metdode for brettet, her er det laget en del slanger og stiger.
     */
    public Brett() {
        // Enkel testverdier for slanger/stiger
        events.put(10, 40);
        events.put(50, -30);
        events.put(30, -10);
        events.put(25, 31);
        events.put(12, 40);
    }

    /**
     * Denne metoden representerer en spillers terning kast, og de reglene som må følges per resultatet på dette kastet
     * Den tar inn en spiller som parameter
     * @param spiller spilleren som triller terning
     */
    public void terningKast(Spiller spiller) {
        // Triller terning
        int randomNr = ThreadLocalRandom.current().nextInt(1, 7);
        System.out.println(spiller.getName() + " got " + randomNr + " on the dice!");
        int newPlass = spiller.getPlass() + randomNr;

        if (spiller.straff(randomNr)){
            System.out.println(spiller.getName() + " gets to stay where they are.");
        }

        // Sjekker om spillet er ferdig.
        if (newPlass == FIN) {
            System.out.println(spiller.getName() + " is the winner!!");
            winner = true;
            return;
        }
        // Sjekker om spiller har kastet en terning som gjør at de vandrer lengere en brettet.
        if (newPlass > FIN) {
            System.out.println("Too far! Better luck next time!");
            return;
        }

        // Flytter brikke, sjekker plass
        if(events.get(newPlass) != null) {
            if (events.get(newPlass) > 0) {
                System.out.println("Player: " + spiller.getName() + " has reached a ladder!" +
                        " And is moving " + events.get(newPlass) + " places up!");
            } else {
                System.out.println("Player: " + spiller.getName() + " has angered a snake!" +
                        " And is being chased " + events.get(newPlass) + " places down!");
            }
            // Ligger til, eller trekker fra antall plasser, dette er stiger eller slanger
            newPlass = newPlass + events.get(newPlass);
        }

        spiller.setPlass(newPlass);
        System.out.println("The new location of player " + spiller.getName() + " is: " + spiller.getPlass());
        //Sjekker om det er trillet 6
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
        // Setter telle variabel til 0
        spiller.straff(randomNr);
    }

    /**
     * Sjekker om det er deklarert en vinner, denne brukes i spill loopen.
     * @return returnerer en bool som forteller oss om spillet er over
     */
    public boolean foundWinner() {
        return winner;
    }
}
