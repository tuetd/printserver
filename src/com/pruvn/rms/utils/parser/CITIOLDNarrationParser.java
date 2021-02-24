package com.pruvn.rms.utils.parser;

import org.apache.commons.lang.StringUtils;

import com.pruvn.rms.utils.CommonUtils;

public class CITIOLDNarrationParser extends NarrationParser {

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
	
	public static void main(String[] args) {
		String narration = "CHANG A LUC+PRU FINANCE+           030170706110043362";
		String str = CommonUtils.getOnlyNumerics(narration, " ");
		System.out.println(narration);
		System.out.println(str);
		
		String[] agreementsuspect = str.trim().split(" ");
		for (int count = 0; count < agreementsuspect.length; count++) {
			String str1 = agreementsuspect[count].trim();
			if (!StringUtils.isEmpty(str1) && str1.length() == 8 && str1.startsWith("1")) {
				System.out.println(str1);
				break;
			}
		}
	}

}
