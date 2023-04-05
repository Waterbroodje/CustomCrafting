package me.waterbroodje.customcrafting.utilities;

import me.waterbroodje.customcrafting.CustomCrafting;
import me.waterbroodje.customcrafting.models.Recipe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class RecipesManager {
    private final CustomCrafting plugin;
    private final List<Recipe> recipes = new ArrayList<>();

    public RecipesManager(CustomCrafting plugin) {
        this.plugin = plugin;
    }

    public void registerRecipes() {
        FileConfiguration config = plugin.getConfig();
        Objects.requireNonNull(config.getConfigurationSection("recipes")).getKeys(false).forEach(key -> {
            String path = "recipes." + key + ".";

            Map<String, String> map = new HashMap<>();
            Objects.requireNonNull(config.getConfigurationSection(path + "ingredients")).getKeys(false).forEach(key1 -> {
                map.put(key1, config.getString(path + "ingredients." + key1));
            });

            Recipe recipe = new Recipe(
                    config.getString(path + "name"),
                    config.getStringList(path + "shape").toArray(new String[0]),
                    map,
                    new ItemStack(Material.valueOf(config.getString(path + "result.material")), config.getInt(path + "result.amount"))
            );
            recipes.add(recipe);
        });
    }

    public void registerRecipesToServer() {
        recipes.forEach(recipe -> {
            Bukkit.addRecipe(recipe.toBukkitRecipe());
            Bukkit.getLogger().log(new LogRecord(Level.ALL, "Added recipe " + recipe.getName() + " to the server!"));
        });
    }
}
