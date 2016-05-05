-- Created by Farhan Ahmad
-- Last modification date: 2016-05-6 17:17:55.701
-- Client Database

-- Create Client Database
CREATE database clientdb;
USE clientdb;

-- tables
-- Table: clients

-- Table: peersarray
CREATE TABLE peersarray (
    uniquefileid int NOT NULL,    
    peers varchar(100) NOT NULL,
    CONSTRAINT peersarray_pk PRIMARY KEY (uniquefileid)
);

-- Table: clientfile
CREATE TABLE clientfile (
     filename char(40) NOT NULL,
    totalblocks int NOT NULL,
    filetype char(20) NOT NULL,
    peers varchar(100) NOT NULL,
    peercount int NOT NULL,
    uniquefileid int NOT NULL,
    filechecksum int NOT NULL,
    metadatachecksum int NOT NULL,		
    CONSTRAINT clientfile_pk PRIMARY KEY (uniquefileid)
);

-- Table: clientpeers
CREATE TABLE clientpeers (
    id int NOT NULL,
    latestip int NOT NULL,
    blaklist binary(1) NOT NULL,
    timestamp timestamp NOT NULL,
    files varchar(100) NOT NULL,
    filecount int NOT NULL,
    CONSTRAINT clientpeers_pk PRIMARY KEY (id)
);

-- End of file.

