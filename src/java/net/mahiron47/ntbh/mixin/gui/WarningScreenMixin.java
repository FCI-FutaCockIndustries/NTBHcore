package net.mahiron47.ntbh.mixin.gui;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.WarningScreen;

@Mixin(WarningScreen.class)
public class WarningScreenMixin extends Screen {
	protected WarningScreenMixin() {
		super(null);
	}

	@Inject(method = "render", at = @At("HEAD"))
	private void onRender(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
		this.renderBackgroundTexture(context);
	}
}
