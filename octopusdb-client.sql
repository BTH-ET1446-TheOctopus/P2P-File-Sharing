-- Last modification date: 2016-05-14 16:12:19.232
-- Client Database

-- Create Client Database
CREATE database clientdb;
USE clientdb;

-- tables
-- Table: clients

-- Table: peersarray
CREATE TABLE peersarray (
    id int NOT NULL AUTO_INCREMENT,		
    uniquefileid varchar(100) NOT NULL,    
    peers varchar(15) NOT NULL,
    CONSTRAINT peersarray_pk PRIMARY KEY (id)
);

-- Table: clientswarm
CREATE TABLE clientswarm (
    filename char(40) NOT NULL,
    totalblocks int NOT NULL,
    peers varchar(15) NOT NULL,
    peercount int NOT NULL,
    uniquefileid varchar(100) NOT NULL,
    filechecksum varchar(100) NOT NULL,
    metadatachecksum varchar(100) NOT NULL,		
    CONSTRAINT clientswarm_pk PRIMARY KEY (uniquefileid)
);

-- Table: clientpeers
CREATE TABLE clientpeers (
    id varchar(100) NOT NULL,
    latestip varchar(15) NOT NULL,
    blaklist binary(1) NOT NULL,
    timestamp timestamp NOT NULL,
    files varchar(100) NOT NULL,
    filecount int NOT NULL,
    CONSTRAINT clientpeers_pk PRIMARY KEY (id)
);

-- End of file.

