package fr.isima.cuicuizz.front.mode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public enum ModeEnum{
	
	SPEED(0, "Speed", Speed.class),
	NORMAL(1, "Normal", Normal.class),
	DUEL(2, "Duel", Duel.class);
	
	private String name;
	private int id;
	Class<?> clazz;
	private IMode instance;
	
	ModeEnum(int pId, String pName, Class<?> pClazz) {
		this.id = pId;
		this.name = pName;
		clazz = pClazz;
		instance = null;
	}
	
	/**
	 * Return a mode matching an id
	 * 
	 * @param id
	 * 		id to find
	 * @return
	 * 		the mode
	 */
	public static ModeEnum getById(int id){
		for (ModeEnum mode : ModeEnum.values()) {
            if (mode.id == id) {
            	return mode;
            }
        }
		return null;
		
	}
	
	public String getName() {
		return name;
	}
	
	public IMode getInstance() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Class<?> c = Class.forName(clazz.getName());
		Method method = c.getDeclaredMethod("getInstance");
		return (IMode) method.invoke(c);
	}

}
