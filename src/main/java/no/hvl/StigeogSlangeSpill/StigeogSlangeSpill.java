package no.hvl.StigeogSlangeSpill;

import java.util.Scanner;

import static java.lang.Thread.*;

public class StigeogSlangeSpill {

    public static void main(String[] args) {

        int antall = 0;
        Scanner scanInt = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        Brett nyttSpill = new Brett();
        Spiller[] spillere;

        // Velg antall spillere
        antall = antallSpillere(scanInt);

        spillere = new Spiller[antall];

        //Velg navn på spillere
        velgNavn(spillere, scan);

        // Gjennbruker antall variablet for a telle runder
        antall = 1;
        System.out.println("The game begins!");

        // Dette er selve game loopen, while brukes for å holde spillet i gang, for loopen brukes for å velge spilleren
        // som skal spille. sleep metoden brukes for å "simulere" selve kastet på terningen.

        spillLoop(nyttSpill, spillere, scan, antall);

        scan.close();
        scanInt.close();
    }

    /**
     * Dette er en hjelpe metode brukt i oppstart av spill
     * @param scanInt Scanner som brukes for å velge antall spillere
     * @return returnerer antall spillere i spillet
     */
    private static int antallSpillere(Scanner scanInt) {
        // While lokken er brukt for å sikre at brukere gir et antall spillere for de fortsetter.
        boolean gyldigSpill = false;
        int antall = 0;
        while (!gyldigSpill) {
            System.out.println("How many players do you want? Min 2, Maxs 4");
            antall = scanInt.nextInt();
            if (antall < 2 || 4 < antall){
                System.out.println("Invalid number!");
            }
            gyldigSpill = antall >= 2 && 4 >= antall;
        }
            return antall;
    }

    /**
     * Denne metoden brukes for å få navn av bruker
     * @param spillere dette er deltagerene
     * @param scan denne scanner classen brukes for å skrive inn navn
     */
    private static void velgNavn(Spiller[] spillere, Scanner scan) {
        // Etter at antall spillere er registrert, må brukere gi navn. Vi gjør dette gjennom en for lokke
        for (int i = 0; i < spillere.length; i++) {
            System.out.println("Name of the player: " + (i+1));
            String navn = scan.nextLine();
            spillere[i] = new Spiller(navn);
            System.out.println("Hope you have fun, " + spillere[i].getName() + "!");
        }
    }

    /**
     * Dette er selve spill loopen, det er her spillet foregår
     * @param spill dette er brettet spillet tar plass på
     * @param spillere spillerene som deltar
     * @param scan Scanner metode brukes når bruker "triller"
     * @param antall Denne variabelen brukes for å ha kontroll på runder, f.eks Runde 1
     */
    private static void spillLoop(Brett spill, Spiller[] spillere, Scanner scan, int antall) {
        while (!spill.foundWinner()) {
            System.out.println("*****************");
            System.out.println("Round " + antall);
            System.out.println("*****************");

            for (Spiller spiller : spillere) {
                System.out.println("Player: " + spiller.getName() + "'s turn! You are at " + spiller.getPlass()
                        + "\n" + "Press enter when you are ready to throw the dice!");

                // Kommenter ut for rask simulasjon
                //scan.nextLine();

                System.out.print("Dice are thrown");
                // Simulerer tid i terningkastet
                try {
                    sleep(300);
                    System.out.print(".");
                    sleep(300);
                    System.out.print(".");
                    sleep(300);
                    System.out.println(".");
                    sleep(350);
                    System.out.println();

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                spill.terningKast(spiller);
                // Vi trenger ikke la de andre trille når vi allerede veit vinner
                if (spill.foundWinner()) {
                    break;
                }
                System.out.println();
            }
            antall++;
        }
    }
}