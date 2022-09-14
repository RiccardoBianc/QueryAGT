package runner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;
import runner.TestsParser.All_proj_existsContext;
import runner.TestsParser.BoundnessContext;
import runner.TestsParser.DeclarationContext;
import runner.TestsParser.IomContext;
import runner.TestsParser.LetContext;
import runner.TestsParser.Projection_assertContext;
import runner.TestsParser.Projection_existsContext;
import runner.TestsParser.QueryContext;
import runner.TestsParser.TypingContext;
import runner.TestsParser.Well_formdnessContext;


public class Interpreter extends runner.TestsBaseVisitor<Void> {


	private HashMap<String, List<String>> environment = new HashMap<>(); /*In this hashmap keys are the types of entities and values the variables names. It is the 
									       execution environment*/
	private HashMap<String, String> codes = new HashMap<>(); /*In this hasmap a key is the variable name, a value is the prolog code representing the associated entity*/

	private final String PROCESS = "Process";
	private final String TYPE = "GlobalType";
	private final String QUEUE = "Queue";
	private final String NETWORK = "Session"; /*These string are the type names*/
	String indentationSpace; /*This string is used to adapt indentation to the mode*/
	int countPassed=0; /*Number of passed queries*/
	int total=0; /*Number of total queries*/

	private void createEnv() { /*This function initialize environment and codes. At the beginning there are no entities*/
		HashMap<String, List<String>> map = new HashMap<>();
		map.put(QUEUE, new ArrayList<>());
		map.put(PROCESS, new ArrayList<>());
		map.put(TYPE, new ArrayList<>());
		map.put(NETWORK, new ArrayList<>());
		this.environment = map;	
		this.codes = new HashMap<>();
	}
	public Interpreter(String space) {
		super();
		createEnv();
		this.indentationSpace = space; /*The indentation is different in interactive and batch mode*/
	}

	public HashMap<String, List<String>> deepCopyEnvironment() {/*This function returns a deep copy of the current environment*/
		HashMap<String, List<String>> snapshot = new HashMap<String,List<String>>();
		new HashMap<String, List<String>>();
		for (String key : this.environment.keySet()) {
			List<String> valuesSnapshot = new ArrayList<String>();
			for (String value : this.environment.get(key)) {
				valuesSnapshot.add(new String(value));
			}
			snapshot.put(key, valuesSnapshot);
		}
		return snapshot;
	}
	
	@Override
	public Void visitLet(LetContext ctx) {
		List<DeclarationContext> declarations = ctx.declaration();
		List<String> declared = new ArrayList<>();
		HashMap<String, List<String>> snapshot = deepCopyEnvironment(); /*Here we save a snapshot of environment*/
		try {
			
			for (runner.TestsParser.DeclarationContext decl : declarations) { /*This loop updates the environment with the declarations in input. If some type error is encountered then the environment is restored*/
				String type = decl.getChild(0).getText();
				String var = CodeBuilder.capitaliseVariableName(decl.getChild(1).getText());
				
				if(declared.contains(var)) { /*In the let block the same variable can not be declared more than one time*/
					this.environment = snapshot; /*Environment is restored*/
					throw new QueryAGTException("Multiple variable declaration: variable " + var + " is declared multiple times in the let block");
				}
				
				declared.add(var);
				List<String> listType = this.environment.get(type);
				listType.add(var);
				this.environment.put(type,listType); /*Environment updating*/
			}
			
			CodeBuilder stringConverter = new CodeBuilder(this.environment);
			for (runner.TestsParser.DeclarationContext decl : declarations) {
				String var = stringConverter.visit(decl.getChild(1)) + " = "; /*CodeBuilder makes typechecking control*/
				String code = stringConverter.visit(decl) +"\r\n";
				this.codes.put(var, code);
			}
		}
		catch(Throwable e) {
			this.environment = snapshot;
			throw e;
		}
		return null;
	}

