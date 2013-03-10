package server.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;

import chronos.Person;
import chronos.Singleton;

import events.AuthEvent;
import events.CalEvent;
import events.QueryEvent;

/**
 * Handles the connection with the database server, and executes queries
 * 
 * @author anon
 * 
 */
public class DatabaseController implements DatabaseControllerInterface {
	private String jdbcDriver;
	private Connection conn;
	private String url;
	private String user;
	private String password;
	private Properties prop;

	public DatabaseController() {
		prop = loadConfig();
	}

	private Properties loadConfig() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("Properties.properties")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public void initialize() {
		jdbcDriver = prop.getProperty("jdbcDriver");
		url = prop.getProperty("url");
		user = prop.getProperty("user");
		password = prop.getProperty("password");

		try {
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public boolean execute(String sql) throws SQLException {
		Statement st = conn.createStatement();
		return st.execute(sql);
	}

	public ResultSet makeSingleQuery(String sql) throws SQLException {
		Statement st = conn.createStatement();
		return st.executeQuery(sql);
	}

	public int makeUpdate(String sql) throws SQLException {
		Statement st = conn.createStatement();
		return st.executeUpdate(sql);
	}

	public PreparedStatement makeBatchUpdate(String sql) throws SQLException {
		PreparedStatement st = conn.prepareStatement(sql);
		return st;
	}

	public void closeConnection() throws SQLException {
		conn.close();
	}

	@Override
	public AuthEvent authenticateUser(AuthEvent event) {
		Singleton.log("Authenticating " + event.getUsername());
		event.setPerson(new Person(event.getUsername(), "bob"));
		event.setAccessGranted(true);
		return event;
	}

	@Override
	public QueryEvent getAvailableRooms(QueryEvent event) {
		return null;
	}

	@Override
	public QueryEvent getUsers(QueryEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getCalEvents(Person person) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getNewCalEvents(Person person) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addCalEvent(CalEvent event, Person person) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCalEvent(CalEvent event, Person person) {
		// TODO Auto-generated method stub

	}

	@Override
	public Date lastLoggedIn(Person person) {
		// TODO Auto-generated method stub
		return null;
	}
}
