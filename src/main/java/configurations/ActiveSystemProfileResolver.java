package configurations;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
public class ActiveSystemProfileResolver {

    private static final String user1 = "b";
    private static final String user2 = "ashidlovskaya-pro13.local";
    private static final String user3 = "c";
    private static final String user4 = "d";

    @Getter
    private static String profile;

    public static String resolveCurrentProfile(){
        try {
            String osHostName = InetAddress.getLocalHost().getHostName();

            switch (osHostName){
                case user1 :
                    profile = "rx-ui";
                    break;
                case user2 :
                    profile = "Sergey";
                    break;
                case user3:
                    profile = "Yullia";
                    break;
                case user4:
                    profile = "SergeyM";
                    break;
                default:
                    profile = "rx-ui";
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return profile;
    }
}
