package junit.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.dream.bean.user.Gender;
import cn.dream.bean.user.User;
import cn.dream.bean.user.ContactInfo;
import cn.dream.service.book.ShelfService;
import cn.dream.service.user.UserService;

public class UserServiceTest {
	private static UserService userService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext cxt = new ClassPathXmlApplicationContext(
					"beans.xml");
			userService = (UserService) cxt.getBean("userServiceBean");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void saveUser() {
		User u = new User("123456", "xuhuihui", "123456", "xuhuihui@163.com");
		userService.exsit(u.getLibrarycard(), u.getUsername());
		userService.update(u);
	}

	@Test
	public void testExsit() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testClear() {
		fail("Not yet implemented");
	}

	@Test
	public void testSave() {
		 User user=new User("ly");
		 user.setGender(Gender.MAN);
		 user.setLibrarycard("56566656");
		userService.save(user);
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		userService.delete("123451");
	}

	@Test
	public void testFind() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetScrollDataClassOfTIntIntStringObjectArrayLinkedHashMapOfStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetScrollDataClassOfTIntIntStringObjectArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetScrollDataClassOfTIntIntLinkedHashMapOfStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetScrollDataClassOfTIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetScrollDataClassOfT() {
		fail("Not yet implemented");
	}

}
