# Supported predicates

File ```global_types.pl``` contains the core of the tool: the SWI-Prolog implementation of relevant judgements on global types, processes, sessions and queues. 
Most important predicates provded by this file are the following: 

* Auxiliary predicates (all defined inductively)
  * ```participant_name(A)```
  * ```label(Lambda)```
  * ```unique_keys(KVs)```
  * ```map_from_to(DomainA,DomainB,List)```
  * ```pait_domain(X,Y,Left-Right)```
* Entities
  * ```global_type(G)```
    * defined coinductively 
  * ```context(Ctx)```
    * defined coinductively 
  * ```queue(Q)```
    * defined inductively 
  * ```process(P)```
    * defined coinductively 
  * ```session(S)```
    * defined coinductively 
* Operations on contexts
  * ```fill_context(Ctx,Ps,P)```
    * defined inductively 
  * ```check_each_process(Ctx,A,Lambdas,Ps,List)```
    * defined inductively 
  * ```build_context(LP1s,LP2s,A,Lambda1,Lambda2,LCtxs)```
    * defined coinductively 
  * ```build_process_result(Context,Fills,A,P)```
    * defined coinductively 
* Players operations
  * ```player(G,A)```
    * defined negating coinductive predicate
  * ```players(G,As)```
    * defined inductively with by-hand cycle detection
* Depth
  * ```all_finite_depth(G,A)```
    * defined coinductively 
* Queue manipulation
    * ```add_to_queue(A,B,Lambda,M0,M1)```
    * ```remove_from_queue(M,A,B,Lambda,M1)```
* Projection
  * ```projection(G,A,P)```
    * defined inductively with by-hand cycle detection
  * ```projection(GP_found,G,A,P)```
    * defined inductively with by-hand cycle detection
* Process preorder
  * ```process_preorder(P,Q)```
    * defined coinductively 
* Queries
  * ```typing(N,G-M)```
    * defined inductively 
  * ```project_net(N,G-Q)```
    * defined inductively 
  * ```bounded(G)```
    * defined inductively calling a coinductive predicate
  * ```io_match(G,M)```
    * defined inductively calling a coinductive predicate
  * ```well_formdness(G,M)```
    * defined inductively calling a coinductive predicate
  * ```projection_defined_all_players(G)```
    * defined inductively calling predicate ```projection```
