package org.nmap.parser.services;

import org.nmap.parser.domain.Ip;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class WriteService {
    private static final String IP_FILE = "ip-result.txt";

    public void writeToFile(Set<Ip> ipSet) throws IOException {

        FileWriter fileWriter = new FileWriter(IP_FILE);
        for (Ip ip: ipSet){
            fileWriter.write(ip.getIp() + System.lineSeparator());
        }
        fileWriter.close();
    }
}
