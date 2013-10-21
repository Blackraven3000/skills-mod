package deity.skills;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.PacketDispatcher;
import deity.skills.network.RequestSkillUpdatePacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class SkillGui extends GuiScreen {

	static final ResourceLocation resource = new ResourceLocation(ModBase.Info.ID.toLowerCase(), "textures/gui/skillgui.png");

	int tabWidth = 128, tabHeight = 32;

	int exitPosX = 0, exitPosY = 2;
	int prevPagePosX = 0, prevPagePosY = 0;
	int nextPagePosX = 0, nextPagePosY = 0;

	int tabRows = 0, tabColumns = 0;
	int tabXSpace = 0, tabYSpace = 0;

	GuiButton exit = new GuiButton(0, 0, 0, "exit");
	GuiButton nextPage = new GuiButton(1, 0, 0, "next page");
	GuiButton prevPage = new GuiButton(2, 0, 0, "prev page");

	EntityPlayer player;

	public SkillGui(EntityPlayer player) {

		this.player = player;
		PacketDispatcher.sendPacketToServer(new RequestSkillUpdatePacket().makePacket());
	}

	@Override
	public void initGui() {

		exitPosX = this.width - 18;

		tabRows = (int) (this.width / tabWidth);
		tabColumns = (int) (this.height / tabHeight) - 1;

		tabXSpace = (int) (this.width - (tabRows * tabWidth)) / tabRows;
		tabYSpace = (int) (this.height - ((tabColumns + 1) * tabHeight)) / (tabColumns + 1);
		
		FMLLog.info(" SKILL TABS [" + tabRows + "x" + tabColumns + "]", this);
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
		
		//exit button
		this.drawTexturedModalRect(exitPosX, exitPosY, tabWidth, 16, 16, 16);

		int tabX = 1, tabY = 1;
		for (String name : SkillRegistry.getSkillNames()) {

			if (tabY <= tabColumns)
			{				
				int xDiff = ((tabX - 1) * tabWidth) + ((tabX - 1) * tabXSpace);
				int yDiff = ((tabY - 1) * tabHeight) + ((tabY - 1) * tabYSpace);

				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				mc.renderEngine.bindTexture(resource);
				
				Skill skill = Skill.getSkill(player, name);
				
				//background
				this.drawTexturedModalRect((tabXSpace / 2) + xDiff, 20 + yDiff, 0, 0, tabWidth, tabHeight);
				
				//icon
				mc.renderEngine.bindTexture(new ResourceLocation(skill.getIcon()));
				drawTexture((tabXSpace / 2) + xDiff + 8, 20 + yDiff + 8, 16, 16);
				
				//name
				this.fontRenderer.drawString(skill.getName(), (tabXSpace / 2) + xDiff + 8, 18 + yDiff, 0xFFFF55);
				
				//level
				this.fontRenderer.drawString("999 / 999", (tabXSpace / 2) + xDiff + 30, 20 + yDiff + 9, 0xFFFFFF);
				
				//exp
				//this.fontRenderer.drawString("999,999,999", (tabXSpace / 2) + xDiff + 29, 20 + yDiff + 19, 0xFFFFFF);
				mc.renderEngine.bindTexture(resource);
				this.drawTexturedModalRect( (tabXSpace / 2) + xDiff + 29,  20 + yDiff + 19, tabWidth, 0, 93, 8);
				
				tabX++;

				if (tabX > tabRows)
				{
					tabX = 1;
					tabY++;
				}
			}
		}

		super.drawScreen(x, y, f);
	}
	
	public void drawTexture(int x, int y, int w, int h)
	{
	    GL11.glColor4f(1F, 1F, 1F, 1F);
	    Tessellator tessellator = Tessellator.instance;
	    tessellator.startDrawingQuads();
	    tessellator.addVertexWithUV(x + 0, y + h, this.zLevel, 0D, 1D);
	    tessellator.addVertexWithUV(x + w, y + h, this.zLevel, 1D, 1D);
	    tessellator.addVertexWithUV(x + w, y + 0, this.zLevel, 1D, 0D);
	    tessellator.addVertexWithUV(x + 0, y + 0, this.zLevel, 0D, 0D);
	    tessellator.draw();
	}

	@Override
	public void updateScreen() {

	}

	@Override
	protected void mouseClicked(int par1, int par2, int par3) {

		super.mouseClicked(par1, par2, par3);

		if (par3 == 0) {

			if (par1 >= exitPosX && par1 <= exitPosX + 16 && par2 >= exitPosY && par2 <= exitPosY + 16) {

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
