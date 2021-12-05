package io.github.simplexdev.simplexcore.ban;

import org.jetbrains.annotations.NotNull;

public enum BanType {
    PERMANENT("P-"),
    TEMPORARY("T-"),
    CUSTOM("C-"),
    UNKNOWN("");

    private final String prefix;

    BanType(String prefix) {
        this.prefix = prefix;
    }

    public static @NotNull String value(@NotNull BanType type) {
        if (type.equals(PERMANENT)) {
            return "Permanent";
        } else if (type.equals(TEMPORARY)) {
            return "Temporary";
        } else {
            return "Unknown";
        }
    }

    public static BanType getFromId(@NotNull String banId) {
        if (banId.startsWith("P")) {
            return PERMANENT;
        } else if (banId.startsWith("T")) {
            return TEMPORARY;
        } else if (banId.startsWith("C")){
            return CUSTOM;
        } else {
            return UNKNOWN;
        }
    }

    public String getPrefix() {
        return prefix;
    }
}
