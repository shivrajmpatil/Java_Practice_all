import java.util.*;
import java.sql.*;

public class EmployeeAppJDBC {

	public static void main(String[] args) throws Exception{
		
		Scanner scan = new Scanner(System.in);
		
//		com.mysql.cj.jdbc.Driver d = new com.mysql.cj.jdbc.Driver();
//		DriverManager.registerDriver(d);
		
		//Class.forName("com.mysql.cj.jdbc.Driver");//replace above two line using Class.forName method.
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb","root", "2143");
		String s=conn!=null? "Database Connected":"Some Problem to Connect database";
		System.out.println(s);
		
		int no=1,check;
		int Eid,Esalary;
		String Ename,Eemail,Econtact; //define variables for Employee Information
		while(no!=0)
		{
			
			System.out.println("1. Create Employee table\n2. Insert records of employee\n3.Display all records of employees\n4.Display employee records using id or name\n5.Display employees id and name of same salary\n6. Update employee records by id.\n7. Delete record of emloyee by using id or name\nEnter your choice");
			no=scan.nextInt();
			
			
			switch(no)
			{
				
			
			case 1: PreparedStatement pstc = conn.prepareStatement("create table Employee(Eid int(5), Ename varchar(200) not null, Eemail varchar(200) unique, Esalary int(5), Econtact varchar(10))");
						check=pstc.executeUpdate();
						s=check<1?"Employee table created sucessfully":"Some problem to create table";
						System.out.println(s);
				break;
				
				
			
			case 2: System.out.print("How many employee you want to add");
						int n=scan.nextInt();
						for(int i=0; i<n; i++)
						{
							
						System.out.println("Enter id of employee");
				 		Eid = scan.nextInt();
				
				 		System.out.println("Enter name of employee");
				 		Ename = scan.next();
				 
				 		System.out.println("Enter Email of employee");
				 		Eemail = scan.next();
				 		
				 		System.out.println("Enter Salary of employee");
				 		Esalary = scan.nextInt();
				 		
				 		System.out.println("Enter Contact of employee");
				 		Econtact = scan.next();
				 		
				 		PreparedStatement psti= conn.prepareStatement(" insert into Employee values(?,?,?,?,?)");
				 		
				 		psti.setInt(1, Eid);
				 		psti.setString(2,Ename);
				 		psti.setString(3, Eemail);
				 		psti.setInt(4, Esalary);
				 		psti.setString(5, Econtact);
				 		
				 		check=psti.executeUpdate();
				 		s=check>0?"Employee Records Added Sucessfully":"Some problem to add employee records";
				 		System.out.println(s);
				 		
						}
						
				 break;
				 
				
			
			
			case 3: PreparedStatement psts= conn.prepareStatement("select *from employee");
						ResultSet rs = psts.executeQuery();
						boolean flag=true;
						while(rs.next())
						{	
							flag=false;
							System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getInt(4)+"\t"+rs.getString(5));
						}
						
						if(flag)
							System.out.print("Records not found");
				break;
				
				
			
			
			case 4: System.out.println("Enter id and name of employee");
					Eid=scan.nextInt();
					Ename=scan.next();
				
					PreparedStatement pstss= conn.prepareStatement("select *from employee where Eid=? or Ename=?");
						pstss.setInt(1, Eid);
						pstss.setString(2, Ename);
						rs = pstss.executeQuery();
						flag=true;
						while(rs.next())
						{	
							flag=false;
							System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getInt(4)+"\t"+rs.getString(5));
						}
						
						if(flag)
							System.out.println("Record not found..");
						
				break;
				 
				
				
			case 5: System.out.println("Enter salary to search");
					Esalary = scan.nextInt();
					
					PreparedStatement pstsal= conn.prepareStatement("select Eid, Ename from employee where Esalary=?");
					pstsal.setInt(1, Esalary);
					rs = pstsal.executeQuery();
					flag=true;
					while(rs.next())
					{
						flag=false;
						System.out.println(rs.getInt("Eid")+"\t"+rs.getString("Ename"));
					}
					
					if(flag)
						System.out.println("Record not found");
					
				break;
				
				
				
			case 6: System.out.println("Enter Employee id for update records");
					Eid = scan.nextInt();
			
					System.out.println("Enter name of employee");
					Ename = scan.next();
	 
					System.out.println("Enter Email of employee");
					Eemail = scan.next();
	 		
					System.out.println("Enter Salary of employee");
					Esalary = scan.nextInt();
	 		
					System.out.println("Enter Contact of employee");
					Econtact = scan.next();
	 		
					PreparedStatement pstu= conn.prepareStatement(" update employee set Ename=?, Eemail=?, Esalary=?, Econtact=? where Eid=?");
					pstu.setString(1,Ename);
					pstu.setString(2, Eemail);
					pstu.setInt(3, Esalary);
					pstu.setString(4, Econtact);
					pstu.setInt(5, Eid);
	 		
					check=pstu.executeUpdate();
					s=check>0?"Employee Records Update Sucessfully":"Some problem to Update employee records";
					System.out.println(s);
					
				break;
				
				
				
			case 7: System.out.println("Enter Employee id and name for delete record");
					Eid = scan.nextInt();
					Ename =scan.next();
					
					PreparedStatement pstd = conn.prepareStatement("delete from employee where Eid=? or Ename=?");
					pstd.setInt(1, Eid);
					pstd.setString(2, Ename);
					
					check=pstd.executeUpdate();
					s=check>0?"Employee Record Deleted Sucessfully":"Some problem to Delete employee record";
					System.out.println(s);
					
				break;
			
				
				
			case 0: System.out.println("Exit Sucessfully.......");
			break;
			
			
			
			default: System.out.println("Invalid input");
			}
		}
	}

}
