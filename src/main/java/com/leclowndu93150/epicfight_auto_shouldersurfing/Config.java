package com.leclowndu93150.epicfight_auto_shouldersurfing;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = EpicFightAutoShoulderSurfing.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.BooleanValue ENABLED = BUILDER
            .comment("Enable automatic camera switching between battle and mining modes")
            .define("enabled", true);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean enabled;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        enabled = ENABLED.get();
    }
}
