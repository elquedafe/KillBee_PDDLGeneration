package controller.structuralElements;

/**
 * Class that represents the diferent sectors of the crop
 * @author Lukasz Marek Olszewski, Laura L�pez P�rez, �lvaro Luis Mart�nez, Miguel Lagares Velasco
 *
 */
public class Sector {
	// Class variables
	private String idSector;
	private Plant plantsSector[];
	
	/**
	 * Sector constructor
	 * @param idSector string that identity the sector
	 * @param plantsSector list with the plants within the sector
	 */
	public Sector(String idSector, Plant plantsSector[]) {
		this.idSector = idSector;
		this.plantsSector = plantsSector;
	}

	/**
	 * Method that returns string with the sector id
	 * @return string with the sector id
	 */
	public String getIdSector() {
		return idSector;
	}

	/**
	 * Method that returns array with the plants within the sector
	 * @return list with the plants of the sector
	 */
	public Plant[] getPlantsSector() {
		return plantsSector;
	}
	
}
