package br.com.cadsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cadsys.model.Permissao;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Integer>{

}