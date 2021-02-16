package io.github.paldiu.simplexcore.utils;

import junit.framework.TestCase;
import io.github.paldiu.simplexcore.banning.BanType;

public class UtilitiesTest extends TestCase {
    public void testGenerateBanId() {
        System.out.println(Utilities.generateBanId(BanType.TEMPORARY));
    }
}