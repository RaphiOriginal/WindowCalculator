package ch.raphi.jomoscalculator.gui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import ch.raphi.jomoscalculator.calculation.CalculatorI;

/**
 * @author raphaelbrunner
 *
 */
public class GUI extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4712592528127860072L;
	
	private double halterhoehe = 4.0;
	private double stosserhoehe = 16.0;
	private double armkurz = 58.0;
	private double armlang = 104.0;
	private double drehpunktFenster = 0.0;
	private double fensterWinkel = 60.0;
	private double fensterUeberstand = 2.0;
	
	//create MenuBar
	JMenuBar menuBar;
	
	//create Menu
	JMenu info;
	
	//create MenuItem
	JMenuItem about;
	
	//create Calculator
	CalculatorI calculator;
	
	//create Button
	JButton calculateBtn;
	JButton clearBtn;
	
	//create Textfields
	JFormattedTextField halterhoeheField;
	JFormattedTextField stosserhoeheField;
	JFormattedTextField armkurzField;
	JFormattedTextField armlangField;
	JFormattedTextField drehpunktFensterField;
	JFormattedTextField fensterWinkelField;
	JFormattedTextField fensterUeberstandField;
	
	//field format
	private NumberFormat format;
	
	//create resultLabels
	JLabel resultX;
	JLabel resultA;
	
	/**
	 * constructor for the GUI
	 * @param calc
	 */
	public GUI(CalculatorI calc){
		calculator = calc;
		//Closing operation and programm title
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Montagerechner");
		
		//Preparing Menu
		menuBar = new JMenuBar();
		info = new JMenu("Info");
		menuBar.add(info);
		about = new JMenuItem("About");
		about.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new About();
			}
			
		});
		
		info.add(about);
		
		//Labels
		//TODO rename and reorganise labelnames!
		JLabel halterhoeheLabel = new JLabel("Halterhöhe");
		JLabel stosserhoeheLabel = new JLabel("Stosserhöhe");
		JLabel armkurzLabel = new JLabel("Armlänge eingefahren");
		JLabel armlangLabel = new JLabel("Armlänge ausgefahren");
		JLabel drehpunktFensterLabel = new JLabel("Abstand Fensterdrehpunkt");
		JLabel fensterWinkelLabel = new JLabel("Winkel des Fensters");
		JLabel fensterUeberstandLabel = new JLabel("Ueberstand des Fensters");
		JLabel resultXLabel = new JLabel("X-Länge");
		resultX = new JLabel("Leer");
		JLabel resultALabel = new JLabel("A-Länge");
		resultA = new JLabel("Leer");
		
		//initialize inputfields
		//TODO rename and reorganise fieldnames
	    halterhoeheField = new JFormattedTextField(format);
	    halterhoeheField.setValue(halterhoehe);
	    
		stosserhoeheField = new JFormattedTextField(format);
		stosserhoeheField.setValue(stosserhoehe);
		
		armkurzField = new JFormattedTextField(format);
		armkurzField.setValue(armkurz);
		
		armlangField = new JFormattedTextField(format);
		armlangField.setValue(armlang);
		
		drehpunktFensterField = new JFormattedTextField(format);
		drehpunktFensterField.setValue(drehpunktFenster);
		
		fensterWinkelField = new JFormattedTextField(format);
		fensterWinkelField.setValue(fensterWinkel);
		
		fensterUeberstandField = new JFormattedTextField(format);
		fensterUeberstandField.setValue(fensterUeberstand);
		
		//Buttons
		calculateBtn = new JButton("Berechnen");
		clearBtn = new JButton("Clear");
		
		//Add actionlistener
		calculateBtn.addActionListener(this);
		clearBtn.addActionListener(this);
		
		//Panels
		JPanel southPanel = new JPanel();
		JPanel westPanel = new JPanel();
		
		//Layouts
		BorderLayout mainFrameLayout = new BorderLayout(5,5);
		FlowLayout southPanelLayout = new FlowLayout(FlowLayout.LEFT, 2, 2);
		GridLayout westPanelLayout = new GridLayout(9,2,2,2);
				
		//Set layouts
		this.setLayout(mainFrameLayout);
		southPanel.setLayout(southPanelLayout);
		westPanel.setLayout(westPanelLayout);
		
		//AddPanels
		add(southPanel, BorderLayout.SOUTH);
		add(westPanel, BorderLayout.WEST);
		
		//Add Buttons
		southPanel.add(clearBtn);
		southPanel.add(calculateBtn);
		
		//Add Menu
		add(menuBar,BorderLayout.NORTH);
		
		//Add Labels and Fields
		westPanel.add(halterhoeheLabel);
		westPanel.add(halterhoeheField);
		westPanel.add(stosserhoeheLabel);
		westPanel.add(stosserhoeheField);
		westPanel.add(armkurzLabel);
		westPanel.add(armkurzField);
		westPanel.add(armlangLabel);
		westPanel.add(armlangField);
		westPanel.add(drehpunktFensterLabel);
		westPanel.add(drehpunktFensterField);
		westPanel.add(fensterWinkelLabel);
		westPanel.add(fensterWinkelField);
		westPanel.add(fensterUeberstandLabel);
		westPanel.add(fensterUeberstandField);
		westPanel.add(resultXLabel);
		westPanel.add(resultX);
		westPanel.add(resultALabel);
		westPanel.add(resultA);
		
		//Set the GUI visible
		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(calculateBtn)){
			readValues();
			double resultHalter = calculator.calculateX(halterhoehe, stosserhoehe, armkurz, armlang, drehpunktFenster, fensterWinkel, fensterUeberstand);
			double resultStosser = calculator.calculateY(halterhoehe, stosserhoehe, armkurz, resultHalter);
			resultX.setText(""+resultHalter);
			resultA.setText(""+resultStosser);
		} else if(e.getSource().equals(clearBtn)){
			reset();
		}
	}
	/**
	 * read values from textfield
	 */
	private void readValues(){
		halterhoehe = (double) halterhoeheField.getValue();
		stosserhoehe = (double) stosserhoeheField.getValue();
		armkurz = (double) armkurzField.getValue();
		armlang = (double) armlangField.getValue();
		drehpunktFenster = (double) drehpunktFensterField.getValue();
		fensterWinkel = (double) fensterWinkelField.getValue();
		fensterUeberstand = (double) fensterUeberstandField.getValue();
	}
	/**
	 * set all values in textfields to zero
	 */
	private void reset(){
		double resetValue = 0.0;
	    halterhoeheField.setValue(resetValue);
		stosserhoeheField.setValue(resetValue);
		armkurzField.setValue(resetValue);
		armlangField.setValue(resetValue);
		drehpunktFensterField.setValue(resetValue);
		fensterWinkelField.setValue(resetValue);
		fensterUeberstandField.setValue(resetValue);
		
		String resetResult = "leer";
		resultA.setText(resetResult);
		resultX.setText(resetResult);
	}
}
