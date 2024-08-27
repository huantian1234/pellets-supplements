package com.fantasky.ps.recipe.input;

import com.fantasky.ps.PelletsSupplements;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.input.RecipeInput;
import net.minecraft.util.collection.DefaultedList;

import java.util.List;

public class MixerStacksRecipeInput implements RecipeInput {
    List<ItemStack> input;
    public MixerStacksRecipeInput(List<ItemStack> input){
        this.input =input;
    }

    public boolean match(DefaultedList<Ingredient> ingredients){
        DefaultedList<ItemStack> itemStacks = DefaultedList.ofSize(ingredients.size(),ItemStack.EMPTY);
        for(int i=0;i<ingredients.size();i++){
            PelletsSupplements.LOGGER.info(String.valueOf(ingredients.get(i).getMatchingStacks()[0]));
            itemStacks.set(i,ingredients.get(i).getMatchingStacks()[0]);
        }
        PelletsSupplements.LOGGER.info(String.valueOf(itemStacks));
        if(itemStacks.size()!=this.input.size()){
            return false;
        }
        else{
            int size =itemStacks.size();
            int count =0;
            DefaultedList<Boolean> RecipeMatch = DefaultedList.ofSize(size,false);
            DefaultedList<Boolean> InputMatch = DefaultedList.ofSize(size,false);
            for(int i=0;i<size;i++){
                if(!RecipeMatch.get(i)){
                for(int j=0;j<size;j++){
                    if(!InputMatch.get(j)) {
                        PelletsSupplements.LOGGER.info(String.valueOf(itemStacks.get(i)));
                        PelletsSupplements.LOGGER.info(String.valueOf(this.input.get(j)));
                        PelletsSupplements.LOGGER.info(String.valueOf(itemStacks.get(i).isOf(this.input.get(j).getItem())));
                            if ((itemStacks.get(i).isOf(this.input.get(j).getItem()))) {

                                RecipeMatch.set(i, true);
                                InputMatch.set(j, true);
                                count++;
                            }
                        }
                    }
                }
            }
            PelletsSupplements.LOGGER.info(String.valueOf(count));
            return count==size;
        }
    }
    @Override
    public ItemStack getStackInSlot(int slot) {
        return input.get(slot);
    }

    @Override
    public int getSize() {
        return input.size();
    }
}
