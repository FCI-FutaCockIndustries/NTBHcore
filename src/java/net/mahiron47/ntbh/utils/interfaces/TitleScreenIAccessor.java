package net.mahiron47.ntbh.utils.interfaces;

import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.util.Identifier;

public interface TitleScreenIAccessor {
    RotatingCubeMapRenderer getBackgroundRenderer();
    static Identifier getPanoramaOverlay() {
        return new Identifier("textures/gui/title/background/panorama_overlay.png");
    }
}
