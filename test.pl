:- include(global_types).
:- use_module(library(plunit)).
:- dynamic test_info/2.

:- begin_tests(global_type).
%X = P!Q{"label";end}
test(finite_type) :-  X = output_type("P", "Q", ["label"- end]),
						global_type(X).
%X = A?B{"label"; A!B{"label2";X}}
test(infinite_type) :-  X = input_type("A", "B", "label",output_type("A", "B", ["label2"- X])),
			global_type(X).
						
test(end_type) :- global_type(end).


:- end_tests(global_type).

:- begin_tests(process).
%X = P?{"label";zero}
test(finite_process) :-  X = receive_process("P",["label"- zero]),
			 process(X).
%X = A?{"label";A!{"label2";X}}
test(infinite_process) :-  X = receive_process("A",["label"-send_process("A",["label2"- X])]),
			   process(X).
						
test(zero_process) :-  process(zero).


:- end_tests(process).
 
:- begin_tests(player).
%X = A!B{"label";X} 
 test(existing_player) :- X = output_type("A", "B", ["label" - X]),
			  player(X,"A").
%X = A!B{"label";X}
 test(not_existing_player) :- X = output_type("A", "B", ["label" - X]),
			      \+player(X,"C").
%X = A!B{"label";X}			
 test(participant_not_player) :- X = output_type("A", "B", ["label" - X]),
				 \+player(X,"B").
 :- end_tests(player).

:- begin_tests(projection).

%X = P!Q{"label";X}
%Y = Q!{"label";Y} 
% Y is the projection of X on P
test(projection_existing_participant) :- X = output_type("P", "Q", ["label" - X]),
					 Y = send_process("Q",["label" - Y]),
					 \+projection(X,[],"P", Y).
%X = P!Q{"label";X}
test(projection_not_existing_participant) :-  X = output_type("P", "Q", ["label" - X]),
					      projection(X,[],"L", zero).
%X = P!Q{"label";X}
%Y = L!{"label";Y} 									 
test(projection_existing_participant_wrong) :-   X = output_type("P", "Q", ["label" - X]),
						 Y = send_process("L",["label" - Y]),
						 \+(projection(X,[],"P", Y)).
%X = P!Q{"label";X}
%Y = W!{"label";Y} 											 
test(projection_not_existing_participant_wrong) :- X = output_type("P", "Q", ["label" - X]),
						   Y = send_process("W",["label" - Y]),
						   \+(projection(X,[],"L", Y)).
										 

% G1 = sr!cl{"yes";sr?cl{"yes";G},"no";sr?cl{"no";G}}
% G = cl!sr{"lq";cl?sr{"lq";G1},"hq";cl?sr{"hq";G1}}
% Y = sr?{"yes";sr!{"lq";Y,"hq";Y}, "no";sr!{"lq";Y, "hq";Y}}
test(client_server_1) :- G1 = output_type("sr","cl",["yes"- input_type("sr", "cl", "yes", G),"no"- input_type("sr", "cl", "no", G)]),
			 G = output_type("cl","sr",["lq"-input_type("cl", "sr", "lq", G1),"hq"-input_type("cl", "sr", "hq", G1)]),
			 Y=receive_process("sr",["yes"-send_process("sr",["lq"-Y,"hq"-Y]),"no"-send_process("sr",["lq"-Y,"hq"-Y])]),
			 projection(G1,[],"cl",Y).

