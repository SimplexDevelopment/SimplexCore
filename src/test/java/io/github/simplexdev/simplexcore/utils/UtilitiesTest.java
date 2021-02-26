package io.github.simplexdev.simplexcore.utils;

import junit.framework.TestCase;
import io.github.simplexdev.simplexcore.ban.BanType;

public class UtilitiesTest extends TestCase {
    public void testGenerateBanId() {
        System.out.println(Utilities.generateBanId(BanType.TEMPORARY));
    }
}