package me.waterbroodje.customcrafting.models;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class Recipe {
    private final String name;
    private final String[] shape;
    private final Map<String, String> ingredients;
    private final ItemStack result;

    public Recipe(String name, String[] shape, Map<String, String> ingredients, ItemStack result) {
        this.name = name;
        this.shape = shape;
        this.ingredients = ingredients;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public String[] getShape() {
        return shape;
    }

    public Map<String, String> getIngredients() {
        return ingredients;
    }

    public ItemStack getResult() {
        return result;
    }

    public org.bukkit.inventory.Recipe toBukkitRecipe() {
        org.bukkit.inventory.ShapedRecipe recipe = new org.bukkit.inventory.ShapedRecipe(result);
        recipe.shape(shape);
        ingredients.forEach((key, value) -> recipe.setIngredient(key.charAt(0), Material.valueOf(value)));
        return recipe;
    }
}
