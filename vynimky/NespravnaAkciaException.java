package vynimky;

/**
 * Trieda vytvorí vlastnú výnimku na zachytenie nesprávnej akcie.
 *
 * @author Matúš Piroh
 * @version 16.5.2026
 */
public class NespravnaAkciaException extends Exception {
    /**
     * Konštruktor načíta chybovú správu.
     * @param message správa na načítanie
     */
    public NespravnaAkciaException(String message) {
        super(message);
    }
}
