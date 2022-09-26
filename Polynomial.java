public class Polynomial {
	
	private double[] coef;
	
	//sets  the  polynomial  to  zero
	public Polynomial() {
		this (new double[0]);
	}
	
	/* takes  an  array  of  double  as  an  argument  and  sets  the 
	coefficients accordingly */

	public Polynomial(double[] coef) {
		 this.coef = new double[coef.length];
		for (int i = 0; i< coef.length; i++) {
			this.coef[i] = coef[i];
		}
		
	}
    
	/*takes  one  argument  of  type  Polynomial  and returns the polynomial 
	 *resulting from adding the calling object and the argument 
	 */
	
	
	public void setCoef(int index, double coeff) {
		
        if (index > coef.length - 1) {
  
            double temp[] = coef;
            coef = new double[index + 1];

            for (int i = 0; i < temp.length; i++) {
            	coef[i] = temp[i];
            }
        }
        
        coef[index] = coeff;
    }
	
    public Polynomial add(Polynomial second) {
    	
        Polynomial result = new Polynomial();
        int len1 = this.coef.length;
        int len2 = second.coef.length;
        int len = Math.min(len1, len2);
        int i;
        
        for (i = 0; i < len; i++) {
            result.setCoef(i, this.coef[i] + second.coef[i]);
        }
        while (i < len1) {
            result.setCoef(i, this.coef[i]);
            i++;
        }
        while (i < len2) {
            result.setCoef(i, second.coef[i]);
            i++;
        }
        return result;
    }
    
	//evaluates the polynomial 
	
	public double evaluate (double x) {
		double result = 0;
		for(int i = 0; i < coef.length; i++) {
		 result += coef[i]*Math.pow(x, i);
		}
		
		return result;
	}
	
	//determines whether this value is a root of the polynomial or not
	
	public boolean hasRoot(double x) {
		boolean isRoot = evaluate(x) == 0;
		
		return isRoot;
	}
	

}
