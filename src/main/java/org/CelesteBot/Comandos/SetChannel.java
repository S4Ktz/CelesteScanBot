package org.CelesteBot.Comandos;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.CelesteBot.CelesteScanBot;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SetChannel extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        if (!event.getName().equals("setchannel"))return;

        /*long ChannelID;
        TextChannel textChannel = event.getChannel().asTextChannel();*/

       long IdCanal = Objects.requireNonNull(event.getOption("ChannelID")).getAsChannel().getIdLong();
        CelesteScanBot.canalReportID = IdCanal;
        event.reply("Canal De Report Definido").setEphemeral(true).queue();




    }
}
