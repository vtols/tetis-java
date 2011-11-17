package tetisconsole;

import java.io.File;

public class Options {
    
    static {
        String home = System.getProperty("user.home");
        File optdir = new File(home + "/.tetis");
        if(!optdir.exists())
            optdir.mkdir();
    }
    
}
