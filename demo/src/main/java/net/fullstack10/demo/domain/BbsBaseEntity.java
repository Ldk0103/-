package net.fullstack10.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

//super 메서드 사용을 위한 어노테이션(상속하여 필드만 공유하도록)
@MappedSuperclass

//엔티티가 생성되거나 수정될 때 자동으로 특정 필드를 채우도록 함
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

// 상위 클래스의 프로퍼티도 빌더에 포함 시킴 --> 클래스명.builer().build() 상속받은 객체도 사용할 수 있음
@SuperBuilder(toBuilder = true)
public abstract class BbsBaseEntity {

    //엔티티가 처음 생성될 때 자동으로 작성자를 저장
    @CreatedDate
    //데이터베이스의 reg_date 컬럼에 매핑되며, null이 허용되지 않음
    @Column(name="reg_date", nullable = false, columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일'")
    private LocalDateTime reg_date;

    //수정한 사용자 정보를 자동으로 저장하는 데 사용하는 어노테이션
    @LastModifiedDate

    //DB 테이블의 modify_date 컬럼에 매핑됩니다.
    //nullable은 null값이 허용됨
    //insertable은 insert시에는 이 값을 넣지 않는다(처음 생성될 때는 이 값이 무시됨)
    @Column(name="modify_date", nullable = true, insertable = false, updatable = true, columnDefinition = "DATETIME NULL DEFAULT NULL COMMENT '수정일'")
    private LocalDateTime modify_date;

    public void setModify_date(LocalDateTime modify_date) {
        this.modify_date = modify_date;
    }
}
