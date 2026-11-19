package dev.enderclient;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public final class EnderClient implements ClientModInitializer {
    private static final Minecraft CLIENT = Minecraft.getInstance();
    private static boolean overlayEnabled = true;

    private static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(
            Identifier.fromNamespaceAndPath("enderclient", "main")
    );

    private static final KeyMapping TOGGLE_OVERLAY = KeyMappingHelper.registerKeyMapping(
            new KeyMapping(
                    "key.enderclient.toggle_overlay",
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_RIGHT_SHIFT,
                    CATEGORY
            )
    );

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (TOGGLE_OVERLAY.consumeClick()) {
                overlayEnabled = !overlayEnabled;
            }
        });

        HudElementRegistry.attachElementBefore(
                VanillaHudElements.CHAT,
                Identifier.fromNamespaceAndPath("enderclient", "main_overlay"),
                (graphics, deltaTracker) -> renderOverlay(graphics)
        );
    }

    private static void renderOverlay(net.minecraft.client.gui.GuiGraphicsExtractor graphics) {
        if (!overlayEnabled || CLIENT.player == null || CLIENT.level == null) {
            return;
        }

        var player = CLIENT.player;
        int x = (int) Math.floor(player.getX());
        int y = (int) Math.floor(player.getY());
        int z = (int) Math.floor(player.getZ());

        graphics.fill(6, 6, 132, 55, 0xB5100B1D);
        graphics.fill(6, 6, 132, 8, 0xFF9B5CFF);
        graphics.text(CLIENT.font, "ENDER CLIENT", 12, 13, 0xFFE8D9FF, true);
        graphics.text(CLIENT.font, "XYZ: " + x + " / " + y + " / " + z, 12, 26, 0xFFFFFFFF, true);
        graphics.text(CLIENT.font, "ARMOR: " + player.getArmorValue(), 12, 39, 0xFFBFA6FF, true);
    }
}
