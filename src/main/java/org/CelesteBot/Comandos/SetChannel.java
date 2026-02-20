package org.CelesteBot.Comandos;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.CelesteBot.CelesteScanBot;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SetChannel extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        if (!event.getName().equals("setchannel") )return;

        if (!Objects.requireNonNull(event.getMember()).hasPermission(Permission.ADMINISTRATOR)) {
            event.reply("Você não tem permissão para usar este comando!").setEphemeral(true).queue();
            return;
        }

        /*long ChannelID;
        TextChannel textChannel = event.getChannel().asTextChannel();*/

       long IdCanal = Objects.requireNonNull(event.getOption("canal")).getAsChannel().getIdLong();
        CelesteScanBot.canalReportID = IdCanal;
        event.reply("Canal De Report Definido").setEphemeral(true).queue();




    }
}
