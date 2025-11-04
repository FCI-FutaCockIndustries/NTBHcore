package net.mahiron47.ntbh.mixin.gui;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.mahiron47.ntbh.utils.GUIData;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;

@Mixin(Screen.class)
public abstract class ScreenMixin {
	@Shadow
	public int width;
	@Shadow
	public int height;

	/**
	 * @author Mahiron47
	 * @reason changed the background color
	 */
	@Overwrite
    public void renderBackground(DrawContext context) {
		context.setShaderColor(0.25F, 0.25F, 0.25F, 1.0F);
		this.renderBackgroundTexture(context);
		context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
	}

	/**
	 * @author Mahiron47
	 * @reason changed the background texture
	 */
	@Overwrite
	public void renderBackgroundTexture(DrawContext context) {
		context.drawTexture(GUIData.PANORAMA_OVERLAY, 0, 0, this.width, this.height, 0.0F, 0.0F, 16, 128, 16, 128);
	}
}
