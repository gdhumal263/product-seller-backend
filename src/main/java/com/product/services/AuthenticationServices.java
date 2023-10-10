package com.product.services;

import com.product.entities.User;

public interface AuthenticationServices {

	User signInAndReturnJWT(User signInRequest);

}
