package deity.skills;

import java.util.Locale;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Icon;
import net.minecraft.util.StatCollector;

public class Skill {

	protected Settings settings;

	protected String name;

	protected int curLevel = 1;
	protected int curSoftLevel = 1;

	protected int curExp = 0;
	protected int totExp = 0;
	
	private void settingsSaftey() {

		if (settings == null)
			settings = SkillRegistry.getSettings(getName());
	}
	
	public Skill setEnabled(boolean abled) {
		
		settingsSaftey();
		settings.enabled = abled;
		return this;
	}
	
	public final boolean isEnabled() {
		
		settingsSaftey();
		return settings.enabled;
	}

	public Skill setName(String skill) {

		if (name == null || name.isEmpty()) {
			name = skill;
			settings = SkillRegistry.getSettings(skill);
		}

		return this;
	}

	public final String getName() {

		if (settings != null) {
			if (settings.skillName != null)
				return settings.skillName;
		}

		if (name != null)
			return StatCollector.translateToLocal("skill." + name);

		return "Skill Name Error";
	}

	public final String getUnlocalizedName() {
		return ("skill." + name);
	}
	
	public Skill() {
	}

}