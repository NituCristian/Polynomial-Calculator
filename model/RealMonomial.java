package model;

public class RealMonomial implements Comparable<RealMonomial> {
	
	protected float coefficient;
	protected int degree;
	
	public RealMonomial(float coefficient, int degree) {
		this.coefficient = coefficient;
		this.degree = degree;
	}
	
	public RealMonomial() {
		
	}
	
	public int getDegree() {
		return this.degree;
	}
	
	public float getCoefficient() {
		return this.coefficient;
	}
	
	public RealMonomial addMonomial(RealMonomial monomial1, RealMonomial monomial2){
		RealMonomial monomial3 = new RealMonomial();
		
		if(monomial1.degree == monomial2.degree) {
			monomial3.coefficient = monomial1.coefficient + monomial2.coefficient;
			monomial3.degree = monomial1.degree;	
		}

		else {
			monomial3.degree = -1;
		}
		return monomial3;
	}
	
	public  RealMonomial substractMonomial(RealMonomial monomial1, RealMonomial monomial2) { 
		RealMonomial monomial3 = new RealMonomial();
		
		if(monomial1.degree == monomial2.degree) {
			monomial3.coefficient = monomial1.coefficient - monomial2.coefficient;
			monomial3.degree = monomial1.degree;
			}
		
		else {
			monomial3.degree = -1;
		}
		
		return monomial3;
		
	}
	
	public  RealMonomial multiplyMonomial(RealMonomial monomial1, RealMonomial monomial2) {
		RealMonomial monomial3 = new RealMonomial();
		monomial3.coefficient = monomial1.coefficient * monomial2.coefficient;
		monomial3.degree = monomial1.degree + monomial2.degree;
		return monomial3;
	}	
	
	public  RealMonomial deriveMonomial(RealMonomial monomial1) {
		RealMonomial monomial3 = new RealMonomial();
		
		if(monomial1.degree == 0) {
			monomial3.coefficient = 0.00f;
			monomial3.degree = 0;
		}
		
		else {
			monomial3.coefficient = monomial1.coefficient * monomial1.degree;
			monomial3.degree = monomial1.degree - 1;
		}
		
		return monomial3;
	}
	
	public RealMonomial integrateMonomial(RealMonomial monomial1) {
		
		RealMonomial monomial3 = new RealMonomial();
		
		if(monomial1.coefficient == 0) {
			monomial3.coefficient = 0;
			monomial3.degree = 0;
		}
		
		else {
			monomial3.coefficient = monomial1.coefficient / (monomial1.degree + 1);
			monomial3.degree = monomial1.degree + 1;
		}
		
		return monomial3;
	}

	public int compareTo(RealMonomial monomial) {
		return monomial.degree - this.degree;
	}


}




	
	
	
	

	
	
	
	
