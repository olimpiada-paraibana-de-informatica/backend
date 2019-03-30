package br.edu.opi.manager.user;

import br.edu.opi.manager.exceptions.security.PasswordNotHashRuntimeException;
import br.edu.opi.manager.utils.BeanUtil;
import br.edu.opi.manager.utils.CryptoUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Listener to Simple User.
 */
public class UserListener {

	@Autowired
	CryptoUtil cryptoUtil;

	@PrePersist
	public void methodExecuteBeforeCreate(final User user) {
		BeanUtil.autowire(this, this.cryptoUtil);
		if (this.cryptoUtil.isNotHashPassword(user.getPassword())) {
			user.setPassword(cryptoUtil.hashPassword(user.getPassword()));
		} else {
			throw new PasswordNotHashRuntimeException();
		}
	}

	@PreUpdate
	public void methodExecuteBeforeUpdate(final User user) {
		BeanUtil.autowire(this, this.cryptoUtil);
		if (this.cryptoUtil.isNotHashPassword(user.getPassword())) {
			user.setPassword(cryptoUtil.hashPassword(user.getPassword()));
		}
	}

}
