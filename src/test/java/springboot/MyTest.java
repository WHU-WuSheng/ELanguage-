package springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zzz.springboot.Application;
import com.zzz.springboot.entity.Mail;
import com.zzz.springboot.service.impl.MailService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MyTest {
	@Autowired
	private MailService mailService;

	@Test
	public void test() {
		mailService.sendMail(new Mail(null, "454050427@qq.com", "379240726@qq.com", "邮件测试", "验证码是：123456", null, null, null, null, null, null));
	}
}
