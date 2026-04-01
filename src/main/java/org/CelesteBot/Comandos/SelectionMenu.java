package org.CelesteBot.Comandos;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.GenericSelectMenuInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import org.jetbrains.annotations.NotNull;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;



import java.awt.*;
import java.util.EnumSet;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class SelectionMenu extends ListenerAdapter {
    String textBlock = """
            **NÃO** crie varios ticket,espere que um dos suportes etc... respondam\\s 
            caso quebre isso **TODOS** seus ticket abertos serão fechados e novas tentativas 
            serão ignoradas e caso persista em spammar será castigado ou banido\\n 
            **NÃO** faça denúncias falsas(sem provas ex: prints), caso faça será castigado ou **BANIDO**\\n +
            **CASO** tenha certeza da sua denúncia porem não tem provas(ou quer apenas comentar) utilize a opção de comentar
            """;

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!event.getName().equals("menu-ticket")) return;


        EmbedBuilder embedBuilder = getEmbedBuilder();


        StringSelectMenu menu = StringSelectMenu.create("Abrir Ticket")
                .setPlaceholder("Selecione uma opção")

                .addOption("Abrir Ticket Suporte","Suporte")

                .addOption("Denúncia","Denúncia")

                .addOption("Cargos","Cargos")

                .addOption("Recompensa","Recompensa")

                .build();

        event.replyEmbeds(embedBuilder.build())
                .addActionRow(menu)
                .queue();



    }

    @NotNull
    private static EmbedBuilder getEmbedBuilder() {
        EmbedBuilder embedBuilder = new EmbedBuilder();


        embedBuilder.setTitle("**TICKET**");

        embedBuilder.addField("**INFORMAÇÃO**","Abra um ticket para entrar em contato" +
                " com um **suporte** ou **ADM**",false);

        embedBuilder.addField("**LEIA COM ATENÇÃO**⚠\n","""
            
            
            **NÃO** crie varios ticket,espere que um dos suportes etc... respondam,caso quebre isso **TODOS** seus ticket abertos serão fechados e novas tentativas
            serão ignoradas e caso persista em spammar será castigado ou banido
            
            **NÃO** faça denúncias falsas(sem provas ex: prints), caso faça será castigado ou **BANIDO**
            
            **CASO** tenha certeza da sua denúncia porem não tem provas(ou quer apenas comentar) utilize a opção de comentar
            """
                ,true);


        embedBuilder.setImage("https://i.pinimg.com/1200x/58/a3/a6/58a3a6d80d9c9a242d7a6a241f84ba23.jpg");

        embedBuilder.setThumbnail("https://i.pinimg.com/originals/db/11/e9/db11e9023f39ec8ba622890fcbdd6d41.gif");

        embedBuilder.setFooter(" O ticket será respondido o mais rapido possivel "
                ,"https://i.pinimg.com/originals/98/dd/ad/98ddad0b6d4f92f873af8054d7eb3aa4.gif");

        embedBuilder.setColor(Color.WHITE);

        return embedBuilder;
    }


    @Override
    public void onStringSelectInteraction(@NotNull StringSelectInteractionEvent event){
        if (!event.getComponentId().equals("Abrir Ticket")) return;

        event.editSelectMenu(event.getSelectMenu().createCopy().build()).queue();

       String Valor = event.getValues().getFirst();

        switch (Valor){
            case "Suporte" -> criarCanalTicket(event,"Suporte");

            case "Denúncia" -> criarCanalTicket(event,"Denúncia");

            case "Cargos" -> criarCanalTicket(event,"Cargos");

            case "Recompensa" -> criarCanalTicket(event,"Recompensa");

            default -> event.reply("Esse ticket não existe").setEphemeral(true).queue();
        }
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        if (event.getComponentId().equals("fechar-ticket")){

            event.reply("O ticket está preste a ser fechado").queue();

            event.getChannel().delete().queueAfter(3,TimeUnit.SECONDS);
        }
    }

    private void criarCanalTicket(StringSelectInteractionEvent event, String categoria) {
        Category categoriaTicket = Objects.requireNonNull(event.getGuild()).getCategoryById(1488270738891997194L);
        Objects.requireNonNull(event.getGuild()).createTextChannel("ticket - " + event.getUser().getEffectiveName())
                .setParent(categoriaTicket)
                .addMemberPermissionOverride(event.getUser().getIdLong(),
                        EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND), null)
                .addRolePermissionOverride(event.getGuild().getPublicRole().getIdLong(),
                        null, EnumSet.of(Permission.VIEW_CHANNEL))
                .queue(canal -> {
                    event.getHook().sendMessage("Ticket de " + categoria + "aberto: " + canal.getAsMention())
                                    .setEphemeral(true).queue();
                    /*event.getHook.sendMessage("Ticket de " + categoria + " aberto: " + canal.getAsMention())
                            .setEphemeral(true).queue();*/

                    canal.sendMessage("Olá " + event.getUser().getAsMention() + "! Aguarde o suporte para seu Ticket de " + categoria + ".")
                            .addActionRow(Button.danger("fechar-ticket", "Fechar Ticket 🔒"))
                            .queue();
                });

    }
    }

