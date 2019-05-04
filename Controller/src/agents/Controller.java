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
						for(int i=0; i<beeHive.getScoutingBeesList().length; i++) {
							if(beeHive.getScoutingBeesList()[i].getIdBee() == action.getScoutingBeeId()) {
								for(int j=0; j<crop.getSectors().length; j++) {
									if(crop.getSectors()[j].getIdSector() == action.getSectorId()) 
										beeHive.getScoutingBeesList()[i].setSectorAssigned(crop.getSectors()[j]);
								}
							}
						}
						break;
					case "fly-to-first-plant":
						assignActionToScoutingBee(action);
						break;
					case "go-to-next-plant":
						assignActionToScoutingBee(action);
						break;
					case "analyze-plant":
						assignActionToScoutingBee(action);
						break;
					case "tracker-back-home":
						assignActionToScoutingBee(action);
						break;
					case "receive-infected-location":
						
						break;
					case "fill-pesticide-tank":
						assignActionToFumigatorBee(action);
						break;
					case "go-to-infected-location":
						assignActionToFumigatorBee(action);
						break;
					case "fumigate":
						assignActionToFumigatorBee(action);
						break;
					case "fumigator-back-home":
						assignActionToFumigatorBee(action);
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
	
	public void assignActionToScoutingBee(Action action) {
		for(int i=0; i<beeHive.getScoutingBeesList().length; i++) {
			if(beeHive.getScoutingBeesList()[i].getIdBee() == action.getScoutingBeeId()) {
				beeHive.getScoutingBeesList()[i].setAction(action);
				beeHive.getScoutingBeesList()[i].getHasWorkToDo().notify();
			}
		}
	}
	
	public void assignActionToFumigatorBee(Action action) {
		for(int i=0; i<beeHive.getFumigatorBeesList().length; i++) {
			if(beeHive.getFumigatorBeesList()[i].getIdBee() == action.getFumigatorBeeId()) {
				beeHive.getFumigatorBeesList()[i].setAction(action);
				beeHive.getFumigatorBeesList()[i].getHasWorkToDo().notify();
			}
		}
	}

}
