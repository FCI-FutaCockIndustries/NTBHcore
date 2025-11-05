package net.mahiron47.ntbh.mixin.gui;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.mahiron47.ntbh.utils.GUIData;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.DownloadingTerrainScreen;

@Mixin(value = DownloadingTerrainScreen.class, priority = 1100)
public class WorldGenerationProgressScreenMixin {

	@Inject(method = "render", at = @At("HEAD"))
	private void onRenderHead(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
		// Рендерим панораму позади экрана генерации мира
		context.setShaderColor(0.25F, 0.25F, 0.25F, 1.0F);
		GUIData.BACKGROUND_RENDERER.render(delta, 1.0F);
		context.drawTexture(GUIData.PANORAMA_OVERLAY, 0, 0, context.getScaledWindowWidth(), context.getScaledWindowHeight(), 0.0F, 0.0F, 16, 128, 16, 128);
		context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
	}
}
