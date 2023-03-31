package ru.bestaford.ariovale.service.impl;

import com.google.inject.Injector;
import ru.bestaford.ariovale.form.Form;
import ru.bestaford.ariovale.service.FormService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class FormServiceImpl implements FormService {

    private final Injector injector;

    @Inject
    public FormServiceImpl(Injector injector) {
        this.injector = injector;
    }

    @Override
    public <T extends Form> T createForm(Class<T> formClass) {
        return injector.getInstance(formClass);
    }
}