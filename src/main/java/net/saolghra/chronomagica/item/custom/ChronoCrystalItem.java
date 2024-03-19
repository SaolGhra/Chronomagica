package net.saolghra.chronomagica.item.custom;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.InteractionResultHolder;

import java.util.List;

public class ChronoCrystalItem extends Item {

    public static final int DURABILITY = 500;

    public ChronoCrystalItem(Properties properties) {
        super(properties.durability(DURABILITY));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        int currentDurability = stack.getDamageValue();

        if (currentDurability < DURABILITY) {
            if (!world.isClientSide) {
                // Apply slowness effect to nearby mobs
                AABB area = new AABB(player.getX() - 5, player.getY() - 2.5, player.getZ() - 5, player.getX() + 5, player.getY() + 2.5, player.getZ() + 5);
                List<Entity> entities = world.getEntities(null, area);
                for (Entity entity : entities) {
                    if (entity instanceof Mob) {
                        ((Mob) entity).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));
                    }
                }

                // Generate Dragon Breath particles around the player
                for (int i = 0; i < 50; i++) {
                    double xOffset = world.random.nextGaussian() * 0.5;
                    double yOffset = world.random.nextGaussian() * 0.5;
                    double zOffset = world.random.nextGaussian() * 0.5;
                    world.addParticle(ParticleTypes.DRAGON_BREATH,
                            player.getX() + xOffset,
                            player.getY() + player.getBbHeight() / 2 + yOffset,
                            player.getZ() + zOffset,
                            0, 0, 0);
                }

                // Damage the item
                stack.hurtAndBreak(1, player, (p_220017_1_) -> {
                    p_220017_1_.broadcastBreakEvent(hand);
                });
            }

            player.swing(hand);
            return InteractionResultHolder.success(stack);
        }

        return InteractionResultHolder.fail(stack);
    }
}