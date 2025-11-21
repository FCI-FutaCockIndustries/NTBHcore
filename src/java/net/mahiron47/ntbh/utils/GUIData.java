package net.mahiron47.ntbh.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.CubeMapRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.util.Identifier;

public class GUIData {
	private GUIData() {
		// static class
	}
    public static void render_panorama_background(DrawContext context, float delta) {
        if (MinecraftClient.getInstance().world != null) {
            context.fillGradient(0, 0, context.getScaledWindowWidth(), context.getScaledWindowHeight(), -1072689136, -804253680);
        } else {
            context.setShaderColor(0.25F, 0.25F, 0.25F, 1.0F);
            context.drawTexture(GUIData.PANORAMA_OVERLAY, 0, 0, context.getScaledWindowWidth(), context.getScaledWindowHeight(), 0.0F, 0.0F, 16, 128, 16, 128);
            GUIData.BACKGROUND_RENDERER.render(delta, 1.0F);
            context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
    public static void render_panorama_background(DrawContext context) {
        if (MinecraftClient.getInstance().world != null) {
            context.fillGradient(0, 0, context.getScaledWindowWidth(), context.getScaledWindowHeight(), -1072689136, -804253680);
        } else {
            context.setShaderColor(0.25F, 0.25F, 0.25F, 1.0F);
            context.drawTexture(GUIData.PANORAMA_OVERLAY, 0, 0, context.getScaledWindowWidth(), context.getScaledWindowHeight(), 0.0F, 0.0F, 16, 128, 16, 128);
            context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
    public static void render_panorama_background(float delta) {
        GUIData.BACKGROUND_RENDERER.render(delta, 1.0F);
    }
    public static final Identifier PANORAMA_OVERLAY = new Identifier("textures/gui/title/background/panorama_overlay.png");
	public static final CubeMapRenderer PANORAMA_CUBE_MAP = new CubeMapRenderer(new Identifier("textures/gui/title/background/panorama"));
	public static final RotatingCubeMapRenderer BACKGROUND_RENDERER = new RotatingCubeMapRenderer(PANORAMA_CUBE_MAP);
}
