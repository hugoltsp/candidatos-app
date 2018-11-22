package com.teles.candidaturas.api.validator.annotation.groups;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, ConstraintValidation.class})
public interface OrderedValidation {

}
