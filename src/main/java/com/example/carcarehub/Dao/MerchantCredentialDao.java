package com.example.carcarehub.Dao;

import com.example.carcarehub.domain.Merchant;
import com.example.carcarehub.domain.MerchantCredential;

public interface MerchantCredentialDao {
    MerchantCredential createMerchantCredential(MerchantCredential merchantCredential);

    MerchantCredential findByUserName(String userName);

    void deleteMerchantCredentials(Merchant merchant);

}
