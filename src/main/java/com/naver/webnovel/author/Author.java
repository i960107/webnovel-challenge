package com.naver.webnovel.author;

import com.naver.webnovel.base.BaseTimeEntity;
import com.naver.webnovel.base.Status;
import com.naver.webnovel.novel.novel.Novel;
import com.naver.webnovel.user.User;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Builder;

@Entity
public class Author extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    @OneToOne
    @JoinColumn(name = "user_idx", referencedColumnName = "idx")
    private User user;

    @Column(name = "penname")
    private String penname;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Novel> novels = new ArrayLis<>();

    @Column(name = "status")
    private Status status = Status.ACTIVATED;

    @Builder
    public Author(User user, String penname, Status status) {
        this.user = user;
        this.penname = penname;
        this.status = status;
    }
}
