/*------------------------------------------------------------------------------
2 Name: CSVReader.java
3 Project: jutils.org
4 Comment: Reads CSV (Comma Separated Value) files
5 Version: $Id: CSVReader.java,v 1.1 2004/04/07 07:40:45 laurent Exp $
6 Author: Roedy Green roedy@mindprod.com, Heinrich Goetzger goetzger@gmx.net
7 ------------------------------------------------------------------------------*/
 package com.pruvn.rms.utils.csv;

 import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

 /**
  * Reads CSV (Comma Separated Value) files.
  *
  * This format is mostly used my Microsoft Word and Excel.
  * Fields are separated by commas, and enclosed in
  * quotes if they contain commas or quotes.
  * Embedded quotes are doubled.
  * Embedded spaces do not normally require surrounding quotes.
  * The last field on the line is not followed by a comma.
  * Null fields are represented by two commas in a row.
  * We ignore leading and trailing spaces on fields, even inside quotes.
  *
  * @author copyright (c) 2002 Roedy Green Canadian Mind Products
  * Roedy posted this code on Newsgroups:comp.lang.java.programmer on 27th March 2002.
  *
  * Heinrich added some stuff like comment ability and linewise working.
  *
  */

 public class CSVReader {
    /**
     * Constructor
     *
     * @param r input Reader source of CSV Fields to read.
     * @param separator
     * field separator character, usually ',' in North America,
     * ';' in Europe and sometimes '\t' for tab.
     */
    public CSVReader (Reader r, char separator) {
       /* convert Reader to BufferedReader if necessary */
       if ( r instanceof BufferedReader ) {
          this.r = (BufferedReader) r;
       } else {
          this.r = new BufferedReader(r);
       }
       this.separator = separator;
    } // end of CSVReader

    /**
     * Constructor with default field separator ','.
     *
    * @param r input Reader source of CSV Fields to read.
     */
    public CSVReader (Reader r) {
       /* convert Reader to BufferedReader if necessary */
       if ( r instanceof BufferedReader ) {
          this.r = (BufferedReader) r;
       } else {
          this.r = new BufferedReader(r);
       }
       this.separator = ',';
    } // end of CSVReader

    private static final boolean debugging = true;

    /**
     * Reader source of the CSV fields to be read.
     */
    private BufferedReader r;

    /*
    * field separator character, usually ',' in North America,
    * ';' in Europe and sometimes '\t' for tab.
    */
    private char separator;

    /**
     * category of end of line char.
     */
    private static final int EOL = 0;

    /**
     * category of ordinary character
     */
    private static final int ORDINARY = 1;

    /**
     * categotory of the quote mark "
     */
    private static final int QUOTE = 2;

    /**
     * category of the separator, e.g. comma, semicolon
     * or tab.
     */
    private static final int SEPARATOR = 3;

    /**
     * category of characters treated as white space.
     */
    private static final int WHITESPACE = 4;

    /**
     * categorise a character for the finite state machine.
     *
     * @param c the character to categorise
     * @return integer representing the character's category.
     */
    private int categorise ( char c ) {
       switch ( c ) {
          case ' ':
          case '\r':
          case 0xff:
             return WHITESPACE;
 // case ';':
 // case '!':
 case '#':
             //return EOL;
 case '\n':
             return EOL; /* artificially applied to end of line */
          case '\"':
             return QUOTE;
          default:
             if (c == separator) {
                /* dynamically determined so can't use as case label */
                return SEPARATOR;
             } else if ( '!' <= c && c <= '~' ) {
                /* do our tests in crafted order, hoping for an early return */
                return ORDINARY;
             } else if ( 0x00 <= c && c <= 0x20 ) {
                return WHITESPACE;
             } else if ( Character.isWhitespace(c) ) {
                return WHITESPACE;
             } else {
                return ORDINARY;
             }
       } // end of switch
 } // end of categorise


    /**
     * parser: We are in blanks before the field.
     */
   private static final int SEEKINGSTART = 0;

   /**
     * parser: We are in the middle of an ordinary field.
     */
    private static final int INPLAIN = 1;

    /**
     * parser: e are in middle of field surrounded in quotes.
     */
    private static final int INQUOTED = 2;

    /**
     * parser: We have just hit a quote, might be doubled
     * or might be last one.
     */
    private static final int AFTERENDQUOTE = 3;
    /**
    * parser: We are in blanks after the field looking for the separator
    */
    private static final int SKIPPINGTAIL = 4;

    /**
     * state of the parser's finite state automaton.
     */

    /**
     * The line we are parsing.
     * null means none read yet.
     * Line contains unprocessed chars. Processed ones are removed.
     */
    private String line = null;

    /**
     * How many lines we have read so far.
     * Used in error messages.
     */
    private int lineCount = 0;

    public String[] getLine() {
       Vector<String> lineArray = new Vector<String>();
       String token = null;
       String returnArray [] = null;

       // reading values from line until null comes

       try {
          while (lineArray.size() == 0) {
             while ( (token = get() ) != null ) {
                lineArray.add(token);
             } // end of while
 } // end of while
 } catch (EOFException e) {
          return null;
       } catch (IOException e) {
       }

       returnArray = new String[lineArray.size()];
       

       for(int ii=0; ii < lineArray.size(); ii++) {    	   
          returnArray[ii] = lineArray.elementAt(ii).toString();
       } // end of for
       

       return returnArray;
    }

    /**
     * Read one field from the CSV file
     *
     * @return String value, even if the field is numeric. Surrounded
     * and embedded double quotes are stripped.
     * possibly "". null means end of line.
     *
     * @exception EOFException
     * at end of file after all the fields have
     * been read.
     *
     * @exception IOException
     * Some problem reading the file, possibly malformed data.
     */
    private String get() throws EOFException, IOException {
       StringBuffer field = new StringBuffer(50);
       /* we implement the parser as a finite state automaton with five states. */
       readLine();

       int state = SEEKINGSTART; /* start seeking, even if partway through a line */
       /* don't need to maintain state between fields. */

       /* loop for each char in the line to find a field */
       /* guaranteed to leave early by hitting EOL */
       for ( int i=0; i<line.length(); i++ ) {
          char c = line.charAt(i);
          int category = categorise(c);
          switch ( state ) {
             case SEEKINGSTART: {
                /* in blanks before field */
                switch ( category ) {
                   case WHITESPACE:
                      /* ignore */
                      break;
                   case QUOTE:
                      state = INQUOTED;
                      break;
                   case SEPARATOR:
                      /* end of empty field */
                      line = line.substring(i+1);
                      return "";
                   case EOL:
                      /* end of line */
                      line = null;
                      return null;
                   case ORDINARY:
                      field.append(c);
                      state = INPLAIN;
                      break;
                }
                break;
             } // end of SEEKINGSTART
 case INPLAIN: {
                /* in middle of ordinary field */
                switch ( category ) {
                   case QUOTE:
                      throw new IOException("Malformed CSV stream. Missing quote at start of field on line " + lineCount);
                   case SEPARATOR:
                      /* done */
                      line = line.substring(i+1);
                      return field.toString().trim();
                   case EOL:
                      line = line.substring(i); /* push EOL back */
                      return field.toString().trim();
                   case WHITESPACE:
                      field.append(' ');
                      break;
                   case ORDINARY:
                      field.append(c);
                      break;
                }
                break;
             } // end of INPLAIN
 case INQUOTED: {
                /* in middle of field surrounded in quotes */
                switch ( category ) {
                   case QUOTE:
                      state = AFTERENDQUOTE;
                      break;
                   case EOL:
                      throw new IOException ("Malformed CSV stream. Missing quote after field on line "+lineCount);
                   case WHITESPACE:
                      field.append(' ');
                      break;
                   case SEPARATOR:
                   case ORDINARY:
                      field.append(c);
                      break;
                }
                 break;
             } // end of INQUOTED
 case AFTERENDQUOTE: {
                /* In situation like this "xxx" which may
                   turn out to be xxx""xxx" or "xxx",
                   We find out here. */
                switch ( category ) {
                      case QUOTE:
                         field.append(c);
                         state = INQUOTED;
                         break;
                      case SEPARATOR :
                         /* we are done.*/
                         line = line.substring(i+1);
                         return field.toString().trim();
                      case EOL:
                         line = line.substring(i); /* push back eol */
                         return field.toString().trim();
                      case WHITESPACE:
                         /* ignore trailing spaces up to separator */
                         state = SKIPPINGTAIL;
                         break;
                      case ORDINARY:
                         throw new IOException("Malformed CSV stream, missing separator after field on line " + lineCount);
                }
                break;
             } // end of AFTERENDQUOTE
 case SKIPPINGTAIL: {
                /* in spaces after field seeking separator */
                switch ( category ) {
                   case SEPARATOR :
                      /* we are done.*/
                     line = line.substring(i+1);
                      return field.toString().trim();
                   case EOL:
                      line = line.substring(i); /* push back eol */
                      return field.toString().trim();
                   case WHITESPACE:
                      /* ignore trailing spaces up to separator */
                      break;
                   case QUOTE:
                   case ORDINARY:
                      throw new IOException("Malformed CSV stream, missing separator after field on line " + lineCount);
                } // end of switch
 break;
             } // end of SKIPPINGTAIL
 } // end switch(state)
 } // end for
 throw new IOException("Program logic bug. Should not reach here. Processing line " + lineCount);
    } // end get

    /**
     * Make sure a line is available for parsing.
     * Does nothing if there already is one.
     *
     * @exception EOFException
     */
    private void readLine() throws EOFException, IOException {
       if ( line == null ) {
          line = r.readLine(); /* this strips platform specific line ending */
          if ( line == null ) {
                 /* null means EOF, yet another inconsistent Java convention. */
             throw new EOFException();
          } else {
             line += '\n'; /* apply standard line end for parser to find */
             lineCount++;
          }
       }
    } // end of readLine


    /**
     * Skip over fields you don't want to process.
     *
     * @param fields How many field you want to bypass reading.
     * The newline counts as one field.
     * @exception EOFException
     * at end of file after all the fields have
     * been read.
     * @exception IOException
     * Some problem reading the file, possibly malformed data.
     */
    public void skip(int fields) throws EOFException, IOException {
       if ( fields <= 0 ) {
          return;
       }
       for ( int i=0; i<fields; i++ ) {
          // throw results away
    	   	get();
       }
    } // end of skip

    /**
     * Skip over remaining fields on this line you don't want to process.
     *
     * @exception EOFException
     * at end of file after all the fields have
     * been read.
     * @exception IOException
     * Some problem reading the file, possibly malformed data.
     */
    public void skipToNextLine() throws EOFException, IOException {
       if ( line == null ) {
          readLine();
       }
       line = null;
    } // end of skipToNextLine

    /**
     * Close the Reader.
     */
    public void close() throws IOException {
       if ( r != null ) {
          r.close();
          r = null;
       }
    } // end of close    
    
    public static String[][] readFile(String filePath, char delimiter, String charSet) throws IOException {    	
    	List<String[]> result = new ArrayList<String[]>();
    	int lineSize = -1;    	    	
        String loadLine[] = null;        
        String DEL = ",";        

        if ( debugging ) {
          CSVReader csv = new CSVReader(new InputStreamReader(new FileInputStream(filePath)), delimiter);
          if (null != charSet) {
        	  csv = new CSVReader(new InputStreamReader(new FileInputStream(filePath), charSet), delimiter);
          }
          while( (loadLine = csv.getLine()) != null) {
        	  if (lineSize < 0) {
        		  lineSize = loadLine.length;
        	  }             
        	  StringBuffer logBuffer = new StringBuffer();//        	  
        	  for(int i=1; i < loadLine.length; i++) {
                logBuffer.append(DEL).append(loadLine[i]);
        	  }                
              
             if (loadLine.length < lineSize) {
            	 String[] newLoadLine = new String[lineSize];                	 
            	 for (int i = 0; i < loadLine.length; i++) {            		            		 
            		 newLoadLine[i] = loadLine[i];
            	 }            	 
            	 result.add(newLoadLine);                	 
             }
             else {
            	 result.add(loadLine);
             }
             
          } // end of while
          csv.close();           
        } // end if
        return result.toArray(new String[result.size()][lineSize]);                
    }
    
    public static String[][] readFile(File file, char delimiter, String charSet) throws IOException {    	
    	List<String[]> result = new ArrayList<String[]>();
    	int lineSize = -1;    	    	
        String loadLine[] = null;        
        String DEL = ",";        

        if ( debugging ) {
          CSVReader csv = new CSVReader(new InputStreamReader(new FileInputStream(file)), delimiter);
          if (null != charSet) {
        	  csv = new CSVReader(new InputStreamReader(new FileInputStream(file), charSet), delimiter);
          }
          while( (loadLine = csv.getLine()) != null) {
        	  if (lineSize < 0) {
        		  lineSize = loadLine.length;
        	  }             
        	  StringBuffer logBuffer = new StringBuffer();//        	  
        	  for(int i=1; i < loadLine.length; i++) {
                logBuffer.append(DEL).append(loadLine[i]);
        	  }                
              
             if (loadLine.length < lineSize) {
            	 String[] newLoadLine = new String[lineSize];                	 
            	 for (int i = 0; i < loadLine.length; i++) {            		            		 
            		 newLoadLine[i] = loadLine[i];
            	 }            	 
            	 result.add(newLoadLine);                	 
             }
             else {
            	 result.add(loadLine);
             }
             
          } // end of while
          csv.close();           
        } // end if
        return result.toArray(new String[result.size()][lineSize]);                
    }    
 } // end CSVReader

// end of file
 