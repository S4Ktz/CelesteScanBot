package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

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
        shardManager = builder.build();


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
