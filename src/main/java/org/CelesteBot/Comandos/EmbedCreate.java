package org.CelesteBot.Comandos;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.awt.*;
import java.util.Objects;

public class EmbedCreate extends ListenerAdapter {

    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent event) {
        if (!event.getModalId().equals("modal-embed")) {

            String corEscolhida = event.getModalId().split(":")[1];

            String titulo = Objects.requireNonNull(event.getValue("titulo")).toString();
            String desc = event.getValue("descricao").toString();

            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle(titulo);
            embedBuilder.setDescription(desc);
            embedBuilder.setColor(Color.)


            switch (corEscolhida){

                case "roxo" -> embedBuilder.setColor(Color.magenta);
                case "azul" -> embedBuilder.setColor(Color.blue);
                case "vermelho" -> embedBuilder.setColor(Color.red);
                case "verde" -> embedBuilder.setColor(Color.green);
                case "branco" -> embedBuilder.setColor(Color.white);
                case "preto" -> embedBuilder.setColor(Color.black);
                case "cinza" -> embedBuilder.setColor(Color.GRAY);
                case "cinza-escuro" -> embedBuilder.setColor(Color.darkGray);
                

            }
        }
    }

    @Override
    public void onStringSelectInteraction(@NotNull StringSelectInteractionEvent event) {
        if (!event.getComponentId().equals("menu-cores"))
            return;

        String corEscolhida = event.getValues().get(0);

        TextInput titulo = TextInput.create("embed-titulo", "titulo", TextInputStyle.SHORT).build();
        TextInput descricao = TextInput.create("embed-desc", "descricao", TextInputStyle.PARAGRAPH).build();

        Modal modal = Modal.create("modal-embed" + corEscolhida, "Configurar Embed")
                .addComponents(ActionRow.of(titulo), ActionRow.of(descricao))
                .build();

        event.replyModal(modal).queue();
    }

    /*@Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!event.getName().equals("embed")){
            TextInput titulo = TextInput.create("embed-titulo","titulo", TextInputStyle.SHORT)
                    .setPlaceholder("coloque o titulo")
                    .setRequired(true)
                    .build();

            TextInput cor = TextInput.create("embed-cor","Cor (Ex:#1234567890)",TextInputStyle.SHORT)
                    .setPlaceholder("#")
                    .setRequired(false)
                    .build();

            TextInput descricao = TextInput.create("embed-desc","descrição",TextInputStyle.PARAGRAPH)
                    .setPlaceholder("coloque a descrição")
                    .setRequired(true)
                    .build();

            TextInput imagem = TextInput.create("embed-imagem","Imagem",TextInputStyle.PARAGRAPH)
                    .setPlaceholder("Coloque o link da imagem")
                    .setRequired(false)
                    .build();

            Modal modal = Modal.create("gerador-embed","criar embed")
                    .addComponents(ActionRow.of(titulo),ActionRow.of(cor),ActionRow.of(descricao),ActionRow.of(imagem))
                    .build();

            event.replyModal(modal).queue();
        }
            */





    }

