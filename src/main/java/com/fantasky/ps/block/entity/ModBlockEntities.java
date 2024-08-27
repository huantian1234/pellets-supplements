package com.fantasky.ps.block.entity;

import com.fantasky.ps.PelletsSupplements;
import com.fantasky.ps.block.ModBlocks;
import com.mojang.datafixers.types.Type;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class ModBlockEntities {
    public static final BlockEntityType<TestBlockEntity> TEST_BLOCK_ENTITY =create("test_block_entity", BlockEntityType.Builder.create(TestBlockEntity::new,ModBlocks.TEST_BLOCK));
    public static final BlockEntityType<MixerBlockEntity> MIXER_BLOCK_ENTITY =create("mixer_block_entity", BlockEntityType.Builder.create(MixerBlockEntity::new,ModBlocks.MIXER_BLOCK));
    private static <T extends BlockEntity> BlockEntityType<T> create(String id, BlockEntityType.Builder<T> builder) {


        Type<?> type = Util.getChoiceType(TypeReferences.BLOCK_ENTITY, id);
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(PelletsSupplements.MOD_ID, id), builder.build(type));
    }
    public static void registerBlockEntityType(){
        PelletsSupplements.LOGGER.info("Registering Block Entity Type for " + PelletsSupplements.MOD_ID);
    }
}
