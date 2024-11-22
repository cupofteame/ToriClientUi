package com.terraformersmc.modmenu.util;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BackgroundRenderer {
    private static final Identifier BLUR_SHADER = new Identifier("modmenu", "shaders/post/blur.json");
    private static PostEffectProcessor blurShader;
    
    public static void render(MatrixStack matrices, MinecraftClient client, float blurAmount) {
        try {
            if (blurShader == null) {
                blurShader = new PostEffectProcessor(client.getTextureManager(), client.getResourceManager(), 
                    client.getFramebuffer(), BLUR_SHADER);
                blurShader.setupDimensions(client.getWindow().getFramebufferWidth(), 
                    client.getWindow().getFramebufferHeight());
            }

            // Save current framebuffer
            Framebuffer mainBuffer = client.getFramebuffer();
            
            // Apply blur shader
            blurShader.render(client.getTickDelta());
            
            // Draw blurred background
            RenderSystem.setShaderTexture(0, mainBuffer.getColorAttachment());
            DrawableHelper.drawTexture(matrices, 0, 0, 0, 0, client.getWindow().getScaledWidth(), 
                client.getWindow().getScaledHeight(), client.getWindow().getScaledWidth(), 
                client.getWindow().getScaledHeight());
                
        } catch (Exception e) {
            // Fallback if shader fails to load
            RenderSystem.setShaderColor(0.25F, 0.25F, 0.25F, 1.0F);
            DrawableHelper.fill(matrices, 0, 0, client.getWindow().getScaledWidth(), 
                client.getWindow().getScaledHeight(), 0x88000000);
        }
    }
    
    public static void close() {
        if (blurShader != null) {
            blurShader.close();
            blurShader = null;
        }
    }
}
