package deity.skills;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import net.minecraft.entity.player.EntityPlayer;

import com.google.common.collect.HashBiMap;

import cpw.mods.fml.common.FMLLog;

public class SkillRegistry {

	static HashMap<String, Class<? extends Skill>> skills = new HashMap();
	static HashMap<String, Settings> settings = new HashMap();

	public static int skillCount() {
		return skills.size();
	}

	public static void register(Skill skill) {

		register(skill, new Settings(skill.getName()));
	}
	
	public static void register(Skill skill, Settings sets) {
		
		if (skills.containsKey(skill)) {
			//error
			return;
		}

		skills.put(skill.getName().toLowerCase(), skill.getClass());
		
		if (settings.containsKey(skill.getName().toLowerCase())) {
			//error
			return;
		}
		
		settings.put(skill.getName().toLowerCase(), sets);			
	}

	public static void registerPlayerSkills(EntityPlayer player) {

		for (String name : getSkillNames()) {

			if (Skill.getSkill(player, name) != null)
				continue;

			try {
				
				Skill instance = skills.get(name).newInstance().setName(name).setOwner(player);
				player.registerExtendedProperties(ModBase.Info.ID + name, instance);
				
			} catch (InstantiationException e) {
				FMLLog.severe(e.toString(), ModBase.instance);
			} catch (IllegalAccessException e) {
				FMLLog.severe(e.toString(), ModBase.instance);
			}
		}
	}

	public static List<String> getSkillNames() {

		List<String> skillz = new ArrayList<String>(skills.size());
		for (String skill : skills.keySet())
			skillz.add(skill);

		return skillz;
	}

	public static Settings getSettings(String skill) {

		if (!settings.containsKey(skill))
			settings.put(skill, new Settings(skill));

		return settings.get(skill);
	}
}