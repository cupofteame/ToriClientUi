package com.terraformersmc.modmenu.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import java.util.HashMap;
import java.util.Map;

public class ModsScreen extends Screen {
    private final Map<String, Boolean> modHasConfigScreen = new HashMap<>();
    
    public ModsScreen(Screen parent) {
        super(Text.literal("Mods"));
    }

    public Map<String, Boolean> getModHasConfigScreen() {
        return modHasConfigScreen;
    }
} 