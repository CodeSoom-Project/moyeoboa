package com.jakeseo.moyeoboa.learning_test;

import com.jakeseo.moyeoboa.dto.StudyGroupCreationDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@DisplayName("Java EE Validation 적용")
public class ValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Nested
    @DisplayName("Validator 는")
    class Describe_validator {
        @Nested
        @DisplayName("검증 애노테이션을 가진 필드가 있는 클래스가 있을 때")
        class Context_with_class_having_field_containing_validation_annotations{
            @Test
            @DisplayName("객체를 검증하고, Set<ConstraintViolation> 을 반환한다.")
            void it_returns_set_of_constraint_violation() {
                StudyGroupCreationDto studyGroupCreationDto = StudyGroupCreationDto.builder().name("").build();
                Set<ConstraintViolation<StudyGroupCreationDto>> violations = validator.validate(studyGroupCreationDto);

                Assertions.assertThat(violations).isNotNull();
                Assertions.assertThat(violations.size()).isNotZero();

                for (ConstraintViolation<StudyGroupCreationDto> violation : violations) {
                    String message = violation.getMessage();
                    System.out.println("message = " + message);
                    Path propertyPath = violation.getPropertyPath();
                    System.out.println("propertyPath = " + propertyPath);
                    Class<StudyGroupCreationDto> rootBeanClass = violation.getRootBeanClass();
                    System.out.println("rootBeanClass = " + rootBeanClass);
                    String messageTemplate = violation.getMessageTemplate();
                    System.out.println("messageTemplate = " + messageTemplate);
                }
            }
        }
    }
}
