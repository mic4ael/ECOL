package pl.indecoders.archetype.form.account;

import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import static pl.indecoders.archetype.navigation.Navigator.EMAIL_REGEX;

import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import pl.indecoders.archetype.validation.annotation.FieldMatch;
import pl.indecoders.archetype.validation.annotation.RegularExpression;
import pl.indecoders.archetype.validation.annotation.UniqueEmail;

@FieldMatch(firstField="password", secondField="repeatedPassword")
public class RegisterAccountForm {

	@RegularExpression(expression = EMAIL_REGEX, expressions = {})
	@NotEmpty
	@UniqueEmail
	private String email;

	@NotEmpty
	@Size(min = 5, max = 25)
	private String password;

	@NotEmpty
	@Size(min = 5, max = 25)
	private String repeatedPassword;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatedPassword() {
		return repeatedPassword;
	}

	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}

	public RegisterAccountForm() {
		super();
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
	}
}
