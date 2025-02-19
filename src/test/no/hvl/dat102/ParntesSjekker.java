package no.hvl.dat102;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ParentesSjekkerTest {
    ParentesSjekker parentesSjekker = new ParentesSjekker();

    @Test
    public void testKorrektParenteser() {
        String input = "{ [ ( ) ] }";
        assertTrue(parentesSjekker.sjekkParenteser(input), "Skal returnere true for korrekt parentesoppsett");
    }

    @Test
    public void testManglerSluttparentes() {
        String input = "{ [ ( ) ]";
        assertFalse(parentesSjekker.sjekkParenteser(input), "Skal returnere false når det mangler sluttparentes");
    }

    @Test
    public void testManglerStartparentes() {
        String input = "[ ( ) ] }";
        assertFalse(parentesSjekker.sjekkParenteser(input), "Skal returnere false når det mangler startparentes");
    }

    @Test
    public void testSluttparentesForTidlig() {
        String input = "{ [ ( ] ) }";
        assertFalse(parentesSjekker.sjekkParenteser(input), "Skal returnere false når sluttparentes kommer for tidlig");
    }

    @Test
    public void testKorrektJavaProgram() {
        String javaprogram = """
            class HelloWorld {
                public static void main(String[] args) {
                    System.out.println("Hello World!");
                }
            }
            """;
        assertTrue(parentesSjekker.sjekkParenteser(javaprogram), "Skal returnere true for korrekt parentesoppsett i et Java-program");
    }
}