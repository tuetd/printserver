package com.pruvn.tools.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import javax.xml.bind.JAXBException;

import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlException;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.w3c.dom.Node;

import com.jxcell.CellException;
import com.jxcell.View;

public class OOHandle {
	 public static final int INSERT_BEFORE = 0;
	    public static final int INSERT_AFTER = 1;
	    public static final int REPLACE = 2; 
    
    /** Enumeration for document formats. */
    public enum DocumentFormatEnum {
        PDF ("PDF", "writer_pdf_Export"),
        MSWORD ("MS-Word", "MS Word 97");
        private String name;
        private String code;
        private static DocumentFormatEnum[] values = { PDF, MSWORD };

        private DocumentFormatEnum(String name, String code) {
            this.name = name;
            this.code = code;
        }
        public String getName() { return name; }
        public String getFormatCode() { return code; }
        public static DocumentFormatEnum[] getValues() { return values; }
    }

    /** Enumeration for techniques of inserting text into the document. */
    public enum TextInsertionModeEnum {
        REPLACE_ALL   ("ReplaceAll", "Insert text into a specific text field, replacing contents"),
        ADD_TEXT      ("AddText", "Append text to the existing contents of a text field"),
        ADD_SENTENCE  ("AddSentence", "Append text into a specific text field as a new sentence"),
        ADD_PARAGRAPH ("AddParagraph", "Append text into a specific text field as a new paragraph");
        private String name;
        private String desc;
        private static TextInsertionModeEnum[] values =
            { REPLACE_ALL, ADD_TEXT, ADD_SENTENCE, ADD_PARAGRAPH };

        private TextInsertionModeEnum(String name, String desc) {
            this.name = name;
            this.desc = desc;
        }
        public String getName() { return name; }
        public String getDesc() { return desc; }
        public static TextInsertionModeEnum[] getValues() { return values; }
    }

    /** Enumeration for techniques of inserting the image into a document. */
    public enum ImageInsertionModeEnum {
        INSERT_IN_TEXT_FIELD ("InsertInTextField", "Insert image into a specific text field"),
        INSERT_IN_TEXT_BODY  ("InsertInTextBody", "Insert image into the document body"),
        REPLACE_IN_TEXT_FIELD ("ReplaceInTextField", "Insert image into a specific text field, replacing existing content"),
        REPLACE_IN_TEXT_BODY ("ReplaceInTextBody", "Insert chart into the document body, replacing existing content");
        private String name;
        private String desc;
        private static ImageInsertionModeEnum[] values = {
            INSERT_IN_TEXT_FIELD,
            INSERT_IN_TEXT_BODY,
            REPLACE_IN_TEXT_FIELD,
            REPLACE_IN_TEXT_BODY
        };

        private ImageInsertionModeEnum(String name, String desc) {
            this.name = name;
            this.desc = desc;
        }
        public String getName() { return name; }
        public String getDesc() { return desc; }
        public static ImageInsertionModeEnum[] getValues() { return values; }
    }

    //update 12.02.2014 use POI replace bookmark
    
    
    public static void printReplaceText(String[] bookmarkNamesArray,
			String[] valuesArray, String exportpdf, String templateFile,
			String filePath) throws Docx4JException, JAXBException, IOException{
    		System.out.println("----printReplaceText----------");
    		GregorianCalendar cale=new GregorianCalendar();
	        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new File(templateFile));
	        MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
	        HashMap<String, String> mappings = new HashMap<String, String>();
	        
	        
//	        documentPart.variableReplace(arg0)
	        for (int i = 0; i < bookmarkNamesArray.length; i++) {
	        	mappings.put("v"+bookmarkNamesArray[i].toLowerCase(), valuesArray[i]);
	        	System.out.println("v"+bookmarkNamesArray[i].toLowerCase());
//	        	String xpath = "//w:r[w:t[contains(text(),'V_"+bookmarkNamesArray[i].trim()+"')]]";
//	        	System.out.println(xpath);
//		        List<Object> list = documentPart.getJAXBNodesViaXPath(xpath, false);
//		        for (Object obj : list) {
//		            ObjectFactory factory = new ObjectFactory();
//		            List<Object>  objContent=((R)obj).getContent();
//		            objContent.clear();   
//		            Text t = factory.createText();
//		            t.setValue(valuesArray[i]);
//		        	System.out.println(valuesArray[i]);
//		            objContent.add(t);
//		        }
			}
	        
