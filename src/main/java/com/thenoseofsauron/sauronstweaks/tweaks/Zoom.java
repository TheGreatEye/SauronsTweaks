package com.thenoseofsauron.sauronstweaks.tweaks;

import com.thenoseofsauron.sauronstweaks.util.Reference;

import org.lwjgl.input.Keyboard;

import jline.internal.Log;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class Zoom {
	
	private static KeyBinding zoombind;
	
	public static void init() {
		
		zoombind = new KeyBinding("Zoom Camera", Keyboard.KEY_C, "key.categories.misc");
        ClientRegistry.registerKeyBinding(zoombind);
		
	}
	
	@SubscribeEvent
    public static void onEvent(FOVUpdateEvent event) {
		
        if (zoombind.isKeyDown()) {
        	
            event.setNewfov(0.5f);
            Minecraft.getMinecraft().gameSettings.smoothCamera = true;
            
        }
    	
    }
	
}