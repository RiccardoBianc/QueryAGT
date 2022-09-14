package runner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import runner.TestsParser.Parenthesis_queueContext;
import runner.TestsParser.Parenthesis_sessionContext;


public class CodeBuilder extends TestsBaseVisitor<String> {
	
	@Override
	public String visitParenthesis_session(Parenthesis_sessionContext ctx) {
		if(ctx.session()!=null) {
			return visit(ctx.session());} 
			else {return visit(ctx.parenthesis_session());}
	}


	private HashMap<String, List<String>> declMap = new HashMap<>();//a map that associates each type of entity with a list of variables
	private final String PROCESS = "Process";// a type of entity
	private final String TYPE = "GlobalType";// a type of entity
	private final String QUEUE = "Queue";// a type of entity
	private final String NETWORK = "Session";// a type of entity
	String code_final;// resulting SWI-Prolog-code

	public CodeBuilder(HashMap<String, List<String>> declMap) {// the environment is passed, since it is computed in Interpreter.java
		super();
		this.declMap = declMap;
	}

	@Override
	public String visitTest_group_name(TestsParser.Test_group_nameContext ctx) {
		String var = ctx.TOKEN_IDENTIFIER().getText();
		return var.substring(0, 1).toLowerCase() + var.substring(1);
	}

	@Override
	public String visitTest_name(TestsParser.Test_nameContext ctx) {
		String var = ctx.TOKEN_IDENTIFIER().getText();
		return var.substring(0, 1).toLowerCase() + var.substring(1);	}
	
	
	@Override
	public String visitQuery(TestsParser.QueryContext ctx) {
		String not = "";
		if(ctx.NOT() != null) {
			not = "\\+";			
		}
		return not + visitChildren(ctx);	
	}
	
	
	@Override 
	public String visitQueue(TestsParser.QueueContext ctx) { 
		if(ctx.getChild(0).getText().equals("Empty")) {//empty list in SWI_Prolog is represented with "[]"
			return "[]";
		}
		if(ctx.getChildCount() == 1){// if this node has one element, then it is a variable
			return visit(ctx.variableQueue());			
		}
		
		List<TestsParser.ParticipantContext> list = ctx.participant();
		List<TestsParser.LabelContext> messages = ctx.label();

		HashMap<String, List<String>> queue = new HashMap<>();
		
		for (int i = 0; i <= list.size() - 2; i+=2) {// queue is visited as a list of messages an it is transformed in a queue as implemented in Prolog part
			String sender = visit(list.get(i));
			String receiver = visit(list.get(i+1));
			String pair = sender + "-" + receiver;
			String message = visit(messages.get(i/2));
			if(queue.containsKey(pair)) {// in Prolog pairs of sender and receiver are associated with lists of messages
				List<String> value = queue.get(pair);// pair already present, list upddated
				value.add(message);
				queue.put(pair,value);
			}
			else {
				List<String> value = new ArrayList<>();// new pair, list created
				value.add(message);
				queue.put(pair,value);
			}
		}
		String res = "[";

		for (String pair : queue.keySet()) {
			res += pair + "-";
			res += queue.get(pair).toString() + ",";
		}
		
		return res.substring(0, res.length() - 1) + "]"; 
		
	}

	@Override 
	public String visitSession(TestsParser.SessionContext ctx) {
		if(ctx.getChildCount() == 1) {
			return visit(ctx.variableSession());			
		}
		String res = "[";
		List<TestsParser.ParticipantContext> participants = ctx.participant();
		List<TestsParser.Parenthesis_processContext> processes = ctx.parenthesis_process();
		
		for (int i = 0; i < participants.size(); i++) {
			res += visit(participants.get(i)) + "-" + visit(processes.get(i)) + ",";
		}
		res = res.substring(0, res.length() - 1)+ "]";
		res += "-" + visit(ctx.parenthesis_queue()) ;
		return res; 
	}


