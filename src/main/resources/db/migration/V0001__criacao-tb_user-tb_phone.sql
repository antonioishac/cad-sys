CREATE TABLE TB_USER (
	ID VARCHAR(100) PRIMARY KEY,
	NAME VARCHAR(100) NOT NULL,
	EMAIL VARCHAR(50) NOT NULL,
	PASSWORD VARCHAR(100) NOT NULL
);

CREATE TABLE TB_PHONE (
	DDD VARCHAR(2) NOT NULL,
	PHONE_NUMBER VARCHAR(20) NOT NULL,
	ID_USER VARCHAR(100) NOT NULL,
	FOREIGN KEY (ID_USER) REFERENCES TB_USER(ID)
);