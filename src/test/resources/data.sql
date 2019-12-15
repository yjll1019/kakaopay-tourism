insert into region values('region02e22707', '강원도 속초');
insert into region values('region02e21231', '강원도 양양');

insert into theme(name) values('생태체험');
insert into theme(name) values('문화체험');

insert into program(contents, introduce, name) values ('강원도 여행', '강원도로 떠나는 여행', '강원도');
insert into program(contents, introduce, name) values ('양양 여행', '양양으로 가는 여행', '양양');

insert into program_regions values(1, 'region02e22707');
insert into program_themes values(1, 1);

insert into program_regions values(2, 'region02e21231');
insert into program_themes values(2, 2);