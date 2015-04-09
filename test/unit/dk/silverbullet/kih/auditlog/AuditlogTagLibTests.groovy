package dk.silverbullet.kih.auditlog

import dk.silverbullet.kih.api.auditlog.AuditLogLookup
import grails.test.mixin.TestFor
import org.junit.Test

/**
 * See the API for {@link grails.test.mixin.web.GroovyPageUnitTestMixin} for usage instructions
 */
@TestFor(AuditlogTagLib)
class AuditlogTagLibTests {


    def specificTestString = "Overblik over test"
    def specificTestClosure = { g -> "Opret ny side" }

    @Test
    void testLookup() {

        def lookup = [:]
        lookup["test"] = [
                "index" : specificTestString,
                "create" : specificTestClosure
        ]

        def tagLib = new AuditlogTagLib()

        tagLib.auditLogLookupBean = new AuditLogLookup() {

            @Override
            Map retrieve() {
                return [
                        "test" : [
                                "index" : specificTestString,
                                "create" : specificTestClosure
                        ]
                ]
            }

        }
    }

}
