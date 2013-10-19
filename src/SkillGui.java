package deity.skills;

import cpw.mods.fml.common.network.PacketDispatcher;
import deity.skills.network.RequestSkillUpdatePacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;

public class SkillGui extends GuiScreen {
	
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
