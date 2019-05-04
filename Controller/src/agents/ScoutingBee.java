package agents;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import functionality.Action;
import structuralElements.Sector;

/**
 * Class that represents the scouting bees of our system
 * @author Lukasz Marek Olszewski, Laura López Pérez, Álvaro Luis Martínez, Miguel Lagares Velasco
 *
 */
public class ScoutingBee implements Runnable {
	// Class variables
	private String idBee;
	private Sector sectorAssigned;
	private Action action;
	private boolean powerOff;
	private boolean inHive;
	private Object hasWorkToDo;
	
	/**
	 * Scouting bee constructor
	 * @param idBee string that identify the scouting bee
	 * @param sectorAssigned sector object that have assigned the scouting bee
	 */
	public ScoutingBee(String idBee, Sector sectorAssigned, boolean powerOff, boolean inHive, Action action, Object hasWorkToDo) {
		this.idBee = idBee;
		this.sectorAssigned = sectorAssigned;
		this.action = action;
		this.powerOff = powerOff;
		this.inHive = inHive;
		this.hasWorkToDo = hasWorkToDo;
	}
	
	public void run() {
		Writer writer = null;
		
		while(!powerOff) { 
			
			try {
				hasWorkToDo.wait();
			} catch (InterruptedException e3) {
				e3.printStackTrace();
			}
			
			switch(action.getActionName()) {
				case "fly-to-first-plant":
					try {
						this.setInHive(false);
						this.flyToFirstPlant(action.getActionTimeExecution());
					} catch (InterruptedException e2) {
						e2.printStackTrace();
					}
					break;
				case "go-to-next-plant":
					try {
						this.goToNextPlant(action.getActionTimeExecution());
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					break;
				case "analyze-plant":
					for(int i = 0; i<sectorAssigned.getPlantsSector().length; i++) {
						if(sectorAssigned.getPlantsSector()[i].getIdPlant() == action.getPlantId()
								&& !sectorAssigned.getPlantsSector()[i].isAnalyzed()) { 
							try {
								//Analyze plant
								sectorAssigned.getPlantsSector()[i].analyzePlant(action.getActionTimeExecution());
								//Set that plant is analyzed
								sectorAssigned.getPlantsSector()[i].setAnalyzed(true);
								//Check if plant is infected
								if(sectorAssigned.getPlantsSector()[i].isInfected()) {
									//Write a new line in infected file with plant id
									writer = new BufferedWriter(new FileWriter("infectedPlants.txt", true));
									writer.append(System.lineSeparator()+sectorAssigned.getPlantsSector()[i].getIdPlant()); 
									writer.close();
								}
							} catch (InterruptedException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
					break;
				case "tracker-back-home":
					try {
						this.trackerBackHome(action.getActionTimeExecution());
						this.setInHive(true);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				default:
					System.out.println("ERROR: Action impossible to perform by the scouting bee");
			}
		}
		
	}

	/**
	 * Method that returns the bee id
	 * @return string with the bee id
	 */
	public String getIdBee() {
		return idBee;
	}

	/**
	 * Method that returns the sector that have assigned the scouting bee
	 * @return Sector object that have assigned the scouting bee
	 */
	public Sector getSectorAssigned() {
		return sectorAssigned;
	}

	/**
	 * Method that modify the sector that have assigned the scouting bee
	 * @param sectorAssigned the sector that is going to be assigned to the scouting bee
	 */
	public void setSectorAssigned(Sector sectorAssigned) {
		this.sectorAssigned = sectorAssigned;
	}

	/**
	 * Method that allow assign actions to the scouting bee
	 * @param action assigned action
	 */
	public void setAction(Action action) {
		this.action = action;
	}
	
	/**
	 * Method that simulate the flying of the scouting bee to the first plant to analyze
	 * @param time that costs the execution of the action
	 * @throws InterruptedException 
	 */
	public void flyToFirstPlant(int time) throws InterruptedException { // Este método más adelante puede ser usado para introducir tasas de error para que haya un fallo y se necesite replanificación
		Thread.sleep(time);
	}
	
	/**
	 * Method that simulate the flying of the scouting bee to the last plant to analyze
	 * @param time that costs the execution of the action
	 * @throws InterruptedException 
	 */
	public void goToNextPlant(int time) throws InterruptedException { // Este método más adelante puede ser usado para introducir tasas de error para que haya un fallo y se necesite replanificación
		Thread.sleep(time);
	}
	
	/**
	 * Method that simulate the flying of the scouting bee to the bee hive
	 * @param time that costs the execution of the action
	 * @throws InterruptedException
	 */
	public void trackerBackHome(int time) throws InterruptedException { // Este método más adelante puede ser usado para introducir tasas de error para que haya un fallo y se necesite replanificación
		Thread.sleep(time);
	}
	
	/**
	 * Method that indicates if the scouting bee is in the hive
	 * @return true if the scouting bee is in the hive false if not
	 */
	public boolean isInHive() {
		return inHive;
	}

	/**
	 * Method that allow change the value of inHive variable
	 * @param inHive true if the scouting bee is in the hive false if not
	 */
	public void setInHive(boolean inHive) {
		this.inHive = inHive;
	}
	
	/**
	 * Method that returns if the bee have work to do
	 * @return true if the bee have work false if not
	 */
	public Object getHasWorkToDo() {
		return hasWorkToDo;
	}

	/**
	 * Method that inform if the scouting bee is powered off
	 * @return true if the scouting bee is powered off false if not
	 */
	public boolean isPowerOff() {
		return powerOff;
	}

	/**
	 * Method that simulate the power off of the scouting bee when the plan is finished
	 */
	public void setPowerOff() {
		if(this.isInHive())
			powerOff = true;
	}
	
}
