package com.lee.web.aop;


import java.lang.annotation.*;

@Target(ElementType.METHOD)        // 적용 대상: 메서드
@Retention(RetentionPolicy.RUNTIME) // 실행 시에도 유지됨
@Documented
public @interface LogExecutionTime {
    /*
        어노테이션	설명
        @Target	어노테이션이 적용될 위치 지정
    → METHOD, TYPE, FIELD 등
        @Retention	어노테이션이 유지되는 기간 지정
    → SOURCE, CLASS, RUNTIME
        @Documented	JavaDoc에 포함되도록 지정 (선택사항)
        @Inherited	상속받는 클래스에 자동 적용할지 여부 (TYPE에만 유효)
    */
}