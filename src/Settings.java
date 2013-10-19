package deity.skills;

import net.minecraft.util.Icon;

public class Settings {
	
	protected boolean enabled = true;
	
	protected Icon skillIcon;
	
	protected int maxLevel = 99;
	protected int maxSoftLevel = 120;
	
	protected int baseLevelAt = 16;
	protected int[] levelAtTable = new int[maxLevel];	
	
	protected boolean doLevelingEffects = true;
	protected boolean doLevelingMessage = true;
	
	public final boolean enabled() {
		return this.enabled;
	}
	
	public Settings setEnabled(boolean able) {
		this.enabled = able;
		return this;
	}

	public final Icon getIcon() {
		return this.skillIcon;
	}

	public Settings setIcon(Icon icon) {
		this.skillIcon = icon;
		return this;
	}

	public final int getMaxLevel() {
		return this.maxLevel;
	}

	public Settings setMaxLevel(int level) {
		this.maxLevel = level;
		if (this.maxLevel >= this.maxSoftLevel)
			this.maxSoftLevel = this.maxLevel + 1;

		return this;
	}

	public final int getMaxSoftLevel() {
		return this.maxSoftLevel;
	}

	public Settings setMaxSoftLevel(int level) {
		this.maxSoftLevel = level;
		if (this.maxSoftLevel <= this.maxLevel)
			this.maxLevel = this.maxSoftLevel - 1;

		return this;
	}

	public final int getBaseLevelAt() {
		return this.baseLevelAt;
	}

	public Settings setBaseLevelAt(int levelAt) {
		this.baseLevelAt = levelAt;
		genLevelAtTable();
		return this;
	}

	public final boolean getDoLevelingEffects() {
		return this.doLevelingEffects;
	}

	public Settings setDoLevelingEffects(boolean value) {
		this.doLevelingEffects = value;
		return this;
	}

	public final boolean getDoLevelingMessage() {
		return this.doLevelingMessage;
	}

	public Settings setDoLevelingMessage(boolean value) {
		this.doLevelingMessage = value;
		return this;
	}
	
	public Settings() {	}
	
	public void genLevelAtTable() {
		// LLD = Last Level Difference
		// LDD = Last Difference Difference
		double LLD = 0, LDD = 0;

		int[] temp = new int[this.maxLevel];
		for (int i = 0; i < temp.length; i++) {	
			
			if (i <= 0) {				
				temp[i] = this.baseLevelAt;
				LLD = this.baseLevelAt;
			} else {				
				LDD += (i * 2) + (i + 1) * 1.125;
				LLD += LDD;

				temp[i] = (int) (Math.round((temp[i - 1] + LLD)));
			}
		}

		this.levelAtTable = temp;
	}
}