package com.example.demo.board.domain;

import javax.persistence.*;

@Entity // entity 테이블과 매핑되는 클래스에 지정하는 애노테이션
@Table(name = "article") // 테이블 메타 데이터
@SecondaryTable( // 이거 추카 매핑할 테이블을 정의할 때 사용하는 애노테이션
    name = "article_content",   // 매핑할 다른 테이블의 이름
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "id")  // 매핑할 다른 테이블의 기본 키 컬럼 속성.
)
public class Article {

    @Id // 테이블의 로우 데이터 식별자
    @GeneratedValue(strategy = GenerationType.IDENTITY) //  IDENTITY 전략은 디비 채번의 AUTO_INCREMENT 를 사용하는 것처럼 데이터베이스에 값을 저장하고 나서야 기본 키 값을 구할 수 있을 때 사용한다.
    private Long id;

    private String title;

    @AttributeOverrides({
            @AttributeOverride( // 임베디드 타입에 정의한 매핑정보를 재정의할 때 사용
                    name = "content",
                    column = @Column(table = "article_content", name = "content")),
            @AttributeOverride(
                    name = "contentType",
                    column = @Column(table = "article_content", name = "content_type"))
    })
    @Embedded // 새로운 값 타입을 직접 정의해서 사용할 때 사용 (복합 값 타입, 클래스로 받아야 할 때)
    private ArticleContent content;

    protected Article(){}

    public Article(String title, ArticleContent content){
        this.title = title;
        this.content = content;
    }

    public Long getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public ArticleContent getContent(){
        return content;
    }

}
