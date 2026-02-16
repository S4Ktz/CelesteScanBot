package org.CelesteBot;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.CelesteBot.Eventos.MessageScanner;

import javax.security.auth.login.LoginException;


public class CelesteScanBot {

    private final Dotenv config;
    private final ShardManager shardManager;

    public CelesteScanBot() throws LoginException {
        config = Dotenv.configure().load();
        String TOKEN = config.get("TOKEN");

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(TOKEN);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.customStatus("Criado por: sakdeveloperx"));
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        shardManager = builder.build();



        //Registar eventos
        shardManager.addEventListener(new MessageScanner());

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
