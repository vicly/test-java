package vic.test.jpa.relation.one2one;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import vic.test.jpa.BaseEntity;

import com.google.common.base.MoreObjects;

@Entity
public class AccountProfile extends BaseEntity {

	@Column
	private String nickname;
	
	@OneToOne(mappedBy = "profile")
	private Account account;
	
	public AccountProfile() {}
	
	public String getNickname() {
		return this.nickname;
	}

	public AccountProfile setNickname(String nickname) {
		this.nickname = nickname;
		return this;
	}
	
	public Account getAccount() {
		return this.account;
	}
	
	public AccountProfile setAccount(Account account) {
		this.account = account;
		return this;
	}

	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", getId())
				.add("nickname", this.nickname).toString();
	}
	
}
