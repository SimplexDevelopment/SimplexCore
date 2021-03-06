package io.github.simplexdev.simplexcore.sign;

import io.github.simplexdev.api.IUsableSign;
import io.github.simplexdev.api.func.VoidSupplier;
import io.github.simplexdev.simplexcore.SimplexCorePlugin;
import io.github.simplexdev.simplexcore.listener.SimplexListener;
import io.github.simplexdev.simplexcore.plugin.SimplexAddon;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignFactory {
    private static final List<Material> SIGN_TYPES = new ArrayList<>() {{
        add(Material.OAK_SIGN);
        add(Material.OAK_WALL_SIGN);
        add(Material.DARK_OAK_SIGN);
        add(Material.DARK_OAK_WALL_SIGN);
        add(Material.BIRCH_SIGN);
        add(Material.BIRCH_WALL_SIGN);
        add(Material.ACACIA_SIGN);
        add(Material.ACACIA_WALL_SIGN);
        add(Material.CRIMSON_SIGN);
        add(Material.CRIMSON_WALL_SIGN);
        add(Material.JUNGLE_SIGN);
        add(Material.JUNGLE_WALL_SIGN);
        add(Material.SPRUCE_SIGN);
        add(Material.SPRUCE_WALL_SIGN);
        add(Material.WARPED_SIGN);
        add(Material.WARPED_WALL_SIGN);
    }};

    private SignData signData = null;

    public void activateBasicSignDataListener() {
        signData = new SignData(SimplexCorePlugin.getInstance());
    }

    public SignData getSignData() {
        return signData;
    }

    public IUsableSign create(Sign sign, boolean canInteract, String tag, VoidSupplier execute) {
        return new AbstractSign(sign) {
            @Override
            public boolean canInteract() {
                return canInteract;
            }

            @Override
            public void execute() {
                execute.get();
            }

            @Override
            public String signTag() {
                return tag;
            }
        };
    }

    @Nullable
    public IUsableSign addTagsToSign(Sign sign, Object... tags) {
        if (getSignData() != null) {
            if (!SignData.getUnlabeledSigns().containsKey(sign)) {
                createNoTag(sign);
            }

            AbstractSign abs = SignData.getUnlabeledSigns().get(sign);
            for (Object tag : tags) {
                if (tag instanceof VoidSupplier) {
                    abs.setExecuteScript((VoidSupplier) tag);
                }
                if (tag instanceof String) {
                    abs.setSignTag((String) tag);
                }
                if (tag instanceof Boolean) {
                    abs.setCanInteract((Boolean) tag);
                }
            }
            return abs;
        } else {
            return null;
        }
    }

    private AbstractSign createNoTag(Sign sign) {
        return new AbstractSign(sign) {
            @Override
            public boolean canInteract() {
                return canInteract;
            }

            @Override
            public void execute() {
                executeScript.get();
            }
        };
    }

    private static class SignData extends SimplexListener {
        public SignData(SimplexAddon<?> plugin) {
            register(this, plugin);
        }

        private static final Map<Sign, AbstractSign> signMap = new HashMap<>();

        @EventHandler
        public void blockPlaceEvent(BlockPlaceEvent event) {
            if (SIGN_TYPES.contains(event.getBlockPlaced().getType())
                    && (event.getBlockPlaced() instanceof Sign)) {
                Sign sign = (Sign) event.getBlockPlaced();
                AbstractSign abs = new SignFactory().createNoTag(sign);
                signMap.put(sign, abs);
            }
        }

        @EventHandler
        public void blockInteractEvent(PlayerInteractEvent event) {
            if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)
                    && event.getClickedBlock() != null
                    && SIGN_TYPES.contains(event.getClickedBlock().getType())) {
                Sign sign = (Sign) event.getClickedBlock();
                if (signMap.containsKey(sign)) {
                    IUsableSign iSign = signMap.get(sign);
                    String tag = iSign.signTag();
                    if (iSign.getLines().get(0).equals(tag)) {
                        iSign.execute();
                    }
                }
            }
        }

        public static Map<Sign, AbstractSign> getUnlabeledSigns() {
            return signMap;
        }
    }
}
