package com.build.prompt_storing.dao;

import java.util.List;
import java.util.Optional;

import com.build.prompt_storing.model.Prompt;

public interface PromptDao {
    int create(Prompt prompt);
    Optional<Prompt> findById(int id);
    List<Prompt> findAll();
    boolean update(int id, Prompt prompt);
    boolean delete(int id);
}