package ar.edu.utn.frc.tup.lciii;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    void createDeckTest() {
        Deck deck = new Deck();
        // Comprueba que el mazo se crea con 40 cartas
        assertEquals(40, deck.getCards().size());

        // Comprueba que no se incluyen los números 8 y 9
        for (Card card : deck.getCards()) {
            assertFalse(card.getNumber() == 8 || card.getNumber() == 9);
        }

        // Crea un mazo de referencia con todas las cartas de un mazo de 40 cartas
        Deck referenceDeck = new Deck();
        Stack<Card> referenceCards = referenceDeck.getCards();

        // Remueve las cartas 8 y 9 del mazo de referencia
        referenceCards.removeIf(card -> card.getNumber() == 8 || card.getNumber() == 9);

        // Comprueba que todas las cartas del mazo de referencia están presentes en el mazo creado
        assertTrue(deck.getCards().containsAll(referenceCards));
        // TODO: Crear un test que valide que el mazo se crea con 40 cartas,
        //  que no se incluyen los 8 y 9.
        //  Validar que todas las cartas de un mazo de 40 cartas estén presentes.
    }

    @Test
    void takeCardTest() {
        Deck deck = new Deck();
        int initialSize = deck.getCards().size();

        // Toma una carta del mazo
        Card topCard = deck.getCards().peek();
        Card takenCard = deck.takeCard();

        // Verifica que la cantidad de cartas en el mazo disminuya en 1
        assertEquals(initialSize - 1, deck.getCards().size());

        // Verifica que la carta tomada sea la que se esperaba (la que está en la parte superior de la pila)
        assertEquals(topCard, takenCard);
        // TODO: Crear un test que valide que al tomar una carta del mazo,
        //  la cantidad de cartas en el mazo disminuye en 1 y que la carta tomada
        //  es la que se esperaba; es decir la que está al tope de la pila.
        //fail("Not implemented yet");
    }

    @Test
    void isEmptyTest() {
        Deck deck = new Deck();

        deck.getCards().clear();
        assertTrue(deck.isEmpty());

        Card card = new Card(CardSuit.BASTOS,5,1);
        deck.getCards().add(card);

        assertFalse(deck.isEmpty());
        // TODO: Crear un test que valide que el mazo está vacío cuando no tiene cartas
        //  y que no está vacío cuando tiene al menos una carta.
    }

/*
    @Test
     void shuffleDeckTest() {
        // Crea un mazo
        Deck deck = new Deck();

        // Crea una copia del mazo antes de mezclarlo
        List<Card> originalOrder = new ArrayList<>(deck.getCards());

        // Mezcla el mazo
        deck.shuffleDeck();

        // Obtiene el nuevo orden de las cartas después de mezclar
        List<Card> shuffledOrder = deck.getCards();

        // Verifica que las dos listas (el orden original y el orden mezclado) sean diferentes
        assertNotEquals(originalOrder, shuffledOrder);
        // TODO: Crear un test que valide que al mezclar el mazo, las cartas no están en el mismo orden
        //  que al crear el mazo.
        //fail("Not implemented yet");
    } */

    @Test
    void shuffleDeckTest2() throws Exception {
        // Crea un mazo
        Deck deck = new Deck();

        // Copia el mazo original
        Stack<Card> originalOrder = new Stack<>();
        originalOrder.addAll(deck.getCards());

        // Utiliza reflexión para obtener el método shuffleDeck()
        Method shuffleMethod = Deck.class.getDeclaredMethod("shuffleDeck");
        shuffleMethod.setAccessible(true); // Hace que el método sea accesible

        // Invoca el método shuffleDeck() utilizando reflexión
        shuffleMethod.invoke(deck);

        // Verifica que las cartas no estén en el mismo orden
        assertNotEquals(originalOrder, deck.getCards());
    }
}