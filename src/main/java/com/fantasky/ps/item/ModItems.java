package com.fantasky.ps.item;

import com.fantasky.ps.PelletsSupplements;
import com.fantasky.ps.block.ModBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item MIXER_BLOCK=registerItem("mixer_block",new BlockItem(ModBlocks.MIXER_BLOCK,new Item.Settings()));
    public static final Item TEST_BLOCK=registerItem("test_block",new BlockItem(ModBlocks.TEST_BLOCK,new Item.Settings()));
    public static final Item MUD_BALL=registerItem("mud_ball",new Item(new Item.Settings().food(ModFoodComponent.MUD_BALL)));
    private static Item registerItem(String id, Item item){
        return Registry.register(Registries.ITEM,Identifier.of(PelletsSupplements.MOD_ID,id),item);
    }
    public static void registerModItems(){
        PelletsSupplements.LOGGER.info("Registering Mod Items for "+PelletsSupplements.MOD_ID);
    }
}
