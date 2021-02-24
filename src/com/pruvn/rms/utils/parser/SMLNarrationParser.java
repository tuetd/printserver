package com.pruvn.rms.utils.parser;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.pruvn.rms.utils.CommonUtils;

public class SMLNarrationParser extends NarrationParser {

	@Override
	public String getAgreementNo(String narration) {
		String ret = "";
		String str = CommonUtils.getOnlyNumerics(narration, " ");
		List<String> agreementLst = new ArrayList<String>();
		Boolean isStartWithSender = false;
		if (narration.startsWith("REM       Sender")) {
			isStartWithSender = true;
		}
		
		if (StringUtils.isNotEmpty(str)) {
			String[] agreementsuspect = str.trim().split(" ");
			for (int count = 0; count < agreementsuspect.length; count++) {
				String str1 = agreementsuspect[count].trim();
				if (!StringUtils.isEmpty(str1) && str1.length() == 8 && str1.startsWith("1")) {
					ret = str1;
					if (!isStartWithSender)
					{
						break;
					}else {
						agreementLst.add(str1);
					}
				}
			}
		}
		
		if (isStartWithSender && agreementLst.size() > 0) // Get last agreement;
		{
			ret = agreementLst.get(agreementLst.size() - 1);
		}
		
		return ret;
	}

	@Override
	public String getCustomerName(String narration) {
		// TODO Auto-generated method stub
		return null;
	}

}
