package com.example.carcarehub.Dao;

import com.example.carcarehub.domain.Merchant;

public interface MerchantDao {
    public Merchant registerMerchant(Merchant merchant);
    Merchant findMerchantById(Integer id);

    Merchant updateMerchant(Merchant merchant);

    void deleteMerchant(Merchant merchant);
    Merchant findByStationName(String stationName);
}
