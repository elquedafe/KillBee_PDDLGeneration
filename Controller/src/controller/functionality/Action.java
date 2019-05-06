package controller.functionality;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that represents the actions that are going to berform the bees and the bee hive
 * @author Lukasz Marek Olszewski, Laura L�pez P�rez, �lvaro Luis Mart�nez, Miguel Lagares Velasco
 *
 */
public class Action {
	// Class variables
	private String actionName;
	private String actionParameters[];
	
	/**
	 * Action constructor
	 * @param actionName String that identify the name of the action
	 * @param actionParameters array string that contains the parameters of the action
	 */
	public Action(String actionName, String actionParameters[]) {
		this.actionName = actionName;
		this.actionParameters = actionParameters;
	}

	/**
	 * Method that returns the name of the action
	 * @return action name
	 */
	public String getActionName() {
		return actionName;
	}

	/**
	 * Method that returns array string with the parameters of the action
	 * @return action parameters
	 */
	public String[] getActionParameters() {
		return actionParameters;
	}
	
	/**
	 * Method that returns the scouting bee id that is going to perform the action
	 * @return scouting bee id
	 */
	public String getScoutingBeeId() { 
		Pattern p = Pattern.compile("tr[0-9]*");
		String scoutingBeeId = null;
		
		for(int i=0; i<this.actionParameters.length; i++) {
			Matcher m = p.matcher(actionParameters[i]);
				if(m.find()) {
					scoutingBeeId = actionParameters[i];
				}
		}
		
		return scoutingBeeId;
	}
	
	/**
	 * Method that returns the fumigator bee id that is going to perform the action
	 * @return fumigator bee id
	 */
	public String getFumigatorBeeId() { 
		Pattern p = Pattern.compile("fum[0-9]*");
		String fumigatorBeeId = null;
		
		for(int i=0; i<this.actionParameters.length; i++) {
			Matcher m = p.matcher(actionParameters[i]);
				if(m.find()) {
					fumigatorBeeId = actionParameters[i];
				}
		}
		
		return fumigatorBeeId;
	}
	
	/**
	 * Method that returns the sector id where the action is going to perform
	 * @return sector id
	 */
	public String getSectorId() { 
		Pattern p = Pattern.compile("s[0-9]*");
		String sectorId = null;
		
		for(int i=0; i<this.actionParameters.length; i++) {
			Matcher m = p.matcher(actionParameters[i]);
				if(m.find()) {
					sectorId = actionParameters[i];
				}
		}
		
		return sectorId;
	}
	
	/**
	 * Method that returns the plant id that is going to receive the action
	 * @return plant id
	 */
	public String getPlantId() { 
		Pattern p = Pattern.compile("tr[0-9]*");
		String plantId = null;
		
		for(int i=0; i<this.actionParameters.length; i++) {
			Matcher m = p.matcher(actionParameters[i]);
				if(m.find()) {
					plantId = actionParameters[i];
				}
		}
		
		return plantId;
	}
	
	/**
	 * Method that returns the time that costs the execution of the action
	 * @return time that costs the execution of the action
	 */
	public int getActionTimeExecution() { 
		Pattern p = Pattern.compile("[0-9]*");
		int timeExecution = 0;
		
		for(int i=0; i<this.actionParameters.length; i++) {
			Matcher m = p.matcher(actionParameters[i]);
				if(m.find()) {
					timeExecution = Integer.parseInt(actionParameters[i]);
				}
		}
		
		return timeExecution;
	}
	
	/**
	 * Method that returns the agent id who performs the action
	 * @return agent who performs the action
	 */
	public String getAgent() {
		return actionParameters[0];
	}
}
