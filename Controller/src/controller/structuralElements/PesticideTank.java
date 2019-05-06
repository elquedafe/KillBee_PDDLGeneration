package controller.structuralElements;

/**
 * Class that represents the pesticide tank of the fumigator bees, it allows the filling of the
 * pesticide tank and know its state
 * @author Lukasz Marek Olszewski, Laura L�pez P�rez, �lvaro Luis Mart�nez, Miguel Lagares Velasco
 *
 */
public class PesticideTank {
	// Class variables
	private boolean fullTank;
	
	/**
	 * Pesticide tank constructor
	 * @param fullTank boolean that inform about the state of the pesticide tank
	 */
	public PesticideTank(boolean fullTank) {
		this.fullTank = fullTank;
	}
	
	/**
	 * Method that returns the state of the fumigator bee tank
	 * @return boolean with the state of the tank
	 */
	public boolean isFullTank() {
		return fullTank;
	}

	/**
	 * Method that allow change the state of the fumigator bees tank
	 * @param fullTank true if the tank is full or false if it is empty
	 */
	public void setFullTank(boolean fullTank) {
		this.fullTank = fullTank;
	}
	
}
