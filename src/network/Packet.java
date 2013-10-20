package deity.skills.network;

import net.minecraft.entity.player.EntityPlayer;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import deity.skills.ModBase;

public abstract class Packet {

	private static BiMap<Integer, Class<? extends Packet>> packets;

	static {
		ImmutableBiMap.Builder<Integer, Class<? extends Packet>> builder = ImmutableBiMap.builder();

		builder.put(Integer.valueOf(0), RequestSkillGuiPacket.class);
		builder.put(Integer.valueOf(1), RequestSkillUpdatePacket.class);
		builder.put(Integer.valueOf(2), SkillUpdatePacket.class);

		packets = builder.build();
	}

	public static Packet construct(int packetID) throws ProtocolException, ReflectiveOperationException {

		Class<? extends Packet> clazz = packets.get(Integer.valueOf(packetID));

		if (clazz != null)
			return clazz.newInstance();

		throw new ProtocolException("Unknown Packet ID");

	}

	public static class ProtocolException extends Exception {

		public ProtocolException() {
		}

		public ProtocolException(String message, Throwable cause) {
			super(message, cause);
		}

		public ProtocolException(String message) {
			super(message);
		}

		public ProtocolException(Throwable cause) {
			super(cause);
		}
	}

	public final int getPacketID() {

		if (packets.inverse().containsKey(getClass()))
			return packets.inverse().get(getClass()).intValue();

		throw new RuntimeException("Packet" + getClass().getSimpleName() + " is not registered.");
	}

	public final net.minecraft.network.packet.Packet makePacket() {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeByte(getPacketID());
		write(out);

		return PacketDispatcher.getPacket(ModBase.Info.CHANNEL, out.toByteArray());
	}

	public abstract void write(ByteArrayDataOutput out);

	public abstract void read(ByteArrayDataInput in) throws ProtocolException;

	public abstract void execute(EntityPlayer player, Side side) throws ProtocolException;
}