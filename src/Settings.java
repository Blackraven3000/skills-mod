package deity.skills;

import net.minecraft.util.Icon;

public class Settings {
	
	protected boolean enabled = true;
	
	protected Icon skillIcon;
	protected final String skillName;
	
	protected int maxLevel = 99;
	protected int maxSoftLevel = 120;
	
	protected int baseLevelAt = 16;
	protected int[] levelAtTable = new int[maxLevel];	
	
	protected boolean doLevelingEffects = true;
	protected boolean doLevelingMessage = true;
	
	public Settings(String name) {
		
		this.skillName = name;
	}
}