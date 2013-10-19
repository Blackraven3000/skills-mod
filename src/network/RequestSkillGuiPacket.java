package deity.skills.network;

import net.minecraft.entity.player.EntityPlayer;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import deity.skills.ModBase;

//
// This packet should always be called client side
//
public class RequestSkillGuiPacket extends Packet{
	
	public RequestSkillGuiPacket() { }

	@Override
	public void write(ByteArrayDataOutput out) {
		
	}

	@Override
	public void read(ByteArrayDataInput in) throws ProtocolException {
		
	}

	@Override
	public void execute(EntityPlayer player, Side side) throws ProtocolException {
		
		if (side.isServer()) //respond to request
			PacketDispatcher.sendPacketToPlayer(new RequestSkillGuiPacket().makePacket(), (Player)player);
		else //fulfill request
			player.openGui(ModBase.instance, 0, player.getEntityWorld(), player.serverPosX, player.serverPosY, player.serverPosZ);
	}
}
