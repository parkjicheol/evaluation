package com.bgfnc.web.evaluation.model;

import com.bgfnc.web.evaluation.repository.MemberRepository;
import com.bgfnc.web.evaluation.service.MemberService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="question")
public class Question extends AuditModel {

    @Id
    @Column(name = "seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seq;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 100)
    private String title;

    @NotNull
    @Column(columnDefinition = "text")
    private String description;

    @OneToOne
    @JoinColumn(name = "member_seq")
    private Member member;

    @OneToMany(targetEntity=Answer.class, mappedBy="question")
    @OrderBy("registerDate DESC")
    @Builder.Default
    private List<Answer> answers = new ArrayList<Answer>();

    @Builder
    public Question(String title, String description) {
        this.title = title;
        this.description = description;
    }

}
