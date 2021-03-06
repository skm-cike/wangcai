package com.wangcai.member.base.vo;

import org.springframework.security.core.GrantedAuthority;

public class MemRole implements GrantedAuthority{
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MEM_BASE_ROLE.ROLEID
     *
     * @mbggenerated Wed Apr 01 17:00:37 CST 2015
     */
    private Long roleid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MEM_BASE_ROLE.ROLENAME
     *
     * @mbggenerated Wed Apr 01 17:00:37 CST 2015
     */
    private String rolename;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MEM_BASE_ROLE.ROLEDESC
     *
     * @mbggenerated Wed Apr 01 17:00:37 CST 2015
     */
    private String roledesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MEM_BASE_ROLE.ENABLE
     *
     * @mbggenerated Wed Apr 01 17:00:37 CST 2015
     */
    private Integer enable;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MEM_BASE_ROLE.ROLEID
     *
     * @return the value of MEM_BASE_ROLE.ROLEID
     *
     * @mbggenerated Wed Apr 01 17:00:37 CST 2015
     */
    public Long getRoleid() {
        return roleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MEM_BASE_ROLE.ROLEID
     *
     * @param roleid the value for MEM_BASE_ROLE.ROLEID
     *
     * @mbggenerated Wed Apr 01 17:00:37 CST 2015
     */
    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MEM_BASE_ROLE.ROLENAME
     *
     * @return the value of MEM_BASE_ROLE.ROLENAME
     *
     * @mbggenerated Wed Apr 01 17:00:37 CST 2015
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MEM_BASE_ROLE.ROLENAME
     *
     * @param rolename the value for MEM_BASE_ROLE.ROLENAME
     *
     * @mbggenerated Wed Apr 01 17:00:37 CST 2015
     */
    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MEM_BASE_ROLE.ROLEDESC
     *
     * @return the value of MEM_BASE_ROLE.ROLEDESC
     *
     * @mbggenerated Wed Apr 01 17:00:37 CST 2015
     */
    public String getRoledesc() {
        return roledesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MEM_BASE_ROLE.ROLEDESC
     *
     * @param roledesc the value for MEM_BASE_ROLE.ROLEDESC
     *
     * @mbggenerated Wed Apr 01 17:00:37 CST 2015
     */
    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc == null ? null : roledesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MEM_BASE_ROLE.ENABLE
     *
     * @return the value of MEM_BASE_ROLE.ENABLE
     *
     * @mbggenerated Wed Apr 01 17:00:37 CST 2015
     */
    public Integer getEnable() {
        return enable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MEM_BASE_ROLE.ENABLE
     *
     * @param enable the value for MEM_BASE_ROLE.ENABLE
     *
     * @mbggenerated Wed Apr 01 17:00:37 CST 2015
     */
    public void setEnable(Integer enable) {
        this.enable = enable;
    }

	public String getAuthority() {
		return this.rolename;
	}
}