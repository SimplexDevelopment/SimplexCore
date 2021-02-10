package io.github.paldiu.simplexcore.banning;

import java.util.Date;
import java.util.SplittableRandom;
import java.util.UUID;

public interface IBan {
    UUID getOffender();

    String getSender();

    String getBanReason();

    String getBanId();

    Date getDate();

    long getBanDuration();

    BanType getBanType();
}
