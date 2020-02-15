package model;

public class ManageString {
	
	
	public ManageString() {
		// TODO Auto-generated constructor stub
	}
	
public String oneTimeX(String str) {
		
		int[] visit = new int[str.length() + 5];
		
		for(int i: visit) {
			visit[i] = 0; 
		}
		
		int check = 0;
		for(char ch: str.toCharArray()) {
			
			if(ch == 'x') {
				visit[check] = 1;
			}
			
			check++;
		}
		check = 0;
		for(char ch: str.toCharArray()) {
			
			if(ch != '*' && visit[check+1] == 1) {
				str = str.replace(" x", " 1*x");
				str = str.replace("-x", " -1*x");				
			}
			
			if(ch == 'x' && check == 0) {
				str = "1*" + str;
			}
			check++;
		}
		
		return str;
	}
	
	public String formatStringOneExponent(String str) {
		str = str.replace("-", "+-");
		str = str.replaceAll(" ", "");
	
		int ok = 0;
		String b = null;
		
		for(char ch: str.toCharArray()) {		
			if(ok == 1 && (ch == '+') ) {
				int dim = str.lastIndexOf(b);
	
				if(dim >= 0) {
				 str = new StringBuilder(str).replace(dim, dim+1,"x^1").toString();
				}		
			}
			
			ok = 0;
			
			if(str.charAt(str.length() - 1) == 'x') {	
				int dim = str.lastIndexOf('x');
				
				if(dim >= 0) {
				 str = new StringBuilder(str).replace(dim, dim+1,"x^1").toString();
				}
			}
		
			if(ch == 'x') {
				ok = 1;
				b = Character.toString(ch);
			}
		}
		
		return str;
	}

	public String formatStringZeroExponent(String str) {
	
		if(str.length() < 1) {
			str = "";
		}
		
		else {
			char c2 = str.charAt(str.length() - 1);
		
			if(str.length() > 1) {
				char c3 = str.charAt(str.length() - 2);
		
				if(c2 >= '0' && c2 <= '9' && c3 != '^') {	
					str = str.concat("*x^0");
				}
		
			}
		}
		
		if(str.length() == 1) {
			str = str.concat("*x^0");
		}
		return str;
		
	}
	
}
