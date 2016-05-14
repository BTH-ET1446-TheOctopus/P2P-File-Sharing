-- Last modification date: 2016-05-06 16:18:34.567
-- Server Database

-- Create Server Database
CREATE database serverdb;
USE serverdb;

-- tables
-- Table: bootstrapserver
CREATE TABLE bootstrapserver (
    ip varchar(15) NOT NULL,
    name char(20) NOT NULL,
    timestamp timestamp NOT NULL,
    clientcount int NOT NULL,
    servercount int NOT NULL,
    CONSTRAINT bootstrapserver_pk PRIMARY KEY (ip)
);

-- Table: peerarray
CREATE TABLE peersarray (
    uniquefileid varchar(100)  NOT NULL,    
    peers varchar(15) NOT NULL,
    CONSTRAINT peersarray_pk PRIMARY KEY (uniquefileid)
);

-- Table: serverswarm
CREATE TABLE serverswarm (
    filename char(40) NOT NULL,
    totalblocks int NOT NULL,
    peers varchar(15) NOT NULL,
    peercount int NOT NULL,
    uniquefileid varchar(100) NOT NULL,
    filechecksum varchar(100) NOT NULL,
    metadatachecksum varchar(100) NOT NULL,		
    CONSTRAINT serverswarm_pk PRIMARY KEY (uniquefileid)
);

-- Table: serverpeers
CREATE TABLE serverpeers (
    id varchar(100) NOT NULL,
    latestIP varchar(15) NOT NULL,
    blacklist binary(1) NOT NULL,
    timestamp timestamp NOT NULL,
    CONSTRAINT serverpeers_pk PRIMARY KEY (id)
);

-- End of file.

