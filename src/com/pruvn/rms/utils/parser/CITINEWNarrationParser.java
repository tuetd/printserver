package com.pruvn.rms.utils.parser;

import org.apache.commons.lang.StringUtils;

import com.pruvn.rms.utils.CommonUtils;

public class CITINEWNarrationParser extends NarrationParser {

	@Override
	public String getAgreementNo(String narration) {
		String ret = "";
		String str = CommonUtils.getOnlyNumerics(narration, " ");
		
		String[] agreementsuspect = str.trim().split(" ");
		for (int count = 0; count < agreementsuspect.length; count++) {
			String str1 = agreementsuspect[count].trim();
			if (!StringUtils.isEmpty(str1) && str1.length() == 8 && str1.startsWith("1")) {
				ret = str1;
				break;
			}
		}
		return ret;
	}

	@Override
	public String getCustomerName(String narration) {
		// TODO Auto-generated method stub
		return null;
	}

}
