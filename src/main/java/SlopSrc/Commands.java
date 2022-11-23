package SlopSrc;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Commands extends ListenerAdapter {

    public String message = "";
    private String prefix = "s$";
    public String nameChange = "";
    public int randCounter = ThreadLocalRandom.current().nextInt(50, 250);
    public final File f = new File("log.txt");

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        String userStr = event.getAuthor().toString().substring(2).replaceAll("[0-9()]", "");

        boolean isBot = event.getAuthor().isBot();
        final boolean isServer = event.getGuild().getName().equals("Femboy Sorority");
        CommandShortcuts scts = new CommandShortcuts();

        if(args[0].equalsIgnoreCase(prefix + "activity")) {
            scts.sendMessage(event, "done", true);
            event.getJDA().getPresence().setActivity(Activity.listening(event.getAuthor().getAsTag() + " screaming"));
        }

        if(args[0].equalsIgnoreCase(prefix + "activityclear")) {
            scts.sendMessage(event, "cleared", true);
            event.getJDA().getPresence().setActivity(Activity.playing("Los Pollos Hermanos"));

            try {
                PrintWriter pw = new PrintWriter(new FileWriter(f, true));
                pw.println("Slop Master activity has been reset.");
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(args[0].equalsIgnoreCase(prefix + "soy")) {
            IMentionable mentionedUser = event.getMessage().getMentions(Message.MentionType.USER).get(0);
            String replace = mentionedUser.toString();
            long id = Long.parseLong(replace.replaceAll("[^0-9]", ""));
            scts.sendMessage(event, "<@" + String.valueOf(id) + ">", false);
            scts.sendMessage(event, "https://cdn.discordapp.com/attachments/931616914227208203/956269644455493632/bounce.gif", false);
            scts.deleteMessage(event);
        }

        if(args[0].equalsIgnoreCase("necoarc") || (args[0].equalsIgnoreCase("neco") && args[1].equalsIgnoreCase("arc"))) {
            scts.sendMessage(event, "https://soundcloud.com/clown-588603371/neco-arc-dilemma-full-version?ref=clipboard&p=a&c=0&utm_campaign=social_sharing&utm_medium=text&utm_source=clipboard", true);
        }

        for(int i = 0; i < args.length; i++) {
            if(args[i].equalsIgnoreCase("tuesday")) {
                i = args.length;
                String link = "Tuesday? Am I so out of touch? https://www.youtube.com/watch?v=D00M2KZH1J0";
                scts.sendMessage(event, link, false);
            }
        }

        if(args[0].equalsIgnoreCase(prefix + "play")) {
            String activity = "";
            for (int i = 1; i < args.length; i++) {
                activity += args[i] += " ";
                event.getJDA().getPresence().setActivity(Activity.playing(activity));
            }
            scts.sendMessage(event, "I am now playing " + activity, false);
        }

        if(args[0].equalsIgnoreCase(prefix + "listen")) {
            String activity = "";
            for (int i = 1; i < args.length; i++) {
                activity += args[i] += " ";
                event.getJDA().getPresence().setActivity(Activity.listening(activity));
            }
            scts.sendMessage(event, "I am now listening to " + activity, false);
        }

        List<Role> allRoles = event.getGuild().getRoles();
        allRoles = allRoles.subList(0, allRoles.size() - 1);
        if(args[0].equalsIgnoreCase(prefix + "role")) {
            try {
                //Debug stuff
                String role = "";
                boolean nullRole = false;
                for(int i = 0; i < allRoles.size(); i++) {
                    System.out.println(allRoles.get(i));
                }
                if(args.length > 1) {
                    for(int i = 1; i < args.length; i++) {
                        //This takes the message, drops the command prefix, and just gets the role they want
                        role += args[i] + " ";
                        //patman was here
                    }
                    //System.out.println(role);
                } else if(args.length == 2) {
                    //If it is just a one word role, this sets the role variable to that argument
                    role = args[1];
                }
                for(int i = 8; i < allRoles.size(); i++) { //i = 8 specifically for my server REMINDER: go back and make it dynamic per server
                    //checks if role is equal to the raw string name of a sublist of allRoles
                    if(role.contains(allRoles.get(i).toString().substring(2).replaceAll("[0-9()]", ""))  && (!args[1].equalsIgnoreCase("Sinful") && !args[2].equalsIgnoreCase("Fool"))) {
                        //Gets rid of everything in the Role but the ID, which is a long value as string
                        String rolee = allRoles.get(i).toString().replaceAll("[a-zA-Z():]", "").substring(1).trim();
                        //System.out.println(rolee);
                        event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRoleById(rolee)).queue();
                        scts.sendMessage(event, "roled", true);
                        nullRole = true;
                        try {
                            PrintWriter pw = new PrintWriter(new FileWriter(f, true));
                            pw.println(userStr + " was given role " + role);
                            pw.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if(!nullRole || (args[1].equalsIgnoreCase("Sinful") && args[2].equalsIgnoreCase("Fool"))) {
                    scts.sendMessage(event, "this shit ain't fr!", true);
                }
            } catch(Exception e) {
                System.out.println(e);
            }
        }

        if(args[0].equalsIgnoreCase(prefix + "unrole")) {
            try {
                String role = "";
                boolean nullRole = false;
                for(int i = 0; i < allRoles.size(); i++) {
                    System.out.println(allRoles.get(i));
                }
                if(args.length > 1) {
                    for(int i = 1; i < args.length; i++) {
                        role += args[i] + " ";
                    }
                } else if(args.length == 2) {
                    role = args[1];
                }
                for(int i = 8; i < allRoles.size(); i++) {
                    if(role.contains(allRoles.get(i).toString().substring(2).replaceAll("[0-9()]", "")) && (!args[1].equalsIgnoreCase("Sinful") && !args[2].equalsIgnoreCase("Fool"))) {
                        String rolee = allRoles.get(i).toString().replaceAll("[a-zA-Z():]", "").substring(1).trim();
                        //System.out.println(rolee);
                        event.getGuild().removeRoleFromMember(event.getMember(), event.getGuild().getRoleById(rolee)).queue();
                        scts.sendMessage(event, "role taken away. bye bye", true);
                        nullRole = true;
                        try {
                            PrintWriter pw = new PrintWriter(new FileWriter(f, true));
                            pw.println(userStr + " was removed from role:  " + role);
                            pw.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if(!nullRole && (args[1].equalsIgnoreCase("Sinful") && args[2].equalsIgnoreCase("Fool"))) {
                    scts.sendMessage(event, "this shit ain't fr!", true);
                }
            } catch(Exception e) {
                System.out.println(e);
            }
        }

        if(args[0].equalsIgnoreCase(prefix + "rolelist")) {
            String[] rolesArr = new String[allRoles.size()]; //setting string array length as allRoles
            String everyRole = "";
            for(int i = 0; i < allRoles.size(); i++) {
                rolesArr[i] = allRoles.get(i).toString().substring(2).replaceAll("[0-9()]", ""); //replacing all the useless mumbo jumbo in the roles so its easier to read
                everyRole += rolesArr[i] += "\n";
            }
            final String privateRollList = everyRole; //this only exists since only final variables can be sent in dms
            scts.sendMessage(event, "snent", true);
            scts.sendDM(event, privateRollList);
        }

        int count = event.getGuild().getMemberCount();
        if(args[0].equalsIgnoreCase(prefix + "usercount")) {
            scts.sendMessage(event, "There are " + count + " people in the server", false);
        }

        if(args[0].equalsIgnoreCase(prefix + "banme")) {
            int troll = count - 1;
            scts.deleteMessage(event);
            scts.sendMessage(event, event.getAuthor().getAsMention() + " has been banned *(" + troll + ")*", true);
        }
    }

}
