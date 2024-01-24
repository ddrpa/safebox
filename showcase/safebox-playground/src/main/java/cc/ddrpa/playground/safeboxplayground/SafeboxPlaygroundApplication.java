package cc.ddrpa.playground.safeboxplayground;

import com.baomidou.mybatisplus.autoconfigure.DdlApplicationRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SafeboxPlaygroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafeboxPlaygroundApplication.class, args);
	}

	/**
	 * 临时解决方案
	 * see https://github.com/baomidou/mybatis-plus/issues/5867
	 *
	 * @param ddlList
	 * @return
	 */
	@Bean
	public DdlApplicationRunner ddlApplicationRunner(@Autowired(required = false) List ddlList) {
		return new DdlApplicationRunner(ddlList);
	}
}
