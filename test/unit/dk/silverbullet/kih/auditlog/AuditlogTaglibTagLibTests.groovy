package dk.silverbullet.kih.auditlog

import dk.silverbullet.kih.api.auditlog.AuditLogLookup
import grails.test.mixin.TestFor
import org.junit.Test

/**
 * See the API for {@link grails.test.mixin.web.GroovyPageUnitTestMixin} for usage instructions
 */
@TestFor(AuditlogTaglibTagLib)
class AuditlogTaglibTagLibTests {


    def specificTestString = "Overblik over test"

    @Test
    void testLookup() {

        def lookup = [:]
        lookup["test"] = ["index": specificTestString]

        def taglib = new AuditlogTaglibTagLib()

        tagLib.auditLogLookupBean = new AuditLogLookup() {

            @Override
            Map retrieve() {
                return ["test" : ["index" : specificTestString]]
            }

        }
    }

}
