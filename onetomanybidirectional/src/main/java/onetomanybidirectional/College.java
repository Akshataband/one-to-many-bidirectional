package onetomanybidirectional;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
	public class College {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "c_id")
		@SequenceGenerator(name = "c_id", initialValue = 100, allocationSize = 1)
		private int id;
		private String name;
		private String Location;
		
		
		@OneToMany(mappedBy="c")
		private List <Student> student;


		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getLocation() {
			return Location;
		}


		public void setLocation(String location) {
			Location = location;
		}


		public List<Student> getStudent() {
			return student;
		}


		public void setStudent(List<Student> student) {
			this.student = student;
		}
	}

