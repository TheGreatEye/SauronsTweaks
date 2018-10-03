package com.thenoseofsauron.sauronstweaks.tweaks;

import org.apache.logging.log4j.Logger;
import java.lang.reflect.Field;
import org.apache.logging.log4j.LogManager;
import org.lwjgl.input.Keyboard;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import com.thenoseofsauron.sauronstweaks.util.Reference;

public class ReloadSound {
	static {
		
		String tmpMcVersion;
		
		{ //Get the MC version in a way that doesn't make it a compile time constant
			
			Field fldMcVersion;
			
			try {
				
				fldMcVersion = MinecraftForge.class.getField("MC_VERSION");
				
			} catch (Exception e) {
				
				fldMcVersion = null;
				
			}
			
			try {
				
				tmpMcVersion = (String) fldMcVersion.get(null);
				
			} catch (IllegalArgumentException e) {
				
				tmpMcVersion = null;
				
			} catch (IllegalAccessException e) {
				
				tmpMcVersion = null;
				
			}
		}
		
		mcVersion = tmpMcVersion;
		
	}
	
	public static final String MODID = "soundreloader";
	public static final String VERSION = "1.1";
	public static final Logger Logger = LogManager.getLogger(Reference.MOD_ID);
	
	protected static final String mcVersion;
	protected static final boolean debug = System.getenv("DEBUG") != null && !System.getenv("DEBUG").isEmpty();
	protected static final boolean devEnv = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
	
	protected static KeyBinding soundbind;
	
	@EventHandler
	public static void preinit() {
		
		Logger.info("Registering Keybindings");
		Logger.info("Minecraft version: '" + mcVersion + "'");
		if(devEnv)
			Logger.info("Running in Development (Deobfuscated) Environment");
		soundbind = new KeyBinding("Reload Sound System", Keyboard.KEY_P, "key.categories.tweaks");
		ClientRegistry.registerKeyBinding(soundbind);
		
	}
	
	public static class KeyInputHandler {
		
		private static SoundManager cachedManager = null;
		
		@SubscribeEvent
	    public void onKeyInput(InputEvent.KeyInputEvent event) {
			
	        if(ReloadSound.soundbind.isPressed()){
	        	
	        	ReloadSound.Logger.info("Reloading sound system...");
	        	
	        	if(cachedManager == null){
	        		
		        	SoundHandler handler = Minecraft.getMinecraft().getSoundHandler();
		        	SoundManager mngr = null;
		        	Exception ex = null;
		        	
		        	try {
		        		
		        		if(debug)
							for(Field fd : SoundHandler.class.getDeclaredFields())
								Logger.info("SoundHandler Field: '" + fd.getName() + "' of type '" + fd.getType() + "'");
		        		Field fi;
		        		
			        	if(devEnv){
			        		
							fi = SoundHandler.class.getDeclaredField("sndManager");// mcpbot says field_148622_c but MC (code above) says field_147694_f
							
		        		} else {
		        			
		        			fi = SoundHandler.class.getDeclaredField("field_147694_f");// mcpbot says field_148622_c but MC (code above) says field_147694_f
		        			
		        		}
			        	
						fi.setAccessible(true);
						mngr = (SoundManager) fi.get(handler);
						
					} catch (NoSuchFieldException e) {
						
						ex = e;
						
					} catch (SecurityException e) {
						
						ex = e;
						
					} catch (IllegalArgumentException e) {
						
						ex = e;
						
					} catch (IllegalAccessException e) {
						
						ex = e;
						
					} finally {
						
						if(ex != null)
							ex.printStackTrace();
						
					}
		        	
		        	cachedManager = mngr;
		        	
	        	}
	        	
	        	if(cachedManager != null){
	        		
	        		cachedManager.reloadSoundSystem();
	        		ReloadSound.Logger.info("Reloaded sound system");
	        		
	        	} else {
	        		
	        		ReloadSound.Logger.warn("Unable to get sound manager");
	        		
	        	}
	        }
	    }
	}
}