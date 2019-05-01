package structuralElements;

/**
 * Class that represents the crop that is going to use our system
 * @author Lukasz Marek Olszewski, Laura López Pérez, Álvaro Luis Martínez, Miguel Lagares Velasco
 *
 */
public class Crop {
	// Class variables
	private Sector[] sectors;
	
	/**
	 * Crop Constructor
	 * @param numSectors number of sectors that is going to have the crop
	 * @param numPlantsSector numer of plants that is going to have each sector
	 */
	public Crop(int numSectors, int numPlantsSector) {
		// Creation of the sectors of the crop
		for(int i=1; i<=numSectors; i++) {
			Plant plants[] = new Plant[numPlantsSector];
			//Creation of the plants that have each sector
			for(int j=1; j<=numPlantsSector; j++) {
				Plant plant = new Plant("p"+numPlantsSector, "s"+numSectors, false, true);
				plants[j-1] = plant;
			}
			
			Sector sector = new Sector("s"+numSectors, plants);
			sectors[i-1] = sector;
		}
	}

	
	/**
	 * Method that returns the sectors that have the crop
	 * @return list with the sectors of the crop
	 */
	public Sector[] getSectors() {
		return sectors;
	}
	
	/**
	 * Method that returns information about the infection possibility of the sectors
	 * @return true if there are sectors infected false if not
	 */
	public boolean thereAreInfectedSectors() {
		boolean infectedSector = false;
		boolean infectedSectors = false;
		
		for(int i = 0; i<this.getSectors().length; i++) {
			for(int j = 0; j<this.getSectors()[i].getPlantsSector().length; i++) {
				if(this.getSectors()[i].getPlantsSector()[j].isInfected())
					infectedSector = true;
			}
			if(infectedSector)
				infectedSectors = true;
		}
		
		return infectedSectors;
	}
	
}
