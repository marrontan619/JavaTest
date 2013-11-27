package test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerConfigurationException;
//import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
//import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//備忘録：このプログラムで実験したこと
//setAttribute("AttrName", null)は、AttrName=""の形で出力される
//Nodeは使いまわしできないので、importを使う。

public class XmlTest {
	
	private DocumentBuilderFactory dbFactory;
	private DocumentBuilder db;
	private Document srcDocument;  //読み込んだファイル
	
	public static void main(String[] args) {
		XmlTest test = new XmlTest();
		System.out.println("Program start.");
//		String destPath = "../test/resultXml/result.xml";
//		try{
//			Document document = test.createDocument("y-aoki", "22", "Male");
//			test.createXmlFile(document, new File(destPath));
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		test.writeFemaleElements();
		System.out.println("Program successfully ended");
	}
	
	/**
	 * @param 親フォルダーのパス
	 * @return xmlファイルのリスト
	 * 指定されたフォルダ配下にあるxmlファイルのパスを取得する
	 */
	public List<String> getXmlFileList(String parentFolderPath){
		List<String> xmlFileList = new ArrayList<String>();
		File parentFolder = new File(parentFolderPath);
		File[] childrenFiles = parentFolder.listFiles();
		for(File childFile: childrenFiles){
			if(childFile.getName().endsWith(".xml")){
				xmlFileList.add(childFile.getName());
			}
		}
		return xmlFileList;
	}
	
	/**
	 * @param Name
	 * @param age
	 * @param gender
	 * @return
	 * ユーザーxmlファイルを作る
	 */
	public Document createDocument(String name, String age, String gender) throws Exception{
		dbFactory = DocumentBuilderFactory.newInstance();
		db = dbFactory.newDocumentBuilder();
		Document document = db.newDocument();
		Element user = document.createElement("User");
		Element info = document.createElement("Infomation");
		info.setAttribute("Age", age);
		info.setAttribute("Name", name);
		info.setAttribute("Gender", gender);
		user.appendChild(info);
		document.appendChild(user);
		return document;
	}
	
	/**
	 * 
	 */
	public void createXmlFile(Document document, File file){
		Transformer transformer = null;
		TransformerFactory tFactory = TransformerFactory.newInstance();
		try{
			transformer = tFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			document.setXmlStandalone(true);
			if(file.exists()){
				file.delete();
			}
			transformer.transform(new DOMSource(document), new StreamResult(file));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * テストに作成したメソッド
	 * Nodeの使いまわしはできないらしい
	 */
	public void writeFemaleElements(){
		String srcFilePath = "../test/inputXml/test.xml";
		File src = new File(srcFilePath);
//		List<Node> targetElements = new ArrayList<Node>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		TransformerFactory tf = TransformerFactory.newInstance();
		try{
			DocumentBuilder dBuilder = dbf.newDocumentBuilder();
			Document srcDoc = dBuilder.parse(src);
			NodeList infoList = srcDoc.getElementsByTagName("Infomation");
			Document newDoc = dBuilder.newDocument();
			newDoc.setXmlStandalone(true);
			Element result = newDoc.createElement("Result");
			result.setAttribute("xmlns", "test");
			for(int i = 0; i < infoList.getLength(); i++){
				if(infoList.item(i).getAttributes().getNamedItem("Gender").getNodeValue().equals("Female")){
//					Element infomation = newDoc.createElement("Infomation");
//					infomation.setAttribute(infoList.item(i).getAttributes().getNamedItem("Name").getNodeName(), infoList.item(i).getAttributes().getNamedItem("Name").getNodeValue());
//					infomation.setAttribute(infoList.item(i).getAttributes().getNamedItem("Age").getNodeName(), infoList.item(i).getAttributes().getNamedItem("Age").getNodeValue());
//					infomation.setAttribute(infoList.item(i).getAttributes().getNamedItem("Gender").getNodeName(), infoList.item(i).getAttributes().getNamedItem("Gender").getNodeValue());
//					result.appendChild(infomation);
					Node newInfo = newDoc.importNode(infoList.item(i), true);
					result.appendChild(newInfo);
				}
			}
			Element area = newDoc.createElement("Area");
			area.setAttribute("City", null);
			result.appendChild(area);
			newDoc.appendChild(result);
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.transform(new DOMSource(newDoc), new StreamResult(new File("../test/resultXml/testResult.xml")));
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
