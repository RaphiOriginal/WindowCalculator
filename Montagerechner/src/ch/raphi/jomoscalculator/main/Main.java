package ch.raphi.jomoscalculator.main;

import ch.raphi.jomoscalculator.calculation.Calculator;
import ch.raphi.jomoscalculator.gui.GUI;
/***
 * The Main class which starts the application
 * @author raphaelbrunner
 *
 */
public class Main {

	/**
	 * start the application
	 * @param args
	 */
	public static void main(String[] args) {
		new GUI(new Calculator());
	}

}
