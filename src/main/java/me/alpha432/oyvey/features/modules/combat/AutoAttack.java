package me.alpha432.oyvey.features.modules.combat;

import me.alpha432.oyvey.event.impl.TickEvent;
import me.alpha432.oyvey.event.system.Subscribe;
import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.InteractionHand;

public class KillAuraModule extends Module {

    private final double range = 3.0;

    public KillAuraModule() {
        super("KillAura", "Automatically attacks nearby entities", Category.COMBAT);
    }

    @Subscribe
    public void onTick(TickEvent event) {
        if (mc.player == null || mc.level == null) return;

        for (Entity entity : mc.level.entitiesForRendering()) {

            // Skip invalid targets
            if (!(entity instanceof LivingEntity target)) continue;
            if (entity == mc.player) continue;
            if (!target.isAlive()) continue;

            // Only attack players and mobs
            if (!(target instanceof Player) &&
                !(target instanceof Monster) &&
                !(target instanceof Animal)) continue;

            // Check range
            if (mc.player.distanceTo(target) > range) continue;

            attack(target);
            break; // attack only one entity per tick
        }
    }

    private void attack(LivingEntity target) {
        mc.gameMode.attack(mc.player, target);
        mc.player.swing(InteractionHand.MAIN_HAND);
    }

    @Override
    public String getDisplayInfo() {
        return "3.0";
    }
}
