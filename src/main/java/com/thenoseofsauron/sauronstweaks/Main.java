package com.thenoseofsauron.sauronstweaks;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import com.thenoseofsauron.sauronstweaks.proxy.CommonProxy;
import com.thenoseofsauron.sauronstweaks.tweaks.RebindNarrator;
import com.thenoseofsauron.sauronstweaks.tweaks.ReloadSound;
import com.thenoseofsauron.sauronstweaks.tweaks.Zoom;
import com.thenoseofsauron.sauronstweaks.util.Reference;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {
	
	@Instance
	public static Main instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event) {
		
		
		
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event) {
		
		RebindNarrator.init();
		Zoom.init();
		ReloadSound.init();
		
	}
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event) {
		
		
		
	}
	
}