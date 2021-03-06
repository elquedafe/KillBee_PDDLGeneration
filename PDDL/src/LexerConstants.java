/* Generated By:JavaCC: Do not edit this line. LexerConstants.java */



/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface LexerConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int SINGLE_LINE_COMMENT = 9;
  /** RegularExpression Id. */
  int FORMAL_COMMENT = 10;
  /** RegularExpression Id. */
  int MULTI_LINE_COMMENT = 11;
  /** RegularExpression Id. */
  int LEFT_BRACKET = 13;
  /** RegularExpression Id. */
  int RIGHT_BRACKET = 14;
  /** RegularExpression Id. */
  int ALIAS = 15;
  /** RegularExpression Id. */
  int DEFINE = 16;
  /** RegularExpression Id. */
  int DOMAIN = 17;
  /** RegularExpression Id. */
  int REQUIREMENTS = 18;
  /** RegularExpression Id. */
  int TYPES = 19;
  /** RegularExpression Id. */
  int EITHER = 20;
  /** RegularExpression Id. */
  int CONSTANTS = 21;
  /** RegularExpression Id. */
  int PREDICATES = 22;
  /** RegularExpression Id. */
  int FUNCTIONS = 23;
  /** RegularExpression Id. */
  int ACTION = 24;
  /** RegularExpression Id. */
  int PARAMETERS = 25;
  /** RegularExpression Id. */
  int PRECONDITION = 26;
  /** RegularExpression Id. */
  int EFFECT = 27;
  /** RegularExpression Id. */
  int PREFERENCE = 28;
  /** RegularExpression Id. */
  int WHEN = 29;
  /** RegularExpression Id. */
  int DURATIVE_ACTION = 30;
  /** RegularExpression Id. */
  int DURATION = 31;
  /** RegularExpression Id. */
  int CONDITION = 32;
  /** RegularExpression Id. */
  int DERIVED = 33;
  /** RegularExpression Id. */
  int PROBLEM = 34;
  /** RegularExpression Id. */
  int P_DOMAIN = 35;
  /** RegularExpression Id. */
  int OBJECTS = 36;
  /** RegularExpression Id. */
  int INIT = 37;
  /** RegularExpression Id. */
  int GOAL = 38;
  /** RegularExpression Id. */
  int METRIC = 39;
  /** RegularExpression Id. */
  int METHOD = 40;
  /** RegularExpression Id. */
  int STRIPS = 41;
  /** RegularExpression Id. */
  int TYPING = 42;
  /** RegularExpression Id. */
  int NEGATIVE_PRECONDITIONS = 43;
  /** RegularExpression Id. */
  int DISJUNCTIVE_PRECONDITIONS = 44;
  /** RegularExpression Id. */
  int EQUALITY = 45;
  /** RegularExpression Id. */
  int EXISTENTIAL_PRECONDITIONS = 46;
  /** RegularExpression Id. */
  int UNIVERSAL_PRECONDITIONS = 47;
  /** RegularExpression Id. */
  int QUANTIFIED_PRECONDITIONS = 48;
  /** RegularExpression Id. */
  int CONDITIONAL_EFFECTS = 49;
  /** RegularExpression Id. */
  int ACTION_COSTS = 50;
  /** RegularExpression Id. */
  int FLUENTS = 51;
  /** RegularExpression Id. */
  int NUMERIC_FLUENTS = 52;
  /** RegularExpression Id. */
  int OBJECT_FLUENTS = 53;
  /** RegularExpression Id. */
  int GOAL_UTILITIES = 54;
  /** RegularExpression Id. */
  int ADL = 55;
  /** RegularExpression Id. */
  int DURATIVE_ACTIONS = 56;
  /** RegularExpression Id. */
  int DERIVED_PREDICATES = 57;
  /** RegularExpression Id. */
  int TIMED_INITIAL_LITERALS = 58;
  /** RegularExpression Id. */
  int PREFERENCES = 59;
  /** RegularExpression Id. */
  int CONSTRAINTS = 60;
  /** RegularExpression Id. */
  int CONTINUS_EFFECTS = 61;
  /** RegularExpression Id. */
  int DURATION_INEQUALITIES = 62;
  /** RegularExpression Id. */
  int HTN = 63;
  /** RegularExpression Id. */
  int EXPANSION = 64;
  /** RegularExpression Id. */
  int GREATER = 65;
  /** RegularExpression Id. */
  int LESS = 66;
  /** RegularExpression Id. */
  int GEQUAL = 67;
  /** RegularExpression Id. */
  int LEQUAL = 68;
  /** RegularExpression Id. */
  int EQUAL = 69;
  /** RegularExpression Id. */
  int MUL = 70;
  /** RegularExpression Id. */
  int DIV = 71;
  /** RegularExpression Id. */
  int ADD = 72;
  /** RegularExpression Id. */
  int SUB = 73;
  /** RegularExpression Id. */
  int ASSIGN = 74;
  /** RegularExpression Id. */
  int SCALE_UP = 75;
  /** RegularExpression Id. */
  int SCALE_DOWN = 76;
  /** RegularExpression Id. */
  int INCREASE = 77;
  /** RegularExpression Id. */
  int DECREASE = 78;
  /** RegularExpression Id. */
  int OR = 79;
  /** RegularExpression Id. */
  int AND = 80;
  /** RegularExpression Id. */
  int IMPLY = 81;
  /** RegularExpression Id. */
  int NOT = 82;
  /** RegularExpression Id. */
  int FORALL = 83;
  /** RegularExpression Id. */
  int EXISTS = 84;
  /** RegularExpression Id. */
  int ALWAYS = 85;
  /** RegularExpression Id. */
  int SOMETIME = 86;
  /** RegularExpression Id. */
  int WITHIN = 87;
  /** RegularExpression Id. */
  int AT_MOST_ONCE = 88;
  /** RegularExpression Id. */
  int SOMETIME_AFTER = 89;
  /** RegularExpression Id. */
  int SOMETIME_BEFORE = 90;
  /** RegularExpression Id. */
  int ALWAYS_WITHIN = 91;
  /** RegularExpression Id. */
  int HOLD_DURING = 92;
  /** RegularExpression Id. */
  int HOLD_AFTER = 93;
  /** RegularExpression Id. */
  int HOLD_BEFORE = 94;
  /** RegularExpression Id. */
  int HOLD_BETWEEN = 95;
  /** RegularExpression Id. */
  int AT = 96;
  /** RegularExpression Id. */
  int START = 97;
  /** RegularExpression Id. */
  int END = 98;
  /** RegularExpression Id. */
  int OVER = 99;
  /** RegularExpression Id. */
  int ALL = 100;
  /** RegularExpression Id. */
  int TOTAL_TIME = 101;
  /** RegularExpression Id. */
  int TOTAL_COST = 102;
  /** RegularExpression Id. */
  int MAXIMIZE = 103;
  /** RegularExpression Id. */
  int MINIMIZE = 104;
  /** RegularExpression Id. */
  int IS_VIOLATED = 105;
  /** RegularExpression Id. */
  int BEFORE = 106;
  /** RegularExpression Id. */
  int AFTER = 107;
  /** RegularExpression Id. */
  int BETWEEN = 108;
  /** RegularExpression Id. */
  int VAR_DURATION = 109;
  /** RegularExpression Id. */
  int CONTINUOUS_VARIABLE = 110;
  /** RegularExpression Id. */
  int NUMBER_TYPE = 111;
  /** RegularExpression Id. */
  int OBJECT_TYPE = 112;
  /** RegularExpression Id. */
  int VARIABLE_SYMBOL = 113;
  /** RegularExpression Id. */
  int SYMBOL = 114;
  /** RegularExpression Id. */
  int NUMBER = 115;
  /** RegularExpression Id. */
  int DIGIT = 116;
  /** RegularExpression Id. */
  int LETTER = 117;
  /** RegularExpression Id. */
  int SEPARATOR = 118;

  /** Lexical state. */
  int DEFAULT = 0;
  /** Lexical state. */
  int IN_SINGLE_LINE_COMMENT = 1;
  /** Lexical state. */
  int IN_FORMAL_COMMENT = 2;
  /** Lexical state. */
  int IN_MULTI_LINE_COMMENT = 3;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\t\"",
    "\"\\n\"",
    "\"\\r\"",
    "\"//\"",
    "\";\"",
    "<token of kind 7>",
    "\"/*\"",
    "<SINGLE_LINE_COMMENT>",
    "\"*/\"",
    "\"*/\"",
    "<token of kind 12>",
    "\"(\"",
    "\")\"",
    "\"@\"",
    "\"define\"",
    "\"domain\"",
    "\":requirements\"",
    "\":types\"",
    "\"either\"",
    "\":constants\"",
    "\":predicates\"",
    "\":functions\"",
    "\":action\"",
    "\":parameters\"",
    "\":precondition\"",
    "\":effect\"",
    "\"preference\"",
    "\"when\"",
    "\":durative-action\"",
    "\":duration\"",
    "\":condition\"",
    "\":derived\"",
    "\"problem\"",
    "\":domain\"",
    "\":objects\"",
    "\":init\"",
    "\":goal\"",
    "\":metric\"",
    "\":method\"",
    "\":strips\"",
    "\":typing\"",
    "\":negative-preconditions\"",
    "\":disjunctive-preconditions\"",
    "\":equality\"",
    "\":existential-preconditions\"",
    "\":universal-preconditions\"",
    "\":quantified-preconditions\"",
    "\":conditional-effects\"",
    "\":action-costs\"",
    "\":fluents\"",
    "\":numeric-fluents\"",
    "\":object-fluents\"",
    "\":goal-utilities\"",
    "\":adl\"",
    "\":durative-actions\"",
    "\":derived-predicates\"",
    "\":timed-initial-literals\"",
    "\":preferences\"",
    "\":constraints\"",
    "\":continus-effects\"",
    "\":duration-inequalities\"",
    "\":htn\"",
    "\":expansion\"",
    "\">\"",
    "\"<\"",
    "\">=\"",
    "\"<=\"",
    "\"=\"",
    "\"*\"",
    "\"/\"",
    "\"+\"",
    "\"-\"",
    "\"assign\"",
    "\"scale-up\"",
    "\"scale-down\"",
    "\"increase\"",
    "\"decrease\"",
    "\"or\"",
    "\"and\"",
    "\"imply\"",
    "\"not\"",
    "\"forall\"",
    "\"exists\"",
    "\"always\"",
    "\"sometime\"",
    "\"within\"",
    "\"at-most-once\"",
    "\"sometime-after\"",
    "\"sometime-before\"",
    "\"always-within\"",
    "\"hold-during\"",
    "\"hold-after\"",
    "\"hold-before\"",
    "\"hold-between\"",
    "\"at\"",
    "\"start\"",
    "\"end\"",
    "\"over\"",
    "\"all\"",
    "\"total-time\"",
    "\"total-cost\"",
    "\"maximize\"",
    "\"minimize\"",
    "\"is-violated\"",
    "\"before\"",
    "\"after\"",
    "\"between\"",
    "\"?duration\"",
    "\"#t\"",
    "\"number\"",
    "\"object\"",
    "<VARIABLE_SYMBOL>",
    "<SYMBOL>",
    "<NUMBER>",
    "<DIGIT>",
    "<LETTER>",
    "<SEPARATOR>",
  };

}
