package net.saolghra.chronomagica.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.saolghra.chronomagica.Chronomagica;
import net.saolghra.chronomagica.item.custom.ChronoCrystalItem;
import net.saolghra.chronomagica.item.custom.MetalDetectorItem;
import net.saolghra.chronomagica.item.custom.ModFoods;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Chronomagica.MOD_ID);

    // Adds a Sapphire item to the game
    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire",
            () -> new Item(new Item.Properties()));

    // Adds the raw_sapphire to the game.
    public static final RegistryObject<Item> RAW_SAPPHIRE = ITEMS.register("raw_sapphire",
            () -> new Item(new Item.Properties()));

    // Adds a chrona item to the game
    public static final RegistryObject<Item> CHRONA = ITEMS.register("chrona",
            () -> new Item(new Item.Properties()));

    // Adds the raw_chrona to the game.
    public static final RegistryObject<Item> RAW_CHRONA = ITEMS.register("raw_chrona",
            () -> new Item(new Item.Properties()));

    // Adds the metal detector to game
    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
            () -> new MetalDetectorItem(new Item.Properties().durability(200)));

    // Adds Chrono Crystal
    public static final RegistryObject<Item> CHRONO_CRYSTAL = ITEMS.register("chrono_crystal",
            () -> new ChronoCrystalItem(new Item.Properties().durability(500)));

    public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry",
            () -> new Item(new Item.Properties().food(ModFoods.STRAWBERRY)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
