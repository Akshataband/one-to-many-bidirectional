package onetomanybidirectional;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;



public class ClgStudentDriver {
	public static void main(String[] args) {
//addData();
//getStudById(3);
//getStudByClgId(101);
//getClgById(101);
//getClgByStudId(5);
//UpdateStudent(5, "Siya");
//UpdateCollege(103, "fc college");
//DeleteStudent(8);
//allocateCollege(103, 4); 
//deallocateCollege(102, 6); 
findAll();
	}

	public static void addData() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		Student s1 = new Student();
		s1.setName("Shivani");
		s1.setBranch("it");

		Student s2 = new Student();
		s2.setName("vedashri");
		s2.setBranch("mechanical");

		ArrayList li = new ArrayList();
		li.add(s1);
		li.add(s2);

		College c = new College();
		c.setName("modern college");
		c.setLocation("pune");

		c.setStudent(li);

		s1.setC(c);
		s2.setC(c);

		et.begin();
		em.persist(s1);
		em.persist(s2);
		em.persist(c);
		et.commit();
	}

	public static void addStudent() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		Student s1 = new Student();
		s1.setName("Anushka");
		s1.setBranch("Civil");

		Student s2 = new Student();
		s2.setName("Murli");
		s2.setBranch("Mechanical");

		ArrayList li = new ArrayList();
		li.add(s1);
		li.add(s2);

		et.begin();
		em.persist(s1);
		em.persist(s2);
		et.commit();

	}

	public static void addCollege() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		College c = new College();
		c.setName("SPPU");
		c.setLocation("ShivajiNagar");

		et.begin();
		em.persist(c);
		et.commit();
	}

	public static void getStudById(int sid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		Student s = em.find(Student.class, sid);
	    System.out.println("Student ID: " + s.getId() + ", Name: " + s.getName()) ;

	}

	public static void getStudByClgId(int cid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		College c = em.find(College.class, cid);
	    c.getStudent().forEach(s -> System.out.println(s.getId() + " " + s.getName()));
	}

	public static void getClgById(int cid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		College c = em.find(College.class, cid);
	    System.out.println("College ID: " + c.getId() + ", Name: " + c.getName());

	}

	public static void getClgByStudId(int sid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		Student s = em.find(Student.class, sid);
	    System.out.println("College: " + s.getC().getName());
		
	}

	public static void UpdateStudent(int sid, String newName) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction et = em.getTransaction();

	    et.begin();
	    Student s = em.find(Student.class, sid);
	    s.setName(newName);
	    em.merge(s);
	    et.commit();
	}


	public static void UpdateCollege(int cid, String newName) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction et = em.getTransaction();

	    et.begin();
	    College c = em.find(College.class, cid);
	    c.setName(newName);
	    em.merge(c);
	    et.commit();
	}


	public static void DeleteStudent(int sid) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction et = em.getTransaction();

	    et.begin();
	    Student s = em.find(Student.class, sid);
	    em.remove(s);
	    et.commit();
	}


	public static void allocateCollege(int cid, int sid) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction et = em.getTransaction();

	    College c = em.find(College.class, cid);
	    Student s = em.find(Student.class, sid);

	    s.setC(c); 

	    List<Student> list = c.getStudent();
	    list.add(s); 
	    c.setStudent(list);
	    et.begin();
	    em.merge(s); 
	    em.merge(c); 
	    et.commit();
	}


	public static void deallocateCollege(int cid, int sid) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction et = em.getTransaction();

	    College c = em.find(College.class, cid);
	    Student s = em.find(Student.class, sid);

	    List<Student> list = c.getStudent();
	    list.remove(s); 
	    s.setC(null);  
	    
	    et.begin();
	    em.merge(s); 
	    em.merge(c); 
	    et.commit();
	}

	public static void findAll() {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
	    EntityManager em = emf.createEntityManager();

	    List<Student> list = em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
	    list.forEach(s -> System.out.println(s.getId() + " " + s.getName()));
	}

}
