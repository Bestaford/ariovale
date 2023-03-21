package ru.bestaford.ariovale.service;

import ru.bestaford.ariovale.form.base.Form;

public interface FormService {

    <T extends Form> T createForm(Class<T> formClass);

}