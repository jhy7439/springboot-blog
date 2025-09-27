package com.haribo.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haribo.blog.model.Board;


public interface BoardRepository extends JpaRepository<Board, Integer>{

}