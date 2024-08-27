package com.fantasky.ps.block;

import com.fantasky.ps.PelletsSupplements;
import com.fantasky.ps.block.coustom.MixerBlock;
import com.fantasky.ps.block.coustom.TestBlock;
import com.fantasky.ps.block.entity.ModBlockEntities;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block TEST_BLOCK=registerBlock("test_block",new TestBlock(
            AbstractBlock.Settings.create(),
            ()-> ModBlockEntities.TEST_BLOCK_ENTITY
    ));
    public static final Block MIXER_BLOCK=registerBlock("mixer_block",new MixerBlock(
            AbstractBlock.Settings.create().nonOpaque()
    ));
    private static Block registerBlock(String id, Block block){
        return Registry.register(Registries.BLOCK, Identifier.of(PelletsSupplements.MOD_ID,id),block);}
    public static void  regiserModBlocks(){
        PelletsSupplements.LOGGER.info("Registering ModBlocks for "+PelletsSupplements.MOD_ID);
    }
}
