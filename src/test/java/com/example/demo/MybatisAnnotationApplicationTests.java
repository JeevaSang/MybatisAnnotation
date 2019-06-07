package com.example.demo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.model.Employees;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisAnnotationApplicationTests {
	@Autowired
	private EmployeeMapper repo;

	@Before

	public void setUp() throws Exception {
		Employees employee = new Employees();
		employee.setFirstName("kathir");
		employee.setLastName("dev");		
		repo.insert(employee);
	}

	/*@Test
	public void testcase01() {
		Employees emp1 = repo.findById((long) 1);
		emp1.setLastName("SSK");
		assertEquals(0, emp1.getVersion().intValue());
		repo.update(emp1);
	}
	 @Test(expected = Exception.class) //ObjectOptimisticLockingFailure public
	 void testcase02() { Employees emp1 = repo.findById((long) 4);
	  emp1.setVersion(1); repo.update(emp1); System.out.println("Done"); }
	 */

	@Test
	public void testcase003optimisticLockingTest() throws Exception {

		Employees employee1 = repo.findById((long) 2);
		Employees employee2 = repo.findById((long) 2);

		System.out.println(employee1);
		System.out.println(employee2);

		employee1.setFirstName("rai");
		if (repo.update(employee1)) {
			System.out.println("Rai update succeeded.");
		} else {
			System.out.println("Rai update failed");
		}

		employee2.setFirstName("shetty");
		if (repo.update(employee2)) { // Will be updated if without optimistic locking,
			System.out.println("Shetty update succeeded."); // since it's put after employee1.
		} else {
			System.out.println("Shetty update failed.");
		}

		Employees employee3 = repo.findById((long) 2);
		System.out.println(employee3);
		assertEquals(employee3.getFirstName(), "rai");
	}

}
