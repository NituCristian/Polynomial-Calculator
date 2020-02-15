package model;

import exceptions.*;
import java.math.BigDecimal;
import java.util.*;

public class Polynomial{
	public List<RealMonomial> polynomial = new ArrayList<RealMonomial>();

	public Polynomial(){
	}
	
	public Polynomial(ArrayList<RealMonomial> polynomial) {
		this.polynomial = polynomial;
	}

	public Polynomial convertStringToPolynomial(String str, Polynomial polynomial1)  {
		RealMonomial[] RealMonomials = new RealMonomial[20];
		
		for(int i = 0; i < RealMonomials.length; i++) {
			RealMonomials[i] = new RealMonomial(0, 0);
		}
		
		StringTokenizer st = new StringTokenizer(str, "*x^+"); 
		float key = 0;
		int index = 0, val = 0;
		
		while(st.hasMoreTokens()) {  
			try {
	 	   		key = Float.parseFloat(st.nextToken());
	 	   		val = Integer.parseInt(st.nextToken());
			}catch (NumberFormatException e){
				key = 0;
				val = 0;
			}
	
			RealMonomials[index].coefficient = key;
			RealMonomials[index].degree = val;
			polynomial1.polynomial.add(RealMonomials[index]);
			index++;
		}	
		return polynomial1;	
	}
	
	public Polynomial addPolynomials(Polynomial polynomial1, Polynomial polynomial2) {
		Polynomial polynomial3 = new Polynomial();
		RealMonomial auxMonom = new RealMonomial();
		int ok1 = 0, ok2 = 0;
		
		for(RealMonomial monomial1: polynomial1.polynomial) {
			for(RealMonomial monomial2: polynomial2.polynomial) {
					auxMonom = monomial1.addMonomial(monomial1, monomial2);
					if(auxMonom.degree != -1) {
						polynomial3.polynomial.add(auxMonom);
						ok1 = 1;
					}
			}
			
			if(ok1 == 0) {
				polynomial3.polynomial.add(monomial1);
			}
			ok1 = 0;
		}
		for(RealMonomial monomial2: polynomial2.polynomial) {
			for(RealMonomial monomial3: polynomial3.polynomial) {		
				if(monomial2.degree == monomial3.degree) {
					ok2 = 1;
				}
			}
			
			if(ok2 == 0) {
				polynomial3.polynomial.add(monomial2);
			}
			ok2=0;
		}
		return polynomial3;
	}

	public Polynomial substractPolynomials(Polynomial polynomial1, Polynomial polynomial2) {
		Polynomial polynomial3 = new Polynomial();
		RealMonomial auxMonom = new RealMonomial();
		int ok1 = 0, ok2 = 0;
		
		for(RealMonomial monomial1: polynomial1.polynomial) {
			for(RealMonomial monomial2: polynomial2.polynomial) {
					auxMonom = monomial1.substractMonomial(monomial1, monomial2);
					if(auxMonom.degree != -1) {
						polynomial3.polynomial.add(auxMonom);
						ok1 = 1;
					}
			}
			
			if(ok1 == 0) {
				polynomial3.polynomial.add(monomial1);
			}
			ok1 = 0;
		}
		for(RealMonomial monomial2: polynomial2.polynomial) {
			for(RealMonomial monomial3: polynomial3.polynomial) {		
				if(monomial2.degree == monomial3.degree) {
					ok2 = 1;
				}
			}
			
			if(ok2 == 0) {
				monomial2.coefficient = -monomial2.coefficient;
				polynomial3.polynomial.add(monomial2);
			}
			ok2=0;
		}
		return polynomial3;
	}
	
	public Polynomial multiplyPolynomials(Polynomial polynomial1, Polynomial polynomial2) {
		Polynomial polynomial3 = new Polynomial();
		RealMonomial monomial1 = new RealMonomial();
		RealMonomial monomial2= new RealMonomial();
		RealMonomial monomial3 = new RealMonomial();
		Iterator<RealMonomial> iterator1 = polynomial1.polynomial.listIterator();
		Iterator<RealMonomial> iterator2 = polynomial2.polynomial.listIterator();
		
		while(iterator1.hasNext()) {
			monomial1 = iterator1.next();
					
			while(iterator2.hasNext()) {
				monomial2 = iterator2.next();	
				monomial3 = monomial1.multiplyMonomial(monomial1, monomial2);
				polynomial3.polynomial.add(monomial3);
			}
			iterator2 = polynomial2.polynomial.listIterator();
		}
		manageSameDegrees(polynomial3);
				
		return polynomial3;
	}
	
	public void manageSameDegrees(Polynomial polynomial3) {
		ListIterator<RealMonomial>iterator1 = polynomial3.polynomial.listIterator();
		ListIterator<RealMonomial>iterator2 = polynomial3.polynomial.listIterator();
		List<RealMonomial> found = new ArrayList<RealMonomial>();
		RealMonomial monomial1 = new RealMonomial();
		RealMonomial monomial2= new RealMonomial();
		int index1 = 0, index2 = 0;
		
			while(iterator1.hasNext()) {
				monomial1 = iterator1.next();
				index1++;
				while(iterator2.hasNext()) {
					monomial2 = iterator2.next();
					index2++;
					
					if(index1 != index2) {
						if(monomial1.degree == monomial2.degree) {
							monomial1.coefficient += monomial2.coefficient;
							found.add(monomial2);
						}
					}
				}
				
				iterator2 = polynomial3.polynomial.listIterator();
				for (int i = 0; i < index1; i++) {
					iterator2.next();
				}
				index2 = index1;
			}
			polynomial3.polynomial.removeAll(found);
	}
			
