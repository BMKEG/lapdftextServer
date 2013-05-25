package edu.isi.bmkeg.utils.secured;

import java.util.Map;

import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.security3.AuthenticationResultUtils;
import org.springframework.stereotype.Service;


@RemotingDestination(value="securityHelper")
@Service
public class Security3Helper {

    public Map<String, Object> getAuthentication() {
        return AuthenticationResultUtils.getAuthenticationResult();
    }
}
