package fr.isima.cuicuizz.front.management;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import fr.isima.cuicuizz.front.Utils;
import fr.isima.cuicuizz.front.mode.ModeEnum;

@Component
@Qualifier("ModeManagement")
public class ModeManagement implements IManagement {

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
