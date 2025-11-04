package net.mahiron47.ntbh.utils;

import net.minecraft.client.gui.CubeMapRenderer;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.util.Identifier;

public class GUIData {
	private GUIData() {
		// static class
	}
    public static final Identifier PANORAMA_OVERLAY = new Identifier("textures/gui/title/background/panorama_overlay.png");
	public static final CubeMapRenderer PANORAMA_CUBE_MAP = new CubeMapRenderer(new Identifier("textures/gui/title/background/panorama"));
	public static final RotatingCubeMapRenderer BACKGROUND_RENDERER = new RotatingCubeMapRenderer(PANORAMA_CUBE_MAP);
}