	        documentPart.variableReplace(mappings);
	        wordMLPackage.save(new File(filePath+".docx"));
	    
	        GregorianCalendar cale2=new GregorianCalendar();
	     	long t1=cale2.getTimeInMillis() - cale.getTimeInMillis();
	     	System.out.println(t1);
	        // file 24
	        // 1) read file .docx
	        
	        FileInputStream fis = new FileInputStream(new File(filePath+".docx"));
	        XWPFDocument document = new XWPFDocument(fis); 
//	        for (int i = 0; i < document.getParagraphs().size(); i++) {
//		        XWPFParagraph paragraph = document.getParagraphs().get(i);
//		        paragraph.setSpacingBefore(40);
//		        paragraph.setSpacingAfter(40);
//		    }
	        // 2) Prepare Pdf options
            PdfOptions options = PdfOptions.create();
//            options.fontProvider(new IFontProvider() {
//				@Override
//				public Font getFont(String familyName, String encoding, float size, int style, Color color) {
//					System.out.println("familyName:" + familyName);
//					System.out.println("encoding:" + encoding);
//					System.out.println("size:" + size);
//					System.out.println("style:" + style);
//					System.out.println("color:" + color);
//					
//					try {
//						//I would like to be judged string is not Chinese...but I can't find field.
//						BaseFont bfChinese = BaseFont.createFont("C:/WINDOWS/Fonts/Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//					
//						Font fontChinese = new Font(bfChinese, size, style, color);
//					
//						
//						if (familyName != null) {
//							System.out.println("familyName"+bfChinese.getDifferences()[0]);
//							fontChinese.setFamily(familyName);
//						}
//						return fontChinese;
//					} catch (Throwable e) {
//						e.printStackTrace();
//						return ITextFontRegistry.getRegistry().getFont(familyName, encoding, size, style, color);
//					}
//				}
//			});
            
