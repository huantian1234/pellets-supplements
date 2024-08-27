package com.fantasky.ps.recipe;

import com.fantasky.ps.PelletsSupplements;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipeTypes {
    public static final RecipeSerializer<MixerBlockRecipe> MIXER_BLOCK_SERIALIZER = Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(PelletsSupplements.MOD_ID,MixerBlockRecipe.Type.ID), new MixerBlockRecipe.Serializer());

    public static void registerModRecipeSerializers() {
        Registry.register(Registries.RECIPE_TYPE, Identifier.of(PelletsSupplements.MOD_ID,MixerBlockRecipe.Type.ID), MixerBlockRecipe.Type.INSTANCE);
        PelletsSupplements.LOGGER.info("Registering Recipe Serializers for " + PelletsSupplements.MOD_ID);
    }
}
