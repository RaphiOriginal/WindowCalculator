package ch.raphi.jomoscalculator.calculation;

/**
 * @author raphaelbrunner
 * This Inteface helps to replace with other calculation algorithms
 */
public interface CalculatorI {
	
	/**
	 * Calculates the position of the 'console' for the piston
	 * @param halterhoehe
	 * @param stosserhoehe
	 * @param armkurz
	 * @param armlang
	 * @param drehpunktFenster
	 * @param fensterWinkel
	 * @param fensterUeberstand
	 * @return
	 */
	public double calculateX(double halterhoehe, double stosserhoehe, double armkurz, double armlang, double drehpunktFenster, double fensterWinkel, double fensterUeberstand);
	
	/**
	 * Calculates the position for the piston 'console' on the window
	 * @param halterhoehe
	 * @param stosserhoehe
	 * @param resX
	 * @return
	 */
	public double calculateY(double halterhoehe, double stosserhoehe, double armkurz, double resX);
}
