package backend.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import backend.Settings;
import backend.json.Blacklist;
import backend.json.Bootstraps;
import backend.json.ID;
import backend.json.Peers;
import backend.json.PeersInfo;
import backend.json.Swarm;
import backend.json.Swarms;
import backend.json.SwarmsHelper;
import backend.json.SwarmsInfo;
import backend.json.Sync;
import backend.json.TestAddress;
import sql.DatabaseAPI;
import sql.DatabaseCalls;
import sql.sqlconnector;

import java.sql.*;
import java.util.UUID;

import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/rest")
public class Rest {
	private static final Logger LOG = Logger.getLogger(Rest.class.getName());
	private DatabaseAPI database = new DatabaseCalls();

	@GET
	@Path("/test/")
	@Produces(MediaType.APPLICATION_JSON)
	public TestAddress convertCtoF(@Context org.glassfish.grizzly.http.server.Request re) {
		System.out.println(re.getRemoteAddr());
		TestAddress adr = new TestAddress();
		adr.setAge(32);
		adr.setName("Fidde");
		adr.setSurename("Lass");

		// System.out.println(uriInfo.getBaseUri());
		return adr;
	}

	/**
	 * Gives the client an UUID if it has none and and update the Valid time for
	 * the client.
	 * 
	 * @param id The clients UUID if it has any
	 * @return the client UUID
	 */
	@GET
	@Path("/hello/")
	@Produces(MediaType.APPLICATION_JSON)
	public ID getHello(@Context org.glassfish.grizzly.http.server.Request caller, @QueryParam("id") String id) {
		ID respons = new ID();

		if (database.isBlacklisted(caller.getRemoteAddr())) {
			respons = null;
		} else {
			if (database.isPeerIDExisting(id)) {
				respons.setid(id);
				database.updatePeer(caller.getRemoteAddr(), respons.getid(), Settings.getNTP());
				LOG.log(Level.INFO, "Peer update validy time and ip: " + id);
			} else {
				UUID uuid = UUID.randomUUID();
				respons.setid(uuid.toString());
				database.addPeers(respons.getid(), caller.getRemoteAddr(), false, Settings.getNTP());
				LOG.log(Level.INFO, "New peer connected id: " + respons.getid());
			}
		}
		return respons;
	}

	/**
	 * Used by the client to retrive data about the peers that the server is
	 * knowing of
	 * 
	 * @return Peers, list of peers connected to bootstrap
	 */
	@GET
	@Path("/peers/")
	@Produces(MediaType.APPLICATION_JSON)
	public Peers getPeers(@Context org.glassfish.grizzly.http.server.Request caller) {
		Peers peers = new Peers();
		if (database.isBlacklisted(caller.getRemoteAddr())) {
			peers = null;
		} else {
			peers = database.getpeers();
		}
		return peers;
	}

	/**
	 * Used by the client to get all the bootstrap servers the bootstrap server
	 * is aware of. This is in case something happens to our main bootstrap
	 * 
	 * @return list of all bootstraps that the bootstrap knows of
	 */

	@GET
	@Path("/bootstraps/")
	@Produces(MediaType.APPLICATION_JSON)
	public Bootstraps getBootstraps(@Context org.glassfish.grizzly.http.server.Request caller) {
		Bootstraps bootstraps = new Bootstraps();
		if (database.isBlacklisted(caller.getRemoteAddr())) {
			bootstraps = null;
		} else {
			bootstraps = database.getBootstraps();
		}
		return bootstraps;
	}

	/**
	 * Used by the client to get the blacklist from the bootstrap.
	 * 
	 * @return list of blacklisted peers
	 */

	@GET
	@Path("/blacklist/")
	@Produces(MediaType.APPLICATION_JSON)
	public Blacklist getBlacklist(@Context org.glassfish.grizzly.http.server.Request caller) {
		Blacklist blacklist = new Blacklist();
		if (database.isBlacklisted(caller.getRemoteAddr())) {
			blacklist = null;
		} else {
			blacklist = database.getBlacklist();
		}
		return blacklist;
	}

	/**
	 * Gives the client a list of all swarms that the bootstrap has. In order to
	 * get more information about those swarms one need to use the UUID that is
	 * provided in the response
	 * 
	 * @return list of swarms
	 */
	@GET
	@Path("/swarms/")
	@Produces(MediaType.APPLICATION_JSON)
	public SwarmsHelper getSwarms(@Context org.glassfish.grizzly.http.server.Request caller) {
		SwarmsHelper swarmHelp = new SwarmsHelper();
		if (database.isBlacklisted(caller.getRemoteAddr())) {
			swarmHelp = null;
		} else {
			swarmHelp = database.getSwarms();
		}
		return swarmHelp;
	}

