package net.yurkevichkazimir.tryingmod.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class PigEffect extends MobEffect {
    protected PigEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide()) {
            ServerLevel serverWorld = (ServerLevel) entity.level();

            // Transform the entity into a pig
            if (entity.getType() != EntityType.PIG) {
                Entity pig = EntityType.PIG.create(serverWorld);
                if (pig != null) {
                    pig.copyPosition(entity);
                    pig.setHealth(entity.getHealth());
                    serverWorld.addFreshEntity(pig);
                    entity.remove();
                    pig.addEffect(new PigEffect(PigEffectCategory.BENEFICIAL, 0xF5B6B2)); // apply a temporary effect to pig

                    // Schedule the reversion task
                    serverWorld.getServer().getScheduler().schedule(() -> {
                        if (pig.isAlive()) {
                            pig.remove();
                            LivingEntity revertedEntity = (LivingEntity) pig;
                            serverWorld.addFreshEntity(revertedEntity);
                        }
                    }, 60 * 3, 0); // 60 ticks per second * 3 seconds
                }
            }
        }
    }

    @Override
    public boolean isInstant() {
        return false;
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        super.removeAttributeModifiers(entity, attributeMap, amplifier);
    }

}
