package com.fantasky.ps.mixin;

import com.fantasky.ps.PelletsSupplements;
import com.fantasky.ps.level.PsLevelManager;
import com.llamalad7.mixinextras.sugar.Local;
import com.sun.jdi.LocalVariable;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
//    @Inject(method ="damage(Lnet/minecraft/entity/damage/DamageSource;F)Z",at=@At( "HEAD"))
//    private void removeDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
//        amount=0;
//    }
    private PsLevelManager psLevelManager=new PsLevelManager();
    @ModifyVariable(method = "damage(Lnet/minecraft/entity/damage/DamageSource;F)Z",at=@At("HEAD"))
    private float modifyDamage(float amount, DamageSource source){
        amount*=(1-this.psLevelManager.getDamageReductionRadio());
        PelletsSupplements.LOGGER.info(String.valueOf(this.psLevelManager.getDamageReductionRadio()));
        return amount;
    }

    @Inject(method="readCustomDataFromNbt(Lnet/minecraft/nbt/NbtCompound;)V",at=@At("TAIL"))
    private void readNbt(NbtCompound nbt, CallbackInfo ci){
        this.psLevelManager.readNbt(nbt);
    }

    @Inject(method="writeCustomDataToNbt(Lnet/minecraft/nbt/NbtCompound;)V",at = @At("TAIL"))
    private void writeNbt(NbtCompound nbt, CallbackInfo ci){
        this.psLevelManager.writeNbt(nbt);
    }
}
