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
:- coinductive process_preorder_list/3.
:- coinductive process_preorder/2.

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

check_branch([receive_process(A,[Lambda-P])],A,Lambda,[Lambda-P]).

check_branch([receive_process(A,[Lambda-P])|Ps],A,Lambda,[Lambda-P|Res]) :-
	check_branch(Ps,A,Lambda,Res).

check_each_process(Context,_,[_],[P],[]) :-
	process(Context),
	fill_context(Context,[],P).

check_each_process(Context,A,[_|Lambdas],[P|Ps],[]) :-	
	process(Context),
	fill_context(Context,[],P),
	check_each_process(Context,A,Lambdas,Ps,_).


check_each_process(Context,A,[Lambda],[P],LPss) :-
	fill_context(Context,Fillings,P),
	check_branch(Fillings,A,Lambda,LPs),
	cons_choice(LPs,LPss).

check_each_process(Context,A,[Lambda|Lambdas],[P|Ps],LPss) :-
	fill_context(Context,Fillings,P),
	check_branch(Fillings,A,Lambda,Res),
	check_each_process(Context,A,Lambdas,Ps,LPss_Tail),
	\+member(Lambda,Lambdas),
	add_branch(Res,LPss_Tail,LPss).

add_branch([LP],[Head],[[LP|Head]]).

add_branch([LP|LPs_single],[Head|LPs_multi],[[LP|Head]|LPs_All]) :-
	add_branch(LPs_single,LPs_multi,LPs_All).

cons_choice([Lambda-P],[[Lambda-P]]).

cons_choice([Lambda-P|LPs],[[Lambda-P]|LPss]) :- cons_choice(LPs,LPss).

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

add_if_not_present(GP_found,G,P,GP_found_modified) :-
	assoc_to_keys(GP_found,Gs_found),
	\+member(G,Gs_found),
	put_assoc(G,GP_found,P,GP_found_modified).

%--------------------------------------------------------------------------------------------------------------------------------
projection_list(GP_found,[Lambda-G],A,[Lambda-P]):-
	projection(GP_found,G,A,P),!.

projection_list(GP_found,[Lambda-G|LGs],A,[Lambda-P|LPs]) :-
	projection(GP_found,G,A,P),!,
	projection_list(GP_found,LGs,A,LPs),!.	

%--------------------------------------------------------------------------------------------------------------------------------

projection(GP_found,G,_,P) :-
	get_assoc(G,GP_found,P).
	
projection(_,G,A,zero) :- not_player(G,A).


projection(GP_found,output_type(A,B,LGs),A,send_process(B,LPs)) :-
  pairs_keys(LGs,KeysG),
  pairs_keys(LPs,KeysG),
  add_if_not_present(GP_found,output_type(A,B,LGs),send_process(B,LPs),GP_found_modified),
  player(output_type(A,B,LGs),A),
  projection_list(GP_found_modified,LGs,A,LPs).

projection(GP_found,output_type(A,B,[Lambda1-G1,Lambda2-G2|LGs]),B,P) :-
  add_if_not_present(GP_found,output_type(A,B,[Lambda1-G1,Lambda2-G2|LGs]),P,GP_found_modified),
  player(output_type(A,B,[Lambda1-G1,Lambda2-G2|LGs]),B),
  projection_list(GP_found_modified,[Lambda1-G1,Lambda2-G2|LGs],B,[Lambda1-P1,Lambda2-P2|LPs]),
  build_context_list([_-P1],[_-P2],A,Lambda1,Lambda2,[_-Context]),!,
  pairs_keys(LGs,Lambdas),
  pairs_values(LPs,Ps),
  check_each_process(Context,A,[Lambda1,Lambda2|Lambdas],[P1,P2|Ps],Fillings),
  build_process_result(Context,Fillings,A,P).

