package com.fantasky.ps.recipe;

import com.fantasky.ps.recipe.input.MixerStacksRecipeInput;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class MixerBlockRecipe implements Recipe<MixerStacksRecipeInput> {
    final DefaultedList<Ingredient> ingredients;
    final ItemStack result;

    public MixerBlockRecipe( ItemStack result,DefaultedList<Ingredient> ingredients) {
        this.ingredients = ingredients;
        this.result=result;
    }


    @Override
    public boolean matches(MixerStacksRecipeInput input, World world) {
        return input.match(this.ingredients);
    }

    @Override
    public ItemStack craft(MixerStacksRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return this.result.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return this.result.copy();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeTypes.MIXER_BLOCK_SERIALIZER;
    }
    public static class Type implements RecipeType<MixerBlockRecipe> {
        private Type() {}
        public static final Type INSTANCE = new Type();
        public static final String ID = "mixer_block";
    }
    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Serializer implements RecipeSerializer<MixerBlockRecipe> {
        private static final MapCodec<MixerBlockRecipe> CODEC= RecordCodecBuilder.mapCodec(
                instance ->instance.group(

                        ItemStack.VALIDATED_CODEC.fieldOf("result").forGetter(recipe->recipe.result),
                        Ingredient.DISALLOW_EMPTY_CODEC
                                .listOf()
                                .fieldOf("ingredients")
                                .flatXmap(
                                        ingredients ->{

                                            Ingredient[] ingredients2=(Ingredient[]) ingredients.stream().filter(ingredient -> !ingredient.isEmpty()).toArray(Ingredient[]::new);
                                            if(ingredients2.length==0){
                                                return DataResult.error(()->"Recipe has no ingredients");
                                            }else{
                                                return ingredients2.length>4
                                                        ? DataResult.error(()->"Recipe has too many ingredients")
                                                        :DataResult.success(DefaultedList.copyOf(Ingredient.EMPTY, ingredients2));
                                            }
                                        },
                                        DataResult::success
                                )
                                .forGetter(recipe->recipe.ingredients)
                )
                        .apply(instance,MixerBlockRecipe::new)
        );
        public static final PacketCodec<RegistryByteBuf, MixerBlockRecipe> PACKET_CODEC=PacketCodec.ofStatic(
                    MixerBlockRecipe.Serializer::write,
                    MixerBlockRecipe.Serializer::read
                );
        @Override
        public MapCodec<MixerBlockRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, MixerBlockRecipe> packetCodec() {
            return PACKET_CODEC;
        }
        public static MixerBlockRecipe read( RegistryByteBuf buf) {
            int i=buf.readVarInt();
            DefaultedList<Ingredient> defaultedList = DefaultedList.ofSize(i, Ingredient.EMPTY);
            defaultedList.replaceAll(empty -> Ingredient.PACKET_CODEC.decode(buf));
            ItemStack itemStack = ItemStack.PACKET_CODEC.decode(buf);
            return new MixerBlockRecipe(itemStack, defaultedList);
        }
        public static void write( RegistryByteBuf buf,MixerBlockRecipe recipe) {
            buf.writeVarInt(recipe.ingredients.size());
            for (Ingredient ingredient : recipe.ingredients) {
                Ingredient.PACKET_CODEC.encode(buf, ingredient);
            }
            ItemStack.PACKET_CODEC.encode(buf, recipe.result);
        }
    }
}
