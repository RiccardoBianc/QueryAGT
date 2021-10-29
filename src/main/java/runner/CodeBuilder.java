package runner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class CodeBuilder extends InputParser.TestsBaseVisitor<String> {
	
	private HashMap<String, List<String>> declMap = new HashMap<>();
	private final String PROCESS = "Process";
	private final String TYPE = "GlobalType";
	private final String QUEUE = "Queue";
	private final String NETWORK = "Session";
	String code_final;
	public HashMap<String, List<String>> test_map = new HashMap<>();
	int projectionsCounter = 0;
	
	private void initializeDecl(){
		HashMap<String, List<String>> map = new HashMap<>();
		map.put(QUEUE, new ArrayList<>());
		map.put(PROCESS, new ArrayList<>());
		map.put(TYPE, new ArrayList<>());
		map.put(NETWORK, new ArrayList<>());
		this.declMap = map;
	}
	
    public CodeBuilder() {
		super();
		initializeDecl();
	}


	public CodeBuilder(HashMap<String, List<String>> declMap) {
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
		}
		return not + visitChildren(ctx);	
	}
	
	
	@Override 
	public String visitQueue(InputParser.TestsParser.QueueContext ctx) { 
		if(ctx.getChild(0).getText().equals("Empty")) {
			return "[]";
		}
		if(ctx.getChildCount() == 1){
			return visit(ctx.variableQueue());			
		}
		
		List<InputParser.TestsParser.ParticipantContext> list = ctx.participant();
		List<InputParser.TestsParser.LabelContext> messages = ctx.label();

		HashMap<String, List<String>> queue = new HashMap<>();
		
		for (int i = 0; i <= list.size() - 2; i+=2) {
			String sender = visit(list.get(i));
			String receiver = visit(list.get(i+1));
			String pair = sender + "-" + receiver;
			String message = visit(messages.get(i/2));
			if(queue.containsKey(pair)) {
				List<String> value = queue.get(pair);
				value.add(message);
				queue.put(pair,value);
			}
			else {
				List<String> value = new ArrayList<>();
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
		List<InputParser.TestsParser.ProcessContext> processes = ctx.process();
		
		for (int i = 0; i < participants.size(); i++) {
			res += visit(participants.get(i)) + "-" + visit(processes.get(i)) + ",";
		}
		res = res.substring(0, res.length() - 1)+ "]";
		res += "-" + visit(ctx.queue()) ;
		return res; 
	}


	@Override 
	public String visitWell_formdness(InputParser.TestsParser.Well_formdnessContext ctx) { 
		try {
			String type = visit(ctx.global_type());
			String queue = visit(ctx.queue());
			return "well_formdness("+ type + "," + queue + ")";
			}
			catch (Error e) {
				throw new QueryAGTException("Type error in query");
			}	
	}
	
	@Override
	public String visitBoundness(InputParser.TestsParser.BoundnessContext ctx) {
		try {
			String type = visit(ctx.global_type());
			return "bounded("+ type + ")";
			}
			catch (Error e) {
				throw new QueryAGTException("Type error in query");
			}	
		}

	@Override
	public String visitIom(InputParser.TestsParser.IomContext ctx) {
		try {
			String type = visit(ctx.global_type());
			String queue = visit(ctx.queue());
			return "io_match("+ type + "," + queue + ")";
			}
			catch (Error e) {
				throw new QueryAGTException("Type error in query");
			}
		}

	@Override
	public String visitProjection_compute(InputParser.TestsParser.Projection_computeContext ctx) {
		int projectionCounter = this.projectionsCounter;
		this.projectionsCounter++;
		return "projection("+visit(ctx.global_type())+","+visit(ctx.participant())+",_generatedProcess"+projectionCounter+")";
	}

	
	@Override 
	public String visitAll_proj_exists(InputParser.TestsParser.All_proj_existsContext ctx) { 
		try {
			String type = visit(ctx.global_type());
			return "projection_defined_all_players("+ type + ")";
			}
			catch (Error e) {
				throw new QueryAGTException("Type error in query");
			}		
		}
	
	@Override 
	public String visitTyping(InputParser.TestsParser.TypingContext ctx) { 
		try {
			String network = visit(ctx.session());
			String type = visit(ctx.global_type());
			String queue = visit(ctx.queue());
			String res = "typing("+ network + "," + type + "-" + queue + ")";
			return res;
			}
			catch (Error e) {
				throw new QueryAGTException("Type error in query");
			}	
	}
	
	
	@Override
	public String visitProjection_exists(InputParser.TestsParser.Projection_existsContext ctx) {
		try {
			String type = visit(ctx.global_type());
			String participant = visit(ctx.participant());
			String res = "projection("+ type + "," + participant + ",_),!";
			return res;
			}
			catch (Error e) {
				throw new QueryAGTException("Type error in query");
			}
		}

	@Override 
	public String visitProjection_assert(InputParser.TestsParser.Projection_assertContext ctx) {
		try {
		String type = visit(ctx.global_type());
		String participant = visit(ctx.participant());
		String process = visit(ctx.process());
		String res = "projection("+ type + "," + participant + "," + process + "),!";
		return res;
		}
		catch (Error e) {
			throw new QueryAGTException("Type error in query");
		}
		 
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
		return visit(ctx.global_type()); 
	}
	
	@Override public String visitParenthesis_process(InputParser.TestsParser.Parenthesis_processContext ctx) {
		return visit(ctx.process()); 
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
	
	private String checkVar(String var, String type) {
		Set<String> types = this.declMap.keySet();
		for (String key : types) {
			if(!key.equals(type) && this.declMap.get(key).contains(var)) {
				throw new QueryAGTException("Variable " + var + " has type " + key + " but " + type +" is expected");
			}
		}
		if(!this.declMap.get(type).contains(var)) {
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
