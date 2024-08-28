package com.fantasky.ps.level;

import com.fantasky.ps.PelletsSupplements;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

public class PsLevelManager {
    private PlayerEntity playerEntity;
    private int psLevel=1;
    public void readNbt(NbtCompound nbt){
        if(nbt.contains("psLevel")){
            this.psLevel = nbt.getInt("psLevel");
        }
    }

    public void writeNbt(NbtCompound nbt){
        nbt.putInt("psLevel",this.psLevel);
        PelletsSupplements.LOGGER.info("psLevel:"+this.psLevel);
    }

    public float getDamageReductionRadio(){
        return (float) this.psLevel;
    }
}
