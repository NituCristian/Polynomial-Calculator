package view;


import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PolynomialInterface extends JFrame{

	private String[] oneOperandOperations = {"Derive the first polynomial", "Derive the second polynomial", "Integrate the first polinomyal", "Integrate the second polynomial"};
	private String[] twoOperandsOperations = {"Add the polynomials", "Substract the polynomials", "Multiply the polynomials", "Divide the polynomials"};
	
	private JTextField firstPolynomial = new JTextField(100);
	private JTextField secondPolynomial = new JTextField(100);
	private JTextField result = new JTextField(100);
	
	private JComboBox<String> oneOperand = new JComboBox<String>(oneOperandOperations);
	private JComboBox<String> twoOperands = new JComboBox<String>(twoOperandsOperations);
	
	JLabel giveFirstPolynomial = new JLabel("Type the first polynomial");
	JLabel giveSecondPolynomial = new JLabel("Type the second polynomial");
	JLabel selectTwoPolynomialsOperation = new JLabel("Select an operation between both polynomials");
	JLabel selectOnePolynomialOperation = new JLabel("Select an operation with a single polynomial");
	JLabel operationResult = new JLabel("The result of the selected operation is");
	
	public PolynomialInterface(){
	
	JPanel panel1 = new JPanel();   
	JPanel panel2 = new JPanel();   
	JPanel panel3 = new JPanel();   
	JPanel panel4 = new JPanel();
	JPanel panel5 = new JPanel();
	
	panel1.setLayout(new FlowLayout());   
	panel2.setLayout(new FlowLayout());   
	panel3.setLayout(new FlowLayout());   
	panel4.setLayout(new FlowLayout());   
	panel5.setLayout(new FlowLayout());   

	panel1.add(giveFirstPolynomial);
	panel1.add(firstPolynomial);
	
	panel2.add(giveSecondPolynomial);
	panel2.add(secondPolynomial);
	
	panel3.add(selectOnePolynomialOperation);
	panel3.add(oneOperand);
	
	panel4.add(selectTwoPolynomialsOperation);
	panel4.add(twoOperands);
	
	panel5.add(operationResult);
	panel5.add(result);
	
	JPanel p = new JPanel();  
	p.add(panel1);   
	p.add(panel2); 
	p.add(panel3);
	p.add(panel4);
	p.add(panel5);
	
	p.setLayout(new BoxLayout(p, BoxLayout. Y_AXIS ));  
	this.setContentPane(p);
	
	this.pack();               
	this.setTitle("Operations with polynomials");  
	  
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public String getFirstPolynomial() {
		return firstPolynomial.getText();
	}
	
	public String getSecondPolynomial() {
		return secondPolynomial.getText();
	}
	
	public void setResult(String newResult) {
		result.setText(newResult);
	}
	
	public int getOneOperandCombo() {
		return oneOperand.getSelectedIndex();
	}
	
	public int getTwoOperandsCombo() {
		return twoOperands.getSelectedIndex();
	}

	public void addOneOperandListener(ActionListener listener) {
		oneOperand.addActionListener(listener);
	}
	
	public void addTwoOperandsListener(ActionListener listener) {
		twoOperands.addActionListener(listener);
	}
	
}

