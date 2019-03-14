package fr.isima.cuicuizz.front;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.stereotype.Controller;

import fr.isima.cuicuizz.front.mode.ModeEnum;

@Controller
public class ModeManagement {

	public void modeChoose() throws IOException {
		for (int index = 0; index < ModeEnum.values().length; index++) {
			final ModeEnum mode = ModeEnum.getById(index);
			System.out.println(index + "." + mode.getName());
		}
		System.out.println(ModeEnum.values().length + ".Back");
		System.out.println("Enter the number of the mode:");
		final String i = Application.game.readEntry();
		if (Integer.parseInt(i) < ModeEnum.values().length) {
			final ModeEnum mode = ModeEnum.getById(Integer.parseInt(i));
			final List<Question> questions = Application.game.chooseThemeAndNumberQuestion();
			try {
				mode.getInstance().execute(questions);
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		} else {
			if (Integer.parseInt(i) == ModeEnum.values().length) {
				Application.game.menu();
			} else {
				System.out.println("incorrect entry");
				modeChoose();
			}
		}
		System.out.println();
	}
}
