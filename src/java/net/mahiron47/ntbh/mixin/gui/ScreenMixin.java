package net.mahiron47.ntbh.mixin.gui;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

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
		//if (this.client.world != null) {
		//	context.fillGradient(0, 0, this.width, this.height, -1072689136, -804253680);
		//} else {
			this.renderBackgroundTexture(context);
		//}
	}

	/**
	 * @author Mahiron47
	 * @reason changed the background texture
	 */
	@Overwrite
	public void renderBackgroundTexture(DrawContext context) {
		context.setShaderColor(0.25F, 0.25F, 0.25F, 1.0F);
		//context.drawTexture(OPTIONS_BACKGROUND_TEXTURE, 0, 0, 0, 0.0F, 0.0F, this.width, this.height, 32, 32);
		context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
	}
}
