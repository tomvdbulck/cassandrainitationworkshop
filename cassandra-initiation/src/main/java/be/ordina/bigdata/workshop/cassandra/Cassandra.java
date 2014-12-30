package be.ordina.bigdata.workshop.cassandra;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.InvalidQueryException;
import com.datastax.driver.core.exceptions.NoHostAvailableException;

public class Cassandra {

	private static final String CASSANDRA_HOST_URL = "127.0.0.1";
	private static final String CASSANDRA_DATABASE = "demo";

	private static final Logger log = Logger.getLogger(Cassandra.class.getName());
	
	private Cluster cluster = null;
	private Session session = null;

	public Session getCassandraClient() {
		if (session == null) {
			session = startConnection();
		}

		return session;
	}

	private Session startConnection() {
		try {
			// Connecting to Redis server on localhost
			log.info("Start connection to cassandra.");
			cluster = Cluster.builder().addContactPoint(CASSANDRA_HOST_URL).build();
			
			Metadata metadata = cluster.getMetadata();
			log.info("Connected to cluster: " + metadata.getClusterName());
			for ( Host host : metadata.getAllHosts() ) {
				log.info("Datacenter: " + host.getDatacenter() + "; Host: " + host.getAddress() + "; Rack: " + host.getRack());
			} 
			
			try {
				session = cluster.connect(CASSANDRA_DATABASE);
			} catch (InvalidQueryException iqe) {
				session = cluster.connect();
				createKeyspace();
				createUsertable();
			}
			
			log.info("Succesfully connected to cassandra.");
			
			return session;
		} catch (NoHostAvailableException cassandraException) {
			log.log(Level.SEVERE, "Can't connect to Cassandra on : " + CASSANDRA_HOST_URL, cassandraException);
			throw cassandraException;
		}
	}
	
	private void createKeyspace() {
		log.info("Creating keyspace: " + CASSANDRA_DATABASE);
		session.execute("CREATE KEYSPACE " + CASSANDRA_DATABASE + " WITH replication " + 
			      "= {'class':'SimpleStrategy', 'replication_factor':3};");
		
		session.execute("USE " + CASSANDRA_DATABASE + ";");
		
	}
	
	private void createUsertable() {
		log.info("Creating table for users.");
		session.execute("CREATE TABLE users (user_id int PRIMARY KEY,fname text,lname text);");
		
	}
	
	public void shutdownCassandraClient(){
		if (!cluster.isClosed()) {
			cluster.close();
		}
	}
	
}
