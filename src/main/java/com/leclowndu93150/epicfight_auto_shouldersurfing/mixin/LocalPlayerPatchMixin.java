package com.leclowndu93150.epicfight_auto_shouldersurfing.mixin;

import com.github.exopandora.shouldersurfing.api.client.ShoulderSurfing;
import com.github.exopandora.shouldersurfing.api.model.Perspective;
import com.leclowndu93150.epicfight_auto_shouldersurfing.Config;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.client.world.capabilites.entitypatch.player.AbstractClientPlayerPatch;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;
import yesman.epicfight.config.ClientConfig;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;

@Mixin(value = LocalPlayerPatch.class, remap = false)
public abstract class LocalPlayerPatchMixin extends AbstractClientPlayerPatch<LocalPlayer> {

    @Inject(method = "toEpicFightMode", at = @At("TAIL"))
    public void onToEpicFightMode(boolean synchronize, CallbackInfo ci) {
        if (Config.enabled && this.playerMode != PlayerPatch.PlayerMode.VANILLA && 
            ClientConfig.authSwitchCamera) {
            ShoulderSurfing.getInstance().changePerspective(Perspective.SHOULDER_SURFING);
        }
    }

    @Inject(method = "toVanillaMode", at = @At("TAIL"))
    public void onToVanillaMode(boolean synchronize, CallbackInfo ci) {
        if (Config.enabled && this.playerMode != PlayerPatch.PlayerMode.EPICFIGHT && 
            ClientConfig.authSwitchCamera) {
            ShoulderSurfing.getInstance().changePerspective(Perspective.FIRST_PERSON);
        }
    }
}
