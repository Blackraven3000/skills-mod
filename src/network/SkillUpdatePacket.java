package deity.skills.network;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;
import deity.skills.Skill;
import deity.skills.SkillRegistry;

//
// This packet should always be called server side
//
public class SkillUpdatePacket extends Packet {

	private EntityPlayer skillOwner;
	private String skillName;

	public SkillUpdatePacket() {
	}

	public SkillUpdatePacket(EntityPlayer player) {
		this.skillOwner = player;
	}

	public SkillUpdatePacket(EntityPlayer player, String skill) {
		this.skillOwner = player;
		this.skillName = skill;
	}

	@Override
	public void write(ByteArrayDataOutput out) {

		out.writeInt((int) skillOwner.entityId);

		if (skillName != null) {

			out.writeInt((int) 1);
			
			out.writeUTF(skillName);
			
		} else {

			out.writeInt(SkillRegistry.skillCount());
			for (String skill : SkillRegistry.getSkillNames()) {
				
				out.writeUTF(skill);
				
			}
		}
	}

	@Override
	public void read(ByteArrayDataInput in) throws ProtocolException {

		int entityId = in.readInt();

		skillOwner = (EntityPlayer)Minecraft.getMinecraft().theWorld.getEntityByID(entityId);

		int total = in.readInt();
		for (int i = 0; i < total; i++) {
			
			String name = in.readUTF();

		}
	}

	@Override
	public void execute(EntityPlayer player, Side side)
			throws ProtocolException {

	}

}
