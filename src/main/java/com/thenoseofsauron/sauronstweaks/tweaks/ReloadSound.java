package com.thenoseofsauron.sauronstweaks.tweaks;

import org.lwjgl.input.Keyboard;

import com.thenoseofsauron.sauronstweaks.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ReloadSound {
		
		private static KeyBinding soundbind;
		
		public static void init() {
			
			soundbind = new KeyBinding("Reload Sounds", Keyboard.KEY_P, "key.categories.tweaks");
	        ClientRegistry.registerKeyBinding(soundbind);
					
		}
		
		@SubscribeEvent
		public static void Input(InputEvent event) {
			
			if(soundbind.isPressed()) {
				
				Minecraft mc = Minecraft.getMinecraft();
	            mc.getSoundHandler().onResourceManagerReload(mc.getResourceManager());
				
			}
			
		}
		
}