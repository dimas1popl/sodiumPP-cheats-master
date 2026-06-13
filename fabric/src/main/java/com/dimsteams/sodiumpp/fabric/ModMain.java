package com.dimsteams.sodiumpp.fabric;

import com.dimsteams.sodiumpp.Constants;
import com.dimsteams.sodiumpp.common.Events;
import com.dimsteams.sodiumpp.common.ModLoaderBridge;
import com.dimsteams.sodiumpp.common.ModLoaderBridgeInstance;
import com.dimsteams.sodiumpp.common.WrappedRegistry;
import com.dimsteams.sodiumpp.font.SystemFonts;
import com.dimsteams.sodiumpp.modules.Modules;
import com.dimsteams.sodiumpp.modules.utilities.Profiles;
import com.dimsteams.sodiumpp.utils.DebugScreenExtensions;
import com.dimsteams.sodiumpp.webui.ConfigHttpServer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientChunkEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Collection;

public class ModMain implements ClientModInitializer {

    public ModMain() {
        ModLoaderBridgeInstance.init(new Bridge());
    }

    @Override
    public void onInitializeClient() {
        setupFabricEvents();

        SystemFonts.initAsync();
        Profiles.instance.init();
        ConfigHttpServer.instance.start();
        Modules.registerKeyBindings();
        Modules.register();
        Events.RegisterKeyBindings.trigger(KeyMappingHelper::registerKeyMapping);
        DebugScreenExtensions.register();
    }

    private void setupFabricEvents() {
        ClientChunkEvents.CHUNK_LOAD.register((_, chunk) -> {
            Events.RawChunkLoaded.trigger(chunk);
        });
        ClientChunkEvents.CHUNK_UNLOAD.register((_, chunk) -> {
            Events.RawChunkUnloaded.trigger(chunk);
        });
    }

    private static class Bridge implements ModLoaderBridge {

        @Override
        public WrappedRegistry<Block> getBlockRegistry() {
            return new VanillaWrapperRegistry<>(BuiltInRegistries.BLOCK);
        }

        @Override
        public WrappedRegistry<Item> getItemRegistry() {
            return new VanillaWrapperRegistry<>(BuiltInRegistries.ITEM);
        }

        @Override
        public WrappedRegistry<EntityType<?>> getEntityTypeRegistry() {
            return new VanillaWrapperRegistry<>(BuiltInRegistries.ENTITY_TYPE);
        }

        @Override
        public WrappedRegistry<MobEffect> getMobEffectRegistry() {
            return new VanillaWrapperRegistry<>(BuiltInRegistries.MOB_EFFECT);
        }

        @Override
        public boolean isProduction() {
            return !FabricLoader.getInstance().isDevelopmentEnvironment();
        }

        @Override
        public String getModLoaderName() {
            return "Fabric";
        }

        @Override
        public String getModLoaderVersion() {
            return FabricLoader.getInstance().getModContainer("fabricloader").orElseThrow().getMetadata().getVersion().getFriendlyString();
        }

        @Override
        public String getModVersion() {
            return FabricLoader.getInstance().getModContainer(Constants.MOD_ID).orElseThrow().getMetadata().getVersion().getFriendlyString();
        }

        @Override
        public int getModCount() {
            return FabricLoader.getInstance().getAllMods().size();
        }

        @Override
        public boolean hasMod(String modId) {
            return FabricLoader.getInstance().isModLoaded(modId);
        }
    }

    private record VanillaWrapperRegistry<T>(Registry<T> registry) implements WrappedRegistry<T> {

        @Override
        public Identifier getKey(T value) {
            return registry.getKey(value);
        }

        @Override
        public T getValue(Identifier id) {
            return registry.getValue(id);
        }

        @Override
        public Collection<T> getValues() {
            return registry.keySet().stream().map(this::getValue).toList();
        }
    }
}