package net.saolghra.chronomagica.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

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
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    private void createAndActivateTimeBubble(Player player) {
        Level world =player.getCommandSenderWorld();
        BlockPos playerPos = player.blockPosition();
        List<Entity> entities = world.getEntities(player, new AABB(playerPos.offset(-TIME_BUBBLE_RADIUS, -TIME_BUBBLE_RADIUS, -TIME_BUBBLE_RADIUS),
                playerPos.offset(TIME_BUBBLE_RADIUS + 1, TIME_BUBBLE_RADIUS + 1, TIME_BUBBLE_RADIUS + 1)));

        for (Entity entity : entities) {
            if (entity instanceof LivingEntity livingEntity) {
                slowDownEntity(livingEntity);
            }
        }

        player.sendSystemMessage(Component.literal("Chrono Crystal activated!")); // Placeholder message
    }

    private void slowDownEntity(LivingEntity entity) {
        entity.setDeltaMovement(entity.getDeltaMovement().scale(TIME_SLOW_FACTOR));
        // You may need to adjust other attributes of the entity depending on your requirements
    }
}
