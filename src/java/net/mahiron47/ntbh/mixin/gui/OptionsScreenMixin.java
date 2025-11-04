package net.mahiron47.ntbh.mixin.gui;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Final;

import net.mahiron47.ntbh.utils.GUIData;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;

@Mixin(OptionsScreen.class)
public abstract class OptionsScreenMixin extends Screen {
    @Shadow @Final
    private Screen parent;
    
    @Shadow @Final
    private GameOptions settings;
    
    // Конструктор миксина
    protected OptionsScreenMixin() {
		super(Text.translatable("options.title"));
	}

	/**
	 * @author Mahiron47
	 * @reason Render the panorama background on the options screen.
	 */
    @Overwrite
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.setShaderColor(0.25F, 0.25F, 0.25F, 1.0F);
        GUIData.BACKGROUND_RENDERER.render(delta, 1.0F);
        context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 15, 16777215);
        
        super.renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
    }
}
