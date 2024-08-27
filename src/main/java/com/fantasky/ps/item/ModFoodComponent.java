package com.fantasky.ps.item;

import net.minecraft.component.type.FoodComponent;

public class ModFoodComponent {
    public static final FoodComponent MUD_BALL=new FoodComponent.Builder()
            .nutrition(-2)
            .saturationModifier(4.0F)
            .alwaysEdible()
            .build();
}
