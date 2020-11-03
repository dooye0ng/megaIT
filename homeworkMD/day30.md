# Day 30 Homework

## 1. CREATE MEMBER TABLE
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

## 2. CREATE QNA TABLE
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