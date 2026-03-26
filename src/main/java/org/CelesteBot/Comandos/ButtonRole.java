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

        EmbedBuilder embedBuilder = getEmbedBuilder();

        event.replyEmbeds(embedBuilder.build())
                .addActionRow(
                        //Button.primary("cargo-teste", "Ganhar Cargo 🟢"),// ID do botão e Texto
                        Button.primary("cargo-anúncio","Anúncio 📢"),
                        Button.primary("cargo-evento","Evento 🎉"),
                        Button.primary("cargo-repost","Repost 📰"),
                        Button.primary("cargo-campeonato","Campeonato 🏆"),
                        Button.danger("remover-cargo", "Remover cargos 🔴")
                )
                .setEphemeral(true) // Opcional: só quem usou o comando vê
                .queue();



    }

    @NotNull
    private static EmbedBuilder getEmbedBuilder() {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(" 🌌 Selecione o Cargo 🌌");
        embedBuilder.setThumbnail("https://cdn.discordapp.com/attachments/1455731641820381334/1483133901173162115/208d3e085483d3db426593ddd51565a2.jpg?ex=69bcc755&is=69bb75d5&hm=41c84ed90a1919a839958159174866b3c3ab90d6710c9de996c3dab0ace79e54&");
        embedBuilder.setImage("https://cdn.discordapp.com/attachments/1455731641820381334/1483133901173162115/208d3e085483d3db426593ddd51565a2.jpg?ex=69bcc755&is=69bb75d5&hm=41c84ed90a1919a839958159174866b3c3ab90d6710c9de996c3dab0ace79e54&");
        embedBuilder.setFooter("Clique nos botões abaixo para selecionar seu cargo");
        embedBuilder.setColor(Color.BLACK);
        return embedBuilder;
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        String idButton = event.getComponentId();
        String idCargo = "";

        switch (idButton){
            case "cargo-anúncio" -> {

               String idCargoAnuncio = "1481988042515550288";
                Role role = Objects.requireNonNull(event.getGuild()).getRoleById(idCargoAnuncio);

                if (role != null){
                    if (Objects.requireNonNull(event.getMember()).getRoles().contains(role)){
                        event.getGuild().removeRoleFromMember(event.getMember(),role).queue();
                            event.reply("Cargo ** Anúncio ** removido!").setEphemeral(true).queue();

                    }else{
                        event.getGuild().addRoleToMember(event.getMember(),role).queue();
                            event.reply("Cargo ** Anúncio ** adicionado!! ").setEphemeral(true).queue();
                    }
                }
            }
            case "cargo-evento" ->{
                idCargo = "1481285092981608491";
            }
            case "cargo-repost" -> {
                idCargo = "1481285210439155732";
            }
            case "cargo-campeonato" ->{
                idCargo = "1481988125516496966";
            }
            case "remover-cargo" -> {

                //event.reply("Está função estará disponível em breve").setEphemeral(true).queue();
                return;
            }
            default -> {return;}

        }

        Role role = Objects.requireNonNull(event.getGuild()).getRoleById(idCargo);

        if (role == null){
            event.reply("Erro: Cargo não encontrado ou não existe").setEphemeral(true).queue();
            return;
        }
        event.getGuild().addRoleToMember(event.getUser(),role).queue();
        event.reply("Cargo: " + "**" + role.getName() + "**" + " Adicionado com sucesso")
                .setEphemeral(true).queue();


    }
}
