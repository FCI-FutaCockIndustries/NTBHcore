package net.mahiron47.ntbh.mixin.gui;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.mahiron47.ntbh.utils.GUIData;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
//import net.minecraft.util.Identifier;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.GridWidget;
import net.minecraft.client.gui.widget.TabNavigationWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

@Mixin(CreateWorldScreen.class)
public abstract class CreateWorldScreenMixin {
    
	@Inject(method = "render", at = @At("HEAD"))
	private void injectRender(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {	
		context.setShaderColor(0.25F, 0.25F, 0.25F, 1.0F);
		GUIData.BACKGROUND_RENDERER.render(delta, delta);
		context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
	}
	/**
	 * @author Mahiron47
	 * @reason changed the background texture
	 */
	@Overwrite
	public void renderBackgroundTexture(DrawContext context) {

	}
}
