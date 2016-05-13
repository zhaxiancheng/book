package junit.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.dream.bean.book.BookType;
import cn.dream.bean.book.Shelf;
import cn.dream.service.book.ShelfService;


public class ShelfTest {
	private static ShelfService shelfService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			AbstractApplicationContext cxt = new ClassPathXmlApplicationContext(
					"beans.xml");
			shelfService = (ShelfService) cxt.getBean("shelfServiceBean");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSave() {
		for (int i = 0; i < 20; i++) {
			Shelf shelf = new Shelf();
			shelf.setName("书架"+i);
			shelf.setlocation("二楼"+i);
			shelf.setType(new BookType(3));
			shelfService.save(shelf);
		}
		
			
		

	}
	@Test
	public void testupdate() {
		Shelf shelf = shelfService.find("03ad9f0f-bc03-413c-ab71-cd18cea4ee39");
			shelf.setName("书架");
			shelf.setlocation("二楼");
			shelf.setType(new BookType(3));
			shelfService.update(shelf);
		

	}}
