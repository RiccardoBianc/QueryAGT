package runner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import InputParser.TestsBaseVisitor;
import InputParser.TestsParser.Parenthesis_queryContext;
import InputParser.TestsParser.Proj_equalityContext;
import InputParser.TestsParser.Type_participant_parenthesisContext;
import InputParser.TestsParser.Type_queue_parenthesisContext;


public class CodeBuilder extends InputParser.TestsBaseVisitor<String> {
	
	@Override
	public String visitParenthesis_query(Parenthesis_queryContext ctx) {
		if(ctx.query() != null) {
			return visit(ctx.query());
		}
		return visit(ctx.parenthesis_query());
	}

	@Override
	public String visitProj_equality(Proj_equalityContext ctx) {
		if(ctx.proj_equality() != null) {
			return visit(ctx.proj_equality());
		}
		return visit(ctx.type_participant_parenthesis()) + "@" + visit(ctx.parenthesis_process());
	}

	@Override
	public String visitType_participant_parenthesis(Type_participant_parenthesisContext ctx) {
		if(ctx.type_participant_parenthesis() != null) {
			return visit(ctx.type_participant_parenthesis());
		}
		return visit(ctx.parenthesis_type()) + "@" + visit(ctx.participant());
	}

	@Override
	public String visitType_queue_parenthesis(Type_queue_parenthesisContext ctx) {
		if(ctx.type_queue_parenthesis() != null) {
			return visit(ctx.type_queue_parenthesis());
		}
		return visit(ctx.parenthesis_type()) + "@" + visit(ctx.parenthesis_queue());
	}