	@Override
	public Void visitQuery(QueryContext ctx) {
		CodeBuilder stringConverter = new CodeBuilder(this.environment);
		String queryCode = stringConverter.visit(ctx);
		String declarations = "";
		for (String decl : codes.values()) { /*This loop builds the declarations prolog code*/
			declarations += decl + ",";
		}
		String converted = ":- include(\"global_types\").\r\n\n\n"
				+ "test() :- "+ 
				declarations +
				queryCode +
				",write(\"PASSED\").\n "
				+ "test() :- write(\"NOT PASSED\")."; /*This line creates a prolog predicate to execute the test*/


		File tempFile=null; /*Prolog code is written on a temporary file that is then executed*/
		try {
			tempFile = File.createTempFile("prolog", null);
			tempFile.deleteOnExit();

			BufferedWriter out = new BufferedWriter(new FileWriter(tempFile));
			out.write(converted);
			out.close();
		}
		catch (Throwable e)
		{
			throw new QueryAGTException("Error in creating the temporary file containing prolog queries");
		}

		Runtime cmd = Runtime.getRuntime();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(cmd.exec("swipl -s  "+ tempFile.getAbsolutePath() +" -g test -g halt").getInputStream()));
			String line = "";
			System.out.print(indentationSpace+"Query ");
			while ((line = reader.readLine()) != null) {
				QueryContext auxCtx = ctx;
				while(auxCtx.NOT() != null) {
					System.out.print("not ");
					auxCtx = auxCtx.query();
				}
				visit(auxCtx.getChild(0));
				System.out.println(line);
				if(line.equals("PASSED")) {
					countPassed++;
				}
				total++;
			}


		}
		catch(Throwable e){
			throw new QueryAGTException("Error in executing swi-prolog interpreter");
		}
		return null;
	}

	@Override
	public Void visitAll_proj_exists(All_proj_existsContext ctx) {
		for (ParseTree subEl : ctx.children) {
			System.out.print(subEl.getText() + " ");
		}
		return null;
	}


	@Override
	public Void visitIom(IomContext ctx) {
		for (ParseTree subEl : ctx.children) {
			System.out.print(subEl.getText() + " ");
		}
		return null;
	}

	@Override
	public Void visitBoundness(BoundnessContext ctx) {
		for (ParseTree subEl : ctx.children) {
			System.out.print(subEl.getText() + " ");
		}
		return null;
	}

	@Override
	public Void visitProjection_assert(Projection_assertContext ctx) {
		for (ParseTree subEl : ctx.children) {
			System.out.print(subEl.getText() + " ");
		}
		return null;
	}

	@Override
	public Void visitProjection_exists(Projection_existsContext ctx) {
		for (ParseTree subEl : ctx.children) {
			System.out.print(subEl.getText() + " ");
		}
		return null;
	}

	@Override
	public Void visitWell_formdness(Well_formdnessContext ctx) {
		for (ParseTree subEl : ctx.children) {
			System.out.print(subEl.getText() + " ");
		}
		return null;
	}

	@Override
	public Void visitTyping(TypingContext ctx) {
		for (ParseTree subEl : ctx.children) {
			System.out.print(subEl.getText() + " ");
		}
		return null;
	}

	@Override
	public Void visitProg(runner.TestsParser.ProgContext ctx) {
		for (int i = 0; i < ctx.test_group().size(); i++) {
			System.out.println("I'm executing test group: " + ctx.test_group_name(i).getText());
			visit(ctx.test_group(i));
		}

		return null;
	}

	@Override
	public Void visitTest_group(runner.TestsParser.Test_groupContext ctx) {
		for (int i = 0; i < ctx.test().size(); i++) {
			System.out.println("\tI'm executing test: " + ctx.test_name(i).getText() + ": ");
			visit(ctx.test(i));
		}
		return null;
	}

	@Override
	public Void visitTest(runner.TestsParser.TestContext ctx){
		createEnv();
		List<DeclarationContext> declarations = ctx.declaration();
		List<String> declared = new ArrayList<>();
		HashMap<String, List<String>> snapshot = deepCopyEnvironment(); /*Here we save a snapshot of environment*/
		try {
		for (runner.TestsParser.DeclarationContext decl : declarations) {
			String type = decl.getChild(0).getText();
			String var = CodeBuilder.capitaliseVariableName(decl.getChild(1).getText());
			
			if(declared.contains(var)) { /*In the let block the same variable can not be declared more than one time*/
				this.environment = snapshot; /*Environment is restored*/
				throw new QueryAGTException("Multiple variable declaration: variable " + var + " is declared multiple times in the let block");
			}
			
			declared.add(var);
			List<String> listType = this.environment.get(type);
			listType.add(var);
			this.environment.put(type,listType);
		}
		CodeBuilder stringConverter = new CodeBuilder(this.environment);
		
		for (runner.TestsParser.DeclarationContext decl : declarations) {
			String var = stringConverter.visit(decl.getChild(1)) + " = ";
			String code = stringConverter.visit(decl) +"\r\n";
			this.codes.put(var, code);
		}
		
		for (QueryContext query : ctx.queries().query()) {
			visit(query);
		}
		}
		catch(Throwable e) {
			this.environment = snapshot;
			throw e;
		}
		
		return null;
	}

	@Override
	public Void visitQueries(runner.TestsParser.QueriesContext ctx) {
		for (runner.TestsParser.QueryContext query : ctx.query()) {
			visit(query);
		}
		return null;
	}

}
