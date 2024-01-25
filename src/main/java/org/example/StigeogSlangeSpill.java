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
            System.out.println("Hvor mange spillere er det ønsket? Min 2, Maks 4");
            antall = scanInt.nextInt();
            if (antall < 2 || 4 < antall){
                System.out.println("Ugyldig antall!");
            }
            gyldigSpill = (antall < 2 || 4 < antall) ? false : true;
        }
        // Etter at antall spillere er registrert, må brukere gi navn. Vi gjør dette gjennom en for lokke
        spillere = new Spiller[antall];
        for (int i = 0; i < spillere.length; i++) {
            System.out.println("Navn på spiller " + (i+1));
            String navn = scan.nextLine();
            spillere[i] = new Spiller(navn);
            System.out.println("Håper du har det gøy " + spillere[i].getName() + "!");
        }
        // Gjennbruker antall variablet for a telle runder
        antall = 1;
        System.out.println("Spillet begynner!");
        // Dette er selve game loopen, while brukes for å holde spillet i gang, for loopen brukes for å velge spilleren
        // som skal spille. sleep metoden brukes for å "simulere" selve kastet på terningen.
        while (!nyttSpill.foundWinner()) {
            System.out.println("*****************");
            System.out.println("Runde " + antall);
            System.out.println("*****************");
            for (int i = 0; i < spillere.length; i++) {
                System.out.println("Spiller: " + spillere[i].getName() + " sinn tur! Du står på plass " + spillere[i].getPlass()
                        + "\n" + "Press enter når du er klar til å trille!");

                scan.nextLine();

                System.out.println("Triller...");
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