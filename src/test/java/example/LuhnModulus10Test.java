package example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Os números dos cartões foram gerados usando as ferramentas:
 * https://www.4devs.com.br/gerador_de_numero_cartao_credito
 * https://www.fordevs.com.br/gerador-de-cartao-de-credito
 */
public class LuhnModulus10Test {

    private static final String validMastercard = "5434234959738204";
    private static final String validVisa = "4916905399159941";
    private static final String validAmericanExpress = "372340195056668";
    private static final String validDinersClub = "38081353709724";
    private static final String validDiscover = "6011398987673326";
    private static final String validHiperCard = "6062826750626970";
    private static final String validElo = "4389354088985860";

    private static final String validVisa1 = "4111111111111111";
    private static final String validVisa2 = "4012888888881881";
    private static final String validAmex1 = "378282246310005";
    private static final String validDiscover1 = "6011111111111117";
    private static final String validMastercard1 = "5105105105105100";

    private static final String invalidVisa1 = "4111111111111";
    private static final String invalidMastercard1 = "5105105105105106";
    private static final String invalidGeneric1 = "9111111111111111";

    private static final LuhnModulus10 validator = new LuhnModulus10();

    @Test void cardsTest() {
        assertTrue(validator.isValid(validMastercard));
        assertTrue(validator.isValid(validVisa));
        assertTrue(validator.isValid(validAmericanExpress));
        assertTrue(validator.isValid(validDinersClub));
        assertTrue(validator.isValid(validDiscover));
        assertTrue(validator.isValid(validHiperCard));
        assertTrue(validator.isValid(validElo));

        assertTrue(validator.isValid(validVisa1));
        assertTrue(validator.isValid(validVisa2));
        assertTrue(validator.isValid(validAmex1));
        assertTrue(validator.isValid(validDiscover1));
        assertTrue(validator.isValid(validMastercard1));

        assertFalse(validator.isValid(invalidVisa1));
        assertFalse(validator.isValid(invalidMastercard1));
        assertFalse(validator.isValid(invalidGeneric1));
    }

    @Test void emptyNumberTest() {
        assertFalse(validator.isValid(""));
    }

    @Test void nummNumberTest() {
        assertFalse(validator.isValid(null));
    }

    @ParameterizedTest(name = "Run {index}: cardNumber={0}")
    @MethodSource("validCards_Parameters")
    void validCardsWithParamsTest(String cardNumber) throws Throwable {
        assertTrue(validator.isValid(cardNumber));
    }

    static Stream<Arguments> validCards_Parameters() throws Throwable {
        return Stream.of(
                Arguments.of(validMastercard),
                Arguments.of(validVisa),
                Arguments.of(validAmericanExpress),
                Arguments.of(validDinersClub),
                Arguments.of(validDiscover),
                Arguments.of(validHiperCard),
                Arguments.of(validElo)
        );
    }

}
