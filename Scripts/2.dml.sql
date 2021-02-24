select user(),database();

show tables;

desc title;
desc department ;
desc employee ;

select * from title;

select * from department ;
select * from employee  ;

select tno,tname from title;
select deptNo, deptName, floor from department;


select tno,tname from title	where tno = 3;
select empno, empname, title, manager, salary, dept from employee ;
insert into title values(6 , '인턴');
delete from title where tno = 6;
delete from department where deptno = 5;
delete from employee where empno = 1004;
select deptno,deptname,floor from department ;
insert into department values(2, '청소',)

select empno,empname,title,manager,salary,dept from employee  ;

create table student(
stdNo int
);
 

insert into student values (1, "전수린", 90, 90, 90),
(2, "김상건", 91, 91, 91),
(3, "이태훈", 92, 92, 92);

insert into employee values (1004, '천사', 5, 4377, 2000000, 1);

select * from department;

update employee 
set dept = 3
where empno =1004;

delete
from employee
where empno = 1004;

update title set tname ='계약직', where tno =6;


-- 1번 부서에 소속된 사원
select empno, empname, title, manager, salary,dept from employee where dept = (select deptNo from department where deptno= 1);
