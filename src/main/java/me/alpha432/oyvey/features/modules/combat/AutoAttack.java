package me.alpha432.oyvey.features.modules.combat;

import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Hand;

public class AutoAttack extends Module {

    private final double range = 3.0;

    public AutoAttack() {
        super("AutoAttack", "Ataca automat entitatile in radius de 3 blockuri", Category.COMBAT, true, false, false);
    }

    @Override
    public void onTick() {

        if (mc.player == null || mc.world == null)
            return;

        for (Entity entity : mc.world.getEntities()) {

            if (!(entity instanceof LivingEntity))
                continue;

            if (entity == mc.player)
                continue;

            if (entity.isRemoved())
                continue;

            if (mc.player.distanceTo(entity) <= range) {

                attack(entity);
                break;
            }
        }
    }

    private void attack(Entity entity) {

        mc.interactionManager.attackEntity(mc.player, entity);
        mc.player.swingHand(Hand.MAIN_HAND);

    }
}
