package structuralElements;

import perception.InfectionDetection;

/**
 * Class that represents the plants of the crop
 * @author Lukasz Marek Olszewski, Laura L�pez P�rez, �lvaro Luis Mart�nez, Miguel Lagares Velasco
 *
 */
public class Plant {
	// Class variables
	private String idPlant;
	private String idSector;
	private boolean analyzed;
	private boolean infected;
	
	/**
	 * Plant constructor 
	 * @param idPlant string that identify the plant
	 * @param idSector string that identify the sector where is the plant
	 * @param analyzed boolean that inform if the plant has been analyzed
	 * @param infected boolean that inform if the plant is infected
	 */
	public Plant(String idPlant, String idSector, boolean analyzed, boolean infected) {
		this.idPlant = idPlant;
		this.idSector = idSector;
		this.analyzed = analyzed;
		this.infected = infected;
	}

	/**
	 * Method that eturns string with the plant id
	 * @return string with the plat id
	 */
	public String getIdPlant() {
		return idPlant;
	}

	/**
	 * Method that returns array with the plants within the sector
	 * @return string with the sector id
	 */
	public String getIdSector() {
		return idSector;
	}
	
	/**
	 * Method that returns information about the analysis of the plant
	 * @return true if the plant was analyzed or false if not
	 */
	public boolean isAnalyzed() {
		return analyzed;
	}

	/**
	 * Method that returns information about the infection possibility of the plant
	 * @return true if the plant is infected or false if not
	 */
	public boolean isInfected() {
		return infected;
	}

	/**
	 * Method that allow change the analysis information of the plant
	 * @param analyzed true if the plant was analyzed or false if not
	 */
	public void setAnalyzed(boolean analyzed) {
		this.analyzed = analyzed;
	}

	/**
	 * Method that allow change the infection information of the plant
	 * @param infected true if the plant is infected or false if not
	 */
	public void setInfected(boolean infected) {
		this.infected = infected;
	}

	/**
	 * Method that simulate plant anlyzation
	 * @param time that costs the execution of the action
	 * @return true if the plant is infected
	 * @throws InterruptedException 
	 */
	public void analyzePlant(int time) throws InterruptedException {
		Thread.sleep(time);
		this.infected = InfectionDetection.isInfected(this);
	}
	
	/**
	 * Method that simulate plant fumigation
	 * @param time that costs the execution of the action
	 * @throws InterruptedException 
	 */
	public void fumigatePlant(int time) throws InterruptedException {
		Thread.sleep(time);
	}
	
}
