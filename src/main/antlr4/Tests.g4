grammar Tests;
@header {
    package InputParser;
    import java.util.HashMap;
}

interactive
	:
	let
	| query
	;

let
	:
	LET '[' (declaration)+ ']'
	;

LET
	:
	'let'
	;

prog
	: (test_group_name '[' test_group ']')+
	;
	
test_group_name
	:
	TOKEN_IDENTIFIER
	;
	

test_group
	: (test_name '{' test '}')+
	;

test_name
	:
	TOKEN_IDENTIFIER
	;


test
	: declaration ( declaration)* queries
	;

queries
	: query (query)*
	;

query
	: projection_assert
	| projection_exists
	| typing
	| well_formdness
	| boundness
	| iom
	| all_proj_exists
	| NOT query	
	;
	
all_proj_exists:
	ALL_PROJ_EXISTS parenthesis_type
	;
	
ALL_PROJ_EXISTS:
	'exist-all-proj'
	;
	
iom:
	IOM parenthesis_type '|' parenthesis_queue;

IOM:
	'io-match';
	
boundness:
	BOUND parenthesis_type
	;
	
BOUND:
	'bounded'
	;

projection_assert 
	: PROJ '('	parenthesis_type ',' participant  ')' '==' parenthesis_process
	;

projection_exists
	:   EXISTS_PROJ '('	parenthesis_type ',' participant  ')'
	;

ON
	: 'on'
	;
	
NOT
	:
	'not'
	;

	
PROJECTION
	:
	'projection'
	;

	
EXISTS_PROJ
	:
	'exists-proj'
	;
	
well_formdness
	:
	WELL_FORMED parenthesis_type '|' parenthesis_queue
	;
	
typing
	:
	 parenthesis_session HAS TYPE parenthesis_type '|' parenthesis_queue
	;

declaration
   :  
     ATTRIBUTE_PROCESS variableProcess '=' parenthesis_process /* processi */
   | ATTRIBUTE_TYPE variableType '=' parenthesis_type /* tipi globali */
   | ATTRIBUTE_QUEUE variableQueue '=' parenthesis_queue
   | ATTRIBUTE_SESSION variableSession '=' parenthesis_session
   ;

parenthesis_session
	: '(' parenthesis_session ')'
	| session
	;	

parenthesis_queue
	: '(' parenthesis_queue ')'
	| queue
	;	
		
queue
	: 'Empty'
	|  ('<' participant ',' label ','  participant '>')+
	| variableQueue
	;

session
	: participant '[' parenthesis_process ']' ( '|' participant '[' parenthesis_process ']')* '|' parenthesis_queue
	| variableSession
	;


global_type
    : variableType
	| participant '>' participant op='!' '{' head_type tail_type '}'
	| participant '>' participant op='!'  head_type
	| participant '>' participant op='?' '{'  label ';' parenthesis_type '}'
	| participant '>' participant op='?' label ';' parenthesis_type
    | END 
	;

head_type
	: label ';' parenthesis_type
    ;

tail_type
    : (',' head_type)*
    ; 
END 
	:
	'End'
	;

process
	: participant op=('?' | '!')  '{' head_process tail_process '}'
	| participant op=('?' | '!')      head_process
	| variableProcess
    | '0' 
	;

tail_process
    : (',' head_process)*
    ;
      
head_process
    : label ';' parenthesis_process
    ;

parenthesis_type 
	: '(' parenthesis_type ')'
	|     global_type
	;

parenthesis_process 
	: '(' parenthesis_process ')'
	|     process
	;

participant
    : 
    value
    ;
	
label
    : 
    value
    ;

variableSession
	: 
	variable
	;

variableQueue
	:
	variable 
	;
	
variableType
	:
	variable
	;

variableProcess
	:
	variable
	;

value
	:
	TOKEN_IDENTIFIER
	;

variable
	:
	TOKEN_IDENTIFIER
	;

DEPTH
	:
	'depth'
	;

FINITE
	:
	'finite'
	;

PLAYER
	:
	 'player'
	;

IN 
	:
	'in'
	;
	
WELL_FORMED
	:
	'wf'
	;
	
PROJ 
	:
	'proj'
	;

TYPE
	:
	'type'
	;
	
HAS
	:
	'has'
	;
	
PART
	:
	'part'
	;

ATTRIBUTE_PROCESS 
	:
	'Process'
	;
	
ATTRIBUTE_QUEUE
	:
	'Queue'
	;
	
ATTRIBUTE_TYPE
	:
	'GlobalType'
	;
	
ATTRIBUTE_SESSION
	:
	'Session'
	;

TOKEN_IDENTIFIER
	:
	[a-zA-Z][a-zA-Z0-9_]*
	;


COMMENT
   : '/*' .*? '*/' -> skip
   ;

WS
   : [ \t\r\n] -> skip
   ;