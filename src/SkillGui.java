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
	
	static final ResourceLocation resource = new ResourceLocation(ModBase.Info.ID.toLowerCase(), "textures/gui/skillgui.png");
	
	EntityPlayer player;
	
	public SkillGui(EntityPlayer player) {
		
		this.player = player;
		PacketDispatcher.sendPacketToServer(new RequestSkillUpdatePacket().makePacket());
	}
	
	@Override
	public void initGui() {
		
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void drawScreen(int x, int y, float f) {
		
		this.drawDefaultBackground();
		
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

    protected void actionPerformed(GuiButton guibutton) {
            //id is the id you give your button
            switch(guibutton.id) {
            default: break;
            
            }
    }
}
