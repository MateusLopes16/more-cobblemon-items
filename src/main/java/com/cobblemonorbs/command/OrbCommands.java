package com.cobblemonorbs.command;

import com.cobblemonorbs.CobblemonOrbs;
import com.cobblemonorbs.config.ConfigFileManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

/**
 * Commands for managing Cobblemon Orbs configuration.
 */
@EventBusSubscriber(modid = CobblemonOrbs.MOD_ID)
public class OrbCommands {
    
    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        
        dispatcher.register(Commands.literal("cobblemonorbs")
            .requires(source -> source.hasPermission(2)) // Requires OP level 2
            .then(Commands.literal("reload")
                .executes(OrbCommands::reloadConfig))
            .then(Commands.literal("info")
                .executes(OrbCommands::showInfo))
        );
    }
    
    private static int reloadConfig(CommandContext<CommandSourceStack> context) {
        ConfigFileManager.reload();
        context.getSource().sendSuccess(
            () -> Component.literal("§a[Cobblemon Orbs] Configuration reloaded!"),
            true
        );
        CobblemonOrbs.LOGGER.info("Configuration reloaded by {}", 
            context.getSource().getTextName());
        return 1;
    }
    
    private static int showInfo(CommandContext<CommandSourceStack> context) {
        context.getSource().sendSuccess(
            () -> Component.literal("§6[Cobblemon Orbs] Config Location:§r " + 
                ConfigFileManager.getConfigDir().toAbsolutePath()),
            false
        );
        context.getSource().sendSuccess(
            () -> Component.literal("§6[Cobblemon Orbs]§r Edit files in /recipes/ and /items/ folders"),
            false
        );
        context.getSource().sendSuccess(
            () -> Component.literal("§6[Cobblemon Orbs]§r Use /cobblemonorbs reload after changes"),
            false
        );
        return 1;
    }
}
