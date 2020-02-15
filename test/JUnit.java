package test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.*;

import exceptions.CannotDivideException;
import view.*;
import model.*;

public class JUnit {
	private Polynomial polynomialModel;
	private static int success = 0;
	
	public JUnit() {
		
	} 
	
	public boolean checkEqual(Polynomial polynomial1, Polynomial polynomial2) {
		boolean ok = true;
		int index1 = 0, index2 = 0;
		for(RealMonomial monomial1: polynomial1.polynomial) {
			for(RealMonomial monomial2: polynomial2.polynomial) {
				if(index1 == index2) {
					if((monomial1.getCoefficient() != monomial2.getCoefficient()) || (monomial1.getDegree() != monomial2.getDegree())) {
						ok = false;
					}
				}
				index2++;
			}
			index1++;
			index2 = 0;
		}
		
		return ok;
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testAdd() {
		Polynomial polynomial1 = new Polynomial();
		Polynomial polynomial2 = new Polynomial();
		Polynomial polynomial3 = new Polynomial();
		Polynomial polynomial4 = new Polynomial();
		
		polynomial1.polynomial.add(0, new RealMonomial(2.0f, 1));
		polynomial1.polynomial.add(1,  new RealMonomial(4.0f, 0));
		polynomial2.polynomial.add(0,  new RealMonomial(3.0f, 1));
		polynomial2.polynomial.add(1,  new RealMonomial(5.0f, 0));
		polynomial4.polynomial.add(0,  new RealMonomial(5.0f, 1));
		polynomial4.polynomial.add(1,  new RealMonomial(9.0f, 0));
		
		polynomial3 = polynomial1.addPolynomials(polynomial1, polynomial2);

		boolean ok = checkEqual(polynomial3, polynomial4);
			assertTrue(ok == true);
	 } 
	
	@SuppressWarnings("deprecation")
	@Test
	public void testSubstract() {
		Polynomial polynomial1 = new Polynomial();
		Polynomial polynomial2 = new Polynomial();
		Polynomial polynomial3 = new Polynomial();
		Polynomial polynomial4 = new Polynomial();
		
		polynomial1.polynomial.add(0, new RealMonomial(3.0f, 2));
		polynomial1.polynomial.add(1,  new RealMonomial(4.0f, 0));
		polynomial2.polynomial.add(0,  new RealMonomial(1.0f, 1));
		polynomial2.polynomial.add(1,  new RealMonomial(6.0f, 0));
		polynomial4.polynomial.add(0,  new RealMonomial(3.0f, 2));
		polynomial4.polynomial.add(1,  new RealMonomial(-1.0f, 1));
		polynomial4.polynomial.add(1,  new RealMonomial(-2.0f, 0));
		
		polynomial3 = polynomial1.substractPolynomials(polynomial1, polynomial2);

		boolean ok = checkEqual(polynomial3, polynomial4);
		assertTrue(ok == true);
	 } 
	
	@SuppressWarnings("deprecation")
	@Test
	public void testMultiply() {
		Polynomial polynomial1 = new Polynomial();
		Polynomial polynomial2 = new Polynomial();
		Polynomial polynomial3 = new Polynomial();
		Polynomial polynomial4 = new Polynomial();
		
		polynomial1.polynomial.add(0, new RealMonomial(2.0f, 2));
		polynomial1.polynomial.add(1,  new RealMonomial(-4.0f, 1));
		polynomial2.polynomial.add(0,  new RealMonomial(1.0f, 1));
		polynomial2.polynomial.add(1,  new RealMonomial(6.0f, 0));
		polynomial4.polynomial.add(0,  new RealMonomial(2.0f, 3));
		polynomial4.polynomial.add(1,  new RealMonomial(8.0f, 2));
		polynomial4.polynomial.add(2,  new RealMonomial(-24.0f, 1));
		
		polynomial3 = polynomial1.multiplyPolynomials(polynomial1, polynomial2);

		boolean ok = checkEqual(polynomial3, polynomial4);
		assertTrue(ok == true);
	 } 

	@SuppressWarnings("deprecation")
	@Test
	public void testDivision() {
		Polynomial polynomial1 = new Polynomial();
		Polynomial polynomial2 = new Polynomial();
		Polynomial polynomial3 = new Polynomial();
		Polynomial polynomial4 = new Polynomial();
		Polynomial polynomial5 = new Polynomial();
		Polynomial polynomial6 = new Polynomial();
		
		polynomial1.polynomial.add(0, new RealMonomial(3.0f, 2));
		polynomial1.polynomial.add(1,  new RealMonomial(5.0f, 1));
		polynomial1.polynomial.add(2,  new RealMonomial(2.0f, 0));
		polynomial2.polynomial.add(0,  new RealMonomial(2.0f, 1));
		polynomial2.polynomial.add(1,  new RealMonomial(1.0f, 0));
		polynomial4.polynomial.add(0,  new RealMonomial(1.5f, 1));
		polynomial4.polynomial.add(1,  new RealMonomial(1.75f, 0));
		polynomial5.polynomial.add(0,  new RealMonomial(0.25f, 0));
	
		try {
			polynomial3 = polynomial1.quotientDividePolynomials(polynomial1, polynomial2);
		} catch (CannotDivideException e) {
			System.out.println(e.getMessage());
		}
			
			polynomial6 = polynomial1.remainderDividePolynomials(polynomial1, polynomial2);
		
		boolean ok1 = checkEqual(polynomial3, polynomial4);
		boolean ok2 = checkEqual(polynomial5, polynomial6);
		assertTrue(ok1 == true && ok2 == true);
	} 

	@SuppressWarnings("deprecation")
	@Test
	public void testDerivation() {
		Polynomial polynomial1 = new Polynomial();
		Polynomial polynomial2 = new Polynomial();
		Polynomial polynomial3 = new Polynomial();
		Polynomial polynomial4 = new Polynomial();
		
		polynomial1.polynomial.add(0, new RealMonomial(7.0f, 2));
		polynomial1.polynomial.add(1,  new RealMonomial(-4.0f, 1));
		polynomial1.polynomial.add(2,  new RealMonomial(1.0f, 0));
		
		polynomial4.polynomial.add(0,  new RealMonomial(14.0f, 1));
		polynomial4.polynomial.add(1,  new RealMonomial(-4.0f, 0));
		
		polynomial3 = polynomial1.derivePolynomial(polynomial1);

		boolean ok = checkEqual(polynomial3, polynomial4);
		assertTrue(ok == true);
	 } 

	@SuppressWarnings("deprecation")
	@Test
	public void testIntegration() {
		Polynomial polynomial1 = new Polynomial();
		Polynomial polynomial3 = new Polynomial();
		Polynomial polynomial4 = new Polynomial();
		
		polynomial1.polynomial.add(0, new RealMonomial(9.0f, 2));
		polynomial1.polynomial.add(1,  new RealMonomial(-4.0f, 1));
		polynomial1.polynomial.add(2,  new RealMonomial(6.0f, 0));
		
		polynomial4.polynomial.add(0,  new RealMonomial(3.0f, 3));
		polynomial4.polynomial.add(1,  new RealMonomial(-2.0f, 2));
		polynomial4.polynomial.add(2,  new RealMonomial(6.0f, 1));
		
		polynomial3 = polynomial1.integratePolynomial(polynomial1);

		boolean ok = checkEqual(polynomial3, polynomial4);
		assertTrue(ok == true);
	 } 
}
