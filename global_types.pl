:- use_module(library(coinduction)).
:- use_module(library(apply)).
:- coinductive global_type/1.
:- coinductive process/1.
:- coinductive not_player_list/2.
:- coinductive not_player/2.
:- coinductive build_process_result/4.
:- coinductive all_finite_depth/2.
:- coinductive all_finite_depth_list/2.
:- coinductive build_context/6.
:- coinductive build_context_list/6.
:- coinductive not_consume_queue/4.
:- coinductive not_consume_queue_children/4.
:- coinductive participants/2.
:- coinductive participants_list/2.
%--------------------------------------------------------------------------------------------------------------------------------
participant_name(A) :- string(A).

label(Lambda) :- string(Lambda).

unique_keys(KVs) :-
	pairs_keys(KVs,Ks),
	is_set(Ks).

map_from_to(DomainA,DomainB,List) :-
	unique_keys(List),
	maplist(({DomainA,DomainB}/[Pair]>> pair_domain(DomainA,DomainB,Pair)), List).

pair_domain(X,Y,Left-Right):-
	call(X,Left),
	call(Y,Right).

%--------------------------------------------------------------------------------------------------------------------------------

global_type(output_type(A,B,[Lambda-G|LGs])) :- 
	A \= B,
	participant_name(A),
	participant_name(B),
	map_from_to(label,global_type,[Lambda-G|LGs]).

global_type(input_type(A,B,L,G)) :- 
	A \= B,
	participant_name(A), 
	participant_name(B),
	label(L),
	global_type(G).

global_type(end).

%--------------------------------------------------------------------------------------------------------------------------------
context(hole).

context(Ctx) :- 
	process(Ctx).

context(send_process(A,[Lambda-Ctx|LCtxs])) :-
	participant_name(A),
	map_from_to(label,context,[Lambda-Ctx|LCtxs]).

context(receive_process(A,[Lambda-Ctx|LCtxs])) :-
	participant_name(A),
	map_from_to(label,context,[Lambda-Ctx|LCtxs]).

%--------------------------------------------------------------------------------------------------------------------------------

fill_context(Ctx,Ps,P) :- fill_aux(Ctx,Ps,P,[]),!.

fill_aux(hole,[P|Ps],P,Ps) :- !.

fill_aux(Ctx,Ps,Ctx,Ps) :- process(Ctx),!.

fill_aux(send_process(A,LCtxs),Ps,send_process(A,LPs),Remaining) :- 
	fill_aux_list(LCtxs,Ps,LPs,Remaining),!.

fill_aux(receive_process(A,LCtxs),Ps,
		receive_process(A,LPs),Remaining) :-
	fill_aux_list(LCtxs,Ps,LPs,Remaining),!.

fill_aux_list([L-Ct],Ps,[L-P],Remaining) :- 
	fill_aux(Ct,Ps,P,Remaining),!.

fill_aux_list([L-Ct|LCs],Ps,[L-P|LPs],Remaining_modified) :- 
	fill_aux(Ct,Ps,P,Remaining),!, 
	fill_aux_list(LCs,Remaining,LPs,Remaining_modified),!.

%--------------------------------------------------------------------------------------------------------------------------------

check_branch([receive_process(A,[Lambda-P])],A,Lambda,[[Lambda-P]]).

check_branch([receive_process(A,[Lambda-P])|Ps],A,Lambda,[[Lambda-P]|Res]) :-
	check_branch(Ps,A,Lambda,Res).

check_each_process(Context,A,[_|Lambdas],[P|Ps],[]) :-
	process(Context),	
	fill_context(Context,[],P),
	check_each_process(Context,A,Lambdas,Ps,_).

check_each_process(Context,_,[_],[P],[]) :-
	process(Context),	
	fill_context(Context,[],P).

check_each_process(Context,A,[Lambda],[P],LPss) :-
	\+(process(Context)),
	fill_context(Context,Fillings,P),
	check_branch(Fillings,A,Lambda,LPss).

