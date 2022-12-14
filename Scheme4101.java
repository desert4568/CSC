// Scheme4101 -- The main program of the Scheme interpreter.

import Parse.Scanner;

import Parse.Parser;
import Tokens.Token;
import Tokens.TokenType;
import Tree.BuiltIn;
import Tree.Ident;
import Tree.Node;

public class Scheme4101 {

	// private static Environment env = null;

	// private static final String prompt = "Scheme4101> ";
	// private static final String prompt = "> ";

	// private static final String ini_file = "ini.scm";

	public static void main(String args[]) {

		// Create scanner that reads from standard input
		Scanner scanner = new Scanner(System.in);

		if (args.length > 1 ||
				(args.length == 1 && !args[0].equals("-d"))) {
			System.err.println("Usage: java Scheme4101 [-d]");
			System.exit(1);
		}

		// If command line option -d is provided, debug the scanner
		if (args.length == 1 && args[0].equals("-d")) {

			Token tok = scanner.getNextToken();
			while (tok != null) {
				TokenType tt = tok.getType();

				System.out.print(tt.name());
				if (tt == TokenType.INT)
					System.out.println(", intVal = " + tok.getIntVal());
				else if (tt == TokenType.STRING)
					System.out.println(", strVal = " + tok.getStrVal());
				else if (tt == TokenType.IDENT)
					System.out.println(", name = " + tok.getName());
				else
					System.out.println();

				tok = scanner.getNextToken();
			}
			System.exit(0);
		}

		// Create parser
		Parser parser = new Parser(scanner);
		Node root;
		Ident id;

		var env = new Tree.Environment();
		id = new Ident("b+");
		env.define(id, new BuiltIn(id));
		id = new Ident("b-");
		env.define(id, new BuiltIn(id));
		id = new Ident("b*");
		env.define(id, new BuiltIn(id));
		id = new Ident("b/");
		env.define(id, new BuiltIn(id));
		id = new Ident("b=");
		env.define(id, new BuiltIn(id));
		id = new Ident("b<");
		env.define(id, new BuiltIn(id));
		id = new Ident("b>");
		env.define(id, new BuiltIn(id));
		id = new Ident("car");
		env.define(id, new BuiltIn(id));
		id = new Ident("cdr");
		env.define(id, new BuiltIn(id));
		id = new Ident("cons");
		env.define(id, new BuiltIn(id));
		id = new Ident("set-car!");
		env.define(id, new BuiltIn(id));
		id = new Ident("set-cdr!");
		env.define(id, new BuiltIn(id));
		id = new Ident("symbol?");
		env.define(id, new BuiltIn(id));
		id = new Ident("number?");
		env.define(id, new BuiltIn(id));
		id = new Ident("null?");
		env.define(id, new BuiltIn(id));
		id = new Ident("pair?");
		env.define(id, new BuiltIn(id));
		id = new Ident("eq?");
		env.define(id, new BuiltIn(id));
		id = new Ident("procedure?");
		env.define(id, new BuiltIn(id));
		id = new Ident("display");
		env.define(id, new BuiltIn(id));
		id = new Ident("newline");
		env.define(id, new BuiltIn(id));
		id = new Ident("exit");
		env.define(id, new BuiltIn(id));
		id = new Ident("quit");
		env.define(id, new BuiltIn(id));
		id = new Ident("read");
		env.define(id, new BuiltIn(id));
		id = new Ident("write");
		env.define(id, new BuiltIn(id));
		id = new Ident("eval");
		env.define(id, new BuiltIn(id));
		id = new Ident("apply");
		env.define(id, new BuiltIn(id));
		env = new Tree.Environment(env);
		// TO DO: Create and populate the built-in environment and
		// create the top-level environment

		// env = new Environment();
		// BuiltIn.setGlobalEnv(env);
		//
		// populate the environment with BuiltIns and the code from ini.scm
		//
		// env = new Environment(env);
		// BuiltIn.setGlobalEnv(env);

		// Read-eval-print loop

		// TO DO: print prompt and evaluate the expression
		root = (Node) parser.parseExp();
		while (root != null) {
			root.print(0);
			root = (Node) parser.parseExp();
		}
		System.exit(0);
	}
}
