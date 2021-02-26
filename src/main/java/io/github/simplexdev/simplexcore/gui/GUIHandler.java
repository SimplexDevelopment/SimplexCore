package io.github.simplexdev.simplexcore.gui;

import io.github.simplexdev.api.func.Action;
import io.github.simplexdev.api.IGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.UUID;

public final class GUIHandler implements Listener {
    @EventHandler(priority = EventPriority.NORMAL)
    public void invClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        UUID pID = player.getUniqueId();
        UUID invUUID = AbstractGUI.getOpenInvs().get(pID);

        if (invUUID != null) {
            event.setCancelled(true);
            IGUI gui = AbstractGUI.getInvByUUId().get(invUUID);
            Action action = gui.getActions().get(event.getSlot());
            if (action != null) {
                action.onClick(player);
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        AbstractGUI.getOpenInvs().remove(player.getUniqueId());
    }
}
