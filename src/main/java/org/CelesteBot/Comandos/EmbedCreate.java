package org.CelesteBot.Comandos;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
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
        if (!event.getModalId().equals("gerador-embed")){

            String titulo = Objects.requireNonNull(event.getValue("embed-titulo")).toString();

            String corHex = Objects.requireNonNull(event.getValue("embed-cor")).toString();

            String desc = Objects.requireNonNull(event.getValue("embed-desc")).toString();

            String imagemUrl = Objects.requireNonNull(event.getValue("embed-imagem")).toString();

            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle(titulo);
            embedBuilder.setDescription(desc);

            try {
                embedBuilder.setColor(Color.decode(corHex.startsWith("#") ? corHex : "#" + corHex));
            }catch (Exception e){
                embedBuilder.setColor(Color.WHITE);
            }

            if (imagemUrl != null && imagemUrl.startsWith("http")){
                embedBuilder.setImage(imagemUrl);
            }

            event.replyEmbeds(embedBuilder.build()).queue();

    }
    }


    @Override
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




    }
}
