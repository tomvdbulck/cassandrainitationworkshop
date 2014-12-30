package be.ordina.bigdata.workshop.cassandra;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class CassandraTest {

	Cassandra cassandra;
	Session cassandraSession;
	
	
	@Before
	public void init(){
		cassandra = new Cassandra();
		cassandraSession = cassandra.getCassandraClient();
	}
	
	@After
	public void destroy(){
		cassandra.shutdownCassandraClient();
	}
	
	@Test
	public void testConnection(){
		//Successful if no exception is thrown during init.
	}
	
	@Test
	public void testWriteReadDelete(){
		cassandraSession.execute("INSERT INTO users (user_id,  fname, lname) VALUES (1111, 'Luc', 'De Cleene');");
		cassandraSession.execute("INSERT INTO users (user_id,  fname, lname) VALUES (2222, 'Tom', 'Van den Bulck');");
		cassandraSession.execute("INSERT INTO users (user_id,  fname, lname) VALUES (3333, 'Chris', 'De Bruyne');");
		
		ResultSet results = cassandraSession.execute("SELECT * FROM users;");
		List<Row> rows = results.all();
		
		assertEquals(3, rows.size());
		
		for (Row row : rows) {
			assertNotNull(row.getInt("user_id"));
			assertNotNull(row.getString("fname"));
			assertNotNull(row.getString("lname"));
			System.out.println(row.getInt("user_id") + " : " + row.getString("fname") + " " + row.getString("lname"));
		}
		
		cassandraSession.execute("TRUNCATE users;");
		
		results = cassandraSession.execute("SELECT * FROM users;");
		rows = results.all();
		
		assertEquals(0, rows.size());
	}
	
}
