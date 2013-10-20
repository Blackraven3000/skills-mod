package deity.skills;

import java.util.Locale;

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

		this.skillName = name.toLowerCase(Locale.ENGLISH);
	}

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