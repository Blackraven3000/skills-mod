package deity.skills;

import java.util.HashMap;

import com.google.common.collect.HashBiMap;

import cpw.mods.fml.common.FMLLog;

public class SkillRegistry {
	
	/*
	 *  BiMap Values cannot be the same or it will crash -
	 *  - have to use a HashMap for base registry.
	 */
	static HashMap<String, Class<? extends Skill>> skills = new HashMap();
	static HashMap<String, Settings> settings = new HashMap();
	
	public static void register(Skill skill) {
		
		register(skill, new Settings());
	}
	
	public static void register(Skill skill, Settings settings) {
		
	}
}