	@Override 
	public String visitWell_formdness(TestsParser.Well_formdnessContext ctx) { 
		try {
			String type = visit(ctx.parenthesis_type());
			String queue = visit(ctx.parenthesis_queue());
			return "well_formdness("+ type + "," + queue + ")";
			}
			catch (Error e) {
				throw new QueryAGTException("Type error in query");
			}	
	}
	
	@Override
	public String visitBoundness(TestsParser.BoundnessContext ctx) {
		try {
			String type = visit(ctx.parenthesis_type());
			return "bounded("+ type + ")";
			}
			catch (Error e) {
				throw new QueryAGTException("Type error in query");
			}	
		}

	@Override
	public String visitIom(TestsParser.IomContext ctx) {
		try {
			String type = visit(ctx.parenthesis_type());
			String queue = visit(ctx.parenthesis_queue());
			return "io_match("+ type + "," + queue + ")";
			}
			catch (Error e) {
				throw new QueryAGTException("Type error in query");
			}
		}

	@Override 
	public String visitAll_proj_exists(TestsParser.All_proj_existsContext ctx) { 
		try {
			String type = visit(ctx.parenthesis_type());
			return "projection_defined_all_players("+ type + ")";
			}
			catch (Error e) {
				throw new QueryAGTException("Type error in query");
			}		
		}
	
	@Override 
	public String visitTyping(TestsParser.TypingContext ctx) { 
		try {
			String network = visit(ctx.parenthesis_session());
			String type = visit(ctx.parenthesis_type());
			String queue = visit(ctx.parenthesis_queue());
			String res = "typing("+ network + "," + type + "-" + queue + ")";
			return res;
			}
			catch (Error e) {
				throw new QueryAGTException("Type error in query");
			}	
	}
	
	
	@Override
	public String visitProjection_exists(TestsParser.Projection_existsContext ctx) {
		try {
			String type = visit(ctx.parenthesis_type());
			String participant = visit(ctx.participant());
			String res = "projection("+ type + "," + participant + ",_),!";
			return res;
			}
			catch (Error e) {
				throw new QueryAGTException("Type error in query");
			}
		}

	@Override 
	public String visitProjection_assert(TestsParser.Projection_assertContext ctx) {
		try {
		String type = visit(ctx.parenthesis_type());
		String participant = visit(ctx.participant());
		String process = visit(ctx.parenthesis_process());
		String res = "projection("+ type + "," + participant + "," + process + "),!";
		return res;
		}
		catch (Error e) {
			throw new QueryAGTException("Type error in query");
		}
		 
	}

		
	@Override
	public String visitParenthesis_queue(Parenthesis_queueContext ctx) {
		if(ctx.queue()!=null) {
		return visit(ctx.queue());} 
		else {return visit(ctx.parenthesis_queue());}
	}

	@Override 
	public String visitDeclaration(TestsParser.DeclarationContext ctx){
		String variable = visit(ctx.getChild(1));
		String element = visit(ctx.getChild(3));
		return variable + " = " + element;		
	}
	
	
	@Override
	public String visitGlobal_type(TestsParser.Global_typeContext ctx) {
		if(ctx.getChild(0).getText().equals("End")) {return "end";}
		if(ctx.getChildCount() == 1) {return visit(ctx.variableType());}
		
		String sender = visit(ctx.participant(0));
		String receiver = visit(ctx.participant(1));
			
		if(ctx.op.getText().equals("!")) {
			String  tail = "";
			if(ctx.tail_type() != null) {
				tail = visit(ctx.tail_type());				
			}
			String head = visit(ctx.head_type());
			return "output_type(" + sender + "," + receiver + ",["	+ head + tail + "])";
		}
		else {
		String label = visit(ctx.label());
		String type = visit(ctx.parenthesis_type());
		return "input_type(" + sender + "," + receiver + "," + label + "," + type +")";
		}
	}