check_each_process(Context,A,[Lambda|Lambdas],[P|Ps],LPss) :-
	\+(process(Context)),
	fill_context(Context,Fillings,P),
	check_branch(Fillings,A,Lambda,Res),
	check_each_process(Context,A,Lambdas,Ps,LPss_Tail),
	\+member(Lambda,Lambdas),
	change_shape(Res,LPss_Tail,LPss).

change_shape([[LP]],[Head],[[LP|Head]]).

change_shape([[LP]|LPs_single],[Head|LPs_multi],[[LP|Head]|LPs_All]) :-
	change_shape(LPs_single,LPs_multi,LPs_All).

%--------------------------------------------------------------------------------------------------------------------------------
build_context_list([L-P1],[L-P2],A,Lambda1,Lambda2,[L-Context]) :- 
	build_context(P1,P2,A,Lambda1,Lambda2,Context).

build_context_list([L-P1|LP1s],[L-P2|LP2s],A,Lambda1,Lambda2,[L-Context|Contexts]) :-
	build_context(P1,P2,A,Lambda1,Lambda2,Context),
	build_context_list(LP1s,LP2s,A,Lambda1,Lambda2,Contexts).

build_context(zero,zero,_,_,_,zero).

build_context(receive_process(A,[Lambda1-_]),receive_process(A,[Lambda2-_]), A,Lambda1,Lambda2,hole) :-
  Lambda1 \= Lambda2.

build_context(receive_process(B,LP1),receive_process(B,LP2),A,Lambda1,Lambda2,receive_process(B,Context)) :- 
  build_context_list(LP1,LP2,A,Lambda1,Lambda2,Context).

build_context(send_process(B,LP1),send_process(B,LP2),A,Lambda1,Lambda2,send_process(B,Context)) :-
  build_context_list(LP1,LP2,A,Lambda1,Lambda2,Context).
%--------------------------------------------------------------------------------------------------------------------------------
player(G,A) :- \+not_player(G,A).

%--------------------------------------------------------------------------------------------------------------------------------

not_player_list([_-G],A) :- 
	not_player(G,A).

not_player_list([_-G|LGs],A) :- 
	not_player(G,A),!,
	not_player_list(LGs,A).

not_player(output_type(A,_,LGs), B):- 
	B \= A,
	not_player_list(LGs, B).

not_player(input_type(_,B,_,G), A) :- 
	A \= B,
	not_player(G,A).
	
not_player(end,_).
%--------------------------------------------------------------------------------------------------------------------------------

process(zero).

process(send_process(A,[Lambda-P|LPs])) :- 
	participant_name(A),
	map_from_to(label,process,[Lambda-P|LPs]).

process(receive_process(A,[Lambda-P|LPs])) :- 
	participant_name(A),
	unique_keys(LPs),
	map_from_to(label,process,[Lambda-P|LPs]).

%--------------------------------------------------------------------------------------------------------------------------------

all_finite_depth(output_type(A,B,LGs),C) :-
	finite_depth(output_type(A,B,LGs),C,[]),
	all_finite_depth_list(LGs,C).

all_finite_depth(input_type(A,B,Lambda,G),C) :- 
	finite_depth(input_type(A,B,Lambda,G),C,[]),
	all_finite_depth(G,C).

all_finite_depth(end,_).

all_finite_depth_list([_-G],A) :- 
	all_finite_depth(G,A).

all_finite_depth_list([_-G|LGs],A) :-
	all_finite_depth(G,A),
	all_finite_depth_list(LGs,A).

%--------------------------------------------------------------------------------------------------------------------------------
finite_depth_list([_-G],A,G_found) :-
	finite_depth(G,A,G_found).

finite_depth_list([_-G|LGs],A,G_found) :-
	finite_depth(G,A,G_found),
	finite_depth_list(LGs,A,G_found).

finite_depth(G,A,_) :- 
	not_player(G,A).

finite_depth(output_type(A,_,_),A,_). 

finite_depth(input_type(_,A,_,_),A,_). 

finite_depth(output_type(A,B,LGs),C,G_found) :- 
	\+member(output_type(A,B,LGs),G_found), 
	finite_depth_list(LGs,C,[output_type(A,B,LGs)|G_found]). 

