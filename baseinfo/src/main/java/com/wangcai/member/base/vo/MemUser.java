package com.wangcai.member.base.vo;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MemUser implements UserDetails {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MEM_BASE_USER.ID
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MEM_BASE_USER.CARDID
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    private String cardid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MEM_BASE_USER.LOGIN
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    private String login;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MEM_BASE_USER.USERNAME
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MEM_BASE_USER.PASSWORD
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MEM_BASE_USER.ACCOUNTNONEXPIRED
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    private Integer accountnonexpired;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MEM_BASE_USER.ACCOUNTNONLOCKED
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    private Integer accountnonlocked;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MEM_BASE_USER.CREDENTIALSNONEXPIRED
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    private Integer credentialsnonexpired;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MEM_BASE_USER.ENABLE
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    private Integer enable;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MEM_BASE_USER.SEX
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    private Integer sex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MEM_BASE_USER.BIRTHDAY
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    private String birthday;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MEM_BASE_USER.TELEPHONE
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    private String telephone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MEM_BASE_USER.MOBILE
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    private String mobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MEM_BASE_USER.EMERGENCYPHONE
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    private String emergencyphone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MEM_BASE_USER.EMAIL
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MEM_BASE_USER.HOMEPAGE
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    private String homepage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MEM_BASE_USER.ADDRESS
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MEM_BASE_USER.ZIPCODE
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    private String zipcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MEM_BASE_USER.OPENDATE
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    private Date opendate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MEM_BASE_USER.VALIDATEDATE
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    private Date validatedate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MEM_BASE_USER.ID
     *
     * @return the value of MEM_BASE_USER.ID
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MEM_BASE_USER.ID
     *
     * @param id the value for MEM_BASE_USER.ID
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MEM_BASE_USER.CARDID
     *
     * @return the value of MEM_BASE_USER.CARDID
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public String getCardid() {
        return cardid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MEM_BASE_USER.CARDID
     *
     * @param cardid the value for MEM_BASE_USER.CARDID
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public void setCardid(String cardid) {
        this.cardid = cardid == null ? null : cardid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MEM_BASE_USER.LOGIN
     *
     * @return the value of MEM_BASE_USER.LOGIN
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public String getLogin() {
        return login;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MEM_BASE_USER.LOGIN
     *
     * @param login the value for MEM_BASE_USER.LOGIN
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public void setLogin(String login) {
        this.login = login == null ? null : login.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MEM_BASE_USER.USERNAME
     *
     * @return the value of MEM_BASE_USER.USERNAME
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MEM_BASE_USER.USERNAME
     *
     * @param username the value for MEM_BASE_USER.USERNAME
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MEM_BASE_USER.PASSWORD
     *
     * @return the value of MEM_BASE_USER.PASSWORD
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MEM_BASE_USER.PASSWORD
     *
     * @param password the value for MEM_BASE_USER.PASSWORD
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MEM_BASE_USER.ACCOUNTNONEXPIRED
     *
     * @return the value of MEM_BASE_USER.ACCOUNTNONEXPIRED
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public Integer getAccountnonexpired() {
        return accountnonexpired;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MEM_BASE_USER.ACCOUNTNONEXPIRED
     *
     * @param accountnonexpired the value for MEM_BASE_USER.ACCOUNTNONEXPIRED
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public void setAccountnonexpired(Integer accountnonexpired) {
        this.accountnonexpired = accountnonexpired;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MEM_BASE_USER.ACCOUNTNONLOCKED
     *
     * @return the value of MEM_BASE_USER.ACCOUNTNONLOCKED
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public Integer getAccountnonlocked() {
        return accountnonlocked;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MEM_BASE_USER.ACCOUNTNONLOCKED
     *
     * @param accountnonlocked the value for MEM_BASE_USER.ACCOUNTNONLOCKED
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public void setAccountnonlocked(Integer accountnonlocked) {
        this.accountnonlocked = accountnonlocked;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MEM_BASE_USER.CREDENTIALSNONEXPIRED
     *
     * @return the value of MEM_BASE_USER.CREDENTIALSNONEXPIRED
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public Integer getCredentialsnonexpired() {
        return credentialsnonexpired;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MEM_BASE_USER.CREDENTIALSNONEXPIRED
     *
     * @param credentialsnonexpired the value for MEM_BASE_USER.CREDENTIALSNONEXPIRED
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public void setCredentialsnonexpired(Integer credentialsnonexpired) {
        this.credentialsnonexpired = credentialsnonexpired;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MEM_BASE_USER.ENABLE
     *
     * @return the value of MEM_BASE_USER.ENABLE
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public Integer getEnable() {
        return enable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MEM_BASE_USER.ENABLE
     *
     * @param enable the value for MEM_BASE_USER.ENABLE
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MEM_BASE_USER.SEX
     *
     * @return the value of MEM_BASE_USER.SEX
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MEM_BASE_USER.SEX
     *
     * @param sex the value for MEM_BASE_USER.SEX
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MEM_BASE_USER.BIRTHDAY
     *
     * @return the value of MEM_BASE_USER.BIRTHDAY
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MEM_BASE_USER.BIRTHDAY
     *
     * @param birthday the value for MEM_BASE_USER.BIRTHDAY
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MEM_BASE_USER.TELEPHONE
     *
     * @return the value of MEM_BASE_USER.TELEPHONE
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MEM_BASE_USER.TELEPHONE
     *
     * @param telephone the value for MEM_BASE_USER.TELEPHONE
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MEM_BASE_USER.MOBILE
     *
     * @return the value of MEM_BASE_USER.MOBILE
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MEM_BASE_USER.MOBILE
     *
     * @param mobile the value for MEM_BASE_USER.MOBILE
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MEM_BASE_USER.EMERGENCYPHONE
     *
     * @return the value of MEM_BASE_USER.EMERGENCYPHONE
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public String getEmergencyphone() {
        return emergencyphone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MEM_BASE_USER.EMERGENCYPHONE
     *
     * @param emergencyphone the value for MEM_BASE_USER.EMERGENCYPHONE
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public void setEmergencyphone(String emergencyphone) {
        this.emergencyphone = emergencyphone == null ? null : emergencyphone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MEM_BASE_USER.EMAIL
     *
     * @return the value of MEM_BASE_USER.EMAIL
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MEM_BASE_USER.EMAIL
     *
     * @param email the value for MEM_BASE_USER.EMAIL
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MEM_BASE_USER.HOMEPAGE
     *
     * @return the value of MEM_BASE_USER.HOMEPAGE
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public String getHomepage() {
        return homepage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MEM_BASE_USER.HOMEPAGE
     *
     * @param homepage the value for MEM_BASE_USER.HOMEPAGE
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public void setHomepage(String homepage) {
        this.homepage = homepage == null ? null : homepage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MEM_BASE_USER.ADDRESS
     *
     * @return the value of MEM_BASE_USER.ADDRESS
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MEM_BASE_USER.ADDRESS
     *
     * @param address the value for MEM_BASE_USER.ADDRESS
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MEM_BASE_USER.ZIPCODE
     *
     * @return the value of MEM_BASE_USER.ZIPCODE
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MEM_BASE_USER.ZIPCODE
     *
     * @param zipcode the value for MEM_BASE_USER.ZIPCODE
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode == null ? null : zipcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MEM_BASE_USER.OPENDATE
     *
     * @return the value of MEM_BASE_USER.OPENDATE
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public Date getOpendate() {
        return opendate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MEM_BASE_USER.OPENDATE
     *
     * @param opendate the value for MEM_BASE_USER.OPENDATE
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public void setOpendate(Date opendate) {
        this.opendate = opendate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MEM_BASE_USER.VALIDATEDATE
     *
     * @return the value of MEM_BASE_USER.VALIDATEDATE
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public Date getValidatedate() {
        return validatedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MEM_BASE_USER.VALIDATEDATE
     *
     * @param validatedate the value for MEM_BASE_USER.VALIDATEDATE
     *
     * @mbggenerated Wed Apr 01 16:59:03 CST 2015
     */
    public void setValidatedate(Date validatedate) {
        this.validatedate = validatedate;
    }

    private Collection<? extends GrantedAuthority> authorities;
    
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public void setAuthorities(Set<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public boolean isAccountNonExpired() {
		return this.accountnonexpired==1?true:false;
	}

	public boolean isAccountNonLocked() {
		return this.accountnonlocked==1?true:false;
	}

	public boolean isCredentialsNonExpired() {
		return this.credentialsnonexpired==1?true:false;
	}

	public boolean isEnabled() {
		return this.enable==1?true:false;
	}
}