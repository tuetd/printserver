package com.pruvn.rms.utils;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

public class DocReader {

	public void readDocFile() {
		File docFile = null;
		WordExtractor docExtractor = null ;
		WordExtractor exprExtractor = null ;
		try {
			docFile = new File("D://Working//Materials//UtilityWeb//datafiles//Convert so phu NH//ACB Online_1.doc");
			//A FileInputStream obtains input bytes from a file.
			FileInputStream fis=new FileInputStream(docFile.getAbsolutePath());

			//A HWPFDocument used to read document file from FileInputStream
			HWPFDocument doc = new HWPFDocument(fis);

			docExtractor = new WordExtractor(doc);
		}
		catch(Exception exep)
		{
			System.out.println(exep.getMessage());
		}

		//This Array stores each line from the document file.
		String [] docArray = docExtractor.getParagraphText();
		String pattern = "[^A-Z a-z0-9()-://]";
		List<String> strings = new ArrayList<String>();
		for (int i = 0; i < docArray.length; i++)
		{
			if(docArray[i] != null ){
				String strippedString = docArray[i].replaceAll(pattern, "");
//				if (StringUtils.isNotEmpty(strippedString)){
//					System.out.println("Line "+ i +" : " + strippedString);
//				}
				if (findDate(strippedString) || i == docArray.length - 1) {
					//Process data
					for (String string : strings) {
						System.out.println("Line "+ i +" : " + string);
					}
					System.out.println(" ********************************************************************** ");
					strings.clear();
				}
				strings.add(strippedString);
			}
			
			
		}
	} 
	
	public Boolean findDate(String date)
	{
		if (date.trim().length() != 8) {
			return false;
		}
		SimpleDateFormat checker = new SimpleDateFormat();
		String[] formats = {"dd-MM-yy"};
		boolean success = false;
		Date result = new Date();
		for(int i = 0; i < formats.length; i++)
		{
			if(success)
				break;
			ParsePosition pp = new ParsePosition(0);
			checker.applyPattern(formats[i]);
			for(int j = 0; j < date.length(); j++)
			{
				pp.setIndex(j);
				result = checker.parse(date, pp);
				if(result != null)
				{
					success = true;
					break;
				}
			}
		}

		return success;
	}

	public static void main(String[] args) {
		DocReader reader = new DocReader(	);
		reader.readDocFile();
		
//		String testString = "120938H>?<E)(*#l)(*L192L731O````W=-0O23423R2093820342L?><?>????D";
//        String pattern = "[^A-Za-z0-9()-]";
//       
//        String strippedString = testString.replaceAll(pattern, "");
//        System.out.println("Original String is:         "+testString);
//        System.out.println("After Replacing Characters: "+strippedString);
	}
}