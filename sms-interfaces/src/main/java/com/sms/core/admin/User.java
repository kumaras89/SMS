package com.sms.core.admin;

import com.sms.core.BaseModel;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "SMS_MA_USER")
public class User extends BaseModel {

	@Column(name = "US_FIRST_NAME")
	private String firstName;

	@Column(name = "US_LAST_NAME")
	private String lastName;

	@Column(name = "US_BRANCH")
	private String branch;

	@Column(name = "US_NAME")
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "US_ROLE")
	private UserRole role;

	@Column(name = "US_PASSWORD")
	private String password;

	public User() {
		super();
	}

	private User(final Builder builder) {
		super(builder);

		this.name = builder.name.orElse(null);
		this.role = builder.userRole.orElse(null);
		this.password = builder.password.orElse(null);
		this.firstName = builder.firstName.orElse(null);
		this.lastName = builder.lastName.orElse(null);
		this.branch = builder.branch.orElse(null);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder toBuilder(final User user) {

		return builder().withId(user.getId())
				.withRole(user.getRole())
				.withPassword(user.getPassword())
				.withBranch(user.getBranch())
				.withFirstName(user.getFirstName())
				.withLastName(user.getLastName());

	}

	public String getName() {
		return name;
	}

	public UserRole getRole() {
		return role;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getBranch() {
		return branch;
	}

	public static class Builder extends BaseModel.Builder<User, Builder> {

		private Optional<String> firstName = Optional.empty();
		private Optional<String> lastName = Optional.empty();
		private Optional<String> branch = Optional.empty();
		private Optional<String> name = Optional.empty();
		private Optional<UserRole> userRole = Optional.empty();
		private Optional<String> password = Optional.empty();

		private Builder() {
			super();
		}

		public Builder withName(final String theName) {
			this.name = Optional.of(theName);
			return this;
		}

		public Builder withFirstName(final String theName) {
			this.firstName = Optional.of(theName);
			return this;
		}

		public Builder withLastName(final String theName) {
			this.lastName = Optional.of(theName);
			return this;
		}

		public Builder withBranch(final String theName) {
			this.branch = Optional.of(theName);
			return this;
		}

		public Builder withRole(final UserRole theUserRole) {
			this.userRole = Optional.of(theUserRole);
			return this;
		}

		public Builder withPassword(final String thePassword) {
			this.password = Optional.of(thePassword);
			return this;
		}

		public User build() {
			return new User(this);
		}

		@Override
		protected Builder getThis() {
			return this;
		}
	}

}
