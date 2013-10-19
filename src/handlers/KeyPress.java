package deity.skills.handlers;

import java.util.EnumSet;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import deity.skills.network.RequestSkillGuiPacket;

public class KeyPress extends KeyHandler {

	public EnumSet tick = EnumSet.of(TickType.CLIENT);
	public static boolean pressed = false;

	public KeyPress(KeyBinding[] keyBindings, boolean[] repeatings) {
		super(keyBindings, repeatings);
	}

	@Override
	public String getLabel() {
		return "SkillBind";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {

		pressed = !pressed;

		if (tickEnd && kb.keyCode == Keyboard.KEY_S && Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			PacketDispatcher.sendPacketToServer(new RequestSkillGuiPacket().makePacket());
		}

	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {

		pressed = !pressed;

	}

	@Override
	public EnumSet<TickType> ticks() {
		return tick;
	}
}
