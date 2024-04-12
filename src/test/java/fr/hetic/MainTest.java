package fr.hetic;

import static fr.hetic.Main.*;
import static org.junit.jupiter.api.Assertions.*;

import fr.hetic.model.Personne;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MainTest {
        @Test
        // Test the method trierParPrenom
        public void testTrierParPrenom() {
                List<Personne> personnes = trierParPrenom(personnes());
                assertEquals("Eric", personnes.get(0).getPrenom());
                assertEquals("Jean", personnes.get(1).getPrenom());
                assertEquals("Marie", personnes.get(2).getPrenom());
                assertEquals("Timo", personnes.get(3).getPrenom());
        }

    private List personnes() {
        initPopulation();
        return personnes;
    }
}
