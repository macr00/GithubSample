package com.githubsample.viewmodel.transformer;

import com.githubsample.model.User;
import com.githubsample.transformer.AbstractTransformer;
import com.githubsample.viewmodel.UserViewModel;

import javax.inject.Inject;

/**
 * Created by Rory_McCormack on 16/08/2016.
 */
public class UserViewModelTransformer extends AbstractTransformer<User, UserViewModel> {

    @Inject
    public UserViewModelTransformer() {
    }

    @Override
    public UserViewModel transform(User user) {
        if (user == null) {
            return null;
        }

        return UserViewModel.builder()
                .avatar(user.getAvatar())
                .login(user.getLogin())
                .url(user.getUrl())
                .build();
    }
}
