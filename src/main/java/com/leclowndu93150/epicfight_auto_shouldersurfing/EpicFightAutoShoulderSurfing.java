package com.leclowndu93150.epicfight_auto_shouldersurfing;

import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
@Mod(EpicFightAutoShoulderSurfing.MODID)
public class EpicFightAutoShoulderSurfing {

    public static final String MODID = "epicfight_auto_shouldersurfing";
    private static final Logger LOGGER = LogUtils.getLogger();

    public EpicFightAutoShoulderSurfing() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