	public Polynomial derivePolynomial(Polynomial polynomial1) {
		Polynomial polynomial3 = new Polynomial();
		RealMonomial monomial2 = new RealMonomial();
		
		for(RealMonomial monomial1: polynomial1.polynomial) {
			monomial2 = monomial1.deriveMonomial(monomial1);
			polynomial3.polynomial.add(monomial2);
		}
		
		return polynomial3;
	}
	
	public Polynomial integratePolynomial(Polynomial polynomial1) {
		Polynomial polynomial3 = new Polynomial();
		RealMonomial monomial2 = new RealMonomial();
	
		for(RealMonomial monomial1: polynomial1.polynomial) {
			monomial2 = monomial1.integrateMonomial(monomial1);
			polynomial3.polynomial.add(monomial2);
		}
		
		return polynomial3;
	}
	
	public void divideException(Polynomial polynomial1) throws CannotDivideException{
		int ok = 0;
		
		for(RealMonomial monomial: polynomial1.polynomial) {
			if(monomial.coefficient != 0) {
				ok = 1;
			}
		}
		
		if(ok == 0) {
			throw new CannotDivideException("The divisor is equal to 0");
		}
	}
	
	public Polynomial quotientDividePolynomials(Polynomial polynomial1, Polynomial polynomial2) throws CannotDivideException{
		divideException(polynomial2);
		Polynomial polynomial3 = new Polynomial();
		Polynomial polynomial4 = new Polynomial();
		RealMonomial monomial1 = new RealMonomial();
		RealMonomial monomial3 = new RealMonomial();
		int i = 0, ok = 0;
		
		if(polynomial1.polynomial.get(0).degree < polynomial2.polynomial.get(0).degree) {
			polynomial3.polynomial.add(new RealMonomial((float)0.0, 0));
			return polynomial3;
		}
		
		while(polynomial1.polynomial.get(i).degree >= polynomial2.polynomial.get(0).degree) {
			
			if(polynomial1.polynomial.get(i).coefficient != 0) {
				monomial1.coefficient = polynomial1.polynomial.get(i).coefficient / polynomial2.polynomial.get(0).coefficient;
				monomial1.degree = polynomial1.polynomial.get(i).degree - polynomial2.polynomial.get(0).degree;
				RealMonomial monomial2 = new RealMonomial(monomial1.coefficient, monomial1.degree);
				polynomial3.polynomial.add(i, monomial2);
				monomial3 = polynomial3.polynomial.get(i);
				Polynomial polynomial5 = new Polynomial();
				polynomial5.polynomial.add(monomial3);
				polynomial4 = polynomial5.multiplyPolynomials(polynomial5, polynomial2);
				polynomial1 = polynomial1.substractPolynomials(polynomial1, polynomial4);
				Collections.sort(polynomial1.polynomial);
				if(polynomial1.polynomial.get(polynomial1.polynomial.size() - 1).coefficient != 0.0) {
					i++;
				}
			}
			else break;
		}
		return polynomial3;
	}
	
	public Polynomial remainderDividePolynomials(Polynomial polynomial1, Polynomial polynomial2) {
		Polynomial polynomial3 = new Polynomial();
		Polynomial polynomial4 = new Polynomial();
		Polynomial polynomial5= new Polynomial();
		Polynomial polynomial6 = new Polynomial();
		
		try {
			polynomial3 = polynomial1.quotientDividePolynomials(polynomial1, polynomial2);
		} catch (CannotDivideException e) {
			System.out.println(e.getMessage());
		}
		polynomial4 = polynomial2.multiplyPolynomials(polynomial2, polynomial3);
		polynomial5 = polynomial1.substractPolynomials(polynomial1, polynomial4);
		
		for(RealMonomial realMonomial: polynomial5.polynomial) {
			if(realMonomial.coefficient != 0.0) {
				polynomial6.polynomial.add(realMonomial);
			}
		}
		
		return polynomial6;
	}
		
	@SuppressWarnings("deprecation")
	public float twoDecimalsFormat(float number) {
		BigDecimal bd = new BigDecimal(Float.toString(number));
	    bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
	    return bd.floatValue();
	}
	
	public String convertPolynomialToString(Polynomial polynomial3) {
		String strConv = " ";
			
		for(RealMonomial ss: polynomial3.polynomial) {
			ss.coefficient = twoDecimalsFormat(ss.coefficient);
			if(ss.coefficient != 0.0) {
				if(ss.coefficient > 0.0) {
					strConv += " + " + ss.coefficient + "*x^" + ss.degree;
				}
				else if(ss.coefficient < 0.0){
					strConv += " - " + ss.coefficient + "*x^" + ss.degree;
				}
			}
		}
			strConv = strConv.replace("- -", "- ");
			strConv = strConv.replace("*x^0", Character.toString(' '));
			strConv = strConv.replace("x^1", Character.toString('x'));
			strConv = strConv.replace(" 1.0*", Character.toString(' '));
			return strConv;
	}	
}



