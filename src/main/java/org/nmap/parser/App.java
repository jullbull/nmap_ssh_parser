package org.nmap.parser;

import org.nmap.parser.domain.Ip;
import org.nmap.parser.services.WriteService;
import org.nmap.parser.services.XmlReader;

import java.io.IOException;
import java.util.Set;

public class App {

    public static void main(String[] args) throws IOException {
        XmlReader reader = new XmlReader();
        WriteService writer = new WriteService();
        Set<Ip> ip = reader.getIp();
        writer.writeToFile(ip);
    }

}
