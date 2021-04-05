package io.github.simplexdev.simplexcore.ban;

public enum BanType {
    PERMANENT("P-"),
    TEMPORARY("T-"),
    CUSTOM("C-"),
    UNKNOWN("");

    private final String prefix;

    BanType(String prefix) {
        this.prefix = prefix;
    }

    public static String value(BanType type) {
        if (type.equals(PERMANENT)) {
            return "Permanent";
        } else if (type.equals(TEMPORARY)) {
            return "Temporary";
        } else {
            return "Unknown";
        }
    }

    public static BanType getFromId(String banId) {
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
