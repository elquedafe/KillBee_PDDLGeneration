package agents;

import structuralElements.Crop;
import structuralElements.PesticideTank;

/**
 * Class that represents the hive (administration point) of our system
 * @author Lukasz Marek Olszewski, Laura López Pérez, Álvaro Luis Martínez, Miguel Lagares Velasco
 *
 */
public class BeeHive {
	// Class variables
	private ThreadGroup scoutingBeesGroup;
	private ThreadGroup fumigatorBeesGroup;
	private Thread scoutingBeesThreadList[];
	private Thread fumigatorBeesThreadList[];
	private ScoutingBee scoutingBeesList[];
	private FumigatorBee fumigatorBeesList[];
	private Crop crop;
	
	/**
	 * Bee hive constructor
	 * @param numScoutingBees number of scouting bees that is going to have the bee hive
	 * @param numFumigatorBees number of fumigator bees that is going to have the bee hive
	 * @param crop crop object
	 */
	public BeeHive(int numScoutingBees, int numFumigatorBees, Crop crop) {
		this.crop = crop;
		scoutingBeesList = new ScoutingBee[numScoutingBees-1];
		fumigatorBeesList = new FumigatorBee[numFumigatorBees-1];
		
		// Creation of the scouting bees threads
		scoutingBeesGroup = new ThreadGroup("Scouting bees group");
		int counter = 0;
		
		do {
			ScoutingBee scoutingBee = new ScoutingBee("tr" + numScoutingBees, null, false, true, null, false);
			scoutingBeesList[counter] = scoutingBee;
			Thread threadScoutingBee = new Thread(scoutingBeesGroup, scoutingBee, "tr" + numScoutingBees);
			threadScoutingBee.start();
			numScoutingBees--;
			counter++;
		}while(numScoutingBees != 0);
		
		scoutingBeesThreadList = new Thread[scoutingBeesGroup.activeCount()];
		scoutingBeesGroup.enumerate(scoutingBeesThreadList);
		
		// Creation of the fumigator bees threads
		fumigatorBeesGroup = new ThreadGroup("Fumigator bees group");
		counter = 0;
		
		do {
			FumigatorBee  fumigatorBee = new FumigatorBee(this, "fum" + numFumigatorBees, null, new PesticideTank(false), false, true, null, false);
			fumigatorBeesList[counter] = fumigatorBee;
			Thread threadFumigatorBee = new Thread(fumigatorBeesGroup, fumigatorBee, "fum" + numFumigatorBees);
			threadFumigatorBee.start();
			numFumigatorBees--;
			counter++;
		}while(numFumigatorBees != 0);		
		
		fumigatorBeesThreadList = new Thread[fumigatorBeesGroup.activeCount()];
		fumigatorBeesGroup.enumerate(fumigatorBeesThreadList);
	}

	/**
	 * Returns the scouting bees thread group
	 * @return ThreadGroup object
	 */
	public ThreadGroup getScoutingBeesGroup() {
		return scoutingBeesGroup;
	}

	/**
	 * Returns the fumigator bees thread group
	 * @return ThreadGroup object
	 */
	public ThreadGroup getFumigatorBeesGroup() {
		return fumigatorBeesGroup;
	}

	/**
	 * Returns array with the scouting bees threads
	 * @return Thread[] list
	 */
	public Thread[] getScoutingBeesThreadList() {
		return scoutingBeesThreadList;
	}

	/**
	 * Returns array with the fumigator bees threads
	 * @return Thread[] list
	 */
	public Thread[] getFumigatingBeesThreadList() {
		return fumigatorBeesThreadList;
	}
	
	/**
	 * Returns array with the scouting bees objects
	 * @return list
	 */
	public ScoutingBee[] getScoutingBeesList() {
		return scoutingBeesList;
	}

	/**
	 * Returns array with the fumigator bees objects
	 * @return list
	 */
	public FumigatorBee[] getFumigatorBeesList() {
		return fumigatorBeesList;
	}

	/**
	 * Method that simulate the pesticide tank filling
	 * @param time that costs the execution of the action
	 * @throws InterruptedException
	 */
	public void fillTank(PesticideTank pesticideTank, int time) throws InterruptedException {
		Thread.sleep(time);
		pesticideTank.setFullTank(true);
	}
	
	/**
	 * Method that assign sector to scouting bee
	 * @param scoutingBee scouting bee object
	 */
	public void assignSector(ScoutingBee scoutingBee, String sector) {
		for(int i=0; i<crop.getSectors().length; i++) {
			if(crop.getSectors()[i].getIdSector() == sector) {
				scoutingBee.setSectorAssigned(crop.getSectors()[i]);
			}
		}
	}
	
	/**
	 * Method that power off the both types of bees
	 */
	public void powerOffBees() {
		for(int i = 0; i<this.getScoutingBeesList().length; i++) {
			this.getScoutingBeesList()[i].setPowerOff();
		}
		for(int j = 0; j<this.getFumigatorBeesList().length; j++) {
			this.getFumigatorBeesList()[j].setPowerOff();
		}
	}
	
	/**
	 * Method that check if all bees are powered off
	 * @return true if all bees are powered off, false if not
	 */
	public boolean thereAreActiveBees() {
		for(int i = 0; i<this.getScoutingBeesList().length; i++) {
			if(!this.getScoutingBeesList()[i].isPowerOff())
				 return true;
		}
		for(int j = 0; j<this.getFumigatorBeesList().length; j++) {
			if(!this.getFumigatorBeesList()[j].isPowerOff())
				return true;
		}
		return false;
	}
	
}
