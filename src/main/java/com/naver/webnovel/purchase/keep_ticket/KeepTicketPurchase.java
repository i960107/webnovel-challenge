package com.naver.webnovel.purchase.keep_ticket;

import com.naver.webnovel.base.BaseTimeEntity;
import com.naver.webnovel.base.PurchaseStatus;
import com.naver.webnovel.user.User;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.data.annotation.Id;

public class KeepTicketPurchase extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_idx", nullable = false)
    private User user;

    @NotNull
    @Column(name = "ticket_count", nullable = false)
    private Long ticketCount;

    @NotNull
    @Column(name = "cash_used", nullable = false)
    private Long cashUsed;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PurchaseStatus status;

    @Builder
    public KeepTicketPurchase(final User user, final Long ticketCount, final Long cashUsed) {
        this.user = user;
        this.ticketCount = ticketCount;
        this.cashUsed = cashUsed;
        this.status = PurchaseStatus.PENDING;
    }
}
