# Asynchronous-global-types-implementation

Tool executed with Java version 15.0.1 and SWI-Prolog version 8.2.2.

- eclipseProject.jar is the jar containing the eclipse project
- global_types.pl is the SWI-Prolog code
- test.txt contains some example tests written in the query language
- test_generator.jar is the jar file containing the tool

To execute the tests written in file "input.txt" and write the resulting prolog code in file "code.pl" 
```
java -jar test_generator.jar input.txt code.pl
```
During the execution the tool produces a new copy of "global_types.pl" in the same folder of runnable jar and "code.pl" is overwritten if already exists.

To see and modify the query language implementation in Eclipse:
- Create a new Java project without "module-info.java" file setting a jre version >= 15.0.1
- In the new project right-click and select "Import"
- In General folder select "Archive File"
- Browse the file "eclipseProject.jar"
- Click the button "Finish"
- Right-click in the file "antlr-4.8-complete.jar" -> Build Path -> Add to Build Path
- Right click in file "TestRunner.java" Run As -> Run Configurations...
- In text-box "Program Arguments" inside the tab "Arguments" insert "input.txt code.pl" to read from file "input.txt" and to save the resulting code in "code.pl"
- Click "Apply" and the "Run"
In the project is also contained the file "Tests.g4" containing the ANTLR grammar implemented
