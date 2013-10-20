package deity.skills;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.PacketDispatcher;
import deity.skills.network.RequestSkillUpdatePacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class SkillGui extends GuiScreen {

	int exitPosX = 0; // set in init because of width
	int exitPosY = 20;
	int exitSize = 16;

	GuiButton exit = new GuiButton(0, 0, 0, "exit");

	static final ResourceLocation resource = new ResourceLocation(ModBase.Info.ID.toLowerCase(), "textures/gui/skillgui.png");

	EntityPlayer player;

	public SkillGui(EntityPlayer player) {

		this.player = player;
		PacketDispatcher.sendPacketToServer(new RequestSkillUpdatePacket().makePacket());
	}

	@Override
	public void initGui() {
		
		exitPosX = this.width - 40;

	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void drawScreen(int x, int y, float f) {

		this.drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(resource);
		this.drawTexturedModalRect(exitPosX, exitPosY, 0, 24, exitSize, exitSize);

		int id = 1;
		for (String skill : SkillRegistry.getSkillNames()) {

			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			mc.renderEngine.bindTexture(resource);
			this.drawTexturedModalRect(20 * id, 20 * id, 0, 0, 144, 24);
			id++;
		}

		super.drawScreen(x, y, f);
	}

	@Override
	public void updateScreen() {

	}

	@Override
	protected void mouseClicked(int par1, int par2, int par3) {

		super.mouseClicked(par1, par2, par3);

		if (par3 == 0) {

			if (par1 >= exitPosX && par1 <= exitPosX + exitSize && par2 >= exitPosY && par2 <= exitPosY + exitSize) {
				
				this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
				this.actionPerformed(exit);
			}
		}
	}

	protected void actionPerformed(GuiButton guibutton) {
		
		// id is the id you give your button
		switch (guibutton.id) {
		
		default:
		case 0:
			mc.thePlayer.closeScreen();
			break;

		}
	}
}
