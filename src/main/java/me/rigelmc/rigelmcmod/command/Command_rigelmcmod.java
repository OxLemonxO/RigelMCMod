package me.rigelmc.rigelmcmod.command;

import me.rigelmc.rigelmcmod.RigelMCMod;
import me.rigelmc.rigelmcmod.config.ConfigEntry;
import me.rigelmc.rigelmcmod.rank.Rank;
import me.rigelmc.rigelmcmod.util.FLog;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.NON_OP, source = SourceType.BOTH)
@CommandParameters(description = "Shows information about RigelMCMod or reloads it.", usage = "/<command> [reload]", aliases = "rmcm")
public class Command_rigelmcmod extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length == 1)
        {
            if (!args[0].equals("reload"))
            {
                return false;
            }

            if (!plugin.al.isAdmin(sender))
            {
                noPerms();
                return true;
            }

            plugin.config.load();
            plugin.services.stop();
            plugin.services.start();

            final String message = String.format("%s v%s reloaded.",
                    RigelMCMod.pluginName,
                    RigelMCMod.pluginVersion);

            msg(message);
            FLog.info(message);
            return true;
        }
        msg("RigelMCMod for 'RigelMC', an all-op freebuild server.", ChatColor.GOLD);
        msg("Version: " + RigelMCMod.pluginVersion, ChatColor.GOLD);
        msg("Compiled by: " + RigelMCMod.compiledBy, ChatColor.GOLD);
        msg("Compile date: " + RigelMCMod.buildDate, ChatColor.GOLD);
        msg("Running on " + ConfigEntry.SERVER_NAME.getString() + ".", ChatColor.GOLD);
        msg("Created by aggelosQQ, AwesomePinch, CreeperSeth, LightWarp, and santadeath.", ChatColor.GOLD);
        msg("Visit " + ChatColor.AQUA + "http://forum.rigelmc.ga" + ChatColor.GREEN + " for more information.", ChatColor.GREEN);
        return true;
    }
}
