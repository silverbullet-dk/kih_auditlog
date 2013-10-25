package dk.silverbullet.kih.api.auditlog.impl

import dk.silverbullet.kih.api.auditlog.AuditLogFactory
import dk.silverbullet.kih.api.auditlog.exception.AuditLogException
import dk.silverbullet.kih.auditlog.AuditLogControllerEntity
import dk.silverbullet.kih.auditlog.AuditLogEntry
import grails.test.mixin.Mock
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import org.codehaus.groovy.grails.plugins.testing.GrailsMockHttpServletRequest
import org.codehaus.groovy.grails.plugins.testing.GrailsMockHttpServletResponse
import org.codehaus.groovy.grails.plugins.testing.GrailsMockHttpSession
import org.junit.Before
import org.junit.Test

@TestMixin(GrailsUnitTestMixin)
@Mock([AuditLogEntry, AuditLogControllerEntity])
class AuditLogImplTests {
    def session, request, response, auditLog, user, controller, action

    @Before
    void setup() {
        request = new GrailsMockHttpServletRequest()
        session = new GrailsMockHttpSession()
        response = new GrailsMockHttpServletResponse()
        auditLog = AuditLogFactory.createAuditLogging()

        user = "test"
        controller = "controller"
        action = "action"
    }

    @Test
    void testStartRequestAllValid() {
        def id = auditLog.startRequest(session,request, user, controller,action)
        assert id !=null
    }

    @Test
    void testStartRequestNullUser() {
        user = null
        def id = auditLog.startRequest(session,request, user, controller,action)
        assert id !=null
    }

    @Test
    void testStartRequestNullAction() {
        action = null
        def id = auditLog.startRequest(session,request, user, controller,action)
        assert id !=null
    }

    @Test
    void testStartRequestNullController() {
        controller = null
        def id = auditLog.startRequest(session,request, user, controller,action)
        assert id !=null
    }


    @Test
    void testEndRequest() {

        def id = auditLog.startRequest(session,request, user, controller,action)


        def testPassed = true
        try {
            auditLog.endRequest(session, response, id, true, new HashMap())

        } catch (AuditLogException e) {
            fail("Caught exception")
        }

        assert testPassed == true

        id = auditLog.startRequest(session,request, user, controller,action)
        testPassed = true
        try {
            auditLog.endRequest(session, response, id, false, null)
        } catch (AuditLogException e) {
            fail("Caught exception")
        }
        assert testPassed == true

        id = auditLog.startRequest(session,request, user, controller,action)
        testPassed = true
        try {
            auditLog.endRequest(session, response, id, true, null)
        } catch (AuditLogException e) {
            fail("Caught exception")
        }
        assert testPassed == true

        id = auditLog.startRequest(session,request, user, controller,action)
        testPassed = true
        try {
            auditLog.endRequest(session, response, null, true, null)
        } catch (AuditLogException e) {
            fail("Caught exception")
        }
        assert testPassed == true


    }



}