% G2 = cl!sr{"lq";cl?sr{"lq";sr!cl{"yes";sr?cl{"yes";G_first}}},"no";sr?cl{"no";G_first}}
% G_first = cl!sr{"hq";cl?sr{"hq";sr!cl{"yes";sr?cl{"yes";G_first},"no";sr?cl{"no";G2}}}}
% Y = sr!{"hq";sr?{"yes";Y,"no";sr!{"lq";sr?{"yes";Y,"no";Y}}}}
test(client_server_2) :- G2 = output_type("cl","sr",["lq"- input_type("cl", "sr", "lq",output_type("sr", "cl",["yes"-input_type("sr", "cl", "yes", G_first),"no"-input_type("sr", "cl", "no", G_first)]))]),					 
			 G_first = output_type("cl","sr",["hq"- input_type("cl", "sr", "hq",output_type("sr", "cl",["yes"-input_type("sr", "cl", "yes", G_first),"no"-input_type("sr", "cl", "no", G2)]))]),
			 Y=send_process("sr",["hq"-receive_process("sr",["yes"-Y,"no"-send_process("sr",["lq"-receive_process("sr",["yes"-Y,"no"-Y])])])]),
			 projection(G_first,[],"cl",Y).

%X = P!Q{"label";P?Q{"label";X}}
%Y = P?{"label";Y} 	
test(projection_label_exchanging) :- X = output_type("P", "Q",["label"- input_type("P", "Q", "label", X)]),
				     Y = receive_process("P",["label" - Y]),
				     projection(X,[],"Q",Y).

%X = P!Q{"label";P?Q{"label2";X}}
%Y = P?{"label";Y} 	
test(projection_label_exchanging_wrong_label1) :-  X = output_type("P", "Q",["label"- input_type("P", "Q", "label2", X)]),
						   Y = receive_process("P",["label" - Y]),
						   \+projection(X,[],"Q",Y).

%X = P!Q{"label2";P?Q{"label";X}}
%Y = P?{"label";Y} 	
test(projection_label_exchanging_wrong_label2) :-  X = output_type("P", "Q",["label2"- input_type("P", "Q", "label", X)]),
					           Y = receive_process("P",["label" - Y]),
						   \+projection(X,[],"Q",Y).

%X = P!Q{"label";X}
test(projection_on_not_player1) :- X = output_type("P", "Q",["label"- X]),
				   projection(X,[],"Q", zero).

%X = L!Q{"label";P?Q{"label";X}}
test(projection_on_not_player2) :-  X = output_type("L", "Q",["label"- output_type("P", "Q",["label"- X])]),
			            projection(X,[],"Q", zero).
%X = L!Q{"label";P?Q{"label";X}}
test(projection_on_not_player3) :- X = output_type("L", "Q",["label"- output_type("P", "Q",["label"- X])]),
				   projection(X,[],"Q", zero).

%X = P!Q{"label";P!Q{"label2";{P?Q{"label";P?Q{"label2";X}}}}}
%Y = P?{"label";P?{"label2";Y}}
test(projection_double_send1) :- X = output_type("P", "Q",["label"- output_type("P", "Q",["label2"- input_type("P", "Q", "label", input_type("P", "Q", "label2", X ))])]),
				 Y = receive_process("P",["label" - receive_process("P",["label2" - Y])]),
				 projection(X,[],"Q",Y).
				 
%X = P!Q{"label2";P?Q{"label2";{P!Q{"label";P?Q{"label";X}}}}}
%Y = P?{"label2";P?{"label";Y}}
test(projection_double_send2) :- X = output_type("P", "Q",["label2"- input_type("P", "Q", "label2", output_type("P", "Q",["label"- input_type("P", "Q", "label", X )]))]),
				 Y = receive_process("P",["label2" - receive_process("P",["label" - Y])]),
				 projection(X,[],"Q", Y).

% X = P!L{"label";P!Q{"label";X}}
test(projection_on_not_player4) :-X = output_type("P", "L", ["label" - output_type("P", "Q", ["label" - X])]),
				  projection(X,[],"Q", zero).

%X = P!L{"label";P!Q{"label";X}}	
test(projection_on_not_player5) :- X = output_type("P", "L", ["label" - output_type("P", "Q", ["label" - X])]),
				   projection(X,[],"Q",zero).

%X = P!Q{"label";P?Q{"label2";X}}
%Y = P?{"label";Y}
test(projection_on_receiver_wrong_labels) :-X = output_type("P", "Q", ["label" - input_type("P","Q","label2",X) ]),
					    Y = receive_process("P",["label" - Y ]),
					    \+projection(X,[],"Q", Y).
					 							 
