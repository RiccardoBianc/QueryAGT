:- include(global_types).
:- use_module(library(plunit)).
:- dynamic test_info/2.

 
:- begin_tests(projection).

test(infinity_type) :- 
						X = output_type("P", "Q", ["message"- X]),
						global_type(X).


test(infinite_type_projection_existing_participant) :- 
										 X = output_type("P", "Q", ["message" - X]),
										 Y = send_process("Q",["message" - Y]),
										 \+projection(X,[],"P", Y).

test(infinite_type_projection_not_existing_participant) :- 
										 X = output_type("P", "Q", ["ciao" - X]),
										 projection(X,[],"L", zero).
										 
test(infinite_type_projection_existing_participant_wrong_process) :- 
										 X = output_type("P", "Q", ["ciao" - X]),
										 Y = send_process("L",["ciao" - Y]),
										 \+(projection(X,[],"P", Y)).
										 
test(infinite_type_projection_not_existing_participant_wrong_process) :-
										 X = output_type("P", "Q", ["ciao" - X]),
										 Y = send_process("W",["ciao" - Y]),
										 \+(projection(X,[],"L", Y)).
										 
test(player) :- X = output_type("P", "Q", ["ciao" - X]),
						player(X,"P").

test(client_server_1) :- G1 = output_type("sr","cl",["yes"- input_type("sr", "cl", "yes", G),"no"- input_type("sr", "cl", "no", G)]),
						G = output_type("cl","sr",["lq"-input_type("cl", "sr", "lq", G1),"hq"-input_type("cl", "sr", "hq", G1)]),
						Y=receive_process("sr",["yes"-send_process("sr",["lq"-Y,"hq"-Y]),"no"-send_process("sr",["lq"-Y,"hq"-Y])]),
						projection(G1,[],"cl",Y).

test(client_server_2) :- 
						G2 = output_type("cl","sr",["lq"- input_type("cl", "sr", "lq",output_type("sr", "cl",["yes"-input_type("sr", "cl", "yes", G_first),"no"-input_type("sr", "cl", "no", G_first)]))]),					 

						G_first = output_type("cl","sr",["hq"- input_type("cl", "sr", "hq",output_type("sr", "cl",["yes"-input_type("sr", "cl", "yes", G_first),"no"-input_type("sr", "cl", "no", G2)]))]),
						Y=send_process("sr",["hq"-receive_process("sr",["yes"-Y,"no"-send_process("sr",["lq"-receive_process("sr",["yes"-Y,"no"-Y])])])]),
						projection(G_first,[],"cl",Y).
	 
	
test(infinite_type_projection_on_receiver_receive_double) :- 
							 X = output_type("P", "Q",["message"- input_type("P", "Q", "message", X)]),
							 Y = receive_process("P",["message" - receive_process("P",["message" - Y])]),
							 projection(X,[],"Q",Y).

test(infinite_type_projection_on_receiver_receive_single) :- 
							 X = output_type("P", "Q",["message"- input_type("P", "Q", "message", X)]),
							 Y = receive_process("P",["message" - Y]),
							 projection(X,[],"Q",Y).

test(infinite_type_projection_on_receiver_receive_single_wrong_message_1) :- 
							 X = output_type("P", "Q",["message"- input_type("P", "Q", "message2", X)]),
							 Y = receive_process("P",["message" - Y]),
							 \+projection(X,[],"Q",Y).

test(infinite_type_projection_on_receiver_receive_single_wrong_message_2) :- 
							 X = output_type("P", "Q",["message2"- input_type("P", "Q", "message", X)]),
							 Y = receive_process("P",["message" - Y]),
							 \+projection(X,[],"Q",Y).

test(infinite_type_projection_on_receiver) :- 
							 X = output_type("P", "Q",["message"- X]),
							 Y = receive_process("P",["message" - Y]),
							 \+projection(X,[],"Q", Y).

