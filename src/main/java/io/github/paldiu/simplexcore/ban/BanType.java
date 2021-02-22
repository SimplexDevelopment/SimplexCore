package io.github.paldiu.simplexcore.ban;

public enum BanType {
    PERMANENT("P-"),
    TEMPORARY("T-");

    private final String prefix;

    BanType(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
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
}
