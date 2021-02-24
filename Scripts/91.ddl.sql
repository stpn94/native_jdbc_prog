-- 학생관리
DROP SCHEMA IF EXISTS native_jdbc;

-- 학생관리
CREATE SCHEMA native_jdbc;

-- 학생관리프로그램
CREATE TABLE native_jdbc.student (
	stdNo   INT(10)     NOT NULL COMMENT '학생번호', -- 학생번호
	stdName VARCHAR(20) NULL     COMMENT '학생명', -- 학생명
	kor     INT(10)     NULL     COMMENT '국어', -- 국어
	eng     INT(10)     NULL     COMMENT '영어', -- 영어
	math    INT(10)     NULL     COMMENT '수학' -- 수학
)
COMMENT '학생관리프로그램';

-- 학생관리프로그램
ALTER TABLE native_jdbc.student
	ADD CONSTRAINT student -- 학생관리프로그램 기본키
		PRIMARY KEY (
			stdNo -- 학생번호
		);