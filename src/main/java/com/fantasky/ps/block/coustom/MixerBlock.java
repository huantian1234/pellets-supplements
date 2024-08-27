package com.fantasky.ps.block.coustom;

import com.fantasky.ps.block.entity.MixerBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MixerBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final MapCodec<MixerBlock> CODEC = createCodec(MixerBlock::new);
    protected static final VoxelShape SHAPE = Block.createCuboidShape(5.0,0.0,5.0,11.0,7.0,11.0);
    public MixerBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MixerBlockEntity(pos,state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        if(world.isClient()) return ItemActionResult.SUCCESS;

        MixerBlockEntity BlockEntity= (MixerBlockEntity) world.getBlockEntity(pos);
//        NamedScreenHandlerFactory screenHandlerFactory=state.createScreenHandlerFactory(world,pos);
        Boolean result=false;
        if(!player.getStackInHand(hand).isEmpty()){
            if(player.getStackInHand(hand).isOf(Items.STICK)){
               result=BlockEntity.tryCraft(pos,world);
            }
            if(result){
                return ItemActionResult.SUCCESS;
            }
            else{
                BlockEntity.addItem(player,player.getStackInHand(hand));
            }

            return ItemActionResult.SUCCESS;
        }
        else{
            if(!BlockEntity.isEmpty()){
                BlockEntity.removeItem(player);
            }
            return ItemActionResult.SUCCESS;
        }
//        if(screenHandlerFactory!=null){
//            player.openHandledScreen(screenHandlerFactory);
//        }
    }

    @Override
    public Settings getSettings() {
        return super.getSettings();
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }


}
