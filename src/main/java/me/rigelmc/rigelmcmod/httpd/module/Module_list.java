package me.rigelmc.rigelmcmod.httpd.module;

import java.util.Collection;
import me.rigelmc.rigelmcmod.RigelMCMod;
import me.rigelmc.rigelmcmod.config.ConfigEntry;
import me.rigelmc.rigelmcmod.httpd.NanoHTTPD;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Module_list extends HTTPDModule
{

    public Module_list(RigelMCMod plugin, NanoHTTPD.HTTPSession session)
    {
        super(plugin, session);
    }

    @Override
    public String getBody()
    {
        final StringBuilder body = new StringBuilder();

        final Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();

        body.append("<p>There are ").append(onlinePlayers.size()).append("/").append(Bukkit.getMaxPlayers()).append(" players online:</p>\r\n");

        body.append("<ul>\r\n");

        for (Player player : onlinePlayers)
        {
            String tag = plugin.rm.getDisplay(player).getTag();
            body.append("<li>").append(tag).append(player.getName()).append("</li>\r\n");
        }

        body.append("</ul>\r\n");

        return body.toString();
    }

    @Override
    public String getTitle()
    {
        return ConfigEntry.SERVER_NAME.getString() + " - Online Users";
    }
}
