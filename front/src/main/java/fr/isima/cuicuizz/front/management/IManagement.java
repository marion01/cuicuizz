package fr.isima.cuicuizz.front.management;

import java.io.IOException;

public interface IManagement {
	
	int choose() throws IOException;

	int handling() throws IOException;
	
	boolean handlingEntry(int id) throws IOException;
}
