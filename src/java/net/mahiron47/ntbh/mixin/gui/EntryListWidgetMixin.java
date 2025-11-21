package net.mahiron47.ntbh.mixin.gui;

import net.mahiron47.ntbh.utils.GUIData;
import net.minecraft.client.gui.AbstractParentElement;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.EntryListWidget;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntryListWidget.class)
public abstract class EntryListWidgetMixin extends AbstractParentElement {
    protected EntryListWidgetMixin() {
        super();
    }
    @Shadow
    protected int width;
    @Shadow
    protected int height;
    @Shadow
    protected int top;
    @Shadow
    protected int bottom;
    @Shadow
    protected int right;
    @Shadow
    protected int left;
    @Shadow
    public abstract double getScrollAmount();
    @Redirect(method = "render",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/widget/EntryListWidget;renderBackground(Lnet/minecraft/client/gui/DrawContext;)V",
                    ordinal = 0))
    public void onRender(EntryListWidget screen, DrawContext context) {
        //nothing
    }
	@Inject(method = "render", at = @At("HEAD"))
	private void injectRender(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {	
		GUIData.render_panorama_background(context, delta);
	}
    @Redirect(method = "render",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIFFIIII)V",
                    ordinal = 0))
    public void onDrawTexture0(DrawContext context, net.minecraft.util.Identifier identifier, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight) {
        context.drawTexture(
                GUIData.PANORAMA_OVERLAY,
                this.left,
                this.top,
                this.right,
                this.bottom + (int)this.getScrollAmount(),
                this.right - this.left,
                this.bottom - this.top,
                32,
                32
        );
    }
    @Redirect(method = "render",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIFFIIII)V",
                    ordinal = 1))
    public void onDrawTexture1(DrawContext context, net.minecraft.util.Identifier identifier, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight) {
        context.drawTexture(GUIData.PANORAMA_OVERLAY, this.left, 0, 0.0F, 0.0F, this.width, this.top, 32, 32);
    }
    @Redirect(method = "render",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIFFIIII)V",
                    ordinal = 2))
    public void onDrawTexture2(DrawContext context, net.minecraft.util.Identifier identifier, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight) {
        context.drawTexture(GUIData.PANORAMA_OVERLAY, this.left, this.bottom, 0.0F, this.bottom, this.width, this.height - this.bottom, 32, 32);
    }
}
