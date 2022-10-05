import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Driver { 
 public static void main(String [] args) throws FileNotFoundException { 
  Polynomial p = new Polynomial("test.txt"); 
  
    Polynomial p1 = new Polynomial("test2.txt"); 

    Polynomial s = p1.multiplyPolynomial(p); 

  System.out.println("p(0.1) = " + p.evaluate(0.1)); 
  if(s.hasRoot(1))
  System.out.println("1 is a root of p"); 
  else 
  System.out.println("1 is not a root of p"); 

  try {
    File myObj = new File("result.txt");
    if (myObj.createNewFile()) {
      System.out.println("File created: " + myObj.getName());
    } else {
      System.out.println("File already exists.");
    }

    FileWriter myWriter = new FileWriter("result.txt");
    myWriter.write("Result: ");
    for (int i = 0; i < s.coef.length; i++) {
        myWriter.write(s.coef[i]+"x"+s.exponents[i]);
    } 
    myWriter.close();
  } catch (IOException e) {
    System.out.println("An error occurred.");
    e.printStackTrace();
  }


 } 
} 