package com.support.android.vkclient.dagger;

import com.support.android.vkclient.VkClientApp;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ApplicationModule.class,
        LogicModule.class,
        NetModule.class,
        AndroidModule.class,
        PresenterModule.class,
        StorageModule.class
})
public interface AppComponent extends AndroidInjector<VkClientApp> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<VkClientApp> {
    }
}
