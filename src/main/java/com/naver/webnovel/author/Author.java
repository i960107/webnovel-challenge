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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Entity
public class Author extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    @NotNull
    @OneToOne
    @JoinColumn(name = "user_idx")
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
    public Author(User user, String penname) {
        this.user = user;
        this.penname = penname;
        this.status = Status.ACTIVATED;
    }
}
