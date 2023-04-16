package com.naver.webnovel.author;

import com.naver.webnovel.base.BaseTimeEntity;
import com.naver.webnovel.base.Status;
import com.naver.webnovel.novel.novel.Novel;
import com.naver.webnovel.user.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@NoArgsConstructor
public class Author extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private User user;

    @Length(max = 10)
    @Column(name = "penname", length = 10, nullable = false)
    private String penname;

    @OneToMany(mappedBy = "author")
    private List<Novel> novels = new ArrayList<>();

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Author(final User user, final String penname) {
        setUser(user);
        this.penname = penname;
        this.status = Status.ACTIVATED;
    }

    private void setUser(User user) {
        this.user = user;
        this.user.setAuthor(this);
    }

    public void addNovel(Novel novel) {
        this.novels.add(novel);
    }
}
