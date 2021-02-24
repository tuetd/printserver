package com.pruvn.rms.utils.poi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.eventusermodel.EventWorkbookBuilder.SheetRecordCollectingListener;
import org.apache.poi.hssf.eventusermodel.FormatTrackingHSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFEventFactory;
import org.apache.poi.hssf.eventusermodel.HSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFRequest;
import org.apache.poi.hssf.eventusermodel.MissingRecordAwareHSSFListener;
import org.apache.poi.hssf.eventusermodel.dummyrecord.LastCellOfRowDummyRecord;
import org.apache.poi.hssf.eventusermodel.dummyrecord.MissingCellDummyRecord;
import org.apache.poi.hssf.model.HSSFFormulaParser;
import org.apache.poi.hssf.record.BOFRecord;
import org.apache.poi.hssf.record.BlankRecord;
import org.apache.poi.hssf.record.BoolErrRecord;
import org.apache.poi.hssf.record.BoundSheetRecord;
import org.apache.poi.hssf.record.FormulaRecord;
import org.apache.poi.hssf.record.LabelRecord;
import org.apache.poi.hssf.record.LabelSSTRecord;
import org.apache.poi.hssf.record.NoteRecord;
import org.apache.poi.hssf.record.NumberRecord;
import org.apache.poi.hssf.record.RKRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.SSTRecord;
import org.apache.poi.hssf.record.StringRecord;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class EventSAXReader implements HSSFListener {
	private int minColumns;
	private POIFSFileSystem fs;
//	private PrintStream output;

	private int lastRowNumber;
	private int lastColumnNumber;

	/** Should we output the formula, or the value it has? */
	private boolean outputFormulaValues = true;

	/** For parsing Formulas */
	private SheetRecordCollectingListener workbookBuildingListener;
	private HSSFWorkbook stubWorkbook;

	// Records we pick up as we process
	private SSTRecord sstRecord;
	private FormatTrackingHSSFListener formatListener;
	
	/** So we known which sheet we're on */
	private int sheetIndex = -1;
	private BoundSheetRecord[] orderedBSRs;
	private ArrayList boundSheetRecords = new ArrayList();

	// For handling formulas with string results
	private int nextRow;
	private int nextColumn;
	private boolean outputNextStringRecord;

    private Map<String,List<String[]>> sheet2data = new HashMap<String,List<String[]>>();
    private List<String[]> currentData = new ArrayList<String[]>();
    private List<String> currentRow = new ArrayList<String>();
    private String currentSheetName;

	/**
	 * Creates a new XLS -> CSV converter
	 * @param fs The POIFSFileSystem to process
	 * @param output The PrintStream to output the CSV to
	 * @param minColumns The minimum number of columns to output, or -1 for no minimum
	 */
	public EventSAXReader(POIFSFileSystem fs, PrintStream output, int minColumns) {
		this.fs = fs;
//		this.output = output;
		this.minColumns = minColumns;
		
		sheet2data = new HashMap<String,List<String[]>>();
		sheetIndex = -1;
	}

	/**
	 * Creates a new XLS -> CSV converter
	 * @param filename The file to process
	 * @param minColumns The minimum number of columns to output, or -1 for no minimum
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public EventSAXReader(POIFSFileSystem poifsFileSystem, int minColumns) throws IOException, FileNotFoundException {
		this(
				poifsFileSystem,
				System.out, minColumns
		);
	}

	/**
	 * Initiates the processing of the XLS file to CSV
	 */
	public void process() throws IOException {
		MissingRecordAwareHSSFListener listener = new MissingRecordAwareHSSFListener(this);
		formatListener = new FormatTrackingHSSFListener(listener);

		HSSFEventFactory factory = new HSSFEventFactory();
		HSSFRequest request = new HSSFRequest();

		if(outputFormulaValues) {
			request.addListenerForAllRecords(formatListener);
		} else {
			workbookBuildingListener = new SheetRecordCollectingListener(formatListener);
			request.addListenerForAllRecords(workbookBuildingListener);
		}

		factory.processWorkbookEvents(request, fs);
		
		// make sure we got the last sheet
        if (currentSheetName != null && sheet2data.get(currentSheetName) == null) {
            sheet2data.put(currentSheetName, currentData);
        }
	}

	/**
	 * Main HSSFListener method, processes events, and outputs the
	 *  CSV as the file is processed.
	 */
	public void processRecord(Record record) {
		int thisRow = -1;
		int thisColumn = -1;
		String thisStr = null;
		
		//p("looking at " + record.getClass().getName() + " sid=0x" + Integer.toHexString(record.getSid()));
//        if ((record.getSid() != BoundSheetRecord.sid &&
//                record.getSid() != BOFRecord.sid) &&
//                currentSheetName != null && this.requiredSheetName != null
//                && !this.requiredSheetName.equals(currentSheetName)) {
//            return;
//        }
		if (sheetIndex >= 1) {
			return;
		}

		switch (record.getSid())
		{
		case BoundSheetRecord.sid:
			boundSheetRecords.add(record);
			break;
		case BOFRecord.sid:
			BOFRecord br = (BOFRecord)record;
			if(br.getType() == BOFRecord.TYPE_WORKSHEET) {
				// Create sub workbook if required
				if(workbookBuildingListener != null && stubWorkbook == null) {
					stubWorkbook = workbookBuildingListener.getStubHSSFWorkbook();
				}
				
				// Output the worksheet name
				// Works by ordering the BSRs by the location of
				//  their BOFRecords, and then knowing that we
				//  process BOFRecords in byte offset order
				sheetIndex++;
				if(orderedBSRs == null) {
					orderedBSRs = BoundSheetRecord.orderByBofPosition(boundSheetRecords);
				}

				startNewSheet();
				
				currentSheetName = orderedBSRs[sheetIndex].getSheetname();
				startNewRow();
//				output.println();
//				output.println( 
//						orderedBSRs[sheetIndex].getSheetname() +
//						" [" + (sheetIndex+1) + "]:"
//				);
			}
			break;

		case SSTRecord.sid:
			sstRecord = (SSTRecord) record;
			break;

		case BlankRecord.sid:
			BlankRecord brec = (BlankRecord) record;

			thisRow = brec.getRow();
			thisColumn = brec.getColumn();
			thisStr = "";
			break;
		case BoolErrRecord.sid:
			BoolErrRecord berec = (BoolErrRecord) record;

			thisRow = berec.getRow();
			thisColumn = berec.getColumn();
			thisStr = "";
			break;

		case FormulaRecord.sid:
			FormulaRecord frec = (FormulaRecord) record;

			thisRow = frec.getRow();
			thisColumn = frec.getColumn();

			if(outputFormulaValues) {
				if(Double.isNaN( frec.getValue() )) {
					// Formula result is a string
					// This is stored in the next record
					outputNextStringRecord = true;
					nextRow = frec.getRow();
					nextColumn = frec.getColumn();
				} else {
					thisStr = formatListener.formatNumberDateCell(frec);
				}
			} else {
				thisStr = '"' +
					HSSFFormulaParser.toFormulaString(stubWorkbook, frec.getParsedExpression()) + '"';
			}
			break;
		case StringRecord.sid:
			if(outputNextStringRecord) {
				// String for formula
				StringRecord srec = (StringRecord)record;
				thisStr = srec.getString();
				thisRow = nextRow;
				thisColumn = nextColumn;
				outputNextStringRecord = false;
			}
			break;

		case LabelRecord.sid:
			LabelRecord lrec = (LabelRecord) record;

			thisRow = lrec.getRow();
			thisColumn = lrec.getColumn();
			thisStr = '"' + lrec.getValue() + '"';
			break;
		case LabelSSTRecord.sid:
			LabelSSTRecord lsrec = (LabelSSTRecord) record;

			thisRow = lsrec.getRow();
			thisColumn = lsrec.getColumn();
			if(sstRecord == null) {
				thisStr = '"' + "(No SST Record, can't identify string)" + '"';
			} else {
				thisStr = '"' + sstRecord.getString(lsrec.getSSTIndex()).toString() + '"';
			}
			break;
		case NoteRecord.sid:
			NoteRecord nrec = (NoteRecord) record;

			thisRow = nrec.getRow();
			thisColumn = nrec.getColumn();
			// TODO: Find object to match nrec.getShapeId()
			thisStr = '"' + "(TODO)" + '"';
			break;
		case NumberRecord.sid:
			NumberRecord numrec = (NumberRecord) record;

			thisRow = numrec.getRow();
			thisColumn = numrec.getColumn();

			// Format
			thisStr = formatListener.formatNumberDateCell(numrec);
			break;
		case RKRecord.sid:
			RKRecord rkrec = (RKRecord) record;

			thisRow = rkrec.getRow();
			thisColumn = rkrec.getColumn();
			thisStr = '"' + "(TODO)" + '"';
			break;
		default:
			break;
		}

		// Handle new row
		if(thisRow != -1 && thisRow != lastRowNumber) {
			lastColumnNumber = -1;
		}

		// Handle missing column
		if(record instanceof MissingCellDummyRecord) {
			MissingCellDummyRecord mc = (MissingCellDummyRecord)record;
			thisRow = mc.getRow();
			thisColumn = mc.getColumn();
			thisStr = "";
		}

		// If we got something to print out, do so
		if(thisStr != null) {
//			if(thisColumn > 0) {
//				output.print(',');
//			}
//			output.print(thisStr);
			appendCell(thisStr);
		}

		// Update column and row count
		if(thisRow > -1)
			lastRowNumber = thisRow;
		if(thisColumn > -1)
			lastColumnNumber = thisColumn;

		// Handle end of row
		if(record instanceof LastCellOfRowDummyRecord) {
			// Print out any missing commas if needed
//			if(minColumns > 0) {
				// Columns are 0 based
//				if(lastColumnNumber == -1) { lastColumnNumber = 0; }
//				for(int i=lastColumnNumber; i<(minColumns); i++) {
//					output.print(',');
//				}
//			}

			// We're onto a new row
			lastColumnNumber = -1;

			// End the row
//			output.println();
			
			startNewRow();
		}
	}
	
	private void appendCell(String value) {
		if (value == null) {
            value = "";
        }
        currentRow.add(value);
	}

	private void startNewSheet() {
        if (currentSheetName != null) {
            sheet2data.put(this.currentSheetName, currentData);
        }
        currentData = new ArrayList<String[]>();
    }
	
	private void startNewRow() {
        int sz = currentRow.size();
        String[] sa = new String[sz];
        currentRow.toArray(sa);
        // convert empty strings to null
        for (int i = 0; i < sz; i++) {
            if (sa[i].length() == 0) {
                sa[i] = null;
            }
        }
        
        if (sa != null && sa.length > 1 && StringUtils.isNotEmpty(sa[0])) {
        	currentData.add(sa);
        }
        currentRow.clear();
        
    }
	
	public List<String[]> getData() throws Exception {
        List<String[]> ret = null;
	    List<String> names = getSheetNames();
	    if (names.size() > 0) {
	        ret = getData(names.get(0));
	    }
	    
        return ret;
    }
    
    public List<String[]> getData(String sheetName) {
        return sheet2data.get(sheetName);
    }
    public List<String> getSheetNames() {
        List<String> ret = new ArrayList<String>();
        if (this.orderedBSRs != null) {
            for (BoundSheetRecord bsr : orderedBSRs) {
                ret.add(bsr.getSheetname());
            }
        }
        return ret;
    }
    
}
