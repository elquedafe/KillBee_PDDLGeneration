package controller.functionality;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlanReader implements Runnable {

	private boolean firstWord = false;
	private String actionName;
	private LinkedHashMap<String, ArrayList<String>> planMap;

	public PlanReader(LinkedHashMap<String, ArrayList<String>> planMap) {
		this.planMap = planMap;
	}
	
	public void run() {
		while(true) {
			File dir = new File("plans");

			Pattern p = Pattern.compile("plan[0-9]*");
			for(File f : dir.listFiles()) {
				Matcher m = p.matcher(f.getName());
				if(m.find()) {
					FileReader fileReader = null;
					try {
						fileReader = new FileReader(f.getName());
						BufferedReader bufferedReader = new BufferedReader(fileReader);
						Scanner scanner = new Scanner(bufferedReader);
						Scanner lineScanner = null;
						String lineScannerAux;
						p = Pattern.compile("\\([0-9]*\\)");
						
						while(scanner.hasNextLine()) {
							List<String> parameters = new ArrayList<>();
							lineScanner = new Scanner(scanner.nextLine());
							firstWord = true;
							
							while(lineScanner.hasNext()) {
								if(firstWord) {
									actionName = lineScanner.next();
									firstWord = false;
								}
								
								lineScannerAux = lineScanner.next();
								m = p.matcher(lineScannerAux);
								
								if(!m.find()) {
									parameters.add(lineScannerAux);
								}else {
									lineScannerAux = lineScannerAux.substring(1);
									lineScannerAux = lineScannerAux.substring(0, lineScannerAux.length()-1);
									parameters.add(lineScannerAux);
								}	
							}
							
							planMap.put(actionName, (ArrayList<String>) parameters);
						}
						
						planMap.remove("Plan");
						f.delete();
						
						scanner.close();
						lineScanner.close();
						bufferedReader.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
