import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.*;

import agents.BeeHive;
import agents.Controller;
import structuralElements.Crop;

/**
 * Class that puts the system into operation and waits while there are active tasks
 * @author Lukasz Marek Olszewski, Laura López Pérez, Álvaro Luis Martínez, Miguel Lagares Velasco
 *
 */
public class Main {

	public static void main(String[] args) {
		// Class variables
		int numSectors; // Number of sectors that is going to have the crop
		int numPlantsSector; // Number of plants that is going to have each sector
		int numScoutingBees; // Number of scouting bees that is going to store the bee hive
		int numFumigatorBees; // Number of fumigator bees that is going to store the bee hive
		String fileName; // The name of the file that contains the plan
		
		if(args.length == 5) {
			numSectors = Integer.parseInt(args[0]);
			numPlantsSector = Integer.parseInt(args[1]);
			numScoutingBees = Integer.parseInt(args[2]);
			numFumigatorBees = Integer.parseInt(args[3]);
			fileName = args[4];
			
			// Initialization of the crop and its elements
			Crop crop = new Crop(numSectors, numPlantsSector);
			
			// Initialization of the BeeHive
			BeeHive beeHive = new BeeHive(numScoutingBees, numFumigatorBees, crop);
			
			// Plan to LinkedHashMap parser
			LinkedHashMap<String, ArrayList<String>> planMap = null;
			
			try {
				planMap = new LinkedHashMap<String, ArrayList<String>>();
				boolean firstWord;
				String actionName = null;
				FileReader fileReader = new FileReader(fileName);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				Scanner scanner = new Scanner(bufferedReader);
				Scanner lineScanner = null;
				
				String lineScannerAux;
				Pattern p = Pattern.compile("\\([0-9]*\\)");
				
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
						Matcher m = p.matcher(lineScannerAux);
						
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
				
				planMap.remove("Plan"); // Remove of the last line of the plan, is not an action
				
				// JSON parser
				Gson gson = new Gson(); 
				String json = gson.toJson(planMap);
				
				System.out.println(json);
				
				FileWriter writer = new FileWriter("planJSON.json");
				writer.write(json);
				
				writer.close();
				scanner.close();
				lineScanner.close();
				bufferedReader.close();
				
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// Initializarion of the controller
			Controller controller = new Controller(beeHive, crop, planMap);
			Thread threadController = new Thread(controller);
			threadController.start();
			
			while(threadController.isAlive()) { // The main thread waits while the controller of the system is alive
				try {
					Thread.sleep(1000);
				}catch(InterruptedException e) {
					throw new IllegalStateException("Error: "+e.getMessage());
				}
			}
			
		}else {
			System.out.println("ERROR: Please introduce the correct parameters: numSectors, numPlantsSector, numScoutingBees, numFumigatorBees, fileName");
		}
	}

}
