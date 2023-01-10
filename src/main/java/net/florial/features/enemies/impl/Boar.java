package net.florial.features.enemies.impl;

import net.florial.features.enemies.Mob;
import net.florial.features.enemies.events.MobDeathEvent;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Boar extends Mob implements Listener {
    public Boar(EntityType entity) {
        super(EntityType.HOGLIN, EntityType.SKELETON, 100, 8, 20, List.of(new ItemStack(Material.GUNPOWDER)));
    }


    @EventHandler
    public void onBoarDeath(MobDeathEvent event) {

        if (event.getEntity() != getEntity()) return;

        event.getDrops().add(new ItemStack(Material.GUNPOWDER));
    }
}
