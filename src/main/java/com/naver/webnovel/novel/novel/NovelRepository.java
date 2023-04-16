package com.naver.webnovel.novel.novel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract class NovelRepository implements JpaRepository<Novel, Long> {
}
