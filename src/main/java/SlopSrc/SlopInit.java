package SlopSrc;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SlopInit {

    public static void main(String[] args) throws LoginException, IOException {
        String token = Files.readAllLines(Paths.get("TOKEN GOES HERE")).get(0);
        JDA jda = JDABuilder.createDefault(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.GUILD_WEBHOOKS)
                .disableCache(CacheFlag.EMOTE)
                .enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES,
                        GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.GUILD_WEBHOOKS)
                .enableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.ONLINE_STATUS, CacheFlag.ACTIVITY,
                        CacheFlag.VOICE_STATE, CacheFlag.ROLE_TAGS)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .addEventListeners(new Commands(), new ModCommands(), new PrivateMessages())
                .setActivity(Activity.playing("slopping innocent people (i hate them)"))
                .setStatus(OnlineStatus.ONLINE)
                .build();
    }

}
