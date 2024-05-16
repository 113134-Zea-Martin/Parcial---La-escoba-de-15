package ar.edu.utn.frc.tup.lciii;

import ar.edu.utn.frc.tup.lciii.round.EscobaMatchRound;
import ar.edu.utn.frc.tup.lciii.round.RoundPlayer;

import java.util.Scanner;

/**
 * Esta clase representa una partida completa del juego.
 */
public class EscobaMatch {

    /**
     * Scanner para capturar las entradas del usuario
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Jugador asignado al usuario
     */
    private User humanUser;

    /**
     * Jugador asignado a la app
     */
    private User appUser;

    /**
     * Puntos del jugador humano
     */
    private Integer humanUserPoints;

    /**
     * Puntos de la app
     */
    private Integer appUserPoints;

    /**
     * Jugador que gano el juego
     */
    private User winner;

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public User getHumanUser() {
        return humanUser;
    }

    public void setHumanUser(User humanUser) {
        this.humanUser = humanUser;
    }

    public User getAppUser() {
        return appUser;
    }

    public void setAppUser(User appUser) {
        this.appUser = appUser;
    }

    public Integer getHumanUserPoints() {
        return humanUserPoints;
    }

    public void setHumanUserPoints(Integer humanUserPoints) {
        this.humanUserPoints = humanUserPoints;
    }

    public Integer getAppUserPoints() {
        return appUserPoints;
    }

    public void setAppUserPoints(Integer appUserPoints) {
        this.appUserPoints = appUserPoints;
    }

    public User getWinner() {
        return winner;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }

    /**
     * Constructor de la clase EscobaMatch
     * Este constructor inicializa los atributos humanUser, appUser, humanUserPoints y appUserPoints.
     *
     * @param humanUser jugador que representa a la persona
     * @param appUser   jugador que representa a la app
     */
    public EscobaMatch(User humanUser, User appUser) {
        this.humanUser = humanUser;
        this.appUser = appUser;
        this.humanUserPoints = 0;
        this.appUserPoints = 0;
        this.winner = null;
    }

    /**
     * Este método define si la partida terminó.
     * El juego termina cuando uno de los dos jugadores (El player o la app)
     * ha obtenido más de 15 punto y más puntos que su oponente.
     * Cuando el juego termina, este método setea en el atributo winner quien ganó.
     *
     * @return true si el juego terminó, false si aún no hay un ganador
     * @see #winner
     */
    public Boolean isFinish() {
        int winnerPoints;
        if (Math.max(humanUserPoints, appUserPoints) >= 15 && !humanUserPoints.equals(appUserPoints)) {
            if (humanUserPoints > appUserPoints) {
                winner = humanUser;
                winnerPoints = humanUserPoints;
            } else {
                winner = appUser;
                winnerPoints = appUserPoints;
            }
            LetterByLetterPrinter.println("The player " + winner.getName() + " won with " + winnerPoints + " points." + System.lineSeparator());
            return true;
        } else {
            LetterByLetterPrinter.println("The game is not over yet." + System.lineSeparator());
            LetterByLetterPrinter.println("The player " + humanUser.getName() + " has " + humanUserPoints + " points." + System.lineSeparator());
            LetterByLetterPrinter.println("The player " + appUser.getName() + " has " + appUserPoints + " points." + System.lineSeparator());
        }
        // TODO: Validar si algún jugador ya llegó a los 15 puntos y obtuvo mas puntos que el oponente.DONE
        //  Si algún jugador ganó, setear el ganador en winner y mostrar el siguiente mensaje:DONE
        //  "The player [nombre del jugador ganador] won with [puntos del jugador] points."DONE
        //  y retornar true si hubo un ganador.DONDE
        //  Si no hay ganador, mostrar el siguiente mensaje:.DONDE
        //  "The game is not over yet.".DONDE
        //  "The player [nombre del jugador humano] has [puntos del jugador humano] points.".DONDE
        //  "The player [nombre del jugador APP] has [puntos del jugador app] points.".DONDE

        return false;
    }

    /**
     * Suma en los puntos de la partida, los puntos obtenidos en una ronda por cada jugado.
     *
     * @param escobaMatchRound ronda jugada a sumar en los puntos del partido
     * @see EscobaMatch#humanUserPoints
     * @see EscobaMatch#appUserPoints
     * @see EscobaMatchRound#getRoundPlayerHuman()
     * @see EscobaMatchRound#getRoundPlayerApp()
     * @see RoundPlayer#getRoundPoints()
     */
    public void calculateScore(EscobaMatchRound escobaMatchRound) {
        humanUserPoints += escobaMatchRound.getRoundPlayerHuman().getRoundPoints();
        appUserPoints += escobaMatchRound.getRoundPlayerApp().getRoundPoints();
        LetterByLetterPrinter.println("The player " + humanUser.getName() + " has " + humanUserPoints + " points." + System.lineSeparator());
        LetterByLetterPrinter.println("The player " + appUser.getName() + " has " + appUserPoints + " points." + System.lineSeparator());

    }
}
