//package com.naver.webnovel.purchase.cash;
//
//import com.naver.webnovel.base.BaseTimeEntity;
//import com.naver.webnovel.base.PurchaseStatus;
//import com.naver.webnovel.user.User;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.validation.constraints.NotNull;
//import lombok.Builder;
//
//@Entity
//public class Cash extends BaseTimeEntity {
////    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    private Long idx;
////
////
////    @NotNull
////    @ManyToOne
////    @JoinColumn(name = "user_idx", nullable = false)
////    private User user;
////
////    @NotNull
////    @Column(name = "ticket_used", nullable = false)
////    private Integer ticketUsed;
////
////    @NotNull
////    @Enumerated(EnumType.STRING)
////    private PurchaseStatus status;
////
////    @Builder
////    public Cash(final User user, final Integer ticketUsed) {
////        this.user = user;
////        this.ticketUsed = ticketUsed;
////        this.status = PurchaseStatus.PENDING;
////    }
//
//}
