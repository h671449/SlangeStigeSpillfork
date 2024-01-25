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

        while (!gyldigSpill) {
            System.out.println("Hvor mange spillere er det ønsket? Min 2, Maks 4");
            antall = scanInt.nextInt();
            if (antall < 2 || 4 < antall){
                System.out.println("Ugyldig antall!");
            }
            gyldigSpill = (antall < 2 || 4 < antall) ? false : true;
        }
        spillere = new Spiller[antall];
        for (int i = 0; i < spillere.length; i++) {
            System.out.println("Navn på spiller " + (i+1));
            String navn = scan.nextLine();
            spillere[i] = new Spiller(navn);
            System.out.println("Håper du har det gøy " + spillere[i].getName() + "!");
        }

        System.out.println("Spillet begynner!");
        while (!nyttSpill.winner) {
            for (int i = 0; i < spillere.length; i++) {
                System.out.println("Spiller: " + spillere[i].getName() + " sinn tur!\n" + "Press enter når du er klar til å trille!");
                scan.nextLine();
                System.out.println("Triller...");
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                nyttSpill.terningKast(spillere[i]);
                System.out.println();
            }

        }
        scan.close();
        scanInt.close();
    }
}