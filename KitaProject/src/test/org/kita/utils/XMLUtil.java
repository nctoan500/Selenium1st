package org.kita.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.testng.TestNG;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLUtil {
	public static void createXML() throws Exception {
		try {
			// Create object for excel utility class
			String xlFilePath = "Resources\\Excel\\Initial.xls";
			ExcelLib_JXL excel = new ExcelLib_JXL(xlFilePath);
			excel.ColumnDictionary();

			// Initialization of drivers
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.newDocument();
			System.out.println("1");

			// Get the flagged cell with value = "Y" from excel file
			excel.GetFlaggedMethods("Flag");

			// Get the number of parameter to be created in XML
			int totalNoOfElementsFlaggedToRun = excel.flaggedMethod.size();

			// Type the suite tag element in the XML file
			Element rootElementSuite = document.createElement("suite");

			// Type the parameter set of lines in the XML file
			for (int elementCounter = 1; elementCounter <= totalNoOfElementsFlaggedToRun; elementCounter++) {
				org.w3c.dom.Element rootElementParameter = document
						.createElement("parameter");

				String[] flagElement = excel.flaggedMethod.get(elementCounter)
						.toString().split(";");

				rootElementParameter.setAttribute("name", flagElement[0]);
				rootElementParameter.setAttribute("value", flagElement[1]);
				rootElementSuite.appendChild(rootElementParameter);
				System.out.println("2");
			}

			// Type the root elements in the XML file
			Element rootElementTest = document.createElement("test");
			Element rootElementClass = document.createElement("classes");
			Element childElementClass = document.createElement("class");

			// Assign attribute to the root elements
			childElementClass.setAttribute("name",
					"pom.demo2_ExeAuto.tests.TestCaller");

			Element rootElementGroups = document.createElement("methods");

			// Assign attribute to the root elements
			rootElementSuite.setAttribute("name", "SeleniumJavaFramework");
			rootElementTest.setAttribute("name", "testing");
			System.out.println("3");

			// Append values to the root elements
			rootElementSuite.appendChild(rootElementTest);
			rootElementTest.appendChild(childElementClass);
			rootElementClass.appendChild(childElementClass);
			childElementClass.appendChild(rootElementGroups);
			document.appendChild(rootElementSuite);

			// Obtain the column value flag = "Y" in a loop
			for (int elementCounter = 1; elementCounter <= totalNoOfElementsFlaggedToRun; elementCounter++) {
				String element = "include";
				Element em = document.createElement(element);
				String[] flagElement = excel.flaggedMethod.get(elementCounter)
						.toString().split("");
				em.setAttribute("name", flagElement[0]);
				rootElementGroups.appendChild(em);
				System.out.println("4");
			}

			// Generate the file
			FileWriter fstream = new FileWriter("testng.xml");
			BufferedWriter out = new BufferedWriter(fstream);

			// Generate the requited XML output file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);

			// Prints all the Generated XML code in the File object
			StreamResult result = new StreamResult(fstream);

			transformer.transform(source, result);
			System.out.println("5");

			// Close the generated file
			out.close();
		} catch (DOMException e) {
			e.printStackTrace();
		}
	}

	public static void autoRunXml() {
		// Create a list
		List<String> files = new ArrayList<String>();

		// Add the required xml files to the list
		files.add("testng.xml");

		// Create object for TestNG
		TestNG tng = new TestNG();

		// Add the list of files to create a suite
		tng.setTestSuites(files);

		// Run the suite
		tng.run();
	}

}
