import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import fr.uga.pddl4j.parser.NamedTypedList;
import fr.uga.pddl4j.parser.RequireKey;
import fr.uga.pddl4j.parser.Symbol;




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
		domain.addType(new TypedSymbol(new Symbol(Symbol.Kind.TYPE, "bee location hive plant sector"), "object"));
		domain.addType(new TypedSymbol(new Symbol(Symbol.Kind.TYPE, "tracker fumigator"), "bee"));
		
		//FUNCTIONS
		
		//PREDICATES
		domain.addPredicate(new NamedTypedList(new Symbol(Symbol.Kind.PREDICATE, "directly-connected ?l1 - location ?l2 - location")));
		domain.addPredicate(new NamedTypedList(new Symbol(Symbol.Kind.PREDICATE, "at ?b - bee ?l - location")));
		domain.addPredicate(new NamedTypedList(new Symbol(Symbol.Kind.PREDICATE, "in ?b - bee ?h - hive")));
		domain.addPredicate(new NamedTypedList(new Symbol(Symbol.Kind.PREDICATE, "free ?s - sector")));
		
		//ACTIONS
		
		w.print(domain.toString()); // Write into domainKillbee.pddl
		w.close();
		

		System.out.println(domain.toString());
	}

}
