package SlopSrc;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModCommands extends ListenerAdapter {

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {

        String[] args = event.getMessage().getContentRaw().split(" ");
        String prefix = "s$";
        boolean isAdmin = event.getMember().hasPermission(Permission.ADMINISTRATOR);
        CommandShortcuts scts = new CommandShortcuts();

        if(args[0].equalsIgnoreCase(prefix + "warn")) {
            if(isAdmin) {
                List<Member> ments = event.getMessage().getMentionedMembers();
                ArrayList<String> warning = new ArrayList<String>();
                if(args.length > 2) {
                    for(int i = 2; i < args.length; i++) {
                        warning.add(args[i]);
                    }
                    String msg = warning.stream().map(Object::toString).collect(Collectors.joining(" "));
                    //ments.get(0).getUser().openPrivateChannel().flatMap(channel -> channel.sendMessage(msg)).queue();
                    scts.sendDM(event, ments.get(0).getUser(), msg);
                } else {
                    ments.get(0).getUser().openPrivateChannel().flatMap(channel -> channel.sendMessage("watch it")).queue();
                }
            } else {
                scts.sendMessage(event, "You can't do that idiot", true);
            }
        }
    }

}
