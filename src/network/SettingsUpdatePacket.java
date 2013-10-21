package deity.skills.network;

import net.minecraft.entity.player.EntityPlayer;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;

public class SettingsUpdatePacket extends Packet {
	
	public SettingsUpdatePacket() {
	}
	
	public SettingsUpdatePacket(EntityPlayer player) {
		
	}
	
	public SettingsUpdatePacket(EntityPlayer player, String skill) {
		
	}

	@Override
	public void write(ByteArrayDataOutput out) {
		
	}

	@Override
	public void read(ByteArrayDataInput in) throws ProtocolException {
		
	}

	@Override
	public void execute(EntityPlayer player, Side side) throws ProtocolException {
		
	}
}