%G = P?Q{"l1";P!Q{"l1";P?Q{"l1";G}},"l2";P?Q{"l2";G}}		 
%Y = Q!{"l1";Y, "l2";Y}
test(projection_crossed_dependencies) :- G = input_type("P","Q","l1", output_type("P","Q",["l1" -input_type("P","Q","l1",G), "l2"-input_type("P","Q","l2",G)])),
					 Y = send_process("Q",["l1"-Y,"l2"-Y]),     
                            		 \+projection(G,["P"-"Q"-["l1"]],"P", _).
							  
%G1 = P!Q{"l1";P?Q{"l1";Q!R{"k";Q?R{"k";end}}},"l2";P?Q{"l2";G1}}
%Y = Q?{"k";zero}
test(projection_not_finite_weight) :- G1 = output_type("P","Q",["l1"-input_type("P","Q","l1",output_type("Q","R",["k"-input_type("Q","R","k",end)])),"l2"-input_type("P","Q","l2",G1)]),
				      Y = receive_process("Q",["k"-zero]),
				      projection(G1,[],"R",Y).                                            

%G2 = P!R{"k";G_first}
%G_first = P!Q{"l";P?Q{"l";G_first}}
test(projection_not_all_messages_read) :- G2 = output_type("P","R",["k"-G_first]),
					  G_first = output_type("P","Q",["l"-input_type("P","Q","l",G_first)]),
					  \+projection(G2,[],"P",_).
%G = P!Q{"l";P?Q{"l";G}}	
%Y = Q!{"l";Y}
test(projection_all_messages_read) :- G = output_type("P","Q",["l"-input_type("P","Q","l",G)]),
				      Y = send_process("Q",["l"-Y]),
				      projection(G,[],"P",Y).

:- end_tests(projection).

:- begin_tests(depth).
test(test_depth_zero) :- Y = output_type("B","C",["l1"-Y]), all_finite_depth(Y,"A").

test(test_depth_one_out) :- Y = output_type("B","C",["l1"-Y]), all_finite_depth(Y,"B").

test(test_depth_one_in) :- Y = input_type("B","C","label",Y), all_finite_depth(Y,"C").

test(test_depth_infinite_1) :- Y = output_type("B","C",["l1"-Y,"l2"-output_type("C","E",["l1"-Y])]),
			       \+all_finite_depth(Y,"C").

test(test_depth_infinite_2) :- G1 = output_type("P","Q",["l1"-input_type("P","Q","l1",output_type("Q","R",["k"-input_type("Q","R","k",end)])),"l2"-input_type("P","Q","l2",G1)]),
			       \+all_finite_depth(G1,"R").
						  
test(test_check_infinitely_often) :- G = output_type("Q","R",["label"-G_first]),
		                     G_first = output_type("P","Q",["l1"-output_type("Q","R",["l3"-end]),"l2"-G_first]),
				     \+all_finite_depth(G,"Q").
						  
test(test_not_check_infinitely_often) :- G = output_type("Q","R",["label"-G_first]),
					 G_first = output_type("P","Q",["l1"-output_type("Q","R",["l3"-end]),"l2"-G_first]),
					 finite_depth(G,"Q",[]).
:- end_tests(depth).

:- begin_tests(fill).
test(substitution) :- 	G = send_process("A",["label"-zero]),
			fill_context(send_process("A",["label"-hole]),X,G),
			X = [zero].

test(send_fill) :- fill_context(send_process("A",["label"-hole]),[send_process("A",["label"-zero])],G),
		   G = send_process("A",["label"-send_process("A",["label"-zero])]).

test(process_fill) :- 	Y = receive_process("A",["test"-Y]),
			fill_context(send_process("A",["first" - Y, "second"-hole]),[send_process("A",["test"-zero])],G),
			G = send_process("A",["first"-Y,"second"-send_process("A",["test"-zero])]).

