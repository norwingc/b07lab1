import java.io.File; 
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner; 

public class Polynomial {
	
	public double[] coef = new double[0];
	public int[] exponents = new int[0];

	public Polynomial(int polSyze) {
		this.coef = new double[polSyze];
		this.exponents = new int[polSyze];
	   for (int i = 0; i< coef.length; i++) {
		   this.coef[i] = 0;
		   this.exponents[i] = 0;
	   }
	   
   }
	
	//file reader
	public String newPolynomialString(String file) throws FileNotFoundException{
  
		File myPolymialFile = new File(file);
	
		Scanner myReader = new Scanner(myPolymialFile);
	
		String data = "";
	
		//The polynomial has to be in one line only
		while (myReader.hasNextLine()) {
			data = myReader.nextLine();
		}
		myReader.close();
		return data;
	}

	//returns the list of components (Coef with exponent)
	public String[] splitPolynomial(String stringPolynomial){

	String[] result = stringPolynomial.split("(?=\\+|\\-)");
		return result;
	}

	public Polynomial(String text) throws FileNotFoundException{
		String textPoly = newPolynomialString(text);
		String[] polyComponents = splitPolynomial(textPoly);
		
		this.coef = new double[polyComponents.length];
		this.exponents = new int[polyComponents.length];
		
		for (int i = 0; i < polyComponents.length; i++){
			
			if(polyComponents[i].contains("x")){
				String[] splitAroundX = polyComponents[i].split("x");

				if(splitAroundX[0]!="" &! splitAroundX[0].equals("-") &! splitAroundX[0].equals("+")  && splitAroundX.length>1){
					this.coef[i] = Double.parseDouble(splitAroundX[0]);
				 	this.exponents[i] = Integer.parseInt(splitAroundX[1]);
				}
				else if(splitAroundX[0]=="" &! splitAroundX[0].equals("-") &! splitAroundX[0].equals("+") && splitAroundX.length>1 ){
					this.coef[i] = 1;
				 	this.exponents[i] = Integer.parseInt(splitAroundX[1]);
				}
				else if(splitAroundX[0]!="" && splitAroundX.length<=1 &! splitAroundX[0].equals("-") &! splitAroundX[0].equals("+") ){
					this.coef[i] = Double.parseDouble(splitAroundX[0]);
				 	this.exponents[i] = 1;
				}
				else{
					this.coef[i] = 1;
				 	this.exponents[i] = 1;
				}
			}
			else{
				this.coef[i] = Double.parseDouble(polyComponents[i]);
				this.exponents[i] = 0;
			}
		}
		
	}

	//Creates a ArrayList of all the differemt exponents
	public ArrayList<Integer> diffExponents(Polynomial second){

		Polynomial first = this;
		Polynomial sec = second;

		ArrayList<Integer> exponenList = new ArrayList<Integer>();

		for (int i = 0; i < first.exponents.length; i++) {
			exponenList.add(first.exponents[i]);
		}

		for (int i = 0; i < sec.exponents.length; i++) {
			if(!exponenList.contains(sec.exponents[i])){
				exponenList.add(sec.exponents[i]);
			}
		}

		return exponenList;
	}

	/*takes  one  argument  of  type  Polynomial  and returns the polynomial 
	 *resulting from adding the calling object and the argument 
	 */
	
    public Polynomial add(Polynomial second) throws FileNotFoundException {
    	
		ArrayList<Integer> listOfExponents = diffExponents(second);
		Polynomial result = new Polynomial(listOfExponents.size());

		for (int i = 0; i < listOfExponents.size(); i++) {
			for (int j = 0; j < second.exponents.length; j++) {
				if(second.exponents[j]==listOfExponents.get(i)){
					result.coef[i] += second.coef[j];
					result.exponents[i] = second.exponents[j];
				}
			}
			for (int j = 0; j < this.exponents.length; j++) {
				if(this.exponents[j]==listOfExponents.get(i)){
					result.coef[i] += this.coef[j];
					result.exponents[i] = this.exponents[j];
				}
			}
		}
		return result;
    }

	//evaluates the polynomial 
	public double evaluate (double x) {
		double result = 0;
		for(int i = 0; i < coef.length; i++) {
		 result += coef[i]*Math.pow(x, this.exponents[i]);
		}
		return result;
	}
	
	//determines whether this value is a root of the polynomial or not
	public boolean hasRoot(double x) {
		boolean isRoot = evaluate(x) == 0;
		return isRoot;
	}

	public Polynomial multiplyPolynomial(Polynomial second) throws FileNotFoundException{

		Polynomial result = new Polynomial(this.exponents.length>=second.exponents.length?this.exponents.length:second.exponents.length);
		Polynomial resultMul = new Polynomial(this.exponents.length>=second.exponents.length?this.exponents.length:second.exponents.length);

		for (int i = 0; i < this.coef.length; i++) {
			for (int j = 0; j < second.coef.length; j++) {
				result.coef[j] = this.coef[i]*second.coef[j];
				result.exponents[j] = this.exponents[i]+second.exponents[j];
			}
			resultMul = resultMul.add(result);	
		}
		return resultMul;
	}
}