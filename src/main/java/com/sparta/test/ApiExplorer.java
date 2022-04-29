package com.sparta.test;

import lombok.Builder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class ApiExplorer {
//    public static void main(String[] args) throws IOException {
//        StringBuilder urlBuilder = new StringBuilder("http://openapi.forest.go.kr/openapi/service/cultureInfoService/gdTrailInfoOpenAPI"); /*URL*/
//        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + "cvbuiI62JcgVFYZeKhNySkudFpfeTmn%2FbwXZWziaO%2Bov%2BQAPo14nlvnYMLGvr1jpjYUjxDGLSReE7qrIzStnkw%3D%3D"); /*Service Key*/
//        urlBuilder.append("&" + URLEncoder.encode("searchMtNm","UTF-8") + "=" + URLEncoder.encode("관악산", "UTF-8")); /*산명*/
//        urlBuilder.append("&" + URLEncoder.encode("searchArNm","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*지역명*/
//        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
//        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*페이지당 표시 항목 수*/
//        URL url = new URL(urlBuilder.toString());
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("Content-type", "application/json");
//        System.out.println("Response code: " + conn.getResponseCode());
//        BufferedReader rd;
//        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        } else {
//            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//        }
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = rd.readLine()) != null) {
//            sb.append(line);
//        }
//        rd.close();
//        conn.disconnect();
//        System.out.println(sb.toString());
//    }

    private static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if(nValue == null)
            return null;
        return nValue.getNodeValue();
    }

    public static void main(String[] args) {
        try{
            StringBuilder urlBuilder = new StringBuilder("http://openapi.forest.go.kr/openapi/service/cultureInfoService/gdTrailInfoOpenAPI"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + "CUnaloj7dqGa0E7B1yinNSBe8arlqRx4Vz9sWrfACPuZk2RnkhAo1wSYHxnZB0q2ephaL1YaanF%2BzpKUF%2FQcIg%3D%3D"); /*Service Key*/
            String url = new URL(urlBuilder.toString()).toString();
            System.out.println(url);

            DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
            Document doc = dBuilder.parse(url);

            // root tag
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            // 파싱할 tag
            NodeList nList = doc.getElementsByTagName("item");

            for(int temp = 0; temp < nList.getLength(); temp++){
                Node nNode = nList.item(temp);
                if(nNode.getNodeType() == Node.ELEMENT_NODE){

                    Element eElement = (Element) nNode;
                    //System.out.println(eElement.getTextContent());
                    System.out.println("설명 : " + getTagValue("aeatreason", eElement));

                }	// for end
            }	// if end

        } catch (Exception e){
            e.printStackTrace();
        }	// try~catch end
    }	// main end
}