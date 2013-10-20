package deity.skills.handlers;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.IGuiHandler;
import deity.skills.SkillGui;

public class Gui implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		switch (ID) {

		case 0: {

			if (Minecraft.getMinecraft().currentScreen instanceof SkillGui) {
				player.closeScreen();
				return null;
			}

			return new SkillGui(player);
		}
		default:
			return null;

		}
	}
}