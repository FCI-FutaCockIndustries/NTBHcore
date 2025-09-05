package net.mahiron47.ntbh.mixin.gui;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Final;

import net.mahiron47.ntbh.utils.interfaces.TitleScreenIAccessor;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.SplashTextRenderer;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.option.AccessibilityOptionsScreen;
import net.minecraft.client.gui.screen.option.CreditsAndAttributionScreen;
import net.minecraft.client.gui.screen.option.LanguageOptionsScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.PressableTextWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen implements TitleScreenIAccessor {
    @Shadow @Final
    private RotatingCubeMapRenderer backgroundRenderer;
    
    @Shadow @Final
    private static Identifier PANORAMA_OVERLAY;
    
    @Shadow @Final
    public static Text COPYRIGHT;

    @Shadow
    private SplashTextRenderer splashText;

    @Shadow
    abstract void initWidgetsNormal(int y, int spacingY);

    // Конструктор для наследования от Screen
    protected TitleScreenMixin() {
        super(Text.translatable("title.screen"));
    }

	@Override
	public RotatingCubeMapRenderer getBackgroundRenderer() {
		return this.backgroundRenderer;
	}

	// TODO: Integrate modmenu mod
    @Redirect(method = "initWidgetsNormal", 
              at = @At(value = "INVOKE", 
                       target = "Lnet/minecraft/client/gui/screen/TitleScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;",
					   ordinal = 2))
    private Element redirectAddDrawableChild(TitleScreen instance, Element drawableElement) {
        return this.addDrawableChild(
            ButtonWidget.builder(Text.translatable("menu.mods"), btn -> System.out.println("Mods clicked"))
                .dimensions(((ButtonWidget)drawableElement).getX(), ((ButtonWidget)drawableElement).getY(), ((ButtonWidget)drawableElement).getWidth(), ((ButtonWidget)drawableElement).getHeight())
                .build()
        );
    }

    /**
     * @author Mahiron47
     * @reason reduce number of conditions, cut realms
     */
    @Overwrite
    public void init() {
		if (this.splashText == null) {
			this.splashText = this.client.getSplashTextLoader().get();
		}

		int i = this.textRenderer.getWidth(COPYRIGHT);
		int j = this.width - i - 2;
		int l = this.height / 4 + 48;
		
		this.initWidgetsNormal(l, 24);

		this.addDrawableChild(
			new TexturedButtonWidget(
				this.width / 2 - 124,
				l + 72 + 12,
				20,
				20,
				0,
				106,
				20,
				ButtonWidget.WIDGETS_TEXTURE,
				256,
				256,
				button -> this.client.setScreen(new LanguageOptionsScreen(this, this.client.options, this.client.getLanguageManager())),
				Text.translatable("narrator.button.language")
			)
		);
		this.addDrawableChild(
			ButtonWidget.builder(Text.translatable("menu.options"), button -> this.client.setScreen(new OptionsScreen(this, this.client.options)))
				.dimensions(this.width / 2 - 100, l + 72 + 12, 98, 20)
				.build()
		);
		this.addDrawableChild(
			ButtonWidget.builder(Text.translatable("menu.quit"), button -> this.client.scheduleStop()).dimensions(this.width / 2 + 2, l + 72 + 12, 98, 20).build()
		);
		this.addDrawableChild(
			new TexturedButtonWidget(
				this.width / 2 + 104,
				l + 72 + 12,
				20,
				20,
				0,
				0,
				20,
				ButtonWidget.ACCESSIBILITY_TEXTURE,
				32,
				64,
				button -> this.client.setScreen(new AccessibilityOptionsScreen(this, this.client.options)),
				Text.translatable("narrator.button.accessibility")
			)
		);
		this.addDrawableChild(
			new PressableTextWidget(j, this.height - 10, i, 10, COPYRIGHT, button -> this.client.setScreen(new CreditsAndAttributionScreen(this)), this.textRenderer)
		);
	}
}