test(longer_input) :- 	Y = receive_process("A",["test"-Y]),
			\+fill_context(send_process("A",["first" - Y, "second"-hole]),[send_process("A",["test"-zero]),zero],_).

test(shorter_input) :- fill_context(send_process("A",["first" - zero, "second"-hole]),[send_process("A",["test"-zero])],G),
		       G = send_process("A",["first" - zero, "second"-send_process("A",["test"-zero])]).

test(more_holes) :- fill_context(send_process("A",["first"-hole,"second"-send_process("A",["third"-hole])]),[send_process("A",["test1"-zero]),send_process("A",["test2"-zero])],G),
		    G = send_process("A",["first"-send_process("A",["test1"-zero]),"second"-send_process("A",["third"-send_process("A",["test2"-zero])])]).

:- end_tests(fill).

:- begin_tests(build_context).

test(check_context_right) :- P1 = send_process("A",["L"-receive_process("B",["lambda"-zero]),"L"-send_process("B",["lambda"-receive_process("B",["lambda"-zero])])]),
			     P2 = send_process("A",["L"-receive_process("B",["lambda1"-receive_process("B",["lambda"-zero])]),
			     		"L"-send_process("B",["lambda"-receive_process("B",["lambda1"-zero])])]),
			     build_context(P1,P2,"B","lambda","lambda1",Context),
                             Context = send_process("A",["L"-hole,"L"-send_process("B",["lambda"-hole])]).
							 
test(same_process) :- P1 = send_process("A",["lambda"-P1]),
		      build_context(P1,P1,"any","any","any",Context),
		      Context = send_process("A",["lambda"- Context]).
:- end_tests(build_context).

 
:- begin_tests(check_each_process).

test(one_substition_zero) :- Context = send_process("A",["lambda"-hole]),
			     check_each_process(Context,"A",["lambda"],[send_process("A",["lambda"-receive_process("A",["lambda"-zero])])],Res),
			     Res = [["lambda"-zero]].			
	 
test(one_substition_non_zero) :- Context = send_process("A",["lambda"-receive_process("A",["lambda"-hole])]),
				 check_each_process(Context,"A",["lambda"],[send_process("A",["lambda"-receive_process("A",["lambda"-receive_process("A",["lambda"-zero])])])],Res),
				 Res = [["lambda"-zero]].

test(more_holes) :-  Context = send_process("A",["lambda"-receive_process("A",["lambda"-hole]),"lambda2"-								receive_process("A",["lambda"-hole])]),
		     check_each_process(Context,"A",["lambda"],[send_process("A",["lambda"-receive_process("A",["lambda"-receive_process("A",["lambda"-zero])]),"lambda2"-receive_process("A",["lambda"-receive_process("A",["lambda"-send_process("A",["ll"-zero])])])])],Res),
		     Res = [["lambda"-zero],["lambda"-send_process("A",["ll"-zero])]].

test(more_branches) :- Context = receive_process("A",["lambda"-hole]),
		       check_each_process(Context,"A",["lambda","lambda2"],[receive_process("A",["lambda"-receive_process("A",["lambda"-zero])]),receive_process("A",["lambda"-receive_process("A",["lambda2"-zero])])],Res),
		       Res = [["lambda"-zero,"lambda2"-zero]].
	
test(more_branches_more_holes) :- Context = receive_process("A",["lambda"-hole,"lambda2"-hole]),
				  check_each_process(Context,"A",["lambda","lambda2"],[receive_process("A",["lambda"-receive_process("A",["lambda"-zero]),"lambda2"-receive_process("A",["lambda"-zero])]),receive_process("A",["lambda"-receive_process("A",["lambda2"-zero]),"lambda2"-receive_process("A",["lambda2"-zero])])],Res),
		                  Res = [["lambda"-zero,"lambda2"-zero],["lambda"-zero,"lambda2"-zero]].
 
:- end_tests(check_each_process).
 
:- begin_tests(is_network).

test(empy_net) :- Network = ["A"-zero,"B"-zero]-[], network(Network).

:- end_tests(is_network).
 
