package com.pruvn.rms.service;

import java.util.List;

import com.pruvn.rms.domain.Icm;
import com.pruvn.rms.dto.IBMCMDto;

public interface ICMService {

	public void save(List<Icm> lst) ;
	public List<IBMCMDto> zipAgreementNo();
}
 