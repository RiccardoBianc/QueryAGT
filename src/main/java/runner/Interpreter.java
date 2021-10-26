package runner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ParseTree;

import InputParser.TestsParser.All_proj_existsContext;
import InputParser.TestsParser.BoundnessContext;
import InputParser.TestsParser.DeclarationContext;
import InputParser.TestsParser.IomContext;
import InputParser.TestsParser.LetContext;
import InputParser.TestsParser.Projection_assertContext;
import InputParser.TestsParser.Projection_computeContext;
import InputParser.TestsParser.Projection_existsContext;
import InputParser.TestsParser.QueryContext;
import InputParser.TestsParser.TypingContext;
import InputParser.TestsParser.Well_formdnessContext;



public class Interpreter extends InputParser.TestsBaseVisitor<Void> {

	private HashMap<String, List<String>> environment = new HashMap<>();
	private HashMap<String, String> codes = new HashMap<>();

	private final String PROCESS = "Process";
	private final String TYPE = "GlobalType";
	private final String QUEUE = "Queue";
	private final String NETWORK = "Session";
	String code_final;
	public HashMap<String, List<String>> test_map = new HashMap<>();
	int projectionsCounter = 0;
	String space;
	int countPassed=0;
	int total=0;
	
	private void createEnv() {
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
		this.space = space;
	}

	@Override
	public Void visitLet(LetContext ctx) {
		List<DeclarationContext> declarations = ctx.declaration();
		List<String> declared = new ArrayList<>();
		for (InputParser.TestsParser.DeclarationContext decl : declarations) {
			String type = decl.getChild(0).getText();
			String var = CodeBuilder.capitaliseVariableName(decl.getChild(1).getText());
			if(declared.contains(var)) {
			throw new Error("Multiple variable declaration: variable " + var + " is declared multiple times in the let block");
			}
				declared.add(var);
				List<String> listType = this.environment.get(type);
				listType.add(var);
				this.environment.put(type,listType);
		}
		CodeBuilder stringConverter = new CodeBuilder(this.environment);
		for (InputParser.TestsParser.DeclarationContext decl : declarations) {
			String var = stringConverter.visit(decl.getChild(1)) + " = ";
			String code = stringConverter.visit(decl) +"\r\n";
			this.codes.put(var, code);
		}
	return null;
	}

	@Override
	public Void visitQuery(QueryContext ctx) {
		CodeBuilder stringConverter = new CodeBuilder(this.environment);
		String queryCode = stringConverter.visit(ctx);
		String declarations = "";
		for (String decl : codes.values()) {
			declarations += decl + ",";
		}
		//declarations = declarations.substring(0, declarations.length() - 1);
		String converted = ":- include(\"global_types\").\r\n\n\n"
				+ "test() :- "+declarations+queryCode;
		converted += ",write(\"PASSED\").\n test() :- write(\"NOT PASSED\").";

		File tempFile=null;
		try {
			tempFile = File.createTempFile("prolog", null);
		    tempFile.deleteOnExit();
		    
			BufferedWriter out = new BufferedWriter(new FileWriter(tempFile));
		    out.write(converted);
		    out.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		Runtime cmd = Runtime.getRuntime();
		try {
		BufferedReader reader = new BufferedReader(new InputStreamReader(cmd.exec("swipl -s  "+ tempFile.getAbsolutePath() +" -g test -g halt").getInputStream()));
		String line = "";
    	System.out.print(space+"Query ");
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
		catch(Exception e){
			System.out.println(e.getMessage());
			System.exit(1);
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
	public Void visitProjection_compute(Projection_computeContext ctx) {
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
	public Void visitProg(InputParser.TestsParser.ProgContext ctx) {
	    for (int i = 0; i < ctx.test_group().size(); i++) {
			System.out.println("I'm executing test group: " + ctx.test_group_name(i).getText());
			visit(ctx.test_group(i));
		}
			
		return null;
	}

	@Override
	public Void visitTest_group(InputParser.TestsParser.Test_groupContext ctx) {
		for (int i = 0; i < ctx.test().size(); i++) {
			System.out.println("\tI'm executing test: " + ctx.test_name(i).getText() + ": ");
			visit(ctx.test(i));
		}
		return null;
	}

	@Override
	public Void visitTest(InputParser.TestsParser.TestContext ctx){
		createEnv();
		List<DeclarationContext> declarations = ctx.declaration();
		List<String> declared = new ArrayList<>();
		for (InputParser.TestsParser.DeclarationContext decl : declarations) {
			String type = decl.getChild(0).getText();
			String var = CodeBuilder.capitaliseVariableName(decl.getChild(1).getText());
			if(declared.contains(var)) {
			throw new Error("Multiple variable declaration: variable " + var + " is declared multiple times in the let block");
			}
				declared.add(var);
				List<String> listType = this.environment.get(type);
				listType.add(var);
				this.environment.put(type,listType);
		}
		CodeBuilder stringConverter = new CodeBuilder(this.environment);
		for (InputParser.TestsParser.DeclarationContext decl : declarations) {
			String var = stringConverter.visit(decl.getChild(1)) + " = ";
			String code = stringConverter.visit(decl) +"\r\n";
			this.codes.put(var, code);
		}
		for (QueryContext query : ctx.queries().query()) {
			visit(query);
		}
		
		
		
		return null;

/*		
		CodeBuilder stringConverter = new CodeBuilder();
		String testCode = stringConverter.visit(ctx);

		String converted = ":- include(\"global_types\").\r\n\n\n"
				+ "test() :- "+testCode;
		converted += ",write(\"OK\").\n test() :- write(\"NOT OK\").";

		File tempFile=null;
		try {
			tempFile = File.createTempFile("prolog", null);
		    tempFile.deleteOnExit();
		    
			BufferedWriter out = new BufferedWriter(new FileWriter(tempFile));
		    out.write(converted);
		    out.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		Runtime cmd = Runtime.getRuntime();
		try {
		BufferedReader reader = new BufferedReader(new InputStreamReader(cmd.exec("swipl -s  "+ tempFile.getAbsolutePath() +" -g test -g halt").getInputStream()));
		String line = "";
	    while ((line = reader.readLine()) != null) {
	        System.out.println(line);
	    }
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.exit(1);
		}

		return null;
*/
	}

	@Override
	public Void visitQueries(InputParser.TestsParser.QueriesContext ctx) {
		for (InputParser.TestsParser.QueryContext query : ctx.query()) {
			visit(query);
		}
		return null;
	}

}
