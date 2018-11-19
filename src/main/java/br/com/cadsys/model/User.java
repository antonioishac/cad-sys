package br.com.cadsys.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author aishac
 * 
 * Entidadde para armazenar as informações de usuários
 *
 */
@Entity
@Table(name = "TB_USER")
public class User implements Serializable {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 1056975603348282321L;
	
	@Id
	@Column(name = "ID")
	private String id;
	
	@NotNull
	@Size(min = 2, max = 100)
	@Column(name = "NAME", unique = true)
	private String name;

	@Email
	@Size(min = 2, max = 50)
	@Column(name = "EMAIL")
	private String email;
	
	@NotEmpty
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "TOKEN", nullable = false)
	private String token;
	
	@Column(name = "MODIFIED")
	private LocalDateTime modified;
	
	@Column(name = "LAST_LOGIN")
	private LocalDateTime lastLogin;
	
	@Column(name = "CREATED")
	private LocalDateTime created;
	
	@JsonIgnoreProperties("user")
	@OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Phone> phones;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "TB_USER_PERMISSAO", joinColumns = @JoinColumn(name = "ID_USER")
		, inverseJoinColumns = @JoinColumn(name = "ID_PERMISSAO"))
	private List<Permissao> permissoes;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the modified
	 */
	public LocalDateTime getModified() {
		return modified;
	}

	/**
	 * @param modified the modified to set
	 */
	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}

	/**
	 * @return the lastLogin
	 */
	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	/**
	 * @param lastLogin the lastLogin to set
	 */
	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * @return the created
	 */
	public LocalDateTime getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	/**
	 * @return the phones
	 */
	public List<Phone> getPhones() {
		return phones;
	}

	/**
	 * @param phones the phones to set
	 */
	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	/**
	 * @return the permissoes
	 */
	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	/**
	 * @param permissoes the permissoes to set
	 */
	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", token=" + token
				+ ", modified=" + modified + ", lastLogin=" + lastLogin + ", created=" + created + ", phones=" + phones
				+ ", permissoes=" + permissoes + "]";
	}	
}
