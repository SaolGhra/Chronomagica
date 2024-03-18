package net.saolghra.chronomagica.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Random;

public class ChronoCrystalItem extends Item {
    private static final int TIME_BUBBLE_RADIUS = 10; // Adjust the radius of the time bubble as needed
    private static final double TIME_SLOW_FACTOR = 0.5; // Adjust the time slow factor as needed

    public ChronoCrystalItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        if (player != null) {
            if (!context.getLevel().isClientSide()) {
                createAndActivateTimeBubble(player);
                player.playSound(SoundEvents.BELL_BLOCK, 1.0f, 1.0f); // Play ding sound
                player.sendSystemMessage(Component.literal("Chrono Crystal activated!")); // Display message to player
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    private void createAndActivateTimeBubble(Player player) {
        Level world = player.getCommandSenderWorld();
        BlockPos playerPos = player.blockPosition();
        List<Entity> entities = world.getEntities(player, new AABB(playerPos.offset(-TIME_BUBBLE_RADIUS, -TIME_BUBBLE_RADIUS, -TIME_BUBBLE_RADIUS),
                playerPos.offset(TIME_BUBBLE_RADIUS + 1, TIME_BUBBLE_RADIUS + 1, TIME_BUBBLE_RADIUS + 1)));

        // Spawn particle effects
        Random random = new Random();
        for (double x = playerPos.getX() - TIME_BUBBLE_RADIUS; x <= playerPos.getX() + TIME_BUBBLE_RADIUS; x += 1) {
            for (double y = playerPos.getY() - TIME_BUBBLE_RADIUS; y <= playerPos.getY() + TIME_BUBBLE_RADIUS; y += 1) {
                for (double z = playerPos.getZ() - TIME_BUBBLE_RADIUS; z <= playerPos.getZ() + TIME_BUBBLE_RADIUS; z += 1) {
                    double distanceSq = player.distanceToSqr(x, y, z);
                    if (distanceSq <= TIME_BUBBLE_RADIUS * TIME_BUBBLE_RADIUS) {
                        Vec3 pos = new Vec3(x + 0.5, y + 0.5, z + 0.5);
                        Vec3 motion = new Vec3((random.nextDouble() - 0.5) * 0.1, (random.nextDouble() - 0.5) * 0.1, (random.nextDouble() - 0.5) * 0.1);
                        world.addParticle(ParticleTypes.DRIPPING_WATER, pos.x, pos.y, pos.z, motion.x, motion.y, motion.z);
                    }
                }
            }
        }

        for (Entity entity : entities) {
            if (entity instanceof LivingEntity livingEntity) {
                slowDownEntity(livingEntity);
            }
        }
    }

    private void slowDownEntity(LivingEntity entity) {
        entity.setDeltaMovement(entity.getDeltaMovement().scale(TIME_SLOW_FACTOR));
        // You may need to adjust other attributes of the entity depending on your requirements
    }
}
