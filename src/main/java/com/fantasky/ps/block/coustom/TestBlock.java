package com.fantasky.ps.block.coustom;

import com.fantasky.ps.block.entity.ModBlockEntities;
import com.fantasky.ps.block.entity.TestBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class TestBlock extends AbstractChestBlock<TestBlockEntity> {
    public static final MapCodec<TestBlock> CODEC = createCodec(    settings->new TestBlock(settings,()-> ModBlockEntities.TEST_BLOCK_ENTITY));
    public TestBlock(Settings settings, Supplier<BlockEntityType<? extends TestBlockEntity>> blockEntityTypeSupplier) {
        super(settings, blockEntityTypeSupplier);
    }

    @Override
    protected MapCodec<? extends AbstractChestBlock<TestBlockEntity>> getCodec() {
        return CODEC;
    }

    @Override
    public DoubleBlockProperties.PropertySource<? extends ChestBlockEntity> getBlockEntitySource(BlockState state, World world, BlockPos pos, boolean ignoreBlocked) {
        return null;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TestBlockEntity(pos,state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else {
            NamedScreenHandlerFactory namedScreenHandlerFactory = this.createScreenHandlerFactory(state, world, pos);
            if (namedScreenHandlerFactory != null) {
                player.openHandledScreen(namedScreenHandlerFactory);
            }

            return ActionResult.CONSUME;
        }
    }
}
