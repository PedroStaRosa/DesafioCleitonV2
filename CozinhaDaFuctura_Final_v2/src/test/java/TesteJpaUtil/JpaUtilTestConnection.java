package TesteJpaUtil;

import org.junit.Test;

import util.JpaUtil;

public class JpaUtilTestConnection {
	@Test
	public void connectionTest() {
		
		JpaUtil.getEntityManager();
		
	}	
}
