package test;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//備忘録：このプログラムで実験したこと
//xmlファイルから取得する値は、全部String型（"0/1"、"true/false"の変換は不可）
//getChildNodes()は、タグを順番に取得するわけではないらしい

public class XmlTypeChecker {
	
	DocumentBuilder db;
	Document doc;
	
	public static void main(String[] args){
		XmlTypeChecker x = new XmlTypeChecker();
		x.checkType();
	}
	
	/**
	 * 適当に書き換えてテストすること
	 * JavaとXML上での"0","1","true","false"の扱いを確認する
	 * @return
	 */
	public boolean checkType(){
		boolean ret = false;
		File file = new File("../test/InputXml/booleanCheck.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try{
			db = dbFactory.newDocumentBuilder();
			doc = db.parse(file);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		Element root = doc.getDocumentElement();
		
		NodeList nodeList = root.getChildNodes();
//		System.out.println(t.item(0).getAttributes().item(0).getNodeName());
		
		for(int i = 0; i < nodeList.getLength(); i++){
			if(nodeList.item(i).getNodeType() == Node.ELEMENT_NODE){
				System.out.println("1".equals(nodeList.item(i).getAttributes().getNamedItem("Number").getNodeValue()));
				System.out.println("1".equals(nodeList.item(i).getAttributes().getNamedItem("String").getNodeValue()));
			}
		}
		
		ret = true;
		return ret;
		
	}

}
