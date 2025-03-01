DROP SEQUENCE IF EXISTS bm_id_seq;
CREATE SEQUENCE bm_id_seq START WITH 1 INCREMENT BY 50; 
-- hibernate by default uses increment by 50 
CREATE TABLE bookmarks (
    id BIGINT DEFAULT NEXT VALUE FOR bm_id_seq NOT NULL,
    title VARCHAR(255) NOT NULL,
    url VARCHAR(255) NOT NULL,
    created_at TIMESTAMP,
    PRIMARY KEY (id)
);
