package com.githubsample.entity.transformer;


import com.githubsample.entity.UserEntity;
import com.githubsample.model.User;
import com.githubsample.transformer.AbstractTransformer;

/**
 * Created by Rory_McCormack on 16/08/2016.
 */
public class UserEntityTransformer extends AbstractTransformer<UserEntity, User> {

    @Override
    public User transform(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        return User.builder()
                .avatar(userEntity.getAvatar())
                .url(userEntity.getUrl())
                .login(userEntity.getName())
                .build();
    }
}
