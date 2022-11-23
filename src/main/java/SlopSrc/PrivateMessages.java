package SlopSrc;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public class PrivateMessages extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        final String prefix = "s$";
        CommandShortcuts scts = new CommandShortcuts();

        switch (args[0]) {
            case prefix + "slop":
                scts.sendMessage(event, "slopped", false);
                break;
            case prefix + "saymyname":
                scts.sendMessage(event, event.getAuthor().getAsMention(), false);
                break;
            case prefix + "servers":
                List<Guild> guild = event.getJDA().getGuilds();
                String guilds = "";
                for (int i = 0; i < guild.size(); i++) {
                    guilds += guild.get(i).getName() + " \n";
                }
                scts.sendMessage(event, guilds, false);
                break;
            case prefix:
                scts.sendMessage(event, "Not a command idiot!", false);
                break;
            default:
                break;
        }
    }
}
