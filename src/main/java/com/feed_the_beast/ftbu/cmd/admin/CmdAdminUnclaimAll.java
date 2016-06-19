package com.feed_the_beast.ftbu.cmd.admin;

import com.feed_the_beast.ftbl.api.ForgePlayer;
import com.feed_the_beast.ftbl.api.ForgePlayerMP;
import com.feed_the_beast.ftbl.api.ForgeWorldMP;
import com.feed_the_beast.ftbl.api.cmd.CommandLM;
import com.feed_the_beast.ftbu.world.FTBUWorldDataMP;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nonnull;

public class CmdAdminUnclaimAll extends CommandLM
{
    public CmdAdminUnclaimAll()
    {
        super("admin_unclaim_all_chunks");
    }

    @Nonnull
    @Override
    public String getCommandUsage(@Nonnull ICommandSender ics)
    {
        return '/' + commandName + " <player | @a>";
    }

    @Override
    public boolean isUsernameIndex(String[] args, int i)
    {
        return i == 0;
    }

    @Override
    public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender ics, @Nonnull String[] args) throws CommandException
    {
        checkArgs(args, 1);

        if(args[0].equals("@a"))
        {
            for(ForgePlayer p : ForgeWorldMP.inst.playerMap.values())
            {
                FTBUWorldDataMP.unclaimAllChunks(p.toMP(), null);
            }
            ics.addChatMessage(new TextComponentString("Unclaimed all chunks"));
            return;
        }

        ForgePlayerMP p = ForgePlayerMP.get(args[0]);
        FTBUWorldDataMP.unclaimAllChunks(p, null);
        ics.addChatMessage(new TextComponentString("Unclaimed all " + p.getProfile().getName() + "'s chunks"));
    }
}