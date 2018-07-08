package org.nmap.parser.services;

import org.nmap.parser.domain.Attribute;
import org.nmap.parser.domain.Elements;
import org.nmap.parser.domain.Ip;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class XmlReader {
    private static final String PATH_NAME = "resource";
    private static final String SSH  = "22";

    public Set<Ip> getIp() {

        Set<Ip> ip = new HashSet<>();
        for (String fileName : getListOfFiles()) {
            try {
                File file = new File(PATH_NAME + "/" + fileName);
                DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document doc = dBuilder.parse(file);
                doc.getDocumentElement().normalize();
                NodeList addressList = doc.getElementsByTagName(Elements.ADDRESS);
                NodeList portList = doc.getElementsByTagName(Elements.PORT);
                for (int i = 0; i < addressList.getLength(); i++) {
                    Node addressNode = addressList.item(i);
                    Node portNode = portList.item(i);
                    Element address = (Element) addressNode;
                    Element port = (Element) portNode;
                    if (port.getAttribute(Attribute.PORT_ID).equals(SSH)) {
                        Ip obj = new Ip();
                        obj.setIp(address.getAttribute(Attribute.IP));
                        ip.add(obj);
                    }
                }
            } catch (Exception e) {
                continue;
            }
        }
        return ip;
    }
    private List<String> getListOfFiles() {

        List<String> fileName = new ArrayList<>();
        File folder = new File(PATH_NAME);
        for (File fileEntry : folder.listFiles()) {
            fileName.add(fileEntry.getName());
        }
        return fileName;
    }
}