projection(GP_found,output_type(A,B,[Lambda1-G1,Lambda2-G2|LGs]),C, P):-
	A\=C,B\=C,
	add_if_not_present(GP_found,output_type(A,B,[Lambda1-G1,Lambda2-G2|LGs]),P,GP_found_modified),
	player(output_type(A,B,[Lambda1-G1,Lambda2-G2|LGs]),C),
	projection_list(GP_found_modified,[Lambda1-G1,Lambda2-G2|LGs],C,[Lambda1-P1,Lambda2-P2|LPs]),
	build_context_list([_-P1],[_-P2],R,Lambda1_prime,Lambda2_prime,[_-Context]),!,
	pairs_values(LPs,Ps),
	check_each_process(Context,R,[Lambda1_prime,Lambda2_prime|_],[P1,P2|Ps],Fillings),
	build_process_result(Context,Fillings,R,P).

projection(GP_found,output_type(A,B,[Lambda-G]),C,P) :- 
	C \= A,
	add_if_not_present(GP_found,output_type(A,B,[Lambda-G]),P,GP_found_modified),
	player(output_type(A,B,[Lambda-G]),C),
	projection(GP_found_modified,G,C,P),!.

projection(GP_found,input_type(A,B,Lambda,G),B,receive_process(A,[Lambda-P_children])) :- 
	add_if_not_present(GP_found,input_type(A,B,Lambda,G),receive_process(A,[Lambda-P_children]),GP_found_modified),
	player(input_type(A,B,Lambda,G),B),
	projection(GP_found_modified,G,B,P_children).


projection(GP_found,input_type(A,B,Lambda,G),C,P) :-
	C\=B,
	add_if_not_present(GP_found,input_type(A,B,Lambda,G),P,GP_found_modified),
	player(input_type(A,B,Lambda,G),C),
	projection(GP_found_modified,G,C,P).


%--------------------------------------------------------------------------------------------------------------------------------

projection(G,A,P) :- 
	empty_assoc(GP_found),
	projection(GP_found,G,A,P).
	
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

process_preorder_list([Lambda-P],LQs,_) :-
	member(Lambda-Q,LQs),
	process_preorder(P,Q),!.

process_preorder_list([Lambda-_],LQs,_) :- 
	\+member(Lambda-_,LQs).

process_preorder_list([Lambda-P|LPs],LQs,Lambdas) :-
	member(Lambda-Q,LQs),
	process_preorder(P,Q),!,
	process_preorder_list(LPs,LQs,Lambdas),!.

process_preorder_list([Lambda-_|LPs],LQs,Lambdas) :-
	\+member(Lambda-_,LQs),
	process_preorder_list(LPs,LQs,Lambdas),!.

%--------------------------------------------------------------------------------------------------------------------------------

process_preorder(send_process(A,LPs),send_process(A,LQs)) :-
  pairs_keys(LPs,L1s),
  pairs_keys(LQs,L2s),
  subset(L1s,L2s),
  process_preorder_list(LPs,LQs,L2s).
  
process_preorder(receive_process(A,LPs),receive_process(A,LQs)) :- 
  pairs_keys(LPs,L1s),
  pairs_keys(LQs,L2s),
  subset(L2s,L1s),
  process_preorder_list(LPs,LQs,L1s).
	
process_preorder(zero,zero).

%--------------------------------------------------------------------------------------------------------------------------------

players(Gs,G,[]) :-
	member(G,Gs).

players(Gs,output_type(A,B,LGs),[B|Res]) :-
	\+member(output_type(A,B,LGs),Gs),
	players_list([output_type(A,B,LGs)|Gs],LGs,Res).
	
players(Gs,input_type(A,B,Lambda,G),[A|Res]) :-
	\+member(input_type(A,B,Lambda,G),Gs),
	players([input_type(A,B,Lambda,G)|Gs],G,Res).
	
players(_,end,[]).

players_list(Gs,[_-G],Res) :-
	players(Gs,G,Res).

players_list(Gs,[_-G|LGs],Res_tot) :-
	players(Gs,G,Res),
	players_list(Gs,LGs,Res_remaining),
	append(Res,Res_remaining,Res_tot).
	
players(G,As) :- players([],G,Res),sort(Res,Res_no_repetitions),permutation(Res_no_repetitions,As).
%--------------------------------------------------------------------------------------------------------------------------------

