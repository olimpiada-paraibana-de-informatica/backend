package br.edu.opi.manager.person.models;

import br.edu.opi.manager.person.services.PersonService;
import br.edu.opi.manager.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class PersonListener {

	@Autowired // don't remove, BeanUtil.autowire needs this
	PersonService personService;

	@PrePersist
	@PreUpdate
	public void executeBeforeSave(Person person) {
		BeanUtil.autowire(this, this.personService);
		person.setFullName(person.getFullName().trim());
		String fullName = person.getFullName();
		PartsPersonName parts = personService.processName(fullName);
		person.setAcronym(parts.getAcronym());
		person.setFirstName(parts.getLastName());
		person.setLastName(parts.getLastName());
	}

}
