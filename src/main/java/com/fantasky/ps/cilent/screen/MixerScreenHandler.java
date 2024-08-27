package com.fantasky.ps.cilent.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class MixerScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    public MixerScreenHandler(int syncId, PlayerInventory playerInventory){
        this(syncId,playerInventory,new SimpleInventory(2));
    }
    public MixerScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(ModScreenHandlers.MIXER_SCREEN_HANDLER, syncId);
        this.inventory = inventory;
        this.addSlot(new Slot(inventory,0,80,17));
        player_inventory(playerInventory);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    public void player_inventory(PlayerInventory playerInventory){
        int m;
        int l;
        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }
        for (m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
        }
    }
}
