package deity.skills;

import java.util.HashMap;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import cpw.mods.fml.common.FMLLog;

public class SkillRegistry {
	
	/*
	 *  BiMap Values cannot be the same or it will crash -
	 *  - have to use a HashMap for base registry.
	 */
	static HashMap<String, Class<? extends Skill>> skills = new HashMap();
	
	public static void register(String name, Skill skill) {
		FMLLog.info("REGISTERING " + name + " @ " + skill.getClass(), ModBase.instance);
		skills.put(name, skill.getClass());
	}
	
	public static void post() {
		
		FMLLog.info("SKILLS REGISTERED (" + skills.size() + ")", ModBase.instance);
		for (String skill : skills.keySet()) {
			FMLLog.info("SKILL " + skill + " - " + skills.get(skill), ModBase.instance);
		}
	}
}