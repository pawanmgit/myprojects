/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.xpathxmlparser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author pawan
 */
public class XmlParser {

    private static final String S1_QUERY = "/m:tag1/m:tag";
    private static final String S1_M_QUERY = "m:mtag1";
    private static final String S1_A_QUERY = "m:atag1";
    private static final String S1_NUMBER_QUERY = "m:anumbertag1";
    private static final String S1_TYPE_QUERY = "m:type";
    private static final String S1_NAME_QUERY = "m:tag/m:name1";

    private static NodeList getNodeListByXPath(Document doc, String xpathVal) {
        NodeList nl = null;
        try {
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            xpath.setNamespaceContext(new NamespaceContext() {

                @Override
                public Iterator getPrefixes(String arg0) {
                    return null;
                }

                @Override
                public String getPrefix(String arg0) {
                    return null;
                }

                @Override
                public String getNamespaceURI(String prefix) {
                    if (prefix == null) {
                        throw new NullPointerException("Null prefix");
                    }

                    return doc.lookupNamespaceURI(prefix);
                }
            });
//          XPathExpression expr = xpath.compile(xpathVal);
            nl = (NodeList) xpath.evaluate(xpathVal, doc, XPathConstants.NODESET);

        } catch (XPathExpressionException ex) {
            ex.printStackTrace();
        }
        return nl;
    }

    private static NodeList getNodeListByXPath(Node node, String xpathVal) {
        NodeList nl = null;
        try {
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            xpath.setNamespaceContext(new NamespaceContext() {

                @Override
                public Iterator getPrefixes(String arg0) {
                    return null;
                }

                @Override
                public String getPrefix(String arg0) {
                    return null;
                }

                @Override
                public String getNamespaceURI(String prefix) {
                    if (prefix == null) {
                        throw new NullPointerException("Null prefix");
                    }

                    return node.lookupNamespaceURI(prefix);
                }
            });
            nl = (NodeList) xpath.evaluate(xpathVal, node, XPathConstants.NODESET);

        } catch (XPathExpressionException ex) {
            ex.printStackTrace();
        }
        return nl;
    }

    public static void main(String[] args) throws Exception {
        //Build DOM
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("Test.xml");

        //Create XPath
        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();

        xpath.setNamespaceContext(new NamespaceContext() {

            @Override
            public Iterator getPrefixes(String arg0) {
                return null;
            }

            @Override
            public String getPrefix(String arg0) {
                return null;
            }

            @Override
            public String getNamespaceURI(String prefix) {
                if (prefix == null) {
                    throw new NullPointerException("Null prefix");
                }

                return doc.lookupNamespaceURI(prefix);
            }
        });

        System.out.println("1) Get All details");
        Object result = getNodeListByXPath(doc, S1_QUERY);
        XPathExpression expr = null;
        NodeList nodes = (NodeList) result;
        for (int i = 0; i < 1; i++) {
            //TODO: NUll check
            NodeList nds = (NodeList) getNodeListByXPath(nodes.item(i), S1_M_QUERY);
                System.out.println(nds.item(0).getTextContent());
            nds = (NodeList) getNodeListByXPath(nodes.item(i), S1_NAME_QUERY);
                System.out.println(nds.item(0).getTextContent());
            nds = (NodeList) getNodeListByXPath(nodes.item(i), S1_NUMBER_QUERY);
                System.out.println(nds.item(0).getTextContent());
        }
        
     

    }
    
//    List<HashMap<String, Object>> processNodes(NodeList nodelist, HashMap<String, String> xpaths) {
//        
//        List<HashMap<String, Object>> records = new ArrayList();
//        
//        HashMap<String, Object> eachRecord = new HashMap();
//        for(path : xpaths) {
//            // extract data.
//            eachRecord.put(path.value(), value);
//        }
//        records.add(eachRecord);
//        
//        return null;
//    }

}

