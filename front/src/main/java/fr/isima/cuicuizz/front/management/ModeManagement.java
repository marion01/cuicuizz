package fr.isima.cuicuizz.front.management;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import fr.isima.cuicuizz.front.Utils;
import fr.isima.cuicuizz.front.mode.ModeEnum;

/**
 * Class which handle the mode choice
 */
@Component
@Qualifier("ModeManagement")
public class ModeManagement implements IManagement {

	/**
	 * Handle the choice of mode made by the user
	 */
	@Override
	public int handling() throws IOException {
		boolean falseEntry = true;
		int idMode = -1;
		while (falseEntry) {
			idMode = choose();
			falseEntry = !handlingEntry(idMode);
		}
		return idMode;
	}

	/**
	 * Display the modes to choose and recover the entry of the user
	 */
	@Override
	public int choose() throws IOException {
		for (int index = 0; index < ModeEnum.values().length; index++) {
			final ModeEnum mode = ModeEnum.getById(index);
			System.out.println(index + "." + mode.getName());
		}
		System.out.println(ModeEnum.values().length + ".Back");
		System.out.println("Enter the number of the mode:");
		final int i = Utils.readEntryNumber();
		return i;
	}

	/**
	 * Check if the entry made is correct
	 * @return false if the entry isn't correct true otherwise
	 */
	@Override
	public boolean handlingEntry(int idMode) throws IOException {
		boolean correctEntry = false;
		if (idMode <= ModeEnum.values().length) {
			correctEntry = true;
		} else {
			System.out.println("incorrect entry");
		}
		return correctEntry;
	}
}
