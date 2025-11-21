package net.mahiron47.ntbh.mixin.gui;

import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.mahiron47.ntbh.utils.GUIData;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;

@Mixin(CreateWorldScreen.class)
public abstract class CreateWorldScreenMixin extends Screen {
    protected CreateWorldScreenMixin() {
        super(null);
    }
    @Redirect(method = "render",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/world/CreateWorldScreen;renderBackground(Lnet/minecraft/client/gui/DrawContext;)V",
                    ordinal = 0))
    public void onRender(CreateWorldScreen screen, DrawContext context) {
        //nothing
    }
	@Inject(method = "render", at = @At("HEAD"))
	private void injectRender(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {	
		GUIData.render_panorama_background(context, delta);
	}
}
