package com.fantasky.ps.cilent.screen;

import com.fantasky.ps.PelletsSupplements;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static final ScreenHandlerType<MixerScreenHandler> MIXER_SCREEN_HANDLER=
            Registry.register(Registries.SCREEN_HANDLER,
                    Identifier.of(PelletsSupplements.MOD_ID,"mixer_screen_handler"),
                    new ScreenHandlerType<MixerScreenHandler>(MixerScreenHandler::new, FeatureFlags.VANILLA_FEATURES));

    public static void registerModScreenHandlers(){
        PelletsSupplements  .LOGGER.info("Registering Mod Screen Handlers for "+PelletsSupplements.MOD_ID);

    }
}
