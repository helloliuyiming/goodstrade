package me.lym.service;

import me.lym.entity.Address;

import java.util.List;

public interface AddressService {
    int addAddress(Address address);

    int deleteAddress(Address address);

    int deleteAddressByID(int id);

    int updateAddress(Address address);

    List<Address> queryAllAddress(int userId);

    Address queryByID(int addressId);
}
