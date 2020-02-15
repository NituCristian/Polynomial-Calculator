package controller;

import view.*;
import model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

import exceptions.CannotDivideException;

public class PolynomialController {
	
	private PolynomialInterface polynomialInterface;
	private Polynomial polynomialModel;
	private ManageString manageString;
	
	public PolynomialController() {
		// TODO Auto-generated constructor stub
	}
	
	public PolynomialController(PolynomialInterface polynomialInterface, Polynomial polynomialModel, ManageString manageString) {
		this.polynomialInterface = polynomialInterface;
		this.polynomialModel = polynomialModel; 
		this.manageString = manageString;
		
		polynomialInterface.addOneOperandListener(new OneOperandListener());
		polynomialInterface.addTwoOperandsListener(new TwoOperandsListener());
	}
	
	class OneOperandListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			Polynomial polynomial1 = new Polynomial();
			Polynomial polynomial2 = new Polynomial();
			Polynomial polynomial3 = new Polynomial();
			String result = null;
			
			String str1 = polynomialInterface.getFirstPolynomial();
			str1 = manageString.oneTimeX(str1);
			str1 = manageString.formatStringOneExponent(str1);
			str1 = manageString.formatStringZeroExponent(str1);
			polynomial1 = polynomial1.convertStringToPolynomial(str1, polynomial1);
			
			String str2 = polynomialInterface.getSecondPolynomial();
			str2 = manageString.oneTimeX(str2);
			str2 = manageString.formatStringOneExponent(str2);
			str2 = manageString.formatStringZeroExponent(str2);
			polynomial2 = polynomial2.convertStringToPolynomial(str2, polynomial2);
			
			int index1 = polynomialInterface.getOneOperandCombo();
			
			if(index1 == 0) {
				polynomial3 = polynomial1.derivePolynomial(polynomial1);
				Collections.sort(polynomial3.polynomial);
			}
			
			else if(index1 == 1) {
				polynomial3 = polynomial2.derivePolynomial(polynomial2);
			}
			
			else if(index1 == 2) {
				polynomial3 = polynomial1.integratePolynomial(polynomial1);
			}
			
			else if(index1 == 3) {
				polynomial3 = polynomial2.integratePolynomial(polynomial2);
			}
	
			result = polynomial3.convertPolynomialToString(polynomial3);
			
			if((index1 == 0 && polynomial1.polynomial.get(0).getDegree() == 0) || (index1 == 1 && polynomial2.polynomial.get(0).getDegree() == 0)){
				result += "+ 0.0";
			}
			
			polynomialInterface.setResult(result);
			
		}
	}
	
	class TwoOperandsListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			Polynomial polynomial1 = new Polynomial();
			Polynomial polynomial2 = new Polynomial();
			Polynomial polynomial3 = new Polynomial();
			Polynomial polynomial4 = new Polynomial();
			String result = "";
			
			String str1 = polynomialInterface.getFirstPolynomial();
			str1 = manageString.oneTimeX(str1);
			str1 = manageString.formatStringOneExponent(str1);
			str1 = manageString.formatStringZeroExponent(str1);
			polynomial1 = polynomial1.convertStringToPolynomial(str1, polynomial1);
		
			String str2 = polynomialInterface.getSecondPolynomial();
			str2 = manageString.oneTimeX(str2);
			str2 = manageString.formatStringOneExponent(str2);
			str2 = manageString.formatStringZeroExponent(str2);
			polynomial2 = polynomial2.convertStringToPolynomial(str2, polynomial2);
			
			int index1 = polynomialInterface.getTwoOperandsCombo();
			
			if(index1 == 0) {
				polynomial3 = polynomial1.addPolynomials(polynomial1, polynomial2);
				//Collections.sort(polynomial3.polynomial);
			}
			
			else if(index1 == 1) {
				polynomial3 = polynomial1.substractPolynomials(polynomial1, polynomial2);
			}
			
			else if(index1 == 2) {
				polynomial3 = polynomial1.multiplyPolynomials(polynomial1, polynomial2);
			}
			
			else if(index1 == 3) {
				
				try {
					polynomial3 = polynomial1.quotientDividePolynomials(polynomial1, polynomial2);
					polynomial4 = polynomial1.remainderDividePolynomials(polynomial1, polynomial2);
					Collections.sort(polynomial3.polynomial);
					Collections.sort(polynomial4.polynomial);
					
					result += "Quotient is: ";
					if(polynomial1.polynomial.get(0).getDegree() >= polynomial2.polynomial.get(0).getDegree()) {
						result += polynomial3.convertPolynomialToString(polynomial3);
					}
					else {
						result += 0.0;
					}
					if(polynomial4.polynomial.size() == 0) {
						result += "; remainder is: + 0.0";
					}
					else {
						result += "; remainder is: " + polynomial4.convertPolynomialToString(polynomial4);
					}
				} catch (CannotDivideException e1) {
					result += (e1.getMessage());
				}
	
			}
						
			if(index1 != 3) {
				Collections.sort(polynomial3.polynomial);
				result = polynomial3.convertPolynomialToString(polynomial3);
			}
			
			polynomialInterface.setResult(result);
			
			
		}
		
	}
}
