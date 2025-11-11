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

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Mixin(value = LocalPlayerPatch.class, remap = false)
public abstract class LocalPlayerPatchMixin extends AbstractClientPlayerPatch<LocalPlayer> {

    @Inject(method = "toEpicFightMode", at = @At("TAIL"))
    public void onToEpicFightMode(boolean synchronize, CallbackInfo ci) {
        try {
            if (!Config.enabled) return;
            
            Object currentPlayerMode = getPlayerModeField();
            Object vanillaMode = getPlayerModeEnum("VANILLA");
            
            if (currentPlayerMode != null && vanillaMode != null && currentPlayerMode != vanillaMode) {
                ShoulderSurfing.getInstance().changePerspective(Perspective.SHOULDER_SURFING);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Inject(method = "toVanillaMode", at = @At("TAIL"))
    public void onToVanillaMode(boolean synchronize, CallbackInfo ci) {
        try {
            if (!Config.enabled) return;
            
            Object currentPlayerMode = getPlayerModeField();
            Object epicfightMode = getPlayerModeEnum("EPICFIGHT");
            
            if (currentPlayerMode != null && epicfightMode != null && currentPlayerMode != epicfightMode) {
                ShoulderSurfing.getInstance().changePerspective(Perspective.FIRST_PERSON);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object getPlayerModeField() throws Exception {
        Class<?> playerPatchClass = Class.forName("yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch");
        Field field = playerPatchClass.getDeclaredField("playerMode");
        field.setAccessible(true);
        return field.get(this);
    }

    private Object getPlayerModeEnum(String enumName) throws Exception {
        Class<?> playerPatchClass = Class.forName("yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch");
        Class<?>[] declaredClasses = playerPatchClass.getDeclaredClasses();
        
        for (Class<?> innerClass : declaredClasses) {
            if (innerClass.isEnum() && innerClass.getSimpleName().equals("PlayerMode")) {
                Method valuesMethod = innerClass.getMethod("values");
                Object[] enumConstants = (Object[]) valuesMethod.invoke(null);
                
                for (Object enumConstant : enumConstants) {
                    if (enumConstant.toString().equals(enumName)) {
                        return enumConstant;
                    }
                }
            }
        }
        return null;
    }

}
