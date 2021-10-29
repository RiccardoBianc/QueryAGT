# Supported predicates

File ```global_types.pl``` contains the core of the tool: the SWI-Prolog implementation of relevant judgements on global types, processes, sessions and queues. 
Most important predicates provded by this file are the following: 

* Auxiliary predicates
  * ```participant_name(A)```
  * ```label(Lambda)```
  * ```unique_keys(KVs)```
  * ```map_from_to(DomainA,DomainB,List)```
  * ```pait_domain(X,Y,Left-Right)```
* Entities
  * ```global_type(G)```
  * ```context(Ctx)```
  * ```queue(Q)```
  * ```process(P)```
  * ```network(N)```
* Operations on contexts
  * ```fill_context(Ctx,Ps,P)```
  * ```check_each_process(Ctx,A,Lambdas,Ps,List)```
  * ```build_context(LP1s,LP2s,A,Lambda1,Lambda2,LCtxs)```
  * ```build_process_result(Context,Fills,A,P)```
* Players operations
  * ```player(G,A)```
  * ```players(G,As)```
* Depth
  * ```all_finite_depth(G,A)```
* Queue manipulation
    * ```add_to_queue(A,B,Lambda,M0,M1)```
    * ```remove_from_queue(M,A,B,Lambda,M1)```
* Projection
  * ```projection(G,A,P)```
  * ```projection(GP_found,G,A,P)```
* Process preorder
  * ```process_preorder(P,Q)```
* Queries
  * ```typing(N,G-M)```
  * ```project_net(N,G-Q)```
  * ```bounded(G)```
  * ```io_match(G,M)```
  * ```well_formdness(G,M)```
  * ```projection_defined_all_players(G)```
