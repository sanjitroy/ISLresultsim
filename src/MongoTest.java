/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sroy13
 */
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import java.util.Arrays;

public class MongoTest{
   public static void main( String args[] ){
       String teams[] = new String[20];
       double table[][] = new double[10][20];
      try{   
		 // To connect to mongodb server
         MongoClient mongoClient = new MongoClient( "novus.modulusmongo.net" , 27017 );
         // Now connect to your databases
         DB db = mongoClient.getDB( "yzaqib6A" );
		 System.out.println("Connect to database successfully");
                 String myUserName = "Frodo";
                 char[] myPassword = {'b','a','g','g','i','n','s'};
         boolean auth = db.authenticate(myUserName, myPassword);
		 System.out.println("Authentication: "+auth);
                 DBCollection coll = db.getCollection("bplstats");
                 System.out.println("Collection pujos selected successfully");
      }catch(Exception e){
	     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	  }
   }
}