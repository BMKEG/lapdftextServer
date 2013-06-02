package edu.isi.bmkeg.ftd.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.isi.bmkeg.ftd.model.FTD;
import edu.isi.bmkeg.ftd.model.FTDRuleSet;
import edu.isi.bmkeg.ftd.services.ExtendedFtdService;
import edu.isi.bmkeg.lapdf.dao.LAPDFTextDao;

@RemotingDestination
@Transactional
@Service
public class ExtendedFtdServiceImpl implements ExtendedFtdService {


	@Autowired
	private LAPDFTextDao lapdftextDao;

	public LAPDFTextDao getLapdftextDao() {
		return lapdftextDao;
	}

	public void setLapdftextDao(LAPDFTextDao lapdftextDao) {
		this.lapdftextDao = lapdftextDao;
	}
	
	@Override
	public long runRuleSet(FTD ftd, FTDRuleSet ftdRuleSet) throws Exception {
		
		FTD ftd2 = this.lapdftextDao.runRuleSetOnFtd(ftd, ftdRuleSet);
		return ftd2.getVpdmfId();
		
	}

}
