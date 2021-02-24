package com.pruvn.rms.utils;

import org.displaytag.decorator.TableDecorator;

@SuppressWarnings("unchecked")
public class CSRecordDecorator extends TableDecorator{
	
    public String addRowId()
    {
        return "recordRow" + evaluate("id");
    }
}
