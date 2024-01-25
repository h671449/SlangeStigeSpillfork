package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Brett {
    private final int FIN = 100;
    private final int BEG = 1;
    Map<Integer, Integer> events = new HashMap<>();

    boolean winner = false;

    public Brett() {
        // Enkel testverdier for slanger/stiger
        events.put(10, 40);
        events.put(50, -30);
        events.put(30, -10);
        events.put(25, 31);
        events.put(12, 40);
    }

    public void terningKast(Spiller spiller) {
        // Triller terning
        int randomNr = ThreadLocalRandom.current().nextInt(1, 7);
        System.out.println(spiller.getName() + " fikk " + randomNr + " på terningen!");
        int newPlass = spiller.getPlass() + randomNr;

        // Sjekker om spillet er ferdig.
        if (newPlass == FIN) {
            System.out.println("Dette er vinneren");
            winner = true;
            return;
        }

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
            newPlass = newPlass + events.get(newPlass);
        }

        spiller.setPlass(newPlass);
        //Sjekker om det er trillet 6
        if (randomNr == 6) {
            if (spiller.straff(randomNr)){
                spiller.setPlass(BEG);
            } else {
                terningKast(spiller);
                return;
            }
        }
        spiller.straff(randomNr);
    System.out.println("The new location of player " + spiller.getName() + " is: " + spiller.getPlass());
    }

    public boolean foundWinner() {
        return winner;
    }
}
