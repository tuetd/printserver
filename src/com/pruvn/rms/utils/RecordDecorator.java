package com.pruvn.rms.utils;

import org.displaytag.decorator.TableDecorator;

import com.pruvn.rms.domain.Record;
import com.pruvn.rms.domain.RecordReady;
import com.pruvn.rms.utils.Constant.STAGE;
@SuppressWarnings("unchecked")
public class RecordDecorator extends TableDecorator{
	
	public String addRowClass()
    {
		String css = "";
		Record record = (Record)getCurrentRowObject();
		if(record.getStage().equalsIgnoreCase(STAGE.RMT_RECORD_RMT_MARK_CS_WAIVED.getName())){
			css = "waived";
		}
		
		if ((record.getStage().equalsIgnoreCase(STAGE.FRESH_LOAN.getName()) 
				|| record.getStage().equalsIgnoreCase(STAGE.RMT_RECORD_RMT_MARK_DONE.getName()))
			&& null != record.getSendToBranch()) {
			css = "send_to_branch";
		}
		/*else if(record.getStage().equalsIgnoreCase(STAGE.RMT_RECORD_RMT_MARK_DONE.getName())){
			RecordRD recordRd = (RecordRD)getCurrentRowObject();
			if("YES".equalsIgnoreCase(recordRd.getCsArrived())){
				css = "";
			} else {
				css = "cs_not_ready";
			}
		}*/
		
		//else if(record.getStage().equalsIgnoreCase(STAGE.RMT_RECORD_RMT_CS_POST_RETURN.getName())){
			//css = "post_return";
		//}
        return css;
    }
    public String addRowId()
    {
        return "recordRow" + evaluate("id");
    }
}
