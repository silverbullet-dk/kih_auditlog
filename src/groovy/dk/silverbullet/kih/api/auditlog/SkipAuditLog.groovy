package dk.silverbullet.kih.api.auditlog

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@Target([ElementType.FIELD, ElementType.TYPE, ElementType.METHOD]) // Annotation is for actions as well as controller so target is field and for class
@Retention(RetentionPolicy.RUNTIME) // We need it at run time to identify the annotated controller and action
public @interface SkipAuditLog {
}
