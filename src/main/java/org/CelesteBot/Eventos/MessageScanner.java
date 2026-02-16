package org.CelesteBot.Eventos;


import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageScanner extends ListenerAdapter {
        //lista de mensagens que o bot tem que bloquear
       private final List<String> mensagensBloqueadas = Arrays.asList(
               "Gordo",
               "Preto",
               "Macaco",
               "Gorda",
               "Imenso"

       );



    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return; //impedir o boot de ficar em um loop infinito de mensagens
        String MensagemLida = event.getMessage().getContentRaw();

        for (String palavra : mensagensBloqueadas){
            if (MensagemLida.contains(palavra)){
                //bot tem que analisar a mensagem e deletar ela e enviar uma mensagem de aviso para o usuario
                //pegar o canal em que a mensagem foi enviada e enviar no mesmo canal a mensagem de aviso
                event.getMessage().delete().queue();
                event.getChannel().sendMessage("Mensagem Impropria detectada!").queue();

                break;
            }
        }








    }
}