	/**
	 * The client wants to download a certain file and use the UUID as a id.
	 * This gives the client access to the information needed to start download
	 * the file from different peers
	 * 
	 * @param re used to access clients IP
	 * @param id the UUID of the file the client wants
	 * @return detailed information about a specific swarm
	 */

	@GET
	@Path("/swarms/{swarmID}/")
	@Produces(MediaType.APPLICATION_JSON)
	public Swarm getSwarm(@Context org.glassfish.grizzly.http.server.Request caller,
			@PathParam("swarmID") String swarmID, @QueryParam("clientID") String clientID) {
		Swarm swarm = new Swarm();

		if (database.isBlacklisted(caller.getRemoteAddr())) {
			swarm = null;
		} else {
			if (database.isSwarmExisting(swarmID)) {
				//if (database.isClientOnSwarm(swarmID, clientID) == false) {
					// New client connecting to swarm
					swarm = database.getSwarm(swarmID);
					
					//if (database.updateSwarm(swarmID, clientID)) {
						// Success on update swarm
						LOG.log(Level.INFO, "Client: " + clientID + " added on swarm: " + swarmID);
					//} else {
						// Error on update swarm
						LOG.log(Level.WARNING, "Could not update swarm " + swarmID + " whit: " + clientID);
					}
				//} else {
					// Client already is on swarm
					LOG.log(Level.INFO, "Client " + clientID + " already on swarm: " + swarmID);
				}
			//}
		//}
		return swarm;
	}

	/**
	 * Used by the client to push a new swarm to the bootstraps database to make
	 * it public and may be used by others peers to access the file
	 * 
	 * @param re used to access peers IP
	 * @param blockCount number of blocks the file contains of
	 * @param filename
	 * @param fileChecksum the SHA sum used for file
	 * @param metadataChecksum The SHA sum used for the files header
	 * @return
	 */
	@POST
	@Path("/swarms/")
	@Produces(MediaType.APPLICATION_JSON)
	public ID addSwarmDB(@Context org.glassfish.grizzly.http.server.Request caller,
			@QueryParam("blockCount") int blockCount, @QueryParam("filename") String filename,
			@QueryParam("fileChecksum") String fileChecksum, @QueryParam("metadataChecksum") String metadataChecksum,
			@QueryParam("clientID") String clientID) {
		
		ID respons = null;
		if (database.isBlacklisted(caller.getRemoteAddr())) {
			return respons;
		} else {

			if (blockCount > 0) {
				if(filename != null){
					if(metadataChecksum != null){
						if(fileChecksum != null){
							if(database.isPeerIDExisting(clientID)){
								UUID uuid = UUID.randomUUID();
								//database.addSwarmDB(clientID, blockCount, filename, fileChecksum, metadataChecksum, uuid.toString());
								respons = new ID(uuid.toString());
							} else {
								//Peer does not exist
								LOG.log(Level.WARNING, "Non existing client tries to add file from: " + caller.getRemoteAddr());
							}
						} else {
							//The file checksum is null
							LOG.log(Level.WARNING, "Client: " + clientID + " IP: " + caller.getRemoteAddr() + " tries to uploade swarm whitout file checksum" );
						}
						
					} else {
						//The metadata checksum is null
						LOG.log(Level.WARNING, "Client: " + clientID + " IP: " + caller.getRemoteAddr() + " tries to uploade swarm whitout metadata checksum" );
					}
				} else {
					//filename is empty
					LOG.log(Level.WARNING, "Client: " + clientID + " IP: " + caller.getRemoteAddr() + " tries to uploade swarm whitout filename" );
				}
				
			} else {
				//block count is less than 0
				LOG.log(Level.WARNING, "Client " + clientID + " IP: " + caller.getRemoteAddr() + "tries to uploade swarm whit zero blocks ");
			}
		}
		return respons;
	}

	/**
	 * Function used by bootstrap servers to sync whit each other.
	 * @return Bootstrap information for other servers
	 */
	@GET
	@Path("/sync/")
	@Produces(MediaType.APPLICATION_JSON)
	public Sync sync(@Context org.glassfish.grizzly.http.server.Request caller) {
		Sync sync = new Sync();
		if (database.isBlacklisted(caller.getRemoteAddr())) {
			sync = null;
		} else {
			sync = database.getSync();
		}
		return sync;
	}
}
