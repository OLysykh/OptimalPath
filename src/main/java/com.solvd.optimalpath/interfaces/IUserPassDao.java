package com.solvd.optimalpath.interfaces;


import com.solvd.optimalpath.models.AnimalsModel;
import com.solvd.optimalpath.models.UserPassModel;

import java.util.List;

public interface IUserPassDao {
    void createUserPass(UserPassModel userPassModel);

    void updateUserPass(UserPassModel userPassModel);

    void deleteUserPass(UserPassModel userPassModel);

    UserPassModel getUserPassById(int id);

    List<UserPassModel> getALLUserPass();

}
