package com.fantasky.ps.block.entity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public interface MixerBlockInventory extends Inventory {
    DefaultedList<ItemStack> getItems();

    @Override
    default int size(){
        return getItems().size();
    };

    @Override
    default ItemStack getStack(int slot){
        return getItems().get(slot);
    };

    @Override
    default void setStack(int slot, ItemStack stack){
        getItems().set(slot, stack);
        updateListeners();
    };


    default boolean hasEmpty(){
        for(int i = 0; i < size(); i++){
            if(getStack(i).isEmpty()){
                return true;
            }
        }
        return false;
    }
    @Override
    default boolean isEmpty(){
        for (int i = 0; i < size(); i++){
            if(!getStack(i).isEmpty()){
                return false;
            }
        }
        return true;
    };

    @Override
    default ItemStack removeStack(int slot, int amount){
        ItemStack result = Inventories.splitStack(getItems(), slot, amount);
        if (!result.isEmpty()) {
            markDirty();
        }
        return result;
    }

    @Override
    default ItemStack removeStack(int slot){
        return Inventories.removeStack(getItems(),slot);
    };

    @Override
    default boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    default void clear() {
        getItems().clear();
        updateListeners();
    }
    void updateListeners();
}