            // 3) Convert XWPFDocument to Pdf
            OutputStream out = new FileOutputStream(new File(filePath+".pdf"));
            PdfConverter.getInstance().convert(document, out, options);
            out.close();
            GregorianCalendar cale3=new GregorianCalendar();
        	long t2=cale3.getTimeInMillis()-cale2.getTimeInMillis();
	     	System.out.println(t2);
    }
    
    
	public static void printPOI(String[] bookmarkNamesArray,
			String[] valuesArray, String exportpdf, String templateFile,
			String filePath) throws IOException, XmlException {
		System.out.println("----printPOI----------");
		GregorianCalendar cale=new GregorianCalendar();
		
		 List<XWPFTable> tableList = null;
	        File file = new File(templateFile);
	        FileInputStream fis = new FileInputStream(file);
	        XWPFDocument document = new XWPFDocument(fis); 
	        
	        for (int i = 0; i < bookmarkNamesArray.length; i++) {
	        	 // Firstly, deal with any paragraphs in the body of the document.
	        	procParaList(document.getParagraphs(), bookmarkNamesArray[i], valuesArray[i]);
	            // it is necessary to get at the list of paragraphs 'stored' within the
	            // individual table cell, hence this code which get the tables from the
	            // document, the rows from each table, the cells from each row and the
	            // paragraphs from each cell.
	            tableList = document.getTables();
	            repalceTable(tableList, bookmarkNamesArray[i], valuesArray[i]);
	            
	            List<XWPFHeader> lsHeader = document.getHeaderList();
	            if(lsHeader!=null && lsHeader.size()>0){
	            	 Iterator<XWPFHeader> hdIter = null;
	            	 hdIter = lsHeader.iterator();
	            	 while(hdIter.hasNext()) {
	            		 XWPFHeader hd = hdIter.next();
	            		 procParaList(hd.getListParagraph(), bookmarkNamesArray[i], valuesArray[i]);
	            	 }
	            }
	            
	            
	            List<XWPFFooter> lsFooter = document.getFooterList();
	            if(lsFooter!=null && lsFooter.size()>0){
	            	 Iterator<XWPFFooter> ftIter = null;
	            	 ftIter = lsFooter.iterator();
	            	 while(ftIter.hasNext()) {
	            		 XWPFFooter ft = ftIter.next();
	            		 procParaList(ft.getListParagraph(), bookmarkNamesArray[i], valuesArray[i]);
	            	 }
	            }
	            
//	            tableIter = tableList.iterator();
//	            while(tableIter.hasNext()) {
//	                table = tableIter.next();
//	                rowList = table.getRows();
//	                rowIter = rowList.iterator();
//	                while(rowIter.hasNext()) {
//	                    row = rowIter.next();
//	                    cellList = row.getTableCells();
//	                    cellIter = cellList.iterator();
//	                    while(cellIter.hasNext()) {
//	                        cell = cellIter.next();
//	                        procParaList(cell.getParagraphs(),
//	                        		bookmarkNamesArray[0],
//	                        		valuesArray[0]);
//	                    }
//	                }
//	            }
			}
	        
	        // file 24
	        //1) save doc
//	        OutputStream fos = new FileOutputStream(new File("C://data//app//printserver//doc//test.docx"));
//            document.write(fos); 
	        // 2) Prepare Pdf options
            PdfOptions options = PdfOptions.create();
          
            // 3) Convert XWPFDocument to Pdf
            for (int i = 0; i < document.getParagraphs().size(); i++) {
		        XWPFParagraph paragraph = document.getParagraphs().get(i);
		        paragraph.setSpacingBefore(2);
		        paragraph.setSpacingAfter(2);
		    }
            System.out.println("---------------------------paragraph--------------");
            OutputStream out = new FileOutputStream(new File(filePath));
            PdfConverter.getInstance().convert(document, out, options);
            
            GregorianCalendar cale2=new GregorianCalendar();
        	long t1=cale2.getTimeInMillis() - cale.getTimeInMillis();
	     	System.out.println(t1);
	}
	   
	public static void repalceTable(List<XWPFTable> subTableList, String bookmarkName, String bookmarkValue){
    	if(subTableList!=null && subTableList.size()>0){
    		Iterator<XWPFTable> tableIter = null;
    		tableIter = subTableList.iterator();
            while(tableIter.hasNext()) {
            	XWPFTable table = null;                
               
                List<XWPFTableRow> rowList = null;
                Iterator<XWPFTableRow> rowIter = null;
                
                table = tableIter.next();
                rowList = table.getRows();
                rowIter = rowList.iterator();            
                while(rowIter.hasNext()) {
                	XWPFTableRow row = null;
                	List<XWPFTableCell> cellList = null;
                    Iterator<XWPFTableCell> cellIter = null;
                    
                    row = rowIter.next();
                    cellList = row.getTableCells();
                    cellIter = cellList.iterator();
                    while(cellIter.hasNext()) {
                    	XWPFTableCell cell = null;
                        cell = cellIter.next();
                        procParaList(cell.getParagraphs(),
                                bookmarkName,
                                bookmarkValue);
                        repalceTable(cell.getTables(), bookmarkName, bookmarkValue);
                    }
                }
            }
    	}
    }
	    private static void procParaList(List<XWPFParagraph> paraList,
	            String bookmarkName, String bookmarkValue) {
	        Iterator<XWPFParagraph> paraIter = null;
	        XWPFParagraph para = null;
	        List<CTBookmark> bookmarkList = null;
	        Iterator<CTBookmark> bookmarkIter = null;
	        CTBookmark bookmark = null;
	        XWPFRun run = null;
	        Node nextNode = null;
	        Node styleNode = null; 
	        // Get an Iterator to step through the contents of the paragraph list.
	        paraIter = paraList.iterator();
	        while(paraIter.hasNext()) {
	            // Get the paragraph, a llist of CTBookmark objects and an Iterator
	            // to step through the list of CTBookmarks.
	            para = paraIter.next();
	            bookmarkList = para.getCTP().getBookmarkStartList();
	            bookmarkIter = bookmarkList.iterator();
	           
	            while(bookmarkIter.hasNext()) {
	                // Get a Bookmark and check it's name. If the name of the
	                // bookmark matches the name the user has specified...
	                bookmark = bookmarkIter.next();
	                if(bookmark.getName().equals(bookmarkName)) {
	                    // ...create the text run to insert and set it's text
	                    // content and then insert that text into the document.
	                    run = para.createRun();
	                    if(bookmarkValue!=null){
	                    run.setText(bookmarkValue);
	                    }
	                    // The new Run should be inserted between the bookmarkStart
	                    // and bookmarkEnd nodes, so find the bookmarkEnd node.
	                    // Note that we are looking for the next sibling of the
	                    // bookmarkStart node as it does not contain any child nodes
	                    // as far as I am aware.
	                    nextNode = bookmark.getDomNode().getNextSibling();
	                    styleNode = getStyleNode(nextNode);
	                    
	                    // If the next node is not the bookmarkEnd node, then step
	                    // along the sibling nodes, until the bookmarkEnd node
	                    // is found. As the code is here, it will remove anything
	                    // it finds between the start and end nodes. This, of course
	                    // comepltely sidesteps the issues surrounding boorkamrks
	                    // that contain other bookmarks which I understand can happen.
	                    while(!(nextNode.getNodeName().contains("bookmarkEnd"))) {
	                    	para.getCTP().getDomNode().removeChild(nextNode);
	                        nextNode = bookmark.getDomNode().getNextSibling();
	                        
	                    }
	                   
	                    // Finally, insert the new Run node into the document
	                    // between the bookmarkStrat and the bookmarkEnd nodes.
//	                    
	                    if (styleNode != null) {
  		                    run.getCTR().getDomNode().insertBefore(
  		                            styleNode.cloneNode(true),  run.getCTR().getDomNode().getFirstChild());
  		                }
	                    
	                    para.getCTP().getDomNode().insertBefore(
	                            run.getCTR().getDomNode(),
	                            nextNode);
	                }
	            }
	        }
	    }
	    public static Node getStyleNode(Node parentNode) {
	        Node childNode = null;
	        Node styleNode = null;
	        if (parentNode != null) {

	            // If the node represents a run and it has child nodes then
	            // it can be processed further. Note, whilst testing the code, it
	            // was observed that although it is possible to get a list of a nodes
	            // children, even when a node did have children, trying to obtain this
	            // list would often return a null value. This is the reason why the
	            // technique of stepping from one node to the next is used here.
	            if (parentNode.getNodeName().equalsIgnoreCase("w:r")
	                    && parentNode.hasChildNodes()) {

	                // Get the first node and catch it's reference for return if
	                // the first child node is a style node (w:rPr).
	                childNode = parentNode.getFirstChild();
	                if (childNode.getNodeName().equals("w:rPr")) {
	                    styleNode = childNode;
	                } else {
	                    // If the first node was not a style node and there are other
	                    // child nodes remaining to be checked, then step through
	                    // the remaining child nodes until either a style node is
	                    // found or until all child nodes have been processed.
	                    while ((childNode = childNode.getNextSibling()) != null) {
	                        if (childNode.getNodeName().equals("w:rPr")) {
	                            styleNode = childNode;
	                            // Note setting to null here if a style node is
	                            // found in order order to terminate any further
	                            // checking
	                            childNode = null;
	                        }
	                    }
	                }
	            }
	        }
	        return (styleNode);
	    }


		public static void printPDFPOI(String input, String output) {
			  View m_view = new View();
		        try
		        {
		            m_view.read(input);
		            m_view.setPrintScale(100);     // set print scale value --- default is 100%
//		            m_view.setPrintHeader("Your header");  //set header --- default is &A
//		            m_view.setPrintFooter("Your footer");  //set footer  --- default is Page &P
//		            m_view.setPrintGridLines(true);  //show grid line
		            m_view.exportPDF(output);   //export to pdf file
		        }
		        catch (CellException e)
		        {
		            e.printStackTrace();
		        } catch (IOException e)
		        {
		            e.printStackTrace();
		        }
		    }
		
		
		
		
		//test 2 POI
		
		
	

		    /**
		     * Inserts text into the document at the position indicated by a specific
		     * bookmark. Note that the current implementation does not take account of
		     * nested bookmarks, that is bookmarks that contain other bookmarks. Note
		     * also that any text contained within the bookmark itself will be removed.
		     *
		     * @param paraList An instance of a class that implements the List interface
		     * and which encapsulates references to one or more instances of the
		     * XWPFParagraph class.
		     * @param bookmarkName An instance of the String class that encapsulates the
		     * name of the bookmark that identifies the position within the document
		     * some text should be inserted.
		     * @param bookmarkValue An instance of the AString class that encapsulates
		     * the text that should be inserted at the location specified by the
		     * bookmark.
		     * @param where A primitive int whose value indicates where the text should
		     * be inserted relative to the bookmark, i.e. before or after the bookmark.
		     */
		public static void procParaList(List<XWPFParagraph> paraList,
		            String bookmarkName, String bookmarkValue, int where) throws XmlException {
		        Iterator<XWPFParagraph> paraIter = null;
		        XWPFParagraph para = null;
		        List<CTBookmark> bookmarkList = null;
		        Iterator<CTBookmark> bookmarkIter = null;
		        CTBookmark bookmark = null;
		        XWPFRun run = null;

		        // Get an Iterator for the XWPFParagraph object and step through them
		        // one at a time.
		        paraIter = paraList.iterator();
		        while (paraIter.hasNext()) {
		            para = paraIter.next();

		            // Get a List of the CTBookmark object sthat the paragraph
		            // 'contains' and step through these one at a time.
		            bookmarkList = para.getCTP().getBookmarkStartList();
		            bookmarkIter = bookmarkList.iterator();
		            while (bookmarkIter.hasNext()) {
		                bookmark = bookmarkIter.next();

		                // If the name of the CTBookmakr object matches the value
		                // encapsulated within the argumnet passed to the bookmarkName
		                // parameter then this is where the text should be inserted.
		                if (bookmark.getName().equals(bookmarkName)) {

		                    // Create a new character run to hold the value encapsulated
		                    // within the argument passed to the bookmarkValue parameter
		                    // and then test whether this new run shouold be inserted
		                    // into the document before or after the bookmark.
		                    run = para.createRun();
//		                    run.setFontFamily("Arial");
		                    run.setText(bookmarkValue);
		                    switch (where) {
		                        case OOHandle.INSERT_AFTER:
		                            insertAfterBookmark(bookmark, run, para);
		                            break;
		                        case OOHandle.INSERT_BEFORE:
		                            insertBeforeBookmark(bookmark, run, para);
		                            break;
		                        case OOHandle.REPLACE:
		                            replaceBookmark(bookmark, run, para);
		                            break;

		                    }
		                }
		            }
		        }
		    }

		    /**
		     * Inserts some text into a Word document in a position that is immediately
		     * after a named bookmark.
		     *
		     * Bookmarks can take two forms, they can either simply mark a location
		     * within a document or they can do this but contain some text. The
		     * difference is obvious from looking at some XML markup. The simple
		     * placeholder bookmark will look like this;
		     *
		     * <pre>
		     *
		     * <w:bookmarkStart w:name="AllAlone" w:id="0"/><w:bookmarkEnd w:id="0"/>
		     *
		     * </pre>
		     *
		     * Simply a pair of tags where one tag has the name bookmarkStart, the other
		     * the name bookmarkEnd and both share matching id attributes. In this case,
		     * the text will simply be inserted into the document at a point immediately
		     * after the bookmarkEnd tag. No styling will be applied to the text, it
		     * will simply inherit the documents defaults.
		     *
		     * The more complex case looks like this;
		     *
		     * <pre>
		     *
		     * <w:bookmarkStart w:name="InStyledText" w:id="3"/>
		     *   <w:r w:rsidRPr="00DA438C">
		     *     <w:rPr>
		     *       <w:rFonts w:hAnsi="Engravers MT" w:ascii="Engravers MT" w:cs="Arimo"/>
		     *       <w:color w:val="FF0000"/>
		     *     </w:rPr>
		     *     <w:t>text</w:t>
		     *   </w:r>
		     * <w:bookmarkEnd w:id="3"/>
		     *
		     * </pre>
		     *
		     * Here, the user has selected the word 'text' and chosen to insert a
		     * bookmark into the document at that point. So, the bookmark tags 'contain'
		     * a character run that is styled. Inserting any text after this bookmark,
		     * it is important to ensure that the styling is preserved and copied over
		     * to the newly inserted text.
		     *
		     * The approach taken to dealing with both cases is similar but slightly
		     * different. In both cases, the code simply steps along the document nodes
		     * until it finds the bookmarkEnd tag whose ID matches that of the
		     * bookmarkStart tag. Then, it will look to see if there is one further node
		     * following the bookmarkEnd tag. If there is, it will insert the text into
		     * the paragraph immediately in front of this node. If, on the other hand,
		     * there are no more nodes following the bookmarkEnd tag, then the new run
		     * will simply be positioned at the end of the paragraph.
		     *
		     * Styles are dealt with by 'looking' for a 'w:rPr' element whilst iterating
		     * through the nodes. If one is found, its details will be captured and
		     * applied to the run before the run is inserted into the paragraph. If
		     * there are multiple runs between the bookmarkStart and bookmarkEnd tags
		     * and these have different styles applied to them, then the style applied
		     * to the last run before the bookmarkEnd tag - if any - will be cloned and
		     * applied to the newly inserted text.
		     *
		     * @param bookmark An instance of the CTBookmark class that encapsulates
		     * information about the bookmark.
		     * @param run An instance of the XWPFRun class that encapsulates the text
		     * that is to be inserted into the document following the bookmark.
		     * @param para An instance of the XWPFParagraph class that encapsulates that
		     * part of the document, a paragraph, into which the run will be inserted.
		     */
		    private static void insertAfterBookmark(CTBookmark bookmark, XWPFRun run,
		            XWPFParagraph para) {
		        Node nextNode = null;
		        Node insertBeforeNode = null;
		        Node styleNode = null;
		        int bookmarkStartID = 0;
		        int bookmarkEndID = -1;

		        // Capture the id of the bookmarkStart tag. The code will step through
		        // the document nodes 'contained' within the start and end tags that have
		        // matching id numbers.
		        bookmarkStartID = bookmark.getId().intValue();

		        // Get the node for the bookmark start tag and then enter a loop that
		        // will step from one node to the next until the bookmarkEnd tag with
		        // a matching id is fouind.
		        nextNode = bookmark.getDomNode();
		        while (bookmarkStartID != bookmarkEndID) {

		            // Get the next node along and check to see if it is a bookmarkEnd
		            // tag. If it is, get its id so that the containing while loop can
		            // be terminated once the correct end tag is found. Note that the
		            // id will be obtained as a String and must be converted into an
		            // integer. This has been coded to fail safely so that if an error
		            // is encuntered converting the id to an int value, the while loop
		            // will still terminate.
		            nextNode = nextNode.getNextSibling();
		            if (nextNode.getNodeName().contains("bookmarkEnd")) {
		                try {
		                    bookmarkEndID = Integer.parseInt(
		                            nextNode.getAttributes().getNamedItem("w:id").getNodeValue());
		                } catch (NumberFormatException nfe) {
		                    bookmarkEndID = bookmarkStartID;
		                }
		            } // If we are not dealing with a bookmarkEnd node, are we dealing
		            // with a run node that MAY contains styling information. If so,
		            // then get that style information from the run.
		            else {
		                if (nextNode.getNodeName().equals("w:r")) {
		                    styleNode = getStyleNode(nextNode);
		                }
		            }
		        }

		        // After the while loop completes, it should have located the correct
		        // bookmarkEnd tag but we cannot perform an insert after only an insert
		        // before operation and must, therefore, get the next node.
		        insertBeforeNode = nextNode.getNextSibling();

		        // Style the newly inserted text. Note that the code copies or clones
		        // the style it found in another run, failure to do this would remove the
		        // style from one node and apply it to another.
		        if (styleNode != null) {
		            run.getCTR().getDomNode().insertBefore(
		                    styleNode.cloneNode(true), run.getCTR().getDomNode().getFirstChild());
		        }

		        // Finally, check to see if there was a node after the bookmarkEnd
		        // tag. If there was, then this code will insert the run in front of
		        // that tag. If there was no node following the bookmarkEnd tag then the
		        // run will be inserted at the end of the paragarph and this was taken
		        // care of at the point of creation.
		        if (insertBeforeNode != null) {
		            para.getCTP().getDomNode().insertBefore(
		                    run.getCTR().getDomNode(), insertBeforeNode);
		        }
		    }

		    /**
		     * Inserts some text into a Word document immediately in front of the
		     * location of a named bookmark.
		     *
		     * This case is slightly more straightforward than inserting after the
		     * bookmark. For example, it is possible only to insert a new node in front
		     * of an existing node. When inserting after the bookmark, then end node had
		     * to be located whereas, in this case, the node is already known, it is the
		     * CTBookmark itself. The only information that must be discovered is
		     * whether there is a run immediately in front of the boookmarkStart tag and
		     * whether that run is styled. If there is and if it is, then this style
		     * must be cloned and applied the text which will be inserted into the
		     * paragraph.
		     *
		     * @param bookmark An instance of the CTBookmark class that encapsulates
		     * information about the bookmark.
		     * @param run An instance of the XWPFRun class that encapsulates the text
		     * that is to be inserted into the document following the bookmark.
		     * @param para An instance of the XWPFParagraph class that encapsulates that
		     * part of the document, a paragraph, into which the run will be inserted.
		     */
		    private static void insertBeforeBookmark(CTBookmark bookmark, XWPFRun run,
		            XWPFParagraph para) {
		        Node insertBeforeNode = null;
		        Node childNode = null;
		        Node styleNode = null;

		        // Get the dom node from the bookmarkStart tag and look for another
		        // node immediately preceding it.
		        insertBeforeNode = bookmark.getDomNode();
		        childNode = insertBeforeNode.getPreviousSibling();

		        // If a node is found, try to get the styling from it.
		        if (childNode != null) {
		            styleNode = getStyleNode(childNode);

		            // If that previous node was styled, then apply this style to the
		            // text which will be inserted.
		            if (styleNode != null) {
		                run.getCTR().getDomNode().insertBefore(
		                        styleNode.cloneNode(true), run.getCTR().getDomNode().getFirstChild());
		            }
		        }

		        // Insert the text into the paragraph immediately in front of the
		        // bookmarkStart tag.
		        para.getCTP().getDomNode().insertBefore(
		                run.getCTR().getDomNode(), insertBeforeNode);
		    }

		    /**
		     * Replace the text - if any - contained between the bookmarkStart and it's
		     * matching bookmarkEnd tag with the text specified. The technique used will
		     * resemble that employed when inserting text after the bookmark. In short,
		     * the code will iterate along the nodes until it encounters a matching
		     * bookmarkEnd tag. Each node encountered will be deleted unless it is the
		     * final node before the bookmarkEnd tag is encountered and it is a
		     * character run. If this is the case, then it can simply be updated to
		     * contain the text the users wishes to see inserted into the document. If
		     * the last node is not a character run, then it will be deleted, a new run
		     * will be created and inserted into the paragraph between the bookmarkStart
		     * and bookmarkEnd tags.
		     *
		     * @param bookmark An instance of the CTBookmark class that encapsulates
		     * information about the bookmark.
		     * @param run An instance of the XWPFRun class that encapsulates the text
		     * that is to be inserted into the document following the bookmark.
		     * @param para An instance of the XWPFParagraph class that encapsulates that
		     * part of the document, a paragraph, into which the run will be inserted.
		     */
		    private static void replaceBookmark(CTBookmark bookmark, XWPFRun run,
		            XWPFParagraph para) {
		        Node nextNode = null;
		        Node styleNode = null;
		        Node lastRunNode = null;
		        Stack<Node> nodeStack = null;
		        int bookmarkStartID = 0;
		        int bookmarkEndID = -1;

		        nodeStack = new Stack<Node>();
		        bookmarkStartID = bookmark.getId().intValue();
		        nextNode = bookmark.getDomNode();

		        // Loop through the nodes looking for a matching bookmarkEnd tag
		        while (bookmarkStartID != bookmarkEndID) {

		            nextNode = nextNode.getNextSibling();

		            // If an end tag is found, does it match the start tag? If so, end
		            // the while loop.
		            if (nextNode.getNodeName().contains("bookmarkEnd")) {
		                try {
		                    bookmarkEndID = Integer.parseInt(
		                            nextNode.getAttributes().getNamedItem("w:id").getNodeValue());
		                } catch (NumberFormatException nfe) {
		                    bookmarkEndID = bookmarkStartID;
		                }
		            } else {
		                // If this is not a bookmark end tag, store the reference to the
		                // node on the stack for later deletion. This is easier that
		                // trying to delete the nodes as they are found.
		                nodeStack.push(nextNode);
		            }
		        }

		        // If the stack of nodes found between the bookmark tags is not empty
		        // then they have to be removed.
		        if (!nodeStack.isEmpty()) {

		            // Check the node at the top of the stack. If it is a run, get it's
		            // style - if any - and apply to the run that will be replacing it.
		            lastRunNode = nodeStack.pop();
		            if ((lastRunNode.getNodeName().equals("w:r"))) {
		                styleNode = getStyleNode(lastRunNode);
		                if (styleNode != null) {
		                    run.getCTR().getDomNode().insertBefore(
		                            styleNode.cloneNode(true), run.getCTR().getDomNode().getFirstChild());
		                }
		            }

		            // Delete any and all node that were found in between the start and
		            // end tags. This is slightly safer that trying to delete the nodes
		            // as they are found wile stepping through them in the loop above.
		            para.getCTP().getDomNode().removeChild(lastRunNode);
		            // Now, delete the remaing Nodes on the stack
		            while (!nodeStack.isEmpty()) {
		                para.getCTP().getDomNode().removeChild(nodeStack.pop());
		            }
		        }

		        // Place the text into position, between the bookmark tags.
		        para.getCTP().getDomNode().insertBefore(
		                run.getCTR().getDomNode(), nextNode);
		    }

		    /**
		     * Recover styling information - if any - from another document node. Note
		     * that it is only possible to accomplish this if the node is a run (w:r)
		     * and this could be tested for in the code that calls this method. However,
		     * a check is made in the calling code as to whether a style has been found
		     * and only if a style is found is it applied. This method always returns
		     * null if it does nto find a style making that checking process easier.
		     *
		     * @param parentNode An instance of the Node class that encapsulates a
		     * reference to a document node.
		     * @return An instance of the Node class that encapsulates the styling
		     * information applied to a character run. Note that if no styling
		     * information is found in the run OR if the node passed as an argument to
		     * the parentNode parameter is NOT a run, then a null value will be
		     * returned.
		     */
		  
			
}
