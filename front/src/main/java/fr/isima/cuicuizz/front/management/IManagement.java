package fr.isima.cuicuizz.front.management;

import java.io.IOException;

/**
 * Interface which handle a choice
 */
public interface IManagement {
	
	/**
	 * Display choices and recover the choice of the user
	 * 
	 * @return the choice
	 * @throws IOException
	 */
	int choose() throws IOException;

	/**
	 * Handle the choice of the user
	 * 
	 * @return the choice
	 * @throws IOException
	 */
	int handling() throws IOException;
	
	/**
	 * Check if the entry made by the user is correct
	 * 
	 * @param id
	 * @return if the entry is correct
	 * @throws IOException
	 */
	boolean handlingEntry(int id) throws IOException;
}
