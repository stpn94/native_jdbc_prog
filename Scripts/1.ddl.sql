-- select user(),database();
-- 
-- -- 내 스키마
-- DROP SCHEMA IF EXISTS native_jdbc;
-- 
-- -- 내 스키마
-- CREATE SCHEMA native_jdbc;

-- 직책
CREATE TABLE native_jdbc.title (
	tno   INT         NOT NULL COMMENT '직책코드', -- 직책코드
	tname VARCHAR(20) NOT NULL COMMENT '직책명' -- 직책명
)
COMMENT '직책';

-- 직책
ALTER TABLE native_jdbc.title
	ADD CONSTRAINT PK_title -- 직책 기본키
		PRIMARY KEY (
			tno -- 직책코드
		);

-- 부서
CREATE TABLE native_jdbc.department (
	deptno   INT         NOT NULL COMMENT '부서번호', -- 부서번호
	deptname VARCHAR(20) NOT NULL COMMENT '부서명', -- 부서명
	floor    INT         NULL     COMMENT '위치' -- 위치
)
COMMENT '부서';

-- 부서
ALTER TABLE native_jdbc.department
	ADD CONSTRAINT PK_department -- 부서 기본키
		PRIMARY KEY (
			deptno -- 부서번호
		);

-- 사원
CREATE TABLE native_jdbc.employee (
	empno   INT       NOT NULL COMMENT '사원번호', -- 사원번호
	empname VARCHAR(20) NOT NULL COMMENT '사원명', -- 사원명
	title   INT       NULL     COMMENT '직책', -- 직책
	manager INT       NULL     COMMENT '직속상사', -- 직속상사
	salary  INT       NULL     COMMENT '급여', -- 급여
	dept    INT       NULL     COMMENT '부서' -- 부서
)
COMMENT '사원';

-- 사원
ALTER TABLE native_jdbc.employee
	ADD CONSTRAINT PK_employee -- 사원 기본키
		PRIMARY KEY (
			empno -- 사원번호
		);

-- 사원
ALTER TABLE native_jdbc.employee
	ADD CONSTRAINT FK_title_TO_employee -- 직책 -> 사원
		FOREIGN KEY (
			title -- 직책
		)
		REFERENCES native_jdbc.title ( -- 직책
			tno -- 직책코드
		);

-- 사원
ALTER TABLE native_jdbc.employee
	ADD CONSTRAINT FK_employee_TO_employee -- 사원 -> 사원
		FOREIGN KEY (
			manager -- 직속상사
		)
		REFERENCES native_jdbc.employee ( -- 사원
			empno -- 사원번호
		);

-- 사원
ALTER TABLE native_jdbc.employee
	ADD CONSTRAINT FK_department_TO_employee -- 부서 -> 사원
		FOREIGN KEY (
			dept -- 부서
		)
		REFERENCES native_jdbc.department ( -- 부서
			deptno -- 부서번호
		);
		
--  계정 권한 추가

grant all on native_jdbc.* to 'user_native_jdbc'@'localhost'
identified by 'rootroot';

select deptNo, deptName, floor from department;

create or replace view vw_full_employee
as 
select e.empno, e.empname, t.tno as title_no , t.tname as title_name, e.manager as manager_no,
	   m.empname as manager_name, e. salary, d.deptno ,d.deptname,d.floor 
	from employee e
		join title t on e.title = t.tno 
		left join employee m on e.manager = m.empno 
		join department d on e.dept = d.deptno ;

select * from vw_full_employee;

select empno,empname,title_no,title_name,manager_no,manager_name,salary,deptno,deptname,floor
	from vw_full_employee ;

select empno,empname,title as title_no, manager as manager_no,salary,dept as deptNo
	from employee e where empno = 1003;