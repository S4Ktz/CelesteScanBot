package org.CelesteBot;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.CelesteBot.Comandos.ButtonRole;
import org.CelesteBot.Comandos.EmbedCreate;
import org.CelesteBot.Comandos.SelectionMenu;
import org.CelesteBot.Comandos.SetChannel;
import org.CelesteBot.Eventos.MessageScanner;


import javax.security.auth.login.LoginException;


public class CelesteScanBot {

    private final Dotenv config;
    private final ShardManager shardManager;
    public static long canalReportID = 0L;

    public CelesteScanBot() throws LoginException {
        config = Dotenv.configure().load();
        String TOKEN = config.get("TOKEN");

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(TOKEN);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.customStatus("Criado por: sakdeveloperx"));
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        shardManager = builder.build();









        //Registar eventos/comandos
        shardManager.addEventListener(new MessageScanner());
        shardManager.addEventListener(new SetChannel());
        shardManager.addEventListener(new ButtonRole());
        shardManager.addEventListener(new SelectionMenu());
        shardManager.addEventListener(new EmbedCreate());

        //adicionar comandos
        shardManager.getShards().getFirst().upsertCommand("setchannel","Seleciona o canal de reports")
                .addOption(OptionType.CHANNEL,"canal","selecione o canal",true)
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)).queue();

        shardManager.getShards().getFirst().upsertCommand("cargos","Selecionar cargo").queue();
                //.addOption(OptionType.CHANNEL,"cargos","Selecione cargo")
                //.setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)).queue();
        shardManager.getShards().getFirst().upsertCommand("menu-ticket","Criar o menu de ticket")
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)).queue();

        OptionData opcaoCores = new OptionData(OptionType.STRING, "cor", "Escolha a cor da borda do embed", true)
                .addChoice("Roxo", "roxo")
                .addChoice("Azul", "azul")
                .addChoice("Vermelho", "vermelho")
                .addChoice("Verde", "verde")
                .addChoice("Branco", "branco")
                .addChoice("Preto", "preto")
                .addChoice("Cinza", "cinza")
                .addChoice("Cinza-escuro", "cinza-escuro")
                .addChoice("Rosa", "rosa")
                .addChoice("Amarelo", "amarelo")
                .addChoice("Laranja", "laranja")
                .addChoice("Ciano", "ciano");


        shardManager.getShards().getFirst().upsertCommand(
                Commands.slash("embed", "Crie seu próprio embed")
                        .addOptions(opcaoCores)
        ).queue();




    }

    public ShardManager getShardManager(){
        return shardManager;

    }

    public static void main(String[] args) {
        try{
            CelesteScanBot celesteScanBot = new CelesteScanBot();
        } catch (LoginException e) {
            System.out.println(" ERRO: TOKEN PROVAVELMENTE INVALIDO ");
        }

        }
    }
