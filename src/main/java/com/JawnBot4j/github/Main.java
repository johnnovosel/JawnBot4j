package com.JawnBot4j.github;

import discord4j.core.DiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;

public class Main {
    public static void main(String[] args) {


        DiscordClient.create(System.getenv("BOT_TOKEN"))
                .withGateway(client -> {
                    client.on(ReadyEvent.class)
                            .subscribe(ready -> System.out.println("Logged in as " + ready.getSelf().getUsername()));

                    client.on(MessageCreateEvent.class)
                            .subscribe(event -> {
                                Message message = event.getMessage();
                                if (message.getContent().equals("!ping")) {
                                    message.getChannel().block().createMessage("Pong!").block();
                                }
                            });

                    return client.onDisconnect();
                })
                .block();
    }
}