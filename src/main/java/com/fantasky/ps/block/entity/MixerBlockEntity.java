package com.fantasky.ps.block.entity;

import com.fantasky.ps.PelletsSupplements;
import com.fantasky.ps.recipe.MixerBlockRecipe;
import com.fantasky.ps.recipe.input.MixerStacksRecipeInput;
import com.fantasky.ps.cilent.screen.MixerScreenHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class MixerBlockEntity extends BlockEntity implements MixerBlockInventory, NamedScreenHandlerFactory {
    private final DefaultedList<ItemStack> itemStacks=DefaultedList.ofSize(4, ItemStack.EMPTY);
    public MixerBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
    public MixerBlockEntity(BlockPos pos, BlockState state) {
        this(ModBlockEntities.MIXER_BLOCK_ENTITY,pos,state);
    }



    @Override
    public DefaultedList<ItemStack> getItems() {
        return itemStacks;
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        this.itemStacks.clear();
        Inventories.readNbt(nbt,itemStacks,registryLookup);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt,itemStacks,registryLookup);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.fantasky.mixer_block");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new MixerScreenHandler(syncId,playerInventory,this);
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }
    public void addItem(ItemStack itemStack){
        setStack(0,itemStack);
        updateListeners();
    }
    public void updateListeners() {
        this.markDirty();
        this.getWorld().updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), Block.NOTIFY_ALL);
        this.getWorld().emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(this.getCachedState()));
    }

    public void addItem(@Nullable LivingEntity user, ItemStack itemStack){
        if(hasEmpty()){
            for(int i = 0; i < size(); i++){
                if(getStack(i).isEmpty()){
                    setStack(i, itemStack.splitUnlessCreative(1,user));
                    break;
                }
            }
        }
    }

    public void removeItem(PlayerEntity player){
        for(int i = 0; i < size(); i++){
            if(!getStack(i).isEmpty()){
                player.giveItemStack(getStack(i));
                setStack(i,ItemStack.EMPTY);
                break;
            }
        }
    }
    public boolean tryCraft(BlockPos pos, World world){
        Optional<RecipeEntry<MixerBlockRecipe>> recipe=getRecipe(pos,world);
        PelletsSupplements.LOGGER.info(String.valueOf(recipe));
        if(recipe.isPresent()){
            PelletsSupplements.LOGGER.info("000");
            clear();
            spawn(pos,world,recipe.get().value().getResult(world.getRegistryManager()));
            return true;
        }
        return false;
    }
    public Optional<RecipeEntry<MixerBlockRecipe>> getRecipe(BlockPos pos, World world){
        return world.getRecipeManager().getFirstMatch(MixerBlockRecipe.Type.INSTANCE, new MixerStacksRecipeInput(itemStacks),getWorld());
    }

    private static void spawn(BlockPos pos, World world, ItemStack itemStack) {
        ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), itemStack);
    }
}
