package io.github.simplexdev.api;

import io.github.simplexdev.simplexcore.ban.BanType;
import net.kyori.adventure.text.Component;

import java.util.Date;
import java.util.UUID;

public interface IBan {
    UUID getOffender();

    String getSender();

    Component getBanReason();

    String getBanId();

    Date getDate();

    long getBanDuration();

    BanType getBanType();
}
