/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetisconsole;

import java.io.File;

/**
 *
 * @author mtcomscxstart
 */
public class Options {
    
    static {
        String home = System.getProperty("user.home");
        File optdir = new File(home + "/.tetis");
        if(!optdir.exists())
            optdir.mkdir();
    }
    
}
