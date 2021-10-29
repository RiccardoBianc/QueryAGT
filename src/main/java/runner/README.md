# Classes

These classes implement the interpreter of the query language, which generates and executes the corresponding SWI-Prolog code. 
* ```TestRunner.java```  contains the main method of the interpreter
* ```CodeBuilder.java``` contains the implementation of the visitor of the syntax tree that translates the code from the query language to SWI-Prolog 
* ```Interpreter.java``` contains the implementation of the interpreter that execute the generated SWI-Prolog code 

