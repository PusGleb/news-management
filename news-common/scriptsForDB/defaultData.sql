--------------------------------------------------------
--  File created - Wednesday-November-18-2015
--------------------------------------------------------
--------------------------------------------------------
--  DDL for default insert for TABLE COMMENTS
--------------------------------------------------------
Insert all into COMMENTS (COMMENT_ID,NEWS_ID,COMMENT_TEXT,CREATION_DATE) values (1,1,'comment1',(TIMESTAMP '2012-01-21 22:22:22'))
 into COMMENTS (COMMENT_ID,NEWS_ID,COMMENT_TEXT,CREATION_DATE) values (2,1,'comment2',(TIMESTAMP '2012-02-21 22:22:22'))
 into COMMENTS (COMMENT_ID,NEWS_ID,COMMENT_TEXT,CREATION_DATE) values (3,1,'comment3',(TIMESTAMP '2012-03-21 22:22:22'))
 into COMMENTS (COMMENT_ID,NEWS_ID,COMMENT_TEXT,CREATION_DATE) values (4,2,'comment4',(TIMESTAMP '2012-04-21 22:22:22'))
 into COMMENTS (COMMENT_ID,NEWS_ID,COMMENT_TEXT,CREATION_DATE) values (5,2,'comment5',(TIMESTAMP '2012-05-21 22:22:22'))
 into COMMENTS (COMMENT_ID,NEWS_ID,COMMENT_TEXT,CREATION_DATE) values (6,2,'comment6',(TIMESTAMP '2012-06-21 22:22:22'))
 into COMMENTS (COMMENT_ID,NEWS_ID,COMMENT_TEXT,CREATION_DATE) values (7,2,'comment7',(TIMESTAMP '2012-07-21 22:22:22'))
 into COMMENTS (COMMENT_ID,NEWS_ID,COMMENT_TEXT,CREATION_DATE) values (8,2,'comment8',(TIMESTAMP '2012-08-21 22:22:22'))
 into COMMENTS (COMMENT_ID,NEWS_ID,COMMENT_TEXT,CREATION_DATE) values (9,2,'comment9',(TIMESTAMP '2012-09-21 22:22:22'))
 into COMMENTS (COMMENT_ID,NEWS_ID,COMMENT_TEXT,CREATION_DATE) values (10,2,'comment9',(TIMESTAMP '2012-09-21 22:22:22'))
SELECT * FROM dual;

--------------------------------------------------------
--  DDL for default insert for TABLE NEWS
--------------------------------------------------------

Insert all into NEWS (NEWS_ID,TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE,MODIFICATION_DATE) values (1,'title1','short_text1','full_text1',(TIMESTAMP '2012-02-21 22:22:22'),to_date('09-NOV-15','DD-MON-RR'))
 into NEWS (NEWS_ID,TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE,MODIFICATION_DATE) values (2,'title2','short_text2','full_text2',(TIMESTAMP '2012-02-22 22:22:22'),to_date('19-NOV-15','DD-MON-RR'))
 into NEWS (NEWS_ID,TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE,MODIFICATION_DATE) values (3,'title3','short_text3','full_text3',(TIMESTAMP '2012-02-23 22:22:22'),to_date('19-NOV-15','DD-MON-RR'))
 into NEWS (NEWS_ID,TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE,MODIFICATION_DATE) values (4,'title4','short_text4','full_text4',(TIMESTAMP '2012-02-24 22:22:22'),to_date('19-NOV-15','DD-MON-RR'))
 into NEWS (NEWS_ID,TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE,MODIFICATION_DATE) values (5,'title5','short_text5','full_text5',(TIMESTAMP '2012-02-25 22:22:22'),to_date('19-NOV-15','DD-MON-RR'))
 into NEWS (NEWS_ID,TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE,MODIFICATION_DATE) values (6,'title6','short_text6','full_text6',(TIMESTAMP '2012-02-26 22:22:22'),to_date('19-NOV-15','DD-MON-RR'))
 into NEWS (NEWS_ID,TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE,MODIFICATION_DATE) values (7,'title7','short_text7','full_text7',(TIMESTAMP '2012-02-27 22:22:22'),to_date('19-NOV-15','DD-MON-RR'))
 into NEWS (NEWS_ID,TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE,MODIFICATION_DATE) values (8,'title8','short_text8','full_text8',(TIMESTAMP '2012-02-28 22:22:22'),to_date('19-NOV-15','DD-MON-RR'))
 into NEWS (NEWS_ID,TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE,MODIFICATION_DATE) values (9,'title9','short_text9','full_text9',(TIMESTAMP '2012-02-29 22:22:22'),to_date('19-NOV-15','DD-MON-RR'))
 into NEWS (NEWS_ID,TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE,MODIFICATION_DATE) values (10,'title10','short_text10','full_text10',(TIMESTAMP '2012-02-14 22:22:22'),to_date('19-NOV-15','DD-MON-RR'))
 SELECT * FROM dual;

