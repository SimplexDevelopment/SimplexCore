package io.github.simplexdev.simplexcore.gui;

import io.github.simplexdev.api.func.ClickAction;
import io.github.simplexdev.api.IGUI;
import io.github.simplexdev.simplexcore.listener.SimplexListener;
import io.github.simplexdev.simplexcore.module.SimplexModule;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.UUID;

public final class GUIHandler extends SimplexListener {
    public GUIHandler(SimplexModule<?> plugin) {
        register(this, plugin);
    }

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
            ClickAction clickAction = gui.getActions().get(event.getSlot());
            if (clickAction != null) {
                clickAction.onClick(player);
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        AbstractGUI.getOpenInvs().remove(player.getUniqueId());
    }
}
