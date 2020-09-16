package com.focamacho.dupefixproject.event;

import com.focamacho.dupefixproject.util.LoadedFixes;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class EntityJoinWorldEvent {

    @SubscribeEvent
    public void onPlayerJoinWorld(net.minecraftforge.event.entity.EntityJoinWorldEvent event) {
        if(event.getEntity() instanceof EntityPlayerMP) {
            EntityPlayerMP entity = (EntityPlayerMP) event.getEntity();
            if(FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getOppedPlayers().getEntry(entity.getGameProfile()) != null) {
                String modFixesNotLoaded = LoadedFixes.getModFixesNotLoaded();
                if(!modFixesNotLoaded.isEmpty()) {
                    List<TextComponentString> message = new ArrayList<>();
                    message.add(new TextComponentString("§bDupeFix Project §cfailed to load some fixes"));
                    message.add(new TextComponentString("§cThe following mods have their fixes not loaded:"));
                    message.add(new TextComponentString("§4" + modFixesNotLoaded));
                    message.add(new TextComponentString("§cVisit the mod wiki to learn how to configure it"));

                    TextComponentString clickableUrl = new TextComponentString("§bWiki: §khttps://github.com/Focamacho/DupeFix-Project/wiki");
                    clickableUrl.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/Focamacho/DupeFix-Project/wiki"));
                    message.add(clickableUrl);

                    message.forEach(entity::sendMessage);
                }
            }
        }
    }

}
