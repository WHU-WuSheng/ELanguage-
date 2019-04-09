package springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zzz.springboot.service.impl.MailService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MyTest {
	@Autowired
	private MailService mailService;

	@Test
	public void test() {
		System.out.println("hhh");
	}
}
