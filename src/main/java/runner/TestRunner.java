package runner; 


import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;



public class TestRunner 
{

	public static void main( String[] args) throws Exception 
	{
		InputStream source = TestRunner.class.getClassLoader().getResourceAsStream("global_types.pl");	      
		try (FileOutputStream fos = new FileOutputStream("global_types.pl");){
		    byte[] buffer = new byte[2048];
		    int read_bytes;
		    while(-1 != (read_bytes = source.read(buffer))) {
		        fos.write(buffer, 0, read_bytes);
		    }
		}
		
		if(args.length > 0){
			 try {
				 CharStream input = CharStreams.fromFileName(args[0]);
				 InputParser.TestsParser parser = TestRunner.generateTree(input);
				 ParseTree tree = parser.prog();
				 if(args.length > 1) {
					 PrintStream fileOut = new PrintStream(args[1]);
					 System.setOut(fileOut);
				 }
				 Interpreter interpreter =  new Interpreter("\t\t");
				 interpreter.visit(tree);
				 System.out.println("Passed "+interpreter.countPassed + " queries on "+interpreter.total);
				 return;
			     } catch (Exception ioe) {
			        throw new IllegalArgumentException(ioe);
			     }
		}
	
      Scanner scanner = new Scanner(System.in);
      scanner.useDelimiter("@");
    String test = "";
	CharStream inputCode = null;
	Interpreter interpreter = new Interpreter("");
	while(true){
	System.out.println("Insert test code. Ending char must be '@'. Press only 'exit@' to exit.");
	  try {
	    	inputCode = CharStreams.fromString(scanner.next());
	    	System.out.println(inputCode);

	    	if(inputCode.toString().equals("exit")) {
		    	System.out.println("Thank you!");
		    	return;
	    		}

	      } catch (Exception ioe) {
		        throw new IllegalArgumentException(ioe);
	      }

	  InputParser.TestsParser parser = TestRunner.generateTree(inputCode);
	  ParseTree tree = parser.interactive();
    interpreter.visit(tree);
	}
	}
	
	private static InputParser.TestsParser generateTree(CharStream inputCode) {
		  InputParser.TestsParser parser = null;
		  try {
			  InputParser.TestsLexer lexer = new InputParser.TestsLexer(inputCode);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			parser = new InputParser.TestsParser(tokens);
		  }
		  catch (Exception e) {
				e.printStackTrace();
			}
		  return parser;
	}


}