package net.saolghra.chronomagica.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.saolghra.chronomagica.Chronomagica;
import net.saolghra.chronomagica.block.ModBlocks;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Chronomagica.MOD_ID);

    // Registers specified items and blocks to the added creative mode tab.
    public static final RegistryObject<CreativeModeTab> CHRONOMAGICA_TAB = CREATIVE_MODE_TABS.register("chronomagica_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SAPPHIRE.get()))
                    .title(Component.translatable("creativetab.chronomagica_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        // Items
                        pOutput.accept(ModItems.SAPPHIRE.get());
                        pOutput.accept(ModItems.RAW_SAPPHIRE.get());

                        pOutput.accept(ModItems.CHRONA.get());
                        pOutput.accept(ModItems.RAW_CHRONA.get());

                        // Fuel
                        pOutput.accept(ModItems.TWIG.get());

                        // Blocks
                        pOutput.accept(ModBlocks.SAPPHIRE_BLOCK.get());
                        pOutput.accept(ModBlocks.RAW_SAPPHIRE_BLOCK.get());

                        pOutput.accept(ModBlocks.CHRONA_BLOCK.get());
                        pOutput.accept(ModBlocks.RAW_CHRONA_BLOCK.get());

                        // Ores
                        pOutput.accept(ModBlocks.SAPPHIRE_ORE.get());
                        pOutput.accept(ModBlocks.NETHER_SAPPHIRE_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get());
                        pOutput.accept(ModBlocks.END_STONE_SAPPHIRE_ORE.get());

                        pOutput.accept(ModBlocks.CHRONA_ORE.get());
                        pOutput.accept(ModBlocks.NETHER_CHRONA_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_CHRONA_ORE.get());
                        pOutput.accept(ModBlocks.END_STONE_CHRONA_ORE.get());

                        // Custom Items
                        pOutput.accept(ModItems.METAL_DETECTOR.get());
                        pOutput.accept(ModItems.CHRONO_CRYSTAL.get());
                        pOutput.accept(ModBlocks.SOUND_BLOCK.get());

                        // Foods
                        pOutput.accept(ModItems.STRAWBERRY.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
