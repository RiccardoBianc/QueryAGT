# QueryAGT

This is a simple tool to experiment with the global types formalism for asynchronous sessions described in [1]. 
Details on the implementation can be found in [2]. 
1. Francesco Dagnino, Paola Giannini and Mariangiola Dezani-Ciancaglini (2021).
[Deconfined global types for asynchronous sessions](https://link.springer.com/chapter/10.1007/978-3-030-78142-2_3). 
**Coordination Models and Languages - 23rn International Conference, COORDINATION 2021**
2. Riccardo Bianchini and Francesco Dagnino (2021).
[Asynchronous global types in co-logic programming](https://link.springer.com/chapter/10.1007/978-3-030-78142-2_9).
**Coordination Models and Languages - 23rn International Conference, COORDINATION 2021**
The tool can be either installed locally or it can be executed as a Docker container

## Docker container
To pull the container the command is



    docker pull riccardobia/queryagt



-

## Prerequisites 
* SWI-Prolog (>= v8.2.4) 
* Java (>= v16.0.2)
* Maven (>= v3.8.3)

## Building
To build the tool just clone the repository and run


    mvn package


in the downloaded directory.  
The executable jar file will be at the path ``target/QueryAGT.jar```. 

## Running
To execute the tool you need to have SWI-Prolog in the PATH.
Then run 


    java -jar [path-to-jar/]QueryAGT.jar [path_input_file] [path_output_file]


There are two execution modes: the interactive one and the batch one. 
If no argument is provided, then the tool starts in interactive mode.
otherwise, the tool executes the code from the input file (first argument) and writes the output to the output file (second argument or standard output if not specified). 

## Interactive mode
In interactive mode you have two possible commands: variable declaration and query execution. At the end of the command you have to add ```;;``` as terminator to execute it. 

The syntax of a declaration  command is the following: 



    let [ 
      declaration-1
      ... 
      declaration-n
    ] ;; 


Each declaration begins with a keyword for the kind of declared entity: `Process` for processes, `GlobalType` for global types, `Queue` for queues, and `Session` for sessions.
Declarations inside a `let` block can be mutually recursive. 
All declared variables are in the global scope. 

The tool performs a rudimentary typechecking, checking that all used variables are declared with the correct type,  e.g., rejecting a declaration block where a process variable is used as a queue. 
For instance, the following are valid declaration commands: 



    let [
      GlobalType G1 = p>q!l; G2 
      GlobalType G2 = q>p! {
          m1; p>q?l; q>p?m1; G1, 
          m2; p>q?l; q>p?m2; End 
      }
      Process P  = q!l; P1 
      Process P1 = q?{ m1; P, m2; 0 }
      Process Q  = p!{ m1; p?l; Q, m2; p?l; 0 }
    ] ;; 
    let [
      Session S  = p[P] | q[Q] | Empty 
      Process Q1 = p?l; Q
      Queue M = <p,l,q>,<q,m1,p> 
    ] ;; 


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
A query is written with syntax:


    query ;; 


For instance, the following are valid queries: 


    proj(G1,q) ==  Q ;; 
    p[P1] | q[Q1] | M has type (p>q?l; q>p?m1; G) | M ;;


To terminate the interactive session run 


    exit ;; 



## Batch mode
The source file to be executed in batch mode consists of groups of tests. 
Both groups and tests have names. 
A test has the following structure: 


    test-name {
      declaration-1
      ... 
      declaration-n
      query-1
      ...
      query-m
    }


where declarations and queries are the same as above.
Declarations are local to each test. 
For instance, the the following code defines a single group, composed by two tests. 



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




## Testing
File ***test.txt*** contains 124 queries to test the tool.