test(infinite_type_projection_on_more_sender_1) :- 
							 X = output_type("L", "Q",["message"- output_type("P", "Q",["message"- X])]),
							 Y = receive_process("L",["message"- receive_process("P",["message"-Y])]),
							 \+projection(X,[],"Q", Y).

test(infinite_type_projection_on_more_sender_2) :- 
							 X = output_type("L", "Q",["message"- output_type("P", "Q",["message"- X])]),
							 Y = receive_process("P",["message"- receive_process("L",["message"-Y])]),
							 \+projection(X,[],"Q", Y).
				 
test(infinite_type_projection_on_double_receiver) :- 
							 X = output_type("P", "Q",["message"- output_type("P", "Q",["message2"- input_type("P", "Q", "message", input_type("P", "Q", "message2", X ))])]),
							 Y = receive_process("P",["message" - receive_process("P",["message2" - Y])]),
							 projection(X,[],"Q",Y).

test(infinite_type_projection_on_double_receiver_2) :- 
							 X = output_type("P", "Q",["message2"- input_type("P", "Q", "message2", output_type("P", "Q",["message"- input_type("P", "Q", "message", X )]))]),
							 Y = receive_process("P",["message2" - receive_process("P",["message" - Y])]),
							 projection(X,[],"Q", Y).

test(infinite_type_projection_on_receiver_more_participant) :- 
							 X = output_type("P", "L", ["message" - output_type("P", "Q", ["message" - X])]),
							 Y = receive_process("P",["message" - Y]),
							 projection(X,[],"Q", zero).
						
test(infinite_type_projection_on_receiver_more_participant_wrong_1) :- 
							 X = output_type("P", "L", ["message" - output_type("P", "Q", ["message" - X])]),
							 projection(X,[],"Q",zero).
			
test(infinite_type_projection_on_receiver_more_participant_2) :- 
							 X = output_type("P", "Q", ["message" - input_type("P","Q","message2",X) ]),
							 Y = receive_process("P",["message" - Y ]),
							 \+projection(X,[],"Q", Y).
					 							 
					 
test(crossed_dependencies) :- G = input_type("P","Q","l1", output_type("P","Q",["l1" -input_type("P","Q","l1",G), "l1"-input_type("P","Q","l1",G)])),
							  Y = send_process("Q",["l1"-Y,"l1"-Y]),     
                              \+projection(G,["P"-"Q"-["l1"]],"P", Z).

test(projectable_type_not_finite_weight) :- 
G1 = output_type("P","Q",["l1"-input_type("P","Q","l1",output_type("Q","R",["k"-input_type("Q","R","k",end)])),"l2"-input_type("P","Q","l2",G1)]),
projection(G1,[],"R",receive_process("Q",["k"-zero])).                                            


test(test_not_all_messages_read) :- G2 = output_type("P","R",["k"-G_first]),
							G_first = output_type("P","Q",["l"-input_type("P","Q","l",G_first)]),
							projection(G2,[],"R",zero).


:- end_tests(projection).

:- begin_tests(depth).
test(test_depth_zero) :- Y = output_type("B","C",["l1"-Y]), all_finite_depth(Y,"A").
test(test_depth_one_out) :- Y = output_type("B","C",["l1"-Y]), all_finite_depth(Y,"B").
test(test_depth_one_in) :- Y = input_type("B","C",_,_), all_finite_depth(Y,"C").
test(test_depth_infinite_1) :- Y = output_type("B","C",["l1"-Y,"l2"-output_type("C","E",["l1"-Y])]), \+all_finite_depth(Y,"C").

test(test_depth_infinite_2) :- G1 = output_type("P","Q",["l1"-input_type("P","Q","l1",output_type("Q","R",["k"-								  input_type("Q","R","k",end)])),"l2"-input_type("P","Q","l2",G1)]),
							   \+all_finite_depth(G1,"R").
						  
test(test_check_infinitely_often) :- 
							   G = output_type("Q","R",["lambda"-G_first]),
						       G_first = output_type("P","Q",["l1"-output_type("Q","R",["l3"-end]),"l2"-G_first]),
						       \+all_finite_depth(G,"Q").
						  
