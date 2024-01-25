package org.example;

import java.util.Scanner;

import static java.lang.Thread.*;

public class StigeogSlangeSpill {
    public static void main(String[] args) {

        int antall = 0;
        Scanner scanInt = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        Brett nyttSpill = new Brett();
        boolean gyldigSpill = false;
        Spiller[] spillere;

        // While lokken er brukt for å sikre at brukere gir et antall spillere for de fortsetter.
        while (!gyldigSpill) {
            System.out.println("How many players do you want? Min 2, Maxs 4");
            antall = scanInt.nextInt();
            if (antall < 2 || 4 < antall){
                System.out.println("Invalid number!");
            }
            gyldigSpill = (antall < 2 || 4 < antall) ? false : true;
        }
        // Etter at antall spillere er registrert, må brukere gi navn. Vi gjør dette gjennom en for lokke
        spillere = new Spiller[antall];
        for (int i = 0; i < spillere.length; i++) {
            System.out.println("Name of the player: " + (i+1));
            String navn = scan.nextLine();
            spillere[i] = new Spiller(navn);
            System.out.println("Hope you have fun, " + spillere[i].getName() + "!");
        }
        // Gjennbruker antall variablet for a telle runder
        antall = 1;
        System.out.println("The game begins!");
        // Dette er selve game loopen, while brukes for å holde spillet i gang, for loopen brukes for å velge spilleren
        // som skal spille. sleep metoden brukes for å "simulere" selve kastet på terningen.
        while (!nyttSpill.foundWinner()) {
            System.out.println("*****************");
            System.out.println("Round " + antall);
            System.out.println("*****************");
            for (int i = 0; i < spillere.length; i++) {
                System.out.println("Player: " + spillere[i].getName() + "'s turn! You are at " + spillere[i].getPlass()
                        + "\n" + "Press enter when you are ready to throw the dice!");

                scan.nextLine();

                System.out.println("Dice are thrown...");
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                nyttSpill.terningKast(spillere[i]);
                if (nyttSpill.foundWinner()){
                    break;
                }
                System.out.println();
            }
            antall++;
        }
        scan.close();
        scanInt.close();
    }
}