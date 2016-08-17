package com.githubsample.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.githubsample.ApplicationComponent;
import com.githubsample.GitHubSampleApp;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by Rory_McCormack on 15/08/2016.
 */
public abstract class BaseActivity<P extends Presenter> extends AppCompatActivity implements BaseView {

    private P presenter;

    @LayoutRes
    protected abstract int getLayoutId();

    @Inject
    protected void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    protected P getPresenter() {
        return presenter;
    }

    protected abstract void injectDependencies(ApplicationComponent applicationComponent);

    public ApplicationComponent getApplicationComponent() {
        return GitHubSampleApp.get(this).getComponent();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        ButterKnife.bind(this);

        injectDependencies(getApplicationComponent());
        presenter.attachView(this);
    }

    @Override
    public void displayError(@StringRes int messageId) {
        Toast.makeText(this, messageId, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