test(test_not_check_infinitely_often) :- 
						  G = output_type("Q","R",["lambda"-G_first]),
						  G_first = output_type("P","Q",["l1"-output_type("Q","R",["l3"-end]),"l2"-G_first]),
						  finite_depth(G,"Q",[]).
:- end_tests(depth).

:- begin_tests(fill).
test(substitution) :- 	G = send_process("A",["test"-zero]),
						fill_context(send_process("A",["test"-hole]),X,G),
						X = [zero].

test(send_fill) :- fill_context(send_process("A",["test"-hole]),[send_process("A",["test"-zero])],G),
						G = send_process("A",["test"-send_process("A",["test"-zero])]).

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
							 P2 = send_process("A",["L"-receive_process("B",["lambda1"-receive_process("B",["lambda"-zero])]),"L"-send_process("B",["lambda"-receive_process("B",["lambda1"-zero])])]),
							 build_context(P1,P2,"B","lambda","lambda1",Context,Res1,Res2),
							 Context = send_process("A",["L"-hole,"L"-send_process("B",["lambda"-hole])]),
							 Res1 = ["lambda"-zero,"lambda"-zero],
							 Res2 = ["lambda1"-receive_process("B",["lambda"-zero]),"lambda1"-zero].
							 
test(same_process) :- P1 = send_process("A",["lambda"-P1]),
							build_context(P1,P1,"any","any","any",Context,[],[]),
							Context = send_process("A",["lambda"- Context]).
:- end_tests(build_context).

 
:- begin_tests(check_each_context).

test(one_substition_zero) :- 
				Context = send_process("A",["lambda"-hole]),
				check_each_context(Context,"A",["lambda"],[send_process("A",["lambda"-receive_process("A",["lambda"-zero])])],Res),
				Res = [["lambda"-zero]].			
	 
test(one_substition_non_zero) :- Context = send_process("A",["lambda"-receive_process("A",["lambda"-hole])]),
				check_each_context(Context,"A",["lambda"],[send_process("A",["lambda"-receive_process("A",["lambda"-receive_process("A",["lambda"-zero])])])],Res),
				Res = [["lambda"-zero]].

test(more_holes) :-  Context = send_process("A",["lambda"-receive_process("A",["lambda"-hole]),"lambda2"-								receive_process("A",["lambda"-hole])]),
					check_each_context(Context,"A",["lambda"],[send_process("A",["lambda"-receive_process("A",["lambda"-receive_process("A",["lambda"-zero])]),"lambda2"-receive_process("A",["lambda"-receive_process("A",["lambda"-send_process("A",["ll"-zero])])])])],Res),
					Res = [["lambda"-zero],["lambda"-send_process("A",["ll"-zero])]].

test(more_branches) :- Context = receive_process("A",["lambda"-hole]),
				check_each_context(Context,"A",["lambda","lambda2"],[receive_process("A",["lambda"-receive_process("A",["lambda"-zero])]),receive_process("A",["lambda"-receive_process("A",["lambda2"-zero])])],Res),
				Res = [["lambda"-zero,"lambda2"-zero]].
	
test(more_branches_more_holes) :- Context = receive_process("A",["lambda"-hole,"lambda2"-hole]),
								check_each_context(Context,"A",["lambda","lambda2"],[receive_process("A",["lambda"-receive_process("A",["lambda"-zero]),"lambda2"-receive_process("A",["lambda"-zero])]),receive_process("A",["lambda"-receive_process("A",["lambda2"-zero]),"lambda2"-receive_process("A",["lambda2"-zero])])],Res),
								Res = [["lambda"-zero,"lambda2"-zero],["lambda"-zero,"lambda2"-zero]].
 
:- end_tests(check_each_context).
 
:- begin_tests(is_network).

test(empy_net) :- Network = ["A"-zero,"B"-zero]-[],
					network(Network).

:- end_tests(is_network).
 