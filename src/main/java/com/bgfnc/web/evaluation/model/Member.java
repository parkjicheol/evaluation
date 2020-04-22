package com.bgfnc.web.evaluation.model;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    private Integer seq;

    @NotNull
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String password;

    @Builder
    public Member(Integer seq) {
        this.seq = seq;
    }

    @Builder
    public Member(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

}
