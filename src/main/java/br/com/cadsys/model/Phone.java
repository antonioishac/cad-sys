package br.com.cadsys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author aishac
 * 
 * Entidade para armazenar telefones de usuários
 *
 */
@Entity
@Table(name = "TB_PHONE")
public class Phone implements Serializable {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -472394094882441236L;
	
	@Column(name = "DDD")
	private String ddd;
	
	@Id
	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;
	
	@ManyToOne
	@JoinColumn(name="ID_USER")
	private User user;

	/**
	 * @return the ddd
	 */
	public String getDdd() {
		return ddd;
	}

	/**
	 * @param ddd the ddd to set
	 */
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Phone [ddd=" + ddd + ", phoneNumber=" + phoneNumber + ", user=" + user + "]";
	}	
}