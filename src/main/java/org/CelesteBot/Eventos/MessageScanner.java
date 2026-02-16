package org.CelesteBot.Eventos;


import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MessageScanner extends ListenerAdapter {
        //lista de mensagens que o "bot" tem que bloquear
       private final List<String> mensagensBloqueadas;

    public MessageScanner() {
        this.mensagensBloqueadas = new ArrayList<>();
        mensagensBloqueadas.add("gordo");
        mensagensBloqueadas.add("preto");
        mensagensBloqueadas.add("macaco");
        mensagensBloqueadas.add("puta");
        mensagensBloqueadas.add("filha da puta");
        mensagensBloqueadas.add("gorda");
        mensagensBloqueadas.add("imensa");
        mensagensBloqueadas.add("imenso");
        mensagensBloqueadas.add("escuridão");
        mensagensBloqueadas.add("baleia");
        mensagensBloqueadas.add("vagabunda");
        mensagensBloqueadas.add("macaca");
        mensagensBloqueadas.add("viado");
        mensagensBloqueadas.add("viadinho");
        mensagensBloqueadas.add("negresco");
        mensagensBloqueadas.add("petróleo");
        mensagensBloqueadas.add("vadia");
        mensagensBloqueadas.add("");

    }



    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return; //impedir o boot de ficar em um loop infinito de mensagens

        String MensagemLida = event.getMessage().getContentRaw();
        String CANAL_DE_LOG = "1473012450570801365";

        for (String palavra : mensagensBloqueadas){
            if (MensagemLida.toLowerCase().contains(palavra)){

                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setTitle("⚠ Mensagem suspeita detectada ⚠");
                embedBuilder.setColor(Color.red);
                embedBuilder.addField("Usuario: ", event.getAuthor().getAsMention(),true);
                embedBuilder.addField("Canal: ",event.getChannel().getAsMention(),true);
                embedBuilder.addField("Palavra:","||"+ palavra +"||",true );
                embedBuilder.addField("Mensagem: ",MensagemLida,false);
                embedBuilder.setFooter("ID DO USUARIO: "+ event.getAuthor().getId());

                TextChannel CanalDeLog = event.getGuild().getTextChannelById(CANAL_DE_LOG);
                event.getMessage().delete().queue();

                if (CanalDeLog != null) {
                    CanalDeLog.sendMessageEmbeds(embedBuilder.build()).queue();
                }

                break;
            }
        }








    }
}
