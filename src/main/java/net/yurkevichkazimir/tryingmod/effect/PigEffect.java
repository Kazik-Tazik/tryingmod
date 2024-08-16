package net.yurkevichkazimir.tryingmod.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.yurkevichkazimir.tryingmod.entity.ai.RunAroundLikeCrazyGoal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PigEffect extends MobEffect {

    private static final String ORIGINAL_TYPE_TAG = "OriginalEntityType";
    private static final Logger LOGGER = LogManager.getLogger();

    public PigEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!(entity.level() instanceof ServerLevel)) return;

        ServerLevel serverLevel = (ServerLevel) entity.level();

        // Transform the mob into a pig
        if (entity instanceof Mob && !entity.getType().equals(EntityType.PIG)) {
            LOGGER.info("Attempting to transform entity: " + getEntityTypeName(entity.getType()));
            Mob mob = (Mob) entity;
            Pig pig = EntityType.PIG.create(serverLevel);

            if (pig != null) {
                // Store the original entity type in the persistent data
                CompoundTag persistentData = pig.getPersistentData();
                persistentData.putString(ORIGINAL_TYPE_TAG, getEntityTypeName(mob.getType()));

                // Copy position and other attributes
                pig.moveTo(mob.getX(), mob.getY(), mob.getZ(), mob.getYRot(), mob.getXRot());
                pig.setNoAi(mob.isNoAi());
                pig.setInvulnerable(mob.isInvulnerable());
                pig.setCustomName(mob.getCustomName());
                pig.setHealth(mob.getHealth());

                // Add the custom crazy running AI goal to the pig
                pig.goalSelector.addGoal(0, new RunAroundLikeCrazyGoal(pig, 2.0));

                // Add the pig to the world and remove the original mob
                serverLevel.addFreshEntity(pig);
                mob.remove(Entity.RemovalReason.DISCARDED);

                LOGGER.info("Entity transformed into a pig successfully.");

                pig.addEffect(new MobEffectInstance(this, 70, amplifier, false, false));
            } else {
                LOGGER.warn("Failed to create pig entity.");
            }
        } else {
            LOGGER.info("Entity is already a pig or not a mob.");
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        if (entity instanceof Pig pig) {
            // Retrieve the original entity type from the persistent data
            CompoundTag persistentData = pig.getPersistentData();
            if (persistentData.contains(ORIGINAL_TYPE_TAG)) {
                String originalTypeKey = persistentData.getString(ORIGINAL_TYPE_TAG);
                EntityType<?> originalType = EntityType.byString(originalTypeKey).orElse(null);

                if (originalType != null) {
                    ServerLevel serverLevel = (ServerLevel) pig.level();
                    Mob originalMob = (Mob) originalType.create(serverLevel);

                    if (originalMob != null) {
                        originalMob.moveTo(pig.getX(), pig.getY(), pig.getZ(), pig.getYRot(), pig.getXRot());
                        originalMob.setNoAi(pig.isNoAi());
                        originalMob.setInvulnerable(pig.isInvulnerable());
                        originalMob.setCustomName(pig.getCustomName());
                        originalMob.setHealth(pig.getHealth());

                        // Add the original mob back to the world and remove the pig
                        serverLevel.addFreshEntity(originalMob);
                        pig.remove(Entity.RemovalReason.DISCARDED);

                        LOGGER.info("Pig reverted to original entity: " + getEntityTypeName(originalType));
                    } else {
                        LOGGER.warn("Failed to recreate the original mob entity.");
                    }
                } else {
                    LOGGER.warn("Original entity type not found.");
                }
            } else {
                LOGGER.warn("No original entity type tag found.");
            }
        }
        super.removeAttributeModifiers(entity, attributeMap, amplifier);
    }

    private String getEntityTypeName(EntityType<?> entityType) {
        ResourceLocation location = BuiltInRegistries.ENTITY_TYPE.getKey(entityType);
        return location != null ? location.toString() : "unknown";
    }
}
