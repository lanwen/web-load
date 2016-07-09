package ru.lanwen.web.load;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * @author lanwen (Merkushev Kirill)
 */
@ApplicationPath("/")
public class MainJersey2App extends ResourceConfig {

    public MainJersey2App() {
        packages(MainJersey2App.class.getPackage().toString());
    }
}
