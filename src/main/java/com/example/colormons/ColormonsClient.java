package com.example.colormons;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class ColormonsClient implements ClientModInitializer {
    public static KeyMapping openKey;

    @Override
    public void onInitializeClient() {
        ColorStore.load();
        openKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.colormons.open", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_C, "category.colormons"));
        ClientTickEvents.END_CLIENT_TICK.register(mc -> {
            while (openKey.consumeClick()) {
                if (mc.crosshairPickEntity instanceof PokemonEntity mon) {
                    mc.setScreen(new ColorWheelScreen(mon));
                }
            }
        });
    }
}
