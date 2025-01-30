import java.time.LocalDate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entidad.Address;
import entidad.Student;

public class CreateAuthorWithBooks {

	public static void main(String[] args) {
		
		// crear sesión
		SessionFactory sf = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Author.class)
				.buildSessionFactory();
		
		Session s = sf.getCurrentSession();
		
		try {
			// crea un objeto author
			System.out.println("Creando un nuevo objeto Author...");
			Author tempauthor = createAuthor();
			
			// comienza la transacción
			s.beginTransaction();
			
			// guarda el objeto Student
			System.out.println("Guardando el estudiante...");
			System.out.println(tempauthor);
			s.save(tempauthor);
			
			// hace commit de la transacción
			s.getTransaction().commit();
			
			// obtenemos una nueva sesión y realizamos una nueva transacción
			s = sf.getCurrentSession();
			s.beginTransaction();
			
			// obtenemos (leemos) el estudiante de la bbdd
			System.out.println("\n Obteniendo estudiante con id: " + tempStudent.getId());
			Student myStudent = s.get(Student.class, tempStudent.getId());
			System.out.println("Get completado: " + myStudent);
			
			// hace commit de la transacción
			s.getTransaction().commit();
			System.out.println("Hecho");
		} catch (Exception e) {
			// rollback ante alguna excepción
			System.out.println("Realizando Rollback");
			s.getTransaction().rollback();
			e.printStackTrace();		
		} finally {
			s.close();
			sf.close();
		}
	}

	private static Author createAuthor() {
		
		Author temp= new Author();
		Book tempb= new Book();
		temp.setNombre("MIKEL");
		temp.setApellido("UNAMUNO");
		temp.setFecha(LocalDate.parse("1989-04-04"));
		temp.setNacionalidad("ALEMAN");
		return temp;
	}
}
