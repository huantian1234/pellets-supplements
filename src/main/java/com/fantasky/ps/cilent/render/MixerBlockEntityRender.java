package com.fantasky.ps.cilent.render;

import com.fantasky.ps.block.entity.MixerBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.RotationAxis;

public class MixerBlockEntityRender implements BlockEntityRenderer<MixerBlockEntity> {
    private final ItemRenderer itemRenderer;;
    public MixerBlockEntityRender(BlockEntityRendererFactory.Context ctx) {
        this.itemRenderer = ctx.getItemRenderer();
    }
    @Override
    public void render(MixerBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        DefaultedList<ItemStack> defaultedList = entity.getItems();
        int k = (int) entity.getPos().asLong();
        for(int i = 0; i < defaultedList.size()/2; i++){

            for(int j=0;j<defaultedList.size()/2;j++){
                String BinaryNum=String.valueOf(i)+String.valueOf(j);
                int num=Integer.valueOf(BinaryNum,2);
                ItemStack itemStack = defaultedList.get(num);
                if(itemStack!=ItemStack.EMPTY){
                    matrices.push();
                    matrices.translate(0.40625F+i*0.1875F,0.1,0.40625F+j*0.1875F);
                    matrices.multiply(RotationAxis.POSITIVE_X.rotation(1.57F));
                    matrices.scale(0.25F, 0.25F, 0.25F);
                    int lightAbove = WorldRenderer.getLightmapCoordinates(entity.getWorld(), entity.getPos().up());
                    MinecraftClient.getInstance().getItemRenderer().renderItem(itemStack, ModelTransformationMode.FIXED,lightAbove,overlay,matrices,vertexConsumers,entity.getWorld(),k+i);
                    matrices.pop();
                }
            }
        }

    }
}
/*
dream
                🟩 🟩 🟩 🟩 🟩 🟩 🟩 🟩
                🟩 🟩 🟩 🟩 🟩 🟩 🟩 🟩
                🟩 🟩 🟩 🟩 🟩 🟩 🟩 🟩
                🟩 🟩 🟩 🟩 🟩 🟩 🟩 🟩
                🟩 🟩 🟩 🟩 🟩 🟩 🟩 🟩
                🟩 🟩 🟩 🟩 🟩 🟩 🟩 🟩
                🟩 🟩 🟩 🟩 🟩 🟩 🟩 🟩
                ⬛️ ⬛️ ⬛️ ⬛️ ⬛️ ⬛️ ⬛ ⬛
        🟩 🟩 🟩 ⬛️ ⬛️ ⬛️ ⬛️ ⬛️ ⬛️ 🟩 🟩 🟩
        🟩 ⬛ ⬛️ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬛️ 🟩 🟩
        ⬛️ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬛ 🟩
        ⬛️ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬛
        ⬛️ ⬜ ⬛ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬛ ⬜ ⬛️
        ⬛️ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬛️
        ⬛️ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬛️
        ⬛️ ⬜ ⬜ ⬛ ⬜ ⬜ ⬜ ⬜ ⬛ ⬜ ⬜ ⬛️
        ⬛ ⬜ ⬜ ⬛ ⬛ ⬛ ⬛ ⬛ ⬛ ⬜ ⬜ ⬛️
        🟩 ⬛ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬛️
        🟩 🟩 ⬛️ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬛️ ⬛ 🟩
        🟩 🟩 🟩 ⬛️ ⬛️ ⬛️ ⬛️ ⬛️ ⬛️ 🟩 🟩 🟩
                  ⬛️ ⬜ ⬜⬜ ⬜ ⬛️
                  ⬛️ ⬜ ⬜⬜ ⬜ ⬛️
                  ⬛️ ⬜ ⬜⬜ ⬜ ⬛️
                  ⬛️ ⬜ ⬜⬜ ⬜ ⬛️
                  ⬛️ ⬜ ⬜⬜ ⬜ ⬛️
                  ⬛️ ⬜ ⬜⬜ ⬜ ⬛️
                  ⬛️ ⬜ ⬜⬜ ⬜ ⬛️
                  ⬛ ⬛ ⬛⬛ ⬛ ⬛
 */