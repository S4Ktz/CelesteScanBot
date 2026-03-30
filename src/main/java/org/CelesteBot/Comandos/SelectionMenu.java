package org.CelesteBot.Comandos;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.GenericSelectMenuInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;

public class SelectionMenu extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!event.getName().equals("menu-ticket")) return;



        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("**TICKET**");
        embedBuilder.addField("**INFORMAÇÃO**","Abra um ticket para ter suporte e entrar em contato" +
                " com um **suporte** ou **ADM**",false);
        embedBuilder.setImage("https://i.pinimg.com/1200x/58/a3/a6/58a3a6d80d9c9a242d7a6a241f84ba23.jpg");
        embedBuilder.setThumbnail("https://i.pinimg.com/originals/db/11/e9/db11e9023f39ec8ba622890fcbdd6d41.gif");
        embedBuilder.setColor(Color.WHITE);



        StringSelectMenu menu = StringSelectMenu.create("Abrir Ticket")
                .setPlaceholder("Selecione uma opção")
                .addOption("Suporte","Obtenha Suporte")
                //.addOption("")
                .build();

        event.replyEmbeds(embedBuilder.build())
                .addActionRow(menu)
                .queue();



    }

    @Override
    public void onGenericSelectMenuInteraction(@NotNull GenericSelectMenuInteractionEvent event) {
        Objects.equals(event.getSelectMenu().getId(), "Abrir Ticket");

        //switch (event.getInteraction().getValues().get(0)){

        }
    }

