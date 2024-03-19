package net.saolghra.chronomagica.effect;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.saolghra.chronomagica.Chronomagica;

public class ModEffects {
    public static final DeferredRegister<ModEffects> MOB_EFFECTS = DeferredRegister.create((ResourceLocation) ForgeRegistries.MOB_EFFECTS, Chronomagica.MOD_ID);

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
