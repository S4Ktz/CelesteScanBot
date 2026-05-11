package org.CelesteBot.Comandos;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

public class EmbedCreate extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!event.getName().equals("embed")){
            TextInput titulo = TextInput.create("Embed-titulo","titulo", TextInputStyle.SHORT)
                    .setPlaceholder("coloque o titulo")
                    .setRequired(true)
                    .build();

            TextInput cor = TextInput.create("Embed-cor","Cor (Ex:#1234567890)",TextInputStyle.SHORT)
                    .setPlaceholder("#")
                    .setRequired(false)
                    .build();

            TextInput descricao = TextInput.create("Embed-desc","descrição",TextInputStyle.PARAGRAPH)
                    .setPlaceholder("coloque a descrição")
                    .setRequired(true)
                    .build();

            TextInput imagem = TextInput.create("Embed-imagem","Imagem",TextInputStyle.PARAGRAPH)
                    .setPlaceholder("Coloque o link da imagem")
                    .setRequired(false)
                    .build();

            Modal modal = Modal.create("Gerador-embed","criar embed")
                    .addComponents(ActionRow.of(titulo),ActionRow.of(cor),)
        }
    }
}
