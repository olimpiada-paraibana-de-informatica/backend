package br.edu.opi.manager.olympiad.models;

import br.edu.opi.manager.olympiad.services.ObiGraphService;
import br.edu.opi.manager.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PostPersist;

public class ObiChampionListener {

	@Autowired // don't remove, BeanUtil.autowire needs this
	ObiGraphService obiGraphService;

	@PostPersist
	public synchronized void executeAfterCreate(ObiChampion obiChampion) {
		BeanUtil.autowire(this, this.obiGraphService);
		obiGraphService.addChampionToGraph(obiChampion);
	}

}
