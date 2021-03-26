package io.github.simplexdev.simplexcore.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.simplexdev.api.IJson;
import io.github.simplexdev.api.func.Path;
import io.github.simplexdev.simplexcore.module.SimplexModule;
import org.json.simple.JSONObject;

public final class Json implements IJson {
    // TODO: Write actual JSON implementations.
    //
    private final Gson gson;
    private final SimplexModule<?> plugin;

    Json(SimplexModule<?> plugin) {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.plugin = plugin;
    }

    final Gson getGson() {
        return gson;
    }

    public final SimplexModule<?> getPlugin() {
        return plugin;
    }

    @Override
    public JSONObject getJSONObject(Path path) {
        return null;
    }
}
