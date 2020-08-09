import java.net.*;
import java.sql.*;
import java.net.http.*;
import org.json.*;

import si.mazi.rescu.oauth.*;
import si.mazi.rescu.serialization.*;
import si.mazi.rescu.utils.*;

import com.mysql.cj.xdevapi.JsonNumber;
import com.mysql.cj.xdevapi.JsonValue;

import java.util.*;
import java.util.Date;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.connection.Exchange;

public class Arb {

    String API_URL = "";

    public double bitcoin_high;
    public double bitcoin_low;

    public double ethereum_high;
    public double ethereum_low;

    public double ripple_high;
    public double ripple_low;

    public String orderBookUpdateStatus = "";

    // GET Request
    // Synchronous

    public String runUpdate_Crypto(String url) throws IOException {
    	OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
            .url(url)
            .get()
            .build();

        try (Response response = client.newCall(request).execute()) {
          return update_bf(response.body().string());
        }
    }

    // For Async check out these urls
    // https://www.vogella.com/tutorials/JavaLibrary-OkHttp/article.html
    // https://www.baeldung.com/guide-to-okhttp

    public String update_Crypto (String responseBody) {
    	JSONArray crypto = new JSONArray(responseBody);
    	
    	// Bitcoin values
    	bitcoin_high = crypto.getDouble(1);
    	bitcoin_low = crypto.getDouble(3);
    	
    	// Ethereum Values
    	ethereum_high = crypto.getDouble(1);
    	ethereum_low = crypto.getDouble(3);
    	
    	// Rip values
    	ripple_high = crypto.getDouble(1);
        ripple_low = crypto.getDouble(3);
        
        // Sometimes you can just get the values like this as they are nested within a JSONArray or JSONObject.
        // Can be nested multiple times)
  	
   	    //PRINT OUT
        orderBookUpdateStatus = ("Crypto value Updated");
    	
        System.out.println("bitcoin high = " + bitcoin_high);
        System.out.println("bitcoin low = " + bitcoin_low);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Ethereum high = " + ethereum_high);
        System.out.println("Ethereum Low = " + ethereum_low + "\n");
        System.out.println(~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~);
        System.out.println("Rip High = " + ripple_high);
        System.out.println("Rip Low = " + ripple_low);
        System.out.println(~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~);

    	return bfOrderBookUpdated;
    }

    // MySQL Database methods

    // Connect to database/schema
    public Connection getConnection() throws Exception {
    	
    	try {
            String driver = "com.mysql.cj.jdbc.Driver";
            // Replace "test" with your current schema name
			String url = "jdbc:mysql://localhost:3306/test"; 
			String username = "User";
			String password = "password,1";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, username, password);
//			System.out.println("Connection Established"); // Have this to check if connection works.
			return conn;
		} catch (Exception e) {
			System.out.println(e);
		}
    	
    	return null;
    }

    // Create table in Schema
    public void createTable_crypto() throws Exception{
    	
    	try {
			
    		Connection conn = getConnection();
    		PreparedStatement create = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Crypto(Date varchar(255), bitcoin_high DOUBLE(10,5), bitcoin_low DOUBLE(10,5), ethereum_high DOUBLE(10,5), ethereum_low DOUBLE(10,5), ripple_high DOUBLE(10,5), ripple_low DOUBLE(10,5))");
    		create.executeUpdate();
    		
		} 
    	catch (Exception e) {System.out.println(e);} 
    	finally{System.out.println("Crypto Table Created");}
    	
    }
    
    //CHUCKS THE NEW VALUES INTO A DATABASE
    public void post_crypto() throws Exception{
    	Date date = java.util.Calendar.getInstance().getTime();
    	String currDate = date.toString();
    	
    	Double BTC_High = bitcoin_high;
    	Double BTC_Low = bitcoin_low;
    	Double ETH_High = ethereum_high;
    	Double ETH_Low = ethereum_low;
    	Double XRP_High = ripple_high;
    	Double XRP_Low = ripple_low;
    	
    	try {
			Connection conn = getConnection();
			
			PreparedStatement posted = conn.prepareStatement("INSERT INTO Crypto (Date, bf_BTC_High, bf_BTC_Low, bf_ETH_High, bf_ETH_Low, bf_XRP_High, bf_XRP_Low) VALUES ('"+currDate+"', '" +BTC_High+ "', '"+BTC_Low+"', '" +ETH_High+ "', '"+ETH_Low+"', '"+XRP_High+"', '"+XRP_Low+"')");
			posted.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("Value Inserted");
		}
    }
    
    // DELETE ENTIRE TABLE DATA
    public void del_crypto() throws Exception{
    	try {
			Connection conn = getConnection();
			PreparedStatement posted = conn.prepareStatement("DELETE FROM Crypto");
			posted.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("Table Cleared");
		}
    }

    public double btc_arb = 0.0;
    public double btc_arb_percentage = 0.0;

    public double eth_arb = 0.0;
    public double eth_arb_percentage = 0.0;

    public double xrp_arb = 0.0;
    public double xrp_arb_percentage = 0.0;

    public void mathsStuff () {
        /**
         * Calculate the values between different sites and see their difference in values.
         * This is entirely up to how you want to calculate it and display it
         * so a template here wont make much difference
         */
    }

    private static void print(String string) {
		System.out.println(string);
	}

    public void print_arb_calc_result() {
    	print("\n----------------------------------\n");
    	

        print("\n\nCrypto");
        print("--------------------------------");
        
        print("\nValue: $" + btc_arb);
        print("Arbitrage %: " + btc_arb_percentage + "%");

        print("\nValue: $" + eth_arb);
        print("Arbitrage %: " + eth_arb_percentage + "%");

        print("\nValue: $" + xrp_arb);
        print("Arbitrage %: " + xrp_arb_percentage + "%");
        
        print("\n\n----------------------------------\n");
    }

    /**
     * You only want to run if you are possibly debugging, otherwise run it from GUI side.
     */
    public static void main(String[] args) {

        /**
         * You want it to infinitely run + update prices
         * You want it to update in a way where you are not making too many requests and you arent taking forever to update.
         */
        
        Arb arb = new Arb();
        arb.createTable_crypto();

        Timer cryptoTimer = new Timer();
		cryptoTimer.scheduleAtFixedRate(new TimerTask() {
			
            @Override
            public void run() {
            	try {
                    
                    arb.runUpdate_Crypto(API_URL); // If it doesnt update values, check this first.
                    arb.post_crypto();
                    arb.mathsStuff();
                    arb.print_arb_calc_result();
            	} 
            	catch (Exception e) {System.out.println(e);}
            }
        }, 5000,5000);	
    }
    
}