package ch.raphi.jomoscalculator.calculation;

/***
 * This Class is for the maths needet to calculate the right positions of the elements.
 * @author raphaelbrunner
 *
 */
public class Calculator implements CalculatorI{

	/**
	 * Calculates the position of the 'console' for the piston. The math is powered by Dr. Andreas Vogt
	 * @param halter
	 * @param stosser
	 * @param armkurz
	 * @param armlang
	 * @param drehpunkt
	 * @param winkel
	 * @param ueberstand
	 * @return the position 
	 */
	@Override
	public double calculateX(double halter, double stosser, double armkurz, double armlang, double drehpunkt,
			double winkel, double ueberstand) {
		//prepare variables
		double a1 = halter - drehpunkt;
		double a2 = stosser - halter - drehpunkt;
		double l1 = armkurz;
		double l2 = armlang;
		double alpha = Math.toRadians(winkel);
		
		//calculate help variables
		double a = calculateA(alpha);
		double b = calculateB(alpha);
		double m1 = calculateM1(alpha, a1, a2, l1);
		double m2 = calculateM2(alpha, a1, a2, l1);
		
		//get one result of midnight formula
		double res1 = midnightFormulaPartOne(a, b, m1, m2) + midnightFormulaPartTwo(a, b, m1, m2, l2);
		double res2 = midnightFormulaPartOne(a, b, m1, m2) - midnightFormulaPartTwo(a, b, m1, m2, l2);
		
		if(res1 >= 0) return ((double)Math.round(res1*100))/100;
		return ((double)Math.round(res2*100))/100;
		
	}
	
	private double calculateA(double alpha){
		return 1 - Math.cos(alpha);
	}
	private double calculateB(double alpha){
		return - Math.sin(alpha);
	}
	private double calculateM1(double alpha, double a1, double a2, double l1){
		double cos = Math.cos(alpha);
		double sin = Math.sin(alpha);
		return - cos * sqrt(l1, a2) + sin * (a1 + a2);
	}
	private double calculateM2(double alpha, double a1, double a2, double l1){
		double cos = Math.cos(alpha);
		double sin = Math.sin(alpha);
		return a1 - sin * sqrt(l1, a2) - cos * (a1 + a2);
	}
	private double sqrt(double l1, double a2){
		return Math.sqrt(Math.pow(l1, 2) - Math.pow(a2, 2));
	}
	private double midnightFormulaPartOne(double a, double b, double m1, double m2){
		double up = a * m1 + b * m2;
		double down = Math.pow(a, 2) + Math.pow(b, 2);
		return - (up/down);
	}
	private double midnightFormulaPartTwo(double a, double b, double m1, double m2, double l2){
		double up1 = a * m1 + b * m2;
		double down1 = Math.pow(a, 2) + Math.pow(b, 2);
		double down2 = down1;
		double up2 = Math.pow(m1, 2) + Math.pow(m2, 2) - Math.pow(l2, 2);
		double left = Math.pow((up1/down1), 2);
		double right = up2/down2;
		return Math.sqrt(left - right);
	}

	@Override
	public double calculateY(double halterhoehe, double stosserhoehe, double armkurz, double resX) {
		double a = stosserhoehe - halterhoehe;
		double c = armkurz;
		double b = Math.sqrt(Math.pow(c, 2) - Math.pow(a, 2));
		return ((double)Math.round((b + resX)*100)) / 100;
	}

}
