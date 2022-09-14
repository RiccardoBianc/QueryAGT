// Generated from Tests.g4 by ANTLR 4.8

    package runner;
    import java.util.HashMap;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TestsParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TestsVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TestsParser#interactive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteractive(TestsParser.InteractiveContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#let}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLet(TestsParser.LetContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(TestsParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#test_group_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest_group_name(TestsParser.Test_group_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#test_group}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest_group(TestsParser.Test_groupContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#test_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest_name(TestsParser.Test_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest(TestsParser.TestContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#queries}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQueries(TestsParser.QueriesContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#query}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuery(TestsParser.QueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#all_proj_exists}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAll_proj_exists(TestsParser.All_proj_existsContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#iom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIom(TestsParser.IomContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#boundness}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoundness(TestsParser.BoundnessContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#projection_assert}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProjection_assert(TestsParser.Projection_assertContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#projection_exists}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProjection_exists(TestsParser.Projection_existsContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#well_formdness}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWell_formdness(TestsParser.Well_formdnessContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#typing}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTyping(TestsParser.TypingContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(TestsParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#parenthesis_session}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesis_session(TestsParser.Parenthesis_sessionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#parenthesis_queue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesis_queue(TestsParser.Parenthesis_queueContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#queue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQueue(TestsParser.QueueContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#session}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSession(TestsParser.SessionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#global_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobal_type(TestsParser.Global_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#head_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHead_type(TestsParser.Head_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#tail_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTail_type(TestsParser.Tail_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#process}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcess(TestsParser.ProcessContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#tail_process}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTail_process(TestsParser.Tail_processContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#head_process}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHead_process(TestsParser.Head_processContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#parenthesis_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesis_type(TestsParser.Parenthesis_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#parenthesis_process}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesis_process(TestsParser.Parenthesis_processContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#participant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParticipant(TestsParser.ParticipantContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#label}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabel(TestsParser.LabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#variableSession}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableSession(TestsParser.VariableSessionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#variableQueue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableQueue(TestsParser.VariableQueueContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#variableType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableType(TestsParser.VariableTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#variableProcess}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableProcess(TestsParser.VariableProcessContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(TestsParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestsParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(TestsParser.VariableContext ctx);
}