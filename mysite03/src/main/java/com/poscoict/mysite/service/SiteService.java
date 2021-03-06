package com.poscoict.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.mysite.repository.SiteRepository;
import com.poscoict.mysite.vo.SiteVo;

@Service
public class SiteService {

	
	@Autowired
	private SiteRepository siteRepository;
	
	public SiteVo select() {
		
		
		return siteRepository.select();
	}
	
	public boolean update(SiteVo vo) {
		
		
		return siteRepository.update(vo);
	}
}