--------------------------------------------------------
--  DDL for default insert for TABLE AUTHOR
--------------------------------------------------------

Insert all into AUTHOR (AUTHOR_ID,AUTHOR_NAME,EXPIRED) values (1,'author1',(TIMESTAMP '2012-01-21 22:22:22'))
 into AUTHOR (AUTHOR_ID,AUTHOR_NAME,EXPIRED) values (2,'author2',(TIMESTAMP '2012-01-21 22:22:22'))
 into AUTHOR (AUTHOR_ID,AUTHOR_NAME,EXPIRED) values (3,'author3',(TIMESTAMP '2012-01-21 22:22:22'))
 into AUTHOR (AUTHOR_ID,AUTHOR_NAME,EXPIRED) values (4,'author4',(TIMESTAMP '2012-01-21 22:22:22'))
 into AUTHOR (AUTHOR_ID,AUTHOR_NAME,EXPIRED) values (5,'author5',(TIMESTAMP '2012-01-21 22:22:22'))
 into AUTHOR (AUTHOR_ID,AUTHOR_NAME,EXPIRED) values (6,'author6',(TIMESTAMP '2012-01-21 22:22:22'))
 into AUTHOR (AUTHOR_ID,AUTHOR_NAME,EXPIRED) values (7,'author7',(TIMESTAMP '2012-01-21 22:22:22'))
 into AUTHOR (AUTHOR_ID,AUTHOR_NAME,EXPIRED) values (8,'author8',(TIMESTAMP '2012-01-21 22:22:22'))
 into AUTHOR (AUTHOR_ID,AUTHOR_NAME,EXPIRED) values (9,'author9',(TIMESTAMP '2012-01-21 22:22:22'))
 into AUTHOR (AUTHOR_ID,AUTHOR_NAME,EXPIRED) values (10,'author10',(TIMESTAMP '2012-01-21 22:22:22'))
 SELECT * FROM dual;

--------------------------------------------------------
--  DDL for default insert for TABLE TAG
--------------------------------------------------------

Insert all into TAG (TAG_ID,TAG_NAME) values (1,'tag1')
 into TAG (TAG_ID,TAG_NAME) values (2,'tag2')
 into TAG (TAG_ID,TAG_NAME) values (3,'tag3')
 into TAG (TAG_ID,TAG_NAME) values (4,'tag4')
 into TAG (TAG_ID,TAG_NAME) values (5,'tag5')
 into TAG (TAG_ID,TAG_NAME) values (6,'tag6')
 into TAG (TAG_ID,TAG_NAME) values (7,'tag7')
 into TAG (TAG_ID,TAG_NAME) values (8,'tag8')
 into TAG (TAG_ID,TAG_NAME) values (9,'tag9')
 into TAG (TAG_ID,TAG_NAME) values (10,'tag10')
 SELECT * FROM dual;

--------------------------------------------------------
--  DDL for default insert for NEWS_AUTHOR
--------------------------------------------------------

Insert all into NEWS_AUTHOR (NEWS_ID,AUTHOR_ID) values (1,1)
into NEWS_AUTHOR (NEWS_ID,AUTHOR_ID) values (2,2)
into NEWS_AUTHOR (NEWS_ID,AUTHOR_ID) values (3,3)
into NEWS_AUTHOR (NEWS_ID,AUTHOR_ID) values (4,4)
into NEWS_AUTHOR (NEWS_ID,AUTHOR_ID) values (5,5)
into NEWS_AUTHOR (NEWS_ID,AUTHOR_ID) values (6,6)
into NEWS_AUTHOR (NEWS_ID,AUTHOR_ID) values (7,7)
into NEWS_AUTHOR (NEWS_ID,AUTHOR_ID) values (8,8)
into NEWS_AUTHOR (NEWS_ID,AUTHOR_ID) values (9,9)
into NEWS_AUTHOR (NEWS_ID,AUTHOR_ID) values (10,10)
SELECT * FROM dual;

--------------------------------------------------------
--  DDL for default insert for NEWS_TAG
--------------------------------------------------------

Insert all into NEWS_TAG (NEWS_ID,TAG_ID) values (1,1)
into NEWS_TAG (NEWS_ID,TAG_ID) values (2,2)
into NEWS_TAG (NEWS_ID,TAG_ID) values (3,3)
into NEWS_TAG (NEWS_ID,TAG_ID) values (4,4)
into NEWS_TAG (NEWS_ID,TAG_ID) values (5,5)
into NEWS_TAG (NEWS_ID,TAG_ID) values (6,6)
into NEWS_TAG (NEWS_ID,TAG_ID) values (7,7)
into NEWS_TAG (NEWS_ID,TAG_ID) values (8,8)
into NEWS_TAG (NEWS_ID,TAG_ID) values (9,9)
into NEWS_TAG (NEWS_ID,TAG_ID) values (10,10)
SELECT * FROM dual;