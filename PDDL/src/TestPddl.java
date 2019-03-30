import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



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
		domain.addRequirement(RequireKey.MULTI_AGENT);
		
		//TYPES
		TypedSymbol type = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "bee location hive plant sector"));
		//type.addType(new Symbol(Symbol.Kind.TYPE, "object"));
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
		
			//PREDICATE 7
		predicate = new NamedTypedList(new Symbol(Symbol.Kind.PREDICATE, "bee-with-sector")); //Set predicate name
		
		variable = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?b")); //Set 1 variable
		variable.addType(new Symbol(Symbol.Kind.TYPE, "tracker")); //Add Type for the variable
		predicate.add(variable); // Add variable to the predicate
		
		variable = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?s")); //Set 1 variable
		variable.addType(new Symbol(Symbol.Kind.TYPE, "sector")); //Add Type for the variable
		predicate.add(variable); // Add variable to the predicate
		
		domain.addPredicate(predicate); //Add predicate to domain
			
			//PREDICATE 8
		predicate = new NamedTypedList(new Symbol(Symbol.Kind.PREDICATE, "plant-analyzed")); //Set predicate name
		
		variable = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?p")); //Set 1 variable
		variable.addType(new Symbol(Symbol.Kind.TYPE, "plant")); //Add Type for the variable
		predicate.add(variable); // Add variable to the predicate
		
		domain.addPredicate(predicate); //Add predicate to domain
			
			//PREDICATE 9
		predicate = new NamedTypedList(new Symbol(Symbol.Kind.PREDICATE, "tracker-ready-to-move")); //Set predicate name
		
		variable = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?b")); //Set 1 variable
		variable.addType(new Symbol(Symbol.Kind.TYPE, "tracker")); //Add Type for the variable
		predicate.add(variable); // Add variable to the predicate
		
		domain.addPredicate(predicate); //Add predicate to domain
		
			//PREDICATE 10
		predicate = new NamedTypedList(new Symbol(Symbol.Kind.PREDICATE, "last-plant")); //Set predicate name
		
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
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?hivelocation"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "location"));
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
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?hivelocation"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?l"));
		
		precondition.setAtom(atoms);
		preconditions.addChild(precondition);
					//precond3
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "bee-with-sector"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
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
					//effect3
		effectNot = new Exp(Connective.NOT);
		effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "tracker-ready-to-move"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		effect.setAtom(atoms);

		effectNot.addChild(effect);
		effects.addChild(effectNot);
		
			//AGENT
		TypedSymbol agent = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "b"));
		agent.addType(new Symbol(Symbol.Kind.TYPE, "tracker"));
		
		
		Op op = new Op(new Symbol(Symbol.Kind.ACTION, "fly-to-first-plant"), agent, parameters, preconditions, effects);
		domain.addOperator(op);
		
			//ACTION 2
		parameters = new ArrayList<TypedSymbol>();
		//Parameters
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?b"));
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
		preconditions = new Exp(Connective.AND);
					//precond1
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "in"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		
		precondition.setAtom(atoms);
		preconditions.addChild(precondition);
					//precond2
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "free-sector"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?s"));
		
		precondition.setAtom(atoms);
		preconditions.addChild(precondition);
					//precond3
		effectNot = new Exp(Connective.NOT);
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "bee-with-sector"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?s"));
		precondition.setAtom(atoms);
		
		effectNot.addChild(precondition);
		preconditions.addChild(effectNot);
					//precond4
		effectNot = new Exp(Connective.NOT);
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "sector-tracked"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?s"));
		precondition.setAtom(atoms);
		
		effectNot.addChild(precondition);
		preconditions.addChild(effectNot);
				//Effects
		effects = new Exp(Connective.AND);
					//effect1
		effectNot = new Exp(Connective.NOT);
		effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "free-sector"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?s"));
		effect.setAtom(atoms);
		
		effectNot.addChild(effect);
		effects.addChild(effectNot);
					//effect2
		effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "bee-with-sector"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?s"));
		effect.setAtom(atoms);
		
		effects.addChild(effect);
		
		//AGENT
		agent = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "h"));
		agent.addType(new Symbol(Symbol.Kind.TYPE, "hive"));
		
		op = new Op(new Symbol(Symbol.Kind.ACTION, "assign-sector"), agent, parameters, preconditions, effects);
		domain.addOperator(op);
		
			//ACTION3
		parameters = new ArrayList<TypedSymbol>();
		//Parameters
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "tracker"));
		parameters.add(parameter);
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?l"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "location"));
		parameters.add(parameter);
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?s"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "sector"));
		parameters.add(parameter);
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "plant"));
		parameters.add(parameter);
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "hive"));
		parameters.add(parameter);
				//Preconditions
		preconditions = new Exp(Connective.AND);
					//precond1
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "at"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?l"));
		
		precondition.setAtom(atoms);
		preconditions.addChild(precondition);
					//precond2
		effectNot = new Exp(Connective.NOT);
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "plant-analyzed"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		precondition.setAtom(atoms);
		
		effectNot.addChild(precondition);
		preconditions.addChild(effectNot);
					//precond3
		effectNot = new Exp(Connective.NOT);
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "in"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		precondition.setAtom(atoms);
		
		effectNot.addChild(precondition);
		preconditions.addChild(effectNot);
				//Effects
		effects = new Exp(Connective.AND);
					//effect1
		effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "tracker-ready-to-move"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		effect.setAtom(atoms);
		
		effects.addChild(effect);
					//effect2
		effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "plant-analyzed"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		effect.setAtom(atoms);
		
		effects.addChild(effect);
					//effect3
		effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "last-plant"));
		effect.setAtom(atoms);
		
		effects.addChild(effect);
		
		//AGENT
		agent = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "b"));
		agent.addType(new Symbol(Symbol.Kind.TYPE, "tracker"));
		
		op = new Op(new Symbol(Symbol.Kind.ACTION, "analyze-plant"), agent, parameters, preconditions, effects);
		domain.addOperator(op);		
		
			//ACTION 4
		parameters = new ArrayList<TypedSymbol>();
		//Parameters
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "tracker"));
		parameters.add(parameter);
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?from"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "location"));
		parameters.add(parameter);
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?target"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "location"));
		parameters.add(parameter);
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?s"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "sector"));
		parameters.add(parameter);
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "plant"));
		parameters.add(parameter);
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "hive"));
		parameters.add(parameter);
				//Preconditions
		preconditions = new Exp(Connective.AND);
					//precond1
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "directly-connected"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?from"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?target"));
		
		precondition.setAtom(atoms);
		preconditions.addChild(precondition);
					//precond2
		effectNot = new Exp(Connective.NOT);
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "sector-tracked"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?s"));
		precondition.setAtom(atoms);
		
		effectNot.addChild(precondition);
		preconditions.addChild(effectNot);
					//precond3
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "tracker-ready-to-move"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		
		precondition.setAtom(atoms);
		preconditions.addChild(precondition);
					//precond4
		effectNot = new Exp(Connective.NOT);
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "plant-analyzed"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		precondition.setAtom(atoms);
		
		effectNot.addChild(precondition);
		preconditions.addChild(effectNot);
					//precond5
		effectNot = new Exp(Connective.NOT);
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "in"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		precondition.setAtom(atoms);
		
		effectNot.addChild(precondition);
		preconditions.addChild(effectNot);
				//Effects
		effects = new Exp(Connective.AND);
					//effect1
		effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "at"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?target"));
		effect.setAtom(atoms);
		
		effects.addChild(effect);
					//effect2
		effectNot = new Exp(Connective.NOT);
		effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "tracker-ready-to-move"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		effect.setAtom(atoms);
		
		effectNot.addChild(effect);
		effects.addChild(effectNot);
					//effect3
		effectNot = new Exp(Connective.NOT);
		effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "at"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?from"));
		effect.setAtom(atoms);
		
		effectNot.addChild(effect);
		effects.addChild(effectNot);
		
		//AGENT
		agent = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "b"));
		agent.addType(new Symbol(Symbol.Kind.TYPE, "tracker"));
		
		op = new Op(new Symbol(Symbol.Kind.ACTION, "go-to-next-plant"), agent, parameters, preconditions, effects);
		domain.addOperator(op);

				//ACTION5
		parameters = new ArrayList<TypedSymbol>();
		//Parameters
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "tracker"));
		parameters.add(parameter);
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?l"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "location"));
		parameters.add(parameter);
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?s"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "sector"));
		parameters.add(parameter);
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "plant"));
		parameters.add(parameter);
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "hive"));
		parameters.add(parameter);
				//Preconditions
		preconditions = new Exp(Connective.AND);
					//precond1
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "at"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?l"));
		
		precondition.setAtom(atoms);
		preconditions.addChild(precondition);
					//precond2
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "last-plant"));
		
		precondition.setAtom(atoms);
		preconditions.addChild(precondition);
					//precond3
		effectNot = new Exp(Connective.NOT);
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "plant-analyzed"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		precondition.setAtom(atoms);
		
		effectNot.addChild(precondition);
		preconditions.addChild(effectNot);
					//precond 4
		effectNot = new Exp(Connective.NOT);
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "tracker-ready-to-move"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		precondition.setAtom(atoms);
		
		effectNot.addChild(precondition);
		preconditions.addChild(effectNot);
					//precond 5
		effectNot = new Exp(Connective.NOT);
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "in"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		precondition.setAtom(atoms);
		
		effectNot.addChild(precondition);
		preconditions.addChild(effectNot);
				//Effects
		effects = new Exp(Connective.AND);
					//effect1
		effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "tracker-ready-to-move"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		effect.setAtom(atoms);
		
		effects.addChild(effect);
					//effect2
		effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "plant-analyzed"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		effect.setAtom(atoms);
		
		effects.addChild(effect);
					//effect3
		effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "sector-tracked"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?s"));
		effect.setAtom(atoms);
		
		effects.addChild(effect);
		
		//AGENT
		agent = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "b"));
		agent.addType(new Symbol(Symbol.Kind.TYPE, "tracker"));		
		
		op = new Op(new Symbol(Symbol.Kind.ACTION, "analyze-last-plant"), agent, parameters, preconditions, effects);
		domain.addOperator(op);
		
			//ACTION 6
		parameters = new ArrayList<TypedSymbol>();
		//Parameters
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "tracker"));
		parameters.add(parameter);
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?from"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "location"));
		parameters.add(parameter);
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?target"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "location"));
		parameters.add(parameter);
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?s"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "sector"));
		parameters.add(parameter);
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "hive"));
		parameters.add(parameter);
				//Preconditions
		preconditions = new Exp(Connective.AND);
					//precond1
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "tracker-ready-to-move"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		
		precondition.setAtom(atoms);
		preconditions.addChild(precondition);
					//precond2
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "sector-tracked"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?s"));
		precondition.setAtom(atoms);
		
		preconditions.addChild(precondition);
					//precond3
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "directly-connected"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?from"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?target"));
		precondition.setAtom(atoms);
		
		preconditions.addChild(precondition);
					//precond4
		effectNot = new Exp(Connective.NOT);
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "in"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		precondition.setAtom(atoms);
		
		effectNot.addChild(precondition);
		preconditions.addChild(effectNot);
					//precond5
		/*precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "crop-tracked"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?c"));
		
		precondition.setAtom(atoms);
		preconditions.addChild(precondition);*/
				//Effects
		effects = new Exp(Connective.AND);
					//effect1
		effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "at"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?target"));
		effect.setAtom(atoms);
		
		effects.addChild(effect);
					//effect2
		effectNot = new Exp(Connective.NOT);
		effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "tracker-ready-to-move"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		effect.setAtom(atoms);
		
		effectNot.addChild(effect);
		effects.addChild(effectNot);
					//effect3
		effectNot = new Exp(Connective.NOT);
		effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "at"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?from"));
		effect.setAtom(atoms);
		
		effectNot.addChild(effect);
		effects.addChild(effectNot);
					//effect4
		effectNot = new Exp(Connective.NOT);
		effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "bee-with-sector"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?s"));
		effect.setAtom(atoms);
		
		effectNot.addChild(effect);
		effects.addChild(effectNot);
					//effect5
		effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "in"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		effect.setAtom(atoms);
		
		effects.addChild(effect);
		
		//AGENT
		agent = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "b"));
		agent.addType(new Symbol(Symbol.Kind.TYPE, "tracker"));
		
		op = new Op(new Symbol(Symbol.Kind.ACTION, "back-home"), agent, parameters, preconditions, effects);
		domain.addOperator(op);
		
		
		
		/*
		
		//Test buildAction
		Map<String,String> params = new HashMap<String,String>();
		//PARAMS
		params.put("b", "tracker");
		params.put("l1", "location");
		params.put("l2", "location");
		
		//PRECONDITIONS
		Map<HashMap<String, ArrayList<String>>, String> pres = new HashMap<HashMap<String,ArrayList<String>>,String>();
		HashMap<String, ArrayList<String>> precods = new HashMap<String, ArrayList<String>>();
		//p1
		String bool = "false";
		ArrayList<String> variables = new ArrayList<String>();
		variables.add("b");
		variables.add("l1");
		precods.put("precondition1", variables);
		
		//p2
		bool = "true";
		variables = new ArrayList<String>();
		variables.add("l1");
		variables.add("l2");
		precods.put("precondition2", variables);
		
		//EFFECTS
		Map<HashMap<String, ArrayList<String>>, String> effs = new HashMap<HashMap<String,ArrayList<String>>,String>();
		HashMap<String, ArrayList<String>> precods = new HashMap<String, ArrayList<String>>();
		//p1
		String bool = "false";
		ArrayList<String> variables = new ArrayList<String>();
		variables.add("b");
		variables.add("l1");
		precods.put("precondition1", variables);
		
		//p2
		bool = "true";
		variables = new ArrayList<String>();
		variables.add("l1");
		variables.add("l2");
		precods.put("precondition2", variables);
		
		
		Map<HashMap<String, ArrayList<String>>, String>  effs = new HashMap<HashMap<String,ArrayList<String>>,String>();
		
		buildAction("accion1", params, pres, effs, domain);
		*/
		
		//WRITE FILE
		String domainString = domain.toString();
		domainString = domainString.toLowerCase();
		w.print(domainString);
		w.close();
		

		System.out.println(domainString);
	}
	
	/*
	private static void buildAction(String actionName, Map<String, String> params, List<HashMap<HashMap<String,ArrayList<String>>,String>> pre, List<Map<HashMap<String,ArrayList<String>>,String>> eff, Domain domain) {
		//Parameters
		List<TypedSymbol> parameters = new ArrayList<TypedSymbol>();
		TypedSymbol parameter = null;
		for(Map.Entry<String, String> entry : params.entrySet()){
			parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?"+entry.getKey()));
			parameter.addType(new Symbol(Symbol.Kind.TYPE, entry.getValue()));
			parameters.add(parameter);
		}
		
		//PRECOND
		Exp preconditions = null;
		Exp precondition = null;
		Exp precondNot = null;
		List<Symbol> atomsPre = new ArrayList<Symbol>();
		
		//EFFECTS
		
		Exp effects = null;
		Exp effect = null;
		Exp effectNot = null;
		List<Symbol> atomsEff = new ArrayList<Symbol>();
		if(pre.size()>1) {
			effects = new Exp(Connective.AND);
			effect = new Exp(Connective.ATOM);
		}
		else {
			effects = new Exp(Connective.ATOM);
		}
		
		for(Map<HashMap<String,ArrayList<String>>,String> e : eff) {
			for(Map.Entry<HashMap<String,ArrayList<String>>,String> entry : e.entrySet()){
				if(entry.getValue().equals("false")) {
					effectNot = new Exp(Connective.NOT);
				}
				//Fill atom list
				for(Map.Entry<String,ArrayList<String>> effectEntry : entry.getKey().entrySet() ) {
					atomsEff.add(new Symbol(Symbol.Kind.PREDICATE, effectEntry.getKey()));
					for(String var : effectEntry.getValue()) {
						atomsEff.add(new Symbol(Symbol.Kind.VARIABLE, "?"+var));
					}
				}
				
				
				if(pre.size()>1) {
					effect.setAtom(atomsEff);
					if(entry.getValue().equals("false")) {
						effectNot.addChild(effect);;
						effects.addChild(effectNot);
						
					}
					else {
						effects.addChild(effect);
					}
				}
				else
					effects.setAtom(atomsEff);
				
			}
		}
		Op op = new Op(new Symbol(Symbol.Kind.ACTION, actionName), parameters, preconditions, effects);
		domain.addOperator(op);
		
	}*/
	
	

}
