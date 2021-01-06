package me.lym.service.impl;

import me.lym.dao.AddressDao;
import me.lym.entity.Address;
import me.lym.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDao addressDao;

    @Override
    public int addAddress(Address address) {
        List<Address> allAddress = queryAllAddress(address.getUserId());
        if (address.isDefault()) {
            for (Address addr : allAddress) {
                if (addr.isDefault()) {
                    addr.setIsDefault(false);
                    addressDao.updateAddress(addr);
                }
            }
        }
        if (allAddress == null || allAddress.size() == 0) {
            address.setIsDefault(true);
        }

        return addressDao.addAddress(address);
    }

    @Override
    public int deleteAddress(Address address) {
        return deleteAddressByID(address.getId());
    }

    @Override
    public int deleteAddressByID(int id) {
        return addressDao.deleteAddressByID(id);
    }

    @Override
    public int updateAddress(Address address) {
        Address oldAddr = addressDao.queryByID(address.getId());
        if (oldAddr.isDefault()) {
            address.setIsDefault(true);
        }
        if (!oldAddr.isDefault() && address.isDefault()) {
            List<Address> allAddress = addressDao.queryAllAddress(address.getUserId());
            for (Address addr : allAddress) {
                if (addr.isDefault()) {
                    addr.setIsDefault(false);
                    addressDao.updateAddress(addr);
                }
            }
        }
        return addressDao.updateAddress(address);
    }


    @Override
    public List<Address> queryAllAddress(int userId) {
        return addressDao.queryAllAddress(userId);
    }

    @Override
    public Address queryByID(int addressId) {
        return addressDao.queryByID(addressId);
    }

}
