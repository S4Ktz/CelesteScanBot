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
        //Mensagens que serão bloqueadas pelo "bot"
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
        mensagensBloqueadas.add("Estrupadinha");
        mensagensBloqueadas.add("Abusado");
        mensagensBloqueadas.add("Abusada");

    }



    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return; //impedir o boot de ficar em um loop infinito de mensagens

        String MensagemLida = event.getMessage().getContentRaw();
        String CANAL_DE_LOG = "1473012450570801365";

        for (String palavra : mensagensBloqueadas){
            if (MensagemLida.toLowerCase().contains(palavra)){

                EmbedBuilder embedBuilder = new EmbedBuilder();
                //adiciona o titulo do embed
                embedBuilder.setTitle("⚠ Mensagem suspeita detectada ⚠");
                //setColor para selecionar a cor da borda da mensagem
                embedBuilder.setColor(Color.red);
                //addfield para adicionar campo/campos para mensagens, informações, etc.
                embedBuilder.addField("Usuario: ", event.getAuthor().getAsMention(),true);
                embedBuilder.addField("Canal: ",event.getChannel().getAsMention(),true);
                embedBuilder.addField("Palavra:","||"+ palavra +"||",true );
                embedBuilder.addField("Mensagem: ",MensagemLida,false);
                //tipo uma assinatura/segunda informação
                embedBuilder.setFooter("ID DO USUARIO: "+ event.getAuthor().getId());

                //envia a mensagem para o canal de "logs" e apaga a mensagem do usuario
                TextChannel CanalDeLog = event.getGuild().getTextChannelById(CANAL_DE_LOG);


                event.getChannel().sendMessage("** ⚠ UMA MENSAGEM NESSE CHAT FOI REPORTADA ⚠ **").queue();
                event.getMessage().delete().queue();

                if (CanalDeLog != null) {
                    CanalDeLog.sendMessageEmbeds(embedBuilder.build()).queue();
                }

                break;
            }
        }








    }
}
