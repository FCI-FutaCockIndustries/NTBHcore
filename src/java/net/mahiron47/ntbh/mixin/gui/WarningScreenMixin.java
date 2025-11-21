package net.mahiron47.ntbh.mixin.gui;

import net.mahiron47.ntbh.utils.GUIData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.WarningScreen;

@Mixin(WarningScreen.class)
public abstract class WarningScreenMixin extends Screen {
	protected WarningScreenMixin() {
		super(null);
	}
    @Redirect(method = "render",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/WarningScreen;renderBackground(Lnet/minecraft/client/gui/DrawContext;)V",
                    ordinal = 0))
    public void onRender(WarningScreen screen, DrawContext context) {
        //nothing
    }
	@Inject(method = "render", at = @At("HEAD"))
	private void onRender(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        GUIData.render_panorama_background(context, delta);
	}
}
