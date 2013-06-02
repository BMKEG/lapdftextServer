package edu.isi.bmkeg.ftd.services;

import edu.isi.bmkeg.ftd.model.FTD;
import edu.isi.bmkeg.ftd.model.FTDRuleSet;

public interface ExtendedFtdService {
	
	public long runRuleSet(FTD ftd, FTDRuleSet ftdRuleSet) throws Exception;
	

}
