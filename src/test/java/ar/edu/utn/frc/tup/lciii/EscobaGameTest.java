package ar.edu.utn.frc.tup.lciii;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.Scanner;

import org.junit.platform.commons.util.ReflectionUtils;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;


class EscobaGameTest {

    Scanner scanner = Mockito.mock(Scanner.class);

    EscobaGame escobaGame = Mockito.spy(EscobaGame.class);

    private final PrintStream systemOut = System.out;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
        escobaGame.setScanner(scanner);
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setOut(systemOut);
    }

    @Test
    void welcomeMessage() {
        String expected = "Welcome to the 'Escoba de 15' game!" + System.lineSeparator();
        escobaGame.welcomeMessage();
        assertEquals(expected, getOutput());
    }

    @Test
    void createHumanUser() {
        when(scanner.nextLine()).thenReturn("Hernán");
        User user = escobaGame.createHumanUser();
        assertEquals("Hernán", user.getName());
    }

    @Test
    void createAppUser() {
        User user = escobaGame.createAppUser();
        assertEquals("APP", user.getName());
    }

    @Test
    void wantPlayAgain_TrueAnswer() {
        when(scanner.nextLine()).thenReturn("y");
        Boolean result = escobaGame.wantPlayAgain();
        assertTrue(result);
    }

    @Test
    void wantPlayAgain_FalseAnswer() {
        when(scanner.nextLine()).thenReturn("n");
        Boolean result = escobaGame.wantPlayAgain();
        assertFalse(result);
    }

    @Test
    void getYesNoAnswerTest_YesAnswer() {
        EscobaGame escobaGame1 = new EscobaGame();
        Optional<Method> optionalMethod = ReflectionUtils.findMethod(EscobaGame.class, "getYesNoAnswer", String.class);
        Boolean result1 = null;
        Boolean result2 = null;

        if (optionalMethod.isPresent()) {
            result1 = (Boolean) ReflectionUtils.invokeMethod(optionalMethod.get(), escobaGame1, "n");
            result2 = (Boolean) ReflectionUtils.invokeMethod(optionalMethod.get(), escobaGame1, "N");
        } else {
            fail("Method getYesNoAnswer not found");
        }
        // TODO: Implementar el test para el método getYesNoAnswer de manera tal que se
        //  pruebe que el método retorna false si se ingresa "n" o "N"
        assertFalse(result1 && result2);
    }

    @Test
    void getYesNoAnswerTest_NoAnswer() {
        EscobaGame escobaGame = new EscobaGame();
        Optional<Method> optionalMethod = ReflectionUtils.findMethod(escobaGame.getClass(), "getYesNoAnswer", String.class);
        Boolean result = null;
        Boolean result2 = null;

        if (optionalMethod.isPresent()) {
            result = (Boolean) ReflectionUtils.invokeMethod(optionalMethod.get(), escobaGame, "y");
            result2 = (Boolean) ReflectionUtils.invokeMethod(optionalMethod.get(), escobaGame, "Y");
        } else {
            // TODO: Implementar el test para el método getYesNoAnswer de manera tal que se
            //  pruebe que el método retorna true si se ingresa "y" o "Y"
            fail("Method getYesNoAnswer not found");
        }
        assertTrue(result && result2);
    }

    @Test
    void getYesNoAnswerTest_NullAnswer() {

        EscobaGame escobaMatchRound = new EscobaGame(); // Creo la clase, en caso de que lleve argumentos uso Mockito() como argumentos

        Optional<Method> optionalMethod = ReflectionUtils.findMethod(EscobaGame.class, "getYesNoAnswer", String.class);//Agrego
        // el Optional<Method> y le modifico los argumentos, con la clase, el nombre del emtodo y el tipo de dato q usa el metodo)

        Boolean result = true;//creo una variable del tipo q me devuelve el metodo.

        if (optionalMethod.isPresent()) {
            result = (Boolean) ReflectionUtils.invokeMethod(optionalMethod.get(), escobaMatchRound, "q");//invoco al metodo,
            // pasandole el argumento a controlar. En mi caso "q".
        } else {
            fail("Method getYesNoAnswer not found");
        }

        assertNull(result);

        // TODO: Implementar el test para el método getYesNoAnswer de manera tal que se
        //  pruebe que el método retorna null si se ingresa algo distinto de "y", "Y", "n" o "N"

    }

    private String getOutput() {
        return testOut.toString();
    }
}