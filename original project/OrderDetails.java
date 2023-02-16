package ticketmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class OrderDetails {

	public void orders(Connection con,int num2,long num) throws Exception
	{
		int price=0;
		int totalprice=0;
		int getseats=0;
		int upseats=0;
		int seatsav=0;
		Main1 obj=new Main1();
		Register robj=new Register();
		OrderDetails oobj=new OrderDetails();
		Scanner in = new Scanner(System.in);
		PreparedStatement preparedStatement = null;
	try(Statement stmt=con.createStatement())
	{
		ResultSet rs1=stmt.executeQuery("select theater_name,seats,price from theaters");
		while(rs1.next()){
			System.out.println("Theater : "+rs1.getString(1)+"\nSeats : "+rs1.getInt(2)+""+"\nprice : "+rs1.getInt(3)+"\n---------------------");
		}
		System.out.println("select Your Theater : ");
		int ans=in.nextInt();
		ResultSet rs2= stmt.executeQuery("select price from theaters where theater_id ="+ans+";");
		while (rs2.next())
		{
			price =rs2.getInt(1);
		}
		System.out.println("Enter Your Ticket Count :");
		int ticket_count =in.nextInt();
		totalprice=ticket_count*price;
		System.out.print("Your Total Price = ");
		System.out.println(totalprice);
		System.out.println("amount");
		int uamount=in.nextInt();
		System.out.println("Enter Your payment method : ");
		String payment_via = in.next();
		if (totalprice==uamount)
		{
		ResultSet rs4=stmt.executeQuery("select seats from theaters where theater_id="+ans+";");
		while (rs4.next())
		{
			getseats= rs4.getInt(1);
		}
		seatsav=getseats-ticket_count;
				//System.out.println(seatsav);
				//System.out.println(ans);
		PreparedStatement pr=con.prepareStatement("update theaters set seats = ? where theater_id=?");
		pr.setInt(1, seatsav);
		pr.setInt(2, ans);
		pr.executeUpdate();
		ResultSet rs3=stmt.executeQuery("select * from customers where phone_number ="+num+";" );
		int id=0;
		while(rs3.next())
		{
			id=rs3.getInt("customer_id");
		}
		long millis=System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		System.out.print("debug-->1");
		preparedStatement =con.prepareStatement("insert into orders (ticket_count,amount,payment_via,movie_id,customer_id,theater_id,booking_date) values(?,?,?,?,?,?,?)");
		preparedStatement.setInt(1, ticket_count);
		preparedStatement.setInt(2, totalprice);
		preparedStatement.setString(3, payment_via);
		preparedStatement.setInt(4,num2);
		preparedStatement.setInt(5, id);
		preparedStatement.setInt(6, ans);
		preparedStatement.setDate(7, date);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		System.out.println("---------Your Booking Has Successfully Done---------");
		obj.display(con);
		con.close();
	}
		else
		{
			System.out.println("---------------------------------------------------------------------------------------------------");
			System.out.println("Your Not Entered Vaild Amount Once Your Enter Correct Amount OtherWise You Can't book Your Tickets");
			System.out.println("---------------------------------------------------------------------------------------------------");
		}oobj.orders(con, num2, num);
	}
	
	
	catch ( Exception e)
	{
		System.out.println(e);
	}
}
 
	public void history(Connection con) throws Exception
	{
		try
		{
			Register robj=new Register();
			Main1 obj=new Main1();
			Statement stmt = con.createStatement();
			Scanner in=new Scanner(System.in);
			System.out.println("Enter Your Mobile Number : ");
			long num=in.nextLong();
			boolean has_result=robj.checkuser(con,num);
			if(has_result)
			{
			System.out.println("Your Movie Details : ");
			ResultSet rs=stmt.executeQuery("select order_id,user_name,movie_names,theater_name,ticket_count,amount,booking_date from orders \r\n"
					+ "inner join customers on orders.customer_id=customers.customer_id \r\n"
					+ "inner join movies on orders.movie_id=movies.movie_id \r\n"
					+ "inner join theaters on orders.theater_id=theaters.theater_id where phone_number="+num+";");
			System.out.printf("%10s %10s %10s %10s %6s %14s %14s ", " Order Id |", " Customer Name |", " Movie Name |","Theater_name|", " Ticket Count |", " Total Price|", " booking_date|");
			System.out.println();
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
			while(rs.next())
			{
			System.out.format("%6s %12s %19s %12s %12s %14s %20s ",rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getDate(7)+"\n");
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
			}
			System.out.println("-----------------------------------------------------ThankYou For Your Support--------------------------------------------------------->");	
		}
			else
			{
				System.out.println("Your Not Register Kindly Register ");
				obj.display(con);
			}
		}
		
		catch (Exception e)
		{
			System.out.println(e);
		}
		
	}

}	


