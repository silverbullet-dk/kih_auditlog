package dk.silverbullet.kih.api.auditlog

import org.junit.Test

/**
 * Created with IntelliJ IDEA.
 * User: lch
 * Date: 10/24/12
 * Time: 10:34 AM
 * To change this template use File | Settings | File Templates.
 */
class AuditLogFactoryTests {

    @Test
    void testCreateAuditLogFactory() {
        AuditLog auditLog = AuditLogFactory.createAuditLogging()
        assert auditLog != null
        assert auditLog instanceof AuditLog
    }

    @Test
    void testSameAuditLogForOneThread() {
        AuditLog auditLog = AuditLogFactory.createAuditLogging()

        for (int i = 0; i < 10;i++) {
           def a = AuditLogFactory.createAuditLogging()
           assert a.equals(auditLog)
        }
    }
}