finite_depth(input_type(A,B,_,G),C,G_found) :- 
	\+member(input_type(A,B,_,G),G_found), 
	finite_depth(G,C,[input_type(A,B,_,G)|G_found]).
	
%--------------------------------------------------------------------------------------------------------------------------------
add_receive([],_,[]).

add_receive([LPs],A,[receive_process(A,LPs)]).

add_receive([LPs|Lpss],A,[receive_process(A,LPs)|Ps]) :-
	add_receive(Lpss,A,Ps).

build_process_result(Context,Fills,A,P) :-
	add_receive(Fills,A,Fills_with_receive),
	fill_context(Context,Fills_with_receive,P).

%--------------------------------------------------------------------------------------------------------------------------------
add_to_queue(A,B,Lambda,M0,M1) :-
	get_assoc(A-B, M0, Queue0),
	append(Queue0,[Lambda],Queue1),
	put_assoc(A-B, M0, Queue1, M1).

add_to_queue(A,B,Lambda,M0,M1) :-
	put_assoc(A-B, M0,[Lambda], M1).


%--------------------------------------------------------------------------------------------------------------------------------

remove_from_queue(M,A,B,Lambda,M1) :-
	get_assoc(A-B, M,[Lambda]),!,
	del_assoc(A-B, M, [Lambda], M1),!.

remove_from_queue(M,A,B,Lambda,M1) :-
	get_assoc(A-B, M,[Lambda|Tail]),!,
	put_assoc(A-B, M, Tail, M1).

%--------------------------------------------------------------------------------------------------------------------------------

not_consume_queue_children([_-G],A,B,Zeta) :-
	not_consume_queue(G,A,B,Zeta).

not_consume_queue_children([_-G|LGs],A,B,Zeta) :-
	not_consume_queue(G,A,B,Zeta),
	not_consume_queue_children(LGs,A,B,Zeta).

not_consume_queue_children([_-G|_],A,B,Zeta) :-
	not_consume_queue(G,A,B,Zeta).

not_consume_queue(end,_,_,[_|_]).

not_consume_queue(input_type(A,B,Lambda,G),A,B,[Lambda|Zeta]) :-
	not_consume_queue(G,A,B,Zeta).

not_consume_queue(input_type(A,B,Lambda1,_),A,B,[Lambda2|_]) :-
	Lambda1 \= Lambda2.
	
not_consume_queue(input_type(A,B,_,G),C,D,Zeta) :-
	(A \= C ; B \= D),
	not_consume_queue(G,C,D,Zeta).

not_consume_queue(output_type(_,_,LGs),A,B,Zeta) :-
	not_consume_queue_children(LGs,A,B,Zeta),!.


not_consume_queue_list([Lambda-G],A,B,M) :- 
	add_to_queue(A,B,Lambda,M,M1),
	get_assoc(A-B,M1,Lambdas),
	not_consume_queue(G,A,B,Lambdas).
	
not_consume_queue_list([Lambda-G|LGs],A,B,M) :-
	add_to_queue(A,B,Lambda,M,M1),
	get_assoc(A-B,M1,Lambdas),
	not_consume_queue(G,A,B,Lambdas),
	not_consume_queue_list(LGs,A,B,M).

add_if_not_present(GPM_found,G,P,M,GPM_found_modified) :-
	assoc_to_keys(GPM_found,Gs_found),
	\+member(G,Gs_found),
	put_assoc(G,GPM_found,P-M,GPM_found_modified).

%--------------------------------------------------------------------------------------------------------------------------------
projection_list(GPM_found,B,C,M,[Lambda-G],A,[Lambda-P]):-
	add_to_queue(B,C,Lambda,M,M1),!,
	projection_cycle_detect(GPM_found,G,M1,A,P),!.

projection_list(GPM_found,B,C,M,[(Lambda-G)|LGs],A,[(Lambda-P)|LPs]) :-
	add_to_queue(B,C,Lambda,M,M1),!,
	projection_cycle_detect(GPM_found,G,M1,A,P),!,
	projection_list(GPM_found,B,C,M,LGs,A,LPs),!.

