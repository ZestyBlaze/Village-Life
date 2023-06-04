package dev.zestyblaze.villagelife.registry;

import dev.zestyblaze.villagelife.VillageLife;
import dev.zestyblaze.villagelife.entity.SmartVillagerMob;
import eu.pb4.polymer.core.api.entity.PolymerEntityUtils;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {
    public static final EntityType<SmartVillagerMob> SMART_VILLAGER = FabricEntityTypeBuilder.create(MobCategory.MISC, SmartVillagerMob::new).dimensions(EntityDimensions.scalable(1.0f, 1.0f)).build();

    public static void register() {
        Registry.register(BuiltInRegistries.ENTITY_TYPE, new ResourceLocation(VillageLife.MOD_ID, "smart_villager"), SMART_VILLAGER);
        FabricDefaultAttributeRegistry.register(SMART_VILLAGER, SmartVillagerMob.createAttributes());
        PolymerEntityUtils.registerType(SMART_VILLAGER);
    }
}
