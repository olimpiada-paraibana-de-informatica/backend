package br.edu.opi.manager.conventions.models;

/**
 * Interface to Generic Service. All models must implement it.
 *
 * @param <ID>
 */
public interface Model<ID> {
	ID getId();
	void setId(ID id);
}