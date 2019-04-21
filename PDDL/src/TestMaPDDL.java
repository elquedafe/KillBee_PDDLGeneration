import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;



public class TestMaPDDL {

	public static void main(String[] args) {
		File fDom = new File("domain/domain.pddl");
		File fProb = new File("problem/problem.pddl");
		Scanner sc = null;
		
		//Check file domain
		if(fDom.isFile() && fDom.exists())
			System.out.println("Domain exists at: "+fDom.getName());
			
		FileWriter fwDom = null;
		try {
			fwDom = new FileWriter(fDom);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter wDom = new PrintWriter(fwDom);
		
		//Check file problem
		if(fProb.isFile() && fProb.exists())
			System.out.println("Problem exists at: "+fProb.getName());
			
		FileWriter fwProb = null;
		try {
			fwProb = new FileWriter(fProb);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter wProb = new PrintWriter(fwProb);
		
		//************DOMAIN************//
		String domainString = writeDomain(wDom);
		wDom.print(domainString);
		wDom.close();
		try {
			fwDom.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//System.out.println(domainString);
		
		
		//************PROBELM************//
		sc = new Scanner(System.in);
		
		System.out.println("Tracker bees number: ");
		int nTrackers = sc.nextInt();
		//int nTrackers = 3;
		System.out.println("Fumigator bees number: ");
		int nFumigators = sc.nextInt();
		//int nFumigators = 3;
		System.out.println("Plants number: ");
		int nPlants = sc.nextInt();
		//int nPlants = 7;
		
		String name = nTrackers+"trackers-"+nPlants+"plants";
		String problemString = writeProblem(wDom, name, nTrackers, nFumigators, nPlants);
		wProb.print(problemString);
		wProb.close();
		//System.out.println(problemString);
		
	}
	
	private static String writeProblem(PrintWriter w, String name, int nTrackers, int nFumigators, int nPlants) {
		Problem problem = null;
		TypedSymbol type = null;
		String trackers = "";
		String fumigators = "";
		String plants = "";
		Exp goals = null;
		Exp goal = null;
		
		problem = new Problem(new Symbol(Symbol.Kind.PROBLEM, name));
		
		//DOMAIN
		problem.setDomain(new Symbol(Symbol.Kind.DOMAIN, "killbee"));
		
		//OBJECTS
			//trackers
		for(int i = 0; i < nTrackers; i++) {
			trackers += "tr"+(i+1)+" ";
		}
		type = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, trackers));
		type.addType(new Symbol(Symbol.Kind.TYPE, "tracker"));
		problem.addObject(type);
			//fumigators
		for(int i = 0; i < nFumigators; i++) {
			fumigators += "fum"+(i+1)+" ";
		}
		type = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, fumigators));
		type.addType(new Symbol(Symbol.Kind.TYPE, "fumigator"));
		problem.addObject(type);
			//hive
		type = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "h"));
		type.addType(new Symbol(Symbol.Kind.TYPE, "hive"));
		problem.addObject(type);
			//plants
		for(int i = 0; i < nPlants; i++) {
			plants += "p"+(i+1)+" ";
		}
		type = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, plants));
		type.addType(new Symbol(Symbol.Kind.TYPE, "plant"));
		problem.addObject(type);
		
		//INIT
			//sector init
		ArrayList<String> listaLinks = (ArrayList<String>) sectorAssignment(nTrackers, nPlants);
		Exp init = null;
		for(String s : listaLinks) {
			init = new Exp(Connective.ATOM);
			String[] splitted = s.split(" ");
			List<Symbol> atoms = new ArrayList<Symbol>();
			atoms.add(new Symbol(Symbol.Kind.PREDICATE, splitted[0]));
			atoms.add(new Symbol(Symbol.Kind.VARIABLE, splitted[1]));
			atoms.add(new Symbol(Symbol.Kind.VARIABLE, splitted[2]));
			init.setAtom(atoms);
			init.addChild(init);
			problem.addInitialFact(init);
		}
		
			//tracker init
		for(int i= 0; i < nTrackers; i++) {
			init = new Exp(Connective.ATOM);
			List<Symbol> atoms = new ArrayList<Symbol>();
			atoms.add(new Symbol(Symbol.Kind.PREDICATE, "at"));
			atoms.add(new Symbol(Symbol.Kind.VARIABLE, "tr"+(i+1)));
			atoms.add(new Symbol(Symbol.Kind.VARIABLE, "h"));
			init.setAtom(atoms);
			init.addChild(init);
			problem.addInitialFact(init);
			
			init = new Exp(Connective.ATOM);
			atoms = new ArrayList<Symbol>();
			atoms.add(new Symbol(Symbol.Kind.PREDICATE, "tracker-ready-to-move"));
			atoms.add(new Symbol(Symbol.Kind.VARIABLE, "tr"+(i+1)));
			init.setAtom(atoms);
			init.addChild(init);
			problem.addInitialFact(init);
		}
			//fumigator init
		for(int i= 0; i < nFumigators; i++) {
			init = new Exp(Connective.ATOM);
			List<Symbol> atoms = new ArrayList<Symbol>();
			atoms.add(new Symbol(Symbol.Kind.PREDICATE, "at"));
			atoms.add(new Symbol(Symbol.Kind.VARIABLE, "fum"+(i+1)));
			atoms.add(new Symbol(Symbol.Kind.VARIABLE, "h"));
			init.setAtom(atoms);
			init.addChild(init);
			problem.addInitialFact(init);
			
			init = new Exp(Connective.ATOM);
			atoms = new ArrayList<Symbol>();
			atoms.add(new Symbol(Symbol.Kind.PREDICATE, "pesticide-tank-empty"));
			atoms.add(new Symbol(Symbol.Kind.VARIABLE, "fum"+(i+1)));
			init.setAtom(atoms);
			init.addChild(init);
			problem.addInitialFact(init);
		}
		for(int i = 0; i < nPlants; i++) {
			init = new Exp(Connective.ATOM);
			List<Symbol> atoms = new ArrayList<Symbol>();
			atoms.add(new Symbol(Symbol.Kind.PREDICATE, "healthy-plant"));
			atoms.add(new Symbol(Symbol.Kind.VARIABLE, "p"+(i+1)));
			init.setAtom(atoms);
			init.addChild(init);
			problem.addInitialFact(init);
		}
			//Sector assignment
		sectorAssignment(nTrackers, nPlants);
		
		//GOAL
		goals = new Exp(Connective.AND);
			//trackers
		for(int i = 0; i < nTrackers; i++) {
			goal = new Exp(Connective.ATOM);
			List<Symbol> atoms = new ArrayList<Symbol>();
			atoms.add(new Symbol(Symbol.Kind.PREDICATE, "at"));
			atoms.add(new Symbol(Symbol.Kind.VARIABLE, "tr"+(i+1)));
			atoms.add(new Symbol(Symbol.Kind.VARIABLE, "h"));
			goal.setAtom(atoms);
			goals.addChild(goal);
		}
			//fumigators
		for(int i = 0; i < nFumigators; i++) {
			goal = new Exp(Connective.ATOM);
			List<Symbol> atoms = new ArrayList<Symbol>();
			atoms.add(new Symbol(Symbol.Kind.PREDICATE, "at"));
			atoms.add(new Symbol(Symbol.Kind.VARIABLE, "fum"+(i+1)));
			atoms.add(new Symbol(Symbol.Kind.VARIABLE, "h"));
			goal.setAtom(atoms);
			goals.addChild(goal);
		}
			//analyzed-plants
		for(int i = 0; i < nPlants; i++) {
			goal = new Exp(Connective.ATOM);
			List<Symbol> atoms = new ArrayList<Symbol>();
			atoms.add(new Symbol(Symbol.Kind.PREDICATE, "plant-analyzed"));
			atoms.add(new Symbol(Symbol.Kind.VARIABLE, "p"+(i+1)));
			goal.setAtom(atoms);
			goals.addChild(goal);
			
			goal = new Exp(Connective.ATOM);
			atoms = new ArrayList<Symbol>();
			atoms.add(new Symbol(Symbol.Kind.PREDICATE, "healthy-plant"));
			atoms.add(new Symbol(Symbol.Kind.VARIABLE, "p"+(i+1)));
			goal.setAtom(atoms);
			goals.addChild(goal);
		}
		
		problem.setGoal(goals);

		
		String problemString = problem.toString().toLowerCase();
		return problemString;
	}
	
	private static List<String> sectorAssignment(int nTrackers, int nPlants) {
		List<String> ret = new ArrayList<String>();
		int nSectors = nTrackers;
		int plantsPerSector = (int) Math.floor(nPlants/nSectors);
		int nSectorsWithAdditionalPlant = Math.round((( (float)nPlants/(float)nSectors ) - plantsPerSector)*nSectors);
		int indexPlant = 1;
		
		for(int i = 0; i < nSectors; i++) {
			List<String> listPlantsSector = new ArrayList<String>();
			List<Link> links = new ArrayList<Link>();
			boolean firstPlant = true;
			if(nSectorsWithAdditionalPlant > 0) {
				for(int j = 0; j < (plantsPerSector+1); j++) {
					listPlantsSector.add("p"+indexPlant);
					if(firstPlant) {
						ret.add("first-plant tr"+(i+1)+" p"+indexPlant);
						firstPlant = false;
					}
					indexPlant++;
				}
				nSectorsWithAdditionalPlant--;
			}
			else {
				for(int j = 0; j < plantsPerSector; j++) {
					listPlantsSector.add("p"+indexPlant);
					if(firstPlant) {
						ret.add("first-plant tr"+(i+1)+" p"+indexPlant);
						firstPlant = false;
					}
					indexPlant++;
				}
			}
			
			//add hive to the list of locations
			listPlantsSector.add("h");
			
			//Sector plants links
			for(String plant1 : listPlantsSector) {
				for(String plant2: listPlantsSector) {
					if(!plant1.equals(plant2)) {
						links.add(new Link(plant1,plant2));
					}
				}
			}
			
			//Add links to the problem return variable
			for(Link l : links) {
				ret.add(l.toString());
			}
			
			
				
		}
		return ret;
	}

	/*private static List<String> generateLinks(int nPlants) {
		List<Link> links = new ArrayList<Link>();
		List<String> ret = new ArrayList<String>();
		List<String> listLocations = new ArrayList<String>();
		
		for(int i = 0; i < nPlants; i++) {
			listLocations.add("p"+(i+1));
		}
		listLocations.add("h");
		
		for(String loc1 : listLocations) {
			for(String loc2: listLocations) {
				if(!loc1.equals(loc2)) {
					links.add(new Link(loc1,loc2));
				}
			}
		}
		
		for(Link l: links) {
			ret.add(l.toString());
		}
		return ret;
	}*/

	private static String writeDomain(PrintWriter w) {
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
		TypedSymbol type = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "bee location"));
		//type.addType(new Symbol(Symbol.Kind.TYPE, "object"));
		domain.addType(type);
		
		type = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "tracker fumigator"));
		type.addType(new Symbol(Symbol.Kind.TYPE, "bee"));
		domain.addType(type);
		type = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "hive plant"));
		type.addType(new Symbol(Symbol.Kind.TYPE, "location"));
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
		predicate = new NamedTypedList(new Symbol(Symbol.Kind.PREDICATE, "plant-analyzed")); //Set predicate name
		variable = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?p")); //Set 1 variable
		variable.addType(new Symbol(Symbol.Kind.TYPE, "plant")); //Add Type for the variable
		predicate.add(variable); // Add variable to the predicate
		domain.addPredicate(predicate); //Add predicate to domain
			
			//PREDICATE 4
		predicate = new NamedTypedList(new Symbol(Symbol.Kind.PREDICATE, "tracker-ready-to-move")); //Set predicate name
		variable = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?b")); //Set 1 variable
		variable.addType(new Symbol(Symbol.Kind.TYPE, "tracker")); //Add Type for the variable
		predicate.add(variable); // Add variable to the predicate
		domain.addPredicate(predicate); //Add predicate to domain
		
			//PREDICATE 5
		predicate = new NamedTypedList(new Symbol(Symbol.Kind.PREDICATE, "first-plant")); //Set predicate name
		variable = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?b")); //Set 1 variable
		variable.addType(new Symbol(Symbol.Kind.TYPE, "tracker")); //Add Type for the variable
		predicate.add(variable); // Add variable to the predicate
		variable = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?p")); //Set 1 variable
		variable.addType(new Symbol(Symbol.Kind.TYPE, "plant")); //Add Type for the variable
		predicate.add(variable); // Add variable to the predicate
		domain.addPredicate(predicate); //Add predicate to domain
			
			//PREDICATE 6
		predicate = new NamedTypedList(new Symbol(Symbol.Kind.PREDICATE, "fumigator-ready-to-move")); //Set predicate name
		variable = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?b")); //Set 1 variable
		variable.addType(new Symbol(Symbol.Kind.TYPE, "fumigator")); //Add Type for the variable
		predicate.add(variable); // Add variable to the predicate
		domain.addPredicate(predicate); //Add predicate to domain
			
			//PREDICATE 7
		predicate = new NamedTypedList(new Symbol(Symbol.Kind.PREDICATE, "pesticide-tank-empty")); //Set predicate name
		variable = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?b")); //Set 1 variable
		variable.addType(new Symbol(Symbol.Kind.TYPE, "fumigator")); //Add Type for the variable
		predicate.add(variable); // Add variable to the predicate
		domain.addPredicate(predicate); //Add predicate to domain
		
			//PREDICATE 8
		predicate = new NamedTypedList(new Symbol(Symbol.Kind.PREDICATE, "infected-location")); //Set predicate name
		variable = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?b")); //Set 1 variable
		variable.addType(new Symbol(Symbol.Kind.TYPE, "fumigator")); //Add Type for the variable
		predicate.add(variable); // Add variable to the predicate
		variable = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?l")); //Set 1 variable
		variable.addType(new Symbol(Symbol.Kind.TYPE, "location")); //Add Type for the variable
		predicate.add(variable); // Add variable to the predicate
		domain.addPredicate(predicate); //Add predicate to domain
			
			//PREDICATE 9
		predicate = new NamedTypedList(new Symbol(Symbol.Kind.PREDICATE, "healthy-plant")); //Set predicate name
		variable = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?p")); //Set 1 variable
		variable.addType(new Symbol(Symbol.Kind.TYPE, "plant")); //Add Type for the variable
		predicate.add(variable); // Add variable to the predicate
		domain.addPredicate(predicate); //Add predicate to domain
		
		//ACTIONS
			//ACTION 1
		List<TypedSymbol> parameters = new ArrayList<TypedSymbol>();
				//Parameters
		TypedSymbol parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "hive"));
		parameters.add(parameter);
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "plant"));
		parameters.add(parameter);
				//Preconditions
		Exp preconditions = new Exp(Connective.AND);
					//precond1
		Exp precondition = new Exp(Connective.ATOM);
		List<Symbol> atoms = new ArrayList<Symbol>();
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "at"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		precondition.setAtom(atoms);
		preconditions.addChild(precondition);
					//precond2
		Exp precondNot = new Exp(Connective.NOT);
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "at"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		precondition.setAtom(atoms);
		precondNot.addChild(precondition);
		preconditions.addChild(precondNot);
					//precond3
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "directly-connected"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		precondition.setAtom(atoms);
		preconditions.addChild(precondition);
					//precond 4
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "first-plant"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		precondition.setAtom(atoms);
		preconditions.addChild(precondition);
					//precond 5
		precondNot = new Exp(Connective.NOT);
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "plant-analyzed"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		precondition.setAtom(atoms);
		precondNot.addChild(precondition);
		preconditions.addChild(precondNot);
					//precond 6
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "tracker-ready-to-move"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		precondition.setAtom(atoms);
		preconditions.addChild(precondition);
		
			//Effects
		Exp effects = new Exp(Connective.AND);
					//effect1
		Exp effectNot = new Exp(Connective.NOT);
		Exp effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "at"));
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
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?p"));
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
		TypedSymbol agent = new TypedSymbol(new Symbol(Symbol.Kind.AGENT, "b"));
		agent.addType(new Symbol(Symbol.Kind.TYPE, "tracker"));
		Op op = new Op(new Symbol(Symbol.Kind.ACTION, "fly-to-first-plant"), agent, parameters, preconditions, effects);
		domain.addOperator(op);
		
			//ACTION2
		parameters = new ArrayList<TypedSymbol>();
		//Parameters
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
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		precondition.setAtom(atoms);
		preconditions.addChild(precondition);
					//precond2
		precondNot = new Exp(Connective.NOT);
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "at"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		precondition.setAtom(atoms);
		precondNot.addChild(precondition);
		preconditions.addChild(precondNot);
					//precond3
		precondNot = new Exp(Connective.NOT);
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "tracker-ready-to-move"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		precondition.setAtom(atoms);
		precondNot.addChild(precondition);
		preconditions.addChild(precondNot);
				
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
		
			//Agent
		agent = new TypedSymbol(new Symbol(Symbol.Kind.AGENT, "b"));
		agent.addType(new Symbol(Symbol.Kind.TYPE, "tracker"));
		//Adding action to domain
		op = new Op(new Symbol(Symbol.Kind.ACTION, "analyze-plant"), agent, parameters, preconditions, effects);
		domain.addOperator(op);		
		
		//ACTION 3
		parameters = new ArrayList<TypedSymbol>();
		//Parameters
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?from"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "plant"));
		parameters.add(parameter);
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?target"));
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
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "tracker-ready-to-move"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		precondition.setAtom(atoms);
		preconditions.addChild(precondition);
					//precond3
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "at"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?from"));
		precondition.setAtom(atoms);
		preconditions.addChild(precondition);
					//precond4
		effectNot = new Exp(Connective.NOT);
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "at"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?target"));
		precondition.setAtom(atoms);
		effectNot.addChild(precondition);
		preconditions.addChild(effectNot);
					//precond5
		effectNot = new Exp(Connective.NOT);
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "at"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		precondition.setAtom(atoms);
		effectNot.addChild(precondition);
		preconditions.addChild(effectNot);
					//precond6
		effectNot = new Exp(Connective.NOT);
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "plant-analyzed"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?target"));
		precondition.setAtom(atoms);
		effectNot.addChild(precondition);
		preconditions.addChild(effectNot);
					//precond7
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "plant-analyzed"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?from"));
		precondition.setAtom(atoms);
		preconditions.addChild(precondition);
		
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
		
		//agent
		agent = new TypedSymbol(new Symbol(Symbol.Kind.AGENT, "b"));
		agent.addType(new Symbol(Symbol.Kind.TYPE, "tracker"));
		//Adding action to domain
		op = new Op(new Symbol(Symbol.Kind.ACTION, "go-to-next-plant"), agent, parameters, preconditions, effects);
		domain.addOperator(op);
		
		//ACTION 6
		parameters = new ArrayList<TypedSymbol>();
		//Parameters
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
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "tracker-ready-to-move"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		precondition.setAtom(atoms);
		preconditions.addChild(precondition);
					//precond2
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "at"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		precondition.setAtom(atoms);
		preconditions.addChild(precondition);
					//precond3
		precondNot = new Exp(Connective.NOT);
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "at"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		precondition.setAtom(atoms);
		precondNot.addChild(precondition);
		preconditions.addChild(precondNot);
		
				//Effects
		effects = new Exp(Connective.AND);
					//effect1
		effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "at"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		effect.setAtom(atoms);
		effects.addChild(effect);
					//effect2
		effectNot = new Exp(Connective.NOT);
		effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "at"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		effect.setAtom(atoms);
		effectNot.addChild(effect);
		effects.addChild(effectNot);
					//effect3
		effectNot = new Exp(Connective.NOT);
		effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "healthy-plant"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		effect.setAtom(atoms);
		effectNot.addChild(effect);
		effects.addChild(effectNot);
		
		//AGENT
		agent = new TypedSymbol(new Symbol(Symbol.Kind.AGENT, "b"));
		agent.addType(new Symbol(Symbol.Kind.TYPE, "tracker"));
		
		op = new Op(new Symbol(Symbol.Kind.ACTION, "tracker-back-home"), agent, parameters, preconditions, effects);
		domain.addOperator(op);
		
		
		/*----------------------------------ACCIONES FUMIGADORA-----------------------------------
		------------------------------------------------------------------------------------------
		------------------------------------------------------------------------------------------*/
		
				//ACTION 7
		parameters = new ArrayList<TypedSymbol>();
		//Parameters
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "hive"));
		parameters.add(parameter);
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "plant"));
		parameters.add(parameter);
				//Preconditions
		preconditions = new Exp(Connective.AND);
					//precond1
		effectNot = new Exp(Connective.NOT);
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "fumigator-ready-to-move"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		precondition.setAtom(atoms);
		
		effectNot.addChild(precondition);
		preconditions.addChild(effectNot);
					
					//precond2 
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "directly-connected"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		precondition.setAtom(atoms);
		
		preconditions.addChild(precondition);
					//precond3 
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "at"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		precondition.setAtom(atoms);
		
		preconditions.addChild(precondition);
		
					//precond4
		effectNot = new Exp(Connective.NOT);
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();

		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "infected-location"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		precondition.setAtom(atoms);

		effectNot.addChild(precondition);
		preconditions.addChild(effectNot);
		
					//precond5 
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();

		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "pesticide-tank-empty"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		precondition.setAtom(atoms);
		
					//precond6
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "pesticide-tank-empty"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		precondition.setAtom(atoms);
		
		preconditions.addChild(precondition);
		
					//precond7
		effectNot = new Exp(Connective.NOT);
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();

		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "healthy-plant"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		precondition.setAtom(atoms);

		effectNot.addChild(precondition);
		preconditions.addChild(effectNot);
		
				//Effects
					//effect1
		effects = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "infected-location"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		effects.setAtom(atoms);
		
				//effect2
		
					
		//AGENT
		agent = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "b"));
		agent.addType(new Symbol(Symbol.Kind.TYPE, "fumigator"));
		
		op = new Op(new Symbol(Symbol.Kind.ACTION, "receive-infected-location"), agent, parameters, preconditions, effects);
		domain.addOperator(op);
		
		
				//ACTION 8
		parameters = new ArrayList<TypedSymbol>();
		//Parameters
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "hive"));
		parameters.add(parameter);
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "plant"));
		parameters.add(parameter);
				//Preconditions
		preconditions = new Exp(Connective.AND);
					//precond1
		effectNot = new Exp(Connective.NOT);
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "fumigator-ready-to-move"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		precondition.setAtom(atoms);
		
		effectNot.addChild(precondition);
		preconditions.addChild(effectNot);
					
					//precond2 
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "pesticide-tank-empty"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		precondition.setAtom(atoms);
		
		preconditions.addChild(precondition);
					//precond3 
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "at"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		precondition.setAtom(atoms);
		
		preconditions.addChild(precondition);
					//precond4
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();

		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "infected-location"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		precondition.setAtom(atoms);

		preconditions.addChild(precondition);
			//precond1
		effectNot = new Exp(Connective.NOT);
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();

		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "healthy-plant"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		precondition.setAtom(atoms);

		effectNot.addChild(precondition);
		preconditions.addChild(effectNot);
		
				//Effects
		effects = new Exp(Connective.AND);
					//effect1
		effectNot = new Exp(Connective.NOT);
		effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "pesticide-tank-empty"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		effect.setAtom(atoms);
		
		effectNot.addChild(effect);
		effects.addChild(effectNot);
					//effect2
		effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();
		
		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "fumigator-ready-to-move"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		effect.setAtom(atoms);
		
		effects.addChild(effect);
		
		//AGENT
		agent = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "b"));
		agent.addType(new Symbol(Symbol.Kind.TYPE, "fumigator"));
		
		op = new Op(new Symbol(Symbol.Kind.ACTION, "fill-pesticide-tank"), agent, parameters, preconditions, effects);
		domain.addOperator(op);
		
			//ACTION 9
		parameters = new ArrayList<TypedSymbol>();
		//Parameters
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "hive"));
		parameters.add(parameter);
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "plant"));
		parameters.add(parameter);
		//Preconditions
		preconditions = new Exp(Connective.AND);
			//precond1
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();

		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "fumigator-ready-to-move"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		precondition.setAtom(atoms);

		preconditions.addChild(precondition);

			//precond2
		effectNot = new Exp(Connective.NOT);
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();

		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "pesticide-tank-empty"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		precondition.setAtom(atoms);

		effectNot.addChild(precondition);
		preconditions.addChild(effectNot);
			//precond3 
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();

		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "at"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		precondition.setAtom(atoms);

		preconditions.addChild(precondition);
			//precond4
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();

		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "infected-location"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		precondition.setAtom(atoms);

		preconditions.addChild(precondition);
		
			//precond5
		effectNot = new Exp(Connective.NOT);
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();

		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "healthy-plant"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		precondition.setAtom(atoms);

		effectNot.addChild(precondition);
		preconditions.addChild(effectNot);

			//Effects
		effects = new Exp(Connective.AND);
			//effect1
		effectNot = new Exp(Connective.NOT);
		effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();

		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "at"));
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
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		effect.setAtom(atoms);

		effects.addChild(effect);
				//effect3
		effectNot = new Exp(Connective.NOT);
		effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();

		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "fumigator-ready-to-move"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		effect.setAtom(atoms);

		effectNot.addChild(effect);
		effects.addChild(effectNot);

		//AGENT
		agent = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "b"));
		agent.addType(new Symbol(Symbol.Kind.TYPE, "fumigator"));

		op = new Op(new Symbol(Symbol.Kind.ACTION, "go-to-infected-location"), agent, parameters, preconditions, effects);
		domain.addOperator(op);

				//ACTION 10
		parameters = new ArrayList<TypedSymbol>();
		//Parameters
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "hive"));
		parameters.add(parameter);
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "plant"));
		parameters.add(parameter);
		//Preconditions
		preconditions = new Exp(Connective.AND);
			//precond1
		effectNot = new Exp(Connective.NOT);
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();

		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "fumigator-ready-to-move"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		precondition.setAtom(atoms);

		effectNot.addChild(precondition);
		preconditions.addChild(effectNot);

			//precond2
		effectNot = new Exp(Connective.NOT);
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();

		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "pesticide-tank-empty"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		precondition.setAtom(atoms);

		effectNot.addChild(precondition);
		preconditions.addChild(effectNot);
		
			//precond3 
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();

		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "at"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		precondition.setAtom(atoms);

		preconditions.addChild(precondition);
			//precond4
		effectNot = new Exp(Connective.NOT);
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();

		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "at"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		precondition.setAtom(atoms);

		effectNot.addChild(precondition);
		preconditions.addChild(effectNot);
		
		//precond5
		effectNot = new Exp(Connective.NOT);
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();

		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "healthy-plant"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		precondition.setAtom(atoms);

		effectNot.addChild(precondition);
		preconditions.addChild(effectNot);

		//Effects
		effects = new Exp(Connective.AND);
			//effect1
		effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();

		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "pesticide-tank-empty"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		effect.setAtom(atoms);

		effects.addChild(effect);
			
			//effect2
		effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();

		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "fumigator-ready-to-move"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		effect.setAtom(atoms);

		effects.addChild(effect);
			
			//effect3
		effectNot = new Exp(Connective.NOT);
		effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();

		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "infected-location"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		effect.setAtom(atoms);

		effectNot.addChild(effect);
		effects.addChild(effectNot);
		
			//effect3
		effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();

		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "healthy-plant"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		effect.setAtom(atoms);

		effects.addChild(effect);

		//AGENT
		agent = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "b"));
		agent.addType(new Symbol(Symbol.Kind.TYPE, "fumigator"));

		op = new Op(new Symbol(Symbol.Kind.ACTION, "fumigate"), agent, parameters, preconditions, effects);
		domain.addOperator(op);
		
			//ACTION 11
		parameters = new ArrayList<TypedSymbol>();
		//Parameters
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "hive"));
		parameters.add(parameter);
		parameter = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		parameter.addType(new Symbol(Symbol.Kind.TYPE, "plant"));
		parameters.add(parameter);
		//Preconditions
		preconditions = new Exp(Connective.AND);
			//precond1
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();

		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "fumigator-ready-to-move"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		precondition.setAtom(atoms);

		preconditions.addChild(precondition);
			//precond2
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();

		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "pesticide-tank-empty"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		precondition.setAtom(atoms);

		preconditions.addChild(precondition);
		
			//precond3 
		effectNot = new Exp(Connective.NOT);
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();

		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "at"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		precondition.setAtom(atoms);

		effectNot.addChild(precondition);
		preconditions.addChild(effectNot);
		
			//precond4
		effectNot = new Exp(Connective.NOT);
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();

		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "infected-location"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		precondition.setAtom(atoms);

		effectNot.addChild(precondition);
		preconditions.addChild(effectNot);
			//precond5
		precondition = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();

		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "healthy-plant"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?p"));
		precondition.setAtom(atoms);

		preconditions.addChild(precondition);

		//Effects
		effects = new Exp(Connective.AND);
			//effect1
		effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();

		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "at"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?h"));
		effect.setAtom(atoms);

		effects.addChild(effect);
			
			//effect2
		effectNot = new Exp(Connective.NOT);
		effect = new Exp(Connective.ATOM);
		atoms = new ArrayList<Symbol>();

		atoms.add(new Symbol(Symbol.Kind.PREDICATE, "fumigator-ready-to-move"));
		atoms.add(new Symbol(Symbol.Kind.VARIABLE, "?b"));
		effect.setAtom(atoms);

		effectNot.addChild(effect);
		effects.addChild(effectNot);

		//AGENT
		agent = new TypedSymbol(new Symbol(Symbol.Kind.VARIABLE, "b"));
		agent.addType(new Symbol(Symbol.Kind.TYPE, "fumigator"));

		op = new Op(new Symbol(Symbol.Kind.ACTION, "fumigator-back-home"), agent, parameters, preconditions, effects);
		domain.addOperator(op);
		
		//File domain to string
		String domainString = domain.toString().toLowerCase();
		return domainString;
		
	}
}
