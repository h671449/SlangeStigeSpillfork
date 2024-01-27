package no.hvl.StigeogSlangeSpill;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpillerTest {

    Spiller spiller1;
    @BeforeEach
    void setup() {
        spiller1 = new Spiller("Tester");
    }

    @Test
    void straff3PaaRad() {
        // Tester straff() metoden, denne sjekker om spiller har trillet 6, 3 ganger på rad
        // Den øker et tellevarabel inne i objektet hver gang det trilles en 6, og setter variablet til 0 om det får en ikke 6-er.

        assertEquals(false, spiller1.straff(6));
        assertEquals(false, spiller1.straff(6));
        assertEquals(true, spiller1.straff(6));
    }

    @Test
    void straffIkkePaRad() {

        assertEquals(false, spiller1.straff(6));
       assertEquals(false, spiller1.straff(2));
       assertEquals(false, spiller1.straff(6));
    }

    @Test
    void slippeUtAvFangsel(){
        assertEquals(false, spiller1.straff(6));
        assertEquals(false, spiller1.straff(6));
        assertEquals(true, spiller1.straff(6));
        assertEquals(false, spiller1.straff(6));
    }
    @Test
    void slippeIkkeUtAvFangsel(){
        assertEquals(false, spiller1.straff(6));
        assertEquals(false, spiller1.straff(6));
        assertEquals(true, spiller1.straff(6));
        assertEquals(true, spiller1.straff(2));
    }
}