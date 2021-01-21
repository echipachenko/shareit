package ua.shareit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("ua.shareit");

        noClasses()
            .that()
                .resideInAnyPackage("ua.shareit.service..")
            .or()
                .resideInAnyPackage("ua.shareit.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..ua.shareit.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