typing([A-P,B-Q|APs]-M,G-M) :-
	queue(M),
	project_net([A-P,B-Q|APs],G-M),
	pairs_keys([A-P,B-Q|APs],As),!,
	players(G,Bs),
	subset(Bs,As).

project_net([A-P],G-_) :-
	projection(G,A,P_first),
	process_preorder(P,P_first).

project_net([A-P|APs],G-M) :-
	projection(G,A,P_first),
	process_preorder(P,P_first),
	project_net(APs,G-M).
	
%--------------------------------------------------------------------------------------------------------------------------------	
	
bounded(G) :-
	players(G,As),!,
	bounded_list(G,As).

bounded_list(G,[A]) :-
	all_finite_depth(G,A),!.

bounded_list(G,[A|As]) :-
	all_finite_depth(G,A),!,
	bounded_list(G,As).

%--------------------------------------------------------------------------------------------------------------------------------	

io_match_list(GMs,A,B,[Lambda-G],M) :-
	add_to_queue(A,B,Lambda,M,M1),
	io_match(GMs,G,M1).

io_match_list(GMs,A,B,[Lambda-G|LGs],M) :-
	add_to_queue(A,B,Lambda,M,M1),
	io_match(GMs,G,M1),
	io_match_list(GMs,A,B,LGs,M).

optional(A-B, M, Lambdas1) :-
	get_assoc(A-B, M, Lambdas1).

optional(_, _, []).

check_subset_queue(M,[],MM_prime,M_prime_0,M_prime) :-
	empty_assoc(M),
	empty_assoc(MM_prime),
	empty_assoc(M_prime_0),
	empty_assoc(M_prime).

check_subset_queue(M,[A-B],MM_prime,M_prime_0,M_prime) :-
	optional(A-B, M, Lambdas1),
	get_assoc(A-B, MM_prime, Lambdas2),
	append(Lambdas1, Lambdas_prime, Lambdas2),
	put_assoc(A-B, M_prime_0, Lambdas_prime, M_prime).

check_subset_queue(M,[A-B|ABs],MM_prime,M_prime_0,Res) :-
	optional(A-B, M, Lambdas1),
	get_assoc(A-B, MM_prime, Lambdas2),
	append(Lambdas1, Lambdas_prime, Lambdas2),
	put_assoc(A-B, M_prime_0, Lambdas_prime, M_prime_1),
	check_subset_queue(M,ABs,MM_prime,M_prime_1,Res).

io_match(GMs,G,MM_prime) :-
	member(G-M,GMs),
	assoc_to_keys(MM_prime, ABs),
	empty_assoc(M_prime_0),
	check_subset_queue(M,ABs,MM_prime,M_prime_0,M_prime),!,
	read_queue([],G,M),!,
	read_queue_infinite([],G,M_prime),
	check_remaining([],G,M_prime),!.

io_match(GMs,end,M) :- 	
	\+member(end-_,GMs),
	empty_assoc(M).

io_match(GMs,input_type(A,B,Lambda,G),M) :- 
	\+member(input_type(A,B,Lambda,G)-_,GMs),
	get_assoc(A-B, M,[Lambda|_]),
	remove_from_queue(M,A,B,Lambda,M1),
	io_match([input_type(A,B,Lambda,G)-M|GMs],G,M1). 

io_match(GMs,output_type(A,B,LGs),M) :-
	\+member(output_type(A,B,LGs)-_,GMs),
	io_match_list([output_type(A,B,LGs)-M|GMs],A,B,LGs,M).

io_match(G,M_Pairs) :-
	list_to_assoc(M_Pairs,M_assoc),
	io_match([],G,M_assoc).

well_formdness(G,M) :-
	bounded(G),
	projection_defined_all_players(G),
	io_match(G,M).
	
/*-------------------------------------------------------------------------------------------------------------*/

projection_defined_all_players(G) :-
	players(G,As),!,
	projection_defined_all_players_list(G,As).

projection_defined_all_players_list(G,[A]) :- 
	projection(G,A,_),!.

projection_defined_all_players_list(G,[A|As]) :-
	projection(G,A,_),!,
	projection_defined_all_players_list(G,As).

