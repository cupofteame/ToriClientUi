package com.terraformersmc.modmenu.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;

public class OverlayMenuScreen extends Screen {
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 20;
    private static final int BUTTON_SPACING = 4;
    
    public OverlayMenuScreen() {
        super(Text.literal(""));
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;
        
        // Calculate positions for centered buttons
        int firstButtonY = centerY - BUTTON_HEIGHT - BUTTON_SPACING/2;
        int secondButtonY = centerY + BUTTON_SPACING/2;
        
        // Add Mods button
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Mods"), button -> {
            MinecraftClient.getInstance().setScreen(new ModsScreen(this));
        })
        .position(centerX - BUTTON_WIDTH/2, firstButtonY)
        .size(BUTTON_WIDTH, BUTTON_HEIGHT)
        .build());
        
        // Add Tools button
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Tools"), button -> {
            // TODO: Implement tools screen
        })
        .position(centerX - BUTTON_WIDTH/2, secondButtonY)
        .size(BUTTON_WIDTH, BUTTON_HEIGHT)
        .build());
    }

    @Override
    public void renderBackground(MatrixStack matrices) {
        if (this.client.world != null) {
            fillGradient(matrices, 0, 0, this.width, this.height, 0x80101010, 0x80101010);
        } else {
            super.renderBackground(matrices);
        }
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
} 