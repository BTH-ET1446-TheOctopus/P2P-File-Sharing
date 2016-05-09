-- Created by Farhan Ahmad
-- Last modification date: 2016-05-06 17:17:55.701
-- Server Database

-- Create Server Database
CREATE database serverdb;
USE serverdb;

-- tables
-- Table: servers
CREATE TABLE servers (
    ip varchar(100) NOT NULL,
    name char(20) NOT NULL,
    timestamp timestamp NOT NULL,
    clientcount int NOT NULL,
    servercount int NOT NULL,
    CONSTRAINT Servers_pk PRIMARY KEY (ip)
);

-- Table: servers
CREATE TABLE peersarray (
    uniquefileid int NOT NULL,    
    peers varchar(100) NOT NULL,
    CONSTRAINT peersarray_pk PRIMARY KEY (uniquefileid)
);

-- Table: serverfile
CREATE TABLE serverfile (
    filename char(40) NOT NULL,
    totalblocks int NOT NULL,
    peers varchar(100) NOT NULL,
    peercount int NOT NULL,
    uniquefileid int NOT NULL,
    filechecksum varchar(100) NOT NULL,
    metadatachecksum varchar(100) NOT NULL,		
    CONSTRAINT serverfile_pk PRIMARY KEY (uniquefileid)
);

-- Table: serverpeers
CREATE TABLE serverpeers (
    id int NOT NULL,
    latestIP varchar(100) NOT NULL,
    blacklist binary(1) NOT NULL,
    timestamp timestamp NOT NULL,
    CONSTRAINT serverpeers_pk PRIMARY KEY (id)
);

-- End of file.

