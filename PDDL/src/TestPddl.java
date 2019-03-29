import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import fr.uga.*;
import fr.uga.pddl4j.parser.*;


public class TestPddl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File f = new File("domain/domainKillbee.pddl");
		FileWriter fw = null;
		try {
			fw = new FileWriter(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter w = new PrintWriter(fw);
		Domain domain = new Domain(new Symbol(Symbol.Kind.ALIAS, "killbee"));
		
		
		w.print(domain.toString());
		

		System.out.println(domain.toString());
		
		w.close();
		
		
	}

}
