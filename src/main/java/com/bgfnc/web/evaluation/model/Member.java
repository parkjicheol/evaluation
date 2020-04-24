package com.bgfnc.web.evaluation.model;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name="member")
@DynamicUpdate
public class Member extends AuditModel {

    @Id
    @Column(name = "seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @NotNull
    private String memberId;

    @NotNull
    private String name;

    @NotNull
    private String password;

    @Builder
    public Member(Long seq) {
        this.seq = seq;
    }

    @Builder
    public Member(String memberId, String name, String password) {
        this.memberId = memberId;
        this.name = name;
        this.password = password;
    }

}