%--------------------------------------------------------------------------------------------------------------------------------
projection_cycle_detect(_,G,_,A,zero) :- not_player(G,A),!.

projection_cycle_detect(GPM_found,G,M,_,P) :-
	get_assoc(G,GPM_found,P-M),!.
  
projection_cycle_detect(GPM_found,
	output_type(A,B,[Lambda-G]),M,C,P) :- 
	C \= A,
	add_if_not_present(GPM_found,output_type(A,B,[Lambda-G]),P,M,GPM_found_modified),
	add_to_queue(A,B,Lambda,M,M_modified),!,
	projection_cycle_detect(GPM_found_modified,G,M_modified,B,P),!.

projection_cycle_detect(GPM_found,output_type(A,B,[Lambda1-G1,Lambda2-G2|LGs]),M,B,P) :-
  add_if_not_present(GPM_found,output_type(A,B,[Lambda1-G1,Lambda2-G2|LGs]),P,M,GPM_found_modified),
  projection_list(GPM_found_modified,A,B,M,[Lambda1-G1,Lambda2-G2|LGs],B,[Lambda1-P1,Lambda2-P2|LPs]),!,
  build_context(P1,P2,A,Lambda1,Lambda2,Context),
  pairs_keys(LGs,Lambdas),
  pairs_values(LPs,Ps),
  check_each_process(Context,A,[Lambda1,Lambda2|Lambdas],[P1,P2|Ps],Fillings),
  build_process_result(Context,Fillings,A,P).
  
projection_cycle_detect(GPM_found,output_type(A,B,LGs),M, A,send_process(B,P_children)) :-
  add_if_not_present(GPM_found,output_type(A,B,LGs),send_process(B,P_children),M,GPM_found_modified),
  \+not_consume_queue_list(LGs,A,B,M),
  projection_list(GPM_found_modified,A,B,M,LGs,A,P_children),!.

projection_cycle_detect(GPM_found,output_type(A,B,[Lambda1-G1,Lambda2-G2|LGs]),M,C, P):-
	A\=C, B\=C,
	add_if_not_present(GPM_found,output_type(A,B,[Lambda1-G1,Lambda2-G2|LGs]),P,M,GPM_found_modified),
	player(output_type(A,B,[Lambda1-G1,Lambda2-G2|LGs]),C),
	projection_list(GPM_found_modified,A,B,M,[Lambda1-G1,Lambda2-G2|LGs],C,[Lambda1-P1,Lambda2-P2|LPs]),!,
	build_context(P1,P2,R,Lambda1_prime,Lambda2_prime,Context),
	pairs_values(LPs,Ps),
	check_each_process(Context,R,[Lambda1_prime,Lambda2_prime|_],[P1,P2|Ps],Fillings),
	build_process_result(Context,Fillings,R,P).

projection_cycle_detect(GPM_found,input_type(A,B,Lambda,G_children),M,B,receive_process(A,[Lambda-P_children])) :- 
  add_if_not_present(GPM_found,input_type(A,B,Lambda,G_children),receive_process(A,[Lambda-P_children]),M,GPM_found_modified),
  remove_from_queue(M,A,B,Lambda,M1),!,
  projection_cycle_detect(GPM_found_modified,G_children,M1,B,P_children),!.

projection_cycle_detect(GPM_found,input_type(A,B,Lambda,Gs),M,C,P) :-
	C\=B,!,
	add_if_not_present(GPM_found,input_type(A,B,Lambda,Gs),P,M,GPM_found_modified),
	player(input_type(A,B,Lambda,Gs),C),
	remove_from_queue(M,A,B,Lambda,M1),!,
	projection_cycle_detect(GPM_found_modified,Gs,M1,C,P).
%--------------------------------------------------------------------------------------------------------------------------------

projection(G,M,A,P) :- 
	list_to_assoc(M,M_assoc),
	empty_assoc(GPM_found),
	projection_cycle_detect(GPM_found,G,M_assoc,A,P).
	
