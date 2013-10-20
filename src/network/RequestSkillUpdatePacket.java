package deity.skills.network;

import net.minecraft.entity.player.EntityPlayer;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class RequestSkillUpdatePacket extends Packet {

	public RequestSkillUpdatePacket() {
	}

	@Override
	public void write(ByteArrayDataOutput out) {

	}

	@Override
	public void read(ByteArrayDataInput in) throws ProtocolException {

	}

	@Override
	public void execute(EntityPlayer player, Side side) throws ProtocolException {

		if (side.isServer())
			PacketDispatcher.sendPacketToPlayer(new SkillUpdatePacket(player).makePacket(), (Player)player);
	}
}