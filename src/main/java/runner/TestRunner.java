package runner; 
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;



public class TestRunner 
{

	public static void main( String[] args) throws Exception 
	{
		try {
			InputStream source = TestRunner.class.getClassLoader().getResourceAsStream("global_types.pl");	      
			FileOutputStream fos = new FileOutputStream("global_types.pl");
			byte[] buffer = new byte[2048];
			int read_bytes;
			while(-1 != (read_bytes = source.read(buffer))) {
				fos.write(buffer, 0, read_bytes);
			}
			fos.close();
		}
		catch(IOException e){
			System.out.println("Error in finding and loading prolog code file");
			return;
		}
		catch(NullPointerException e){
			System.out.println("Error in finding and loading prolog code file");
			return;
		}
		catch(SecurityException e) {
			System.out.println("Security error in finding and loading prolog code file");
			return;
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
			}
			catch (QueryAGTException e) {
				System.out.println(e.getMessage());
				return;
			}
			catch (Throwable t) {
				System.out.println("Generic error");
				return;
			}
			
		}
		else {
			String test = "";
			CharStream inputCode = null;
			Interpreter interpreter = new Interpreter("");
			System.out.println("Insert test code. Ending char must be ';;'. Press only 'exit;;' to exit.");
			while(true){
				System.out.print("> ");
				try {
					Scanner scanner = new Scanner(System.in);
					scanner.useDelimiter(";;");
					inputCode = CharStreams.fromString(scanner.next());

					if(inputCode.toString().strip().equals("exit")) {
						System.out.println("Thank you!");
						return;
					}
					
					InputParser.TestsParser parser = TestRunner.generateTree(inputCode);
					ParseTree tree = parser.interactive();
					interpreter.visit(tree);
				}
				catch (QueryAGTException e) {
					System.out.println(e.getMessage());
					continue;
				}
				catch(Throwable e) {
					System.out.println("Generic error");
					continue;
				}
			}
		}
	}

	private static InputParser.TestsParser generateTree(CharStream inputCode) {
		InputParser.TestsParser parser = null;
		InputParser.TestsLexer lexer = new InputParser.TestsLexer(inputCode);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		parser = new InputParser.TestsParser(tokens);
		lexer.addErrorListener(new BaseErrorListener()  {
	        @Override
	        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
	            throw new QueryAGTException("Lexer error");
	        }
	    });
		lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
		parser.addErrorListener(new BaseErrorListener()  {
	        @Override
	        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
	            throw new QueryAGTException("Parser error");
	        }
	    });
		parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
		return parser;
	}


}