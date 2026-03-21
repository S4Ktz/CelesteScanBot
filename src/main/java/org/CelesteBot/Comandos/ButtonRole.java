package org.CelesteBot.Comandos;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import java.awt.*;
import java.util.Objects;


public class ButtonRole extends ListenerAdapter {


    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!event.getName().equals("cargos")) return;

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(" 🌌 Selecione o Cargo 🌌");
        embedBuilder.setThumbnail("https://cdn.discordapp.com/attachments/1455731641820381334/1483133901173162115/208d3e085483d3db426593ddd51565a2.jpg?ex=69bcc755&is=69bb75d5&hm=41c84ed90a1919a839958159174866b3c3ab90d6710c9de996c3dab0ace79e54&");
        embedBuilder.setImage("https://cdn.discordapp.com/attachments/1455731641820381334/1483133901173162115/208d3e085483d3db426593ddd51565a2.jpg?ex=69bcc755&is=69bb75d5&hm=41c84ed90a1919a839958159174866b3c3ab90d6710c9de996c3dab0ace79e54&");
        embedBuilder.setDescription("Clique nos botões abaixo para selecionar seu cargo");
        embedBuilder.setColor(Color.ORANGE);

        event.replyEmbeds(embedBuilder.build())
                .addActionRow(
                        Button.primary("cargo-teste", "Ganhar Cargo 🟢"),// ID do botão e Texto
                        Button.secondary()
                        Button.
                        Button.danger("remover-cargo", "Sair 🔴")
                )
                .setEphemeral(true) // Opcional: só quem usou o comando vê
                .queue();



    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        String id = event.getComponentId();
        Guild guild = event.getGuild();

        long CargoId = id.equals("cargo-teste") ? 1474484289625391177L : 1472959951046574204L;
        Role role = Objects.requireNonNull(guild).getRoleById(CargoId);

        if (role == null){
            event.reply("Cargo não encontrado ❌ ").setEphemeral(true).queue();
            return;
        }
        if (Objects.requireNonNull(event.getMember()).getRoles().contains(role)){
            guild.removeRoleFromMember(event.getUser(),role).queue();
            event.reply("Cargo removido: " + role.getName()).setEphemeral(true).queue();
        }else{
            guild.addRoleToMember(event.getUser(),role).queue();
            event.reply("Adicionado o cargo: " + role.getName()).setEphemeral(true).queue();
        }

    }
}
