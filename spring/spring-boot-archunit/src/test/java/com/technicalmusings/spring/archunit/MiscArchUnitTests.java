package com.technicalmusings.spring.archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import java.util.logging.Logger;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.GeneralCodingRules.*;


@AnalyzeClasses(packages = "com.technicalmusings.spring")
public class MiscArchUnitTests {

    @ArchTest
    private final ArchRule layered_architecture_should_have_top_to_bottom_control_flow =
            layeredArchitecture()
                    .consideringAllDependencies()
                    .layer("Controller").definedBy("..controller..")
                    .layer("Service").definedBy("..service..")
                    .layer("Repository").definedBy("..repository..")

                    .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                    .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
                    .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service");

    @ArchTest
    private final ArchRule all_service_classes_should_be_annotated_with_service =
            classes().that().resideInAPackage("..service..").
                    should().beAnnotatedWith("Service");

    @ArchTest
    private final ArchRule no_classes_should_use_field_injection = NO_CLASSES_SHOULD_USE_FIELD_INJECTION;

    @ArchTest
    private final ArchRule no_classes_should_use_system_out = NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;

    @ArchTest
    private final ArchRule no_classes_should_throw_generic_exception = NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;

    @ArchTest
    private final ArchRule loggers_should_be_private_static_final =
            fields().that().haveRawType(Logger.class)
                    .should().bePrivate()
                    .andShould().beStatic()
                    .andShould().beFinal();
}
