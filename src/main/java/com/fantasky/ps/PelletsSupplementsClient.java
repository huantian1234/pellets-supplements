package com.fantasky.ps;

import com.fantasky.ps.block.entity.ModBlockEntities;
import com.fantasky.ps.cilent.render.MixerBlockEntityRender;
import com.fantasky.ps.cilent.screen.MixerScreen;
import com.fantasky.ps.cilent.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class PelletsSupplementsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.MIXER_SCREEN_HANDLER, MixerScreen::new);
        BlockEntityRendererFactories.register(ModBlockEntities.MIXER_BLOCK_ENTITY, MixerBlockEntityRender::new);
    }
}
