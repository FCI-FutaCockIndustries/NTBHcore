package net.mahiron47.ntbh.mixin.gui;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.mahiron47.ntbh.utils.GUIData;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Screen.class)
public abstract class ScreenMixin {
    @Redirect(method = "renderBackground",
              at = @org.spongepowered.asm.mixin.injection.At(value = "INVOKE",
                                                             target = "Lnet/minecraft/client/gui/screen/Screen;renderBackgroundTexture(Lnet/minecraft/client/gui/DrawContext;)V",
                                                             ordinal = 0))
    public void redirectRenderBackgroundTexture(Screen screen, DrawContext context) {
        GUIData.render_panorama_background(context, 0.0f);
    }
    /**
     * @author Mahiron47
     * @reason Render the panorama background texture on all screens.
     */
    @Overwrite
    public void renderBackgroundTexture(DrawContext context) {
        GUIData.render_panorama_background(context, 0.0f);
    }
}
