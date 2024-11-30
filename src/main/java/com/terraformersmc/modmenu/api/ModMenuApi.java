package com.terraformersmc.modmenu.api;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import java.util.Map;
import java.util.function.Consumer;

public interface ModMenuApi {
	/**
	 * Used to construct a new config screen instance when your mod's
	 * configuration button is selected on the mod menu screen.
	 *
	 * @return A factory for constructing config screen instances.
	 */
	default ConfigScreenFactory<?> getModConfigScreenFactory() {
		return screen -> null;
	}

	/**
	 * Used to provide config screen factories for other mods. This takes second
	 * priority to a mod's own config screen factory provider.
	 *
	 * @return a map of mod ids to screen factories.
	 */
	default Map<String, ConfigScreenFactory<?>> getProvidedConfigScreenFactories() {
		return ImmutableMap.of();
	}

	/**
	 * Used to mark mods with a badge indicating that they are
	 * provided by a modpack.
	 */
	default void attachModpackBadges(Consumer<String> consumer) {
	}
}
