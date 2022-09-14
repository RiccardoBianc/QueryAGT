// Generated from Tests.g4 by ANTLR 4.8

    package runner;
    import java.util.HashMap;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TestsParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		LET=18, ALL_PROJ_EXISTS=19, IOM=20, BOUND=21, ON=22, NOT=23, PROJECTION=24, 
		EXISTS_PROJ=25, END=26, DEPTH=27, FINITE=28, PLAYER=29, IN=30, WELL_FORMED=31, 
		PROJ=32, TYPE=33, HAS=34, PART=35, ATTRIBUTE_PROCESS=36, ATTRIBUTE_QUEUE=37, 
		ATTRIBUTE_TYPE=38, ATTRIBUTE_SESSION=39, TOKEN_IDENTIFIER=40, COMMENT=41, 
		WS=42;
	public static final int
		RULE_interactive = 0, RULE_let = 1, RULE_prog = 2, RULE_test_group_name = 3, 
		RULE_test_group = 4, RULE_test_name = 5, RULE_test = 6, RULE_queries = 7, 
		RULE_query = 8, RULE_all_proj_exists = 9, RULE_iom = 10, RULE_boundness = 11, 
		RULE_projection_assert = 12, RULE_projection_exists = 13, RULE_well_formdness = 14, 
		RULE_typing = 15, RULE_declaration = 16, RULE_parenthesis_session = 17, 
		RULE_parenthesis_queue = 18, RULE_queue = 19, RULE_session = 20, RULE_global_type = 21, 
		RULE_head_type = 22, RULE_tail_type = 23, RULE_process = 24, RULE_tail_process = 25, 
		RULE_head_process = 26, RULE_parenthesis_type = 27, RULE_parenthesis_process = 28, 
		RULE_participant = 29, RULE_label = 30, RULE_variableSession = 31, RULE_variableQueue = 32, 
		RULE_variableType = 33, RULE_variableProcess = 34, RULE_value = 35, RULE_variable = 36;
	private static String[] makeRuleNames() {
		return new String[] {
			"interactive", "let", "prog", "test_group_name", "test_group", "test_name", 
			"test", "queries", "query", "all_proj_exists", "iom", "boundness", "projection_assert", 
			"projection_exists", "well_formdness", "typing", "declaration", "parenthesis_session", 
			"parenthesis_queue", "queue", "session", "global_type", "head_type", 
			"tail_type", "process", "tail_process", "head_process", "parenthesis_type", 
			"parenthesis_process", "participant", "label", "variableSession", "variableQueue", 
			"variableType", "variableProcess", "value", "variable"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'['", "']'", "'{'", "'}'", "'|'", "'('", "','", "')'", "'=='", 
			"'='", "'Empty'", "'<'", "'>'", "'!'", "'?'", "';'", "'0'", "'let'", 
			"'exist-all-proj'", "'io-match'", "'bounded'", "'on'", "'not'", "'projection'", 
			"'exists-proj'", "'End'", "'depth'", "'finite'", "'player'", "'in'", 
			"'wf'", "'proj'", "'type'", "'has'", "'part'", "'Process'", "'Queue'", 
			"'GlobalType'", "'Session'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, "LET", "ALL_PROJ_EXISTS", "IOM", 
			"BOUND", "ON", "NOT", "PROJECTION", "EXISTS_PROJ", "END", "DEPTH", "FINITE", 
			"PLAYER", "IN", "WELL_FORMED", "PROJ", "TYPE", "HAS", "PART", "ATTRIBUTE_PROCESS", 
			"ATTRIBUTE_QUEUE", "ATTRIBUTE_TYPE", "ATTRIBUTE_SESSION", "TOKEN_IDENTIFIER", 
			"COMMENT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Tests.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TestsParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class InteractiveContext extends ParserRuleContext {
		public LetContext let() {
			return getRuleContext(LetContext.class,0);
		}
		public QueryContext query() {
			return getRuleContext(QueryContext.class,0);
		}
		public InteractiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interactive; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitInteractive(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InteractiveContext interactive() throws RecognitionException {
		InteractiveContext _localctx = new InteractiveContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_interactive);
		try {
			setState(76);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LET:
				enterOuterAlt(_localctx, 1);
				{
				setState(74);
				let();
				}
				break;
			case T__5:
			case ALL_PROJ_EXISTS:
			case IOM:
			case BOUND:
			case NOT:
			case EXISTS_PROJ:
			case WELL_FORMED:
			case PROJ:
			case TOKEN_IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(75);
				query();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LetContext extends ParserRuleContext {
		public TerminalNode LET() { return getToken(TestsParser.LET, 0); }
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public LetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_let; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitLet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LetContext let() throws RecognitionException {
		LetContext _localctx = new LetContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_let);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			match(LET);
			setState(79);
			match(T__0);
			setState(81); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(80);
				declaration();
				}
				}
				setState(83); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ATTRIBUTE_PROCESS) | (1L << ATTRIBUTE_QUEUE) | (1L << ATTRIBUTE_TYPE) | (1L << ATTRIBUTE_SESSION))) != 0) );
			setState(85);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProgContext extends ParserRuleContext {
		public List<Test_group_nameContext> test_group_name() {
			return getRuleContexts(Test_group_nameContext.class);
		}
		public Test_group_nameContext test_group_name(int i) {
			return getRuleContext(Test_group_nameContext.class,i);
		}
		public List<Test_groupContext> test_group() {
			return getRuleContexts(Test_groupContext.class);
		}
		public Test_groupContext test_group(int i) {
			return getRuleContext(Test_groupContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(87);
				test_group_name();
				setState(88);
				match(T__0);
				setState(89);
				test_group();
				setState(90);
				match(T__1);
				}
				}
				setState(94); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==TOKEN_IDENTIFIER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Test_group_nameContext extends ParserRuleContext {
		public TerminalNode TOKEN_IDENTIFIER() { return getToken(TestsParser.TOKEN_IDENTIFIER, 0); }
		public Test_group_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_test_group_name; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitTest_group_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Test_group_nameContext test_group_name() throws RecognitionException {
		Test_group_nameContext _localctx = new Test_group_nameContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_test_group_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(TOKEN_IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Test_groupContext extends ParserRuleContext {
		public List<Test_nameContext> test_name() {
			return getRuleContexts(Test_nameContext.class);
		}
		public Test_nameContext test_name(int i) {
			return getRuleContext(Test_nameContext.class,i);
		}
		public List<TestContext> test() {
			return getRuleContexts(TestContext.class);
		}
		public TestContext test(int i) {
			return getRuleContext(TestContext.class,i);
		}
		public Test_groupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_test_group; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitTest_group(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Test_groupContext test_group() throws RecognitionException {
		Test_groupContext _localctx = new Test_groupContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_test_group);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(98);
				test_name();
				setState(99);
				match(T__2);
				setState(100);
				test();
				setState(101);
				match(T__3);
				}
				}
				setState(105); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==TOKEN_IDENTIFIER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Test_nameContext extends ParserRuleContext {
		public TerminalNode TOKEN_IDENTIFIER() { return getToken(TestsParser.TOKEN_IDENTIFIER, 0); }
		public Test_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_test_name; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitTest_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Test_nameContext test_name() throws RecognitionException {
		Test_nameContext _localctx = new Test_nameContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_test_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			match(TOKEN_IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TestContext extends ParserRuleContext {
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public QueriesContext queries() {
			return getRuleContext(QueriesContext.class,0);
		}
		public TestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_test; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitTest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TestContext test() throws RecognitionException {
		TestContext _localctx = new TestContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_test);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			declaration();
			setState(113);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ATTRIBUTE_PROCESS) | (1L << ATTRIBUTE_QUEUE) | (1L << ATTRIBUTE_TYPE) | (1L << ATTRIBUTE_SESSION))) != 0)) {
				{
				{
				setState(110);
				declaration();
				}
				}
				setState(115);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(116);
			queries();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QueriesContext extends ParserRuleContext {
		public List<QueryContext> query() {
			return getRuleContexts(QueryContext.class);
		}
		public QueryContext query(int i) {
			return getRuleContext(QueryContext.class,i);
		}
		public QueriesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_queries; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitQueries(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QueriesContext queries() throws RecognitionException {
		QueriesContext _localctx = new QueriesContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_queries);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			query();
			setState(122);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << ALL_PROJ_EXISTS) | (1L << IOM) | (1L << BOUND) | (1L << NOT) | (1L << EXISTS_PROJ) | (1L << WELL_FORMED) | (1L << PROJ) | (1L << TOKEN_IDENTIFIER))) != 0)) {
				{
				{
				setState(119);
				query();
				}
				}
				setState(124);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QueryContext extends ParserRuleContext {
		public Projection_assertContext projection_assert() {
			return getRuleContext(Projection_assertContext.class,0);
		}
		public Projection_existsContext projection_exists() {
			return getRuleContext(Projection_existsContext.class,0);
		}
		public TypingContext typing() {
			return getRuleContext(TypingContext.class,0);
		}
		public Well_formdnessContext well_formdness() {
			return getRuleContext(Well_formdnessContext.class,0);
		}
		public BoundnessContext boundness() {
			return getRuleContext(BoundnessContext.class,0);
		}
		public IomContext iom() {
			return getRuleContext(IomContext.class,0);
		}
		public All_proj_existsContext all_proj_exists() {
			return getRuleContext(All_proj_existsContext.class,0);
		}
		public TerminalNode NOT() { return getToken(TestsParser.NOT, 0); }
		public QueryContext query() {
			return getRuleContext(QueryContext.class,0);
		}
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_query);
		try {
			setState(134);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PROJ:
				enterOuterAlt(_localctx, 1);
				{
				setState(125);
				projection_assert();
				}
				break;
			case EXISTS_PROJ:
				enterOuterAlt(_localctx, 2);
				{
				setState(126);
				projection_exists();
				}
				break;
			case T__5:
			case TOKEN_IDENTIFIER:
				enterOuterAlt(_localctx, 3);
				{
				setState(127);
				typing();
				}
				break;
			case WELL_FORMED:
				enterOuterAlt(_localctx, 4);
				{
				setState(128);
				well_formdness();
				}
				break;
			case BOUND:
				enterOuterAlt(_localctx, 5);
				{
				setState(129);
				boundness();
				}
				break;
			case IOM:
				enterOuterAlt(_localctx, 6);
				{
				setState(130);
				iom();
				}
				break;
			case ALL_PROJ_EXISTS:
				enterOuterAlt(_localctx, 7);
				{
				setState(131);
				all_proj_exists();
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 8);
				{
				setState(132);
				match(NOT);
				setState(133);
				query();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class All_proj_existsContext extends ParserRuleContext {
		public TerminalNode ALL_PROJ_EXISTS() { return getToken(TestsParser.ALL_PROJ_EXISTS, 0); }
		public Parenthesis_typeContext parenthesis_type() {
			return getRuleContext(Parenthesis_typeContext.class,0);
		}
		public All_proj_existsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_all_proj_exists; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitAll_proj_exists(this);
			else return visitor.visitChildren(this);
		}
	}

	public final All_proj_existsContext all_proj_exists() throws RecognitionException {
		All_proj_existsContext _localctx = new All_proj_existsContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_all_proj_exists);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(ALL_PROJ_EXISTS);
			setState(137);
			parenthesis_type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IomContext extends ParserRuleContext {
		public TerminalNode IOM() { return getToken(TestsParser.IOM, 0); }
		public Parenthesis_typeContext parenthesis_type() {
			return getRuleContext(Parenthesis_typeContext.class,0);
		}
		public Parenthesis_queueContext parenthesis_queue() {
			return getRuleContext(Parenthesis_queueContext.class,0);
		}
		public IomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iom; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitIom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IomContext iom() throws RecognitionException {
		IomContext _localctx = new IomContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_iom);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			match(IOM);
			setState(140);
			parenthesis_type();
			setState(141);
			match(T__4);
			setState(142);
			parenthesis_queue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoundnessContext extends ParserRuleContext {
		public TerminalNode BOUND() { return getToken(TestsParser.BOUND, 0); }
		public Parenthesis_typeContext parenthesis_type() {
			return getRuleContext(Parenthesis_typeContext.class,0);
		}
		public BoundnessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boundness; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitBoundness(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoundnessContext boundness() throws RecognitionException {
		BoundnessContext _localctx = new BoundnessContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_boundness);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			match(BOUND);
			setState(145);
			parenthesis_type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Projection_assertContext extends ParserRuleContext {
		public TerminalNode PROJ() { return getToken(TestsParser.PROJ, 0); }
		public Parenthesis_typeContext parenthesis_type() {
			return getRuleContext(Parenthesis_typeContext.class,0);
		}
		public ParticipantContext participant() {
			return getRuleContext(ParticipantContext.class,0);
		}
		public Parenthesis_processContext parenthesis_process() {
			return getRuleContext(Parenthesis_processContext.class,0);
		}
		public Projection_assertContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_projection_assert; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitProjection_assert(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Projection_assertContext projection_assert() throws RecognitionException {
		Projection_assertContext _localctx = new Projection_assertContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_projection_assert);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			match(PROJ);
			setState(148);
			match(T__5);
			setState(149);
			parenthesis_type();
			setState(150);
			match(T__6);
			setState(151);
			participant();
			setState(152);
			match(T__7);
			setState(153);
			match(T__8);
			setState(154);
			parenthesis_process();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Projection_existsContext extends ParserRuleContext {
		public TerminalNode EXISTS_PROJ() { return getToken(TestsParser.EXISTS_PROJ, 0); }
		public Parenthesis_typeContext parenthesis_type() {
			return getRuleContext(Parenthesis_typeContext.class,0);
		}
		public ParticipantContext participant() {
			return getRuleContext(ParticipantContext.class,0);
		}
		public Projection_existsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_projection_exists; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitProjection_exists(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Projection_existsContext projection_exists() throws RecognitionException {
		Projection_existsContext _localctx = new Projection_existsContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_projection_exists);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			match(EXISTS_PROJ);
			setState(157);
			match(T__5);
			setState(158);
			parenthesis_type();
			setState(159);
			match(T__6);
			setState(160);
			participant();
			setState(161);
			match(T__7);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Well_formdnessContext extends ParserRuleContext {
		public TerminalNode WELL_FORMED() { return getToken(TestsParser.WELL_FORMED, 0); }
		public Parenthesis_typeContext parenthesis_type() {
			return getRuleContext(Parenthesis_typeContext.class,0);
		}
		public Parenthesis_queueContext parenthesis_queue() {
			return getRuleContext(Parenthesis_queueContext.class,0);
		}
		public Well_formdnessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_well_formdness; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitWell_formdness(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Well_formdnessContext well_formdness() throws RecognitionException {
		Well_formdnessContext _localctx = new Well_formdnessContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_well_formdness);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			match(WELL_FORMED);
			setState(164);
			parenthesis_type();
			setState(165);
			match(T__4);
			setState(166);
			parenthesis_queue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypingContext extends ParserRuleContext {
		public Parenthesis_sessionContext parenthesis_session() {
			return getRuleContext(Parenthesis_sessionContext.class,0);
		}
		public TerminalNode HAS() { return getToken(TestsParser.HAS, 0); }
		public TerminalNode TYPE() { return getToken(TestsParser.TYPE, 0); }
		public Parenthesis_typeContext parenthesis_type() {
			return getRuleContext(Parenthesis_typeContext.class,0);
		}
		public Parenthesis_queueContext parenthesis_queue() {
			return getRuleContext(Parenthesis_queueContext.class,0);
		}
		public TypingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typing; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitTyping(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypingContext typing() throws RecognitionException {
		TypingContext _localctx = new TypingContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_typing);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			parenthesis_session();
			setState(169);
			match(HAS);
			setState(170);
			match(TYPE);
			setState(171);
			parenthesis_type();
			setState(172);
			match(T__4);
			setState(173);
			parenthesis_queue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationContext extends ParserRuleContext {
		public TerminalNode ATTRIBUTE_PROCESS() { return getToken(TestsParser.ATTRIBUTE_PROCESS, 0); }
		public VariableProcessContext variableProcess() {
			return getRuleContext(VariableProcessContext.class,0);
		}
		public Parenthesis_processContext parenthesis_process() {
			return getRuleContext(Parenthesis_processContext.class,0);
		}
		public TerminalNode ATTRIBUTE_TYPE() { return getToken(TestsParser.ATTRIBUTE_TYPE, 0); }
		public VariableTypeContext variableType() {
			return getRuleContext(VariableTypeContext.class,0);
		}
		public Parenthesis_typeContext parenthesis_type() {
			return getRuleContext(Parenthesis_typeContext.class,0);
		}
		public TerminalNode ATTRIBUTE_QUEUE() { return getToken(TestsParser.ATTRIBUTE_QUEUE, 0); }
		public VariableQueueContext variableQueue() {
			return getRuleContext(VariableQueueContext.class,0);
		}
		public Parenthesis_queueContext parenthesis_queue() {
			return getRuleContext(Parenthesis_queueContext.class,0);
		}
		public TerminalNode ATTRIBUTE_SESSION() { return getToken(TestsParser.ATTRIBUTE_SESSION, 0); }
		public VariableSessionContext variableSession() {
			return getRuleContext(VariableSessionContext.class,0);
		}
		public Parenthesis_sessionContext parenthesis_session() {
			return getRuleContext(Parenthesis_sessionContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_declaration);
		try {
			setState(195);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATTRIBUTE_PROCESS:
				enterOuterAlt(_localctx, 1);
				{
				setState(175);
				match(ATTRIBUTE_PROCESS);
				setState(176);
				variableProcess();
				setState(177);
				match(T__9);
				setState(178);
				parenthesis_process();
				}
				break;
			case ATTRIBUTE_TYPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(180);
				match(ATTRIBUTE_TYPE);
				setState(181);
				variableType();
				setState(182);
				match(T__9);
				setState(183);
				parenthesis_type();
				}
				break;
			case ATTRIBUTE_QUEUE:
				enterOuterAlt(_localctx, 3);
				{
				setState(185);
				match(ATTRIBUTE_QUEUE);
				setState(186);
				variableQueue();
				setState(187);
				match(T__9);
				setState(188);
				parenthesis_queue();
				}
				break;
			case ATTRIBUTE_SESSION:
				enterOuterAlt(_localctx, 4);
				{
				setState(190);
				match(ATTRIBUTE_SESSION);
				setState(191);
				variableSession();
				setState(192);
				match(T__9);
				setState(193);
				parenthesis_session();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Parenthesis_sessionContext extends ParserRuleContext {
		public Parenthesis_sessionContext parenthesis_session() {
			return getRuleContext(Parenthesis_sessionContext.class,0);
		}
		public SessionContext session() {
			return getRuleContext(SessionContext.class,0);
		}
		public Parenthesis_sessionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parenthesis_session; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitParenthesis_session(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Parenthesis_sessionContext parenthesis_session() throws RecognitionException {
		Parenthesis_sessionContext _localctx = new Parenthesis_sessionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_parenthesis_session);
		try {
			setState(202);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(197);
				match(T__5);
				setState(198);
				parenthesis_session();
				setState(199);
				match(T__7);
				}
				break;
			case TOKEN_IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(201);
				session();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Parenthesis_queueContext extends ParserRuleContext {
		public Parenthesis_queueContext parenthesis_queue() {
			return getRuleContext(Parenthesis_queueContext.class,0);
		}
		public QueueContext queue() {
			return getRuleContext(QueueContext.class,0);
		}
		public Parenthesis_queueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parenthesis_queue; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitParenthesis_queue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Parenthesis_queueContext parenthesis_queue() throws RecognitionException {
		Parenthesis_queueContext _localctx = new Parenthesis_queueContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_parenthesis_queue);
		try {
			setState(209);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(204);
				match(T__5);
				setState(205);
				parenthesis_queue();
				setState(206);
				match(T__7);
				}
				break;
			case T__10:
			case T__11:
			case TOKEN_IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(208);
				queue();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QueueContext extends ParserRuleContext {
		public List<ParticipantContext> participant() {
			return getRuleContexts(ParticipantContext.class);
		}
		public ParticipantContext participant(int i) {
			return getRuleContext(ParticipantContext.class,i);
		}
		public List<LabelContext> label() {
			return getRuleContexts(LabelContext.class);
		}
		public LabelContext label(int i) {
			return getRuleContext(LabelContext.class,i);
		}
		public VariableQueueContext variableQueue() {
			return getRuleContext(VariableQueueContext.class,0);
		}
		public QueueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_queue; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitQueue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QueueContext queue() throws RecognitionException {
		QueueContext _localctx = new QueueContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_queue);
		int _la;
		try {
			setState(225);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__10:
				enterOuterAlt(_localctx, 1);
				{
				setState(211);
				match(T__10);
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 2);
				{
				setState(220); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(212);
					match(T__11);
					setState(213);
					participant();
					setState(214);
					match(T__6);
					setState(215);
					label();
					setState(216);
					match(T__6);
					setState(217);
					participant();
					setState(218);
					match(T__12);
					}
					}
					setState(222); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__11 );
				}
				break;
			case TOKEN_IDENTIFIER:
				enterOuterAlt(_localctx, 3);
				{
				setState(224);
				variableQueue();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SessionContext extends ParserRuleContext {
		public List<ParticipantContext> participant() {
			return getRuleContexts(ParticipantContext.class);
		}
		public ParticipantContext participant(int i) {
			return getRuleContext(ParticipantContext.class,i);
		}
		public List<Parenthesis_processContext> parenthesis_process() {
			return getRuleContexts(Parenthesis_processContext.class);
		}
		public Parenthesis_processContext parenthesis_process(int i) {
			return getRuleContext(Parenthesis_processContext.class,i);
		}
		public Parenthesis_queueContext parenthesis_queue() {
			return getRuleContext(Parenthesis_queueContext.class,0);
		}
		public VariableSessionContext variableSession() {
			return getRuleContext(VariableSessionContext.class,0);
		}
		public SessionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_session; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitSession(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SessionContext session() throws RecognitionException {
		SessionContext _localctx = new SessionContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_session);
		try {
			int _alt;
			setState(246);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(227);
				participant();
				setState(228);
				match(T__0);
				setState(229);
				parenthesis_process();
				setState(230);
				match(T__1);
				setState(239);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(231);
						match(T__4);
						setState(232);
						participant();
						setState(233);
						match(T__0);
						setState(234);
						parenthesis_process();
						setState(235);
						match(T__1);
						}
						} 
					}
					setState(241);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				}
				setState(242);
				match(T__4);
				setState(243);
				parenthesis_queue();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(245);
				variableSession();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Global_typeContext extends ParserRuleContext {
		public Token op;
		public VariableTypeContext variableType() {
			return getRuleContext(VariableTypeContext.class,0);
		}
		public List<ParticipantContext> participant() {
			return getRuleContexts(ParticipantContext.class);
		}
		public ParticipantContext participant(int i) {
			return getRuleContext(ParticipantContext.class,i);
		}
		public Head_typeContext head_type() {
			return getRuleContext(Head_typeContext.class,0);
		}
		public Tail_typeContext tail_type() {
			return getRuleContext(Tail_typeContext.class,0);
		}
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public Parenthesis_typeContext parenthesis_type() {
			return getRuleContext(Parenthesis_typeContext.class,0);
		}
		public TerminalNode END() { return getToken(TestsParser.END, 0); }
		public Global_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_global_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitGlobal_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Global_typeContext global_type() throws RecognitionException {
		Global_typeContext _localctx = new Global_typeContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_global_type);
		try {
			setState(283);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(248);
				variableType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(249);
				participant();
				setState(250);
				match(T__12);
				setState(251);
				participant();
				setState(252);
				((Global_typeContext)_localctx).op = match(T__13);
				setState(253);
				match(T__2);
				setState(254);
				head_type();
				setState(255);
				tail_type();
				setState(256);
				match(T__3);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(258);
				participant();
				setState(259);
				match(T__12);
				setState(260);
				participant();
				setState(261);
				((Global_typeContext)_localctx).op = match(T__13);
				setState(262);
				head_type();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(264);
				participant();
				setState(265);
				match(T__12);
				setState(266);
				participant();
				setState(267);
				((Global_typeContext)_localctx).op = match(T__14);
				setState(268);
				match(T__2);
				setState(269);
				label();
				setState(270);
				match(T__15);
				setState(271);
				parenthesis_type();
				setState(272);
				match(T__3);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(274);
				participant();
				setState(275);
				match(T__12);
				setState(276);
				participant();
				setState(277);
				((Global_typeContext)_localctx).op = match(T__14);
				setState(278);
				label();
				setState(279);
				match(T__15);
				setState(280);
				parenthesis_type();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(282);
				match(END);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Head_typeContext extends ParserRuleContext {
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public Parenthesis_typeContext parenthesis_type() {
			return getRuleContext(Parenthesis_typeContext.class,0);
		}
		public Head_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_head_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitHead_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Head_typeContext head_type() throws RecognitionException {
		Head_typeContext _localctx = new Head_typeContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_head_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			label();
			setState(286);
			match(T__15);
			setState(287);
			parenthesis_type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Tail_typeContext extends ParserRuleContext {
		public List<Head_typeContext> head_type() {
			return getRuleContexts(Head_typeContext.class);
		}
		public Head_typeContext head_type(int i) {
			return getRuleContext(Head_typeContext.class,i);
		}
		public Tail_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tail_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitTail_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Tail_typeContext tail_type() throws RecognitionException {
		Tail_typeContext _localctx = new Tail_typeContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_tail_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6) {
				{
				{
				setState(289);
				match(T__6);
				setState(290);
				head_type();
				}
				}
				setState(295);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProcessContext extends ParserRuleContext {
		public Token op;
		public ParticipantContext participant() {
			return getRuleContext(ParticipantContext.class,0);
		}
		public Head_processContext head_process() {
			return getRuleContext(Head_processContext.class,0);
		}
		public Tail_processContext tail_process() {
			return getRuleContext(Tail_processContext.class,0);
		}
		public VariableProcessContext variableProcess() {
			return getRuleContext(VariableProcessContext.class,0);
		}
		public ProcessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_process; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitProcess(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProcessContext process() throws RecognitionException {
		ProcessContext _localctx = new ProcessContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_process);
		int _la;
		try {
			setState(309);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(296);
				participant();
				setState(297);
				((ProcessContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__13 || _la==T__14) ) {
					((ProcessContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(298);
				match(T__2);
				setState(299);
				head_process();
				setState(300);
				tail_process();
				setState(301);
				match(T__3);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(303);
				participant();
				setState(304);
				((ProcessContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__13 || _la==T__14) ) {
					((ProcessContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(305);
				head_process();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(307);
				variableProcess();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(308);
				match(T__16);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Tail_processContext extends ParserRuleContext {
		public List<Head_processContext> head_process() {
			return getRuleContexts(Head_processContext.class);
		}
		public Head_processContext head_process(int i) {
			return getRuleContext(Head_processContext.class,i);
		}
		public Tail_processContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tail_process; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitTail_process(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Tail_processContext tail_process() throws RecognitionException {
		Tail_processContext _localctx = new Tail_processContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_tail_process);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(315);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6) {
				{
				{
				setState(311);
				match(T__6);
				setState(312);
				head_process();
				}
				}
				setState(317);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Head_processContext extends ParserRuleContext {
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public Parenthesis_processContext parenthesis_process() {
			return getRuleContext(Parenthesis_processContext.class,0);
		}
		public Head_processContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_head_process; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitHead_process(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Head_processContext head_process() throws RecognitionException {
		Head_processContext _localctx = new Head_processContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_head_process);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(318);
			label();
			setState(319);
			match(T__15);
			setState(320);
			parenthesis_process();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Parenthesis_typeContext extends ParserRuleContext {
		public Parenthesis_typeContext parenthesis_type() {
			return getRuleContext(Parenthesis_typeContext.class,0);
		}
		public Global_typeContext global_type() {
			return getRuleContext(Global_typeContext.class,0);
		}
		public Parenthesis_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parenthesis_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitParenthesis_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Parenthesis_typeContext parenthesis_type() throws RecognitionException {
		Parenthesis_typeContext _localctx = new Parenthesis_typeContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_parenthesis_type);
		try {
			setState(327);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(322);
				match(T__5);
				setState(323);
				parenthesis_type();
				setState(324);
				match(T__7);
				}
				break;
			case END:
			case TOKEN_IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(326);
				global_type();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Parenthesis_processContext extends ParserRuleContext {
		public Parenthesis_processContext parenthesis_process() {
			return getRuleContext(Parenthesis_processContext.class,0);
		}
		public ProcessContext process() {
			return getRuleContext(ProcessContext.class,0);
		}
		public Parenthesis_processContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parenthesis_process; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitParenthesis_process(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Parenthesis_processContext parenthesis_process() throws RecognitionException {
		Parenthesis_processContext _localctx = new Parenthesis_processContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_parenthesis_process);
		try {
			setState(334);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(329);
				match(T__5);
				setState(330);
				parenthesis_process();
				setState(331);
				match(T__7);
				}
				break;
			case T__16:
			case TOKEN_IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(333);
				process();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParticipantContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public ParticipantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_participant; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitParticipant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParticipantContext participant() throws RecognitionException {
		ParticipantContext _localctx = new ParticipantContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_participant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(336);
			value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LabelContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public LabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_label; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitLabel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LabelContext label() throws RecognitionException {
		LabelContext _localctx = new LabelContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(338);
			value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableSessionContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public VariableSessionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableSession; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitVariableSession(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableSessionContext variableSession() throws RecognitionException {
		VariableSessionContext _localctx = new VariableSessionContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_variableSession);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(340);
			variable();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableQueueContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public VariableQueueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableQueue; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitVariableQueue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableQueueContext variableQueue() throws RecognitionException {
		VariableQueueContext _localctx = new VariableQueueContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_variableQueue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(342);
			variable();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableTypeContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public VariableTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitVariableType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableTypeContext variableType() throws RecognitionException {
		VariableTypeContext _localctx = new VariableTypeContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_variableType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(344);
			variable();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableProcessContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public VariableProcessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableProcess; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitVariableProcess(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableProcessContext variableProcess() throws RecognitionException {
		VariableProcessContext _localctx = new VariableProcessContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_variableProcess);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(346);
			variable();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public TerminalNode TOKEN_IDENTIFIER() { return getToken(TestsParser.TOKEN_IDENTIFIER, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(348);
			match(TOKEN_IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode TOKEN_IDENTIFIER() { return getToken(TestsParser.TOKEN_IDENTIFIER, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestsVisitor ) return ((TestsVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(350);
			match(TOKEN_IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3,\u0163\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\3\2\5\2O\n\2\3\3\3\3\3\3\6\3T"+
		"\n\3\r\3\16\3U\3\3\3\3\3\4\3\4\3\4\3\4\3\4\6\4_\n\4\r\4\16\4`\3\5\3\5"+
		"\3\6\3\6\3\6\3\6\3\6\6\6j\n\6\r\6\16\6k\3\7\3\7\3\b\3\b\7\br\n\b\f\b\16"+
		"\bu\13\b\3\b\3\b\3\t\3\t\7\t{\n\t\f\t\16\t~\13\t\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\5\n\u0089\n\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3"+
		"\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u00c6\n\22\3\23\3\23\3\23\3\23"+
		"\3\23\5\23\u00cd\n\23\3\24\3\24\3\24\3\24\3\24\5\24\u00d4\n\24\3\25\3"+
		"\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\6\25\u00df\n\25\r\25\16\25\u00e0"+
		"\3\25\5\25\u00e4\n\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\7\26\u00f0\n\26\f\26\16\26\u00f3\13\26\3\26\3\26\3\26\3\26\5\26\u00f9"+
		"\n\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u011e\n\27\3\30\3\30\3\30"+
		"\3\30\3\31\3\31\7\31\u0126\n\31\f\31\16\31\u0129\13\31\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\5\32\u0138\n\32\3\33"+
		"\3\33\7\33\u013c\n\33\f\33\16\33\u013f\13\33\3\34\3\34\3\34\3\34\3\35"+
		"\3\35\3\35\3\35\3\35\5\35\u014a\n\35\3\36\3\36\3\36\3\36\3\36\5\36\u0151"+
		"\n\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3&\2\2\'\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJ\2"+
		"\3\3\2\20\21\2\u0160\2N\3\2\2\2\4P\3\2\2\2\6^\3\2\2\2\bb\3\2\2\2\ni\3"+
		"\2\2\2\fm\3\2\2\2\16o\3\2\2\2\20x\3\2\2\2\22\u0088\3\2\2\2\24\u008a\3"+
		"\2\2\2\26\u008d\3\2\2\2\30\u0092\3\2\2\2\32\u0095\3\2\2\2\34\u009e\3\2"+
		"\2\2\36\u00a5\3\2\2\2 \u00aa\3\2\2\2\"\u00c5\3\2\2\2$\u00cc\3\2\2\2&\u00d3"+
		"\3\2\2\2(\u00e3\3\2\2\2*\u00f8\3\2\2\2,\u011d\3\2\2\2.\u011f\3\2\2\2\60"+
		"\u0127\3\2\2\2\62\u0137\3\2\2\2\64\u013d\3\2\2\2\66\u0140\3\2\2\28\u0149"+
		"\3\2\2\2:\u0150\3\2\2\2<\u0152\3\2\2\2>\u0154\3\2\2\2@\u0156\3\2\2\2B"+
		"\u0158\3\2\2\2D\u015a\3\2\2\2F\u015c\3\2\2\2H\u015e\3\2\2\2J\u0160\3\2"+
		"\2\2LO\5\4\3\2MO\5\22\n\2NL\3\2\2\2NM\3\2\2\2O\3\3\2\2\2PQ\7\24\2\2QS"+
		"\7\3\2\2RT\5\"\22\2SR\3\2\2\2TU\3\2\2\2US\3\2\2\2UV\3\2\2\2VW\3\2\2\2"+
		"WX\7\4\2\2X\5\3\2\2\2YZ\5\b\5\2Z[\7\3\2\2[\\\5\n\6\2\\]\7\4\2\2]_\3\2"+
		"\2\2^Y\3\2\2\2_`\3\2\2\2`^\3\2\2\2`a\3\2\2\2a\7\3\2\2\2bc\7*\2\2c\t\3"+
		"\2\2\2de\5\f\7\2ef\7\5\2\2fg\5\16\b\2gh\7\6\2\2hj\3\2\2\2id\3\2\2\2jk"+
		"\3\2\2\2ki\3\2\2\2kl\3\2\2\2l\13\3\2\2\2mn\7*\2\2n\r\3\2\2\2os\5\"\22"+
		"\2pr\5\"\22\2qp\3\2\2\2ru\3\2\2\2sq\3\2\2\2st\3\2\2\2tv\3\2\2\2us\3\2"+
		"\2\2vw\5\20\t\2w\17\3\2\2\2x|\5\22\n\2y{\5\22\n\2zy\3\2\2\2{~\3\2\2\2"+
		"|z\3\2\2\2|}\3\2\2\2}\21\3\2\2\2~|\3\2\2\2\177\u0089\5\32\16\2\u0080\u0089"+
		"\5\34\17\2\u0081\u0089\5 \21\2\u0082\u0089\5\36\20\2\u0083\u0089\5\30"+
		"\r\2\u0084\u0089\5\26\f\2\u0085\u0089\5\24\13\2\u0086\u0087\7\31\2\2\u0087"+
		"\u0089\5\22\n\2\u0088\177\3\2\2\2\u0088\u0080\3\2\2\2\u0088\u0081\3\2"+
		"\2\2\u0088\u0082\3\2\2\2\u0088\u0083\3\2\2\2\u0088\u0084\3\2\2\2\u0088"+
		"\u0085\3\2\2\2\u0088\u0086\3\2\2\2\u0089\23\3\2\2\2\u008a\u008b\7\25\2"+
		"\2\u008b\u008c\58\35\2\u008c\25\3\2\2\2\u008d\u008e\7\26\2\2\u008e\u008f"+
		"\58\35\2\u008f\u0090\7\7\2\2\u0090\u0091\5&\24\2\u0091\27\3\2\2\2\u0092"+
		"\u0093\7\27\2\2\u0093\u0094\58\35\2\u0094\31\3\2\2\2\u0095\u0096\7\"\2"+
		"\2\u0096\u0097\7\b\2\2\u0097\u0098\58\35\2\u0098\u0099\7\t\2\2\u0099\u009a"+
		"\5<\37\2\u009a\u009b\7\n\2\2\u009b\u009c\7\13\2\2\u009c\u009d\5:\36\2"+
		"\u009d\33\3\2\2\2\u009e\u009f\7\33\2\2\u009f\u00a0\7\b\2\2\u00a0\u00a1"+
		"\58\35\2\u00a1\u00a2\7\t\2\2\u00a2\u00a3\5<\37\2\u00a3\u00a4\7\n\2\2\u00a4"+
		"\35\3\2\2\2\u00a5\u00a6\7!\2\2\u00a6\u00a7\58\35\2\u00a7\u00a8\7\7\2\2"+
		"\u00a8\u00a9\5&\24\2\u00a9\37\3\2\2\2\u00aa\u00ab\5$\23\2\u00ab\u00ac"+
		"\7$\2\2\u00ac\u00ad\7#\2\2\u00ad\u00ae\58\35\2\u00ae\u00af\7\7\2\2\u00af"+
		"\u00b0\5&\24\2\u00b0!\3\2\2\2\u00b1\u00b2\7&\2\2\u00b2\u00b3\5F$\2\u00b3"+
		"\u00b4\7\f\2\2\u00b4\u00b5\5:\36\2\u00b5\u00c6\3\2\2\2\u00b6\u00b7\7("+
		"\2\2\u00b7\u00b8\5D#\2\u00b8\u00b9\7\f\2\2\u00b9\u00ba\58\35\2\u00ba\u00c6"+
		"\3\2\2\2\u00bb\u00bc\7\'\2\2\u00bc\u00bd\5B\"\2\u00bd\u00be\7\f\2\2\u00be"+
		"\u00bf\5&\24\2\u00bf\u00c6\3\2\2\2\u00c0\u00c1\7)\2\2\u00c1\u00c2\5@!"+
		"\2\u00c2\u00c3\7\f\2\2\u00c3\u00c4\5$\23\2\u00c4\u00c6\3\2\2\2\u00c5\u00b1"+
		"\3\2\2\2\u00c5\u00b6\3\2\2\2\u00c5\u00bb\3\2\2\2\u00c5\u00c0\3\2\2\2\u00c6"+
		"#\3\2\2\2\u00c7\u00c8\7\b\2\2\u00c8\u00c9\5$\23\2\u00c9\u00ca\7\n\2\2"+
		"\u00ca\u00cd\3\2\2\2\u00cb\u00cd\5*\26\2\u00cc\u00c7\3\2\2\2\u00cc\u00cb"+
		"\3\2\2\2\u00cd%\3\2\2\2\u00ce\u00cf\7\b\2\2\u00cf\u00d0\5&\24\2\u00d0"+
		"\u00d1\7\n\2\2\u00d1\u00d4\3\2\2\2\u00d2\u00d4\5(\25\2\u00d3\u00ce\3\2"+
		"\2\2\u00d3\u00d2\3\2\2\2\u00d4\'\3\2\2\2\u00d5\u00e4\7\r\2\2\u00d6\u00d7"+
		"\7\16\2\2\u00d7\u00d8\5<\37\2\u00d8\u00d9\7\t\2\2\u00d9\u00da\5> \2\u00da"+
		"\u00db\7\t\2\2\u00db\u00dc\5<\37\2\u00dc\u00dd\7\17\2\2\u00dd\u00df\3"+
		"\2\2\2\u00de\u00d6\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00de\3\2\2\2\u00e0"+
		"\u00e1\3\2\2\2\u00e1\u00e4\3\2\2\2\u00e2\u00e4\5B\"\2\u00e3\u00d5\3\2"+
		"\2\2\u00e3\u00de\3\2\2\2\u00e3\u00e2\3\2\2\2\u00e4)\3\2\2\2\u00e5\u00e6"+
		"\5<\37\2\u00e6\u00e7\7\3\2\2\u00e7\u00e8\5:\36\2\u00e8\u00f1\7\4\2\2\u00e9"+
		"\u00ea\7\7\2\2\u00ea\u00eb\5<\37\2\u00eb\u00ec\7\3\2\2\u00ec\u00ed\5:"+
		"\36\2\u00ed\u00ee\7\4\2\2\u00ee\u00f0\3\2\2\2\u00ef\u00e9\3\2\2\2\u00f0"+
		"\u00f3\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f4\3\2"+
		"\2\2\u00f3\u00f1\3\2\2\2\u00f4\u00f5\7\7\2\2\u00f5\u00f6\5&\24\2\u00f6"+
		"\u00f9\3\2\2\2\u00f7\u00f9\5@!\2\u00f8\u00e5\3\2\2\2\u00f8\u00f7\3\2\2"+
		"\2\u00f9+\3\2\2\2\u00fa\u011e\5D#\2\u00fb\u00fc\5<\37\2\u00fc\u00fd\7"+
		"\17\2\2\u00fd\u00fe\5<\37\2\u00fe\u00ff\7\20\2\2\u00ff\u0100\7\5\2\2\u0100"+
		"\u0101\5.\30\2\u0101\u0102\5\60\31\2\u0102\u0103\7\6\2\2\u0103\u011e\3"+
		"\2\2\2\u0104\u0105\5<\37\2\u0105\u0106\7\17\2\2\u0106\u0107\5<\37\2\u0107"+
		"\u0108\7\20\2\2\u0108\u0109\5.\30\2\u0109\u011e\3\2\2\2\u010a\u010b\5"+
		"<\37\2\u010b\u010c\7\17\2\2\u010c\u010d\5<\37\2\u010d\u010e\7\21\2\2\u010e"+
		"\u010f\7\5\2\2\u010f\u0110\5> \2\u0110\u0111\7\22\2\2\u0111\u0112\58\35"+
		"\2\u0112\u0113\7\6\2\2\u0113\u011e\3\2\2\2\u0114\u0115\5<\37\2\u0115\u0116"+
		"\7\17\2\2\u0116\u0117\5<\37\2\u0117\u0118\7\21\2\2\u0118\u0119\5> \2\u0119"+
		"\u011a\7\22\2\2\u011a\u011b\58\35\2\u011b\u011e\3\2\2\2\u011c\u011e\7"+
		"\34\2\2\u011d\u00fa\3\2\2\2\u011d\u00fb\3\2\2\2\u011d\u0104\3\2\2\2\u011d"+
		"\u010a\3\2\2\2\u011d\u0114\3\2\2\2\u011d\u011c\3\2\2\2\u011e-\3\2\2\2"+
		"\u011f\u0120\5> \2\u0120\u0121\7\22\2\2\u0121\u0122\58\35\2\u0122/\3\2"+
		"\2\2\u0123\u0124\7\t\2\2\u0124\u0126\5.\30\2\u0125\u0123\3\2\2\2\u0126"+
		"\u0129\3\2\2\2\u0127\u0125\3\2\2\2\u0127\u0128\3\2\2\2\u0128\61\3\2\2"+
		"\2\u0129\u0127\3\2\2\2\u012a\u012b\5<\37\2\u012b\u012c\t\2\2\2\u012c\u012d"+
		"\7\5\2\2\u012d\u012e\5\66\34\2\u012e\u012f\5\64\33\2\u012f\u0130\7\6\2"+
		"\2\u0130\u0138\3\2\2\2\u0131\u0132\5<\37\2\u0132\u0133\t\2\2\2\u0133\u0134"+
		"\5\66\34\2\u0134\u0138\3\2\2\2\u0135\u0138\5F$\2\u0136\u0138\7\23\2\2"+
		"\u0137\u012a\3\2\2\2\u0137\u0131\3\2\2\2\u0137\u0135\3\2\2\2\u0137\u0136"+
		"\3\2\2\2\u0138\63\3\2\2\2\u0139\u013a\7\t\2\2\u013a\u013c\5\66\34\2\u013b"+
		"\u0139\3\2\2\2\u013c\u013f\3\2\2\2\u013d\u013b\3\2\2\2\u013d\u013e\3\2"+
		"\2\2\u013e\65\3\2\2\2\u013f\u013d\3\2\2\2\u0140\u0141\5> \2\u0141\u0142"+
		"\7\22\2\2\u0142\u0143\5:\36\2\u0143\67\3\2\2\2\u0144\u0145\7\b\2\2\u0145"+
		"\u0146\58\35\2\u0146\u0147\7\n\2\2\u0147\u014a\3\2\2\2\u0148\u014a\5,"+
		"\27\2\u0149\u0144\3\2\2\2\u0149\u0148\3\2\2\2\u014a9\3\2\2\2\u014b\u014c"+
		"\7\b\2\2\u014c\u014d\5:\36\2\u014d\u014e\7\n\2\2\u014e\u0151\3\2\2\2\u014f"+
		"\u0151\5\62\32\2\u0150\u014b\3\2\2\2\u0150\u014f\3\2\2\2\u0151;\3\2\2"+
		"\2\u0152\u0153\5H%\2\u0153=\3\2\2\2\u0154\u0155\5H%\2\u0155?\3\2\2\2\u0156"+
		"\u0157\5J&\2\u0157A\3\2\2\2\u0158\u0159\5J&\2\u0159C\3\2\2\2\u015a\u015b"+
		"\5J&\2\u015bE\3\2\2\2\u015c\u015d\5J&\2\u015dG\3\2\2\2\u015e\u015f\7*"+
		"\2\2\u015fI\3\2\2\2\u0160\u0161\7*\2\2\u0161K\3\2\2\2\26NU`ks|\u0088\u00c5"+
		"\u00cc\u00d3\u00e0\u00e3\u00f1\u00f8\u011d\u0127\u0137\u013d\u0149\u0150";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}