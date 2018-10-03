package com.thenoseofsauron.sauronstweaks.tweaks;

import com.thenoseofsauron.sauronstweaks.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.ScreenChatOptions;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.IKeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

import org.lwjgl.input.Keyboard;

public class RebindNarrator {
	
	private static KeyBinding narbind;
	
	@EventHandler
	public static void init() {
		
		narbind = new KeyBinding("options.narrator", new IKeyConflictContext() {
			
			@Override
			public boolean isActive() {
				
				return !(Minecraft.getMinecraft().currentScreen instanceof GuiControls);
				
			}
			
			@Override
			public boolean conflicts(IKeyConflictContext other) {
				
				return false;
				
			}
			
		}, KeyModifier.CONTROL, Keyboard.KEY_B, "key.categories.tweaks");
		
		ClientRegistry.registerKeyBinding(narbind);
		
	}
	
	@EventBusSubscriber
	public static class Events {
		
		@SubscribeEvent
		public static void input(InputEvent event) {
			
			if(Keyboard.getEventKeyState() && narbind.isActiveAndMatches(Keyboard.getEventKey())) {
				
				Minecraft mc = Minecraft.getMinecraft();
				mc.gameSettings.setOptionValue(GameSettings.Options.NARRATOR, 1);
				
				if(mc.currentScreen instanceof ScreenChatOptions) {
					
					((ScreenChatOptions)mc.currentScreen).updateNarratorButton();
					
				}
				
			}
			
		}
		
	}
	
}
