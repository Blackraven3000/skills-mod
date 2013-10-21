package deity.skills.handlers;

import deity.skills.SkillRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent;

public class EventSubs {

	@ForgeSubscribe
	public void entityConstruction(EntityEvent.EntityConstructing event) {
		
		if (event.entity instanceof EntityPlayer)
			SkillRegistry.registerPlayerSkills((EntityPlayer)event.entity);
	}
}