package net.mahiron47.ntbh.mixin.gui;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
//import net.minecraft.util.Identifier;

@Mixin(CreateWorldScreen.class)
public abstract class CreateWorldScreenMixin {
    //private static final Identifier CUBIC_BACKGROUND_TEXTURE = 
    //    new Identifier("cubicchunks", "textures/gui/cubic_background.png");
    
	/**
	 * @author Mahiron47
	 * @reason changed the background texture
	 */
	@Overwrite
	public void renderBackgroundTexture(DrawContext context) {
      	//context.drawTexture(CUBIC_BACKGROUND_TEXTURE, 0, 0, 0, 0.0F, 0.0F, ((Screen)(Object)this).width, ((Screen)(Object)this).height, 32, 32);
    }
}
