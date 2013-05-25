package edu.isi.bmkeg.digitalLibrary.rest;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.isi.bmkeg.digitalLibrary.model.qo.citations.LiteratureCitation_qo;
import edu.isi.bmkeg.ftd.dao.FtdDao;
import edu.isi.bmkeg.ftd.model.FTD;
import edu.isi.bmkeg.ftd.model.qo.FTD_qo;
import edu.isi.bmkeg.vpdmf.model.instances.LightViewInstance;

@Controller
public class PdfServer {

	private static final Logger logger = Logger.getLogger(PdfServer.class);

	@Autowired
	private FtdDao ftdDao;

	public void setftdDao(FtdDao ftdDao) {
		this.ftdDao = ftdDao;
	}
	
	@RequestMapping(value="/load", method=RequestMethod.GET, params="swfFile", produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public @ResponseBody ResponseEntity<byte []> byPdfParameter(@RequestParam("swfFile") String fileName) throws Exception {
		
		HttpHeaders responseHeaders = new HttpHeaders();
		
		Long vpdmfId = null;

		Pattern patt = Pattern.compile("(\\d+)\\.swf");
		Matcher m = patt.matcher(fileName);
		
		if( m.find() ) {
			vpdmfId = new Long(m.group(1));
		} else {
			responseHeaders.add("Location", "http://bmkeg.isi.edu");
			return new ResponseEntity<byte []>(null, responseHeaders, HttpStatus.FOUND);
			//return "Pubmed Id "+ pmidStr + " not a numeric code";						
		}
				
		FTD_qo qFtd = new FTD_qo();
		LiteratureCitation_qo ac = new LiteratureCitation_qo();
		qFtd.setCitation(ac);
		ac.setVpdmfId(vpdmfId + "");
		List<LightViewInstance> l = this.ftdDao.listArticleDocument(qFtd);
		
		if( l.size() == 0 ) {
			responseHeaders.add("Location", "http://bmkeg.isi.edu");
			return new ResponseEntity<byte []>(null, responseHeaders, HttpStatus.FOUND);
			//return "Pubmed Id "+ pmid + " not found";
		}

		if( l.size() > 1 ) {
			responseHeaders.add("Location", "http://bmkeg.isi.edu");
			return new ResponseEntity<byte []>(null, responseHeaders, HttpStatus.FOUND);
			//return "Pubmed Id "+ pmid + " ambiguous";					
		}
		
		vpdmfId = l.get(0).getVpdmfId();

		FTD ftd = this.ftdDao.findArticleDocumentById(vpdmfId);

		responseHeaders.setContentType(MediaType.valueOf("application/swf"));
	    responseHeaders.setContentLength(ftd.getLaswf().length);
	    responseHeaders.set("Content-Disposition", "attachment");
	    responseHeaders.add("Content-Disposition", "filename=\"" +  fileName + '\"');

	    ResponseEntity<byte[]> response = new ResponseEntity<byte []>
        		(ftd.getLaswf(), responseHeaders, HttpStatus.OK);
        		
        return response;
		
	}
	
}
