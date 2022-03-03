package fi.hibernate;

import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import fi.beans.UserProfile;

public class Entry {
	public static void main(String[] args)
	{
		Configuration hibernateConfig = new Configuration();
		hibernateConfig.configure("user.cfg.xml");
	try (SessionFactory hibernateFactory = hibernateConfig.buildSessionFactory();
				Session hibernateSession = hibernateFactory.openSession();
				Scanner scanner = new Scanner(System.in))

		{
		    //saveUser(hibernateSession, scanner);

			loadUser(hibernateSession, scanner);

		} catch (HibernateException e)
    {
			e.printStackTrace();
		}
	}

	static void loadUser(Session hibernateSession, Scanner scanner) {
		System.out.println("Enter the user name whose data you want to fetch");
		String userName = scanner.next();
		UserProfile user = (UserProfile) hibernateSession.load(UserProfile.class, userName);
		System.out.println(user.getName());
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
	}

	static void saveUser(Session hibernateSession, Scanner scanner) {
		System.out.println("Enter the username");
		String userName = scanner.next();
		System.out.println("Enter the password");
		String password = scanner.next();
		System.out.println("Enter the name");
		String name = scanner.next();
		System.out.println("Enter the email");
		String email = scanner.next();
		UserProfile objuser = new UserProfile(userName, password, name, email);
		Transaction hibernatetx = hibernateSession.beginTransaction();
		hibernateSession.save(objuser);
		hibernatetx.commit();
		System.out.println("Record Saved...");
	}
}