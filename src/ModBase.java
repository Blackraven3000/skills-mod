package deity.skills;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import deity.skills.handlers.EventSubs;
import deity.skills.handlers.KeyPress;
import deity.skills.handlers.Packets;

@Mod(modid = ModBase.Info.ID, name = ModBase.Info.NAME, version = ModBase.Info.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = true, channels = { ModBase.Info.CHANNEL, }, packetHandler = Packets.class)
public class ModBase {

	public class Info {
		public static final String ID = "DZSM";
		public static final String NAME = "Deit Zeke's Skill Mod";
		public static final String VERSION = "0.0.0.6";
		public static final String CHANNEL = ID;
	}
	
	static final Skill mySkill00 = new Skill().setName("00");
	static final Skill mySkill01 = new Skill().setName("01");
	static final Skill mySkill02 = new Skill().setName("02");
	static final Skill mySkill03 = new Skill().setName("03");
	static final Skill mySkill04 = new Skill().setName("04");
	static final Skill mySkill05 = new Skill().setName("05");
	static final Skill mySkill06 = new Skill().setName("06");
	static final Skill mySkill07 = new Skill().setName("07");
	static final Skill mySkill08 = new Skill().setName("08");
	static final Skill mySkill09 = new Skill().setName("09");
	static final Skill mySkill0A = new Skill().setName("0A");
	static final Skill mySkill0B = new Skill().setName("0B");
	static final Skill mySkill0C = new Skill().setName("0C");
	static final Skill mySkill0D = new Skill().setName("0D");
	static final Skill mySkill0E = new Skill().setName("0E");
	static final Skill mySkill0F = new Skill().setName("0F");
	static final Skill mySkill10 = new Skill().setName("10");
	static final Skill mySkill11 = new Skill().setName("11");
	static final Skill mySkill12 = new Skill().setName("12");
	static final Skill mySkill13 = new Skill().setName("13");

	@Instance
	public static ModBase instance;
	
	@SidedProxy(clientSide = "deity.skills.ClientProxy", serverSide = "deity.skills.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		
		proxy.init(event);

		MinecraftForge.EVENT_BUS.register(new EventSubs());
		NetworkRegistry.instance().registerGuiHandler(this, new deity.skills.handlers.Gui());
		
		SkillRegistry.register(mySkill00);
		SkillRegistry.register(mySkill01);
		SkillRegistry.register(mySkill02);
		SkillRegistry.register(mySkill03);
		SkillRegistry.register(mySkill04);
		SkillRegistry.register(mySkill05);
		SkillRegistry.register(mySkill06);
		SkillRegistry.register(mySkill07);
		SkillRegistry.register(mySkill08);
		SkillRegistry.register(mySkill09);
		SkillRegistry.register(mySkill0A);
		SkillRegistry.register(mySkill0B);
		SkillRegistry.register(mySkill0C);
		SkillRegistry.register(mySkill0D);
		SkillRegistry.register(mySkill0E);
		SkillRegistry.register(mySkill0F);
		SkillRegistry.register(mySkill10);
		SkillRegistry.register(mySkill11);
		SkillRegistry.register(mySkill12);
		SkillRegistry.register(mySkill13);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
		proxy.postInit(event);
		FMLLog.info("SKILLS REGISTERED: " + SkillRegistry.skillCount(), this);
	}
}