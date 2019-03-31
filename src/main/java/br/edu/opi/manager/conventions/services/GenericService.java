package br.edu.opi.manager.conventions.services;

import br.edu.opi.manager.conventions.exceptions.CreateConflictRuntimeException;
import br.edu.opi.manager.conventions.exceptions.DeleteConflictRuntimeException;
import br.edu.opi.manager.conventions.exceptions.NotFoundRuntimeException;
import br.edu.opi.manager.conventions.exceptions.UpdateConflictRuntimeException;
import br.edu.opi.manager.conventions.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Generic Service to optimize development.
 *
 * @param <ID>         type of id that is the focus of the target service.
 * @param <MODEL>      type of domain that is the focus of the target service.
 * @param <REPOSITORY> type of repository layer that is the focus of the target
 *                     service.
 */
public class GenericService<ID, MODEL extends Model<ID>, REPOSITORY extends JpaRepository<MODEL, ID>> {


	protected static final int DEFAULT_PAGE = 0;
	protected static final int DEFAULT_SIZE = 10;
	protected static final Sort DEFAULT_SORT = Sort.by(Order.desc("createdAt"));
	protected static final Sort DEFAULT_NATIVE_SORT = Sort.by(Order.desc("CREATED_AT"));

	@Autowired
	protected REPOSITORY repository;

	@ReadOnlyProperty
	public Page<MODEL> index(Integer page, Integer size) {
		if (page == null) {
			page = DEFAULT_PAGE;
		}
		if (size == null) {
			size = DEFAULT_SIZE;
		}
		return repository.findAll(PageRequest.of(page, size, DEFAULT_SORT));
	}

	@ReadOnlyProperty
	public List<MODEL> index() {
		return repository.findAll(DEFAULT_SORT);
	}

	@ReadOnlyProperty
	public MODEL show(ID id) {
		try {
			return repository.findById(id).orElseThrow(NotFoundRuntimeException::new);
		} catch (IllegalArgumentException iae) {
			throw new NotFoundRuntimeException();
		}
	}

	public MODEL create(MODEL model) {
		validateBeforeCreate(model);
		return this.createTransaction(model);
	}

	public MODEL update(ID id, MODEL model) {
		validateBeforeUpdate(model);
		return this.updateTransaction(id, model);
	}

	public void delete(ID id) {
		validateBeforeDelete(id);
		this.deleteTransaction(id);
	}

	public void validateBeforeCreate(MODEL model){}

	public void validateBeforeUpdate(MODEL model){}

	public void validateBeforeDelete(ID id){}

	// @formatter:off
	/*
	 * if many errors were predicted:
	 *
	 * @see https://stackoverflow.com/questions/32347083/how-to-catch-hibernate-sql-
	 * exceptions-in-spring/32347906#32347906
	 *
	 * To recovery error code, use
	 * org.hibernate.exception.ConstraintViolationException:
	 *
	 * @code {
	 *   catch (DataIntegrityViolationException dive) {
	 *     if(dive.getCause() != null && dive.getCause() instanceof ConstraintViolationException) {
	 *       ConstraintViolationException cve = (ConstraintViolationException) dive.getCause();
	 *       throw new DataManipulationRuntimeException(cve.getErrorCode());
	 *     }
	 *   }
	 * }
	 */
	// @formatter:on
	@Transactional
	MODEL createTransaction(MODEL model) {
		try {
			return repository.save(model);
		} catch (Exception e) {
			throw new CreateConflictRuntimeException(e.getMessage());
		}
	}

	@Transactional
	MODEL updateTransaction(ID id, MODEL model) {
		if (!repository.existsById(id)) {
			throw new NotFoundRuntimeException();
		}
		try {
			model.setId(id);
			return repository.save(model);
		} catch (Exception e) {
			throw new UpdateConflictRuntimeException(e.getMessage());
		}
	}

	@Transactional
	void deleteTransaction(ID id) {
		if (!repository.existsById(id)) {
			throw new NotFoundRuntimeException();
		}
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			throw new DeleteConflictRuntimeException(e.getMessage());
		}
	}

}
