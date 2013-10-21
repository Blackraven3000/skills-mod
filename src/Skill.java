package deity.skills;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import deity.skills.network.SettingsUpdatePacket;
import deity.skills.network.SkillUpdatePacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class Skill implements IExtendedEntityProperties {

	@Override
	public void init(Entity entity, World world) {	
		
	}
	
	protected Settings settings;

	protected EntityPlayer owner;

	protected String name;

	protected int curLevel = 1;
	protected int curSoftLevel = 1;

	protected int curExp = 0;
	protected int totExp = 0;
	
	private void settingsSaftey() {

		if (settings == null)
			settings = SkillRegistry.getSettings(getName());
	}
	
	public Skill setOwner(EntityPlayer player) {
		
		if (owner == null)
			owner = player;
		
		return this;
	}
	
	public final EntityPlayer getOwner() {
		return this.owner;
	}
	
	public Skill setEnabled(boolean abled) {
		
		settingsSaftey();
		settings.enabled = abled;
		syncSettings();
		return this;
	}
	
	public final boolean isEnabled() {
		
		settingsSaftey();
		return settings.enabled;
	}
	
	public Skill setIcon(String texture) {
		
		settingsSaftey();
		settings.Icon = texture;
		syncSettings();
		return this;
	}
	
	public final String getIcon() {
		
		settingsSaftey();
		return settings.Icon;
	}

	public Skill setName(String skill) {

		if (name == null || name.isEmpty())
			name = skill;

		return this;
	}

	public final String getName() {

		if (settings != null) {
			if (settings.skillName != null)
				return settings.skillName;
		}

		if (name != null)
			return StatCollector.translateToLocal(name);

		return "Skill Name Error";
	}

	public final String getUnlocalizedName() {
		return ("skill." + name);
	}
	
	public Skill setSoftLevel(int level) {
		
		this.curSoftLevel = level;
		syncSkill();
		return this;
	}
	
	public final int getSoftLevel() {
		return this.curSoftLevel;
	}
	
	public Skill setMaxSoftLevel(int level) {
		
		settingsSaftey();
		settings.maxSoftLevel = level;
		syncSettings();
		return this;
	}
	
	public final int getMaxSoftLevel() {
		
		settingsSaftey();
		return settings.maxSoftLevel;
	}
	
	public Skill setLevel(int level) {
		
		this.curLevel = level;
		syncSkill();
		return this;
	}
	
	public final int getLevel() {
		return this.curLevel;
	}
	
	public Skill setMaxLevel(int level) {
		
		settingsSaftey();
		settings.maxLevel = level;
		syncSettings();
		return this;
	}
	
	public final int getMaxLevel() {
		
		settingsSaftey();
		return settings.maxLevel;
	}
	
	public final int getExp() {
		return this.curExp;
	}
	
	public final int getTotExp() {
		return this.totExp;
	}
	
	public Skill setBaseLevelAt(int base) {
		
		settingsSaftey();
		settings.baseLevelAt = base;
		settings.genLevelAtTable();
		syncSettings();
		return this;
	}
	
	public final int getLevelAt() {
		
		settingsSaftey();
		return settings.levelAtTable[getLevel() - 1];
	}
	
	public Skill setDoLevelingEffects(boolean able) {
		
		settingsSaftey();
		settings.doLevelingEffects = able;
		syncSettings();
		return this;
	}
	
	public final boolean doLevelingEffects() {
		
		settingsSaftey();
		return settings.doLevelingEffects;
	}
	
	public Skill setDoLevelingMessage(boolean able) {
		
		settingsSaftey();
		settings.doLevelingMessage = able;
		syncSettings();
		return this;
	}
	
	public final boolean doLevelingMessage() {
		
		settingsSaftey();
		return settings.doLevelingMessage;
	}
	
	public Skill() {
	}
	
	private void syncSkill() {
		PacketDispatcher.sendPacketToPlayer(new SkillUpdatePacket(owner, getName()).makePacket(), (Player)owner);
	}
	
	private void syncSettings() {
		PacketDispatcher.sendPacketToPlayer(new SettingsUpdatePacket(owner, getName()).makePacket(), (Player)owner);
	}
	
	public static Skill getSkill(EntityPlayer player, String name) {
		return (Skill) player.getExtendedProperties(ModBase.Info.ID + name.toLowerCase());		
	}

	public void addExp(int value) {

		// Over-Exp At-Cap Filter
		if ((this.curExp + value) > getLevelAt()
				&& this.curLevel >= getMaxLevel())
			value = value - ((this.curExp + value) - getLevelAt());

		if (value <= 0)
			return;

		this.owner.addChatMessage("You gained " + value + " experience in " + getName());

		this.totExp += value;

		int tempExp = this.curExp + value;
		int tempLvl = this.curLevel;
		int timesLvld = 0;

		do {
			if (tempLvl >= getMaxLevel()) {
				tempExp = tempExp - (tempExp - getLevelAt());
				return;
			}

			tempExp = tempExp - getLevelAt();
			tempLvl++;
			timesLvld++;
		} while (tempExp >= getLevelAt());

		this.curExp = tempExp;
		LevelUp(timesLvld);
		syncSkill();
	}

	private final void LevelUp(int timesLvld) {
		if (this.doLevelingEffects())
			LevelEffects();

		this.curLevel += timesLvld;
		for (int i = 0; i < timesLvld; i++)
			OnLevel();

		if (this.doLevelingMessage())
			this.owner.addChatMessage("Your " + getName() + " skill has increased" + ((timesLvld > 1) ? (" by " + timesLvld) : ""));
	}

	protected void LevelEffects() {

	}

	protected void OnLevel() {
		GiveBonuses();
	}

	protected void GiveBonuses() {

	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		
	}
}