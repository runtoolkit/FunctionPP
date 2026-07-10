package com.runtoolkit.datalib.features;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.ServerCommandSource;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

/**
 * Feature 1: Command Alias System
 * Allows datapack creators to define custom command aliases for complex mcfunctions
 */
public class CommandAliasSystem {
    private static final CommandAliasRegistry aliasRegistry = new CommandAliasRegistry();

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            registerAliasCommand(dispatcher);
        });
    }

    private static void registerAliasCommand(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> command = LiteralArgumentBuilder
            .literal("dalias")
            .requires(source -> source.hasPermissionLevel(2))
            .executes(context -> {
                context.getSource().sendFeedback(
                    () -> net.minecraft.text.Text.literal("§6Datapack Alias System - Type /dalias help"),
                    false
                );
                return 1;
            });

        dispatcher.register(command);
    }

    public static CommandAliasRegistry getRegistry() {
        return aliasRegistry;
    }
}
