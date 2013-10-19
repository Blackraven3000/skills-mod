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
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
		proxy.postInit(event);
	}
}