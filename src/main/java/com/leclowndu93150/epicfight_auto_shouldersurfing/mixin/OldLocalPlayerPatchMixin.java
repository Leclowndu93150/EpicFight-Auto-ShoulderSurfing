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
import yesman.epicfight.main.EpicFightMod;

@Mixin(value = LocalPlayerPatch.class, remap = false)
public abstract class OldLocalPlayerPatchMixin extends AbstractClientPlayerPatch<LocalPlayer> {

    @Inject(method = "toBattleMode", at = @At("TAIL"))
    public void onToEpicFightMode(boolean synchronize, CallbackInfo ci) {
        if (Config.enabled && this.playerMode != PlayerMode.MINING &&
                EpicFightMod.CLIENT_CONFIGS.cameraAutoSwitch.getValue()) {
            ShoulderSurfing.getInstance().changePerspective(Perspective.SHOULDER_SURFING);
        }
    }

    @Inject(method = "toMiningMode", at = @At("TAIL"))
    public void onToVanillaMode(boolean synchronize, CallbackInfo ci) {
        if (Config.enabled && this.playerMode != PlayerMode.BATTLE &&
                EpicFightMod.CLIENT_CONFIGS.cameraAutoSwitch.getValue()) {
            ShoulderSurfing.getInstance().changePerspective(Perspective.FIRST_PERSON);
        }
    }
}
