package com.leclowndu93150.epicfight_auto_shouldersurfing;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EpicFightAutoShoulderSurfing.MODID, value = Dist.CLIENT)
public class ClientCommands {

    @SubscribeEvent
    public static void onRegisterClientCommands(RegisterClientCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        
        dispatcher.register(
            Commands.literal("autosurfing")
                .then(Commands.argument("enabled", BoolArgumentType.bool())
                    .executes(context -> {
                        boolean value = BoolArgumentType.getBool(context, "enabled");
                        Config.enabled = value;
                        context.getSource().sendSuccess(
                            () -> Component.literal("Auto camera switching " + (value ? "enabled" : "disabled")),
                            false
                        );
                        return 1;
                    })
                )
                .executes(context -> {
                    context.getSource().sendSuccess(
                        () -> Component.literal("Auto camera switching is currently " + (Config.enabled ? "enabled" : "disabled")),
                        false
                    );
                    return 1;
                })
        );
    }
}
