package ticketmanagement;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class BookMovie
{

	public static void Movie_details(Connection con,long num) throws Exception
	{
		Scanner in = new Scanner(System.in);
		BookMovie bobj=new BookMovie();
		Register robj=new Register();
			System.out.println("------------------------------");
			System.out.println("Happy To Booking Your Tickets!!!");
			System.out.println("------------------------------");
			System.out.println(" hi siva 4th line added once you verifeid please approve")
		
		try 
		{
			OrderDetails oobj=new OrderDetails();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select movie_names from movies " );
			System.out.println(" Movies : ");
            System.out.println("----------------------");
				 while (rs.next()) 
				  {
					System.out.println(rs.getString(1));
				  }
			System.out.println("---------------------");
			System.out.println(" Select Your Movie :");
			System.out.println("---------------------");
			int num2 = in.nextInt();
		    System.out.println("----------------------------");
			System.out.println("select Your Show Time : ");
			System.out.println("----------------------------");
			ResultSet rs1 = stmt.executeQuery("select show_time,show_time1 from movies where movie_id=" + num2+ ";");
			     while (rs1.next()) 
			     {
				  System.out.println("1.showtime :" +rs1.getString("show_time") +"\n---------------------"+ "\n2.showtime1 :" +rs1.getString("show_time1")+"\n---------------------" );
				 int show=in.nextInt();
				 if(show==1)
				   {
				  //show1=rs1.getString(1));
				  System.out.println(1);
				  oobj.orders(con, num2,num);
				   }
				 else
				   {
				  System.out.println("    1)Your Select Your show Time :"  +rs1.getString(2));
				  oobj.orders(con,num2,num);
				   }
			     } 
		}
				catch (Exception e) 
				{
					System.out.println(e);
				}
	}
	
}	
	
	

