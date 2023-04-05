package me.waterbroodje.customcrafting;

import me.waterbroodje.customcrafting.utilities.RecipesManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.LogRecord;

public final class CustomCrafting extends JavaPlugin {

    @Override
    public void onEnable() {
        RecipesManager recipesManager = new RecipesManager(this);
        recipesManager.registerRecipes();
        recipesManager.registerRecipesToServer();

        Bukkit.getLogger().log(new LogRecord(Level.ALL, "CustomCrafting has been enabled!"));
    }
}
