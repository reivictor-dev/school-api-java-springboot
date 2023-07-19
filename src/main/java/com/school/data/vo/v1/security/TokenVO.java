package com.school.data.vo.v1.security;

import java.io.Serializable;
import java.util.Date;

public class TokenVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;
    private Boolean authenticated;
    private Date createdAt;
    private Date expirationAt;
    private String AccessToken;
    private String RefreshToken;

    public TokenVO(String email, Boolean authenticated, Date createdAt, Date expirationAt, String accessToken,
            String refreshToken) {
        this.email = email;
        this.authenticated = authenticated;
        this.createdAt = createdAt;
        this.expirationAt = expirationAt;
        this.AccessToken = accessToken;
        this.RefreshToken = refreshToken;
    }

    public TokenVO() {
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getExpirationAt() {
        return expirationAt;
    }

    public void setExpirationAt(Date expirationAt) {
        this.expirationAt = expirationAt;
    }

    public String getAccessToken() {
        return AccessToken;
    }

    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }

    public String getRefreshToken() {
        return RefreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        RefreshToken = refreshToken;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((authenticated == null) ? 0 : authenticated.hashCode());
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        result = prime * result + ((expirationAt == null) ? 0 : expirationAt.hashCode());
        result = prime * result + ((AccessToken == null) ? 0 : AccessToken.hashCode());
        result = prime * result + ((RefreshToken == null) ? 0 : RefreshToken.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TokenVO other = (TokenVO) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (authenticated == null) {
            if (other.authenticated != null)
                return false;
        } else if (!authenticated.equals(other.authenticated))
            return false;
        if (createdAt == null) {
            if (other.createdAt != null)
                return false;
        } else if (!createdAt.equals(other.createdAt))
            return false;
        if (expirationAt == null) {
            if (other.expirationAt != null)
                return false;
        } else if (!expirationAt.equals(other.expirationAt))
            return false;
        if (AccessToken == null) {
            if (other.AccessToken != null)
                return false;
        } else if (!AccessToken.equals(other.AccessToken))
            return false;
        if (RefreshToken == null) {
            if (other.RefreshToken != null)
                return false;
        } else if (!RefreshToken.equals(other.RefreshToken))
            return false;
        return true;
    }

}
