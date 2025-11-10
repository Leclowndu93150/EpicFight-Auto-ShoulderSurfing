package com.leclowndu93150.epicfight_auto_shouldersurfing;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.forgespi.language.IModInfo;
import org.apache.maven.artifact.versioning.ArtifactVersion;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class MixinPlugin implements IMixinConfigPlugin {
    private static final String EPICFIGHT_MOD_ID = "epicfight";
    private static final ArtifactVersion VERSION_THRESHOLD = new DefaultArtifactVersion("20.9.7");
    
    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (mixinClassName.equals("com.leclowndu93150.epicfight_auto_shouldersurfing.mixin.OldLocalPlayerPatchMixin")) {
            return isEpicFightVersionOldOrEqual();
        } else if (mixinClassName.equals("com.leclowndu93150.epicfight_auto_shouldersurfing.mixin.LocalPlayerPatchMixin")) {
            return isEpicFightVersionNew();
        }
        return true;
    }

    private boolean isEpicFightVersionOldOrEqual() {
        Optional<? extends IModInfo> modInfo = ModList.get().getModContainerById(EPICFIGHT_MOD_ID)
                .map(container -> container.getModInfo());
        
        if (modInfo.isPresent()) {
            ArtifactVersion version = modInfo.get().getVersion();
            return version.compareTo(VERSION_THRESHOLD) <= 0;
        }
        return false;
    }

    private boolean isEpicFightVersionNew() {
        Optional<? extends IModInfo> modInfo = ModList.get().getModContainerById(EPICFIGHT_MOD_ID)
                .map(container -> container.getModInfo());
        
        if (modInfo.isPresent()) {
            ArtifactVersion version = modInfo.get().getVersion();
            return version.compareTo(VERSION_THRESHOLD) > 0;
        }
        return false;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
}
