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
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class Zoom {
	
	private static KeyBinding zoombind;
	
	private static boolean zoombindreleased = false;
	
	public static void init() {
		
		zoombind = new KeyBinding("Zoom Camera", Keyboard.KEY_C, "key.categories.tweaks");
        ClientRegistry.registerKeyBinding(zoombind);
		
	}
	
	@SubscribeEvent
    public static void onEvent(FOVUpdateEvent event) {
		
        if(zoombind.isKeyDown()) {
        	
        	zoombindreleased = false;
        	Minecraft.getMinecraft().gameSettings.smoothCamera = true;
            event.setNewfov(0.25f);
            
        }
    	
    }
	
	@SubscribeEvent
	public static void onClientTick(TickEvent.ClientTickEvent event) {
		
		if(!zoombind.isKeyDown()) {

			if(!zoombindreleased) {
				
				Minecraft.getMinecraft().gameSettings.smoothCamera = false;
				zoombindreleased = true;
				
			}
			
		}
		
	}
	
}