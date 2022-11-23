package SlopSrc;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.RestAction;

import java.io.File;

public class CommandShortcuts extends ListenerAdapter {

    public void sendMessage(MessageReceivedEvent event, String message, boolean isReply) {
        if(!isReply) {
            event.getChannel().sendMessage(message).queue();
        } else {
            event.getMessage().reply(message).queue();
        }
    }

    //For delays and other specific rest action events
    public RestAction<?> sendMessageQueue(MessageReceivedEvent event, String message, boolean isReply) {
        if(isReply) {
            return event.getMessage().reply(message);
        } else {
            return event.getChannel().sendMessage(message);
        }

    }

    //Overloaded method for files
    public void sendMessage(MessageReceivedEvent event, File f) { event.getChannel().sendFile(f).queue(); }

    //Overloaded method for embeds
    public void sendMessage(MessageReceivedEvent event, MessageEmbed embed) { event.getChannel().sendMessageEmbeds(embed).queue(); }

    //Overloaded for embed attachments
    public void sendMessage(MessageReceivedEvent event, MessageEmbed embed, File f) { event.getChannel().sendMessageEmbeds(embed).addFile(f).queue(); }

    public void deleteMessage(MessageReceivedEvent event) { event.getMessage().delete().queue(); }

    public void react(MessageReceivedEvent event, String emote) { event.getMessage().addReaction(emote).queue(); }

    public void sendDM(MessageReceivedEvent event, String message) { event.getAuthor().openPrivateChannel().flatMap(channel -> channel.sendMessage(message)).queue(); }

    //Overloaded method for specific user DM
    public void sendDM(MessageReceivedEvent event, User user, String message) { user.openPrivateChannel().flatMap(channel -> channel.sendMessage(message)).queue(); }

    public RestAction<?> sendEmbedQueue(MessageReceivedEvent event, MessageEmbed embed) { return event.getChannel().sendMessageEmbeds(embed); }

}
