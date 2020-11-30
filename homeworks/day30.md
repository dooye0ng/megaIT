# Day 30 Homework

## Homwork 01
#### 1. 이름이 '피'로 시작하는 포켓몬들의 이름, 레벨 알려줘 (유사검색) 
```sql 
SELECT name, level FROM pokemon WHERE name LIKE '피%';  
```
```
+--------+-------+
| name   | level |
+--------+-------+
| 피카츄 |     7 |
| 피츄   |     5 |
+--------+-------+
```
#### 2. 등록일자가 2020/01/01 이전인 포켓몬들의 번호, 이름, 등록일자 알려줘 
```sql 
SELECT no, name, regdate FROM pokemon WHERE regdate <= 20200101000000;  
```
```
Empty set (0.000 sec)
```
#### 3. 레벨이 5 이상 10 이하인 포켓몬들의 모든 정보 알려줘  
```sql
SELECT * FROM pokemon WHERE level BETWEEN 5 and 10;  
```
```
+----+--------+-------+---------------------+
| no | name   | level | regdate             |
+----+--------+-------+---------------------+
|  1 | 피카츄 |     7 | 2020-11-03 12:10:32 |
|  2 | 피츄   |     5 | 2020-11-03 12:10:41 |
+----+--------+-------+---------------------+
```
#### 4. 모든 포켓몬들의 레벨 보여줘  
```sql
SELECT level FROM pokemon;  
```
```
+-------+
| level |
+-------+
|     7 |
|     5 |
|    12 |
|     4 |
|    13 |
|    13 |
+-------+
```
#### 5. 모든 포켓몬들의 레벨 보여줘. 중복 제거 해줘. (DISTINCT) 
```sql
SELECT DISTINCT level FROM pokemon;
```
```
+-------+
| level |
+-------+
|     7 |
|     5 |
|    12 |
|     4 |
|    13 |
+-------+
```
#### 6. 모든 포켓몬들의 이름, 레벨 보여줘. 이름 오름차순으로 보여줘  (ORDER BY)
```sql
SELECT name, level FROM pokemon ORDER BY name;
```
```
+--------+-------+
| name   | level |
+--------+-------+
| 꼬부기 |    13 |
| 잉어킹 |     4 |
| 파이리 |    12 |
| 푸린   |    13 |
| 피츄   |     5 |
| 피카츄 |     7 |
+--------+-------+
```
#### 7. 모든 포켓몬들의 이름, 레벨 보여줘. 레벨 많은 순으로 보여줘  (ORDER BY)
```sql
SELECT name, level FROM pokemon ORDER BY level DESC;
```
```
+--------+-------+
| name   | level |
+--------+-------+
| 꼬부기 |    13 |
| 푸린   |    13 |
| 파이리 |    12 |
| 피카츄 |     7 |
| 피츄   |     5 |
| 잉어킹 |     4 |
+--------+-------+
```
#### 8. 레벨이 3 이상인 모든 포켓몬들의 이름, 레벨 보여줘. 레벨 많은 순으로 보여줘, 같은 레벨이면 이름 오름차순으로 보여줘  (ORDER BY)
```sql
SELECT name, level FROM pokemon WHERE level >= 3 ORDER BY level DESC, name;
```
```
+--------+-------+
| name   | level |
+--------+-------+
| 꼬부기 |    13 |
| 푸린   |    13 |
| 파이리 |    12 |
| 피카츄 |     7 |
| 피츄   |     5 |
| 잉어킹 |     4 |
+--------+-------+
```
#### 9. 모든 포켓몬들의 이름, 레벨 보여줘. 등록일자 최신순으로 보여줘.
```sql
SELECT name, level FROM pokemon ORDER BY regdate DESC;
```
```
+--------+-------+
| name   | level |
+--------+-------+
| 푸린   |    13 |
| 꼬부기 |    13 |
| 잉어킹 |     4 |
| 파이리 |    12 |
| 피츄   |     5 |
| 피카츄 |     7 |
+--------+-------+
```
## Homwork 02 
#### CREATE MEMBER TABLE
```sql
CREATE TABLE member(
	no INT AUTO_INCREMENT,
	id VARCHAR(30) UNIQUE,
	password VARCHAR(30) UNIQUE,
	email VARCHAR(40) NOT NULL,
	type ENUM('1','2','3','4') DEFAULT '1',
	point INT DEFAULT 1000 CHECK (point >= 0),
	
	PRIMARY KEY(no)
);
```
```
+----+--------+----------+------------------+------+-------+
| no | id     | password | email            | type | point |
+----+--------+----------+------------------+------+-------+
|  1 | user01 | pw01     | user01@email.com | 1    |  1000 |
|  2 | user02 | pw02     | user02@email.com | 2    |  1400 |
|  3 | user03 | pw03     | user03@email.com | 3    |  2200 |
+----+--------+----------+------------------+------+-------+
```

#### CREATE QNA TABLE
```sql
CREATE TABLE qna(
	no INT AUTO_INCREMENT,
	writerNo INT,
	content TEXT NOT NULL,
	regdate DATETIME DEFAULT CURRENT_TIMESTAMP,
	
	PRIMARY KEY(no),
	FOREIGN KEY(writerNo)
	REFERENCES MEMBER(no) ON DELETE CASCADE
);
```
```
+----+----------+-----------------+---------------------+
| no | writerNo | content         | regdate             |
+----+----------+-----------------+---------------------+
|  1 |        1 | content23434282 | 2020-11-03 11:58:19 |
|  2 |        3 | content13114217 | 2020-11-03 11:58:42 |
|  3 |        3 | content83421284 | 2020-11-03 11:58:43 |
|  4 |        2 | content03234289 | 2020-11-03 11:58:44 |
+----+----------+-----------------+---------------------+
```