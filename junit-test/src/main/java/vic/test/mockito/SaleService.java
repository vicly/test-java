package vic.test.mockito;

public class SaleService {

	private UserDao dao;
	
	public SaleService(UserDao dao) {
		this.dao = dao;
	}
	
	public double getSellingPriceForUser(String userId) {
		UserType userType = this.dao.getUserType(userId);
		switch (userType) {
		case NORMAL:
			return 12d;
		case VIP:
			return 10d;
		case SVIP:
			return 5d; 
		default:
			throw new IllegalStateException();
		}
	}
	
}
