package perception;

import structuralElements.Plant;

public class InfectionDetection {
	
	/**
	 * Determines whether a plant is infected or not. If a random number [0-1) is less than 0.3 is infected, if it is greater or equal to 0.3 is not infected.
	 * @param plant Plant to analyze
	 * @return
	 */
	public static boolean isInfected(Plant plant) {
		if(Math.random() < 0.3) {
			return true;
		}
		else {
			return false;
		}
	}

}
