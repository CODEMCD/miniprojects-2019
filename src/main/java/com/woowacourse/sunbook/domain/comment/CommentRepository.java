package com.woowacourse.sunbook.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByArticleId(Long articleId);

    List<Comment> findByParentAndArticleId(Comment parent, Long articleId);
}
