package ticketmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Register {
	public static void show(Connection con)throws Exception
	{
		BookMovie bobj=new BookMovie();
		Register robj=new Register();
		Main1 obj=new Main1();
		Scanner in=new Scanner(System.in);
		System.out.println("Enter Your Mobile Number ;");
		long num=in.nextLong();
		boolean has_result= robj.checkuser(con,num);
				if(has_result==true)
			       {
			    	   System.out.println("Your Already Registered : ");
			    	   System.out.println("----------------------------");
			    	   bobj.Movie_details(con,num); 
			       }
			       else
			       {
			    	   System.out.println("Your Not Register,Kindly Register : ");
			    	   obj.display(con);   
			       } 
				}
	public void register(Connection con) throws Exception
	{
		Main1 obj=new Main1();
		Register robj=new Register();
	PreparedStatement preparedStatement = null;
	try {
		Scanner cu = new Scanner(System.in);
		System.out.println("Enter Your Name : ");
		String name = cu.nextLine();
		System.out.println("Please RE-Enter Your Mobilenumber : ");
		long phonenumber=cu.nextLong();
		boolean has_result=robj.checkuser(con,phonenumber);
		if(has_result==false)
		{
		preparedStatement = con.prepareStatement("insert into customers(user_name,phone_number) values(?,?)");
		preparedStatement.setString(1, name);
		preparedStatement.setLong(2, phonenumber);		
		preparedStatement.executeUpdate();
		System.out.println(" Thank you Register succesfully ");
		obj.display(con);
		preparedStatement.close();
		con.close();
		}
		else  
		{
			System.out.println("-----------Thanking you----------\n\n-------- Your Are Already Registered---------");
		obj.display(con);
		}
		
	}

	catch (Exception e) {
		System.out.println(e);
	}  
}
	public boolean checkuser(Connection con,long num) throws Exception
	{
		   Scanner in =new Scanner(System.in);
	       Statement stmt=con.createStatement();
	       ResultSet rs =stmt.executeQuery("select phone_number from customers where phone_number ='"  +num+  "';");
	       boolean has_result=rs.next();
	       if(has_result)
	       {
	    	   return has_result;
	       }
	       else
	       {
	    	   return has_result;
	       }
	}
}
