package net.mahiron47.ntbh.mixin.gui;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.mahiron47.ntbh.utils.GUIData;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.option.ControlsOptionsScreen;

@Mixin(ControlsOptionsScreen.class)
public abstract class ControlsOptionsScreenMixin {

	@Inject(method = "render", at = @At("HEAD"))
	private void injectRender(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
		context.setShaderColor(0.25F, 0.25F, 0.25F, 1.0F);
		GUIData.BACKGROUND_RENDERER.render(delta, delta);
		context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
	}
}
