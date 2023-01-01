package species;

import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import species.speciesinternal.Species;

import java.util.List;

public class Fox extends Species {
    /**
     * permEffects = list of effects the species will always have, permanently, despite its level
     * maxHealth = the max health of the species. may change upon levels
     *
     * @param permEffects
     * @param maxhealth
     */
    public Fox(List<PotionEffect> permEffects, int maxhealth) {
        super(permEffects, maxhealth, 2);
    }

    @Override
    public void performAbility() {

    }

    @Override
    public void speciesRespawn(PlayerRespawnEvent e) {

    }
}
