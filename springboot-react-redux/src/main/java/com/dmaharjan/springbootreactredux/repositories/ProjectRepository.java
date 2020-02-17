package com.dmaharjan.springbootreactredux.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dmaharjan.springbootreactredux.domain.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
	Project findByProjectIdentifier(String projectIdentifier);
}
