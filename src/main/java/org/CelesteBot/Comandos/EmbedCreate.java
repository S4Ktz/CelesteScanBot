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

import java.awt.*;
import java.util.Objects;

public class EmbedCreate extends ListenerAdapter {

    // AGORA ESTE MÉTODO SE TORNOU ÚNICO E SÓ USA O SEU SWITCH!
    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent event) {
        if (event.getModalId().startsWith("modal-embed:")) {

            // Extrai a cor do ID (Seja vindo do Menu ou do Slash Command)
            String corEscolhida = event.getModalId().split(":")[1];

            String titulo = Objects.requireNonNull(event.getValue("embed-titulo")).getAsString();
            String desc = Objects.requireNonNull(event.getValue("embed-desc")).getAsString();

            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle(titulo);
            embedBuilder.setDescription(desc);


            switch (corEscolhida) {
                case "roxo" -> embedBuilder.setColor(Color.magenta);
                case "azul" -> embedBuilder.setColor(Color.blue);
                case "vermelho" -> embedBuilder.setColor(Color.red);
                case "verde" -> embedBuilder.setColor(Color.green);
                case "branco" -> embedBuilder.setColor(Color.white);
                case "preto" -> embedBuilder.setColor(Color.black);
                case "cinza" -> embedBuilder.setColor(Color.GRAY);
                case "cinza-escuro" -> embedBuilder.setColor(Color.darkGray);
                case "rosa" -> embedBuilder.setColor(Color.pink);
                case "amarelo" -> embedBuilder.setColor(Color.yellow);
                case "laranja" -> embedBuilder.setColor(Color.orange);
                case "ciano" -> embedBuilder.setColor(Color.cyan);
                default -> embedBuilder.setColor(Color.white);
            }

            event.replyEmbeds(embedBuilder.build()).queue();
        }
    }

    @Override
    public void onStringSelectInteraction(@NotNull StringSelectInteractionEvent event) {
        if (!event.getComponentId().equals("menu-cores")) return;

        String corEscolhida = event.getValues().getFirst();

        TextInput titulo = TextInput.create("embed-titulo", "Título", TextInputStyle.SHORT).build();
        TextInput descricao = TextInput.create("embed-desc", "Descrição", TextInputStyle.PARAGRAPH).build();

        Modal modal = Modal.create("modal-embed:" + corEscolhida, "Configurar Embed")
                .addComponents(ActionRow.of(titulo), ActionRow.of(descricao))
                .build();

        event.replyModal(modal).queue();
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!event.getName().equals("embed")) return;


        String corEscolhida = Objects.requireNonNull(event.getOption("cor")).getAsString();

        TextInput titulo = TextInput.create("embed-titulo", "Título", TextInputStyle.SHORT)
                .setPlaceholder("Coloque o título")
                .setRequired(true)
                .build();

        TextInput descricao = TextInput.create("embed-desc", "Descrição", TextInputStyle.PARAGRAPH)
                .setPlaceholder("Coloque a descrição")
                .setRequired(true)
                .build();

        // Enviamos o modal usando o mesmo padrão do menu de seleção!
        Modal modal = Modal.create("modal-embed:" + corEscolhida, "Criar Embed")
                .addComponents(ActionRow.of(titulo), ActionRow.of(descricao))
                .build();

        event.replyModal(modal).queue();
    }
}