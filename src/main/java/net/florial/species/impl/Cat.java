package net.florial.species.impl;

import net.florial.Florial;
import net.florial.models.PlayerData;
import net.florial.species.Species;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cat extends Species {
    
    public Cat(int id) {
        super("Cat", id, 14, true);
    }

    @Override
    public Set<PotionEffect> effects() {

        return new HashSet<>(List.of(
                new PotionEffect(PotionEffectType.SPEED, 1000000, 0, false, false, true),
                new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 1, false, false, true)));
    }

    @Override
    public Set<String> descriptions() {

        return new HashSet<>(Arrays.asList(
                "", ""));
    }

    @Override
    public Set<Material> diet() {
        return new HashSet<>(Arrays.asList(
                Material.BEEF, Material.PORKCHOP,
                Material.CHICKEN, Material.MUTTON,
                Material.TROPICAL_FISH, Material.COD,
                Material.SALMON));
    }

    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player attacker) {

            PlayerData data = Florial.getPlayerData().get(attacker.getUniqueId());

            if (data.getSpecies() != this) return;

            if (attacker.getInventory().getItemInMainHand().getType() == Material.AIR) {

                Bukkit.broadcast(Component.text("Called"));

                Location particleLoc = attacker.getLocation().clone()
                    .add(0.0, 1.0, 0.0)
                    .add(attacker.getLocation().getDirection().clone().normalize().multiply(0.75));

                attacker.spawnParticle(Particle.SWEEP_ATTACK, particleLoc, 2);

                for (Entity e : attacker.getNearbyEntities(3, 3, 3)) {
                    if (!(e instanceof LivingEntity)) return;
                    Vector launchDirection = e.getLocation().toVector().subtract(attacker.getLocation().toVector()).normalize().multiply(1.2);
                    launchDirection.setY(0.5);
                    e.setVelocity(launchDirection);
                    ((LivingEntity) e).damage(6D);
                }
            }
        }
    }
}