	@Override
	public String visitParenthesis_session(InputParser.TestsParser.Parenthesis_sessionContext ctx) {
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
	public String visitTest_group_name(InputParser.TestsParser.Test_group_nameContext ctx) {
		String var = ctx.TOKEN_IDENTIFIER().getText();
		return var.substring(0, 1).toLowerCase() + var.substring(1);
	}

	@Override
	public String visitTest_name(InputParser.TestsParser.Test_nameContext ctx) {
		String var = ctx.TOKEN_IDENTIFIER().getText();
		return var.substring(0, 1).toLowerCase() + var.substring(1);	}
	
	
	@Override
	public String visitQuery(InputParser.TestsParser.QueryContext ctx) {
		String not = "";
		if(ctx.NOT() != null) {
			not = "\\+";
			return not + "(" + visit(ctx.parenthesis_query())+")";	
		}
		return visitChildren(ctx);	
	}
	
	
	@Override 
	public String visitQueue(InputParser.TestsParser.QueueContext ctx) { 
		if(ctx.getChild(0).getText().equals("Empty")) {//empty list in SWI_Prolog is represented with "[]"
			return "[]";
		}
		if(ctx.getChildCount() == 1){// if this node has one element, then it is a variable
			return visit(ctx.variableQueue());			
		}
		
		List<InputParser.TestsParser.ParticipantContext> list = ctx.participant();
		List<InputParser.TestsParser.LabelContext> messages = ctx.label();

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
	public String visitSession(InputParser.TestsParser.SessionContext ctx) {
		if(ctx.getChildCount() == 1) {
			return visit(ctx.variableSession());			
		}
		String res = "[";
		List<InputParser.TestsParser.ParticipantContext> participants = ctx.participant();
		List<InputParser.TestsParser.Parenthesis_processContext> processes = ctx.parenthesis_process();
		
		for (int i = 0; i < participants.size(); i++) {
			res += visit(participants.get(i)) + "-" + visit(processes.get(i)) + ",";
		}
		res = res.substring(0, res.length() - 1)+ "]";
		res += "-" + visit(ctx.parenthesis_queue()) ;
		return res; 
	}


	@Override 
	public String visitWell_formdness(InputParser.TestsParser.Well_formdnessContext ctx) { 
		try {
			String type_queue = visit(ctx.type_queue_parenthesis());
			return "well_formdness("+ type_queue.split("@")[0] + "," + type_queue.split("@")[1] + ")";
			}
			catch (Error e) {
				throw new QueryAGTException("Type error in query");
			}	
	}
	
	@Override
	public String visitBoundness(InputParser.TestsParser.BoundnessContext ctx) {
		try {
			String type = visit(ctx.parenthesis_type());
			return "bounded("+ type + ")";
			}
			catch (Error e) {
				throw new QueryAGTException("Type error in query");
			}	
		}

	@Override
	public String visitIom(InputParser.TestsParser.IomContext ctx) {
		try {
			String type_queue = visit(ctx.type_queue_parenthesis());
			return "io_match("+ type_queue.split("@")[0] + "," + type_queue.split("@")[1] + ")";
			}
			catch (Error e) {
				throw new QueryAGTException("Type error in query");
			}
		}

	@Override 
	public String visitAll_proj_exists(InputParser.TestsParser.All_proj_existsContext ctx) { 
		try {
			String type = visit(ctx.parenthesis_type());
			return "projection_defined_all_players("+ type + ")";
			}
			catch (Error e) {
				throw new QueryAGTException("Type error in query");
			}		
		}
	
	@Override 
	public String visitTyping(InputParser.TestsParser.TypingContext ctx) { 
		try {
			String network = visit(ctx.parenthesis_session());
			String type_queue = visit(ctx.type_queue_parenthesis());
			String res = "typing("+ network + "," + type_queue.split("@")[0] + "-" + type_queue.split("@")[1] + ")";
			return res;
			}
			catch (Error e) {
				throw new QueryAGTException("Type error in query");
			}	
	}
	
	
	@Override
	public String visitProjection_exists(InputParser.TestsParser.Projection_existsContext ctx) {
		try {
			String type_participant = visit(ctx.type_participant_parenthesis());
			String res = "projection("+ type_participant.split("@")[0] + "," + type_participant.split("@")[1] + ",_),!";
			return res;
			}
			catch (Error e) {
				throw new QueryAGTException("Type error in query");
			}
		}

	@Override 
	public String visitProjection_assert(InputParser.TestsParser.Projection_assertContext ctx) {
		try {
		String proj = visit(ctx.proj_equality());
		String res = "projection("+ proj.split("@")[0] + "," + proj.split("@")[1] + "," + proj.split("@")[2] + "),!";
		return res;
		}
		catch (Error e) {
			throw new QueryAGTException("Type error in query");
		}
		 
	}

		
	@Override
	public String visitParenthesis_queue(InputParser.TestsParser.Parenthesis_queueContext ctx) {
		if(ctx.queue()!=null) {
		return visit(ctx.queue());} 
		else {return visit(ctx.parenthesis_queue());}
	}

	@Override 
	public String visitDeclaration(InputParser.TestsParser.DeclarationContext ctx){
		String variable = visit(ctx.getChild(1));
		String element = visit(ctx.getChild(3));
		return variable + " = " + element;		
	}
	
	
	@Override
	public String visitGlobal_type(InputParser.TestsParser.Global_typeContext ctx) {
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
	public String visitProcess(InputParser.TestsParser.ProcessContext ctx) {
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
	public String visitHead_type(InputParser.TestsParser.Head_typeContext ctx) {
		String message = visit(ctx.label());
		String child = visit(ctx.parenthesis_type());
		return message + "-" + child; }

	@Override 
	public String visitTail_type(InputParser.TestsParser.Tail_typeContext ctx) {
	List<InputParser.TestsParser.Head_typeContext> list = ctx.head_type();
		String res = "";
		for (int i = 0; i < list.size(); i++) {
			res = res + "," + visit(list.get(i)); 
		}
		return res;
		}
 	@Override
 	public String visitHead_process(InputParser.TestsParser.Head_processContext ctx) {
 		String message = visit(ctx.label());
		String child = visit(ctx.parenthesis_process());
		return message + "-" + child;
 	}

 	@Override 
 	public String visitTail_process(InputParser.TestsParser.Tail_processContext ctx) { 
 		List<InputParser.TestsParser.Head_processContext> list = ctx.head_process();
		String res = "";
		for (int i = 0; i < list.size(); i++) {
			res = res + "," + visit(list.get(i)); 
		}
		return res; 		
 	}


	@Override public String visitParenthesis_type(InputParser.TestsParser.Parenthesis_typeContext ctx) { 
		if(ctx.global_type()!=null) {
			return visit(ctx.global_type());} 
			else {return visit(ctx.parenthesis_type());}
	}
	
	@Override public String visitParenthesis_process(InputParser.TestsParser.Parenthesis_processContext ctx) {
		if(ctx.process()!=null) {
			return visit(ctx.process());} 
			else {return visit(ctx.parenthesis_process());}
	}
		
	@Override 
	public String visitParticipant(InputParser.TestsParser.ParticipantContext ctx) {
		return "\"" + visit(ctx.value()) + "\"";
	}
	
	
	@Override public String visitVariableSession(InputParser.TestsParser.VariableSessionContext ctx) {
		String var = visit(ctx.variable());
		return checkVar(var,NETWORK);
	}
	
	@Override public String visitVariableQueue(InputParser.TestsParser.VariableQueueContext ctx) { 
		String var = visit(ctx.variable());
		return checkVar(var,QUEUE);	
	}
	
	@Override public String visitVariableType(InputParser.TestsParser.VariableTypeContext ctx) {
		String var = visit(ctx.variable());
		return checkVar(var,TYPE);	
	}
	
	@Override public String visitVariableProcess(InputParser.TestsParser.VariableProcessContext ctx) { 
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
	public String visitValue(InputParser.TestsParser.ValueContext ctx) {
		String var = ctx.TOKEN_IDENTIFIER().getText();
		return var.substring(0, 1).toLowerCase() + var.substring(1);
	}

	@Override
	public String visitVariable(InputParser.TestsParser.VariableContext ctx) { 
		return capitaliseVariableName(ctx.TOKEN_IDENTIFIER().getText());
	}

	protected static String capitaliseVariableName(String var) {
		return var.substring(0, 1).toUpperCase() + var.substring(1);
	}

	
	@Override 
	public String visitLabel(InputParser.TestsParser.LabelContext ctx) {
		return "\"" + ctx.getChild(0).getText() + "\"";
	}

	
}
