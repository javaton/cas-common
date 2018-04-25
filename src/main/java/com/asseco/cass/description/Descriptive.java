package com.asseco.cass.description;

import java.util.Locale;

public interface Descriptive {

    String getCode();
    String getDescription(Locale locale);
    String getDescription();
    boolean isDescriptionMissing(Locale locale);
}

