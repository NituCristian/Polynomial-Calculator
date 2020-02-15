package main;

import controller.*;
import view.*;
import test.*;
import model.*;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ManageString manageString = new ManageString();
		Polynomial polynomial = new Polynomial();
		PolynomialInterface polynomialInterface = new PolynomialInterface();
		JUnit jUnit = new JUnit();
		
		PolynomialController controller = new PolynomialController(polynomialInterface, polynomial, manageString);
		polynomialInterface.setVisible(true);
	}

}
