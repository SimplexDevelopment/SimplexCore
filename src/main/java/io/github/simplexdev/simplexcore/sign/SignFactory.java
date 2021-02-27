package io.github.simplexdev.simplexcore.sign;

import io.github.simplexdev.api.IUsableSign;
import io.github.simplexdev.api.func.VoidSupplier;
import io.github.simplexdev.simplexcore.listener.SimplexListener;
import io.github.simplexdev.simplexcore.plugin.SimplexAddon;
import io.github.simplexdev.simplexcore.utils.Constants;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Supplier;

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
        signData = new SignData(Constants.getPlugin());
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
            AbstractSign abs = SignData.getUnlabeledSigns().get(sign);
            for (Object tag : tags) {
                if (tag instanceof VoidSupplier) {
                    abs.setExecuteScript((VoidSupplier)tag);
                }
                if (tag instanceof String) {
                    abs.setSignTag((String)tag);
                }
                if (tag instanceof Boolean) {
                    abs.setCanInteract((Boolean)tag);
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

            @Override
            public String signTag() {
                return signTag;
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

        public static Map<Sign, AbstractSign> getUnlabeledSigns() {
            return signMap;
        }
    }
}
