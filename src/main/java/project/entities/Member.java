package project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;
import project.commons.constants.MemberType;

import java.time.LocalDateTime;

@Data
@Entity
@Table(indexes = {
        @Index(name="idx_member_userNm", columnList = "userNm"),
        @Index(name="idx_member_mobile", columnList = "mobile")
})
public class Member {

    @Id
    private Long userNo;

    private String email;

    private String password;

    private String userNm;

    private String mobile;

    private MemberType mtype = MemberType.USER;

    private LocalDateTime regDt;
    private LocalDateTime modDt;
}