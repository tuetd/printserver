package com.pruvn.rms.utils;

import org.displaytag.decorator.TableDecorator;

public class MRCDecorator extends TableDecorator{
	
	
    public String addRowId()
    {
        return "mrcRow" + evaluate("id");
    }
}