	@Override 
	public String visitProcess(TestsParser.ProcessContext ctx) {
		if(ctx.getChild(0).getText().equals("0")) {return "zero";}

		if(ctx.getChildCount() == 1) {return visit(ctx.variableProcess());}

		String participant = visit(ctx.participant());
		String operation = ctx.op.getText();
		String head = visit(ctx.head_process());
		String tail = "";
		if(ctx.tail_process() != null) {
			tail = visit(ctx.tail_process());	
		}
		switch (operation) {
		case "!":
			return "send_process(" + participant + ",["	+ head + tail + "])";
		default:
			return "receive_process(" + participant + ",["	+ head + tail + "])";
		}}
	
	@Override 
	public String visitHead_type(TestsParser.Head_typeContext ctx) {
		String message = visit(ctx.label());
		String child = visit(ctx.parenthesis_type());
		return message + "-" + child; }

	@Override 
	public String visitTail_type(TestsParser.Tail_typeContext ctx) {
	List<TestsParser.Head_typeContext> list = ctx.head_type();
		String res = "";
		for (int i = 0; i < list.size(); i++) {
			res = res + "," + visit(list.get(i)); 
		}
		return res;
		}
 	@Override
 	public String visitHead_process(TestsParser.Head_processContext ctx) {
 		String message = visit(ctx.label());
		String child = visit(ctx.parenthesis_process());
		return message + "-" + child;
 	}

 	@Override 
 	public String visitTail_process(TestsParser.Tail_processContext ctx) { 
 		List<TestsParser.Head_processContext> list = ctx.head_process();
		String res = "";
		for (int i = 0; i < list.size(); i++) {
			res = res + "," + visit(list.get(i)); 
		}
		return res; 		
 	}


	@Override public String visitParenthesis_type(TestsParser.Parenthesis_typeContext ctx) { 
		if(ctx.global_type()!=null) {
			return visit(ctx.global_type());} 
			else {return visit(ctx.parenthesis_type());}
	}
	
	@Override public String visitParenthesis_process(TestsParser.Parenthesis_processContext ctx) {
		if(ctx.process()!=null) {
			return visit(ctx.process());} 
			else {return visit(ctx.parenthesis_process());}
	}
		
	@Override 
	public String visitParticipant(TestsParser.ParticipantContext ctx) {
		return "\"" + visit(ctx.value()) + "\"";
	}
	
	
	@Override public String visitVariableSession(TestsParser.VariableSessionContext ctx) {
		String var = visit(ctx.variable());
		return checkVar(var,NETWORK);
	}
	
	@Override public String visitVariableQueue(TestsParser.VariableQueueContext ctx) { 
		String var = visit(ctx.variable());
		return checkVar(var,QUEUE);	
	}
	
	@Override public String visitVariableType(TestsParser.VariableTypeContext ctx) {
		String var = visit(ctx.variable());
		return checkVar(var,TYPE);	
	}
	
	@Override public String visitVariableProcess(TestsParser.VariableProcessContext ctx) { 
		String var = visit(ctx.variable());
		return checkVar(var,PROCESS);	
	}
	
	private String checkVar(String var, String type) {//this function checks that variables are used compatibly with the declaration
		Set<String> types = this.declMap.keySet();
		for (String key : types) {
			if(!key.equals(type) && this.declMap.get(key).contains(var)) {// if a variable is declared with a different type
				throw new QueryAGTException("Variable " + var + " has type " + key + " but " + type +" is expected");
			}
		}
		if(!this.declMap.get(type).contains(var)) {//if variable is not declared
			throw new QueryAGTException("Variable " + var +" is not declared");
		}
		return var;
	}
	
	@Override
	public String visitValue(TestsParser.ValueContext ctx) {
		String var = ctx.TOKEN_IDENTIFIER().getText();
		return var.substring(0, 1).toLowerCase() + var.substring(1);
	}

	@Override
	public String visitVariable(TestsParser.VariableContext ctx) { 
		return capitaliseVariableName(ctx.TOKEN_IDENTIFIER().getText());
	}

	protected static String capitaliseVariableName(String var) {
		return var.substring(0, 1).toUpperCase() + var.substring(1);
	}

	
	@Override 
	public String visitLabel(TestsParser.LabelContext ctx) {
		return "\"" + ctx.getChild(0).getText() + "\"";
	}

	
}
