package net.mahiron47.ntbh.mixin.gui;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.Final;

import com.mojang.blaze3d.systems.RenderSystem;

import net.mahiron47.ntbh.utils.interfaces.TitleScreenIAccessor;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;

@Mixin(OptionsScreen.class)
public abstract class OptionsScreenMixin extends Screen {
    // Убираем @Final, так как будем присваивать значение
    private TitleScreen titleScreen; 

    @Shadow @Final
    private Screen parent;
    
    @Shadow @Final
    private GameOptions settings;
    
    // Конструктор миксина
    protected OptionsScreenMixin() {
		super(Text.translatable("options.title"));
	}

    @Inject(method = "<init>(Lnet/minecraft/client/gui/screen/Screen;Lnet/minecraft/client/option/GameOptions;)V", at = @At("TAIL"))
    private void initStaticRenderer(Screen parent, GameOptions settings, CallbackInfo ci) {
        // Проверяем, что parent это TitleScreen
        if (parent instanceof TitleScreen) {
            this.titleScreen = (TitleScreen) parent;
        }
    }

	/**
	 * @author Mahiron47
	 * @reason Render the panorama background on the options screen.
	 */
    @Overwrite
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        if (((TitleScreenIAccessor) titleScreen).getBackgroundRenderer() != null) {
            ((TitleScreenIAccessor) titleScreen).getBackgroundRenderer().render(delta, 1.0F);
            RenderSystem.enableBlend();
            context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            context.drawTexture(TitleScreenIAccessor.getPanoramaOverlay(), 0, 0, this.width, this.height, 0.0F, 0.0F, 16, 128, 16, 128);
            context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        } else {
            context.fill(0, 0, this.width, this.height, 0xFF000000);
        }
        
        // Рендерим заголовок
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 15, 16777215);
        
        // Рендерим остальные элементы
        super.render(context, mouseX, mouseY, delta);
    }
}
