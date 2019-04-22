package br.edu.opi.manager.delegate.dtos;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;

@Component
public class DelegateOutput {

    @ApiModelProperty
    private Long id;

    @ApiModelProperty(example = "Jose")
    private String name;

    @ApiModelProperty(example = "user@user.com", required = true)
    private String username;

    @ApiModelProperty(example = "P@ssw0rd")
    private String password;

    @ApiModelProperty(example = "950.213.940-29")
    private String cpf;

    @ApiModelProperty(example = "83-88888888")
    private String phone;

    @ApiModelProperty(example = "1")
    private Long profileId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
