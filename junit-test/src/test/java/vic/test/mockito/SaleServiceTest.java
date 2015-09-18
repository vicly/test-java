package vic.test.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class SaleServiceTest {

	private UserDao mockDao;
	private SaleService sale;
	private double delta = 0.1d;

	
	@Before
	public void setUp() {
		this.mockDao = mock(UserDao.class);
		when(this.mockDao.getUserType("tom")).thenReturn(UserType.NORMAL);
		when(this.mockDao.getUserType("jack")).thenReturn(UserType.VIP);
		when(this.mockDao.getUserType("vic")).thenReturn(UserType.SVIP);
		when(this.mockDao.getUserType("unknown")).thenReturn(UserType.UNKNOWN);
		this.sale = new SaleService(this.mockDao);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testUnknowUser() {
		assertEquals(5d, this.sale.getSellingPriceForUser("unknown"), delta);
	}
	
	@Test
	public void testPrice() {
		assertEquals(12d, this.sale.getSellingPriceForUser("tom"), delta); 
		assertEquals(10d, this.sale.getSellingPriceForUser("jack"), delta);
		assertEquals(5d, this.sale.getSellingPriceForUser("vic"), delta);
		verify(this.mockDao).getUserType("vic");
	}
	
}
