package dev.zestyblaze.villagelife.mixin;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Villager.class)
public interface VillagerMixin {
    @Accessor
    EntityDataAccessor<VillagerData> getDATA_VILLAGER_DATA();
}
