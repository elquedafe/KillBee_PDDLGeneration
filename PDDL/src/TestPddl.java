import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import fr.uga.pddl4j.parser.*;




public class TestPddl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File f = new File("domain/domainKillbee.pddl");
		if(f.isFile() && f.exists())
			System.out.println("Domain exists at: "+f.getName());
			
		FileWriter fw = null;
		try {
			fw = new FileWriter(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter w = new PrintWriter(fw);
		
		//DOMAIN
		Domain domain = null;
		try {
			domain = new Domain(new Symbol(Symbol.Kind.DOMAIN, "killbee"));
		}catch(Exception e) {
			e.getMessage();
		}
		
		//REQUIREMENTS
		domain.addRequirement(RequireKey.STRIPS);
		domain.addRequirement(RequireKey.TYPING);
		/*
		 * :multi-agent :unfactored-privacy
		 * These both requirements are missing because pddl4j 
		 * do not support multi-agent PDDL extension
		 */
		
		//TYPES
		TypedSymbol type = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "bee location hive plant sector crop"));
		type.addType(new Symbol(Symbol.Kind.TYPE, "object"));
		domain.addType(type);
		
		type = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "tracker fumigator"));
		type.addType(new Symbol(Symbol.Kind.TYPE, "bee"));
		domain.addType(type);
		
		//FUNCTIONS
		
		//PREDICATES
			//PREDICATE 1
		NamedTypedList predicate = new NamedTypedList(new Symbol(Symbol.Kind.PREDICATE, "directly-connected")); //Set predicate name
		
		TypedSymbol variable = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?l1")); //Set 1 variable
		variable.addType(new Symbol(Symbol.Kind.TYPE, "location")); //Add Type for the variable
		predicate.add(variable); // Add variable to the predicate
		
		variable = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?l2")); //Set variable 2
		variable.addType(new Symbol(Symbol.Kind.TYPE, "location")); //Add Type for the variable
		predicate.add(variable); // Add variable to the predicate
		
		domain.addPredicate(predicate); //Add predicate to domain
		
			//PREDICATE 2
		predicate = new NamedTypedList(new Symbol(Symbol.Kind.PREDICATE, "at")); //Set predicate name
		
		variable = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?b")); //Set 1 variable
		variable.addType(new Symbol(Symbol.Kind.TYPE, "bee")); //Add Type for the variable
		predicate.add(variable); // Add variable to the predicate
		
		variable = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?l")); //Set 1 variable
		variable.addType(new Symbol(Symbol.Kind.TYPE, "location")); //Add Type for the variable
		predicate.add(variable); // Add variable to the predicate
		
		domain.addPredicate(predicate); //Add predicate to domain
			
			//PREDICATE 3
		predicate = new NamedTypedList(new Symbol(Symbol.Kind.PREDICATE, "in")); //Set predicate name
		
		variable = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?b")); //Set 1 variable
		variable.addType(new Symbol(Symbol.Kind.TYPE, "bee")); //Add Type for the variable
		predicate.add(variable); // Add variable to the predicate
		
		variable = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?h")); //Set 1 variable
		variable.addType(new Symbol(Symbol.Kind.TYPE, "hive")); //Add Type for the variable
		predicate.add(variable); // Add variable to the predicate
		
		domain.addPredicate(predicate); //Add predicate to domain
		
			//PREDICATE 4
		predicate = new NamedTypedList(new Symbol(Symbol.Kind.PREDICATE, "free-sector")); //Set predicate name
		
		variable = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?s")); //Set 1 variable
		variable.addType(new Symbol(Symbol.Kind.TYPE, "sector")); //Add Type for the variable
		predicate.add(variable); // Add variable to the predicate
		
		domain.addPredicate(predicate); //Add predicate to domain
		
			//PREDICATE 5
		predicate = new NamedTypedList(new Symbol(Symbol.Kind.PREDICATE, "sector-tracked")); //Set predicate name
		
		variable = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?s")); //Set 1 variable
		variable.addType(new Symbol(Symbol.Kind.TYPE, "sector")); //Add Type for the variable
		predicate.add(variable); // Add variable to the predicate
		
		domain.addPredicate(predicate); //Add predicate to domain
		
			//PREDICATE 5
		predicate = new NamedTypedList(new Symbol(Symbol.Kind.PREDICATE, "crop-tracked")); //Set predicate name
		
		variable = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?c")); //Set 1 variable
		variable.addType(new Symbol(Symbol.Kind.TYPE, "crop")); //Add Type for the variable
		predicate.add(variable); // Add variable to the predicate
		
		domain.addPredicate(predicate); //Add predicate to domain
		
		
		//ACTIONS
			//ACTION 1
		List<TypedSymbol> parameters = new ArrayList<TypedSymbol>();
				//Parameters
		TypedSymbol parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "tracker"));
		parameters.add(parameter);
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "hive"));
		parameters.add(parameter);
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?l"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "location"));
		parameters.add(parameter);
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?s"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "sector"));
		parameters.add(parameter);
				//Preconditions
		Exp preconditions = new Exp(Connective.AND);
					//precond1
		Exp precondition = new Exp(Connective.ATOM);
		List<Symbol> atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "in"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		
		precondition.setAtom(atoms);
		preconditions.addChild(precondition);
					//precond2
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "directly-connected"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?l"));
		
		precondition.setAtom(atoms);
		preconditions.addChild(precondition);
					//precond3
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "free-sector"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?s"));
		
		precondition.setAtom(atoms);
		preconditions.addChild(precondition);
				//Effects
		Exp effects = new Exp(Connective.AND);
					//effect1
		Exp effectNot = new Exp(Connective.NOT);
		Exp effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "in"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		effect.setAtom(atoms);

		effectNot.addChild(effect);
		effects.addChild(effectNot);
					//effect2
		effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "at"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?l"));
		effect.setAtom(atoms);
		
		effects.addChild(effect);
		
		
		Op op = new Op(new Symbol(Symbol.Kind.ACTION, "fly-to-first-plant"), parameters, preconditions, effects);
		domain.addOperator(op);
		
		//WRITE FILE
		w.print(domain.toString());
		w.close();
		

		System.out.println(domain.toString());
	}

}
