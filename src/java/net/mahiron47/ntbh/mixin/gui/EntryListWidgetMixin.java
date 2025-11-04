package net.mahiron47.ntbh.mixin.gui;

import net.mahiron47.ntbh.utils.GUIData;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.EntryListWidget;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntryListWidget.class)
public abstract class EntryListWidgetMixin {
	@Shadow
	private boolean renderBackground = false;
	@Shadow
    private boolean renderHorizontalShadows = false;

	// Disable ugly dirt background in entry lists of language options screen
	@Inject(method = "render", at = @At("HEAD"))
	private void injectRender(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {	
		context.setShaderColor(0.25F, 0.25F, 0.25F, 1.0F);
		GUIData.BACKGROUND_RENDERER.render(delta, delta);
		context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
	}
}
