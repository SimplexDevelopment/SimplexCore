package io.github.simplexdev.simplexcore.block;

import io.github.simplexdev.api.IUsableSign;
import io.github.simplexdev.api.func.VoidSupplier;
import org.bukkit.Material;
import org.bukkit.block.Sign;

import java.util.ArrayList;
import java.util.List;

public class SignFactory {
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
}
