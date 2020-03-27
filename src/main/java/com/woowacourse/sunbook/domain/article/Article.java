package com.woowacourse.sunbook.domain.article;

import com.woowacourse.sunbook.domain.BaseEntity;
import com.woowacourse.sunbook.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Article extends BaseEntity implements Comparable<Article> {

    @Embedded
    private ArticleFeature articleFeature;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    private User author;

    @Enumerated(EnumType.STRING)
    private OpenRange openRange;

    public Article(ArticleFeature articleFeature, User author, OpenRange openRange) {
        this.articleFeature = articleFeature;
        this.author = author;
        this.openRange = openRange;
    }

    public void update(ArticleFeature updatedArticleFeature) {
        articleFeature = updatedArticleFeature;
    }

    public boolean isSameUser(User user) {
        return this.author.equals(user);
    }

    @Override
    public int compareTo(Article article) {
        return this.getUpdatedTime().compareTo(article.getUpdatedTime());
    }
}
