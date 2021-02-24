package com.pruvn.rms.utils;

import org.displaytag.decorator.TableDecorator;
@SuppressWarnings("unchecked")
public class MRCDecorator extends TableDecorator{
	
	
    public String addRowId()
    {
        return "mrcRow" + evaluate("id");
    }
}
