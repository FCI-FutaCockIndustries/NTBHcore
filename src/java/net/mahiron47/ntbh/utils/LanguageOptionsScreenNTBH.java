package net.mahiron47.ntbh.utils;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.AlwaysSelectedEntryListWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.input.KeyCodes;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.resource.language.LanguageDefinition;
import net.minecraft.client.resource.language.LanguageManager;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Util;

@Deprecated
@Environment(EnvType.CLIENT)
public class LanguageOptionsScreenNTBH extends GameOptionsScreen {
   private static final Text LANGUAGE_WARNING_TEXT;
   private LanguageSelectionListWidgetNTBH languageSelectionList;
   final LanguageManager languageManager;

   public LanguageOptionsScreenNTBH(Screen parent, GameOptions options, LanguageManager languageManager) {
      super(parent, options, Text.translatable("options.language"));
      this.languageManager = languageManager;
   }

   protected void init() {
      this.languageSelectionList = new LanguageSelectionListWidgetNTBH(this.client);
      this.addSelectableChild(this.languageSelectionList);
      this.addDrawableChild(this.gameOptions.getForceUnicodeFont().createWidget(this.gameOptions, this.width / 2 - 155, this.height - 38, 150));
      this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, (button) -> {
         this.onDone();
      }).dimensions(this.width / 2 - 155 + 160, this.height - 38, 150, 20).build());
      super.init();
   }

   void onDone() {
      LanguageSelectionListWidgetNTBH.LanguageEntryNTBH languageEntry = (LanguageSelectionListWidgetNTBH.LanguageEntryNTBH)this.languageSelectionList.getSelectedOrNull();
      if (languageEntry != null && !languageEntry.languageCode.equals(this.languageManager.getLanguage())) {
         this.languageManager.setLanguage(languageEntry.languageCode);
         this.gameOptions.language = languageEntry.languageCode;
         this.client.reloadResources();
         this.gameOptions.write();
      }

      this.client.setScreen(this.parent);
   }

   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
      if (KeyCodes.isToggle(keyCode)) {
         LanguageSelectionListWidgetNTBH.LanguageEntryNTBH languageEntry = (LanguageSelectionListWidgetNTBH.LanguageEntryNTBH)this.languageSelectionList.getSelectedOrNull();
         if (languageEntry != null) {
            languageEntry.onPressed();
            this.onDone();
            return true;
         }
      }

      return super.keyPressed(keyCode, scanCode, modifiers);
   }

   public void render(DrawContext context, int mouseX, int mouseY, float delta) {
      this.languageSelectionList.render(context, mouseX, mouseY, delta);
      context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 16, 16777215);
      context.drawCenteredTextWithShadow(this.textRenderer, LANGUAGE_WARNING_TEXT, this.width / 2, this.height - 56, 8421504);
      super.render(context, mouseX, mouseY, delta);
   }

   static {
      LANGUAGE_WARNING_TEXT = Text.literal("(").append(Text.translatable("options.languageWarning")).append(")").formatted(Formatting.GRAY);
   }

   @Environment(EnvType.CLIENT)
	class LanguageSelectionListWidgetNTBH extends AlwaysSelectedEntryListWidget<LanguageOptionsScreenNTBH.LanguageSelectionListWidgetNTBH.LanguageEntryNTBH> {
		protected LanguageSelectionListWidgetNTBH(MinecraftClient client) {
			super(client, LanguageOptionsScreenNTBH.this.width, LanguageOptionsScreenNTBH.this.height, 32, LanguageOptionsScreenNTBH.this.height - 65 + 4, 18);
			String string = LanguageOptionsScreenNTBH.this.languageManager.getLanguage();
			LanguageOptionsScreenNTBH.this.languageManager
				.getAllLanguages()
				.forEach(
					(languageCode, languageDefinition) -> {
						LanguageOptionsScreenNTBH.LanguageSelectionListWidgetNTBH.LanguageEntryNTBH languageEntry = new LanguageOptionsScreenNTBH.LanguageSelectionListWidgetNTBH.LanguageEntryNTBH(
							languageCode, languageDefinition
						);
						this.addEntry(languageEntry);
						if (string.equals(languageCode)) {
							this.setSelected(languageEntry);
						}
					}
				);
			if (this.getSelectedOrNull() != null) {
				this.centerScrollOn(this.getSelectedOrNull());
			}
		}

		@Override
		protected int getScrollbarPositionX() {
			return super.getScrollbarPositionX() + 20;
		}

		@Override
		public int getRowWidth() {
			return super.getRowWidth() + 50;
		}

		@Override
		protected void renderBackground(DrawContext context) {
			//LanguageOptionsScreenNTBH.this.renderBackground(context);
		}

		@Environment(EnvType.CLIENT)
		public class LanguageEntryNTBH extends AlwaysSelectedEntryListWidget.Entry<LanguageOptionsScreenNTBH.LanguageSelectionListWidgetNTBH.LanguageEntryNTBH> {
			private final String languageCode;
			private final Text languageDefinition;
			private long clickTime;

			protected LanguageEntryNTBH(String languageCode, LanguageDefinition languageDefinition) {
				this.languageCode = languageCode;
				this.languageDefinition = languageDefinition.getDisplayText();
			}

			// ugly dirt background is fixed in EntryListWidgetMixin
			@Override
			public void render(DrawContext context, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
				context.drawCenteredTextWithShadow(
					LanguageOptionsScreenNTBH.this.textRenderer, this.languageDefinition, LanguageSelectionListWidgetNTBH.this.width / 2, y + 1, 16777215
				);
			}

			@Override
			public boolean mouseClicked(double mouseX, double mouseY, int button) {
				if (button == 0) {
					this.onPressed();
					if (Util.getMeasuringTimeMs() - this.clickTime < 250L) {
						LanguageOptionsScreenNTBH.this.onDone();
					}

					this.clickTime = Util.getMeasuringTimeMs();
					return true;
				} else {
					this.clickTime = Util.getMeasuringTimeMs();
					return false;
				}
			}

			void onPressed() {
				LanguageSelectionListWidgetNTBH.this.setSelected(this);
			}

			@Override
			public Text getNarration() {
				return Text.translatable("narrator.select", this.languageDefinition);
			}
		}
	}
}
