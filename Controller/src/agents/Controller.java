package agents;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import functionality.Action;
import structuralElements.Crop;

/**
 * Class responsible for the supervision of the agents actions during the simulation time execution
 * @author Lukasz Marek Olszewski, Laura López Pérez, Álvaro Luis Martínez, Miguel Lagares Velasco
 *
 */
public class Controller implements Runnable {
	// Class variables
	private BeeHive beeHive;
	private Crop crop;
	private LinkedHashMap<String, ArrayList<String>> planMap;
	
	/**
	 * Controller constructor
	 * @param beeHive bee hive object
	 * @param crop crop object
	 * @param planMap plan result of the planning process 
	 */
	public Controller(BeeHive beeHive, Crop crop, LinkedHashMap<String, ArrayList<String>> planMap) {
		this.beeHive = beeHive;
		this.crop = crop;
		this.planMap = planMap;
	}
	
	public void run() {
		
		while(crop.thereAreInfectedSectors()) {
			
			for(Map.Entry<String, ArrayList<String>> entry : planMap.entrySet()) {
				Action action = new Action(entry.getKey(), (String[])entry.getValue().toArray());
				
				switch(action.getActionName()) {
					case "assign-sector":
						
						break;
					case "fly-to-first-plant":
						
						break;
					case "go-to-next-plant":
						
						break;
					case "analyze-plant":
						
						break;
					case "analyze-last-plant":
						
						break;
					case "tracker-back-home":
						
						break;
					case "receive-infected-location":
						
						break;
					case "fill-pesticide-tank":
						
						break;
					case "go-to-infected-location":
						
						break;
					case "fumigate":
						
						break;
					case "fumigator-back-home":
						
						break;
					default:
						System.out.println("ERROR: Action impossible to perform by the system");
				}
			}
			
		}
		
		beeHive.powerOffBees();
		while(beeHive.thereAreActiveBees()) {
			beeHive.powerOffBees();
		}
		
	}

}
