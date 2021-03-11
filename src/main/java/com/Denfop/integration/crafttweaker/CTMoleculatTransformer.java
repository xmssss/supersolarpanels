package com.Denfop.integration.crafttweaker;

import com.Denfop.api.Recipes;

import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeOutput;
import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import minetweaker.api.minecraft.MineTweakerMC;
import minetweaker.mods.ic2.IC2RecipeInput;
import minetweaker.mods.ic2.MachineAddRecipeAction;
import modtweaker2.helpers.InputHelper;
import modtweaker2.utils.BaseMapRemoval;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

@ZenClass("mods.SuperSolarPanel.MoleculatTransformer")
public class CTMoleculatTransformer {
  @ZenMethod
  public static void addRecipe(IItemStack[] output, IIngredient ingredient, int minHeat) {
    if (ingredient.getAmount() < 0) {
      MineTweakerAPI.logWarning("invalid ingredient: " + ingredient + " - stack size not known");
    } else {
      NBTTagCompound tag = new NBTTagCompound();
      tag.setInteger("energy", minHeat);
      MineTweakerAPI.apply((IUndoableAction) new MachineAddRecipeAction("MolecularTransformer", Recipes.molecular,

              MineTweakerMC.getItemStacks(output), tag, new IC2RecipeInput(ingredient)));
    }
  }

  @ZenMethod
  public static void addRecipe(IItemStack output, IIngredient ingredient, int minHeat) {
    addRecipe(new IItemStack[]{output}, ingredient, minHeat);
  }

  @ZenMethod
  public static void removeRecipe(IItemStack output) {
    LinkedHashMap<IRecipeInput, RecipeOutput> recipes = new LinkedHashMap<IRecipeInput, RecipeOutput>();

    for (Map.Entry<IRecipeInput, RecipeOutput> set : Recipes.molecular.getRecipes().entrySet()) {
      for (ItemStack stack : set.getValue().items) {
        if (stack.isItemEqual(InputHelper.toStack(output))) {
          recipes.put(set.getKey(), set.getValue());
        }
      }
    }
    MineTweakerAPI.apply(new Remove(recipes));
  }

  private static class Remove extends BaseMapRemoval<IRecipeInput, RecipeOutput> {

    protected Remove(Map<IRecipeInput, RecipeOutput> recipes) {
      super("Molecular Transformer", Recipes.molecular.getRecipes(), recipes);
    }

    @Override
    protected String getRecipeInfo(Map.Entry<IRecipeInput, RecipeOutput> recipe) {
      return recipe.toString();
    }

    @ZenMethod
    public static IItemStack[] getOutput(IItemStack input) {
      RecipeOutput output = Recipes.molecular.getOutputFor(MineTweakerMC.getItemStack(input), false);
      if (output == null || output.items.isEmpty())
        return null;
      return MineTweakerMC.getIItemStacks(output.items);
    }
  }
}
