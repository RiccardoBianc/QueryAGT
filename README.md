# QueryAGT

Tool executed with SWI-Prolog version 8.2.4, Java version 16.0.2. To build we used Maven 3.8.3

This is the implementation of the global types formalism described in [Deconfined global types for asynchronous sessions](https://link.springer.com/chapter/10.1007/978-3-030-78142-2_3). The tool implementation is explained in detail in [Asynchronous global types in co-logic programming](https://link.springer.com/chapter/10.1007/978-3-030-78142-2_9).

Firstly you have to install Maven, Java and SWI-Prolog in the versions as above. Then you can build the tool typing


    mvn package


in the directory containing file ***pom.xml***. The produced executable jar file is contained in the generated directory ***target***, with name ***QuertAGT.jar***.
To execute the tool you have to execute command


    java -jar QueryAGT.jar [input_file] [outputfile]


in the directory containing the jar file. The first optional argument is the input filename, the second optional argument is the output filename. If no argument is provided then the tool is launched in interactive modality.

A program in this language consists of ***groups***, each one consisting of many ***tests***. Both groups and tests have names. 
For instance, the program below consists in a single group, composed by two tests. 



    Test_Group[
    Test1{
    Process P = q!L1; q!L2; P
    Process Q = p?L1; p?L2; Q
    GlobalType G = p>q!L1; p>q?L1; p>q!L2; p>q?L2; G
    Session S = p[P] | q[Q] | Empty
    io-match G|Empty
    bounded G
    proj(G,q) ==  Q
    wf G|Empty
    S has type G|Empty
    }

    Test2{
    GlobalType G = p>q!L; p>q!L; G
    not io-match G|Empty
    bounded G
        exists-proj(G,q)
    exist-all-proj G
    not wf G|Empty
    }
    ]



Each test consists of a list of declarations, followed by a list of queries. Declarations begin with a keyword for the kind of declared entity: `Process` for processes, `GlobalType` for global types, `Queue` for queues, and `Session` for sessions.
 The empty queue is represented by the constant `Empty`.  
The declarations can be mutually recursive. The tool performs a rudimentary typechecking, e.g., rejecting a program where a declared process is used as a queue. 

Queries correspond to judgments described in the papers. In particular:
* `io-match G|M` checks that the configuration type `G|M` is input/output matching
* `bounded G` checks that the global type `G` is bounded
*  `proj(G,p) == P` checks that the projection of the global type `G` on `p` is `P`
* `exists-proj(G,p)` checks that the projection of the global type `G` on `p` is well-defined
* `exist-all-proj G` checks that all the projections of the global type `G` are well-defined
* `wf G|M` checks that the configuration type `G|M` is well-formed.
* `S has type G|M` checks that `S` is well-typed with respect to the  configuration type `G|M`
*  for each query, it is also possible to check that its negation holds by prepending `not`
Again, the tool checks that entities are used in the queries accordingly to their declaration. For instance, in the typing query it is checked that the first argument is a session and the second argument is a configuration type.


In interactive modality you add variable declarations with syntax:


    let[
    decl_1
    ...
    decl_n
    ]@


where `decl_i` is a declaration. A query is written with syntax:


    query@



where `query` is a query with the syntax as above.
