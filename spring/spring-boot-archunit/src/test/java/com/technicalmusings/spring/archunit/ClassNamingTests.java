package com.technicalmusings.spring.archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

@AnalyzeClasses(packages = "com.technicalmusings.spring")
public class ClassNamingTests {

    @ArchTest
    private final ArchRule classes_in_controller_packages_should_have_controller_suffix =
            classes().that().resideInAPackage("..controller..").
            should().haveSimpleNameEndingWith("Controller");

}