/*-------------------------------------------------------------------------------------------------------------*/
check_remaining_list(GMs,[_-G],M) :-
	check_remaining(GMs,G,M).
	
check_remaining_list(GMs,[_-G|LGs],M) :-
	check_remaining(GMs,G,M),
	check_remaining_list(GMs,LGs,M).

check_remaining(GMs,G,M) :-
	member(G-M,GMs).
	
check_remaining(GMs,end,M) :- \+member(end,GMs).

check_remaining(GMs,input_type(A,B,Lambda,G),M) :- 
	\+member(input_type(A,B,Lambda,G)-M,GMs),
	check_remaining([input_type(A,B,Lambda,G)-M|GMs],G,M).

check_remaining(GMs,output_type(A,B,[Lambda-G]),M) :- 
	\+member(output_type(A,B,[Lambda-G])-M,GMs),
	get_assoc(A-B, M,Little_queue),%side condition verification
	append(Little_queue, [Lambda], [Lambda | Little_queue_prime]),
	put_assoc(A-B, M, Little_queue_prime, M_prime),
	check_remaining_list([output_type(A,B,[Lambda-G])-M|GMs],[Lambda-G],M_prime).

check_remaining(GMs,output_type(A,B,LGs),M) :-
	\+member(output_type(A,B,LGs)-M,GMs),
	get_assoc(A-B, M,[]),%side condition verification
	check_remaining_list([output_type(A,B,LGs)-M|GMs],LGs,M).

check_remaining(GMs,output_type(A,B,LGs),M) :- 
	\+member(output_type(A,B,LGs)-M,GMs),
	\+get_assoc(A-B, M,_),%side condition verification
	check_remaining_list([output_type(A,B,LGs)-M|GMs],LGs,M).


read_list(GMs,[_-G],M) :-
	read_queue(GMs,G,M).
	
read_list(GMs,[_-G|LGs],M) :-
	read_queue(GMs,G,M),
	read_list(GMs,LGs,M).

read_queue(_,_,M) :- empty_assoc(M).

read_queue(GMs,input_type(A,B,Lambda,G),M) :-	
	\+member(input_type(A,B,Lambda,G)-M,GMs),
	remove_from_queue(M,A,B,Lambda,M_prime),
	read_queue([input_type(A,B,Lambda,G)-M|GMs],G,M_prime).

read_queue(GMs,input_type(A,B,Lambda,G),M) :-
	\+member(input_type(A,B,Lambda,G)-M,GMs),
	read_queue([input_type(A,B,Lambda,G)-M|GMs],G,M).
	
read_queue(GMs,output_type(A,B,LGs),M) :-
	\+member(output_type(A,B,LGs)-M,GMs),
	read_list([output_type(A,B,LGs)-M|GMs],LGs,M).

%--------------------------------------------------------------------------------------------------------------------------------

read_list_infinite(Gs,[_-G],M) :-
	read_queue_infinite(Gs,G,M).
	
read_list_infinite(Gs,[_-G|LGs],M) :-
	read_queue_infinite(Gs,G,M),
	read_list_infinite(Gs,LGs,M).

read_queue_infinite(Gs,G,M) :- 
	member(G,Gs),
	read_queue([],G,M).

read_queue_infinite(_,_,M) :- empty_assoc(M).

read_queue_infinite(Gs,input_type(A,B,Lambda,G),M) :-	
	\+member(input_type(A,B,Lambda,G),Gs),
	read_queue_infinite([input_type(A,B,Lambda,G)|Gs],G,M).

read_queue_infinite(Gs,input_type(A,B,Lambda,G),M) :-
	\+member(input_type(A,B,Lambda,G),Gs),
	read_queue_infinite([input_type(A,B,Lambda,G)|Gs],G,M).
	
read_queue_infinite(Gs,output_type(A,B,LGs),M) :-
	\+member(output_type(A,B,LGs),Gs),
	read_list([output_type(A,B,LGs)|Gs],LGs,M).

%--------------------------------------------------------------------------------------------------------------------------------
