package ticketmanagement.project;

import java.util.*;
import java.sql.*;

public class Main1
{
	public static Connection getConnection() 
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");  
		    Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_siva","root","tiger");
		    return con;
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
	}
	  public  void display()
      {
    	  BookMovie bobj=new BookMovie();
    	  Register robj=new Register();
  		OrderDetails oobj=new OrderDetails();
    	  Scanner in=new Scanner(System.in);  
//    	  System.out.println(" WELCOME TO BIGG BOSS MOVIE");
//    	  System.out.println("1.Book Your Movie");
    	  System.out.println("2.Register your Id");
    	  System.out.println("3.See your Booking Details");
    	  System.out.println("---------------------------");
    	  System.out.println("Choose Your Page : ");
    	  Connection con =getConnection();
    	  int option =in.nextInt();
    	  try 
    	  {
    	  switch(option)
    	  {
    	  case 1:
    	    robj.show(con);
    	    break;
    	  case 2:
    		robj.register(con);
    		break;
    	  case 3:
    		  oobj.history(con);
    		  break;
    	  }
          }
	     catch (Exception e)
    	  {
	    	 System.out.println(e);
    	  }
      }
       
	
	public static void main(String[] args) 
	{
		Main1 obj=new Main1();
		BookMovie bobj=new BookMovie();
		
		long num = 0;
		int num2=0;
		obj.display();
//		try
//		{
//			OrderDetails oobj=new OrderDetails();
//		Class.forName("com.mysql.jdbc.Driver");  
//	    Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_siva","root","tiger");
//	      obj.display(con);
//		//oobj.orders(con, num2, num);
//		//bobj.Movie_details(con, num);
//	   
//		}
//		catch (Exception e)
//		{
//			System.out.println(e);
//		}
	}
}
