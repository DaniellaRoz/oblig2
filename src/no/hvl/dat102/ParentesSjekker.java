package no.hvl.dat102;

import java.util.EmptyStackException;

public class ParentesSjekker {
    public boolean sjekkParenteser(String s) {
        LenketStabel<Character> parantesStabel = new LenketStabel<>();

        for (char c : s.toCharArray()) {
            switch (finnParantesType(c)) {
                case AAPEN -> parantesStabel.push(c);
                case LUKKET -> {
                    boolean matcherAapning = false;
                        try {
                            switch (c) {
                                case ')' -> {
                                    if (parantesStabel.peek().equals('(')) matcherAapning = true;
                                }
                                case ']' -> {
                                    if (parantesStabel.peek().equals('[')) matcherAapning = true;
                                }
                                case '}' -> {
                                    if (parantesStabel.peek().equals('{')) matcherAapning = true;
                                }
                            }
                        } catch (EmptyStackException e) {
                            // Hvis stablen er tom når det er parantes å sjekke fortsatt, vet vi det ikke er en jevn mengde paranteser
                            return false;
                        }
                    if (matcherAapning) {
                        parantesStabel.pop();
                    } else {
                        return false;
                    }
                }
                default -> {} // Gjør ingenting.
            }
        }

        // Lar bare den returnere true hvis stabelen er tom, hvis det er parantes igjen har ikke alle "matchet".
        return parantesStabel.isEmpty();
    }

    private enum ParantesType {
        AAPEN, LUKKET, INGEN;
    }

    private ParantesType finnParantesType(char c) {
        return switch(c) {
            case '(', '[', '{' -> ParantesType.AAPEN;
            case ')', ']', '}' -> ParantesType.LUKKET;
            default -> ParantesType.INGEN;
        };
    }
}
