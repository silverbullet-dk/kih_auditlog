grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"


grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        grailsCentral()
        // uncomment the below to enable remote dependency resolution
        // from public Maven repositories
        //mavenLocal()
        //mavenCentral()
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"

        mavenRepo name: "Silverbullet Ext", root: "http://ci.silverbullet.dk/artifactory/ext-release-local"


    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        // runtime 'mysql:mysql-connector-java:5.1.18'
    }

    plugins {
        build(":tomcat:$grailsVersion",
              ":release:2.0.3",
              ":rest-client-builder:1.0.2") {
            export = false
        }
        runtime(":spring-security-core:1.2.7.2") {
            export = true
        }

        runtime(":hibernate:$grailsVersion") {
            export = false
        }


        test(":code-coverage:1.2.5",
                ":codenarc:0.17") {
            export = false
        }


    }
}

codenarc.reports = {

// Each report definition is of the form: // REPORT-NAME(REPORT-TYPE) { // PROPERTY-NAME = PROPERTY-VALUE // PROPERTY-NAME = PROPERTY-VALUE // }

    MyXmlReport('xml') { // The report name "MyXmlReport" is user-defined; Report type is 'xml'
        outputFile = 'target/test-reports/CodeNarc-Report.xml' // Set the 'outputFile' property of the (XML) Report
    }

    MyXmlReport('html') { // The report name "MyXmlReport" is user-defined; Report type is 'xml'
        outputFile = 'target/test-reports/CodeNarc-Report.html' // Set the 'outputFile' property of the (XML) Report
    }

}

codenarc.properties = {
    // Each property definition is of the form:  RULE.PROPERTY-NAME = PROPERTY-VALUE
    GrailsPublicControllerMethod.enabled = false
    EmptyIfStatement.priority = 1
    coberturaXmlFile = "target/test-reports/cobertura/coverage.xml"

}

codenarc.ruleSetFiles = ["rulesets/basic.xml,rulesets/exceptions.xml, rulesets/imports.xml,rulesets/grails.xml, rulesets/unused.xml, rulesets/size.xml"]