%--------------------------------------------------------------------------------------------------------------------------------

label_list([]).
label_list(Lambdas) :-
	maplist(label,Lambdas).

%--------------------------------------------------------------------------------------------------------------------------------

participant_pair(A-B) :-
	participant_name(A),
	participant_name(B).

%--------------------------------------------------------------------------------------------------------------------------------

queue([]).

queue([A-B-Lambdas|M]) :-
	map_from_to(participant_pair,label_list,[A-B-Lambdas|M]).

%--------------------------------------------------------------------------------------------------------------------------------

network([A1-P1,A2-P2|APs]-M) :- 
	map_from_to(participant_name,process,[A1-P1,A2-P2|APs]),
	queue(M).

%--------------------------------------------------------------------------------------------------------------------------------	

process_preorder_list([Lambda-P],LQs,Lambdas) :-
	member(Lambda,Lambdas),
	pairs_keys_values(LQs,[Lambda],[Q]),
	process_preorder(P,Q).

process_preorder_list([Lambda-_],_,Lambdas) :-
	\+member(Lambda,Lambdas).
	
process_preorder_list([Lambda-_|LPs],LQs,Lambdas) :-
	\+member(Lambda,Lambdas),
	process_preorder_list(LPs,LQs,_).

process_preorder_list([Lambda-P|LPs],LQs,Lambdas) :-
	member(Lambda,Lambdas),
	pairs_keys_values(LQs,[Lambda],[Q]),
	process_preorder(P,Q),
	process_preorder_list(LPs,LQs,_).

%--------------------------------------------------------------------------------------------------------------------------------

process_preorder(send_process(A,[Lambda-P|LPs]),send_process(A,[Lambda-Q|LQs])) :-
  pairs_keys([Lambda-P|LPs],L1s),
  pairs_keys([Lambda-Q|LQs],L2s),
  subset(L2s,L1s),
  process_preorder_list([Lambda-P|LPs],[Lambda-Q|LQs],L1s).
  
process_preorder(receive_process(A,[Lambda-P|LPs]),receive_process(A,[Lambda-Q|LQs])) :- 
  pairs_keys([Lambda-P|LPs],L1s),
  pairs_keys([Lambda-Q|LQs],L2s),
  subset(L1s,L2s),
  process_preorder_list([Lambda-P|LPs],[Lambda-Q|LQs],L2s).
	
process_preorder(zero,zero).

%--------------------------------------------------------------------------------------------------------------------------------

participants(output_type(A,B,[Lambda-G|LGs]),Res) :-
	participants_list([Lambda-G|LGs],As),
	union([A],As,As_whith_A),
	union([B],As_whith_A,As_with_A_and_B),
	permutation(As_with_A_and_B,Res).

participants(input_type(A,B,_,G),Res) :-
	participants(G,As),
	union([A],As,As_whith_A),
	union([B],As_whith_A,As_with_A_and_B),
	permutation(As_with_A_and_B,Res).
	
participants(end,[]).

participants(end,[_|_]).

participants_list([_-G],As) :-
	participants(G,As).

participants_list([_-G|LGs],As3) :-
	participants(G,As1),
	participants_list(LGs,As2),
	union(As1,As2,As3).

%--------------------------------------------------------------------------------------------------------------------------------

typing([A-P,B-Q|APs]-M,G-M) :-
	project_net([A-P,B-Q|APs],G,M),
	pairs_keys([A-P,B-Q|APs],As),
	participants(G,As).

project_net([A-P],G,M) :-
	projection(G,M,A,P_first),
	process_preorder(P,P_first).	

project_net([A-P|APs],G,M) :-
	projection(G,M,A,P_first),
	process_preorder(P,P_first),
	project_net(APs,G,M).
	
%--------------------------------------------------------------------------------------------------------------------------------	
	
fair(G) :-
	participants(G,As),
	fair_list(G,As).

fair_list(_,[]).

fair_list(G,[A|As]) :-
	all_finite_depth(G,A), fair_list(G,As).
