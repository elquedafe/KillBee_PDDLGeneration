package agents;

import functionality.Action;
import structuralElements.PesticideTank;
import structuralElements.Sector;

/**
 * Class that represents the fumigating bees of our system
 * @author Lukasz Marek Olszewski, Laura López Pérez, Álvaro Luis Martínez, Miguel Lagares Velasco
 *
 */
public class FumigatorBee implements Runnable {
	// Class variables
	private BeeHive beeHive;
	private String idBee;
	private Sector infectedLocation;
	private PesticideTank pesticideTank;
	private Action action;
	private boolean powerOff;
	private boolean inHive;
	private Object hasWorkToDo;
	
	/**
	 * Fumigator bee constructor
	 * @param idBee string that identify the fumigator bee
	 * @param pesticideTank pesticide tank object
	 */
	public FumigatorBee(BeeHive beeHive, String idBee, Sector infectedLocation, PesticideTank pesticideTank, boolean powerOff, boolean inHive, Action action, Object hasWorkToDo) {
		this.beeHive = beeHive;
		this.idBee = idBee;
		this.infectedLocation = infectedLocation;
		this.pesticideTank = pesticideTank;
		this.action = action;
		this.powerOff = powerOff;
		this.inHive = inHive;
		this.hasWorkToDo = hasWorkToDo;
	}
	
	public void run() {
		
		while(powerOff) { 
			
			try {
				hasWorkToDo.wait();
			} catch (InterruptedException e3) {
				e3.printStackTrace();
			}
			
			switch(action.getActionName()) {
				case "fill-pesticide-tank":
					try {
						beeHive.fillTank(pesticideTank, action.getActionTimeExecution());
					} catch (InterruptedException e2) {
						e2.printStackTrace();
					}
					break;
				case "go-to-infected-location":
					try {
						this.setInHive(false);
						this.goToInfectedLocation(action.getActionTimeExecution());
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					break;
				case "fumigate":
					for(int i = 0; i<infectedLocation.getPlantsSector().length; i++) {
						if(infectedLocation.getPlantsSector()[i].getIdPlant() == action.getPlantId()
								&& infectedLocation.getPlantsSector()[i].isInfected()) { 
							try {
								infectedLocation.getPlantsSector()[i].fumigatePlant(action.getActionTimeExecution());
								infectedLocation.getPlantsSector()[i].setInfected(false);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					break;
				case "fumigator-back-home":
					try {
						this.fumigatorBackHome(action.getActionTimeExecution());
						this.setInHive(true);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				default:
					System.out.println("ERROR: Action impossible to perform by the fumigator bee");
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
	 * Method that returns the infected location that have assigned the fumigator bee
	 * @return Sector (infected location) object that have assigned the fumigator bee
	 */
	public Sector getInfectedLocation() {
		return infectedLocation;
	}

	/**
	 * Method that modify the infected location that have assigned the fumigator bee
	 * @param infectedLocation the sector (infected location) that is going to be assigned to the fumigator bee
	 */
	public void setInfectedLocation(Sector infectedLocation) {
		this.infectedLocation = infectedLocation;
	}
	
	/**
	 * Method that returns the pesticide tank object of the fumigator bee
	 * @return Pesticide tank object that have the fumigator bee
	 */
	public PesticideTank getPesticideTank() {
		return pesticideTank;
	}

	/**
	 * Method that allow assign actions to the fumigator bee
	 * @param action assigned action
	 */
	public void setAction(Action action) {
		this.action = action;
	}
	
	/**
	 * Method that simulate the flying of the fumigator bee to the infected location
	 * @param time that costs the execution of the action
	 * @throws InterruptedException 
	 */
	public void goToInfectedLocation(int time) throws InterruptedException { // Este método más adelante puede ser usado para introducir tasas de error para que haya un fallo y se necesite replanificación
		Thread.sleep(time);
	}
	
	/**
	 * Method that simulate the flying of the fumigator bee to the bee hive
	 * @param time that costs the execution of the action
	 * @throws InterruptedException
	 */
	public void fumigatorBackHome(int time) throws InterruptedException { // Este método más adelante puede ser usado para introducir tasas de error para que haya un fallo y se necesite replanificación
		Thread.sleep(time);
	}
	
	/**
	 * Method that indicates if the fumigator bee is in the hive
	 * @return true if the fumigator bee is in the hive false if not
	 */
	public boolean isInHive() {
		return inHive;
	}

	/**
	 * Method that allow change the value of inHive variable 
	 * @param inHive true if the fumigator bee is in the hive false if not
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
	 * Method that inform if the fumigator bee is powered off
	 * @return true if the fumigator bee is powered off false if not
	 */
	public boolean isPowerOff() {
		return powerOff;
	}

	/**
	 * Method that simulate the power off of the fumigator bee when the plan is finished
	 */
	public void setPowerOff() {
		if(this.isInHive())
			powerOff = true;
	}
	
}
