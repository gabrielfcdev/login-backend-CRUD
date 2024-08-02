package br.com.gfc.projeto.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.gfc.projeto.entity.UsuarioEntity;

public class UserDetailsImpl implements UserDetails{
	
	private Long id;
	
	private String name;
	
	private String username;
	
	private String password;
	
	
	
	public UserDetailsImpl(Long id, String name, String string, String username, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.authorities = authorities;
	}
	


	public static UserDetailsImpl build(UsuarioEntity usuario) {
		return new UserDetailsImpl(
				usuario.getId(),
				usuario.getNome(),
				usuario.getLogin(),
				usuario.getEmail(),
				new ArrayList<>());
	}
	
	private Collection<? extends GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
