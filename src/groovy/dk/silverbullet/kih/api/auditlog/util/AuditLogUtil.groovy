package dk.silverbullet.kih.api.auditlog.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.servlet.http.HttpServletRequest

class AuditLogUtil {
    private static Logger log = LoggerFactory.getLogger(AuditLogUtil.class.name)

    public static String extractIpFromRequest(HttpServletRequest request) {
        String retVal
        def foundProxyiedIp = false

        def headersList = [ "HTTP_CLIENT_IP",
                "HTTP_X_FORWARDED_FOR",
                "HTTP_X_FORWARDED",
                "HTTP_X_CLUSTER_CLIENT_IP",
                "HTTP_FORWARDED_FOR",
                "HTTP_FORWARDED",
                "CLIENT_IP",
                "X_FORWARDED_FOR",
                "X_FORWARDED",
                "X_CLUSTER_CLIENT_IP",
                "FORWARDED_FOR",
                "FORWARDED",
                "x-forwarded-for"
        ]


        headersList.each() { header ->

            if (request.getHeader(header)) {
                retVal = request.getHeader(header)
                foundProxyiedIp = true

                log.debug "Found proxyied ip ? " + foundProxyiedIp + "in header: " + header + " value: " + retVal
            }
        }

        if (!foundProxyiedIp) {
            retVal = request.getRemoteAddr()
        }

        log.debug "Found proxyied ip ? " + foundProxyiedIp + " value: " + retVal
        return retVal
    }


}
