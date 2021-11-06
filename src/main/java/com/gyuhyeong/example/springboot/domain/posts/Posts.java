package com.gyuhyeong.example.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor  // 기본 생성자 자동 추가
@Entity // 테이블과 링크될 클래스임을 나타내는 어노테이션. 기본값으로 클래스의 카멜케이스 이름으로 어더스코어 네이밍(_)으로 테이블 이름을 매칭(ex. SalesManger.java => sales_manager table)
public class Posts {

    @Id // 해당 테이블의 PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성 규칙. 스트링부트 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야지만 auto_increment가 된다
    private Long id;

    @Column(length = 500, nullable = false) // 테이블의 컬럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 컬럼이 된다. 기본값 이외에 추가로 변경이 필요한 옵션이 있으면 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    // 생성자 대신 @Builder를 사용하게되면 어느 필드에 어떤 값을 채워야 할지 인지 할 수 있다.
    @Builder // 해당 클래스의 빌더 패턴 클래스 생성. 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

}
