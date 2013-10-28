package pl.indecoders.archetype.service.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.indecoders.archetype.domain.account.Account;
import pl.indecoders.archetype.domain.account.Profile;
import pl.indecoders.archetype.domain.address.Address;
import pl.indecoders.archetype.domain.payment.BankInformations;
import pl.indecoders.archetype.form.account.PersonalInformationForm;
import pl.indecoders.archetype.repository.accout.AccountRepository;

/**
 * The Class ProfileService.
 * @author Mateusz
 */
@Service
public class ProfileService {

	@Autowired
	private AccountRepository accountRepository;
	
	public PersonalInformationForm preparePersonalInformationsForm(Account account) {
		Profile profile = null;
		
		if(account.getProfile() != null) {
			profile = account.getProfile();
		} else {
			profile = new Profile();
		}
		
		PersonalInformationForm form = new PersonalInformationForm();
		prepareBasicInformations(profile, form);
		prepareAddressInformations(profile, form);
		prepareBankInformations(profile, form);
		
		return form;
	}

	private void prepareBasicInformations(Profile profile, PersonalInformationForm form) {
		form.setName(profile.getName() != null ? profile.getName() : "");
		form.setContactPhone(profile.getContactPhone() != null ? profile.getContactPhone() : "");
		form.setNip(profile.getNip() != null ? profile.getNip() : "");
		form.setFaxPhone(profile.getFaxPhone() != null ? profile.getFaxPhone() : "");
	}

	private void prepareAddressInformations(Profile profile, PersonalInformationForm form) {
		if(profile.getAddress() != null) {
			form.setCity(profile.getAddress().getCity() != null ? profile.getAddress().getCity() : "");
			form.setStreet(profile.getAddress().getStreet() != null ? profile.getAddress().getStreet() : "");
			form.setPostal(profile.getAddress().getPostalCode() != null ? profile.getAddress().getPostalCode() : "");
			form.setHomeNumber(profile.getAddress().getHomeNumber() != null ? profile.getAddress().getHomeNumber() : "");
			return;
		}
		form.setCity("");
		form.setStreet("");
		form.setPostal("");
		form.setHomeNumber("");
	}
	
	private void prepareBankInformations(Profile profile, PersonalInformationForm form) {
		if(profile.getBankInformations() != null) {
			form.setBankName(profile.getBankInformations().getBankName() != null ? profile.getBankInformations().getBankName() : "");
			form.setBankNumber(profile.getBankInformations().getBankAccountNumber() != null ? profile.getBankInformations().getBankAccountNumber() : "");
			return;
		}
		form.setBankName("");
		form.setBankNumber("");
	}

	public void processPersonalInformationsForm(PersonalInformationForm form, Account account) {
		Profile profile = new Profile();
		
		profile.setName(form.getName() != null ? form.getName() : null);
		profile.setNip(form.getNip() != null ? form.getNip() : null);
		profile.setContactPhone(form.getContactPhone() != null ? form.getContactPhone() : null);
		profile.setFaxPhone(form.getFaxPhone() != null ? form.getFaxPhone() : null);

		if(form.getCity() != null || form.getStreet() != null || form.getHomeNumber() != null || form.getPostal() != null) {
			processAddressInformations(profile, form);
		}
		
		if(form.getBankName() != null || form.getBankNumber() != null) {
			processBankInformations(profile, form);
		}
		
		account.setProfile(profile);
		accountRepository.saveAndFlush(account);
	}

	private void processAddressInformations(Profile profile, PersonalInformationForm form) {
		Address address = new Address();
		address.setCity(form.getCity() != null ? form.getCity() : null);
		address.setPostalCode(form.getPostal() != null ? form.getPostal() : null);
		address.setStreet(form.getStreet() != null ? form.getStreet() : null);
		address.setHomeNumber(form.getHomeNumber() != null ? form.getHomeNumber() : null);
		profile.setAddress(address);
	}
	
	private void processBankInformations(Profile profile, PersonalInformationForm form) {
		BankInformations bankInformations = new BankInformations();
		bankInformations.setBankAccountNumber(form.getBankNumber() != null ? form.getBankNumber() : null);
		bankInformations.setBankName(form.getBankName() != null ? form.getBankName() : null);
		profile.setBankInformations(bankInformations);
	}

}
