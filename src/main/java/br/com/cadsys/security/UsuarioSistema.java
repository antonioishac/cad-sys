package br.com.cadsys.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UsuarioSistema extends User {

	private static final long serialVersionUID = 1L;

	private br.com.cadsys.model.User usuario;

	public UsuarioSistema(br.com.cadsys.model.User usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(), usuario.getPassword(), authorities);
		this.usuario = usuario;
	}

	public br.com.cadsys.model.User getUsuario() {
		return usuario;
	}

}