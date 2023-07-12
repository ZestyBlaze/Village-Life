package dev.zestyblaze.villagelife.registry;

import dev.zestyblaze.villagelife.VillageLife;
import eu.pb4.polymer.core.api.item.PolymerSpawnEggItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.HashMap;

public class ModItems {
    private static final HashMap<String, Item> ITEMS = new HashMap<>();
    public static final Item SMART_VILLAGER_SPAWN_EGG = new PolymerSpawnEggItem(ModEntities.SMART_VILLAGER, Items.VILLAGER_SPAWN_EGG, new FabricItemSettings());
    public static final CreativeModeTab SMART_VILLAGER_GROUP = FabricItemGroup.builder().icon(() -> new ItemStack(SMART_VILLAGER_SPAWN_EGG)).title(Component.translatable("itemGroup.villagelife.title")).displayItems((enabledFeatures, entries) -> ITEMS.values().stream().map(ItemStack::new).forEach(entries::accept)).build();

    public static void register() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(VillageLife.MOD_ID, "creative_tab"), SMART_VILLAGER_GROUP);
        registerItem("smart_villager_spawn_egg", SMART_VILLAGER_SPAWN_EGG);
    }

    private static void registerItem(String id, Item item) {
        ITEMS.put(id, Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(VillageLife.MOD_ID, id), item));
    }
}
